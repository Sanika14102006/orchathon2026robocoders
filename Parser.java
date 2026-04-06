import java.util.List;

public class Parser {
    private List<Token> tokens;
    private int pos = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token peek() {
        return pos < tokens.size() ? tokens.get(pos) : null;
    }

    private Token eat(Token.Type type) {
        Token t = peek();
        if (t != null && t.type == type) {
            pos++;
            return t;
        }
        return null;
    }

    // LEVEL 1: Addition and Subtraction
    public Node parseExpression() {
        Node node = parseTerm();
        while (true) {
            if (eat(Token.Type.PLUS) != null) node = new Node(Node.Type.ADD, node, parseTerm());
            else if (eat(Token.Type.MINUS) != null) node = new Node(Node.Type.SUB, node, parseTerm());
            else break;
        }
        return node;
    }

    // LEVEL 2: Multiplication (Implicit like 10x)
    private Node parseTerm() {
        Node node = parseFactor();
        // If next token is a Variable, it's implicit multiplication (e.g., 10x)
        while (peek() != null && peek().type == Token.Type.VARIABLE) {
            node = new Node(Node.Type.MUL, node, parseFactor());
        }
        return node;
    }

    // LEVEL 3: Exponents and Bases
    private Node parseFactor() {
        Token t = peek();
        if (eat(Token.Type.NUMBER) != null) {
            Node numNode = new Node(Node.Type.NUMBER, t.value);
            if (eat(Token.Type.CARET) != null) {
                return new Node(Node.Type.POW, numNode, parseFactor());
            }
            return numNode;
        } else if (eat(Token.Type.VARIABLE) != null) {
            return new Node(Node.Type.VARIABLE, t.value);
        }
        throw new RuntimeException("Unexpected math garbage at position " + pos);
    }
}
