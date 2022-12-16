package Postfix;

import java.util.NoSuchElementException;

public class Tokenizer {
    private char[] tokenStr;
    private int pos;

    public Tokenizer(String s){
        tokenStr = s.toCharArray();
    }

    public boolean hasMoreTokens(){
        skipSpaces();

        return pos < tokenStr.length;
    }

    public Token nextToken() throws InvalidExpressionException{
        skipSpaces();

        if(pos >= tokenStr.length){
            throw new NoSuchElementException("No more tokens");
        }

        if(Character.isDigit(tokenStr[pos])){
            return readNumberToken();
        }
        else if(tokenStr[pos] == '('){
            pos++;
            return new LeftParenToken();
        } 
        else if(tokenStr[pos] == ')'){
            pos++;
            return new RightParenToken();
        }
        else {
            return readOperatorToken();
        }
    }

    private OpToken readOperatorToken() throws InvalidExpressionException{
        char c = tokenStr[pos];
        pos++;

        switch (c){
            case '+':
                return new OpAddToken();
            case '-':
                return new OpSubToken();
            case '*':
                return new OpMultToken();
            case '/':
                return new OpDivToken();
            default:
                throw new InvalidExpressionException("Found " +c+" expecting an operator character at "+pos+".");
        }
           
    }

    private NumberToken readNumberToken(){
        int val = 0;

        while(pos < tokenStr.length && Character.isDigit(tokenStr[pos])){
            val = val * 10 + (tokenStr[pos] - '0');
            pos++;
        }

        return new NumberToken(val);
    }

    private void skipSpaces(){ // if first statement is false second half wont run
        while(pos < tokenStr.length && Character.isSpaceChar(tokenStr[pos])){ // tokenStr[pos] == '' spacechar checks space character no matter what charcode (works for other countries)
            pos++;
        }
    }
}
