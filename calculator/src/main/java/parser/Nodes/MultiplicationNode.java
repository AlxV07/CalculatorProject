package parser.Nodes;

public class MultiplicationNode extends OperatorNode implements AstNode{

    public MultiplicationNode(AstNode left, AstNode right) {
        super(left, right);
    }

    @Override
    public int eval() {
        return left.eval() * right.eval();
    }
}
