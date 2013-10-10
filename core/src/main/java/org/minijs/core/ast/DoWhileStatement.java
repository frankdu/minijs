package org.minijs.core.ast;

public class DoWhileStatement {

    private final Expression mConditionExpression;

    private final Statement mSubStatement;

    public DoWhileStatement(Expression conditionExpression, Statement subStatement) {
        mConditionExpression = conditionExpression;
        mSubStatement = subStatement;
    }
}
