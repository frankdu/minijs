package org.minijs.core.ast;

public class AssignmentExpression implements Expression {

    private final Operator mAssignOperator;
    private final Expression mTargetExpression;
    private final Expression mValueExpression;

    public AssignmentExpression(Operator operator, Expression targetExpression, Expression valueExpression) {
        mAssignOperator = operator;
        mTargetExpression = targetExpression;
        mValueExpression = valueExpression;
    }

    public Operator getAssignOperator() {
        return mAssignOperator;
    }

    public Expression getTargetExpression() {
        return mTargetExpression;
    }

    public Expression getValueExpression() {
        return mValueExpression;
    }
}
