package org.minijs.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.minijs.core.ast.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DoWhileStatementTest extends BaseParserTest {

    @Test
    public void testIfStatement() {
        String[] statements = {
                "do{}while(true);",
                "do{if(true){}}while(false);",
                "do i += 1; while (i < 100);",
                "do { i += 1; if (i % 2 == 0) j++; } while(true);",
                "do ; while (true);"
        };

        Class[] expectedSubstatementClasses = {
                BlockStatement.class,
                BlockStatement.class,
                ExpressionStatement.class,
                BlockStatement.class,
                NoopStatement.class,
        };

        for (int i = 0; i < statements.length; i++) {
            initParser(statements[i]);
            ParseTree tree = mParser.program();
            Node node = mVisitor.visit(tree);

            assertEquals(BlockStatement.class, node.getClass());
            List<Statement> statementList = ((BlockStatement) node).getSubStatementList();

            assertEquals(1, statementList.size());

            Statement statement = statementList.get(0);
            assertEquals(DoWhileStatement.class, statement.getClass());

            DoWhileStatement doWhileStatement = (DoWhileStatement) statement;
            assertNotNull(doWhileStatement.getConditionExpression());
            assertNotNull(doWhileStatement.getSubStatement());

            assertEquals(expectedSubstatementClasses[i], doWhileStatement.getSubStatement().getClass());
        }
    }
}
