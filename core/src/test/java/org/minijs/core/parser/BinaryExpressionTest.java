package org.minijs.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.minijs.core.ast.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BinaryExpressionTest extends BaseParserTest {

    @Test
    public void testBinaryExpression() {
        String expressions[] = {
                "1 + 2",
                "1 * 3",
                "1 % 5",
                "a < 6",
                "a <= 7",
                "b > c",
                "b >= c",
                "a == b",
                "a != b",
                "a === b",
                "a !== b",
                "a && b",
                "a || b",
        };

        Operator[] expectedOperators = {
                Operator.PLUS,
                Operator.MUL,
                Operator.MOD,
                Operator.LT,
                Operator.LE,
                Operator.GT,
                Operator.GE,
                Operator.EQ,
                Operator.NEQ,
                Operator.EXACT_EQ,
                Operator.EXACT_NEQ,
                Operator.AND,
                Operator.OR,
        };

        for (int i = 0; i < expressions.length; i++) {
            initParser(expressions[i]);
            ParseTree tree = mParser.expressionList();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof ExpressionList);
            ExpressionList expressionList = (ExpressionList) node;
            assertEquals(1, expressionList.size());

            Expression expr = expressionList.get(0);
            assertTrue(expr instanceof BinaryExpression);
            BinaryExpression binaryExpression = (BinaryExpression) expr;
            assertEquals(expectedOperators[i], binaryExpression.getOperator());
            assertNotNull(binaryExpression.getLeftExpression());
            assertNotNull(binaryExpression.getRightExpression());
        }
    }
}
