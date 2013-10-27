package org.minijs.core.ast;

public class FinallyClause implements Node {

    private final BlockStatement mBlockStatement;

    public FinallyClause(BlockStatement blockStatement) {
        mBlockStatement = blockStatement;
    }

    public BlockStatement getBlockStatement() {
        return mBlockStatement;
    }
}
