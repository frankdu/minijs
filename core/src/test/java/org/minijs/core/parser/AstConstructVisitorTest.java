package org.minijs.core.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;
import org.junit.Test;
import org.minijs.core.ast.*;
import org.minijs.parser.antlr.JavaScriptLexer;
import org.minijs.parser.antlr.JavaScriptParser;

import static org.junit.Assert.*;

public class AstConstructVisitorTest {

    private JavaScriptParser parser;
    private AstConstructVisitor visitor;

    @Before
    public void setup() {
        parser = new JavaScriptParser(null);
        visitor = new AstConstructVisitor();
    }

    private void initParser(String s) {
        JavaScriptLexer lexer = new JavaScriptLexer(new ANTLRInputStream(s));
        parser.setTokenStream(new CommonTokenStream(lexer));
    }

    @Test
    public void testNumberLiteral() {
        int numbers[] = {0, 25, -25};

        for (int n : numbers) {
            initParser(String.valueOf(n));
            ParseTree tree = parser.literal();
            Node node = visitor.visit(tree);

            assertTrue(node instanceof NumberLiteral);
            NumberLiteral literal = (NumberLiteral) node;
            assertEquals(n, literal.getValue(), 1e-10);
        }
    }

    @Test
    public void testBooleanLiteral() {
        boolean expected[] = {true, false};
        String values[] = {"true", "false"};

        for (int i = 0; i < values.length; i++) {
            String v = values[i];

            initParser(v);
            ParseTree tree = parser.literal();
            Node node = visitor.visit(tree);

            assertTrue(node instanceof BooleanLiteral);
            BooleanLiteral literal = (BooleanLiteral) node;
            assertEquals(literal.getValue(), expected[i]);
        }
    }

    @Test
    public void testNullLiteral() {
        String input = "null";

        initParser(input);
        ParseTree tree = parser.literal();
        Node node = visitor.visit(tree);

        assertTrue(node instanceof NullLiteral);
    }

    @Test
    public void testUndefinedLiteral() {
        String input = "undefined";

        initParser(input);
        ParseTree tree = parser.literal();
        Node node = visitor.visit(tree);

        assertTrue(node instanceof UndefinedLiteral);
    }

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
            ParseTree tree = parser.literal();
            Node node = visitor.visit(tree);

            assertTrue(node instanceof StringLiteral);
            StringLiteral literal = (StringLiteral) node;

            assertEquals(s, literal.getValue());
        }
    }

    @Test
    public void testIdentifier() {
        String[] ids = {
                "_i", "i", "j_1", "$i"
        };

        for (String id : ids) {
            initParser(id);
            ParseTree tree = parser.primaryExpression();
            Node node = visitor.visit(tree);

            assertTrue(node instanceof Identifier);
            Identifier identifier = (Identifier) node;

            assertEquals(id, identifier.getIdentifier());
        }
    }

    @Test
    public void testArrayLiteral() {
        String literals[] = {"[]", "[2]", "['hello', 'world', 3 + 7]"};
        int[] expectedLength = {0, 1, 3};

        for (int i = 0; i < literals.length; i++) {
            String s = literals[i];
            initParser(s);
            ParseTree tree = parser.arrayLiteral();
            Node node = visitor.visit(tree);

            assertTrue(node instanceof ArrayLiteral);
            ArrayLiteral arrayLiteral = (ArrayLiteral) node;

            assertEquals(expectedLength[i], arrayLiteral.getSubExpressionCount());
        }
    }

    @Test
    public void testParenthesizedExpression() {
        String expressions[] = {"(3)", "(index)", "(null)", "(\"hello\")"};
        Class subExpressionClasses[] = {NumberLiteral.class, Identifier.class, NullLiteral.class, StringLiteral.class};

        for (int i = 0; i < expressions.length; i++) {
            initParser(expressions[i]);
            ParseTree tree = parser.parenthesizedExpression();
            Node node = visitor.visit(tree);

            assertTrue(node instanceof ParenthesizedExpression);
            Expression subExpr = ((ParenthesizedExpression) node).getExpression();

            assertEquals(subExpressionClasses[i], subExpr.getClass());
        }

    }
}
