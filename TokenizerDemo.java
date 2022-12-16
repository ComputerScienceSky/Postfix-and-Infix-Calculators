package Postfix;

import java.util.Scanner;

public class TokenizerDemo {
    public static void main(String[] args) throws InvalidExpressionException{
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a line");
        String a = in.nextLine();
        double sum = 0;

        Tokenizer tk = new Tokenizer(a);
        while(tk.hasMoreTokens()){
            Token t = tk.nextToken();
            System.out.println(t);

            if(t instanceof NumberToken){
                NumberToken nt = (NumberToken) t;
                sum += nt.value;
                System.out.println("Sum: " + sum);
            }
            else if(t instanceof ParenToken){
                ParenToken pt = (ParenToken) t;
                System.out.println("paren token found");
            }
            else {
                OpToken ot = (OpToken) t;
                System.out.println(ot.eval(15, 5));
            }
            

        }
    }
}
