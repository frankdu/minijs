package org.minijs.core.ast;

public class DoWhileStatement implements Statement {

    private final Expression mConditionExpression;

    private final Statement mSubStatement;

    public DoWhileStatement(Expression conditionExpression, Statement subStatement) {
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
