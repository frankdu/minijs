package org.minijs.core.ast;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NumberLiteralTest extends BaseParserTest {

    @Test
    public void testNumberLiteralInteger() {
        int numbers[] = {0, 25, -25, 123, -123};

        for (int n : numbers) {
            initParser(String.valueOf(n));
            ParseTree tree = mParser.literal();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof NumberLiteral);
            NumberLiteral literal = (NumberLiteral) node;
            assertEquals(n, literal.getValue(), 1e-10);
        }
    }

    @Test
    public void testNumberLiteralDouble() {
        double numbers[] = {0.0, 1.2, -1.2, 1.49, -1.49, 1.99, -1.99};

        for (double n : numbers) {
            initParser(String.valueOf(n));
            ParseTree tree = mParser.literal();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof NumberLiteral);
            NumberLiteral literal = (NumberLiteral) node;
            assertEquals(n, literal.getValue(), 1e-10);
        }
    }
}
