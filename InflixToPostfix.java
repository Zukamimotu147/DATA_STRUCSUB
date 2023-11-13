import java.util.*;

public class InflixToPostfix { 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter infix expression: ");
        String infixExpression = scanner.nextLine();
        try {
        String postfixExpression = convertToPostfix(infixExpression);
        System.out.println("Postfix expression: " + postfixExpression);
        
       
        String result = evaluatePostfix(postfixExpression);
        System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("The expression cannot be evaluated");
        }
        
        scanner.close();
    }
    
    private static String convertToPostfix(String infix) {
        Stack<Character> operators = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        StringBuilder currentNumber = new StringBuilder();

        for (char ch : infix.toCharArray()) {
            if (ch == ' ') {
                continue;
            } else if (Character.isLetterOrDigit(ch)) {
                currentNumber.append(ch);
            } else {
                if (currentNumber.length() > 0) {
                    postfix.append(currentNumber).append(' ');
                    currentNumber.setLength(0); 
                }

                if (ch == '(') {
                    operators.push(ch);
                } else if (ch == ')') {
                    while (!operators.isEmpty() && operators.peek() != '(') {
                        postfix.append(operators.pop()).append(' ');
                    }
                    if (!operators.isEmpty() && operators.peek() == '(') {
                        operators.pop();
                    }
                } else if (isOperator(ch)) {
                    while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                        postfix.append(operators.pop()).append(' ');
                    }
                    operators.push(ch);
                }
            }
        }

        if (currentNumber.length() > 0) {
            postfix.append(currentNumber).append(' ');
        }

        while (!operators.isEmpty()) {
            postfix.append(operators.pop()).append(' ');
        }

        return postfix.toString().trim();
    }

    private static String evaluatePostfix(String postfix) {
       
        Stack<String> values = new Stack<>();
        String[] exp = postfix.split(" ");
        int count = 0;
        System.out.println(postfix);
        for (String token : exp) { 
            //System.out.println(token);
            
            if (isNumeric(token)) {
                values.push(token);
                count += 2;
                
            } else {
                count += 2;
                values.push(token);
                //System.out.println(values);
                String operator = values.pop();
                String num2 = values.pop();
                String num1 = values.pop();
                String result = performOperator(num1, num2, operator);
                values.push(result);
                System.out.printf("%s%s\n", result, postfix.substring(count, postfix.length()));
                //System.out.println(count);
            }
            
        }
   
        return values.pop();
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    private static String performOperator(String operand1, String operand2, String operator) {

        double num1 = Double.parseDouble(operand1);
        double num2 = Double.parseDouble(operand2);

        switch (operator) {
            case "+":
                return Double.toString(num1 + num2);
            case "-":
                return Double.toString(num1 + num2);
            case "*":
                return Double.toString(num1 * num2);
            case "/":
                return Double.toString(num1 / num2);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
