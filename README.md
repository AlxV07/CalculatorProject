# calculator_project

=====Goal=====
To create a calculator that can parse and solve arithmetic problems, according to the Order of Operations.

=====Overview=====
The calculator is split into two components, a lexer and a parser. The lexer takes a valid mathematical equation as a String and converts it into a List of Tokens, which is then fed into the parser. The parser then converts each token into a Node, and constructs a tree of Number and Operator Nodes. Once it has finished parsing the list of Tokens, it calculates the tree and returns the result.

So far, I have written Tokens for the Plus & Minus operators and numbers. I have also written and tested the Lexer for these Tokens. Ast Nodes for Number, Plus, and Minus have been written, and I am currently working on the Parser.

=====Lexer=====
Located: "calculator_project/calculator/src/lexer/Lexer.java"
Status: Functional, Need to add Multiplication & Division Tokens
  =====Variables=====
- List<Token> result = new ArrayList<>()
  The List of Tokens which will be returned once read() is executed.
- String numberAccumulator = ""
  This variable is used when the lexer finds multi-digit numbers; to hold their value until the entire number is read.
  =====Methods=====
- public List<Token> read(String line)
  This method takes the input "line" and uses an enhanced for loop to iterate through the String. For each character, a switch statement is used to determine what to   do with the character. If the character is a new line (\n), a space ( ), or a tab (\t), the program will exitNumberState() and break out of the switch statement.     If the character is an operator symbol ("+", "-" are currently functional), the program will add the .getInstance(); of the operator's token to the result List. If   the character is a number, the program will enterNumberState(current character).
- private void exitNumberState()
  Checks numberAccumulator.isBlank; if not, adds Integer.parseInt(numberAccumulator) to the "result" List, then runs numberAccumulator = "".
- private void enterNumberState(char c)
  Adds the character "c" to "numberAccumulator".
  
=====Parser=====
Located: "calculator_project/calculator/src/parser/Parser.java
Status: Work In Progress
  =====Variables=====
  =====Methods=====
- public AstNode parse(List<Token> tokens)
  Currently does nothing, concept described below.
  The program will iterate through "tokens" and create (conceptually) a "tree" of AstNodes, a single operator on top with two branches, splitting off into either         numbers or other operators. Then, it will return the operator on top. 
  
=====AstNodes=====
Located: "calculator_project/calculator/src/parser
Status: Work In Progress
  =====Implementations=====
   =====NumberNode=====
 - public record NumberNode(int value) implements AstNode
   "value" is the number that the NumberNode will represent when being used in calculations. It is returned in the 
   =====OperatorNode=====
 - public abstract class OperatorNode implements AstNode
   The OperatorNode class 
  
  =====Methods=====
- public int eval() 
  If the AstNode is an OperatorNode, it returns left.eval() + right.eval()
  If the AstNode is a NumberNode, it returns "value"
