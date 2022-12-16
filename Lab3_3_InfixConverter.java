package Postfix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Lab3_3_InfixConverter {


	/* Translates the provided String "s" from infix notation to
		postfix notation.  Returns the postfix formatted string.

		This method assumes that "s" is a properly written infix expression.  It
		is not expected to detect all syntax errors in "s".  However, it may
		throw an exception for some improperly formatted inputs.
	 */
	public static String infixToPostfix(String s) throws Exception {
		Stack<Token> opStack = new Stack<>();
		String output = "";
		Tokenizer tokenizer = new Tokenizer(s);
		Token next;

		while(tokenizer.hasMoreTokens()){
			next = tokenizer.nextToken();
			if(next instanceof NumberToken){
				NumberToken num = (NumberToken) next;
				output += (int) num.value;
				output += " ";
			}
			else if(next instanceof LeftParenToken){
				LeftParenToken left = (LeftParenToken) next;
				opStack.push(left);
			}
			else if(next instanceof RightParenToken){
				while(!(opStack.peek() instanceof LeftParenToken)){
					output += opStack.pop();
					output += " ";
				}
				opStack.pop();
			}
			else if(next instanceof OpToken){
				OpToken op = (OpToken) next;

				while(opStack.size() != 0 && !(opStack.peek() instanceof ParenToken) &&((OpToken) opStack.peek()).getPrecedence() >= op.getPrecedence()){
					output += opStack.pop();
					output += " ";
				}
			
				opStack.push(op);
			}
		}
		while(opStack.size() > 0){
			Token pop = opStack.pop();
			if(!(pop instanceof ParenToken)){
				output += pop;
				output += " ";
			}
		}

		return output;
	}

	/**
	 * Main opens the file 'infix.in'.  It then reads
	 * through the file one line at a time.  For each line, it prints the original
	 * line from the input, then the postfix equivalent, and then prints the
	 * simplified answer.
	 *
	 * If an exception occurs when evaluating a specific line, the exception is
	 * printed and then execution continues with the next line.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("Postfix/infix.in"));

		while (in.hasNextLine()) {
			String line = in.nextLine();
			System.out.println("\n" + line);

			try {
				String postfix = infixToPostfix(line);
				System.out.println("\t Postfix: " + postfix);

				double answer = Lab3_2_PostfixCalculator.calculatePostfix(postfix);
				System.out.println("\t Answer: " + answer);
			}
			catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}

