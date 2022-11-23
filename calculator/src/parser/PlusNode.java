package parser;

public class PlusNode extends OperatorNode implements AstNode{

    public PlusNode(AstNode left, AstNode right) {
        super(left, right);
    }

    @Override
    public int eval() {
        return left.eval() + right.eval();
    }
}
