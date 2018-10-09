package test.java.input.operator;

import main.java.input.Execution;
import main.java.exception.CalculatorException;
import main.java.input.operator.AddOperator;
import main.java.input.operator.SquareRootOperator;
import main.java.input.operator.UndoOperator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Stack;

import static org.junit.Assert.assertTrue;

public class UndoOperatorTest {

    private UndoOperator undoOperator;

    @Before
    public void setUp() {
        undoOperator = new UndoOperator();
    }

    @After
    public void tearDown() {
        this.undoOperator = null;
    }

    @Test
    public void testPerformForAdditionUndo() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        Stack<Execution> mockExecutions = new Stack<Execution>();
        mockStack.push(new BigDecimal("200.123"));
        mockStack.push(new BigDecimal("100.123"));
        AddOperator addOperator = new AddOperator();
        addOperator.perform(mockStack, mockExecutions);
        undoOperator.perform(mockStack, mockExecutions);
        assertTrue(mockStack.pop().doubleValue() == 100.123);
        assertTrue(mockStack.pop().doubleValue() == 200.123);
    }

    @Test
    public void testPerformForSqrtUndo() throws CalculatorException {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        Stack<Execution> mockExecutions = new Stack<Execution>();
        mockStack.push(new BigDecimal("100"));
        SquareRootOperator squareRootOperator = new SquareRootOperator();
        squareRootOperator.perform(mockStack, mockExecutions);
        undoOperator.perform(mockStack, mockExecutions);
        assertTrue(mockStack.pop().doubleValue() == 100);
    }
}
