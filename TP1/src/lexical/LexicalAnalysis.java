package lexical;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

public class LexicalAnalysis implements AutoCloseable {

    private int line;
    private PushbackInputStream input;
    SymbolTable st = new SymbolTable();
    

    public LexicalAnalysis(String filename) throws LexicalException {
        try {
            input = new PushbackInputStream(new FileInputStream(filename));
        } catch (Exception e) {
            throw new LexicalException("Unable to open file");
        }

        line = 1;
    }

    public void close() {
        try {
            input.close();
        } catch (Exception e) {
            // ignore
        }
    }

    public Lexeme nextToken() throws IOException {
        Lexeme lex = new Lexeme("", TokenType.END_OF_FILE);
        int estado = 1;
        int c;
        while(estado != 8){
            c = input.read();
            switch(estado){
                case 1:
                    if(c == -1)
                        return lex;
                    else if(c == '\n')
                        this.line++;
                    else if(c == ' ' || c == '\t' || c == '\n' || c == '\r')
                        estado = 1;
                    else if(c == '#')
                        estado = 2;
                    else if(Character.isDigit(c)){
                        lex.token += (char)c;
                        estado = 3;
                    }
                    else if (c == '!'){
                        lex.token += (char)c;
                        estado = 4;
                    }
                    else if(c == '=' || c == '<' || c == '>'){
                        lex.token += (char)c;
                        estado = 5;
                    }
                    else if(Character.isLetter(c)){
                        lex.token += (char)c;
                        estado = 6;
                    }
                    else if(c == '"')
                        estado = 7;
                    else if(c == '.' || c == ';' || c == ',' || c == '(' || c == ')' || c == '[' ||
                            c == ']' || c == '&' || c == '|' || c == '+' || c == '-' || c == '*' ||
                            c == '%' || c == '/'){
                        lex.token += (char)c;
                        estado = 8;
                    }
                    else{
                        lex.token += (char)c;
                        lex.type = TokenType.INVALID_TOKEN;
                        return lex;
                    }  
                    break;
                case 2:
                    if(c == '\n'){
                        line++;
                        estado = 1;
                    }   
                    else if(c == -1)
                        return lex;
                    else
                        estado = 2;
                    break;
                case 3:
                    if(Character.isDigit(c)){
                        lex.token += (char)c;
                        estado = 3;
                    }
                    else {
                        if(c != -1)
                            input.unread(c);
                        lex.type = TokenType.NUMBER;
                        estado = 8;
                        return lex;
                    }   
                    break;
                case 4:
                    if(c == '='){
                        lex.token += (char)c;
                        estado = 8;
                    }
                    else if(c == -1){
                        lex.type = TokenType.UNEXPECTED_EOF;
                        return lex;
                    }
                    else{
                        lex.type = TokenType.INVALID_TOKEN;
                        return lex;
                    }
                    break;
                case 5:
                    if(c == '='){
                        lex.token += (char)c;
                        estado = 8;
                    }
                    else{
                        if(c == -1)
                            input.unread(c);
                        estado = 8;
                    }
                    break;
                    
                case 6:
                    if(Character.isLetter(c) || Character.isDigit(c)){
                        lex.token += (char)c;
                        estado = 6;
                    }
                    else {
                        if(c != -1)
                            input.unread(c);
                        lex.type = TokenType.NUMBER;
                        estado = 8;
                        //return lex;
                    } 
                    break;
                case 7:
                    if(c != '"')
                        lex.token += (char)c;
                    else if(c == -1){
                        lex.type = TokenType.INVALID_TOKEN;
                        return lex;
                    }
                    else{
                        estado = 8;
                        lex.type = TokenType.STRING;
                        return lex;
                    }
                    break;
                case 8:
                    break;
                default:
                    break;
                
            }           
        }
        if(st.contains(lex.token)){
            lex.type = st.find(lex.token);
        }
        else
            lex.type = TokenType.VAR;
        return lex;
    }
    
    public int getLine(){
        return line;
    }
}
