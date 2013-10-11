package org.minijs.core.ast;

public class ForControl implements Node {

    private final VariableDeclarators mVariableDeclarators;
    private final Expression mConditionExpression;
    private final ExpressionList mUpdateExpressionList;

    public ForControl(
            VariableDeclarators variableDeclarators,
            Expression conditionExpression,
            ExpressionList updateExpressionList) {
        mVariableDeclarators = variableDeclarators;
        mConditionExpression = conditionExpression;
        mUpdateExpressionList = updateExpressionList;
    }

    public VariableDeclarators getVariableDeclarators() {
        return mVariableDeclarators;
    }

    public Expression getConditionExpression() {
        return mConditionExpression;
    }

    public ExpressionList getUpdateExpressionList() {
        return mUpdateExpressionList;
    }
}
