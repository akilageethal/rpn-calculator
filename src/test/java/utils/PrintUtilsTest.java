package test.java.utils;

import main.java.Calculator;
import main.java.input.Execution;
import main.java.utils.PrintUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Stack;

import static org.junit.Assert.assertTrue;

public class PrintUtilsTest {

    @Test
    public void testGetCurrentStackString() {
        Stack<BigDecimal> mockStack = new Stack<BigDecimal>();
        mockStack.push(new BigDecimal("200.123"));
        mockStack.push(new BigDecimal("100.123"));
        mockStack.push(new BigDecimal("50"));
        assertTrue(PrintUtil.getCurrentStackString(mockStack).equals("Stack: 200.123 100.123 50 "));
    }

    @Test
    public void testGetErrorMessage() {
        assertTrue(PrintUtil.getErrorMessage("Test Message", 2, "*").equals("operator * (position 2) Test Message"));

    }
}
