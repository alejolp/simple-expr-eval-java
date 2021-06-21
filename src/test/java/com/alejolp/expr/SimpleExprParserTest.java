package com.alejolp.expr;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SimpleExprParserTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    @Test
    public void tokensTest1() {
    	List<String> tokens0 = SimpleExprParser.tokenize("2 + 3");
    	assertEquals(tokens0.size(), 3);
    	assertEquals(tokens0.get(0), "2");
    	assertEquals(tokens0.get(1), "+");
    	assertEquals(tokens0.get(2), "3");
    }
    
    @Test
    public void tokensTest2() {
    	List<String> tokens0 = SimpleExprParser.tokenize("(2+3)");
    	assertEquals(tokens0.size(), 5);
    	assertEquals(tokens0.get(0), "(");
    	assertEquals(tokens0.get(1), "2");
    	assertEquals(tokens0.get(2), "+");
    	assertEquals(tokens0.get(3), "3");
    	assertEquals(tokens0.get(4), ")");
    }

    @Test
    public void tokensTest3() {
    	List<String> tokens0 = SimpleExprParser.tokenize("2.0 + 3.0");
    	assertEquals(tokens0.size(), 3);
    	assertEquals(tokens0.get(0), "2.0");
    	assertEquals(tokens0.get(1), "+");
    	assertEquals(tokens0.get(2), "3.0");
    }

    @Test
    public void testSimpleExpr1() {
    	List<String> tokens0 = SimpleExprParser.tokenize("2 + 3");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 5.0, 1e-15);
    }

    @Test
    public void testSimpleExpr2() {
    	List<String> tokens0 = SimpleExprParser.tokenize("2 - 3");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, -1.0, 1e-15);
    }

    @Test
    public void testSimpleExpr3() {
    	List<String> tokens0 = SimpleExprParser.tokenize("2 * 3");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 6.0, 1e-15);
    }

    @Test
    public void testSimpleExpr4() {
    	List<String> tokens0 = SimpleExprParser.tokenize("4 / 2");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 2.0, 1e-15);
    }

    @Test
    public void testSimpleExpr5() {
    	List<String> tokens0 = SimpleExprParser.tokenize("2 + 3 * 4");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 14.0, 1e-15);
    }

    @Test
    public void testSimpleExpr6() {
    	List<String> tokens0 = SimpleExprParser.tokenize("2 * 3 + 4");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 10.0, 1e-15);
    }

    @Test
    public void testSimpleExpr7() {
    	List<String> tokens0 = SimpleExprParser.tokenize("(2 + 3) * 4");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 20.0, 1e-15);
    }

    @Test
    public void testSimpleExpr8() {
    	List<String> tokens0 = SimpleExprParser.tokenize("2 * (3 + 4)");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 14.0, 1e-15);
    }

    @Test
    public void testSimpleExpr9() {
    	List<String> tokens0 = SimpleExprParser.tokenize("2 + -2");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 0.0, 1e-15);
    }

    @Test
    public void testSimpleExpr10() {
    	List<String> tokens0 = SimpleExprParser.tokenize("2 + (-2)");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 0.0, 1e-15);
    }

    @Test
    public void testSimpleExpr11() {
    	List<String> tokens0 = SimpleExprParser.tokenize("2 - (-2)");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 4.0, 1e-15);
    }

    @Test
    public void testSimpleExpr12() {
    	List<String> tokens0 = SimpleExprParser.tokenize("2 + 2 + 2 + 2 + 2");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 10.0, 1e-15);
    }

    @Test
    public void testSimpleExpr13() {
    	List<String> tokens0 = SimpleExprParser.tokenize("2 - 2 + 2 - 2 + 2");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 2.0, 1e-15);
    }

    @Test
    public void testSimpleExpr14() {
    	List<String> tokens0 = SimpleExprParser.tokenize("2 * 2 * 2 * 2 * 2");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 32.0, 1e-15);
    }

    @Test
    public void testSimpleExpr15() {
    	// test left associativity expression 
    	List<String> tokens0 = SimpleExprParser.tokenize("2 / 2 / 2 / 2 / 2");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 0.125, 1e-15);
    }

    @Test
    public void testSimpleExpr16() {
    	// test left associativity expression
    	List<String> tokens0 = SimpleExprParser.tokenize("4 / 2 * 7");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 14.0, 1e-15);
    }

    @Test
    public void testSimpleExpr17() {
    	// test floating point number
    	List<String> tokens0 = SimpleExprParser.tokenize("1.5 + 1.5");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 3.0, 1e-15);
    }
    
    @Test
    public void testMinusExpr1() {
    	List<String> tokens0 = SimpleExprParser.tokenize("2 + -(-2)");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 4.0, 1e-15);
    }

    @Test
    public void testMinusExpr2() {
    	List<String> tokens0 = SimpleExprParser.tokenize("2 + -(-(-2))");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, 0.0, 1e-15);
    }

    @Test
    public void testMinusExpr3() {
    	List<String> tokens0 = SimpleExprParser.tokenize("10 / -(-(-2))");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, -5.0, 1e-15);
    }

    @Test
    public void testMinusExpr4() {
    	List<String> tokens0 = SimpleExprParser.tokenize("10 * -(-(-2))");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, -20.0, 1e-15);
    }

    @Test
    public void testMinusExpr5() {
    	List<String> tokens0 = SimpleExprParser.tokenize("10 / -2");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, -5.0, 1e-15);
    }

    @Test
    public void testMinusExpr6() {
    	List<String> tokens0 = SimpleExprParser.tokenize("10 / - 2");
    	double d = SimpleExprParser.parse(tokens0);
    	assertEquals(d, -5.0, 1e-15);
    }

}
