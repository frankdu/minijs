package org.minijs.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.minijs.core.ast.ArrayLiteral;
import org.minijs.core.ast.Node;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayLiteralTest extends BaseParserTest {

    @Test
    public void testArrayLiteral() {
        String literals[] = {
                "[]", "[2]", "['hello', 'world', 3 + 7]",
                "[[]]", "[[], []]", "[[], [], []]",
        };
        int[] expectedLength = {0, 1, 3, 1, 2, 3};

        for (int i = 0; i < literals.length; i++) {
            String s = literals[i];
            initParser(s);
            ParseTree tree = mParser.arrayLiteral();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof ArrayLiteral);
            ArrayLiteral arrayLiteral = (ArrayLiteral) node;

            assertEquals(expectedLength[i], arrayLiteral.getSubExpressionCount());
        }
    }
}
