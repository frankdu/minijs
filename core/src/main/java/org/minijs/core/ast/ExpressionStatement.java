package org.minijs.core.ast;

public class ExpressionStatement implements Statement {

    private final Expression mExpression;

    public ExpressionStatement(Expression expression) {
        mExpression = expression;
    }
}
