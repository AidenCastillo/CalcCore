package com.github.aidencastillo.calccore;

import static org.junit.jupiter.api.Assertions.*;

import com.github.aidencastillo.calccore.math.Expression;
import com.github.aidencastillo.calccore.math.Function;

import org.junit.jupiter.api.Test;

public class TestExpression {

    @Test
    public void testAddition() throws CalcCoreException {
        Expression expression = new Expression("3 + 4");
        assertEquals(7.0, expression.evaluate(), 1e-9);
    }

    @Test
    public void testSubtraction() throws CalcCoreException {
        Expression expression = new Expression("3 - 4");
        assertEquals(-1.0, expression.evaluate(), 1e-9);
    }

    @Test
    public void testFunction() throws CalcCoreException {
        Function f = new Function("f(x) = 3*x");
        Expression expression = new Expression("f(4)", f);

        assertEquals(12.0, expression.evaluate(), 1e-9);
    }

    @Test
    public void testTrigonometricFunction() throws CalcCoreException {
//        Function f = new Function("sin(x)");
//        Expression expression = new Expression("f(0)", f);
        Expression expression = new Expression("sin(0)");
        Expression expression2 = new Expression("cos(0)");
        Expression expression3 = new Expression("tan(0)");

        Function f = new Function("f(x) = sin(x)+3");
        Expression expression4 = new Expression("f(0)", f);

        assertEquals(0.0, expression.evaluate(), 1e-9);
        assertEquals(1.0, expression2.evaluate(), 1e-9);
        assertEquals(0.0, expression3.evaluate(), 1e-9);
        assertEquals(3.0, expression4.evaluate(), 1e-9);
    }

    @Test
    public void testNegativeExpressions() throws CalcCoreException {
        Expression expression = new Expression("-3 + 4");
        Expression expression2 = new Expression("3 + -4");
        Expression expression3 = new Expression("-3 + -4");
        Expression expression4 = new Expression("-3 - 4");
        Expression expression5 = new Expression("3 - -4");
        Expression expression6 = new Expression("-3 - -4");
        Expression expression7 = new Expression("-sin(1) + 4");

        assertEquals(1.0, expression.evaluate(), 1e-9);
        assertEquals(-1.0, expression2.evaluate(), 1e-9);
        assertEquals(-7.0, expression3.evaluate(), 1e-9);
        assertEquals(-7.0, expression4.evaluate(), 1e-9);
        assertEquals(7.0, expression5.evaluate(), 1e-9);
        assertEquals(1.0, expression6.evaluate(), 1e-9);
        assertEquals(3.1585290151921035, expression7.evaluate(), 1e-9);
    }

    @Test
    public void testNegativeFunctions() throws CalcCoreException {
        Function f = new Function("f(x) = -3*x");
        Expression expression = new Expression("f(4)", f);
        Expression expression2 = new Expression("-f(4)", f);
        Expression expression3 = new Expression("f(-4)", f);
        Expression expression4 = new Expression("-f(-4)", f);
        Expression expression5 = new Expression("-f(-4) * (-sin(1))", f);

        assertEquals(-12.0, expression.evaluate(), 1e-9);
        assertEquals(12.0, expression2.evaluate(), 1e-9);
        assertEquals(12.0, expression3.evaluate(), 1e-9);
        assertEquals(-12.0, expression4.evaluate(), 1e-9);
        assertEquals(10.0976518177, expression5.evaluate(), 1e-9);
    }
}
