# RPN Calculator
The RPN Calculator is project based on creating backend source code for a emulated version of a reverse polish notation calculator.  This RPN Calculator is a work in progress and should be checked regularly for updates.

## Goals/ Overview
* Develop a "reverse polish notation programmers calculator" engine/library. 
* The engine is designed to be used in future code that would implement a graphical calculator or command line tool. 
* Some form of front-end might be useful but is entirely optional.
* Test driven development, write tests first and the implementation afterwards!
* Use git/github to show progression from test to implementation.
* Focus on well written code that is adaptable and adheres to the book Clean Code by Robert C. Martin
* naming, functions, objects and data, wrappers, logging, exception handling, etc.

## Specification
An RPN calculator is stack-based. Numbers are pushed onto the stack and operations then operate on the numbers that are in the stack and may pop one or more numbers to use in their calculation. This is a *fixed-precision calculator* that should mirror how different bit-size computers work. That means, if the word-size is 8 bits, all calculations should be done as if they were being done with only 8 bits to work with. E.g. 150 + 150 = 300 which is too large for an 8-bit word size and causes overflow and a result of 44.

## Features
* The RPN Calulator can be initilized with a wordsize that ranges from 4-64 and is a multiple of 4.  Upon initialization the calulator will be in unsigned mode and decimal number mode.

    Ex: RPNCalulator calc = new RPNCalculator(16):

* Standard RPN Calculator funcitonality which offers basic operations and evaluates commands upon the command excecute(String Operation).

    Ex: calc.execute("+"):
	
* Valid methods for RPNCalculator Object

  * void push(String value): Adds string value to stack.
    * May set overflow and truncate value if it is greater than the maximum value designated by the wordsize.
  * String pop(): Returns the string on the top of stack.
  * String peek(int location): Returns string at location from top(0)of stack.
  * void execute("String Operation): Executes the operation on the top value/s stored on the stack.
  * void clear(): Removes everthing from the stack and sets the overflow flag to "o".
  * int size(): Returns the number of elements on stack.
  * String flag(int flagIndex): Returns the flag at specified flagIndex.
    * Currently there are three flags. An overflow flag stored at index 0, a signedbit flag stored at index 1 and a numberBaseFlag stored at index 2.
    * The overflow flag is initialized to "o" and set to "O" when there is an overflow
    * The signedbit flag is initialized to "s" for unsigned and alternates between this and "S" when changeSign() is executed.
    * The numberBaseFlag is initialized to "D" for decimal numbers. 

  * void changeSign(): Changes the calculator from using unsigned numbers to signed numbers or signed numbers to unsigned numbers.
    * All values currently stored on the stack are cleared.
    * The signed bit flag is changed between "s" for unsigned and "S" for signed.
    * If a value is entered that is greater than the maximum possible value that can be stored the overflow flag is set to "O" and the number is truncated and set to its binary equivalent.
    
        Ex: If the wordsize of the calculator is set to 4 then the maximum value that can be stored is 7.  If 8 is pushed onto the stack then the overflow flag is set and the value -8 will be pushed onto the stack.
    * If a value is entered that is less than the minimum possible value that can be stored an exception is thrown.

  * changeBase(String numberBaseFlag): Changes the number base that the calculator is operating in.
    * Valid numberBaseFlags are:
      * "D" Decimal Numbers
        * Format for calculator entry: 123 
      * "B" Binary Numbers
        * Format for calculator entry: 0b10011
      * "O" Octal Numbers
        * Format for calculator entry: 05721
      * 'H" Hexidecimal Numbers
        * Format for calculator entry: 0x42dc2f
    * The number base can be changed at any time during any operation.  
    * This will change the format that strings appear when peek() and pop() are called.
    * Numbers will need to be entered with their cooresponding format in order for the calculator to work properly.
    * The two's complement form will be used to represent negative numbers when the calculator is in Signed mode. 

* Valid operations for the execute command
  * Arithmatic: "+", "-", "*", "/", "%"
  * Bitwise: "&", "|", "^", "~", "<<", ">>"	
  * Relational: "==", "!=", ">", "<", ">=", "<="
  * Logical: "&&", "||", "!"
  * "(c)lear": Removes all elements from stack.
  * "(s)ize": Pushes number of elemnts on stack to stack.
  * "(d)uplicate": Duplicatse the top item on the stack.
  * "(r)everse": Swaps top two stack values.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## Author
Taylor Wilk

## License
[Beerware](https://en.wikipedia.org/wiki/Beerware)