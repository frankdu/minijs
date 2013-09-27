package org.minijs.core.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.minijs.parser.antlr.JavaScriptLexer;
import org.minijs.parser.antlr.JavaScriptParser;

public class BaseParserTest {

    protected JavaScriptParser parser;
    protected AstConstructVisitor visitor;

    @Before
    public void setup() {
        parser = new JavaScriptParser(null);
        visitor = new AstConstructVisitor();
    }

    protected void initParser(String s) {
        JavaScriptLexer lexer = new JavaScriptLexer(new ANTLRInputStream(s));
        parser.setTokenStream(new CommonTokenStream(lexer));
    }

}
