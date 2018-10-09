package test.java.input;

import main.java.input.Execution;
import main.java.exception.CalculatorException;
import main.java.input.OperatorInput;
import main.java.input.operator.AddOperator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Stack;

import static org.junit.Assert.assertTrue;

public class OperatorInputTest {
    private OperatorInput operatorInput;

    @Before
    public void setUp() {
        operatorInput = new AddOperator();
    }

    @After
    public void tearDown() {
        this.operatorInput = null;
    }

    @Test
    public void testPerformCalculatorAction() throws CalculatorException {
        Stack<Execution> mockExecutions = new Stack<Execution>();
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        mockStack.push(new BigDecimal("200"));
        mockStack.push(new BigDecimal("500"));
        operatorInput.performCalculatorAction(mockStack, mockExecutions);
        Execution lastExecution = mockExecutions.pop();
        assertTrue(!lastExecution.isValuePush());
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 500);
        assertTrue(lastExecution.getExecutionValues().pop().doubleValue() == 200);
    }
}
