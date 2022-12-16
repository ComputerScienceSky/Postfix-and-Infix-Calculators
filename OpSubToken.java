package Postfix;

public class OpSubToken extends OpToken{
    public OpSubToken(){
        super('-');
    }

    @Override
    public double eval (double a, double b){
        return a - b;
    }

    public double getPrecedence(){
        return 1;
    }
}
