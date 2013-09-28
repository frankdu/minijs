package org.minijs.core.ast;

public class FunctionCallExpression implements Expression {

    private final Expression mFunctionObject;
    private final ExpressionList mParameters;

    public FunctionCallExpression(Expression functionObject, ExpressionList parameters) {
        mFunctionObject = functionObject;
        mParameters = parameters;
    }

    public Expression getFunctionObject() {
        return mFunctionObject;
    }

    public ExpressionList getParameters() {
        return mParameters;
    }
}
