package parser;

public class DivisionNode extends OperatorNode implements AstNode{

    public DivisionNode(AstNode left, AstNode right) {
        super(left, right);
    }

    @Override
    public int eval() {
        return left.eval() / right.eval();
    }
}
