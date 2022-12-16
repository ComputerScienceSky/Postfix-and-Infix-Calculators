package Postfix;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;


public class Lab3_2_PostfixCalculator {

    /**
     * calculatePostfix calculates and returns the value for the postfix
     * expression "postfixExp".  This method throws exceptions in the following
     * cases:
     * 1) If there are illegal characters in the input.
     * 2) If there are not enough values for a given operator.
     * 3) If there is more than 1 value remaining on the stack after the calculation completes.
     */
    public static double calculatePostfix(String postfixExp) throws InvalidExpressionException{
        Stack<NumberToken> tokenStack = new Stack<>();
        Tokenizer tokenizer = new Tokenizer(postfixExp);
        Token next;
        double num1;
        double num2;
        OpToken op;

        while(tokenizer.hasMoreTokens()){
            next = tokenizer.nextToken();
            if(next instanceof NumberToken){
                tokenStack.push((NumberToken) next);
            } else if (next instanceof OpToken){
                op = (OpToken) next;
                if(tokenStack.size() < 2){
                    throw new InvalidExpressionException("Too few values on stack for operator " + next);
                }
                num1 = tokenStack.pop().value;
                num2 = tokenStack.pop().value;
                tokenStack.push(new NumberToken(op.eval(num2, num1)));
            } 
        }
        if(tokenStack.size() == 1){
            return tokenStack.pop().value;
        }

        throw new InvalidExpressionException(" Too few operators in the expression. Stack has " + tokenStack.size() + " elements at the end of the expression");
    }

    /**
     * Main opens the file specified postfix.in.  It then reads
     * through the file one line at a time.  For each line, it prints the original
     * line from the input and then prints the result.  If an exception occurs when
     * evaluating a specific link, print the message for the exception (using getMessage)
     * and then continue to the next line.
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("Postfix/postfix.in"));

        while (in.hasNextLine()) {
            String line = in.nextLine();
            System.out.println("\n" + line);

            try {
                double answer = calculatePostfix(line);
                System.out.println(answer);
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
