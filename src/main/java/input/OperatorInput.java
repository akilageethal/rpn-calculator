package main.java.input;

import main.java.exception.CalculatorException;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Abstract class for operators, all operators must extend this and implement perform method
 */
public abstract class OperatorInput extends AbstractInput {

    protected static final int PREFERRED_DECIMAL_POINTS = 15;

    @Override
    public void calculate(Stack<BigDecimal> values, Stack<Execution> executions)throws CalculatorException {
        perform(values, executions);
    }

    /**
     * perform the required operation on the values stack.
     * @param values current calculator value stack
     * @param executions stack of previous execution steps. performing execution should be added into this list
     * @throws CalculatorException generic exception thrown to invoker for all exception scenarios
     */
    public abstract  void perform(Stack<BigDecimal> values, Stack<Execution> executions) throws CalculatorException;

    /**
     * check if each subclass operator has sufficient items in the stack to perform its operation
     * @param values value stack
     * @return True/false
     */
    public abstract  boolean hasSufficientItems(Stack<BigDecimal> values);


    /**
     * this method can be called after performing the operation to add the execution step to the list.
     * @param executions current list of previous executions
     * @param values values related to the current execution
     */
    protected void addToExecutions(Stack<Execution> executions, BigDecimal... values) {
        Execution currentExecution = new Execution(false);
        for (BigDecimal value : values) {
            currentExecution.getExecutionValues().push(value);
        }
        executions.add(currentExecution);
    }
}
