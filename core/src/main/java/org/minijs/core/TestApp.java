package org.minijs.core;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
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
        String input = "[1, 2]";

        JavaScriptLexer lexer = new JavaScriptLexer(new ANTLRInputStream(input));
        JavaScriptParser parser = new JavaScriptParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.arrayLiteral();

        AstConstructVisitor visitor = new AstConstructVisitor();
        visitor.visit(tree);

        System.out.println( "Hello test!" );
    }
}
