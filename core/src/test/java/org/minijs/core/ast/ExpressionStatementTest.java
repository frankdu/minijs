package org.minijs.core.ast;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ExpressionStatementTest extends BaseParserTest {

    @Test
    public void testExpressionStatement() {
        String[] statements = {
                "1 + 1;",
                "a + b;",
                "-a;",
        };

        for (int i = 0; i < statements.length; i++) {
            initParser(statements[i]);
            ParseTree tree = mParser.program();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof BlockStatement);
            List<Statement> statementList = ((BlockStatement) node).getSubStatementList();
            assertEquals(1, statementList.size());
            assertEquals(ExpressionStatement.class, statementList.get(0).getClass());
        }
    }
}
