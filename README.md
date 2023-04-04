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

Status: Completed

The parser is the part of the program that calculates the equation. Once the lexer has finished its read(String line) method, it will return a List<Token> for the parser to calculate. The parser converts all the List into a tree of the Tokens' corresponding AstNodes and then evaluates the result.
  
#### Methods:
- **public AstNode parse(List\<Token\> tokens)**
  
  Parses a List of Tokens using recursive procedures, building a virtual tree of AstNodes. Returns the top-level AstNode who's eval() method would start a chain of calculations.

  
### ===AstNodes===
Located: "calculator_project/calculator/src/parser/Nodes

Status: Completed

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
When the parser receives the List of Tokens in its parse() method, the parser first checks if the List is of length 1; that the List contains a singular token. If this is the case, it returns a new NumberNode with the value of the singular token. Otherwise, it then loops through the List, checking for operator tokens. Once the parser comes across one, it will split the List at the point of the token, and return a new AstNode of the corresponding type as the operator token, whose left and right values are parse() calls to the left and right sections of the split List. Now, if this new AstNode's eval() method would be called, it would eval the two other AstNodes that the parse() calls generated, which in-turn would return their eval() values all the way until the child Nodes are NumberNodes, where they would return their number. Then the operations of each OperatorNode are applied to the values returned by their child OperatorNodes or NumberNodes and the final result is returned.

 The process in which the parser loops through the List checking for tokens commences in three separate loops. Each loop checks for a layer in the Order of Operations. They occur in the  following order: Parentheses checks, Addition & Subtraction checks, and finally Multiplication & Division checks.

The reason why Addition and Subtraction are checked before Multiplication & Division is because the tree process calculates from the bottom up, so the higher layers in the Order of Operations should be placed in a lower level than lower layers.

Parentheses tokens are not converted into AstNodes. Instead, they are checked for their partner parentheses, and the tokens in between the indices of the two tokens are parsed as a new, separate List and replaced with a new NumberToken with the value of the AstNode of the parse() call.