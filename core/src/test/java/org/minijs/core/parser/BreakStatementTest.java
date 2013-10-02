package org.minijs.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Ignore;
import org.junit.Test;
import org.minijs.core.ast.BreakStatement;
import org.minijs.core.ast.Node;

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
            ParseTree tree = mParser.statement();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof BreakStatement);
        }
    }
}
