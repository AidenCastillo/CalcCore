package com.github.aidencastillo.calccore;

import com.github.aidencastillo.calccore.math.ExpressionSolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestExpressionSolver {

    @Test
    public void testSolveSimpleAddition() throws CalcCoreException {
        assertEquals(4, ExpressionSolver.solve(new String[] { "2", "2", "+" }), 1e-9);
    }

    @Test
    public void testSolveComplexExpression() throws CalcCoreException {
        assertEquals(-27.0, ExpressionSolver.solve(new String[] {"3", "5", "2", "8", "-", "*", "+"}), 1e-9);
    }

    @Test
    public void testSolveComplexExpression2() throws CalcCoreException {
        assertEquals(-23.0, ExpressionSolver.solve(new String[] {"3", "5", "2", "8", "-", "*", "+", "4", "+"}), 1e-9);
    }

    @Test
    public void testSolveWithDivision() throws CalcCoreException {
        assertEquals(2.5, ExpressionSolver.solve(new String[]{"5", "2", "/"}), 1e-9);
    }

    @Test
    public void testSolveWithEmptyRPN() {
        assertThrows(CalcCoreException.class, () -> ExpressionSolver.solve(new String[]{}));
    }

    @Test
    public void testSolveWithInvalidOperator() {
        assertThrows(CalcCoreException.class, () -> ExpressionSolver.solve(new String[]{"3", "5", "#"}));
    }
}
