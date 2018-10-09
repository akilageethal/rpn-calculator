package main.java.input.operator;

import main.java.input.OperatorInput;

public class OperatorFactory {

    public static OperatorInput getOperator(String token) {
        OperatorInput operator = null;

        Operator enumOperator = Operator.getOperator(token);
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
