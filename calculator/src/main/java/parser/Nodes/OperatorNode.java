package parser.Nodes;

public abstract class OperatorNode implements ASTNode {
    protected final ASTNode left;
    protected final ASTNode right;

    public OperatorNode(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }
}
