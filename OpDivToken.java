package Postfix;

public class OpDivToken extends OpToken{
    public OpDivToken(){
        super('/');
    }

    @Override
    public double eval (double a, double b){
        return a / b;
    }

    @Override
    public double getPrecedence() {
        return 2;
    }
}
