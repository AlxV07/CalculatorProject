package lexer.Tokens;

public class MinusToken implements Token{
    private static final MinusToken instance = new MinusToken();

    public static MinusToken getInstance() {
        return instance;
    }

    private MinusToken() {}

    @Override
    public TokenType getType() {
        return TokenType.MINUS;
    }
}
