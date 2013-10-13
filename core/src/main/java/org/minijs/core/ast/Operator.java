package org.minijs.core.ast;

public enum Operator {
    MUL("*"),
    DIV("/"),
    MOD("%"),
    PLUS("+"),
    MINUS("-"),
    NOT("!"),
    INC("++"),
    DEC("--"),
    LT("<"),
    LE("<="),
    GT(">"),
    GE(">="),
    EQ("=="),
    NEQ("!="),
    EXACT_EQ("==="),
    EXACT_NEQ("!=="),
    AND("&&"),
    OR("||"),
    IN("in"),
    INSTANCEOF("instanceof");

    private final String mOpString;

    Operator(String s) {
        mOpString = s;
    }

    @Override
    public String toString() {
        return mOpString;
    }
}
