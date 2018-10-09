package test.java;

import main.java.Calculator;
import main.java.input.Execution;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
        calculator.setSeparator("\\s");
    }

    @After
    public void tearDown() {
        this.calculator = null;
    }


    @Test
    public void testExecuteForInputStringAddition() {
        String input = "5 2 +";
        calculator.execute(input);
        assertTrue(calculator.getValues().pop().doubleValue() == 7);

        Execution lastExecution = calculator.getExecutions().pop();
        assertTrue(!lastExecution.isValuePush());
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 2);
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 5);
    }

    @Test
    public void testExecuteForInputStringSubtraction() {
        String input = "5 2 -";
        calculator.execute(input);
        assertTrue(calculator.getValues().pop().doubleValue() == 3);

        Execution lastExecution = calculator.getExecutions().pop();
        assertTrue(!lastExecution.isValuePush());
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 2);
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 5);
    }

    @Test
    public void testExecuteForInputStringDivision() {
        String input = "15 3 /";
        calculator.execute(input);
        assertTrue(calculator.getValues().pop().doubleValue() == 5);

        Execution lastExecution = calculator.getExecutions().pop();
        assertTrue(!lastExecution.isValuePush());
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 3);
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 15);
    }

    @Test
    public void testExecuteForInputStringMultiplication() {
        String input = "5 2 *";
        calculator.execute(input);
        assertTrue(calculator.getValues().pop().doubleValue() == 10);

        Execution lastExecution = calculator.getExecutions().pop();
        assertTrue(!lastExecution.isValuePush());
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 2);
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 5);
    }

    @Test
    public void testExecuteForSqrt() {
        String input = "100 sqrt";
        calculator.execute(input);
        assertTrue(calculator.getValues().pop().doubleValue() == 10);

        Execution lastExecution = calculator.getExecutions().pop();
        assertTrue(!lastExecution.isValuePush());
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 100);
    }

    @Test
    public void testExecuteForUndoOperation() {
        String input = "5 2 * undo";
        calculator.execute(input);
        assertTrue(calculator.getValues().pop().doubleValue() == 2);
        assertTrue(calculator.getValues().pop().doubleValue() == 5);

        Execution lastExecution = calculator.getExecutions().pop();
        assertTrue(lastExecution.isValuePush());
    }

    @Test
    public void testExecuteForClearOperation() {
        String input = "5 2 * undo clear";
        calculator.execute(input);
        assertTrue(calculator.getValues().isEmpty());

        Execution lastExecution = calculator.getExecutions().pop();
        assertTrue(!lastExecution.isValuePush());
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 2);
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 5);

    }

    /**
     * test for an invalid input. Calculator should run till the last valid token and then return with error
     */
    @Test
    public void testExecuteForInvalidInput() {
        String input = "5 2 * abc";
        calculator.execute(input);
        assertTrue(calculator.getValues().pop().doubleValue() == 10);

        Execution lastExecution = calculator.getExecutions().pop();
        assertTrue(!lastExecution.isValuePush());
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 2);
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 5);
    }

    /**
     * test for an divide by zero. Calculator should keep the current stack
     */
    @Test
    public void testExecuteForDivideByZero() {
        String input = "5 0 /";
        calculator.execute(input);
        assertTrue(calculator.getValues().pop().doubleValue() == 0);
        assertTrue(calculator.getValues().pop().doubleValue() == 5);
    }

    /**
     * test for an insufficient items to perfomr operation. Calculator should keep the current stack
     */
    @Test
    public void testExecuteForInsufficientItems() {
        String input = "5 /";
        calculator.execute(input);
        assertTrue(calculator.getValues().pop().doubleValue() == 5);
    }
}
