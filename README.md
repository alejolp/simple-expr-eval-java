
Self-contained pure-Java simple expressions parser, implemented using a recursive descent hand-crafted parser in ~150 lines of code. The recursive code follows the EBNF grammar. The EBNF grammar is:

    Grammar: (the {...} means 0+ repetition)
    
    expr: term | term { + term } | term { - term }
    
    term: factor | factor { * factor } | factor { / factor } 
    
    factor: - factor | ( expr ) | value
 
This parser assumes that expressions are always valid. Thus, error detection is done with RuntimeException, NullPointerException or Stack Overflows.  

See the `SimpleExprParserTest` class for tests and examples.

