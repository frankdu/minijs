package org.minijs.core.ast;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParenthesizedExpressionTest extends BaseParserTest {

    @Test
    public void testParenthesizedExpression() {
        String expressions[] = {"(3)", "(index)", "(null)", "(\"hello\")"};
        Class expectedSubExpressionClasses[] = {NumberLiteral.class, Identifier.class, NullLiteral.class, StringLiteral.class};

        for (int i = 0; i < expressions.length; i++) {
            initParser(expressions[i]);
            ParseTree tree = mParser.parenthesizedExpression();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof ParenthesizedExpression);
            Expression subExpr = ((ParenthesizedExpression) node).getSubExpression();

            assertEquals(expectedSubExpressionClasses[i], subExpr.getClass());
        }
    }
}
