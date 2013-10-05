package org.minijs.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.minijs.core.ast.BlockStatement;
import org.minijs.core.ast.IfStatement;
import org.minijs.core.ast.Node;
import org.minijs.core.ast.Statement;

import java.util.List;

import static org.junit.Assert.*;

public class IfStatementTest extends BaseParserTest {

    @Test
    public void testIfStatement() {
        String[] statements = {
                "if(true);",
                "if(true){}",
                "if(true); else ;",
                "if(true){} else {}",
                "if (a > 0) a = 100;",
                "if (a > 0) a = 100; else a = -100;",
                "if (a > 0) { a = 100;}",
                "if (a > 0) { a = 100;} else { a = -100; }",
        };

        boolean[] expectedElseStatement = {
                false, false, true, true, false, true, false, true,
        };

        for (int i = 0; i < statements.length; i++) {
            initParser(statements[i]);
            ParseTree tree = mParser.program();
            Node node = mVisitor.visit(tree);

            assertEquals(BlockStatement.class, node.getClass());
            List<Statement> statementList = ((BlockStatement) node).getSubStatementList();

            assertEquals(1, statementList.size());

            Statement statement = statementList.get(0);
            assertEquals(IfStatement.class, statement.getClass());

            IfStatement ifStatement = (IfStatement) statement;
            assertNotNull(ifStatement.getConditionExpression());
            assertNotNull(ifStatement.getThenStatement());

            if (expectedElseStatement[i]) {
                assertNotNull(ifStatement.getElseStatement());
            } else {
                assertNull(ifStatement.getElseStatement());
            }
        }
    }
}
