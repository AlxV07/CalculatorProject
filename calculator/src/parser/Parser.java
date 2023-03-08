package parser;

import lexer.Tokens.NumberToken;
import lexer.Tokens.Token;
import lexer.Tokens.TokenType;

import java.util.List;

public class Parser {
    public AstNode parse(List<Token> tokens) {

        if (tokens.size() == 1) {
            return new NumberNode(((NumberToken) tokens.get(0)).n());
        }

//        boolean checkingForParentheses = false;
//        int parenthesesStartIndex = -1;
//        int parenthesesEndIndex;
//        int parenthesesLevel = 0;
//        for (int i = 0; i < tokens.size(); i++) {
//            if (tokens.get(i).getType() == TokenType.OPEN) {
//                if (!checkingForParentheses) {
//                    checkingForParentheses = true;
//                    parenthesesStartIndex = i;
//                }
//                parenthesesLevel++;
//            }
//            else if (tokens.get(i).getType() == TokenType.CLOSE) {
//                parenthesesLevel--;
//                if (parenthesesLevel == 0) {
//                    parenthesesEndIndex = i;
//                    NumberToken t = new NumberToken(parse(tokens.subList(parenthesesStartIndex + 1, parenthesesEndIndex)).eval());
//                    if (parenthesesEndIndex >= parenthesesStartIndex) {
//                        tokens.subList(parenthesesStartIndex, parenthesesEndIndex + 1).clear();
//                    }
//                    tokens.add(parenthesesStartIndex, t);
//                    return parse(tokens);
//                }
//            }
//        }

        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).getType() == TokenType.PLUS) {
                return new PlusNode(parse(tokens.subList(0, i)), parse(tokens.subList(i+1, tokens.size())));
            }
            else if (tokens.get(i).getType() == TokenType.MINUS) {
                return new MinusNode(parse(tokens.subList(0, i)), parse(tokens.subList(i+1, tokens.size())));
            }
        }
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).getType() == TokenType.MULTIPLICATION) {
                return new MultiplicationNode(parse(tokens.subList(0, i)), parse(tokens.subList(i+1, tokens.size())));
            }
            else if (tokens.get(i).getType() == TokenType.DIVISION) {
                return new DivisionNode(parse(tokens.subList(0, i)), parse(tokens.subList(i+1, tokens.size())));
            }
        }
        return null;
    }
}
