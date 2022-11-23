package lexer.Tokens;

import java.io.Serializable;

public class NumberToken implements Token, Serializable {
    public final int n;

    public NumberToken(int n) {
        this.n = n;
    }

    @Override
    public TokenType getType() {
        return TokenType.NUMBER;
    }

}
