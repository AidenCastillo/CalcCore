package com.github.aidencastillo.calccore;

import com.github.aidencastillo.calccore.math.MathFunctions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestMathFunction {

    @Test
    public void testAddition() {
        assertEquals(4, MathFunctions.add(  2, 2));
    }

    @Test
    public void testSubtraction() {
        assertEquals(0, MathFunctions.subtract(2, 2));
    }
}
