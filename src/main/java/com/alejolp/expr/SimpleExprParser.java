package com.alejolp.expr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Self-contained pure-Java simple expressions parser.
 * 
 * Examples:
 * 
 * 2 + 2
 * 2 + 3 * 4
 * (2 + 3) * 4
 * 
 */
public class SimpleExprParser {
	private static final Set<String> DIGITS = new HashSet<String>(Arrays.asList(new String[] {
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."}));

	private static final Set<String> SYMBOLS = new HashSet<String>(Arrays.asList(new String[] {
			"+", "-", "*", "/", "(", ")"}));

	public static List<String> tokenize(String expr) {
		List<String> result = new ArrayList<String>();
		
		for (int p = 0; p < expr.length(); ) {
			String c = expr.substring(p, p + 1);
			
			if (c.equals(" ")) {
				// Whitespace, ignored
				++p;
			} else if (c.equals("-") || DIGITS.contains(c)) {
				// It's a digit
				int start = p;
				
				while (p < expr.length()) {
					c = expr.substring(p, p + 1);
					if (p == start && c.equals("-")) {
						p++;
					} else if (DIGITS.contains(c)) {
						p++;
					} else {
						break;
					}
				}
				
				result.add(expr.substring(start, p));
			} else if (SYMBOLS.contains(c)) {
				result.add(c);
				++p;
			} else {
				throw new RuntimeException("Unknown symbol " + c + " at " + p);
			}
		}
		
		return result;
	}
	
	private static class ParseResult {
		public ParseResult(double value, int pos) {
			this.value = value;
			this.pos = pos;
		}
		
		public double value;
		public int pos;
	}
	
	private static ParseResult parse_value(List<String> tokens, int start) {
		double d = Double.valueOf(tokens.get(start));
		return new ParseResult(d, start + 1);
	}
	
	private static ParseResult parse_factor(List<String> tokens, int start) {
		if (tokens.get(start).equals("-")) {
			ParseResult result0 = parse_factor(tokens, start + 1);
			return new ParseResult((-1) * result0.value, result0.pos);
		} else if (tokens.get(start).equals("(")) {
			ParseResult result0 = parse_expr(tokens, start + 1);
			int next_pos = result0.pos;
			if (!tokens.get(next_pos).equals(")")) {
				throw new RuntimeException("Missing ) at " + next_pos);
			}
			return new ParseResult(result0.value, next_pos + 1);
		} else {
			return parse_value(tokens, start);
		}
	}
	
	private static ParseResult parse_term(List<String> tokens, int start) {
		ParseResult result0 = parse_factor(tokens, start);
		int next_pos = result0.pos;
		double v = result0.value;
		
		while (next_pos < tokens.size()) {
			if (tokens.get(next_pos).equals("*")) {
				ParseResult result1 = parse_factor(tokens, next_pos + 1);
				v = v * result1.value;
				next_pos = result1.pos;
			} else if (tokens.get(next_pos).equals("/")) {
				ParseResult result1 = parse_factor(tokens, next_pos + 1);
				v = v / result1.value;
				next_pos = result1.pos;
			} else {
				break;
			}
		}
		
		return new ParseResult(v, next_pos);
	}
	
	private static ParseResult parse_expr(List<String> tokens, int start) {
		ParseResult result0 = parse_term(tokens, start);
		int next_pos = result0.pos;
		double v = result0.value;
		
		while (next_pos < tokens.size()) {
			if (tokens.get(next_pos).equals("+")) {
				ParseResult result1 = parse_term(tokens, next_pos + 1);
				v = v + result1.value;
				next_pos = result1.pos;
			} else if (tokens.get(next_pos).equals("-")) {
				ParseResult result1 = parse_term(tokens, next_pos + 1);
				v = v - result1.value;
				next_pos = result1.pos;
			} else {
				break;
			}
		}
		
		return new ParseResult(v, next_pos);
	}
	
	public static double parse(List<String> tokens) {
		/*
		 * Grammar: (the {...} is 0+ repetition)
		 * 
		 * expr: term | term { + term } | term { - term }
		 * 
		 * term: factor | factor { * factor } | factor { / factor } 
		 * 
		 * factor: - ( expr ) | ( expr ) | value
		 * 
		 */
		int start = 0;
		ParseResult result0 = parse_expr(tokens, start);
		
		if (result0.pos == tokens.size()) {
			return result0.value;
		} else {
			throw new RuntimeException("Parse error! unknown");
		}
	}
}
