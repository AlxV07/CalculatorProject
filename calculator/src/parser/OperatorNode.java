package parser;

public abstract class OperatorNode implements AstNode {
    protected AstNode left;
    protected AstNode right;

    public OperatorNode(AstNode left, AstNode right) {
        this.left = left;
        this.right = right;
    }

    public void setLeft(AstNode left) {
        this.left = left;
    }

    public void setRight(AstNode right) {
        this.right = right;
    }
}
