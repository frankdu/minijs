package org.minijs.core.ast;

public class NumberLiteral implements Literal {

    private final double mValue;

    public NumberLiteral() {
        this(0);
    }

    public NumberLiteral(double v) {
        mValue = v;
    }

    public double getValue() {
        return mValue;
    }

    @Override
    public String toString() {
        return String.valueOf(mValue);
    }
}
