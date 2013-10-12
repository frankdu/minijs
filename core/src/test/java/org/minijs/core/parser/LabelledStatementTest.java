package org.minijs.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.minijs.core.ast.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LabelledStatementTest extends BaseParserTest {

    @Test
    public void testLabelledStatement() {
        String[] statements = {
                "branch1: a = 10;",
                "branch2: if(true);",
                "branch3: a = b + c;",
                "branch4: while(true) a += 3;",
        };

        String[] expectedBranchNames = {
                "branch1", "branch2", "branch3", "branch4",
        };

        Class[] expectedStatementClasses = {
                ExpressionStatement.class,
                IfStatement.class,
                ExpressionStatement.class,
                WhileStatement.class,
        };

        for (int i = 0; i < statements.length; i++) {
            initParser(statements[i]);
            ParseTree tree = mParser.program();
            Node node = mVisitor.visit(tree);

            assertEquals(BlockStatement.class, node.getClass());
            List<Statement> statementList = ((BlockStatement) node).getSubStatementList();

            assertEquals(1, statementList.size());

            Statement statement = statementList.get(0);
            assertEquals(LabelledStatement.class, statement.getClass());

            LabelledStatement labelledStatement = (LabelledStatement) statement;
            assertNotNull(labelledStatement.getLabel());
            assertNotNull(labelledStatement.getSubStatement());

            assertEquals(expectedBranchNames[i], labelledStatement.getLabel());
            assertEquals(expectedStatementClasses[i], labelledStatement.getSubStatement().getClass());
        }
    }
}
