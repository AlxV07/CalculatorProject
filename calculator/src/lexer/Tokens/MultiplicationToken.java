package lexer.Tokens;

public class MultiplicationToken implements Token {
    private static final MultiplicationToken instance = new MultiplicationToken();

    public static MultiplicationToken getInstance() {
        return instance;
    }

    private MultiplicationToken() {}

    @Override
    public TokenType getType() {
        return TokenType.MULTIPLICATION;
    }
}
