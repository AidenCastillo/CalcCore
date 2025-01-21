package com.github.aidencastillo.calccore.math;

import com.github.aidencastillo.calccore.CalcCoreException;
import com.github.aidencastillo.calccore.parser.ExpressionParser;

import java.util.HashMap;
import java.util.Map;

public class Expression {
    private String expression;
    private Map<String, Double> constants;
    private Map<String, Function> functions;

    public Expression(String expression) {
        this.expression = expression;
        this.constants = new HashMap<>();
        this.functions = new HashMap<>();
    }

    public Expression(String expression, Function f) {
        this.expression = expression;
        this.constants = new HashMap<>();
        this.functions = new HashMap<>();
        this.functions.put(f.getName(), f);
    }

    public void addConstant(String name, double value) {
        constants.put(name, value);
    }

    public void addFunction(String name, Function function) {
        functions.put(name, function);
    }

    public double evaluate() throws CalcCoreException {
        String[] rpn = ExpressionParser.parse(expression);

        for (int i = 0; i < rpn.length; i++) {
            if (constants.containsKey(rpn[i])) {
                rpn[i] = String.valueOf(constants.get(rpn[i]));
            }
        }

        return ExpressionSolver.solve(rpn);
    }

    public void setVariable(String key, Double value) {
    }
}
