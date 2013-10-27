package org.minijs.core.ast;

public class FunctionDeclarationStatement extends FunctionDeclaration implements Statement {

    public FunctionDeclarationStatement(String name, FormalParameterList params, BlockStatement body) {
        super(name, params, body);
    }
}
