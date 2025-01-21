package com.github.aidencastillo.calccore;

import com.github.aidencastillo.calccore.parser.ExpressionParser;
import com.github.aidencastillo.calccore.math.ExpressionSolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CalcCore {
    private static Map<String, Function<Double, Double>> functions = new HashMap<>();

    static {
        functions.put("sin", Math::sin);
        functions.put("cos", Math::cos);
        functions.put("tan", Math::tan);
    }

    public static Map<String, Function<Double, Double>> getFunctions() {
        return functions;
    }

    public static void addFunction(String name, Function<Double, Double> implementation) {
        functions.put(name, implementation);
    }

    public static void removeFunction(String name) {
        functions.remove(name);
    }

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

    public static double evaluate(String expression, double x) throws Exception {
        if (expression == null || expression.isEmpty()) {
            throw new CalcCoreException("Invalid expression");
        }

        String[] rpn = ExpressionParser.parse(expression);

        for (int i = 0; i < rpn.length; i++) {
            if (rpn[i].equals("x")) {
                rpn[i] = String.valueOf(x);
            }
        }

        return ExpressionSolver.solve(rpn);
    }

}