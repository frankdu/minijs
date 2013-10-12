package org.minijs.core.ast;

import org.minijs.core.ast.Identifier;
import org.minijs.core.ast.Node;
import org.minijs.core.util.Preconditions;

import java.util.Collections;
import java.util.List;

public class FormalParameterList implements Node {

    private final List<Identifier> mParameters;

    public FormalParameterList(List<Identifier> params) {
        Preconditions.checkNotNull(params);
        mParameters = Collections.unmodifiableList(params);
    }

    public List<Identifier> getParameters() {
        return mParameters;
    }
}
