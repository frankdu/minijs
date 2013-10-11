package org.minijs.core.ast;

public class ForStatement implements Statement {

    private final ForControl mForControl;
    private final Statement mSubStatement;

    public ForStatement(
            ForControl forControl,
            Statement subStatement) {
        mForControl = forControl;
        mSubStatement = subStatement;
    }

    public ForControl getForControl() {
        return mForControl;
    }

    public Statement getSubStatement() {
        return mSubStatement;
    }
}
