package parser.Nodes;

public class MultiplicationNode extends OperatorNode implements ASTNode {

    public MultiplicationNode(ASTNode left, ASTNode right) {
        super(left, right);
    }

    @Override
    public int eval() {
        return left.eval() * right.eval();
    }
}
