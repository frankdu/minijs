package org.minijs.core.ast;

public class ConditionalExpression implements Expression {
    private final Expression mConditionExpression;
    private final Expression mTrueExpression;
    private final Expression mFalseExpression;

    public ConditionalExpression(
            Expression conditionExpression,
            Expression trueExpression,
            Expression falseExpression) {
        mConditionExpression = conditionExpression;
        mTrueExpression = trueExpression;
        mFalseExpression = falseExpression;
    }

    public Expression getConditionExpression() {
        return mConditionExpression;
    }

    public Expression getTrueExpression() {
        return mTrueExpression;
    }

    public Expression getFalseExpression() {
        return mFalseExpression;
    }

    @Override
    public String toString() {
        return mConditionExpression + " ? " + mTrueExpression + " : " + mFalseExpression;
    }
}
