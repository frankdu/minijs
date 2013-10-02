package org.minijs.core.ast;

public class NoopStatement implements Statement {
    @Override
    public String toString() {
        return ";";
    }
}
