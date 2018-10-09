package main.java.input;

import main.java.exception.CalculatorException;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Abstract class for the Input Token type. All user input tokens will be an InputToken type and
 * will be instantiated with the correct subclass
 */
public abstract class InputToken {

    private String value;

    /**
     * performs the calculation on the user input. Two subclasses implement the behaviour accordingly
     * OperatorInput type tokens will perform the operations while the ValueInput type will try to
     * convert the value to a valid number and then add to the stack
     * @param values current value stack of the calculator
     * @param executions execution stack inorder to keep the records for future undo operations
     * @throws CalculatorException all exceptions will be thrown as Calculator Exceptions to the invoker
     */
    public abstract void performCalculatorAction(Stack<BigDecimal> values, Stack<Execution> executions) throws CalculatorException;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
