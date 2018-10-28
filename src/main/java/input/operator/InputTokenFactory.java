package main.java.input.operator;

import main.java.input.AbstractInput;
import main.java.input.OperatorInput;
import main.java.input.ValueInput;

public class InputTokenFactory {

    public static AbstractInput getInputTokenType(String token) {
        OperatorInput operator = null;

        Operator enumOperator = Operator.getOperator(token);
        if(enumOperator == null) { // if not an operator create a value input to push to stack
            return new ValueInput(token);
        }
        switch (enumOperator) {
            case ADD:
                operator = new AddOperator();
                break;
            case SUBTRACT:
                operator = new SubtractOperator();
                break;
            case MULTIPLY:
                operator = new MultiplyOperator();
                break;
            case DIVIDE:
                operator = new DivideOperator();
                break;
            case SQRT:
                operator = new SquareRootOperator();
                break;
            case UNDO:
                operator = new UndoOperator();
                break;
            case CLEAR:
                operator = new ClearOperator();
        }
        return operator;
    }
}
