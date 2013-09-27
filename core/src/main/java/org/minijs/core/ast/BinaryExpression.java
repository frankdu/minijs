package org.minijs.core.ast;

public class BinaryExpression implements Expression {

    private final Operator mOperator;
    private final Expression mLeftExpression;
    private final Expression mRightExpression;

    public BinaryExpression(Operator op, Expression left, Expression right) {
        mOperator = op;
        mLeftExpression = left;
        mRightExpression = right;
    }

    public Operator getOperator() {
        return mOperator;
    }

    public Expression getLeftExpression() {
        return mLeftExpression;
    }

    public Expression getRightExpression() {
        return mRightExpression;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(mLeftExpression.toString());
        builder.append(' ');
        builder.append(mOperator);
        builder.append(' ');
        builder.append(mRightExpression);
        return builder.toString();
    }
}
