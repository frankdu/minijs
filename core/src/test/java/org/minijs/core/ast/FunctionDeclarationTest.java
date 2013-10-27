package org.minijs.core.ast;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FunctionDeclarationTest extends BaseParserTest {

    @Test
    public void testIfStatement() {
        String[] statements = {
                "function foo() {}",
                "function foo() {a = 1;}",
                "function foo(a){}",
                "function foo(a){a = 1; return;}",
                "function foo(a){a = 1; return a;}",
                "function foo(a,b){}",
                "function foo(a,b){a = 1 + b; return;}",
                "function foo(a,b){a = 1 + b; return a;}",
        };

        boolean[] expectedParameters = {
                false, false, true, true, true, true, true, true,
        };

        int[] expectedParameterCounts = {
                0, 0, 1, 1, 1, 2, 2, 2
        };

        for (int i = 0; i < statements.length; i++) {
            initParser(statements[i]);
            ParseTree tree = mParser.program();
            Node node = mVisitor.visit(tree);

            assertEquals(BlockStatement.class, node.getClass());
            List<Statement> statementList = ((BlockStatement) node).getSubStatementList();

            assertEquals(1, statementList.size());

            Statement statement = statementList.get(0);
            assertEquals(FunctionDeclaration.class, statement.getClass());

            FunctionDeclaration funcDeclaration = (FunctionDeclaration) statement;
            assertNotNull(funcDeclaration);
            assertEquals("foo", funcDeclaration.getFunctionName());

            assertEquals(BlockStatement.class, funcDeclaration.getFunctionBody().getClass());

            if (expectedParameters[i]) {
                assertNotNull(funcDeclaration.getParameters());
                assertEquals(expectedParameterCounts[i], funcDeclaration.getParameters().getParameters().size());
            } else {
                assertNull(funcDeclaration.getParameters());
            }
        }
    }
}
