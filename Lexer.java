import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;
    private int pos = 0;

    public Lexer(String input) {
        this.input = input.replace(" ", ""); // Remove spaces immediately
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (pos < input.length()) {
            char current = input.charAt(pos);

            if (Character.isDigit(current)) {
                StringBuilder sb = new StringBuilder();
                while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                    sb.append(input.charAt(pos++));
                }
                tokens.add(new Token(Token.Type.NUMBER, sb.toString()));
            } else if (Character.isLetter(current)) {
                tokens.add(new Token(Token.Type.VARIABLE, String.valueOf(current)));
                pos++;
            } else if (current == '+') {
                tokens.add(new Token(Token.Type.PLUS, "+"));
                pos++;
            } else if (current == '-') {
                tokens.add(new Token(Token.Type.MINUS, "-"));
                pos++;
            } else if (current == '^') {
                tokens.add(new Token(Token.Type.CARET, "^"));
                pos++;
            } else if (current == '=') {
                tokens.add(new Token(Token.Type.EQUALS, "="));
                pos++;
            } else {
                // Garbage Detector
                throw new RuntimeException("Invalid character: " + current);
            }
        }
        return tokens;
    }
}