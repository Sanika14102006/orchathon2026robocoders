import java.util.Map;

public class Solver {

    // This method walks the tree and calculates the result
    public double evaluate(Node node, Map<String, Double> variableValues) {
        if (node == null) return 0;

        switch (node.type) {
            case NUMBER:
                return Double.parseDouble(node.value);
            case VARIABLE:
                return variableValues.getOrDefault(node.value, 0.0);
            case ADD:
                return evaluate(node.left, variableValues) + evaluate(node.right, variableValues);
            case SUB:
                return evaluate(node.left, variableValues) - evaluate(node.right, variableValues);
            case MUL:
                return evaluate(node.left, variableValues) * evaluate(node.right, variableValues);
            case POW:
                return Math.pow(evaluate(node.left, variableValues), evaluate(node.right, variableValues));
            default:
                return 0;
        }
    }
}
