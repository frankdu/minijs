package org.minijs.core.ast;

public class UnaryExpression implements Expression {

    private final Operator mOperator;
    private final Expression mSubExpression;

    public UnaryExpression(Operator op, Expression expr) {
        mOperator = op;
        mSubExpression = expr;
    }

    public Expression getSubExpression() {
        return mSubExpression;
    }

    public Operator getOperator() {
        return mOperator;
    }
}
