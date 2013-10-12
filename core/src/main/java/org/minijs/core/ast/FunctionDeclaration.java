package org.minijs.core.ast;

public class FunctionDeclaration implements Statement {

    private final String mFunctionName;
    private final ExpressionList mParameters;
    private final BlockStatement mFunctionBody;

    public FunctionDeclaration(String name, ExpressionList params, BlockStatement body) {
        mFunctionName = name;
        mParameters = params;
        mFunctionBody = body;
    }

    public String getFunctionName() {
        return mFunctionName;
    }

    public ExpressionList getParameters() {
        return mParameters;
    }

    public BlockStatement getFunctionBody() {
        return mFunctionBody;
    }
}
