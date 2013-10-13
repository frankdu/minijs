package org.minijs.core.parser;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.minijs.core.ast.*;
import org.minijs.core.util.Preconditions;
import org.minijs.parser.antlr.JavaScriptBaseVisitor;
import org.minijs.parser.antlr.JavaScriptLexer;
import org.minijs.parser.antlr.JavaScriptParser;

import java.util.*;

public class AstConstructVisitor extends JavaScriptBaseVisitor <Node> {

    private static final NoopStatement NOOP_STATEMENT = new NoopStatement();
    private static final BreakStatement BREAK_STATEMENT = new BreakStatement();
    private static final ContinueStatement CONTINUE_STATEMENT = new ContinueStatement();
    private static final ReturnStatement RETURN_VOID_STATEMENT = new ReturnStatement(null);

    private static final ThisLiteral THIS_LITERAL = new ThisLiteral();

    private static final NullLiteral NULL_LITERAL = new NullLiteral();
    private static final UndefinedLiteral UNDEFINED_LITERAL = new UndefinedLiteral();
    private static final ExpressionList EMPTY_EXPRESSION_LIST = new ExpressionList(new ArrayList<Expression>());

    private static final Map<String, Operator> sOperatorMap;

    static {
        Map<String, Operator> map = new HashMap<String, Operator>();
        for (Operator op : Operator.values()) {
            map.put(op.toString(), op);
        }
        sOperatorMap = Collections.unmodifiableMap(map);
    }

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
    public Node visitBitwiseShiftExpression(@NotNull JavaScriptParser.BitwiseShiftExpressionContext ctx) {
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
    public Node visitLogicEqualityExpression(@NotNull JavaScriptParser.LogicEqualityExpressionContext ctx) {
        String str = ctx.getChild(1).getText();
        Operator op = sOperatorMap.get(str);
        Preconditions.checkNotNull(op);
        return new BinaryExpression(
                op,
                (Expression)visit(ctx.expression(0)),
                (Expression)visit(ctx.expression(1)));
    }

    @Override
    public Node visitBitwiseLogicExpression(@NotNull JavaScriptParser.BitwiseLogicExpressionContext ctx) {
        String str = ctx.getChild(1).getText();
        Operator op = sOperatorMap.get(str);
        Preconditions.checkNotNull(op);
        return new BinaryExpression(
                op,
                (Expression)visit(ctx.expression(0)),
                (Expression)visit(ctx.expression(1)));
    }

    @Override
    public Node visitLogicAndExpression(@NotNull JavaScriptParser.LogicAndExpressionContext ctx) {
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
            case JavaScriptLexer.THIS:
                return THIS_LITERAL;

            case JavaScriptLexer.IDENTIFIER:
                return new Identifier(text);

            default:
                return super.visitPrimaryExpression(ctx);
        }
    }

    @Override
    public Node visitParenthesizedExpression(@NotNull JavaScriptParser.ParenthesizedExpressionContext ctx) {
        return new ParenthesizedExpression(
                (Expression) visit(ctx.expression())
        );
    }

    @Override
    public Node visitUnaryExpression(@NotNull JavaScriptParser.UnaryExpressionContext ctx) {
        Token token = ctx.getStart();
        Expression subExpr = (Expression) visit(ctx.expression());
        switch (token.getType()) {
            case JavaScriptLexer.INC:
                return new IncDecUpdateExpression(
                        IncDecUpdateExpression.UpdateTiming.PRE,
                        Operator.INC,
                        subExpr
                );

            case JavaScriptLexer.DEC:
                return new IncDecUpdateExpression(
                        IncDecUpdateExpression.UpdateTiming.PRE,
                        Operator.DEC,
                        subExpr
                );

            case JavaScriptLexer.NOT:
            case JavaScriptLexer.BITWISE_NOT:
            case JavaScriptLexer.PLUS:
            case JavaScriptLexer.MINUS:
            case JavaScriptLexer.TYPEOF:
            case JavaScriptLexer.VOID:
            case JavaScriptLexer.DELETE:
                Operator op = sOperatorMap.get(token.getText());
                Preconditions.checkNotNull(op);
                return new UnaryExpression(op, subExpr);

            default:
                return super.visitUnaryExpression(ctx);
        }
    }

    @Override
    public Node visitConditionalExpression(@NotNull JavaScriptParser.ConditionalExpressionContext ctx) {
        return new ConditionalExpression(
                (Expression) visit(ctx.expression(0)),
                (Expression) visit(ctx.expression(1)),
                (Expression) visit(ctx.expression(2))
        );
    }

    @Override
    public Node visitPostUpdateExpression(@NotNull JavaScriptParser.PostUpdateExpressionContext ctx) {
        String opStr = ctx.getChild(1).getText();
        Operator op = sOperatorMap.get(opStr);
        Preconditions.checkNotNull(op);
        return new IncDecUpdateExpression(
                IncDecUpdateExpression.UpdateTiming.POST,
                op,
                (Expression) visit(ctx.expression())
        );
    }

    @Override
    public Node visitFunctionCallExpression(@NotNull JavaScriptParser.FunctionCallExpressionContext ctx) {
        Expression functionObject = (Expression) visit(ctx.expression());

        ExpressionList parameters;
        if (ctx.getChildCount() == 3) {
            parameters = EMPTY_EXPRESSION_LIST;
        } else {
            parameters = (ExpressionList) visit(ctx.expressionList());
        }
        return new FunctionCallExpression(functionObject, parameters);
    }

    @Override
    public Node visitPropertyExpression(@NotNull JavaScriptParser.PropertyExpressionContext ctx) {
        Expression targetExpression = (Expression) visit(ctx.expression());
        String propertyName = ctx.IDENTIFIER().getText();
        return new PropertyExpression(targetExpression, propertyName);
    }

    @Override
    public Node visitIndexorExpression(@NotNull JavaScriptParser.IndexorExpressionContext ctx) {
        Expression targetExpression = (Expression) visit(ctx.expression(0));
        Expression indexExpression = (Expression) visit(ctx.expression(1));
        return new IndexorExpression(targetExpression, indexExpression);
    }

    @Override
    public Node visitNewExpression(@NotNull JavaScriptParser.NewExpressionContext ctx) {
        String className = ctx.IDENTIFIER().getText();
        JavaScriptParser.ExpressionListContext expressionListContext = ctx.expressionList();
        ExpressionList parameters;
        if (expressionListContext == null) {
            parameters = EMPTY_EXPRESSION_LIST;
        } else {
            parameters = (ExpressionList) visit(expressionListContext);
        }

        return new NewExpression(className, parameters);
    }

    @Override
    public Node visitAssignmentExpression(@NotNull JavaScriptParser.AssignmentExpressionContext ctx) {
        String opStr = ctx.getChild(1).getText();
        Operator op = sOperatorMap.get(opStr);
        Preconditions.checkNotNull(op);
        return new AssignmentExpression(
                op,
                (Expression) visit(ctx.expression(0)),
                (Expression) visit(ctx.expression(1))
        );
    }

    @Override
    public Node visitBreakStatement(@NotNull JavaScriptParser.BreakStatementContext ctx) {
        TerminalNode terminalNode = ctx.IDENTIFIER();
        if (terminalNode != null) {
            return new BreakStatement(terminalNode.getText());
        }

        return BREAK_STATEMENT;
    }

    @Override
    public Node visitContinueStatement(@NotNull JavaScriptParser.ContinueStatementContext ctx) {
        TerminalNode terminalNode = ctx.IDENTIFIER();
        if (terminalNode != null) {
            return new ContinueStatement(terminalNode.getText());
        }

        return CONTINUE_STATEMENT;
    }

    @Override
    public Node visitNoopStatement(@NotNull JavaScriptParser.NoopStatementContext ctx) {
        return NOOP_STATEMENT;
    }

    @Override
    public Node visitReturnStatement(@NotNull JavaScriptParser.ReturnStatementContext ctx) {
        int count = ctx.getChildCount();
        if (ctx.getStop().getType() == JavaScriptLexer.SEMI) {
            count -= 1;
        }
        if (count < 2) {
            return RETURN_VOID_STATEMENT;
        } else {
            return new ReturnStatement((Expression) visit(ctx.expression()));
        }
    }

    @Override
    public Node visitBlockStatement(@NotNull JavaScriptParser.BlockStatementContext ctx) {
        List<Statement> subStatementList = new ArrayList<Statement>();

        int statementCount = ctx.getChildCount() - 2;
        for (int i = 0; i < statementCount; i++) {
            Statement subStatement = (Statement) visit(ctx.statement(i));
            subStatementList.add(subStatement);
        }

        return new BlockStatement(subStatementList);
    }

    @Override
    public Node visitProgram(@NotNull JavaScriptParser.ProgramContext ctx) {
        List<Statement> subStatementList = new ArrayList<Statement>();
        int statementCount = ctx.getChildCount();
        for (int i = 0; i < statementCount; i++) {
            subStatementList.add((Statement) visit(ctx.statement(i)));
        }
        return new BlockStatement(subStatementList);
    }

    @Override
    public Node visitExpressionStatement(@NotNull JavaScriptParser.ExpressionStatementContext ctx) {
        Expression expr = (Expression) visit(ctx.expression());
        return new ExpressionStatement(expr);
    }

    @Override
    public Node visitIfStatement(@NotNull JavaScriptParser.IfStatementContext ctx) {
        Expression conditionExpression = (Expression) visit(ctx.expression());

        Statement thenStatement = (Statement) visit(ctx.statement(0));
        Statement elseStatement = null;
        if (ctx.getChildCount() == 7) {
            elseStatement = (Statement) visit(ctx.statement(1));
        }

        return new IfStatement(conditionExpression, thenStatement, elseStatement);
    }

    @Override
    public Node visitDoWhileStatement(@NotNull JavaScriptParser.DoWhileStatementContext ctx) {
        return new DoWhileStatement(
                (Expression) visit(ctx.expression()),
                (Statement) visit(ctx.statement())
        );
    }

    @Override
    public Node visitWhileStatement(@NotNull JavaScriptParser.WhileStatementContext ctx) {
        return new WhileStatement(
                (Expression) visit(ctx.expression()),
                (Statement) visit(ctx.statement())
        );
    }

    @Override
    public Node visitVariableDeclareStatement(@NotNull JavaScriptParser.VariableDeclareStatementContext ctx) {
        return new VariableDeclaratorsStatement((VariableDeclarators) visit(ctx.variableDeclarators()));
    }

    @Override
    public Node visitForStatement(@NotNull JavaScriptParser.ForStatementContext ctx) {
        return new ForStatement(
                (ForControl) visit(ctx.forControl()),
                (Statement) visit(ctx.statement())
        );
    }

    @Override
    public Node visitForInStatement(@NotNull JavaScriptParser.ForInStatementContext ctx) {
        return new ForInStatement(
                ctx.Identifier().getText(),
                (Expression) visit(ctx.expression()),
                (Statement) visit(ctx.statement())
        );
    }

    @Override
    public Node visitForControl(@NotNull JavaScriptParser.ForControlContext ctx) {
        VariableDeclarators variableDeclarators = null;
        Expression conditionExpression = null;
        ExpressionList updateExpressionList = null;

        JavaScriptParser.VariableDeclaratorsContext varDeclaratorsContext = ctx.variableDeclarators();
        if (varDeclaratorsContext!= null) {
            variableDeclarators = (VariableDeclarators) visit(varDeclaratorsContext);
        }

        JavaScriptParser.ExpressionContext expressionContext = ctx.expression();
        if (expressionContext != null) {
            conditionExpression = (Expression) visit(expressionContext);
        }

        JavaScriptParser.ExpressionListContext expressionListContext = ctx.expressionList();
        if (expressionListContext != null) {
            updateExpressionList = (ExpressionList) visit(expressionListContext);
        }

        return new ForControl(
                variableDeclarators,
                conditionExpression,
                updateExpressionList
        );
    }

    @Override
    public Node visitVariableDeclarator(@NotNull JavaScriptParser.VariableDeclaratorContext ctx) {
        Identifier variableName = new Identifier(ctx.IDENTIFIER().getText());

        Expression initializer = null;
        if (ctx.getChildCount() == 3) {
            initializer = (Expression) visit(ctx.expression());
        }

        return new VariableDeclarator(variableName, initializer);
    }

    @Override
    public Node visitVariableDeclarators(@NotNull JavaScriptParser.VariableDeclaratorsContext ctx) {
        int varDeclaratorCount = ctx.getChildCount() / 2;

        List<VariableDeclarator> list = new ArrayList<VariableDeclarator>();
        for (int i = 0; i < varDeclaratorCount; i++) {
            list.add((VariableDeclarator) visit(ctx.variableDeclarator(i)));
        }

        return new VariableDeclarators(list);
    }

    @Override
    public Node visitLabelledStatement(@NotNull JavaScriptParser.LabelledStatementContext ctx) {
        return new LabelledStatement(
                ctx.IDENTIFIER().getText(),
                (Statement) visit(ctx.statement())
        );
    }

    @Override
    public Node visitFormalParameterList(@NotNull JavaScriptParser.FormalParameterListContext ctx) {
        List<Identifier> params = new ArrayList<Identifier>();
        int count = (ctx.getChildCount() + 1) / 2;
        for (int i = 0; i < count; i++) {
            params.add((Identifier) visit(ctx.IDENTIFIER(i)));
        }

        return new FormalParameterList(params);
    }

    @Override
    public Node visitFunctionDeclaration(@NotNull JavaScriptParser.FunctionDeclarationContext ctx) {
        FormalParameterList parameterList = null;
        JavaScriptParser.FormalParameterListContext formalParameterListContext = ctx.formalParameterList();
        if (formalParameterListContext != null) {
            parameterList = (FormalParameterList) visit(formalParameterListContext);
        }

        return new FunctionDeclaration(
                ctx.IDENTIFIER().getText(),
                parameterList,
                (BlockStatement) visit(ctx.blockStatement()));
    }
}
