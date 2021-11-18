
# Calculator
 #### This library stores the implementation of a simple calculator based on the Reverse Polish Notation.
 #### The expression is translated from the infix to the postfix record and calculated.
 #### All negative numbers must be entered in brackets.
 #### Input examples: `(-1)^0-5`, `(-3)*(-7)*5`, `-3+(-5)*(6-7)`.
 
 # Supported operations
 ####
 `+` addition
 `-` subtraction
 `*` multiplication
 `/` division
 `^` exponentation

 # Functions
 #### From infix to postfix
`toPostfix(Expression)` returns postfix version of the expression
 #### Calculate postfix expression
 `calculate(Expression)` calculates result of the expression
 #### Get priority of the operator
`priority(Operator)` returns operators priority
 #### Brackets
`checkBrackets(Expression)` checks the correct placement of brackets
 #### Changing the unary cons(auxiliary function)
 `addZeros(Expression)` adds zeros to the unary cons in the expression
 
 # Exceptions 
 #### EmptyStackException
 It comes out when an expression is entered incorrcetly. For example, unnecessary operator.
 #### ArithmeticException
 Occures due to division by zero.
