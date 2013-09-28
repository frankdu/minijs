package org.minijs.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.minijs.core.ast.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ConditionalExpressionTest extends BaseParserTest {
    @Test
    public void testConditionalExpression() {
        String expressions[] = {
                "a > b ? 1 : 0",
                "1 < 3 ? c + d : 7",
                "true ? true : false",
                "a == 3 ? 1 + 2 : 4 * 5",
                "(a == 3 ? true : false) ? a++ : ++b",
                "a == 3 || b < 4 ? a + b : a - b",
                "a == 3 && b < 4 ? a + b : a - b",
                "100 ? a : b"
        };

        Class[] expectedConditionExprClasses = {
                BinaryExpression.class,
                BinaryExpression.class,
                BooleanLiteral.class,
                BinaryExpression.class,
                ParenthesizedExpression.class,
                BinaryExpression.class,
                BinaryExpression.class,
                NumberLiteral.class
        };

        for (int i = 0; i < expressions.length; i++) {
            initParser(expressions[i]);
            ParseTree tree = mParser.expressionList();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof ExpressionList);
            ExpressionList expressionList = (ExpressionList) node;
            assertEquals(1, expressionList.size());

            Expression expr = expressionList.get(0);
            assertTrue(expr instanceof ConditionalExpression);
            ConditionalExpression conditionalExpression = (ConditionalExpression) expr;
            assertEquals(expectedConditionExprClasses[i], conditionalExpression.getConditionExpression().getClass());
        }
    }
}
