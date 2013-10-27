package org.minijs.core.ast;

public class ThrowStatement implements Statement {

    private final Expression mExpression;

    public ThrowStatement(Expression expression) {
        mExpression = expression;
    }

    public Expression getExpression() {
        return mExpression;
    }

    @Override
    public String toString() {
        return String.format("throw %s;", mExpression);
    }
}
