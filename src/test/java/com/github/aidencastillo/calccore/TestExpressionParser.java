package com.github.aidencastillo.calccore;

import com.github.aidencastillo.calccore.parser.ExpressionParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestExpressionParser {

    @Test
    public void testTokenizeSimpleExpression() {
        String[] tokens = ExpressionParser.tokenize("3+4");
        assertArrayEquals(new String[] { "3", "+", "4" }, tokens);
    }

    @Test
    public void TestRemoveWhitespace() {
        String[] tokens = ExpressionParser.tokenize("3 + 4");
        assertArrayEquals(new String[] { "3", "+", "4" }, tokens);
    }

    @Test
    public void testTokenizeComplexExpression() {
        String[] tokens = ExpressionParser.tokenize("3 + 5 * (2 - 8)");
        assertArrayEquals(new String[] { "3", "+", "5", "*", "(", "2", "-", "8", ")" }, tokens);
    }

    @Test
    public void testToRPNBasicExpression() throws CalcCoreException {
        String[] tokens = ExpressionParser.tokenize("3 + 4");
        String[] rpn = ExpressionParser.toRPN(tokens);
        assertArrayEquals(new String[] { "3", "4", "+" }, rpn);
    }

    @Test
    public void testToRPNComplexExpression() throws CalcCoreException {
        String[] tokens = ExpressionParser.tokenize("3 + 5 * (2 - 8)");
        String[] rpn = ExpressionParser.toRPN(tokens);
        assertArrayEquals(new String[] { "3", "5", "2", "8", "-", "*", "+" }, rpn);
    }

    @Test
    public void testToRPNExpressionWithParentheses() throws CalcCoreException {
        String[] tokens = ExpressionParser.tokenize("3 + (5 * 2) - 8");
        String[] rpn = ExpressionParser.toRPN(tokens);
        assertArrayEquals(new String[] { "3", "5", "2", "*", "+", "8", "-" }, rpn);
    }

    @Test
    public void testToRPNExpressionWithMultipleParentheses() throws CalcCoreException {
        String[] tokens = ExpressionParser.tokenize("3 + (5 * (2 - 8))");
        String[] rpn = ExpressionParser.toRPN(tokens);
        assertArrayEquals(new String[] { "3", "5", "2", "8", "-", "*", "+" }, rpn);
    }

    @Test
    public void testToRPNExpressionWithMultipleParentheses2() throws CalcCoreException {
        String[] tokens = ExpressionParser.tokenize("3 + (5 * (2 - 8)) + 4");
        String[] rpn = ExpressionParser.toRPN(tokens);
        assertArrayEquals(new String[] { "3", "5", "2", "8", "-", "*", "+", "4", "+" }, rpn);
    }

    @Test
    public void testParseWithInvalidExpression() {
        assertThrows(CalcCoreException.class, () -> ExpressionParser.parse("3 + (5 * 2"));
    }
}
