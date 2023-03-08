import lexer.Lexer;
import parser.AstNode;
import parser.Parser;

public class Main {

    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        Parser parser = new Parser();

        AstNode astNode = parser.parse(lexer.read("1"));
        System.out.println(astNode.eval());
    }
}
