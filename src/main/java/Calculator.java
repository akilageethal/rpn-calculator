package main.java;

import main.java.exception.CalculatorException;
import main.java.input.Execution;
import main.java.input.InputToken;
import main.java.input.ValueInput;
import main.java.input.operator.Operator;
import main.java.input.operator.OperatorFactory;
import main.java.utils.PrintUtil;
import java.math.BigDecimal;
import java.util.Stack;

/**
 * Calculator class to do the calculation on a given input string
 * stores the calculator stack and execution stack globally to facilitate continuous run
 */
public class Calculator {

    /**
     * the value stack of the calculator. Always keeps the latest stack
     */
    private Stack<BigDecimal> values = new Stack<BigDecimal>();

    /**
     * stores the history of executions inorder to restore for undo operation
     */
    private Stack<Execution> executions = new Stack<>();

    /**
     * separator for input token. Can be passed in from the runner to be configurable if required
     */
    private String separator = "";

    /**
     * run the calculator operation on a given string, initially tokenize the string by splitting from whitespace
     * and perform the calculator operation on each token.
     * Prints errors and the stack after each operation to the console
     * @param input String input from the user
     */
    public void execute(String input) {
        String[] inputTokens = input.trim().split(getSeparator());

        int count = 1;
        for (String token : inputTokens) {
            InputToken inputToken = createInputToken(token);
            try {
                inputToken.performCalculatorAction(values, executions);
            } catch (CalculatorException e) {
                System.out.println(PrintUtil.getErrorMessage(e.getMessage(), count, token));
                break;
            }
            count += 2; // increment the count by 2 to accommodate the white space in between tokens, for error display
        }
        System.out.println(PrintUtil.getCurrentStackString(getValues()));
    }

    /**
     * create the InputToken object, either a ValueInput or OperatorInput instance using the string token input
     * @param token user input token
     * @return created InputToken type object
     */
    private InputToken createInputToken(String token) {
        InputToken inputToken;
        if(Operator.isValidOperator(token)) {
            inputToken = OperatorFactory.getOperator(token);
        } else {
            inputToken = new ValueInput(token);
        }
        return inputToken;
    }

    public Stack<BigDecimal> getValues() {
        return values;
    }

    public void setValues(Stack<BigDecimal> values) {
        this.values = values;
    }

    public Stack<Execution> getExecutions() {
        return executions;
    }

    public void setExecutions(Stack<Execution> executions) {
        this.executions = executions;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }
}
