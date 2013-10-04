package org.minijs.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.minijs.core.ast.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnaryExpressionTest extends BaseParserTest {

    @Test
    public void testUnaryExpression() {
        String expressions[] = {"+index", "-index", "!false", "++index", "--index"};
        Operator expectedOperators[] = {Operator.PLUS, Operator.MINUS, Operator.NOT, Operator.INC, Operator.DEC};
        Class expectedSubExpressionClasses[] = {
                Identifier.class, Identifier.class, BooleanLiteral.class, Identifier.class, Identifier.class};

        for (int i = 0; i < expressions.length; i++) {
            initParser(expressions[i]);
            ParseTree tree = mParser.unaryExpression();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof UnaryExpression);
            UnaryExpression unaryExpression = (UnaryExpression) node;

            assertEquals(expectedOperators[i], unaryExpression.getOperator());
            assertEquals(expectedSubExpressionClasses[i], unaryExpression.getSubExpression().getClass());
        }
    }
}
