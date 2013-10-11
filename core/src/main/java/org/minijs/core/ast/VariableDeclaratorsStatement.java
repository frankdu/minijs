package org.minijs.core.ast;

public class VariableDeclaratorsStatement implements Statement {

    private final VariableDeclarators mVariableDeclarators;

    public VariableDeclaratorsStatement(VariableDeclarators variableDeclarators) {
        mVariableDeclarators = variableDeclarators;
    }

    public VariableDeclarators getVariableDeclarators() {
        return mVariableDeclarators;
    }
}
