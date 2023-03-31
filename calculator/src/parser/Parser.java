package parser;

import lexer.Tokens.NumberToken;
import lexer.Tokens.Token;
import lexer.Tokens.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public AstNode parse(List<Token> tokens) {
        if (tokens.size() == 1) {
            return new NumberNode(((NumberToken) tokens.get(0)).n());
        }

        boolean isInsideParenGroup = false;
        int exteriorOpenParenIndex = -1;
        int exteriorCloseParenIndex;
        int parenLevel = 0;
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).getType() == TokenType.OPEN) {
                if (!isInsideParenGroup) {
                    isInsideParenGroup = true;
                    exteriorOpenParenIndex = i;
                }
                parenLevel++;
            }
            else if (tokens.get(i).getType() == TokenType.CLOSE) {
                parenLevel--;
                if (parenLevel == 0) {
                    exteriorCloseParenIndex = i;
                    List<Token> temp = new ArrayList<>();
                    for (int j = exteriorOpenParenIndex + 1; j < exteriorCloseParenIndex; j++) {
                        temp.add(tokens.get(j));
                    }
                    NumberToken t = new NumberToken(parse(temp).eval());
                    tokens.subList(exteriorOpenParenIndex, exteriorCloseParenIndex + 1).clear();
                    tokens.add(exteriorOpenParenIndex, t);
                    return parse(tokens);
                }
            }
        }


        for (int i = tokens.size() - 1; i >= 0; i--) {
            if (tokens.get(i).getType() == TokenType.PLUS) {
                return new PlusNode(parse(tokens.subList(0, i)), parse(tokens.subList(i + 1, tokens.size())));
            }
            else if (tokens.get(i).getType() == TokenType.MINUS) {
                return new MinusNode(parse(tokens.subList(0, i)), parse(tokens.subList(i + 1, tokens.size())));
            }
        }
        for (int i = tokens.size() - 1; i >= 0; i--) {
            if (tokens.get(i).getType() == TokenType.MULTIPLICATION) {
                return new MultiplicationNode(parse(tokens.subList(0, i)), parse(tokens.subList(i + 1, tokens.size())));
            }
            else if (tokens.get(i).getType() == TokenType.DIVISION) {
                return new DivisionNode(parse(tokens.subList(0, i)), parse(tokens.subList(i + 1, tokens.size())));
            }
        }
        return null;
    }
}
