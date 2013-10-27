package org.minijs.core.ast;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class FunctionDeclarationExpressionTest extends BaseParserTest {

    @Test
    public void testFunctionDeclarationExpression() {
        String[] expressions = {
                "var bar = function foo() {}",
                "var bar = function foo() {a = 1;}",
                "var bar = function foo(a){}",
                "var bar = function foo(a){a = 1; return;}",
                "var bar = function foo(a){a = 1; return a;}",
                "var bar = function foo(a,b){}",
                "var bar = function foo(a,b){a = 1 + b; return;}",
                "var bar = function foo(a,b){a = 1 + b; return a;}",
        };

        boolean[] expectedParameters = {
                false, false, true, true, true, true, true, true,
        };

        int[] expectedParameterCounts = {
                0, 0, 1, 1, 1, 2, 2, 2
        };

        for (int i = 0; i < expressions.length; i++) {
            initParser(expressions[i]);
            ParseTree tree = mParser.program();
            Node node = mVisitor.visit(tree);

            assertEquals(BlockStatement.class, node.getClass());
            List<Statement> statementList = ((BlockStatement) node).getSubStatementList();

            assertEquals(1, statementList.size());

            Statement statement = statementList.get(0);
            assertEquals(VariableDeclaratorsStatement.class, statement.getClass());

            VariableDeclaratorsStatement varDeclaratorsStatement = (VariableDeclaratorsStatement) statement;
            assertNotNull(varDeclaratorsStatement);

            List<VariableDeclarator> declarators = varDeclaratorsStatement.getVariableDeclarators().getVaribleDeclarators();

            assertNotNull(declarators);

            Expression initializer = declarators.get(0).getInitializer();

            assertTrue(initializer instanceof FunctionDeclarationExpression);

            FunctionDeclarationExpression functionDeclarationExpr = (FunctionDeclarationExpression) initializer;

            assertEquals("foo", functionDeclarationExpr.getFunctionName());

            assertEquals(BlockStatement.class, functionDeclarationExpr.getFunctionBody().getClass());

            if (expectedParameters[i]) {
                assertNotNull(functionDeclarationExpr.getParameters());
                assertEquals(expectedParameterCounts[i], functionDeclarationExpr.getParameters().getParameters().size());
            } else {
                assertNull(functionDeclarationExpr.getParameters());
            }
        }
    }
}
