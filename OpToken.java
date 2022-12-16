package Postfix;

public abstract class OpToken extends Token{
    public final char op;

    public OpToken (char op){
        this.op = op;
    }

    public abstract double eval (double a, double b);

    public abstract double getPrecedence();

    public String toString() {
        return "" + op;
    }
}

