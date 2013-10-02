package org.minijs.core.ast;

import java.util.ArrayList;
import java.util.List;

public class BlockStatement implements Statement {

    private final List<Statement> mSubStatementList;

    public BlockStatement(List<Statement> subStatementList) {
        mSubStatementList = (subStatementList == null ? new ArrayList<Statement>() : subStatementList);
    }

    public List<Statement> getSubStatementList() {
        return mSubStatementList;
    }
}
