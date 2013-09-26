package org.minijs.core;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.minijs.core.ast.Node;
import org.minijs.core.ast.Operator;
import org.minijs.core.parser.AstConstructVisitor;
import org.minijs.parser.antlr.JavaScriptLexer;
import org.minijs.parser.antlr.JavaScriptParser;

public class TestApp
{
    /**
     * Keep this class for my test convenience
     * @param args
     */
    public static void main( String[] args )
    {
        String input = "1 + 2, 1 + 2 * 3, 1 * 2 + 3 % 4";

        JavaScriptLexer lexer = new JavaScriptLexer(new ANTLRInputStream(input));
        JavaScriptParser parser = new JavaScriptParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.expressionList();

        AstConstructVisitor visitor = new AstConstructVisitor();
        Node node = visitor.visit(tree);
        System.out.println(node);

        System.out.println(Operator.DIV);
        System.out.println(Operator.DIV.name());
        System.out.println(Operator.values());
        System.out.println(Operator.valueOf("PLUS").name());

        System.out.println( "Hello test!" );
    }
}
