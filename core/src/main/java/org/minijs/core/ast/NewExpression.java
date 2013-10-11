package org.minijs.core.ast;

public class NewExpression implements Expression {

    private final String mClassName;
    private final ExpressionList mArguments;

    public NewExpression(String className, ExpressionList arguments) {
        mClassName = className;
        mArguments = arguments;
    }

    public String getClassName() {
        return mClassName;
    }

    public ExpressionList getArguments() {
        return mArguments;
    }
}
