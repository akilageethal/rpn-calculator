package test.java.input.operator;

import main.java.input.Execution;
import main.java.exception.CalculatorException;
import main.java.input.operator.SubtractOperator;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.math.BigDecimal;
import java.util.Stack;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SubtractOperatorTest {
    private SubtractOperator subtractOperator;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        subtractOperator = new SubtractOperator();
    }

    @After
    public void tearDown() {
        this.subtractOperator = null;
    }

    @Test
    public void testPerformWhenStackHasValidValues() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        Stack<Execution> mockExecutions = new Stack<Execution>();
        mockStack.push(new BigDecimal("200.123"));
        mockStack.push(new BigDecimal("100.123"));
        subtractOperator.perform(mockStack, mockExecutions);
        assertTrue(mockStack.pop().doubleValue() == 100.000);

        Execution lastExecution = mockExecutions.pop();
        assertTrue(!lastExecution.isValuePush());
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 100.123);
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 200.123);
    }

    @Test
    public void testPerformWhenStackHasValidNegativeValues() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        Stack<Execution> mockExecutions = new Stack<Execution>();
        mockStack.push(new BigDecimal("200.123"));
        mockStack.push(new BigDecimal("-100.123"));
        subtractOperator.perform(mockStack, mockExecutions);
        assertTrue(mockStack.pop().doubleValue() == 300.246);
    }

    @Test
    public void testPerformThrowsExceptionWhenStackIsEmpty() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        Stack<Execution> mockExecutions = new Stack<Execution>();
        exception.expect(CalculatorException.class);
        exception.expectMessage("insufficient parameters");
        subtractOperator.perform(mockStack, mockExecutions);
    }

    @Test
    public void testPerformThrowsExceptionWhenStackHasOneValue() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        mockStack.push(new BigDecimal("100.123"));
        Stack<Execution> mockExecutions = new Stack<Execution>();
        exception.expect(CalculatorException.class);
        exception.expectMessage("insufficient parameters");
        subtractOperator.perform(mockStack, mockExecutions);
    }

    @Test
    public void testHasSufficientItemsNotSatisfied() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        mockStack.push(new BigDecimal("100.123"));
        assertFalse(subtractOperator.hasSufficientItems(mockStack));
    }

    @Test
    public void testHasSufficientItemsSatisfied() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        mockStack.push(new BigDecimal("100.123"));
        mockStack.push(new BigDecimal("100.123"));
        assertTrue(subtractOperator.hasSufficientItems(mockStack));
    }
}
