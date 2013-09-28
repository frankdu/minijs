package org.minijs.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.minijs.core.ast.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FunctionCallExpressionTest extends BaseParserTest {

    @Test
    public void testFunctionCallExpression() {
        String[] expressions = {
                "foo()",
                "foo(a)",
                "foo(a, b, 3)",
                "foo[0]()",
                "foo[0](a)",
                "foo[0](a, b, 3)",
        };

        Class[] expectedFunctionObjectClasses = {
                Identifier.class,
                Identifier.class,
                Identifier.class,
                IndexorExpression.class,
                IndexorExpression.class,
                IndexorExpression.class,
        };

        int[] expectedArgumentCounts = {
                0, 1, 3, 0, 1, 3,
        };

        for (int i = 0; i < expressions.length; i++) {
            initParser(expressions[i]);
            ParseTree tree = mParser.expressionList();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof ExpressionList);
            ExpressionList expressionList = (ExpressionList) node;
            assertEquals(1, expressionList.size());

            Expression expr = expressionList.get(0);
            assertTrue(expr instanceof FunctionCallExpression);
            FunctionCallExpression functionCallExpr = (FunctionCallExpression) expr;

            Expression functionObject = functionCallExpr.getFunctionObject();
            assertNotNull(functionObject);
            assertEquals(expectedFunctionObjectClasses[i], functionObject.getClass());

            ExpressionList params = functionCallExpr.getParameters();
            assertNotNull(params);
            assertEquals(expectedArgumentCounts[i], params.size());
        }
    }
}
