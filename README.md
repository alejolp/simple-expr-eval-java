
Self-contained pure-Java simple expressions parser, implemented using a recursive descent hand-crafted parser in ~150 lines of code.

Given a string `2 + 3 * 4` it returns `14` and `(2 + 3) * 4` gives `20`.  The parser also has the unary minus `-` which can be tricky to get for things like `10 / -(-(2)))`. The parser is interesting because it's done as a recursive descent parser code which is much simpler to understand, follow and debug than the stack parsing algorithm (Shunting-yard algorithm). This project also has a working `pom.xml` for Spring Boot and JUint 5.

The recursive code follows the EBNF grammar. The EBNF grammar is:

    Grammar: (the {...} means 0+ repetition)
    
    expr: term | term { + term } | term { - term }
    
    term: factor | factor { * factor } | factor { / factor } 
    
    factor: - factor | ( expr ) | value
 
This parser assumes that expressions are always valid. Thus, error detection is done with RuntimeException, NullPointerException or Stack Overflows.  

See the `SimpleExprParserTest` class for tests and examples.

