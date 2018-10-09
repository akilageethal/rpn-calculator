package main.java.input.operator;

import main.java.input.Execution;
import main.java.input.OperatorInput;

import java.math.BigDecimal;
import java.util.Stack;

public class ClearOperator extends OperatorInput {
    @Override
    public void perform(Stack<BigDecimal> values, Stack<Execution> executions) {
        if(!values.isEmpty()) {
            addToExecutions(executions, values.toArray(new BigDecimal[values.size()]));
            values.clear();
        }
    }

    @Override
    public boolean hasSufficientItems(Stack<BigDecimal> values) {
        return true;
    }
}
