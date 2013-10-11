package org.minijs.core.ast;

public class ReturnStatement implements Statement {

    private final Expression mExpression;

    public ReturnStatement(Expression expression) {
        mExpression = expression;
    }

    public Expression getExpression() {
        return mExpression;
    }
}
