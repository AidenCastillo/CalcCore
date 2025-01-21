package com.github.aidencastillo.calccore.math;

import com.github.aidencastillo.calccore.CalcCore;
import com.github.aidencastillo.calccore.CalcCoreException;

import java.util.Stack;

import static com.github.aidencastillo.calccore.parser.ExpressionParser.isFunction;
import static com.github.aidencastillo.calccore.parser.ExpressionParser.isNumeric;

public class ExpressionSolver {
    public static double solve(String[] rpn) throws CalcCoreException {
        try {
            Stack<Double> stack = new Stack<>();

            for (String token : rpn) {
                if (isNumeric(token)) {
                    stack.push(Double.parseDouble(token));
                } else if (isFunction(token)) {
                    double opperand = stack.pop();
                    stack.push(applyFunction(opperand, token));
                }
                else {
                    double opperand2 = stack.pop();
                    double opperand1 = stack.pop();
                    stack.push(applyOperator(opperand1, opperand2, token));
                }
            }

            return stack.pop();
        } catch (Exception e) {
            throw new CalcCoreException("Error during evaluation: " + e.getMessage(), e);
        }
    }

    private static Double applyOperator(double opperand1, double opperand2, String token) throws CalcCoreException {
        switch (token) {
            case "+":
                return opperand1 + opperand2;
            case "-":
                return opperand1 - opperand2;
            case "*":
                return opperand1 * opperand2;
            case "/":
                return opperand1 / opperand2;
            default:
                throw new CalcCoreException("Invalid operator: " + token);
        }
    }

    private static Double applyFunction(double opperand, String token) throws CalcCoreException {
        java.util.function.Function <Double, Double> function = CalcCore.getFunctions().get(token);
        if (function != null) {
            return function.apply(opperand);
        } else {
            throw new CalcCoreException("Invalid function: " + token);
        }
    }
}
