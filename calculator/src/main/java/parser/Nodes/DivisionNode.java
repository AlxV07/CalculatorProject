package parser.Nodes;

public class DivisionNode extends OperatorNode implements ASTNode {

    public DivisionNode(ASTNode left, ASTNode right) {
        super(left, right);
    }

    @Override
    public int eval() {
        return left.eval() / right.eval();
    }
}
