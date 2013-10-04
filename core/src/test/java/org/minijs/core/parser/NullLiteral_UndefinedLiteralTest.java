package org.minijs.core.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.minijs.core.ast.Node;
import org.minijs.core.ast.NullLiteral;
import org.minijs.core.ast.UndefinedLiteral;

import static org.junit.Assert.assertTrue;

public class NullLiteral_UndefinedLiteralTest extends BaseParserTest {

    @Test
    public void testNullLiteral() {
        String input = "null";

        initParser(input);
        ParseTree tree = mParser.literal();
        Node node = mVisitor.visit(tree);

        assertTrue(node instanceof NullLiteral);
    }

    @Test
    public void testUndefinedLiteral() {
        String input = "undefined";

        initParser(input);
        ParseTree tree = mParser.literal();
        Node node = mVisitor.visit(tree);

        assertTrue(node instanceof UndefinedLiteral);
    }
}
