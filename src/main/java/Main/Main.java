package Main;

import Calculator.Calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter the expression in the prefix entry: ");
        Scanner in = new Scanner(System.in);
        String variable = in.nextLine();

        while(!variable.equals("stop")){
            try {
                Calculator calc = new Calculator();
                System.out.println("Prefix version: " + variable);
                variable = calc.toPostfix(variable);
                double result = calc.calculate(variable);
                System.out.println("Postfix version: " + variable);
                System.out.println("Result: " + result);
                System.out.println("If you wanna stop enter 'stop' ");
                System.out.println("Else enter the expression in the prefix entry: ");
                variable = in.nextLine();
            }
            catch (Exception e) {
                System.out.println("ERROR!\nPossible errors: division by zero or incorrect data entry ");
                e.printStackTrace();
                variable = "stop";
            }
        }
    }
}
