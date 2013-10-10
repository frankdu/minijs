package org.minijs.core.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockStatement implements Statement {

    private final List<Statement> mSubStatementList;

    public BlockStatement(List<Statement> subStatementList) {
        if (subStatementList == null) {
            subStatementList = new ArrayList<Statement>();
        }
        mSubStatementList = Collections.unmodifiableList(subStatementList);
    }

    public List<Statement> getSubStatementList() {
        return mSubStatementList;
    }
}
