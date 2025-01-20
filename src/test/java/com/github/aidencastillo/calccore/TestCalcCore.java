package com.github.aidencastillo.calccore;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCalcCore {

    @Test
    public void testEvaluateAddition() throws Exception {
        assertEquals(7.0, CalcCore.evaluate("3 + 4"), 1e-9);
    }

    @Test
    public void testEvaluateSubtraction() throws Exception {
        assertEquals(-1.0, CalcCore.evaluate("3 - 4"), 1e-9);
    }

    @Test
    public void testEvaluateMultiplication() throws Exception {
        assertEquals(12.0, CalcCore.evaluate("3 * 4"), 1e-9);
    }

    @Test
    public void testEvaluateDivision() throws Exception {
        assertEquals(0.75, CalcCore.evaluate("3 / 4"), 1e-9);
    }

    @Test
    public void testEvaluateEmptyExpression() {
        assertThrows(CalcCoreException.class, () -> CalcCore.evaluate(""));
    }

    @Test
    public void testEvaluateNullExpression() {
        assertThrows(CalcCoreException.class, () -> CalcCore.evaluate(null));
    }
}
