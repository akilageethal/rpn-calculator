package main.java.input;

import main.java.exception.CalculatorException;

import java.math.BigDecimal;
import java.util.Stack;

public abstract class InputToken {

    private String value;

    public abstract void performCalculatorAction(Stack<BigDecimal> values, Stack<Execution> executions) throws CalculatorException;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
