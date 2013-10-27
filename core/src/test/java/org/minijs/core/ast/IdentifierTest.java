package org.minijs.core.ast;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IdentifierTest extends BaseParserTest {

    @Test
    public void testIdentifier() {
        String[] ids = {
                "_i", "i", "j_1", "$i"
        };

        for (String id : ids) {
            initParser(id);
            ParseTree tree = mParser.primaryExpression();
            Node node = mVisitor.visit(tree);

            assertTrue(node instanceof Identifier);
            Identifier identifier = (Identifier) node;

            assertEquals(id, identifier.getIdentifier());
        }
    }
}
