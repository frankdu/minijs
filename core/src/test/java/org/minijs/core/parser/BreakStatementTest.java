package org.minijs.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Ignore;
import org.junit.Test;
import org.minijs.core.ast.BlockStatement;
import org.minijs.core.ast.BreakStatement;
import org.minijs.core.ast.Node;
import org.minijs.core.ast.Statement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BreakStatementTest extends BaseParserTest {

    @Test
    @Ignore
    public void testBreakStatement() {
        String[] statements = {
                "break",
                "break;"
        };

        for (int i = 0; i < statements.length; i++) {
            initParser(statements[i]);
            ParseTree tree = mParser.program();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof BlockStatement);
            List<Statement> statementList = ((BlockStatement) node).getSubStatementList();
            assertEquals(1, statementList.size());

            assertEquals(BreakStatement.class, statementList.get(0).getClass());
        }
    }
}
