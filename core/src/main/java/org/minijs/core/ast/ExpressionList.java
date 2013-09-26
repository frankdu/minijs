package org.minijs.core.ast;

import java.util.List;

public class ExpressionList implements Node {

    private final List<Expression> mExpressionList;

    public ExpressionList(List<Expression> list) {
        mExpressionList = list;
    }

    public Expression get(int index) {
        return mExpressionList.get(index);
    }

    public int size() {
        return mExpressionList == null ? 0 : mExpressionList.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < mExpressionList.size(); i++) {
            Node expr = mExpressionList.get(i);
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(expr);
        }
        return builder.toString();
    }

}
