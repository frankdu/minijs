package org.minijs.core.ast;

public class FunctionDeclaration implements Statement {

    private final String mFunctionName;
    private final FormalParameterList mParameters;
    private final BlockStatement mFunctionBody;

    public FunctionDeclaration(String name, FormalParameterList params, BlockStatement body) {
        mFunctionName = name;
        mParameters = params;
        mFunctionBody = body;
    }

    public String getFunctionName() {
        return mFunctionName;
    }

    public FormalParameterList getParameters() {
        return mParameters;
    }

    public BlockStatement getFunctionBody() {
        return mFunctionBody;
    }
}
