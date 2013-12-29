package org.minijs.core.ast;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThrowStatementTest extends BaseParserTest {
    @Test
    public void testThrowStatement() {
        String[] stmts = {
                "throw 1;",
                "throw a + b;",
                "throw \"hello\";",
                "throw - b"
        };

        Class[] expectedClasses = {
                NumberLiteral.class,
                BinaryExpression.class,
                StringLiteral.class,
                UnaryExpression.class
        };

        for (int i = 0; i < stmts.length; i++) {
            String s = stmts[i];
            initParser(s);
            ParseTree tree = mParser.statement();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof ThrowStatement);
            ThrowStatement stmt = (ThrowStatement) node;

            assertNotNull(stmt.getExpression());
            assertEquals(expectedClasses[i], stmt.getExpression().getClass());
        }
    }
}
