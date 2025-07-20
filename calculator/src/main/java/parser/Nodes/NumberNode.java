package parser.Nodes;

public record NumberNode (int value) implements ASTNode {
    @Override
    public int eval() {
        return value;
    }
}
