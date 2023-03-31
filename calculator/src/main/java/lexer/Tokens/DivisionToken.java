package lexer.Tokens;

public class DivisionToken implements Token{
    private static final DivisionToken instance = new DivisionToken();

    public static DivisionToken getInstance() {
        return instance;
    }

    private DivisionToken() {}

    @Override
    public TokenType getType() {
        return TokenType.DIVISION;
    }
}
