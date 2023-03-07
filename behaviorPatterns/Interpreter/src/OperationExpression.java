package src;

public class OperationExpression implements Expression {

    private String operation;

    public OperationExpression(String token){
        this.operation = token;
    }

    @Override
    public void interpret(Context context) {
        context.setOperation(this.operation);
        context.calculate();
    }
    
}
