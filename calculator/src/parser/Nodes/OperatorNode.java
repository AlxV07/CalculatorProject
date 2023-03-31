package parser.Nodes;

public abstract class OperatorNode implements AstNode {
    protected final AstNode left;
    protected final AstNode right;

    public OperatorNode(AstNode left, AstNode right) {
        this.left = left;
        this.right = right;
    }
}
