package Calculator;

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Calculator.Calculator based on Reverse Polish Notation
 * @author Anton Lisovsky
 */
public class Calculator {

    /**
     * A function for transforming infix into
     * a postfix version and replacing the unary operator(-)
     * @param expression
     * @return transformed expression
     */
    public String toPostfix(String expression){
        String enterExpression = addZeros(expression);
        StringBuilder exitExpression = new StringBuilder();
        Stack<String> stack = new Stack<>();
        StringTokenizer stringTokenizer = new StringTokenizer(enterExpression,"+-*^/()",true);
        while (stringTokenizer.hasMoreTokens()){
            String element=stringTokenizer.nextToken();
            switch (element) {
                case "+":
                case "*":
                case "-":
                case "^":
                case "/":
                    while (!stack.empty() && priority(stack.peek()) >= priority(element))
                        exitExpression.append(stack.pop()).append(" ");

                    stack.push(element);
                    break;
                case "(":
                    stack.push(element);
                    break;
                case ")":

                    while (!stack.peek().equals("(")) exitExpression.append(stack.pop()).append(" ");
                    stack.pop();
                    break;
                default:
                    exitExpression.append(element).append(" ");
                    break;
            }
        }
        while(!stack.empty()) exitExpression.append(stack.pop()).append(" ");
        return exitExpression.toString();
    }

    /**
     * Function for calculating postfix version of expression
     * @param expression
     * @return result of the expression
     * @throws /EmptyStackException, ArithmeticException
     */
    public double calculate(String expression) throws Exception {
        Stack<Double>stack = new Stack<>();
        StringTokenizer stringTokenizer = new StringTokenizer(expression," ");
        while(stringTokenizer.hasMoreTokens()) {
            String element = stringTokenizer.nextToken();
            if (!element.equals("+") && !element.equals("*") && !element.equals("-") && !element.equals("/") && !element.equals("^")) {
                double value = Double.parseDouble(element);
                stack.push(value);
            }
            else {
                double rightValue = stack.pop();
                double leftValue = stack.pop();
                switch(element.charAt(0)) {
                    case '*': {
                        stack.push(leftValue * rightValue);
                        break;
                    }
                    case '+': {
                        stack.push(leftValue + rightValue);
                        break;
                    }
                    case '-': {
                        stack.push(leftValue - rightValue);
                        break;
                    }
                    case '/': {
                        if(rightValue == 0) throw new ArithmeticException("Division by zero");
                        stack.push(leftValue / rightValue);
                        break;
                    }
                    case '^': {
                        stack.push(Math.pow(leftValue,rightValue));
                        break;
                    }
                }
            }
        }
        return stack.pop();
    }

    /**
     * Function to get the priority of the operations
     * @param operator
     * @return priority
     */
    private int priority(String operator) {

        if(operator.equals("+") || operator.equals("-")) return 1;
        else if(operator.equals("*") || operator.equals("/")) return 2;
        else if(operator.equals("^")) return 3;
        else return 0;
    }

    /**
     * Checking a correct placement of brackets
     * @param expression
     * @return 1|0
     */
    public boolean checkBrackets(String expression){
        Stack<String> stack = new Stack<>();
        StringTokenizer stringTokenizer = new StringTokenizer(expression, "()",true);
        while(stringTokenizer.hasMoreTokens()) {
            String elementInBracket = stringTokenizer.nextToken();
            if(elementInBracket.equals("(")) stack.push(elementInBracket);

            if(elementInBracket.equals(")")) {
                if (stack.isEmpty()) return false;
                if (!stack.pop().equals("(")) return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * Function for changing text with negative numbers
     * @param expression
     * @return
     */
    private String addZeros(String expression){
        StringBuilder stringBuilder = new StringBuilder(expression);
        ArrayList<Integer> IndexList = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(expression,"-(",true);
        int fromIndex=0;
        while(stringTokenizer.hasMoreTokens()){
            String element = stringTokenizer.nextToken();
            if(element.equals("(")) {
                if((stringTokenizer.nextToken()).equals("-")){
                    int index = expression.indexOf('(',fromIndex);
                    fromIndex = index + 1;
                    IndexList.add(index);
                }
                else{
                    fromIndex = expression.indexOf("(",fromIndex) + 1;
                }
            }
        }
        int counter = 1;
        for(Integer indexOfBracket:IndexList){
            stringBuilder.insert(indexOfBracket+counter,'0');
            counter++;

        }
        return stringBuilder.toString();
    }
}
            