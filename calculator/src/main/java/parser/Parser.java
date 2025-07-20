package parser;

import lexer.Tokens.NumberToken;
import lexer.Tokens.Token;
import lexer.Tokens.TokenType;
import parser.Nodes.*;
import java.util.List;

public class Parser {
    private List<Token> tokens;
    private int pos;

    public ASTNode parse(List<Token> t) {
        tokens = t;
        pos = 0;
        return this.parseLevel1();
    }

    private OperatorNode nodeFromType(TokenType t, ASTNode left, ASTNode right) {
        switch (t) {
            case PLUS -> {
                return new PlusNode(left, right);
            }
            case MINUS -> {
                return new MinusNode(left, right);
            }
            case MULTIPLICATION -> {
                return new MultiplicationNode(left, right);
            }
            case DIVISION -> {
                return new DivisionNode(left, right);
            }
            default -> {
                return null;
            }
        }
    }

    private ASTNode parseLevel1() {
        /*
        parse `+`, `-` operations
         */
        ASTNode node = parseLevel2();
        Token next = peek();
        while (next != null && (next.getType() == TokenType.PLUS || next.getType() == TokenType.MINUS)) {
            Token op = consume(next.getType());
            ASTNode right = parseLevel2();
            node = nodeFromType(op.getType(), node, right);
            next = peek();
        }
        return node;
    }

    private ASTNode parseLevel2() {
        /*
        parse `*`, `/` operations
         */
        ASTNode node = parseLevel3();
        Token next = peek();
        while (next != null && (next.getType() == TokenType.MULTIPLICATION || next.getType() == TokenType.DIVISION)) {
            Token op = consume(next.getType());
            ASTNode right = parseLevel3();
            node = nodeFromType(op.getType(), node, right);
            next = peek();
        }
        return node;
    }

    private ASTNode parseLevel3() {
        /*
        parse `#`, `(`, `)` tokens
         */
        Token token = peek();
        assert token != null;
        if (token.getType() == TokenType.NUMBER) {
            consume(TokenType.NUMBER);
            return new NumberNode(((NumberToken) token).n());
        } else if (token.getType() == TokenType.OPEN) {
            consume(TokenType.OPEN);
            ASTNode node = parseLevel1();
            consume(TokenType.CLOSE);
            return node;
        } else {
            throw new RuntimeException("Unexpected token: " + token);
        }
    }

    private Token peek() {
        if (pos < tokens.size()) {
            return tokens.get(pos);
        } else {
            return null;
        }
    }

    private Token consume(TokenType type) {
        Token token = peek();
        assert token != null;
        if (token.getType() != type) {
            throw new RuntimeException("Expected " + type + ", got " + token.getType());
        }
        ++pos;
        return token;
    }
}
