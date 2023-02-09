package lexer;

import lexer.Tokens.*;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    List<Token> result = new ArrayList<>();
    String numberAccumulator = "";

    public List<Token> read(String line) {
        PlusToken plus = PlusToken.getInstance();
        MinusToken minus = MinusToken.getInstance();
        OpenParenthesesToken open = OpenParenthesesToken.getInstance();
        CloseParenthesesToken close = CloseParenthesesToken.getInstance();

        for (char c : line.toCharArray()) {
            switch (c) {
                case ' ':
                case '\t':
                case '\n':
                    exitNumberState();
                    break;
                case '+':
                    exitNumberState();
                    result.add(plus);
                    break;
                case '-':
                    exitNumberState();
                    result.add(minus);
                    break;
                case '(':
                    exitNumberState();
                    result.add(open);
                    break;
                case ')':
                    exitNumberState();
                    result.add(close);
                default:
                    if ('0' <= c && c <= '9') {
                        enterNumberState(c);
                    }
                    break;
            }
        }
        exitNumberState();
        System.out.println(result);
        return result;
    }


    private void enterNumberState(char c) {
        numberAccumulator += c;
    }

    private void exitNumberState() {
        if (!numberAccumulator.isBlank()) {
            result.add(new NumberToken(Integer.parseInt(numberAccumulator)));
            numberAccumulator = "";
        }
    }
}