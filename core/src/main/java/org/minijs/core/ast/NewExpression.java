package org.minijs.core.ast;

public class NewExpression implements Expression {

    private final String mClassName;
    private final ExpressionList mParameters;

    public NewExpression(String className, ExpressionList parameters) {
        mClassName = className;
        mParameters = parameters;
    }
}
