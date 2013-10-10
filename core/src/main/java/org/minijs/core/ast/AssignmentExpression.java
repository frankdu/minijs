package org.minijs.core.ast;

public class AssignmentExpression implements Expression {

    private final Expression mTargetExpression;
    private final Expression mValueExpression;

    public AssignmentExpression(Expression targetExpression, Expression valueExpression) {
        mTargetExpression = targetExpression;
        mValueExpression = valueExpression;
    }

    public Expression getTargetExpression() {
        return mTargetExpression;
    }

    public Expression getValueExpression() {
        return mValueExpression;
    }
}
