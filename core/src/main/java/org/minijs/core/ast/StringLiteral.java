package org.minijs.core.ast;

public class StringLiteral implements Literal {

    private final String mValue;

    private String mToStringCache;

    public StringLiteral() {
        this(null);
    }

    public StringLiteral(String v) {
        mValue = v;
    }

    public String getValue() {
        return mValue;
    }

    @Override
    public String toString() {
        if (mValue == null) {
            return "null";
        }

        if (mToStringCache == null) {
            mToStringCache = String.format("\"%s\"", mValue);
        }
        return mToStringCache;
    }
}
