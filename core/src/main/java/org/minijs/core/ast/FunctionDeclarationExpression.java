package org.minijs.core.ast;

public class FunctionDeclarationExpression extends FunctionDeclaration implements Expression {

    public FunctionDeclarationExpression(FormalParameterList params, BlockStatement body) {
        super(null, params, body);
    }

    public FunctionDeclarationExpression(String name, FormalParameterList params, BlockStatement body) {
        super(name, params, body);
    }
}
