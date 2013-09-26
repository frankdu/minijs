package org.minijs.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.minijs.parser.antlr.JavaScriptBaseVisitor;
import org.minijs.parser.antlr.JavaScriptLexer;
import org.minijs.parser.antlr.JavaScriptParser;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String input = "var a = 'hello'; var b = ' world'; var c; c = a + b;";

        JavaScriptLexer lexer = new JavaScriptLexer(new ANTLRInputStream(input));
        JavaScriptParser parser = new JavaScriptParser(new CommonTokenStream(lexer));



        ParseTree tree = parser.program();

        JavaScriptBaseVisitor visitor = new JavaScriptBaseVisitor();
        visitor.visit(tree);

        System.out.println( "Hello World!" );
    }
}
