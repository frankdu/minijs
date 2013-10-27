package org.minijs.core.ast;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BooleanLiteralTest extends BaseParserTest {

    @Test
    public void testBooleanLiteral() {
        boolean expected[] = {true, false};
        String values[] = {"true", "false"};

        for (int i = 0; i < values.length; i++) {
            String v = values[i];

            initParser(v);
            ParseTree tree = mParser.literal();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof BooleanLiteral);
            BooleanLiteral literal = (BooleanLiteral) node;
            assertEquals(literal.getValue(), expected[i]);
        }
    }
}
