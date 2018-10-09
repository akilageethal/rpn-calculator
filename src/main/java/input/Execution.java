package main.java.input;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Execution class to keep the information about each calculation step
 * to facilitate undo operation when requested.
 */
public class Execution {
    private boolean valuePush;
    private Stack<BigDecimal> executionValues = new Stack<BigDecimal>();

    public Execution(boolean valuePush) {
        this.valuePush = valuePush;
    }

    public Execution(boolean valuePush, Stack<BigDecimal> executionValues) {
        this.valuePush = valuePush;
        this.executionValues = executionValues;
    }

    public boolean isValuePush() {
        return valuePush;
    }

    public void setValuePush(boolean valuePush) {
        this.valuePush = valuePush;
    }

    public Stack<BigDecimal> getExecutionValues() {
        return executionValues;
    }

    public void setExecutionValues(Stack<BigDecimal> executionValues) {
        this.executionValues = executionValues;
    }
}
