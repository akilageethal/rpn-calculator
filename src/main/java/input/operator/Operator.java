package main.java.input.operator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Operator {

    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    SQRT("sqrt"),
    CLEAR("clear"),
    UNDO("undo");

    private String id;
    private static final Map<String, Operator> operatorMap = new HashMap<String, Operator>();

    static {
        for (Operator operator : values()) {
            operatorMap.put(operator.getId(), operator);
        }
    }

    Operator(final String code) {
        this.id = code;
    }

    public String getId() {
        return this.id;
    }

    public static Operator getOperator(String token) {
        return operatorMap.get(token);
    }

    /**
     * Checks if the given token is an accepted operator by comparing with the operator enums ( such as + * sqrt etc)
     * @param token input token to be checked
     * @return True if the token is a valid operator , false otherwise
     */
    public static boolean isValidOperator(String token) {
        return Arrays.stream(Operator.values()).anyMatch((t) -> t.getId().equalsIgnoreCase(token));
    }
}
