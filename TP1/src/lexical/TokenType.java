package lexical;

public enum TokenType {
    // special tokens
    INVALID_TOKEN,
    UNEXPECTED_EOF,
    END_OF_FILE,

    // keywords
    // FIXME: Add the keywords here.

    // operators
    // FIXME: Add the operators here.

    // symbols
    // FIXME: Add the symbols here.

    // others
    VAR,          // variable
    STRING,       // string
    NUMBER,
    ASSIGN,
    INPUT,
    SHOW,
    IF,
    ELSE,
    FOR,
    WHILE,
    END,
    RAND,
    SEC,
    ISEC,
    NULL,
    FILL,
    ID,
    TRANSPPOSED,
    SUM,
    MUL,
    SIZE,
    ROWS,
    COLS,
    VALUE,
    DOUBLE_QUOTES,
    OPEN_PAR,
    CLOSE_PAR,
    OPEN_BRA,
    CLOSE_BRA,
    DOT, COMMA,
    DOT_COMMA,
    HASHTAG,
    MOD,
    EQUAL,
    DIFF,
    LOWER,
    GREATER,
    LOWER_EQUAL,
    GREATR_EQUAL,
    AND,
    OR,
    PLUS,
    MINS,
    TIMES,
    DIV;
}
