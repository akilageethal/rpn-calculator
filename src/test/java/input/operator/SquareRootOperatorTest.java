package test.java.input.operator;

import main.java.input.Execution;
import main.java.exception.CalculatorException;
import main.java.input.operator.SquareRootOperator;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.Stack;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SquareRootOperatorTest {
    private SquareRootOperator squareRootOperator;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        squareRootOperator = new SquareRootOperator();
    }

    @After
    public void tearDown() {
        this.squareRootOperator = null;
    }

    @Test
    public void testPerformWhenStackHasValidValues() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        Stack<Execution> mockExecutions = new Stack<Execution>();
        mockStack.push(new BigDecimal("20"));
        mockStack.push(new BigDecimal("33"));
        squareRootOperator.perform(mockStack, mockExecutions);
        assertTrue(mockStack.pop().doubleValue() == 5.744562646538029);

        Execution lastExecution = mockExecutions.pop();
        assertTrue(!lastExecution.isValuePush());
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 33);
    }

    @Test
    public void testPerformThrowsExceptionWhenStackIsEmpty() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        Stack<Execution> mockExecutions = new Stack<Execution>();
        exception.expect(CalculatorException.class);
        exception.expectMessage("insufficient parameters");
        squareRootOperator.perform(mockStack, mockExecutions);
    }

    @Test
    public void testPerformThrowsExceptionWhenStackHasNegativeValue() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        mockStack.push(new BigDecimal("-100"));
        Stack<Execution> mockExecutions = new Stack<Execution>();
        exception.expect(CalculatorException.class);
        exception.expectMessage("cannot calculate square root on negative number");
        squareRootOperator.perform(mockStack, mockExecutions);
    }

    @Test
    public void testHasSufficientItemsNotSatisfied() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        assertFalse(squareRootOperator.hasSufficientItems(mockStack));
    }

    @Test
    public void testHasSufficientItemsSatisfied() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        mockStack.push(new BigDecimal("100.123"));
        assertTrue(squareRootOperator.hasSufficientItems(mockStack));
    }
}
