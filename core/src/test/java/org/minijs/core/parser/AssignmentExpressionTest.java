package org.minijs.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.minijs.core.ast.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AssignmentExpressionTest extends BaseParserTest {
    @Test
    public void testBinaryExpression() {
        String expressions[] = {
                "a = 3",
                "a = b",
                "a = b + c",
                "a = a + 1",
                "a = b > 0 ? +1 : -1",
                "a = a + b * c",
                "a += 1",
                "a -= 1",
                "a *= 1",
                "a /= 1",
                "a %= 1",
                "a <<= 1",
                "a >>= 1",
                "a >>>= 1",
                "a &= 1",
                "a ^= 1",
                "a |= 1",

        };

        Operator[] expectedOperators = {
                Operator.ASSIGN,
                Operator.ASSIGN,
                Operator.ASSIGN,
                Operator.ASSIGN,
                Operator.ASSIGN,
                Operator.ASSIGN,
                Operator.PLUS_ASSIGN,
                Operator.MINUS_ASSIGN,
                Operator.MUL_ASSIGN,
                Operator.DIV_ASSIGN,
                Operator.MOD_ASSIGN,
                Operator.BIT_LEFT_SHIFT_ASSIGN,
                Operator.BIT_RIGHT_SHIFT_ASSIGN,
                Operator.BIT_UNSIGNED_RIGHT_SHIFT_ASSIGN,
                Operator.BIT_AND_ASSIGN,
                Operator.BIT_XOR_ASSIGN,
                Operator.BIT_OR_ASSIGN,
        };

        for (int i = 0; i < expressions.length; i++) {
            initParser(expressions[i]);
            ParseTree tree = mParser.expressionList();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof ExpressionList);
            ExpressionList expressionList = (ExpressionList) node;
            assertEquals(1, expressionList.size());

            Expression expr = expressionList.get(0);
            assertTrue(expr instanceof AssignmentExpression);
            AssignmentExpression assignmentExpression = (AssignmentExpression) expr;

            assertNotNull(assignmentExpression.getTargetExpression());
            assertNotNull(assignmentExpression.getValueExpression());

            assertEquals(Identifier.class, assignmentExpression.getTargetExpression().getClass());

            assertEquals(expectedOperators[i], assignmentExpression.getAssignOperator());
        }
    }

}
