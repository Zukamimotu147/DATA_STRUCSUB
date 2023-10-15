package DATASTRUCsubject;

import java.util.*;

public class InflixToPostfix {
     public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
        
      ConvertPostfix convertPostfix = new ConvertPostfix();
      System.out.print("Enter inflix:");
      String expression = scan.nextLine();

      String result = convertPostfix.toPostfix(expression);
      System.out.println(result);

      scan.close();

     }
}

class ConvertPostfix {

   public static String toPostfix (String exp) {
      Stack <Character> operators = new Stack<>();
      String postfix = "";

      for (int i = 0; i < exp.length(); i++) {
         char ch = exp.charAt(i);

         if (ch == ' ') {
            continue;
         } else if (Character.isLetterOrDigit(ch)) {
            postfix += ch;
         } else if (ch == '(') {
            operators.push(ch);
         } else if (ch == ')') {
            if(!operators.isEmpty() && operators.peek() != '(') {
               postfix += operators.pop();
            }
            operators.pop();
         } else if (isOperator(ch)) {
                while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                    postfix += operators.pop();
                    
                }
                operators.push(ch);
            } 
      }

      return postfix;
   }
    
   private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    private static int precedence(char operator) {
        switch (operator) {
         case '+':
         case '-':
            return 1;
         case '*':
         case '/':
            return 2;
         case '^':
            return 3; 
         default:
            return 0;    
        }
    }
}