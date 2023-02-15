package lexer.Tokens;

import java.io.Serializable;

public record NumberToken(int n) implements Token, Serializable {

    @Override
    public TokenType getType() {
        return TokenType.NUMBER;
    }

}
