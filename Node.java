public class Node {
    public enum Type { ADD, SUB, MUL, DIV, POW, NUMBER, VARIABLE }

    public Type type;
    public String value; // Holds the number or variable name
    public Node left;
    public Node right;

    // Constructor for numbers/variables (Leaf nodes)
    public Node(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    // Constructor for operations (Internal nodes)
    public Node(Type type, Node left, Node right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }
}
