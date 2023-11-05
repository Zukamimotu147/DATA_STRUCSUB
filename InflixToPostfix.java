package DATASTRUCsubject;

import java.util.*;

public class InflixToPostfix {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ConvertPostfix convertPostfix = new ConvertPostfix();
        System.out.print("Enter infix: ");
        String expression = scan.nextLine();

        String postfix = convertPostfix.toPostfix(expression);
        System.out.println("Postfix: " + postfix);

        double result = convertPostfix.evaluatePostfix(expression);
        System.out.println("Result: " + result);

        scan.close();
    }
}

class ConvertPostfix {

    public String toPostfix(String exp) {
        Stack<Character> operators = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (ch == ' ') {
                continue;
            } else if (Character.isDigit(ch)) {
                postfix.append(ch);
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    postfix.append(operators.pop());
                }
                if (!operators.isEmpty() && operators.peek() == '(') {
                    operators.pop();
                }
            } else if (isOperator(ch)) {
                while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                    postfix.append(operators.pop());
                }
                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            if (operators.peek() == '(') {
                operators.pop();
            } else {
                postfix.append(operators.pop());
            }
        }

        return postfix.toString();
    }

    public double evaluatePostfix(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == ' ') {
                continue;
            } else if (Character.isDigit(ch)) {
                double num = ch - '0';
                while (i + 1 < expression.length() && Character.isDigit(expression.charAt(i + 1))) {
                    num = num * 10 + (expression.charAt(i + 1) - '0');
                    i++;
                }
                values.push(num);
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    char operator = operators.pop();
                    double operand2 = values.pop();
                    double operand1 = values.pop();
                    values.push(performOperator(operand1, operand2, operator));
                }
                operators.pop(); // Pop the '('
            } else if (isOperator(ch)) {
                while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                    char operator = operators.pop();
                    double operand2 = values.pop();
                    double operand1 = values.pop();
                    values.push(performOperator(operand1, operand2, operator));
                }
                operators.push(ch);
            }  
        }

        while (!operators.isEmpty()) {
            char operator = operators.pop();
            double operand2 = values.pop();
            double operand1 = values.pop();
            values.push(performOperator(operand1, operand2, operator));
        }

        return values.pop();
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    private int precedence(char operator) {
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

    private double performOperator(double operand1, double operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            case '^':
                return Math.pow(operand1, operand2);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
