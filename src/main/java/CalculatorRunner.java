package main.java;

import java.util.Scanner;

/**
 * Runner class to run the calculator application
 * Reads the user input and run the calculator if the input is not empty
 */
public class CalculatorRunner {

    private static final String SPLIT_BY = "\\s";

    private void run () {
        String inputString = null;
        Calculator calculator = new Calculator();
        calculator.setSeparator(SPLIT_BY);
        // Read the user input.
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            inputString = scanner.nextLine();
            if (inputString != null && !inputString.isEmpty()) {
                calculator.execute(inputString);
            } else {
                System.out.println("Please enter a valid input");
            }
        }

    }

    public static void main(String[] args) {
        CalculatorRunner calculatorRunner = new CalculatorRunner();
        calculatorRunner.run();
    }

}
