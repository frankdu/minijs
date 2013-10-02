package org.minijs.core.ast;

public class WhileStatement {

    private final Expression mConditionExpression;

    private final Statement mSubStatement;

    public WhileStatement(Expression conditionExpression, Statement subStatement) {
        mConditionExpression = conditionExpression;
        mSubStatement = subStatement;
    }
}
