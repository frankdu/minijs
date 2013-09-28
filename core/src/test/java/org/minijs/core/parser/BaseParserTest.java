package org.minijs.core.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.minijs.parser.antlr.JavaScriptLexer;
import org.minijs.parser.antlr.JavaScriptParser;

public class BaseParserTest {

    protected JavaScriptParser mParser;
    protected AstConstructVisitor mVisitor;

    @Before
    public void setup() {
        mParser = new JavaScriptParser(null);
        mVisitor = new AstConstructVisitor();
    }

    protected void initParser(String s) {
        JavaScriptLexer lexer = new JavaScriptLexer(new ANTLRInputStream(s));
        mParser.setTokenStream(new CommonTokenStream(lexer));
    }

}
