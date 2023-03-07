package parser;

import lexer.Tokens.Token;
import lexer.Tokens.TokenType;

import java.util.List;

public class Parser {

    public AstNode parse(List<Token> tokens) {
        for(int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).getType() == TokenType.PLUS) {
                return new PlusNode(parse(tokens.subList(0, i)), parse(tokens.subList(i+1, tokens.size())));
            }
            else if (tokens.get(i).getType() == TokenType.MINUS) {
                return new MinusNode(parse(tokens.subList(0, i)), parse(tokens.subList(i+1, tokens.size())));
            }
        }
        return null;
    }
}
