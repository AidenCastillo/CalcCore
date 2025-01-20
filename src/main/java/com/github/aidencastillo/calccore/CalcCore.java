package com.github.aidencastillo.calccore;

import com.github.aidencastillo.calccore.parser.ExpressionParser;
import com.github.aidencastillo.calccore.math.ExpressionSolver;

public class CalcCore {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static double evaluate(String expression) throws Exception {
        if (expression == null || expression.isEmpty()) {
            throw new CalcCoreException("Invalid expression");
        }

        String[] rpn = ExpressionParser.parse(expression);

        return ExpressionSolver.solve(rpn);
    }

}