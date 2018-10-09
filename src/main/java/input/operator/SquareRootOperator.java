package main.java.input.operator;

import main.java.input.Execution;
import main.java.exception.CalculatorException;
import main.java.input.OperatorInput;

import java.math.BigDecimal;
import java.util.Stack;

public class SquareRootOperator extends OperatorInput {
    @Override
    public void perform(Stack<BigDecimal> values, Stack<Execution> executions) throws CalculatorException {

        if (!hasSufficientItems(values)) {
            throw new CalculatorException("insufficient parameters");
        }

        BigDecimal topFirst;
        topFirst = values.pop();
        if (topFirst.compareTo(BigDecimal.ZERO) < 0) {
            throw new CalculatorException("cannot calculate square root on negative number");
        } else {
            double doubleValue = topFirst.doubleValue();
            values.push(new BigDecimal(Math.sqrt(doubleValue)).setScale(PREFERRED_DECIMAL_POINTS, BigDecimal.ROUND_HALF_UP));
        }
        addToExecutions(executions, topFirst);
    }

    @Override
    public boolean hasSufficientItems(Stack<BigDecimal> values) {
        return values.size() >= 1;
    }
}
