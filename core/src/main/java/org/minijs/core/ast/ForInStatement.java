package org.minijs.core.ast;

public class ForInStatement implements Statement {

    private final String mPropertyVar;
    private final Expression mObjectExpression;
    private final Statement mSubStatement;

    public ForInStatement(String propertyVar, Expression objectExpression, Statement subStatement) {
        mPropertyVar = propertyVar;
        mObjectExpression = objectExpression;
        mSubStatement = subStatement;
    }

    public String getPropertyVar() {
        return mPropertyVar;
    }

    public Expression getObjectExpression() {
        return mObjectExpression;
    }

    public Statement getSubStatement() {
        return mSubStatement;
    }
}
