package lexical;

import Command.Command;
import Command.AssignCommand;
import Command.CommandBlock;
import Command.ShowCommand;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import model.*;
import Command.*;
/**
 *
 * @author gusta
 */
public class SyntaticalAnalysis {
    private LexicalAnalysis la;
    private Lexeme current;

    public SyntaticalAnalysis(LexicalAnalysis la) throws IOException{
        this.la = la;
        this.current = la.nextToken();
    }

    private void matchToken(TokenType type) throws IOException{
        System.out.println("(" + current.token + ", " + current.type + ") = " + type);
        if(current.type == type){
            current  = la.nextToken();
        }else{
            if(current.type == TokenType.END_OF_FILE ||
               current.type == TokenType.UNEXPECTED_EOF){
                abortEOF();
            }else{
                abortUnexpectToken();
            }
        }

    }

    public CommandBlock start() throws IOException{
        CommandBlock cb = procStatements();
        matchToken(TokenType.END_OF_FILE);
        return cb;
    }

    
    //<statements> ::= <statement> { <statement> }
    private CommandBlock procStatements() throws IOException{
        CommandBlock cb = new CommandBlock();

        Command c = procStatement();
        cb.addCommand(c);

        while(current.type == TokenType.SHOW ||
              current.type == TokenType.VAR ||
                current.type == TokenType.IF ||
                current.type == TokenType.WHILE ||
               current.type == TokenType.FOR){
               c = procStatement();
               cb.addCommand(c);
        }

        return cb;
    }

    
    //<statement> ::= <show> | <assign> | <if> | <while> | <for>
    private Command procStatement() throws IOException{//revisar
        if(current.type == TokenType.SHOW){
            return procShow();
        }else if(current.type == TokenType.VAR){
            return procAssign();
        }else if(current.type == TokenType.FOR){
            return procFor();            
        }else if(current.type == TokenType.WHILE){//<--
            return procWhile();            
        }else if(current.type == TokenType.IF){//<--
            return procIF();            
        }else{
            abortUnexpectToken();    
            return null;
        }
    }

    // <input> ::= input '(' <text> ')'
    private InputIntValue procInput() throws IOException{
        matchToken(TokenType.INPUT);
        matchToken(TokenType.OPEN_PAR);
        Value<?> sv = procText();
        matchToken(TokenType.CLOSE_PAR);

        InputIntValue iiv = new InputIntValue(sv, la.getLine());
        return iiv;
    }

    private Map<String,Variable> myvariables = new HashMap<String,Variable>();
    Variable procVar() throws IOException{
        String varName = current.token;
        matchToken(TokenType.VAR);
        Variable v = myvariables.get(varName);
        if(v==null){
            v = new Variable(varName);
            myvariables.put(varName,v);
        }
        return v;
    }

    private ConstStringValue procString() throws IOException{
        String str = current.token;
        matchToken(TokenType.STRING);
        ConstStringValue csv = new ConstStringValue(str, la.getLine());
        return csv;
    }

    private Integer procNumber() throws IOException{
        String number = current.token;
        matchToken(TokenType.NUMBER);
        return Integer.parseInt(number);
    }


    //<if> ::= if <boolexpr> <statements> [ else <statements> ] end
    private IfCommand procIF() throws IOException{
        matchToken(TokenType.IF);
        BoolValue dbexpr = procBoolExpression();        
        CommandBlock cb_if = procStatements();
        IfCommand if_cmd;      
        if (current.type == TokenType.ELSE) {
            matchToken(TokenType.ELSE);
            CommandBlock cb_else = procStatements();
            if_cmd = new IfCommand(dbexpr, cb_if,cb_else, la.getLine());
            matchToken(TokenType.END);
            return if_cmd;
        } else {
            if_cmd = new IfCommand(dbexpr, cb_if, la.getLine());
            matchToken(TokenType.END);
            return if_cmd;
        }
    }

    //<boolexpr> ::= <expr> <boolop> <expr> { ('&' | '|') <boolexpr> } 
    private BoolValue procBoolExpression() throws IOException{//<<<<< REVER
        Value<?> expr1 = procExpr();        
        RelOp op = procBoolOp();
        Value<?> expr2 = procExpr();
        
        CompareBoolValue cbv = new CompareBoolValue(op,expr1,expr2,la.getLine());
        DualBoolExpr dbe = null;
        BoolValue tmp = null;
        BoolOp bo;
        if(current.type == TokenType.AND || current.type == TokenType.OR){
            while(current.type == TokenType.AND ||
                    current.type == TokenType.OR){
                if(current.type == TokenType.AND){
                    matchToken(TokenType.AND);
                    bo = BoolOp.And;
                    tmp = procBoolExpression();
                }
                else {
                    matchToken(TokenType.OR);
                    bo = BoolOp.And;
                    tmp = procBoolExpression();
                }
                dbe = new DualBoolExpr(bo, dbe, tmp, la.getLine());
            }    
            return dbe;
        }
        return cbv;
    }

    private RelOp procBoolOp() throws IOException {
        RelOp op = null;
        if(current.type == TokenType.EQUAL){
            matchToken(TokenType.EQUAL);
            op = RelOp.Equal;
        }else if(current.type == TokenType.DIFF){
            matchToken(TokenType.DIFF);
            op = RelOp.NotEqual;
        }else if(current.type == TokenType.LOWER){
            matchToken(TokenType.LOWER);
            op = RelOp.LowerThan;
        }else if(current.type == TokenType.GREATER){
            matchToken(TokenType.GREATER);
            op = RelOp.GreaterThan;
        }else if(current.type == TokenType.LOWER_EQUAL){
            matchToken(TokenType.LOWER_EQUAL);
            op = RelOp.LowerEqual;
        }else if(current.type == TokenType.GREATER_EQUAL){
            matchToken(TokenType.GREATER_EQUAL);
            op = RelOp.GreaterEqual;
        }else{
            abortUnexpectToken();
        }
        return op;
    }

    //<for> ::= for <var> '=' <value> <statements> end
    private ForCommand procFor() throws IOException {
        matchToken(TokenType.FOR);
        Variable var = procVar();
        matchToken(TokenType.ASSIGN);
        Value<?> val = procValue();
        CommandBlock cb = procStatements();
        matchToken(TokenType.END);
        ForCommand _for =  new ForCommand(var, val, cb, la.getLine());
        return _for;
    }

    // <assign:> ::= <var> '=' <expr> ';'
    private AssignCommand procAssign() throws IOException {
        Variable var = procVar();
        matchToken(TokenType.ASSIGN);
        Value<?> expr_valor = procExpr();
        matchToken(TokenType.DOT_COMMA);

        AssignCommand c = new AssignCommand(var, expr_valor, la.getLine());
        return c;
    }

    // <expr> ::= <term> [ ('+' | '-') <term> ]
    private Value<?> procExpr() throws IOException {
        Value<?> left = procTerm();

        System.out.println("Next: (" + current.token + ", " + current.type + ")");
        if (current.type == TokenType.PLUS || current.type == TokenType.MINS) {
            IntOp op = null;
            if (current.type == TokenType.PLUS) {
                matchToken(TokenType.PLUS);
                op = IntOp.Add;
            }  else {
                matchToken(TokenType.MINS);
                op = IntOp.Sub;
            }

            Value<?> right = procTerm();

            DualIntExpr expr = new DualIntExpr(op, left, right, la.getLine());
            return expr;
        } else {
            return left;
        }
    }

    // <term> ::= <factor> [ ('*' | '/' | '%') <factor> ]
    private Value<?> procTerm() throws IOException {
        Value<?> fact1 = procFactor();
        if (current.type == TokenType.TIMES || current.type == TokenType.DIV || current.type == TokenType.MOD) {
            IntOp op = null;
            if (current.type == TokenType.TIMES) {
                matchToken(TokenType.TIMES);
                op = IntOp.Mul;
            }else if(current.type == TokenType.DIV){
                matchToken(TokenType.DIV);
                op = IntOp.Div;
            }else{
                matchToken(TokenType.MOD);
                op = IntOp.Mod;
            }

            Value<?> fact2 = procFactor();

            DualIntExpr expr = new DualIntExpr(op, fact1, fact2, la.getLine());
            return expr;
        } else {
            return fact1;
        }
        
    }

    // <factor> ::= <number> | <input> | <value> | '(' <expr> ')'
    private Value<?> procFactor() throws IOException {
        System.out.println("Next: (" + current.token + ", " + current.type + ")");
        if (current.type == TokenType.NUMBER) {
            Integer n = procNumber();
            ConstIntValue cv = new ConstIntValue(n, la.getLine());
            return cv;
        } else if (current.type == TokenType.INPUT) {
            return procInput();
        }else if(current.type == TokenType.VALUE ||
                 current.type == TokenType.VAR)
        {
            return procValue();
        }else if(current.type == TokenType.OPEN_PAR){
            matchToken(TokenType.OPEN_PAR);
            Value<?> expr = procExpr();
            matchToken(TokenType.CLOSE_PAR);
            return expr;
        }else{
            abortUnexpectToken();
        }
        return null;
    }


    private ShowCommand procShow() throws IOException {
        matchToken(TokenType.SHOW);
        matchToken(TokenType.OPEN_PAR);
        Value<?> v = procText();
        matchToken(TokenType.CLOSE_PAR);
        matchToken(TokenType.DOT_COMMA);
        ShowCommand c = new ShowCommand(v,la.getLine());
        return c;
    }

    // <value> ::= (<var> | <gen>)
    //                  { '.' (<opposed> | <transposed> | <sum> | <mul>) }
    //                  [ '.' (<size> | <rows> | <cols> | <val>) ]
    public Value<?> procValue() throws IOException {//revisar
        Value<?> value = null;
        if(current.type == TokenType.VAR){
            value = procVar();
        }else if(current.type == TokenType.OPEN_BRA){
            value = procGen();
        }else {
            abortUnexpectToken();
        }
        
        while(current.type == TokenType.DOT){
            matchToken(TokenType.DOT);
            if(current.type == TokenType.OPPOSED){
                value = procOpposed(value);
            }else if(current.type == TokenType.MUL){
                value = procMul(value);
            }else if(current.type == TokenType.SIZE){
                value = procSize(value);
                break;
            }else if(current.type == TokenType.COLS){
                value = procCols(value);
                break;
            }else if(current.type == TokenType.ROWS){
                value = procRows(value);
                break;
            }else if(current.type == TokenType.SUM){
                value = procSum(value);
                break;
            }else if(current.type == TokenType.TRANSPOSED){
                value = procTransposed(value);
            }else{
                abortUnexpectToken();
            }
        }

        return value;
    }

    //<opposed> ::= opposed '(' ')'
    private OpposedMatrixValue procOpposed(Value<?> matrix) throws IOException {
        matchToken(TokenType.OPPOSED);
        matchToken(TokenType.OPEN_PAR);
        matchToken(TokenType.CLOSE_PAR);
        OpposedMatrixValue omv = new OpposedMatrixValue(matrix,la.getLine());
        return omv;
    }
    
    
    //<transposed> ::= transposed '(' ')'
    private TransposedMatrixValue procTransposed(Value<?> matrix) throws IOException {
        matchToken(TokenType.TRANSPOSED);
        matchToken(TokenType.OPEN_PAR);
        matchToken(TokenType.CLOSE_PAR);
        TransposedMatrixValue tmv = new TransposedMatrixValue(matrix,la.getLine());
        return tmv;
    }

    //<sum> ::= sum '(' <expr> ')'
    private SumMatrixValue procSum(Value<?> matriz_1) throws IOException {
        matchToken(TokenType.SUM);
        matchToken(TokenType.OPEN_PAR);
        Value<?> matriz_2 = procExpr();
        matchToken(TokenType.CLOSE_PAR);
        SumMatrixValue smv = new SumMatrixValue(matriz_1, matriz_2,la.getLine());
        return smv;
    }
    
    //<mul> ::= mul '(' <value> |  <expr>  ')' 
    private MulMatrixValue procMul(Value<?> m) throws IOException {
        matchToken(TokenType.MUL);
        matchToken(TokenType.OPEN_PAR);
        Value<?> m2 = procExpr();//Posso do expr cair em value
        matchToken(TokenType.CLOSE_PAR);
        MulMatrixValue mmv = new MulMatrixValue(m, m2, la.getLine());
        return mmv;
    }

    //<size> ::= size '(' ')'
    private SizeIntMatrixValue procSize(Value<?> m) throws IOException {
        matchToken(TokenType.SIZE);
        matchToken(TokenType.OPEN_PAR);
        matchToken(TokenType.CLOSE_PAR);
        SizeIntMatrixValue smv = new SizeIntMatrixValue(m, la.getLine());
        return smv;
    }

    //<val> ::= value '(' <expr> ',' <expr> ')'
    private Value<?> procVal(Value<?> matrix) throws IOException {
        matchToken(TokenType.VALUE);
        matchToken(TokenType.OPEN_PAR);
        Value<?> expr1 = procExpr();
        matchToken(TokenType.COMMA);
        Value<?> expr2 = procExpr();
        matchToken(TokenType.CLOSE_PAR);
        ValueIntMatrixValue vimv = new ValueIntMatrixValue(matrix, expr1, expr2, la.getLine());
        return vimv;
    }

    //<while> ::= while <boolexpr> <statements> end
    private WhileCommand procWhile() throws IOException {
        matchToken(TokenType.WHILE);
        BoolValue dbexpr = procBoolExpression();
        CommandBlock cb = procStatements();
        matchToken(TokenType.END);
        WhileCommand wc = new WhileCommand(dbexpr, cb, la.getLine());
        return wc;
    }

    //<gen> ::= '[' ']' '.' (<null> | <fill> | <rand> | <id> | <seq> | <iseq>)
    private MatrixValue procGen() throws IOException {
        matchToken(TokenType.OPEN_BRA);
        matchToken(TokenType.CLOSE_BRA);
        matchToken(TokenType.DOT);
        
        if(current.type == TokenType.NULL){
            NullMatrixValue nmv  =  procNull();
            return nmv;
        }else if(current.type == TokenType.FILL){
            FillMatrixValue fmv  =  procFill();
            return fmv;
        }else if(current.type == TokenType.RAND){
            RandMatrixValue fmv  =  procRand();
            return fmv;
        }else if(current.type == TokenType.ID){
            IdMatrixValue idmv  =  procId();
            return idmv;
        }else if(current.type == TokenType.SEQ){
            SeqMatrixValue seqmv  =  procSeq();
            return seqmv;
        }else if(current.type == TokenType.ISEQ){
            SeqMatrixValue iseqmv  =  procISeq();
            return iseqmv;
        }else{
            abortUnexpectToken();
        }
        return null;
    }
    
    /* <text> ::= { <string> | <expr> }
    private Value<?> procText() throws IOException {
        // FIXME: Fazer o while
        if (current.type == TokenType.STRING) {
            String s = procString();

            StringValue sv = new ConstStringValue(s, la.getLine());
            return sv;
        } else {
            Value<?> v = procExpr();
            return v;
        }
    }*/
// <text> ::= { <string> | <expr> }
    private Value<?> procText() throws IOException {
        // FIXME: Fazer o while
        Value<?> value = null;
        while(current.type==TokenType.STRING || current.type==TokenType.NUMBER ||
              current.type==TokenType.INPUT || current.type==TokenType.VAR ||
              current.type==TokenType.OPEN_BRA || current.type==TokenType.OPEN_PAR){
            Value<?> add = null;
            if (current.type == TokenType.STRING) {
                add = procString();
            } else {
                add = procExpr();
            }
            if(add != null){
                value = new StringConcat(value, add, la.getLine());
            }else{
                value = add;
            }
        }
        return value;
    }
    
    private void abortEOF() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void abortUnexpectToken() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //<cols> ::= cols '(' ')'
    private ColsIntMatrixValue procCols(Value<?> matrix) throws IOException {
        matchToken(TokenType.COLS);
        matchToken(TokenType.OPEN_PAR);
        matchToken(TokenType.CLOSE_PAR);
        ColsIntMatrixValue cmv = new ColsIntMatrixValue(matrix,la.getLine());
        return cmv;
    }
    
    //<rows> ::= rows '(' ')'
    private RowsIntMatrixValue procRows(Value<?> matrix) throws IOException {
        matchToken(TokenType.ROWS);
        matchToken(TokenType.OPEN_PAR);
        matchToken(TokenType.CLOSE_PAR);
        RowsIntMatrixValue rmv = new RowsIntMatrixValue(matrix,la.getLine());
        return rmv;
    }
    
    //<null> ::= null '(' <expr> ',' <expr> ')'
    private NullMatrixValue procNull() throws IOException {
        matchToken(TokenType.NULL);
        matchToken(TokenType.OPEN_PAR);
        Value<?> expr1 = procExpr();
        matchToken(TokenType.COMMA);
        Value<?> expr2 = procExpr();
        matchToken(TokenType.CLOSE_PAR);
        NullMatrixValue nmv = new NullMatrixValue(expr1, expr2, la.getLine());
        return nmv;
    }
    
    //<fill> ::= fill '(' <expr> ',' <expr> ',' <expr> ')'
    private FillMatrixValue procFill() throws IOException {
        matchToken(TokenType.FILL);
        matchToken(TokenType.OPEN_PAR);
        Value<?> expr1 = procExpr();
        matchToken(TokenType.COMMA);
        Value<?> expr2 = procExpr();
        matchToken(TokenType.COMMA);
        Value<?> expr3 = procExpr();
        matchToken(TokenType.CLOSE_PAR);
        FillMatrixValue fmv = new FillMatrixValue(expr1, expr2, expr3, la.getLine());
        return fmv;
    }
    
    //<rand> ::= rand '(' <expr> ',' <expr> ')'
    private RandMatrixValue procRand() throws IOException {
        matchToken(TokenType.RAND);
        matchToken(TokenType.OPEN_PAR);
        Value<?> expr1 = procExpr();
        matchToken(TokenType.COMMA);
        Value<?> expr2 = procExpr();
        matchToken(TokenType.CLOSE_PAR);
        RandMatrixValue rmv = new RandMatrixValue(expr1, expr2, la.getLine());
        return rmv;
    }
    
    //<id> ::= id '(' <expr> ',' <expr> ')'
    private IdMatrixValue procId() throws IOException {
        matchToken(TokenType.ID);
        matchToken(TokenType.OPEN_PAR);
        Value<?> expr1 = procExpr();
        matchToken(TokenType.COMMA);
        Value<?> expr2 = procExpr();
        matchToken(TokenType.CLOSE_PAR);
        IdMatrixValue idmv = new IdMatrixValue(expr1, expr2, la.getLine());
        return idmv;
    }
    
    //<seq> ::= seq '(' <expr> ',' <expr> ')'
    private SeqMatrixValue procSeq() throws IOException {
        matchToken(TokenType.SEQ);
        matchToken(TokenType.OPEN_PAR);
        Value<?> expr1 = procExpr();
        matchToken(TokenType.COMMA);
        Value<?> expr2 = procExpr();
        matchToken(TokenType.CLOSE_PAR);
        SeqMatrixValue seqmv = new SeqMatrixValue(expr1, expr2, false, la.getLine());
        return seqmv;
    }
    
    //<iseq> ::= iseq '(' <expr> ',' <expr> ')'
    private SeqMatrixValue procISeq() throws IOException {
        matchToken(TokenType.ISEQ);
        matchToken(TokenType.OPEN_PAR);
        Value<?> expr1 = procExpr();
        matchToken(TokenType.COMMA);
        Value<?> expr2 = procExpr();
        matchToken(TokenType.CLOSE_PAR);
        SeqMatrixValue seqmv = new SeqMatrixValue(expr1, expr2, true, la.getLine());
        return seqmv;
    }
}
