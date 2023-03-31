package lexer.Tokens;

public class CloseParenthesesToken implements Token{
    private static final CloseParenthesesToken instance = new CloseParenthesesToken();

    private CloseParenthesesToken() {}

    public static CloseParenthesesToken getInstance() {
        return instance;
    }

    @Override
    public TokenType getType() {
        return TokenType.CLOSE;
    }
}
