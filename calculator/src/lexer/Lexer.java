package lexer;

import lexer.Tokens.*;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final PlusToken plus;
    private final MinusToken minus;
    private final MultiplicationToken multiple;
    private final DivisionToken divisor;
    private final OpenParenthesesToken open;
    private final CloseParenthesesToken close;
    private final List<Token> result;
    private String numberAccumulator;

    public Lexer() {
        this.plus = PlusToken.getInstance();
        this.minus = MinusToken.getInstance();
        this.multiple = MultiplicationToken.getInstance();
        this.divisor = DivisionToken.getInstance();
        this.open = OpenParenthesesToken.getInstance();
        this.close = CloseParenthesesToken.getInstance();
        this.result = new ArrayList<>();
        this.numberAccumulator = "";
    }

    public List<Token> read(String line) {
        result.clear();
        for (char c : line.toCharArray()) {
            switch (c) {
                case ' ', '\t', '\n' -> exitNumberState();
                case '+' -> {
                    exitNumberState();
                    result.add(plus);
                }
                case '-' -> {
                    exitNumberState();
                    result.add(minus);
                }
                case '*' -> {
                    exitNumberState();
                    result.add(multiple);
                }
                case '/' -> {
                    exitNumberState();
                    result.add(divisor);
                }
                case '(' -> {
                    exitNumberState();
                    result.add(open);
                }
                case ')' -> {
                    exitNumberState();
                    result.add(close);
                }
                default -> {
                    if ('0' <= c && c <= '9') {
                        enterNumberState(c);
                    }
                }
            }
        }
        exitNumberState();
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