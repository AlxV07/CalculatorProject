package parser.Nodes;

public class PlusNode extends OperatorNode implements ASTNode {

    public PlusNode(ASTNode left, ASTNode right) {
        super(left, right);
    }

    @Override
    public int eval() {
        return left.eval() + right.eval();
    }
}
