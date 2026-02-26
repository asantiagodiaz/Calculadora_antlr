import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends LabeledExprBaseVisitor<Integer> {
    // Memoria para guardar las variables (ej: a = 5)
    Map<String, Integer> memory = new HashMap<String, Integer>();

    // stat: ID '=' expr NEWLINE
    @Override
    public Integer visitAssign(LabeledExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();     // Obtener nombre variable
        int value = visit(ctx.expr());      // Calcular valor expresión derecha
        memory.put(id, value);              // Guardar en memoria
        return value;
    }

    // stat: expr NEWLINE
    @Override
    public Integer visitPrintExpr(LabeledExprParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr());  // Evaluar expresión
        System.out.println(value);          // Imprimir resultado
        return 0;                           // Retorno dummy
    }

    // expr: INT
    @Override
    public Integer visitInt(LabeledExprParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    // expr: ID
    @Override
    public Integer visitId(LabeledExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) {
            return memory.get(id);
        }
        return 0; // Si no existe, retorna 0
    }

    // expr: expr op=('*'|'/') expr
    @Override
    public Integer visitMulDiv(LabeledExprParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));      // Evaluar hijo izquierdo
        int right = visit(ctx.expr(1));     // Evaluar hijo derecho
        if (ctx.op.getType() == LabeledExprParser.MUL) {
            return left * right;
        }
        return left / right;
    }

    // expr: expr op=('+'|'-') expr
    @Override
    public Integer visitAddSub(LabeledExprParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));      // Evaluar hijo izquierdo
        int right = visit(ctx.expr(1));     // Evaluar hijo derecho
        if (ctx.op.getType() == LabeledExprParser.ADD) {
            return left + right;
        }
        return left - right;
    }

    // expr: '(' expr ')'
    @Override
    public Integer visitParens(LabeledExprParser.ParensContext ctx) {
        return visit(ctx.expr());           // Retornar valor de la expresión interior
    }
}
