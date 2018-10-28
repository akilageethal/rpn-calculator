package test.java.input;

import main.java.input.Execution;
import main.java.exception.CalculatorException;
import main.java.input.ValueInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.Stack;

import static org.junit.Assert.assertTrue;

public class ValueInputTest {
    private ValueInput valueInput;

    @Before
    public void setUp() {
        valueInput = new ValueInput();
    }

    @After
    public void tearDown() {
        this.valueInput = null;
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * For valid input, value should be added to the stack and execution should be set as valuePush = true
     */
    @Test
    public void testPerformCalculatorActionForValidInput() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        Stack<Execution> mockExecutions = new Stack<Execution>();
        valueInput.setValue("153.36523");
        valueInput.calculate(mockStack, mockExecutions);
        assertTrue(mockStack.pop().doubleValue() == 153.36523);

        Execution lastExecution = mockExecutions.pop();
        assertTrue(lastExecution.isValuePush());
    }

    /**
     * For valid input, value should notbe added to the stack and execution should be set as valuePush = true
     * CalculationException should be thrown
     */
    @Test
    public void testPerformCalculatorActionForInalidInput() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        Stack<Execution> mockExecutions = new Stack<Execution>();
        exception.expect(CalculatorException.class);
        exception.expectMessage("Invalid input token");
        valueInput.setValue("abc");
        valueInput.calculate(mockStack, mockExecutions);
    }
}
