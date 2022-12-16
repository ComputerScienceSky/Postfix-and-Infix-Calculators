package Postfix;

public class OpMultToken extends OpToken{
    public OpMultToken(){
        super('*');
    }

    @Override
    public double eval (double a, double b){
        return a * b;
    }

    public double getPrecedence(){
        return 2;
    }
}