package org.minijs.core.parser;

import edu.emory.mathcs.backport.java.util.Collections;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.minijs.core.ast.*;
import org.minijs.core.util.Preconditions;
import org.minijs.parser.antlr.JavaScriptBaseVisitor;
import org.minijs.parser.antlr.JavaScriptLexer;
import org.minijs.parser.antlr.JavaScriptParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AstConstructVisitor extends JavaScriptBaseVisitor <Node> {
    private static final NullLiteral NULL_LITERAL = new NullLiteral();
    private static final UndefinedLiteral UNDEFINED_LITERAL = new UndefinedLiteral();
    private static final Map<String, Operator> sOperatorMap = Collections.unmodifiableMap(
            new HashMap<String, Operator>() {{
                put("*", Operator.MUL);
                put("/", Operator.DIV);
                put("%", Operator.MOD);
                put("+", Operator.PLUS);
                put("-", Operator.MINUS);

                put("<", Operator.LT);
                put("<=", Operator.LE);
                put(">", Operator.GT);
                put(">=", Operator.GE);
                put("in", Operator.IN);
                put("instanceof", Operator.INSTANCEOF);
                put("==", Operator.EQ);
                put("!=", Operator.NEQ);
                put("===", Operator.EXACT_EQ);
                put("!==", Operator.EXACT_NEQ);

                put("&&", Operator.AND);
                put("||", Operator.OR);
            }}
    );

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
            Node node = visit(ctx.expression(i));
            list.add((Expression)node);
        }

        return new ExpressionList(list);
    }

    @Override
    public Node visitMulExpression(@NotNull JavaScriptParser.MulExpressionContext ctx) {
        String str = ctx.getChild(1).getText();
        Operator op = sOperatorMap.get(str);
        Preconditions.checkNotNull(op);
        return new BinaryExpression(
                op,
                (Expression)visit(ctx.expression(0)),
                (Expression)visit(ctx.expression(1)));
    }

    @Override
    public Node visitPlusExpression(@NotNull JavaScriptParser.PlusExpressionContext ctx) {
        String str = ctx.getChild(1).getText();
        Operator op = sOperatorMap.get(str);
        Preconditions.checkNotNull(op);
        return new BinaryExpression(
                op,
                (Expression)visit(ctx.expression(0)),
                (Expression)visit(ctx.expression(1)));
    }

    @Override
    public Node visitRelationalExpression(@NotNull JavaScriptParser.RelationalExpressionContext ctx) {
        String str = ctx.getChild(1).getText();
        Operator op = sOperatorMap.get(str);
        Preconditions.checkNotNull(op);
        return new BinaryExpression(
                op,
                (Expression)visit(ctx.expression(0)),
                (Expression)visit(ctx.expression(1)));
    }

    @Override
    public Node visitLoginEqualityExpression(@NotNull JavaScriptParser.LoginEqualityExpressionContext ctx) {
        String str = ctx.getChild(1).getText();
        Operator op = sOperatorMap.get(str);
        Preconditions.checkNotNull(op);
        return new BinaryExpression(
                op,
                (Expression)visit(ctx.expression(0)),
                (Expression)visit(ctx.expression(1)));
    }

    @Override
    public Node visitLoginAndExpression(@NotNull JavaScriptParser.LoginAndExpressionContext ctx) {
        String str = ctx.getChild(1).getText();
        Operator op = sOperatorMap.get(str);
        Preconditions.checkNotNull(op);
        return new BinaryExpression(
                op,
                (Expression)visit(ctx.expression(0)),
                (Expression)visit(ctx.expression(1)));
    }

    @Override
    public Node visitLogicOrExpression(@NotNull JavaScriptParser.LogicOrExpressionContext ctx) {
        String str = ctx.getChild(1).getText();
        Operator op = sOperatorMap.get(str);
        Preconditions.checkNotNull(op);
        return new BinaryExpression(
                op,
                (Expression)visit(ctx.expression(0)),
                (Expression)visit(ctx.expression(1)));
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

    @Override
    public Node visitUnaryExpression(@NotNull JavaScriptParser.UnaryExpressionContext ctx) {
        Token token = ctx.getStart();
        Expression subExpr = (Expression) visit(ctx.expression());
        switch (token.getType()) {
            case JavaScriptLexer.PLUS:
                return new UnaryExpression(Operator.PLUS, subExpr);

            case JavaScriptLexer.MINUS:
                return new UnaryExpression(Operator.MINUS, subExpr);

            case JavaScriptLexer.NOT:
                return new UnaryExpression(Operator.NOT, subExpr);

            case JavaScriptLexer.INC:
                return new UnaryExpression(Operator.INC, subExpr);

            case JavaScriptLexer.DEC:
                return new UnaryExpression(Operator.DEC, subExpr);

        }
        return super.visitUnaryExpression(ctx);
    }

    @Override
    public Node visitConditionalExpression(@NotNull JavaScriptParser.ConditionalExpressionContext ctx) {
        return new ConditionalExpression(
                (Expression) visit(ctx.expression(0)),
                (Expression) visit(ctx.expression(1)),
                (Expression) visit(ctx.expression(2))
        );
    }
}
