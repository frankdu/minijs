package org.minijs.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.minijs.core.ast.BlockStatement;
import org.minijs.core.ast.Node;
import org.minijs.core.ast.NoopStatement;
import org.minijs.core.ast.Statement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NoopStatementTest extends BaseParserTest {
    @Test
    public void testNoopStatement() {
        String[] statements = {
                ";",
                ";;",
                ";;;",
        };

        int[] expectedStatementCounts = {
                1, 2, 3,
        };

        Class[] expectedFirstStatementClasses = {
                NoopStatement.class,
                NoopStatement.class,
                NoopStatement.class,
        };

        for (int i = 0; i < statements.length; i++) {
            initParser(statements[i]);
            ParseTree tree = mParser.program();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof BlockStatement);
            List<Statement> statementList = ((BlockStatement) node).getSubStatementList();
            assertEquals(expectedStatementCounts[i], statementList.size());

            assertEquals(expectedFirstStatementClasses[i], statementList.get(0).getClass());
        }
    }
}
