package parser;

import lexer.Tokens.NumberToken;
import lexer.Tokens.Token;
import lexer.Tokens.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    List<AstNode> list = null;
    List<Integer> parenthesesQue = new ArrayList<>();

    public AstNode parse(List<Token> tokens) {
        List<AstNode> t = new ArrayList<>(tokens.size());
        for (int i = 0; i < tokens.size(); i++) {
         switch (tokens.get(i).getType()) {
             case OPEN -> addParenthesesQue(i);
             case CLOSE -> {
//                 list.set(i, completeParenthesesPatient(tokens, i));
//                 for (int j = i; j > parenthesesQue.get(parenthesesQue.size()-1); j--) {
//                     t.remove(j);
//                     i--;
//                 }
             }
         }
        }
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i) != null && tokens.get(i).getType() == TokenType.NUMBER)
                t.set(i, new NumberNode(((NumberToken)tokens.get(i)).n()));
        }
         // Insert Multi && Div here
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i) != null){
                switch (tokens.get(i).getType()) {
                    case PLUS -> {
                        t.set(i, new PlusNode(t.get(i - 1), t.get(i + 1)));
                        tokens.set(i, null);
                        t.remove(i + 1);
                        tokens.remove(i + 1);
                        t.remove(i - 1);
                        tokens.remove(i - 1);
                        i--;
                    }
                    case MINUS -> {
                        t.set(i, new MinusNode(t.get(i - 1), t.get(i + 1)));
                        tokens.set(i, null);
                        t.remove(i + 1);
                        tokens.remove(i + 1);
                        t.remove(i - 1);
                        tokens.remove(i - 1);
                        i--;
                    }
                }
            }
        }
        return t.get(0);
    }

    public void addParenthesesQue(int index) {
        parenthesesQue.add(index);
    }

    public AstNode completeParenthesesPatient(List<Token> l, int index) {
        List<Token> subList = l.subList(parenthesesQue.get(parenthesesQue.size()-1), index+1);
        return parse(subList);
    }
}
