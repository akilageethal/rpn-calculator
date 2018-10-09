package main.java.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Stack;

/**
 * A utility class to do the required console prints with required formatting
 */
public class PrintUtil {

    //decimal format to display the stack on console. set to 10 decimal points as required
    private static final String DECIMAL_FORMAT = "#.##########";

    /**
     * prints the current stack onto the console with correct formatting of values
     *
     * @param stack current value stack to be printed
     */
    public static String getCurrentStackString(Stack<BigDecimal> stack) {
        StringBuilder valueStack = new StringBuilder();

        valueStack.append("Stack: ");
        for (BigDecimal value : stack) {
            valueStack.append(formatValue(value));
            valueStack.append(" ");
        }
        return valueStack.toString();
    }

    /**
     * format the value according to the preferred format
     *c
     * @param valueToFormat value to be formatted as bigdecimal from the stack
     * @return formatted String to be printed to console
     */
    private static String formatValue(BigDecimal valueToFormat) {
        DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT);
        return df.format(valueToFormat);
    }

    public static String getErrorMessage(String message, int count, String token) {
        StringBuilder errorMsg = new StringBuilder();
        errorMsg.append("operator ").append(token);
        errorMsg.append(" (position ").append(count).append(") ");
        errorMsg.append(message);
        return errorMsg.toString();
    }
}
