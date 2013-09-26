package org.minijs.core.ast;

public class UndefinedLiteral implements Literal {

    public UndefinedLiteral() {
    }

    @Override
    public String toString() {
        return "undefined";
    }
}
