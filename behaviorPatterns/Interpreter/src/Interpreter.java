package src;

import java.util.ArrayList;

public class Interpreter {
    public static void main(String[] args) {
        // Creamos el árbol de expresiones y el contexto
        ArrayList<Expression> tree = new ArrayList<Expression>();
        Context context = new Context();

        // Añadimos los tokens pasados como argumentos
        for (String token : args) {
            if (isNumeric(token)) {
                tree.add(new NumericExpression(token));
            } else {
                tree.add(new OperationExpression(token));
            }
        }

        // Interpretamos cada expresión
        for (Expression e : tree) {
            e.interpret(context);
        }

        // Mostramos el resultado
        System.out.println("El resultado de la interpretación es " + context.getResult());
    }

    // Comprueba si una cadena es un número entero válido
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
