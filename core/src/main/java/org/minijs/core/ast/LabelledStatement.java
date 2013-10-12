package org.minijs.core.ast;

public class LabelledStatement implements Statement {

    private final String mLabel;
    private final Statement mSubStatement;

    public LabelledStatement(String label, Statement statement) {
        mLabel = label;
        mSubStatement = statement;
    }

    public String getLabel() {
        return mLabel;
    }

    public Statement getSubStatement() {
        return mSubStatement;
    }
}
