package org.minijs.core.ast;

public class ArrayLiteral implements Literal {

    private final ExpressionList mExpressionList;

    public ArrayLiteral(ExpressionList list) {
        mExpressionList = list;
    }

    public ExpressionList getExpressionList() {
        return mExpressionList;
    }

    public Expression getSubExpression(int index) {
        return mExpressionList.get(index);
    }

    public int getSubExpressionCount() {
        return mExpressionList == null ? 0 : mExpressionList.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        builder.append(mExpressionList);
        builder.append("]");
        return builder.toString();
    }
}
