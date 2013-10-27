package org.minijs.core.ast;

import java.util.Collections;
import java.util.List;

public class TryStatement implements Statement {

    private final BlockStatement mTryBlockStatement;
    private final List<CatchClause> mCatchClauseList;
    private final FinallyClause mFinallyClause;

    public TryStatement(BlockStatement tryBlockStatement, List<CatchClause> catchClauseList, FinallyClause finallyClause) {
        mTryBlockStatement = tryBlockStatement;
        mCatchClauseList = Collections.unmodifiableList(catchClauseList);
        mFinallyClause = finallyClause;
    }

    public BlockStatement getTryBlockStatement() {
        return mTryBlockStatement;
    }

    public List<CatchClause> getCatchClauseList() {
        return mCatchClauseList;
    }

    public FinallyClause getFinallyClause() {
        return mFinallyClause;
    }
}
