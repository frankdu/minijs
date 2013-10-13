package org.minijs.core.ast;

import org.minijs.core.util.Preconditions;

import java.util.Collections;
import java.util.List;

public class CaseStatement implements Statement {

    private final Expression mExpression;
    private final List<Statement> mStatementList;

    public CaseStatement(List<Statement> statementList) {
        this(true, null, statementList);
    }

    public CaseStatement(Expression expression, List<Statement> statementList) {
        this(false, expression, statementList);
    }

    private CaseStatement(boolean isNullExpression, Expression expression, List<Statement> statementList) {
        if (isNullExpression) {
            Preconditions.checkNull(expression);
        } else {
            Preconditions.checkNotNull(expression);
        }

        mExpression = expression;
        mStatementList = Collections.unmodifiableList(statementList);
    }

    public Expression getExpression() {
        return mExpression;
    }

    public List<Statement> getStatementList() {
        return mStatementList;
    }
}
