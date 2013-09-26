package org.minijs.core.ast;

public class BooleanLiteral implements Literal {

    private final boolean mValue;

    public BooleanLiteral() {
        this(false);
    }

    public BooleanLiteral(boolean v) {
        mValue = v;
    }

    public boolean getValue() {
        return mValue;
    }

    @Override
    public String toString() {
        return mValue ? "true" : "false";
    }
}
