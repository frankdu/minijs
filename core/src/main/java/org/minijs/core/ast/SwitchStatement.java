package org.minijs.core.ast;

import java.util.List;

public class SwitchStatement implements Statement {

    private final Expression mExpression;
    private final List<CaseStatement> mCaseStatementList;
    private final CaseStatement mDefaultCaseStatement;

    public SwitchStatement(Expression expression, List<CaseStatement> caseStatementList, CaseStatement defaultCaseStatement) {
        mExpression = expression;
        mCaseStatementList = caseStatementList;
        mDefaultCaseStatement = defaultCaseStatement;
    }

    public Expression getExpression() {
        return mExpression;
    }

    public List<CaseStatement> getCaseStatementList() {
        return mCaseStatementList;
    }

    public CaseStatement getDefaultCaseStatement() {
        return mDefaultCaseStatement;
    }
}
