package org.minijs.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.minijs.core.ast.Node;
import org.minijs.core.ast.StringLiteral;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StringLiteralTest extends BaseParserTest {

    @Test
    public void testStringLiteral() {
        String[] strs = {
                "", "hello", "a b", "a + b",
                " a", " a ", "a ", "a\t",
                "hello 世界",
                "こんにちは世界",
                "Bonjour tout le monde",
                "مرحبا العالم"
        };

        for (String s : strs) {
            initParser("\"" + s + "\"");
            ParseTree tree = mParser.literal();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof StringLiteral);
            StringLiteral literal = (StringLiteral) node;

            assertEquals(s, literal.getValue());
        }
    }
}
