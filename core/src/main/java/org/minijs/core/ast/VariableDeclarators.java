package org.minijs.core.ast;

import org.minijs.core.util.Preconditions;

import java.util.Collections;
import java.util.List;

public class VariableDeclarators implements Node {

    private final List<VariableDeclarator> mVaribleDeclarators;

    public VariableDeclarators(List<VariableDeclarator> variableDeclarators) {
        Preconditions.checkNotNull(variableDeclarators);
        mVaribleDeclarators = Collections.unmodifiableList(variableDeclarators);
    }

    public List<VariableDeclarator> getVaribleDeclarators() {
        return mVaribleDeclarators;
    }
}
