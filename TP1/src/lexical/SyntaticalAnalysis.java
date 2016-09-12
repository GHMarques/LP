/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical;

import java.io.IOException;

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
    
    void procStatements() throws IOException{
        procStatement();
        while(current.type == TokenType.INPUT ||
              current.type == TokenType.SHOW ||
              current.type == TokenType.VAR ||
               //mais
               current.type == TokenType.FOR){
               procStatement();
        }
    }
    
    void procStatement() throws IOException{
        if(current.type == TokenType.INPUT){
            procInput();
        }else if(current.type == TokenType.SHOW){
            procShow();
        }else if(current.type == TokenType.VAR){
            procAssign();
        }else if(current.type == TokenType.FOR){
            procFor();
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
    
    void procVar() throws IOException{
        matchToken(TokenType.VAR);
    }
    
    void procString() throws IOException{
        matchToken(TokenType.STRING);
    }
    
    void procNumber() throws IOException{
        matchToken(TokenType.NUMBER);
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

    private void procIntExpr() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void procBoolOp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void procText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void abortEOF() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void abortUnexpectToken() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void procFor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void procAssign() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void procShow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
