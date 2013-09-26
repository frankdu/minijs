package org.minijs.core.ast;

public class ParenthesizedExpression implements Expression {

    private final Expression mExpression;

    public ParenthesizedExpression(Expression expr) {
        mExpression = expr;
    }

    public Expression getSubExpression() {
        return mExpression;
    }

    @Override
    public String toString() {
        return "(" + mExpression + ")";
    }
}
