package main.java.input.operator;

import main.java.input.Execution;
import main.java.exception.CalculatorException;
import main.java.input.OperatorInput;

import java.math.BigDecimal;
import java.util.Stack;

public class DivideOperator extends OperatorInput {

    @Override
    public void perform(Stack<BigDecimal> values, Stack<Execution> executions) throws CalculatorException {

        if (!hasSufficientItems(values)) {
            throw new CalculatorException("insufficient parameters");
        }

        BigDecimal topFirst, topSecond;
        if (values.get(values.size()-1).compareTo(BigDecimal.ZERO) == 0) {
            throw new CalculatorException("cannot divide by zero");
        } else {
            topFirst = values.pop();
            topSecond = values.pop();
            values.push(topSecond.divide(topFirst, PREFERRED_DECIMAL_POINTS, BigDecimal.ROUND_HALF_UP));
        }
        addToExecutions(executions, topSecond, topFirst);
    }

    @Override
    public boolean hasSufficientItems(Stack<BigDecimal> values) {
        return values.size() >= 2;
    }
}
