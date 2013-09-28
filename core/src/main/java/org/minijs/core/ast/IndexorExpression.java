package org.minijs.core.ast;

public class IndexorExpression implements Expression {

    private final Expression mTargetExpression;
    private final Expression mIndexExpression;

    public IndexorExpression(Expression targetExpression, Expression indexExpression) {
        mTargetExpression = targetExpression;
        mIndexExpression = indexExpression;
    }
}
