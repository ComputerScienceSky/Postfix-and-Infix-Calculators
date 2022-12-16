package Postfix;

public class NumberToken extends Token{
    public double value;

    public NumberToken (double val){
        value = val;
    }

    public String toString(){
        return "" + value; // String.format(value);
    }
}
