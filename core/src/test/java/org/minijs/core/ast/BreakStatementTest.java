package org.minijs.core.ast;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BreakStatementTest extends BaseParserTest {

    @Test
    public void testBreakStatement() {
        String[] statements = {
                "break",
                "break;",
                "break ;",
                "break branch3",
                "break branch4;",
                "break branch5 ;"
        };

        String[] expectedLabels = {
                null, null, null,
                "branch3", "branch4", "branch5",
        };

        for (int i = 0; i < statements.length; i++) {
            initParser(statements[i]);
            ParseTree tree = mParser.program();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof BlockStatement);
            List<Statement> statementList = ((BlockStatement) node).getSubStatementList();
            assertEquals(1, statementList.size());

            assertEquals(BreakStatement.class, statementList.get(0).getClass());

            BreakStatement breakStatement = (BreakStatement) statementList.get(0);
            assertEquals(expectedLabels[i], breakStatement.getLabel());
        }
    }
}
