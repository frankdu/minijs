package org.minijs.core.parser;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.minijs.core.ast.*;
import org.minijs.parser.antlr.JavaScriptBaseVisitor;
import org.minijs.parser.antlr.JavaScriptLexer;
import org.minijs.parser.antlr.JavaScriptParser;

import java.util.ArrayList;
import java.util.List;

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

            default:
                return super.visitLiteral(ctx);
        }
    }

    @Override
    public Node visitExpressionList(@NotNull JavaScriptParser.ExpressionListContext ctx) {
        int childCount = ctx.getChildCount();
        int exprCount = (childCount + 1) / 2;

        List<Expression> list = new ArrayList<Expression>();
        for (int i = 0; i < exprCount; i++) {
            Node node = visitExpression(ctx.expression(i));
            list.add((Expression)node);
        }

        return new ExpressionList(list);
    }

    @Override
    public Node visitExpression(@NotNull JavaScriptParser.ExpressionContext ctx) {
        return super.visitExpression(ctx);
    }

    @Override
    public Node visitArrayLiteral(@NotNull JavaScriptParser.ArrayLiteralContext ctx) {
        ExpressionList exprList = null;
        if (ctx.getChildCount() > 2) {
            exprList = (ExpressionList) visit(ctx.expressionList());
        }
        return new ArrayLiteral(exprList);
    }

    @Override
    public Node visitPrimaryExpression(@NotNull JavaScriptParser.PrimaryExpressionContext ctx) {
        Token token = ctx.getStart();
        String text = token.getText();

        switch (token.getType()) {
            case JavaScriptLexer.IDENTIFIER:
                return new Identifier(text);

            default:
                return super.visitPrimaryExpression(ctx);
        }
    }

    @Override
    public Node visitParenthesizedExpression(@NotNull JavaScriptParser.ParenthesizedExpressionContext ctx) {
        Node exprNode = visit(ctx.expression());
        return new ParenthesizedExpression((Expression)exprNode);
    }
}
