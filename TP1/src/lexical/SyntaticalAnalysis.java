/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical;

import Command.AssignCommand;
import Command.ShowCommand;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import model.*;
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
    
    void procStatements() throws IOException{//revisar
        procStatement();
        while(current.type == TokenType.INPUT ||
              current.type == TokenType.SHOW ||
              current.type == TokenType.VAR ||
               //mais
                current.type == TokenType.IF ||//<--
                current.type == TokenType.WHILE ||//<--
                current.type == TokenType.ASSIGN ||//<--
               //mais
               current.type == TokenType.FOR){
               procStatement();
        }
    }
    
    void procStatement() throws IOException{//revisar
        if(current.type == TokenType.INPUT){
            procInput();
        }else if(current.type == TokenType.SHOW){
            procShow();
        }else if(current.type == TokenType.VAR){
            procAssign();
        }else if(current.type == TokenType.FOR){
            procFor();
        }else if(current.type == TokenType.ASSIGN){//<--
            procAssign();
        }else if(current.type == TokenType.WHILE){//<--
            procWhile();
        }else if(current.type == TokenType.IF){//<--
            procIF();
        }else{
            abortUnexpectToken();
        }
    }
    
    void procInput() throws IOException{
        matchToken(TokenType.INPUT);
        matchToken(TokenType.OPEN_PAR);
        procText();
        matchToken(TokenType.CLOSE_PAR);
        matchToken(TokenType.DOT_COMMA);
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
    
    String procString() throws IOException{
        String str = current.token;
        matchToken(TokenType.STRING);
        return str;
    }
    
    Integer procNumber() throws IOException{
        String number = current.token;
        matchToken(TokenType.NUMBER);
        return Integer.parseInt(number);
    }
 
    void procIF() throws IOException{
        matchToken(TokenType.IF);
        procBoolExpression();
        procStatements();
        
        if(current.type == TokenType.ELSE){
            matchToken(TokenType.ELSE);
            procStatements();
        }
        matchToken(TokenType.END);
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

    private AssignCommand procAssign() throws IOException {
        Variable var = procVar();
        matchToken(TokenType.ASSIGN);
        Value<?> val = procValue();
        
        matchToken(TokenType.DOT_COMMA);
        AssignCommand c = new AssignCommand(var, val, la.getLine()/*lex.getLine()*/);
        return c;
    }

    public ShowCommand procShow() throws IOException {
        
        matchToken(TokenType.SHOW);
        matchToken(TokenType.OPEN_PAR);
        Value<?> v = procText();
        matchToken(TokenType.CLOSE_PAR);
        matchToken(TokenType.DOT_COMMA);
        ShowCommand c = new ShowCommand(v,la.getLine()/*lex.getLine()*/ );
        return c;
    }
    
    public Value<?> procValue() throws IOException {//revisar
        if(current.type == TokenType.VAR){
            procVar();
        }else if(current.type == TokenType.OPEN_BRA){
            procGen();
        }else abortUnexpectToken();
        
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

    private void procMUL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void procSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void procVal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void procWhile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void procGen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Value<?> procText() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
