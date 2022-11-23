package lexer.Tokens;

public class PlusToken implements Token{
        private static final PlusToken instance = new PlusToken();

        public static PlusToken getInstance() {
            return instance;
        }
        private PlusToken() {}

        @Override
        public TokenType getType() {
            return TokenType.PLUS;
    }
}
