package test.java.input.operator;

import main.java.input.Execution;
import main.java.exception.CalculatorException;
import main.java.input.operator.DivideOperator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.Stack;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DivideOperatorTest {
    private DivideOperator divideOperator;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        divideOperator = new DivideOperator();
    }

    @Test
    public void testPerformWhenStackHasValidValues() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        Stack<Execution> mockExecutions = new Stack<Execution>();
        mockStack.push(new BigDecimal("200"));
        mockStack.push(new BigDecimal("33"));
        divideOperator.perform(mockStack, mockExecutions);
        assertTrue(mockStack.pop().doubleValue() == 6.060606060606061);

        Execution lastExecution = mockExecutions.pop();
        assertTrue(!lastExecution.isValuePush());
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 33);
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 200);
    }

    @Test
    public void testPerformWhenStackHasValidNegativeValues() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        Stack<Execution> mockExecutions = new Stack<Execution>();
        mockStack.push(new BigDecimal("200"));
        mockStack.push(new BigDecimal("-33"));
        divideOperator.perform(mockStack, mockExecutions);
        assertTrue(mockStack.pop().doubleValue() == -6.060606060606061);
    }

    @Test
    public void testPerformWhenDivideByZero() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        Stack<Execution> mockExecutions = new Stack<Execution>();
        mockStack.push(new BigDecimal("50"));
        mockStack.push(new BigDecimal("0"));
        exception.expect(CalculatorException.class);
        exception.expectMessage("cannot divide by zero");
        divideOperator.perform(mockStack, mockExecutions);
        assertTrue(mockStack.pop().doubleValue() == -6.060606060606061);
    }

    @Test
    public void testPerformThrowsExceptionWhenStackIsEmpty() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        Stack<Execution> mockExecutions = new Stack<Execution>();
        exception.expect(CalculatorException.class);
        exception.expectMessage("insufficient parameters");
        divideOperator.perform(mockStack, mockExecutions);
    }

    @Test
    public void testPerformThrowsExceptionWhenStackHasOneValue() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        mockStack.push(new BigDecimal("100.123"));
        Stack<Execution> mockExecutions = new Stack<Execution>();
        exception.expect(CalculatorException.class);
        exception.expectMessage("insufficient parameters");
        divideOperator.perform(mockStack, mockExecutions);
    }

    @Test
    public void testHasSufficientItemsNotSatisfied() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        mockStack.push(new BigDecimal("100.123"));
        assertFalse(divideOperator.hasSufficientItems(mockStack));
    }

    @Test
    public void testHasSufficientItemsSatisfied() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        mockStack.push(new BigDecimal("100.123"));
        mockStack.push(new BigDecimal("100.123"));
        assertTrue(divideOperator.hasSufficientItems(mockStack));
    }
}
