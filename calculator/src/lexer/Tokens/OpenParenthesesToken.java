package lexer.Tokens;

public class OpenParenthesesToken implements Token{
    private static final OpenParenthesesToken instance = new OpenParenthesesToken();

    private OpenParenthesesToken() {}

    public static OpenParenthesesToken getInstance() {
        return instance;
    }

    @Override
    public TokenType getType() {
        return TokenType.OPEN;
    }
}
