package org.minijs.core.ast;

public class WhileStatement implements Statement {

    private final Expression mConditionExpression;

    private final Statement mSubStatement;

    public WhileStatement(Expression conditionExpression, Statement subStatement) {
        mConditionExpression = conditionExpression;
        mSubStatement = subStatement;
    }

    public Expression getConditionExpression() {
        return mConditionExpression;
    }

    public Statement getSubStatement() {
        return mSubStatement;
    }
}
