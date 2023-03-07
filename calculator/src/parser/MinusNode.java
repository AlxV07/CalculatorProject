package parser;

public class MinusNode extends OperatorNode implements AstNode{

    public MinusNode(AstNode left, AstNode right) {
        super(left, right);
    }

    @Override
    public int eval() {
        return left.eval() - right.eval();
    }
}
