package parser.Nodes;

public class MinusNode extends OperatorNode implements ASTNode {

    public MinusNode(ASTNode left, ASTNode right) {
        super(left, right);
    }

    @Override
    public int eval() {
        return left.eval() - right.eval();
    }
}
