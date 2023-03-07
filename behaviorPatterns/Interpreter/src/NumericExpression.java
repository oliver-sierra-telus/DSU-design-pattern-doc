package src;

public class NumericExpression implements Expression{
    private String value;

    public NumericExpression(String token){
        this.value = token;
    }

    @Override
    public void interpret(Context context) {
        context.setOperator(context.getInteger(this.value));
        context.calculate();
    }
}
