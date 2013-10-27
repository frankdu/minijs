package org.minijs.core.ast;

public class CatchClause implements Node {

    private final String mExceptionVarName;

    private final Expression mConditionExpression;

    private final BlockStatement mBlockStatement;

    public CatchClause(String exceptionVarName, BlockStatement blockStatement) {
        this(exceptionVarName, null, blockStatement);
    }

    public CatchClause(String exceptionVarName, Expression conditionExpression, BlockStatement blockStatement) {
        mExceptionVarName = exceptionVarName;
        mConditionExpression = conditionExpression;
        mBlockStatement = blockStatement;
    }

    public BlockStatement getBlockStatement() {
        return mBlockStatement;
    }

    public String getExceptionVarName() {
        return mExceptionVarName;
    }

    public Expression getConditionExpression() {
        return mConditionExpression;
    }
}
