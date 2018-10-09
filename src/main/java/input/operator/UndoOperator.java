package main.java.input.operator;

import main.java.input.Execution;
import main.java.input.OperatorInput;

import java.math.BigDecimal;
import java.util.Stack;

public class UndoOperator extends OperatorInput {
    @Override
    public void perform(Stack<BigDecimal> values, Stack<Execution> executions) {

        if(executions.isEmpty()) { // if there is no previous executions
            return;
        }
        Execution lastExecution = executions.pop();
        if (!values.isEmpty()) { // check for empty stack, can happen if the first statement is undo or after a clear execution
            values.pop();  // if the last execution is a value push into stack, removing that item from stack is enough
        }

        // if not a value push, add the values stored in the execution in order
        if(!lastExecution.isValuePush() && !lastExecution.getExecutionValues().isEmpty()) {
            for (BigDecimal oldValue : lastExecution.getExecutionValues()) {
                values.push(oldValue);
            }
        }
    }

    @Override
    public boolean hasSufficientItems(Stack<BigDecimal> values) {
        return true;
    }
}
