package test.java.input.operator;

import main.java.input.Execution;
import main.java.exception.CalculatorException;
import main.java.input.operator.ClearOperator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Stack;

import static org.junit.Assert.assertTrue;

public class ClearOperatorTest {
    private ClearOperator clearOperator;

    @Before
    public void setUp() {
        clearOperator = new ClearOperator();
    }

    @After
    public void tearDown() {
        this.clearOperator = null;
    }

    @Test
    public void testPerformWhenTheStachHasValues() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        Stack<Execution> mockExecutions = new Stack<Execution>();
        mockStack.push(new BigDecimal("200.123"));
        mockStack.push(new BigDecimal("100.123"));
        clearOperator.perform(mockStack, mockExecutions);
        assertTrue(mockStack.isEmpty());

        Execution lastExecution = mockExecutions.pop();
        assertTrue(!lastExecution.isValuePush());
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 100.123);
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 200.123);
    }
}
