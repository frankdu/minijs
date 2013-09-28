package org.minijs.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.minijs.core.ast.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestIncDecUpdateExpression extends BaseParserTest {

    @Test
    public void testIncDecUpdateExpression() {
        String[] expressions = {
                "a++",
                "a--",
                "++a",
                "--a",
                "(a)++",
                "(a)--",
                "++(a)",
                "--(a)"
        };

        Class[] expectedSubExpressionClasses = {
                Identifier.class,
                Identifier.class,
                Identifier.class,
                Identifier.class,
                ParenthesizedExpression.class,
                ParenthesizedExpression.class,
                ParenthesizedExpression.class,
                ParenthesizedExpression.class
        };

        for (int i = 0; i < expressions.length; i++) {
            initParser(expressions[i]);
            ParseTree tree = parser.expressionList();
            Node node = visitor.visit(tree);

            assertTrue(node instanceof ExpressionList);
            ExpressionList expressionList = (ExpressionList) node;
            assertEquals(1, expressionList.size());

            Expression expr = expressionList.get(0);
            assertTrue(expr instanceof IncDecUpdateExpression);
            Expression subExpr = ((IncDecUpdateExpression)expr).getSubExpression();
            assertNotNull(subExpr);
            assertEquals(expectedSubExpressionClasses[i], subExpr.getClass());
        }
    }
}
