package org.minijs.core.ast;

public class IfStatement implements Statement {

    private final Expression mConditionExpression;

    private final Statement mThenStatement;

    private final Statement mElseStatement;

    public IfStatement(Expression conditionExpression, Statement thenStatement) {
        this(conditionExpression, thenStatement, null);
    }

    public IfStatement(Expression conditionExpression, Statement thenStatement, Statement elseStatement) {
        mConditionExpression = conditionExpression;
        mThenStatement = thenStatement;
        mElseStatement = elseStatement;
    }

    public Expression getConditionExpression() {
        return mConditionExpression;
    }

    public Statement getThenStatement () {
        return mThenStatement;
    }

    public Statement getElseStatement() {
        return mElseStatement;
    }
}
