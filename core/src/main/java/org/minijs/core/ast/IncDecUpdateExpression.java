package org.minijs.core.ast;

import org.minijs.core.util.Preconditions;

public class IncDecUpdateExpression extends UnaryExpression {

    public enum UpdateTiming {
        PRE,
        POST,
    }

    private final UpdateTiming mUpdateTiming;

    public IncDecUpdateExpression(
            UpdateTiming updateTiming,
            Operator operator,
            Expression expression) {
        super(operator, expression);
        Preconditions.checkState(operator == Operator.INC || operator == Operator.DEC);
        mUpdateTiming = updateTiming;
    }
}
