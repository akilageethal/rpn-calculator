package test.java.input.operator;

import main.java.input.Execution;
import main.java.exception.CalculatorException;
import main.java.input.operator.MultiplyOperator;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.Stack;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MultiplyOperatorTest {
    private MultiplyOperator multiplyOperator;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        multiplyOperator = new MultiplyOperator();
    }

    @After
    public void tearDown() {
        this.multiplyOperator = null;
    }

    @Test
    public void testPerformWhenStackHasValidValues() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        Stack<Execution> mockExecutions = new Stack<Execution>();
        mockStack.push(new BigDecimal("20"));
        mockStack.push(new BigDecimal("33"));
        multiplyOperator.perform(mockStack, mockExecutions);
        assertTrue(mockStack.pop().doubleValue() == 660);

        Execution lastExecution = mockExecutions.pop();
        assertTrue(!lastExecution.isValuePush());
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 33);
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 20);
    }

    @Test
    public void testPerformWhenStackHasValidNegativeValues() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        Stack<Execution> mockExecutions = new Stack<Execution>();
        mockStack.push(new BigDecimal("20"));
        mockStack.push(new BigDecimal("-33"));
        multiplyOperator.perform(mockStack, mockExecutions);
        assertTrue(mockStack.pop().doubleValue() == -660);

        Execution lastExecution = mockExecutions.pop();
        assertTrue(!lastExecution.isValuePush());
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == -33);
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 20);
    }

    @Test
    public void testPerformThrowsExceptionWhenStackIsEmpty() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        Stack<Execution> mockExecutions = new Stack<Execution>();
        exception.expect(CalculatorException.class);
        exception.expectMessage("insufficient parameters");
        multiplyOperator.perform(mockStack, mockExecutions);
    }

    @Test
    public void testPerformThrowsExceptionWhenStackHasOneValue() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        mockStack.push(new BigDecimal("100.123"));
        Stack<Execution> mockExecutions = new Stack<Execution>();
        exception.expect(CalculatorException.class);
        exception.expectMessage("insufficient parameters");
        multiplyOperator.perform(mockStack, mockExecutions);
    }

    @Test
    public void testHasSufficientItemsNotSatisfied() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        mockStack.push(new BigDecimal("100.123"));
        assertFalse(multiplyOperator.hasSufficientItems(mockStack));
    }

    @Test
    public void testHasSufficientItemsSatisfied() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        mockStack.push(new BigDecimal("100.123"));
        mockStack.push(new BigDecimal("100.123"));
        assertTrue(multiplyOperator.hasSufficientItems(mockStack));
    }
}
