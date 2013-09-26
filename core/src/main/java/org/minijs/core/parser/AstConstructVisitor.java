package org.minijs.core.parser;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.minijs.core.ast.*;
import org.minijs.parser.antlr.JavaScriptBaseVisitor;
import org.minijs.parser.antlr.JavaScriptLexer;
import org.minijs.parser.antlr.JavaScriptParser;

public class AstConstructVisitor extends JavaScriptBaseVisitor <Node> {
    private static final NullLiteral NULL_LITERAL = new NullLiteral();
    private static final UndefinedLiteral UNDEFINED_LITERAL = new UndefinedLiteral();

    @Override
    public Node visitLiteral(@NotNull JavaScriptParser.LiteralContext ctx) {
        Token token = ctx.getStart();
        String text = token.getText();

        switch (token.getType()) {
            case JavaScriptLexer.BOOLEAN_LITERAL:
                return new BooleanLiteral("true".equals(text));

            case JavaScriptLexer.NUMBER:
                return new NumberLiteral(Double.parseDouble(text));

            case JavaScriptLexer.NULL_LITERAL:
                return NULL_LITERAL;

            case JavaScriptLexer.UNDEFINED_LITERAL:
                return UNDEFINED_LITERAL;

            case JavaScriptLexer.STRING_LITERAL:
                return new StringLiteral(text.substring(1, text.length() - 1));
        }

        return super.visitLiteral(ctx);
    }
}
