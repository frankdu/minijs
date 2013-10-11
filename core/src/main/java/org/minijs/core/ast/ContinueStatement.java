package org.minijs.core.ast;

public class ContinueStatement implements Statement {

    private final String mLabel;

    public ContinueStatement() {
        this(null);
    }

    public ContinueStatement(String label) {
        mLabel = label;
    }

    public String getLabel() {
        return mLabel;
    }
}
