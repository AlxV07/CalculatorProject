# Calculator Project
# Table of Contents:
1. Goal
2. Project Summary
3. Detailed Overview
   1. Lexer
   2. Tokens
   3. Parser
   4. AstNodes
   5. AstNode Tree Concept

## Goal:
To create a calculator that can parse and solve arithmetic problems with Parentheses, according to the Order of Operations.
## Project Summary:
This calculator is split into two components, a lexer and a parser. The lexer takes a valid mathematical equation as a String and converts it into a List of Tokens, which is then fed into the parser. The parser then converts each token into an AstNode, and constructs a "tree" of Number and Operator Nodes. Once it has done this, it calculates the "tree" and returns the result.

So far, I have written Tokens for the Numbers, and Plus & Minus operators. I have written and tested the Lexer for these Tokens. Ast Nodes for Number, Plus, and Minus have also been written, and the Parser is currently a work in progress.

## Detailed Overview:

### ===Lexer===
Located: "calculator_project/calculator/src/lexer/Lexer.java"

Status: Functional, Need to program and implement Multiplication, Division, & Parentheses Tokens

The lexer is the part of the program that takes the equation and converts it into a readable format for the parser to calculate. It does this by converting each symbol and number in the equation into a Token (see below). The lexer will return the translated equation in the form of a List<Token> via the read(String line) method. 

#### Variables:
- **List<Token> result = new ArrayList<>()**

  The List of Tokens which will be returned once read() is executed.
- **String numberAccumulator = ""**

  This variable is used when the lexer finds multi-digit numbers; to hold their value until the entire number is read.
  
#### Methods:
- **public List<Token> read(String line)**

  This method takes the input "line" and uses an enhanced for loop to iterate through the String. For each character, a switch statement is used to determine what to do with the character. If the character is a new line (\n), a space ( ), or a tab (\t), the program will exitNumberState() and break out of the switch statement. If the character is an operator symbol ("+", "-" are currently functional), the program will add the getInstance() of the operator's token to the "result" List. If the character is a number, the program will enterNumberState(the number).
- **private void exitNumberState()**

  Checks if "numberAccumulator" is blank; if not, it adds "numberAccumulator" to the "result" List, then sets "numberAccumulator" to blank.
- **private void enterNumberState(char c)**

  Adds the character "c" to "numberAccumulator".

### ===Tokens===
Located: calculator_project/calculator/src/lexer/Tokens

Status: Functional, Need to create Multiplication, Division, & Parentheses Tokens

A Token is a representation of a mathematical symbol or number. OperatorTokens represent their respective operators; likewise, NumberTokens represent their given number.

#### Methods:
- **public TokenType getType()** 

  Returns the Token's type; i.e. TokenType.PLUS, TokenType.NUMBER
  
### ===Parser===
Located: "calculator_project/calculator/src/parser/Parser.java

Status: Work In Progress

The parser is the part of the program that calculates the equation. Once the lexer hs finished its read(String line) method, it will give a List<Token> to the parser to calculate. The parser converts all the Tokens into their corresponding AstNodes and then calculates the result.
#### Variables:
- Work in progress; none so far.
  
#### Methods:
- **public AstNode parse(List<Token> tokens)**
  Currently does nothing, concept described below (See AstNode Tree Concept).

  
### ===AstNodes===
Located: "calculator_project/calculator/src/parser

Status: Work In Progress

  #### Implementations: 
- **NumberNode**
  - public record NumberNode(int value) implements AstNode
  - The NumberNode is assigned the "value" Integer when it is created by the parser. This is the number that it represents when the calculation begins.
  
- **OperatorNode**
  - public abstract class OperatorNode implements AstNode 
  - The OperatorNode has two AstNode variables; left & right. When it is created, the left & right Nodes are assigned to their respective AstNodes.

  
#### Methods:
- **public int eval()** 
  If the AstNode is an OperatorNode, it returns left.eval() + right.eval()
  If the AstNode is a NumberNode, it returns "value"

### ===AstNode Tree Concept===
This is what I will be programming the parser to do with the List of Tokens it will receive:

#### Equation -> Singular OperatorNode
First, the parser will change all the Number Tokens into NumberNodes. Then, it will it scan the List for any Parentheses. If there are any, it will start converting those into AstNodes. Once the Parentheses and everything between them have been translated, the parser will continue in this manner down the Order of Operations, scanning for Multiplication and Division, and lastly Addition and Subtraction.

When the parser locates a Token, it checks the getType() method and follows these orders: 
- If the Token is a Number Token, the parser will replace the Token with a new NumberNode, with the same value as the Number Token. 
- If the Token is a Parentheses, the parser will repeat this process except with only the section of the equation in between the Parentheses. Once completed, the parser will remove both Parentheses.
- If the Token is an operator,  it will replace the Token with a new OperatorNode, with its AstNode variables "left" & "right" being set to the left and right AstNodes of the Token. 

Here is an example of how the parser would "parse" the equation: 1+2*3
- Step 1: When the parser receives this equation from the lexer, it would look something like this: 
  
  _**NumberToken(1),PlusToken,NumberToken(2),MultiplyingToken,NumberToken(3)**_

  The parser would first convert all the NumberTokens into NumberNodes with their corresponding values, resulting in the List becoming:

  _**NumberNode(1),PlusToken,NumberNode(2),MultiplyingToken,NumberNode(3)**_

- Step 2: The parser then scans for Parentheses; finding none, it moves onto Multiplication and locates the MultiplicationToken. It then alters the List to become:

  _**NumberNode(1),PlusToken,MultiplyingNode(NumberNode(2),NumberNode(3))**_

- Step 3: Finally, the parser locates and converts the PlusToken into a PlusNode, and we are left with this:

  _**PlusNode(NumberNode(1), MultiplyingNode(NumberNode(2), NumberNode(3)))**_

  As you can see, there is only one AstNode left in the list; the PlusNode. Everything else from the equation is inside this PlusNode; all the NumberNodes, two of which are in the MultiplyingNode. This PlusNode is what the parse(List<Token> tokens) method would return.

#### Singular OperatorNode -> Single Number (The Result)
Now that we have this tree sprouting out from the PlusNode, how do we calculate it? All we have to do is to run the eval() method of the PlusNode once in order for it to start a chain reaction that will calculate everything else. If we take a look at the eval() method for an OperatorNode, it returns: left.eval() + right.eval(). This means that it will return the sum of the eval() of the "left" and "right" AstNodes. 

Here is how what it would do if we were to run this PlusNode.eval():
- Step 1: Firstly, the PlusNode will get its left.eval() + right.eval(), which would be this:

  _**PlusNode.eval()=NumberNode(1).eval()+MultiplyingNode(NumberNode(2),NumberNode(3)).eval()**_
- Step 2: Next, since the "right" AstNode is a MultiplyingNode, it will return its left.eval() times its right.eval(). This would look like:

  _**PlusNode.eval()=NumberNode(1)+MultiplyingNode.eval()=NumberNode(2).eval()*NumberNode(3).eval()**_
- Step 3: Now, replacing the NumberNodes with the numbers they represent, the program will calculate:
  
  _**PlusNode.eval() = NumberNode(1)+2*3 = 1+6 = 7**_

And the result will be 7.