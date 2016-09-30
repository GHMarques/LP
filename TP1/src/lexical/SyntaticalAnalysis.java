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
            procFor();
            return null;
        }else if(current.type == TokenType.WHILE){//<--
            procWhile();
            return null;
        }else if(current.type == TokenType.IF){//<--
            procIF();
            return null;
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

    private String procString() throws IOException{
        String str = current.token;
        matchToken(TokenType.STRING);
        return str;
    }

    private Integer procNumber() throws IOException{
        String number = current.token;
        matchToken(TokenType.NUMBER);
        return Integer.parseInt(number);
    }


    //<if> ::= if <boolexpr> <statements> [ else <statements> ] end
    private IfCommand procIF() throws IOException{
        matchToken(TokenType.IF);
        DualBoolExpr dbexpr = procBoolExpression();
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
    private DualBoolExpr procBoolExpression() throws IOException{//<<<<< REVER
        Value<?> expr1 = procExpr();
        RelOp op = procBoolOp();
        Value<?> expr2 = procExpr();
        
        CompareBoolValue cbv_1 = new CompareBoolValue(op,expr1,expr2,la.getLine());
        DualBoolExpr dbexpr = null;
        
        if(current.type == TokenType.AND || current.type == TokenType.OR){
            while(current.type == TokenType.AND ||
                    current.type == TokenType.OR){
                if(current.type == TokenType.AND){
                    matchToken(TokenType.AND);
                    DualBoolExpr dbexpr_2 = procBoolExpression();
                }
                else 
                    matchToken(TokenType.OR);
                procBoolExpression();
            }
        }else{
            dbexpr = new DualBoolExpr(null, cbv_1, null, la.getLine());
        }
        return dbexpr;
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

        // FIXME: [ ('*' | '/' | '%') <factor> ]_OK
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
        // FIXME: <number> | <value> | '(' <expr> ')'
        System.out.println("Next: (" + current.token + ", " + current.type + ")");
        if (current.type == TokenType.NUMBER) {
            Integer n = procNumber();
            ConstIntValue cv = new ConstIntValue(n, la.getLine());
            return cv;
        } else if (current.type == TokenType.INPUT) {
            return procInput();
        }else
        {
            return procValue();
        }
        /*else if(current.type == TokenType.VALUE){
            return procValue();
        }else {
            matchToken(TokenType.OPEN_PAR);
            procExpr();
            matchToken(TokenType.CLOSE_PAR);
        }
        return null;
            */
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
            Variable var = procVar();
            return var;
        }else if(current.type == TokenType.OPEN_BRA){
//            MatrixValue m = procGen();
//            return m;
        }else {
            abortUnexpectToken();
        }
        
        while(current.type == TokenType.DOT){
            matchToken(TokenType.DOT);
            if(current.type == TokenType.OPPOSED){
//                matrix = procOpposed(matrix);
            }else if(current.type == TokenType.MUL){
                value = procMUL(value);
            }else if(current.type == TokenType.SIZE){
//                value = procSize(value);
                break;
            }else if(current.type == TokenType.VALUE){
//                value = procVal(value);
                break;
            }else if(current.type == TokenType.COLS){
//                value = procCols(value);
                break;
            }else if(current.type == TokenType.ROWS){
//                value = procRows(value);
                break;
            }else if(current.type == TokenType.SIZE){
//                value = procSize(value);
                break;
            }else if(current.type == TokenType.SUM){
//                value = procSum(value);
            }else if(current.type == TokenType.TRANSPPOSED){
//                value = procTranspossed(value);
            }else{
                abortUnexpectToken();
            }
        }

        return null;//value<?> <--
    }

    private void procOpposed(Value<?> a) throws IOException {
        Value<?> v = procText();
        matchToken(TokenType.OPPOSED);
        matchToken(TokenType.OPEN_PAR);
        matchToken(TokenType.CLOSE_PAR);
        OpposedMatrixValue m = new OpposedMatrixValue((MatrixValue) v,la.getLine());
    }

    //<mul> ::= mul '(' <value> |  <expr  ')'
    private MulMatrixValue procMUL(Value<?> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void procSize(Value<?> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    //<val> ::= value '(' <expr> ',' <expr> ')'
    private void procVal() throws IOException {
        matchToken(TokenType.VALUE);
        matchToken(TokenType.OPEN_PAR);
        procExpr();
        matchToken(TokenType.COMMA);
        procExpr();
        matchToken(TokenType.CLOSE_PAR);
    }

    //<while> ::= while <boolexpr> <statements> end
    private WhileCommand procWhile() throws IOException {
        matchToken(TokenType.WHILE);
        DualBoolExpr dbexpr = procBoolExpression();
        CommandBlock cb = procStatements();
        matchToken(TokenType.END);
        WhileCommand wc = new WhileCommand(dbexpr, cb, la.getLine());
        return wc;
    }

    //<gen> ::= '[' ']' '.' (<null> | <fill> | <rand> | <id> | <seq> | <iseq>)
    private Matrix procGen(Value<?> r,Value<?> c, Value<?> v) throws IOException {
        matchToken(TokenType.OPEN_BRA);
        matchToken(TokenType.CLOSE_BRA);
        matchToken(TokenType.DOT);
        
        if(current.type == TokenType.NULL){
            NullMatrixValue nmv  =  new NullMatrixValue(r,c,la.getLine());
            return nmv.value();
        }else if(current.type == TokenType.FILL){
            FillMatrixValue fmv  =  new FillMatrixValue(r,c,v,la.getLine());
            return fmv.value();
        }else if(current.type == TokenType.RAND){
            RandMatrixValue fmv  =  new RandMatrixValue(r,c,la.getLine());
            return fmv.value();
        }else if(current.type == TokenType.ID){
            IdMatrixValue idmv  =  new IdMatrixValue(r,c,la.getLine());
            return idmv.value();
        }else if(current.type == TokenType.SEQ){
//            SeqMatrixValue seqmv  =  new SeqMatrixValue(r,c,la.getLine());
//            return seqmv.value();
        }else if(current.type == TokenType.ISEQ){
//            IeqMatrixValue iseqmv  =  new IeqMatrixValue(r,c,la.getLine());
//            return iseqmv.value();
        }
        return null;
    }
    
    // <text> ::= { <string> | <expr> }
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
    }
/*
    // <text> ::= { <string> | <expr> }
    private Value<?> procText() throws IOException {
        // FIXME: Fazer o while

        while(current.type==TokenType.STRING || current.type==TokenType.NUMBER ||
              current.type==TokenType.INPUT || current.type==TokenType.VAR ||
              current.type==TokenType.OPEN_BRA || current.type==TokenType.OPEN_PAR){

            if (current.type == TokenType.STRING) {
                String s = procString();

                StringValue sv = new ConstStringValue(s, la.getLine());
                return sv;
            } else {
                Value<?> v = procExpr();
                return v;
            }
        }
        return null;
    }
*/
    private void abortEOF() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void abortUnexpectToken() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void procCols(Value<?> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
