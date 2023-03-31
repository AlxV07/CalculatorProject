package parser.Nodes;

public record NumberNode (int value) implements AstNode {
    @Override
    public int eval() {
        return value;
    }
}
