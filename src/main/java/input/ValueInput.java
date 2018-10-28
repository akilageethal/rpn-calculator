package main.java.input;
import main.java.exception.CalculatorException;
import java.math.BigDecimal;
import java.util.Stack;

public class ValueInput extends AbstractInput {

    public ValueInput(String token) {
        setValue(token);
    }

    public ValueInput() {
    }

    /**
     * push the number inputs from user to the stack.
     * @param values     current value stack
     * @param executions execution stack to keep record od operation for later undo purposes
     * @throws CalculatorException if the given String is not a valid number throws an exception
     */
    @Override
    public void calculate(Stack<BigDecimal> values, Stack<Execution> executions) throws CalculatorException {
        try {
            values.push(new BigDecimal(getValue()));
            executions.push(new Execution(true));
        } catch (NumberFormatException e) {
            throw new CalculatorException("Invalid input token", e);
        }
    }
}
