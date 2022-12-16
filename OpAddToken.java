package Postfix;

public class OpAddToken extends OpToken{
    public OpAddToken(){
        super('+');
    }

    @Override
    public double eval (double a, double b){
        return a + b;
    }

    public double getPrecedence() {
        return 1;
    }
}
