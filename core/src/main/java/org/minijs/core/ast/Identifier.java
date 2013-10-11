package org.minijs.core.ast;

public class Identifier implements Expression {

    private final String mIdentifier;

    public Identifier(String id) {
        mIdentifier = id;
    }

    public String getIdentifier() {
        return mIdentifier;
    }

    @Override
    public String toString() {
        return mIdentifier;
    }
}
