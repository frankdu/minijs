package org.minijs.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.minijs.core.ast.*;

import java.util.List;

import static org.junit.Assert.*;

public class BlockStatementTest extends BaseParserTest {

    @Test
    public void testBlockStatement() {
        String[] statements = {
                "{}",
                "{if(true);}",
                "{a = 3;}",
                "{break;}",
                "{continue;}",
                "{return true;}",
                "{a = b + c;}",
                "{a = bar(100);}",
                "{a += 10 * (c + d);}",
        };

        int[] expectedSubStatementNum = {
                0,
                1, 1, 1, 1, 1, 1, 1, 1,
        };

        Class[] expectedFirstSubstatemntClasses = {
                null,
                IfStatement.class,
                ExpressionStatement.class,
                BreakStatement.class,
                ContinueStatement.class,
                ReturnStatement.class,
                ExpressionStatement.class,
                ExpressionStatement.class,
                ExpressionStatement.class,
        };

        for (int i = 0; i < statements.length; i++) {
            initParser(statements[i]);
            ParseTree tree = mParser.program();
            Node node = mVisitor.visit(tree);

            assertEquals(BlockStatement.class, node.getClass());
            List<Statement> statementList = ((BlockStatement) node).getSubStatementList();

            assertEquals(1, statementList.size());

            Statement statement = statementList.get(0);
            assertEquals(BlockStatement.class, statement.getClass());

            BlockStatement blockStatement = (BlockStatement) statement;
            assertNotNull(blockStatement);
            int subStatementNum = blockStatement.getSubStatementList().size();

            assertEquals(expectedSubStatementNum[i], subStatementNum);

            if (subStatementNum == 1) {
                assertEquals(
                        expectedFirstSubstatemntClasses[i],
                        blockStatement.getSubStatementList().get(0).getClass());
            }
        }
    }
}
