package lexical;

import java.util.Map;
import java.util.HashMap;
//import lexical.TokenType;

class SymbolTable {

    private Map<String, TokenType> st;

    public SymbolTable() {
        
        st = new HashMap<String, TokenType>();
        //SPECIALS
        /*st.put("-2", TokenType.UNEXPECTED_EOF);
        st.put("-1", TokenType.INVALID_LEX);
        st.put("EOF", TokenType.END_OF_FILE);*/
        
        //OTHERS
        st.put("", TokenType.VAR);
        st.put("", TokenType.STRING);
        st.put("", TokenType.NUMBER);
        
        //Functions
        st.put("=", TokenType.ASSIGN);
        st.put("input", TokenType.INPUT);
        st.put("show", TokenType.SHOW);
        st.put("if", TokenType.IF);
        st.put("else", TokenType.ELSE);
        st.put("for", TokenType.FOR);
        st.put("while", TokenType.WHILE);
        st.put("end", TokenType.END);
        
        //MATRIX FUNCTIONS
        st.put("rand", TokenType.RAND);
        st.put("sec", TokenType.SEC);
        st.put("isec", TokenType.ISEC);
        st.put("null", TokenType.NULL);
        st.put("fill", TokenType.FILL);
        st.put("id", TokenType.ID);
        st.put("transpposed", TokenType.TRANSPPOSED);
        st.put("sum", TokenType.SUM);
        st.put("mul", TokenType.MUL);
        st.put("size", TokenType.SIZE);
        st.put("rows", TokenType.ROWS);
        st.put("cols", TokenType.COLS);
        st.put("value", TokenType.VALUE);
        st.put("\"", TokenType.DOUBLE_QUOTES);
        st.put("(", TokenType.OPEN_PAR);
        st.put(")", TokenType.CLOSE_PAR);
        st.put("[", TokenType.OPEN_BRA);
        st.put("]", TokenType.CLOSE_BRA);
        st.put(".", TokenType.DOT);
        st.put(",", TokenType.COMMA);
        st.put(";", TokenType.DOT_COMMA);
        st.put("#", TokenType.HASHTAG);
        st.put("%", TokenType.MOD);
        st.put("==", TokenType.EQUAL);
        st.put("!=", TokenType.DIFF);
        st.put("<", TokenType.LOWER);
        st.put(">", TokenType.GREATER);
        st.put("<=", TokenType.LOWER_EQUAL);
        st.put(">=", TokenType.GREATR_EQUAL);
        st.put("&", TokenType.AND);
        st.put("|", TokenType.OR);
        st.put("+", TokenType.PLUS);
        st.put("-", TokenType.MINS);
        st.put("*", TokenType.TIMES);
        st.put("/", TokenType.DIV);
        
        
        // FIXME: Add the tokens here.
        // st.put("???", TokenType.???);
    }

    public boolean contains(String token) {
        return st.containsKey(token);
    }

    public TokenType find(String token) {
        return this.contains(token) ?
            st.get(token) : TokenType.INVALID_TOKEN;
    }
    
}
