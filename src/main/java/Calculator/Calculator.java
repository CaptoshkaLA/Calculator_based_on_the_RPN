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
     * @param /expression
     * @return transformed expression
     */
    public String toPostfix(String text){
        String enterText=replaceNegative(text);
        StringBuilder exitText= new StringBuilder();
        Stack<String> stack=new Stack<>();
        StringTokenizer stringTokenizer=new StringTokenizer(enterText,"+-*^/()",true);
        while (stringTokenizer.hasMoreTokens()){
            String element=stringTokenizer.nextToken();
            switch (element) {
                case "+":
                case "*":
                case "-":
                case "^":
                case "/":
                    while (!stack.empty() && getPriority(stack.peek()) >= getPriority(element))
                        exitText.append(stack.pop()).append(" ");

                    stack.push(element);
                    break;
                case "(":
                    stack.push(element);
                    break;
                case ")":

                    while (!stack.peek().equals("(")) exitText.append(stack.pop()).append(" ");
                    stack.pop();
                    break;
                default:
                    exitText.append(element).append(" ");
                    break;
            }
        }
        while(!stack.empty()) exitText.append(stack.pop()).append(" ");
        return exitText.toString();
    }

    /**
     * Function for calculating postfix version of expression
     * @param exitText
     * @return result of the expression
     * @throws /EmptyStackException, ArithmeticException
     */
    public double calculate(String exitText) throws Exception {
        Stack<Double>stack=new Stack<>();
        StringTokenizer stringTokenizer=new StringTokenizer(exitText," ");
        while(stringTokenizer.hasMoreTokens()) {
            String element = stringTokenizer.nextToken();
            if (!element.equals("+") && !element.equals("*") && !element.equals("-") && !element.equals("/") && !element.equals("^")) {
                double value = Double.parseDouble(element);
                stack.push(value);
            }
            else {
                double valueOne = stack.pop();
                double valueTwo = stack.pop();
                switch(element.charAt(0)) {
                    case '*': {
                        stack.push(valueTwo * valueOne);
                        break;
                    }
                    case '+': {
                        stack.push(valueTwo + valueOne);
                        break;
                    }
                    case '-': {
                        stack.push(valueTwo - valueOne);
                        break;
                    }
                    case '/': {
                        if(valueOne == 0) throw new ArithmeticException("Division by zero");
                        stack.push(valueTwo / valueOne);
                        break;
                    }
                    case '^': {
                        stack.push(Math.pow(valueTwo,valueOne));
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
    private int getPriority(String operator) {

        if(operator.equals("+") || operator.equals("-")) return 1;
        else if(operator.equals("*") || operator.equals("/")) return 2;
        else if(operator.equals("^")) return 3;
        else return 0;
    }

    /**
     * Checking a correct placement of brackets
     * @param /text
     * @return 1|0
     */
    public boolean checkBrackets(String enterText){
        Stack<String> stackForBrackets = new Stack<>();
        StringTokenizer st = new StringTokenizer(enterText, "()",true);
        while(st.hasMoreTokens()) {
            String elementInBracket = st.nextToken();
            if(elementInBracket.equals("(")) stackForBrackets.push(elementInBracket);

            if(elementInBracket.equals(")")) {
                if (stackForBrackets.isEmpty()) return false;
                if (!stackForBrackets.pop().equals("(")) return false;
            }
        }
        return stackForBrackets.isEmpty();
    }

    /**
     * Function for changing text with negative numbers
     * @param text
     * @return
     */
    private String replaceNegative(String text){
        StringBuilder stringBuilder=new StringBuilder(text);
        ArrayList<Integer> list=new ArrayList<>();
        StringTokenizer stringTokenizer=new StringTokenizer(text,"-(",true);
        int fromIndex=0;
        while(stringTokenizer.hasMoreTokens()){
            String element=stringTokenizer.nextToken();
            if(element.equals("(")){
                if((stringTokenizer.nextToken()).equals("-")){
                    int index=text.indexOf('(',fromIndex);
                    fromIndex=index+1;
                    list.add(index);
                }
                else{
                    fromIndex=text.indexOf("(",fromIndex)+1;
                }
            }
        }
        int counter=1;
        for(Integer indexOfBracket:list){
            stringBuilder.insert(indexOfBracket+counter,'0');
            counter++;

        }
        return stringBuilder.toString();
    }
}
            