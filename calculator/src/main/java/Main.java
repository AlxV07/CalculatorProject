import lexer.Lexer;
import parser.Nodes.ASTNode;
import parser.Parser;

public class Main {

    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        Parser parser = new Parser();
        ASTNode astNode = parser.parse(lexer.read("((((3 + (10 / 5)) * 3) * 3)) + ((((5 * (3 - 2)))))"));
        System.out.println(astNode.eval());
    }
}
