import lexer.Lexer;

public class Main {

    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        lexer.read("1+1");
    }
}
