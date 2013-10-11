package org.minijs.core.ast;

public class VariableDeclarator implements Node {

    private final Identifier mVariableName;

    private final Expression mInitializer;

    public VariableDeclarator(Identifier variableName) {
        this(variableName, null);
    }

    public VariableDeclarator(Identifier variableName, Expression initializer) {
        mVariableName = variableName;
        mInitializer = initializer;
    }

    public Identifier getVariableName() {
        return mVariableName;
    }

    public Expression getInitializer() {
        return mInitializer;
    }
}
