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
    INSTANCEOF("instanceof"),
    TYPE("typeof"),
    VOID("void"),
    DELETE("delete"),
    BIT_LEFT_SHIFT("<<"),
    BIT_RIGHT_SHIFT(">>"),
    BIT_UNSIGNED_RIGHT_SHITF(">>>"),
    ASSIGN("="),
    PLUS_ASSIGN("+="),
    MINUS_ASSIGN("-="),
    MUL_ASSIGN("*="),
    DIV_ASSIGN("/="),
    MOD_ASSIGN("%="),
    BIT_NOT("~"),
    BIT_LEFT_SHIFT_ASSIGN("<<="),
    BIT_RIGHT_SHIFT_ASSIGN(">>="),
    BIT_UNSIGNED_RIGHT_SHIFT_ASSIGN(">>>="),
    BIT_AND_ASSIGN("&="),
    BIT_XOR_ASSIGN("^="),
    BIT_OR_ASSIGN("|="),
    ;

    private final String mOpString;

    Operator(String s) {
        mOpString = s;
    }

    @Override
    public String toString() {
        return mOpString;
    }
}
