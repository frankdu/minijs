package org.minijs.core.ast;

public class PropertyExpression implements Expression {

    private final Expression mTargetExpression;
    private final String mPropertyName;

    public PropertyExpression(Expression targetExpression, String propertyName) {
        mTargetExpression = targetExpression;
        mPropertyName = propertyName;
    }
}
