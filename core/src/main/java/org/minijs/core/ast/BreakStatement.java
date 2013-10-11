package org.minijs.core.ast;

public class BreakStatement implements Statement {

    private final String mLabel;

    public BreakStatement() {
        this(null);
    }

    public BreakStatement(String label) {
        mLabel = label;
    }

    public String getLabel() {
        return mLabel;
    }
}
