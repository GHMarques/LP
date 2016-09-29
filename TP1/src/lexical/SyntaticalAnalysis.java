/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private CommandBlock procStatements() throws IOException{//revisar
        CommandBlock cb = new CommandBlock();

        Command c = procStatement();
        cb.addCommand(c);

        while(current.type == TokenType.SHOW ||
              current.type == TokenType.VAR ||
                current.type == TokenType.IF ||//<--
                current.type == TokenType.WHILE ||//<--
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
    void procIF() throws IOException{
//    private IfCommand procIF() throws IOException{
        matchToken(TokenType.IF);
        procBoolExpression();
        //DualBoolExpr expr = procBoolExpression();
        procStatements();
        //CommandBlock cb = procStatements();
        //CommandBlock cb_else;
        if(current.type == TokenType.ELSE){
            matchToken(TokenType.ELSE);
            procStatements();
            //cb_else  = procStatements();
        }
        matchToken(TokenType.END);

        //IfCommand if_else = new IfCommand();
        //return if_else;
    }

    void procBoolExpression() throws IOException{
        procIntExpr();
        procBoolOp();
        procIntExpr();
        while(current.type == TokenType.AND ||
                current.type == TokenType.OR){
            if(current.type == TokenType.AND)
                matchToken(TokenType.AND);
            else
                matchToken(TokenType.OR);
            procBoolExpression();
        }
    }

    private void procBoolOp() throws IOException {//revisar
        if(current.type == TokenType.EQUAL){
            matchToken(TokenType.EQUAL);
        }else if(current.type == TokenType.DIFF){
            matchToken(TokenType.DIFF);
        }else if(current.type == TokenType.LOWER){
            matchToken(TokenType.LOWER);
        }else if(current.type == TokenType.GREATER){
            matchToken(TokenType.GREATER);
        }else if(current.type == TokenType.LOWER_EQUAL){
            matchToken(TokenType.LOWER_EQUAL);
        }else if(current.type == TokenType.GREATER_EQUAL){
            matchToken(TokenType.GREATER_EQUAL);
        }else{
            abortUnexpectToken();
        }
    }

    private void procFor() throws IOException {
        matchToken(TokenType.FOR);
        Variable var = procVar();
        matchToken(TokenType.ASSIGN);
        Value<?> val = procValue();
        procStatements();
        matchToken(TokenType.END);
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
        if(current.type == TokenType.VAR){
            Variable var = procVar();
            return var;
        }else if(current.type == TokenType.OPEN_BRA){
            procGen();
        }else {
            abortUnexpectToken();
        }

        while(current.type == TokenType.DOT){
            matchToken(TokenType.DOT);
            if(current.type == TokenType.OPPOSED){
                procOpposed();
            }else if(current.type == TokenType.MUL){
                procMUL();
            }else if(current.type == TokenType.SIZE){
                procSize();
                break;
            }else if(current.type == TokenType.VALUE){
                procVal();
            }else abortUnexpectToken();
        }

        return null;//value<?> <--
    }

    private void procOpposed() throws IOException {
        Value<?> v = procText();
        matchToken(TokenType.OPPOSED);
        matchToken(TokenType.OPEN_PAR);
        matchToken(TokenType.CLOSE_PAR);
        OpposedMatrixValue m = new OpposedMatrixValue((MatrixValue) v,la.getLine());
    }

    //<mul> ::= mul '(' <value> |  <expr  ')'
    private MulMatrixValue procMUL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void procSize() {
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

    private void procWhile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void procGen() throws IOException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        matchToken(TokenType.OPEN_BRA);
        matchToken(TokenType.CLOSE_BRA);
        matchToken(TokenType.DOT);
//        if(){
//            
//        }
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

    private void procIntExpr() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
