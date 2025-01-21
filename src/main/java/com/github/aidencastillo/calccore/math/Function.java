package com.github.aidencastillo.calccore.math;

import com.github.aidencastillo.calccore.CalcCore;
import com.github.aidencastillo.calccore.CalcCoreException;
import com.github.aidencastillo.calccore.parser.ExpressionParser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function {
    private String name;
    private String expression;
    private String variable;
    private boolean isBuiltIn;
    private java.util.function.Function<Double, Double> builtInImplementation;

    public Function(String functionDefinition) {
        Pattern pattern = Pattern.compile("([a-zA-Z]+)\\(([^)]+)\\)\\s*=\\s*(.+)");
        Matcher matcher = pattern.matcher(functionDefinition);

        if (matcher.matches()) {
            this.name = matcher.group(1);
            this.variable = matcher.group(2);
            this.expression = matcher.group(3);
            this.isBuiltIn = false;
            CalcCore.addFunction(name, x-> {
                try {
                    Map<String, Double> constants = new HashMap<>();
                    constants.put(variable, x);
                    return evaluate(constants);
                } catch (CalcCoreException e) {
                    e.printStackTrace();
                    return 0.0;
                }
            });
        } else {
            throw new IllegalArgumentException("Invalid function definition");
        }
    }

    public Function(String name, java.util.function.Function<Double, Double> implementation) {
        this.name = name;
        this.builtInImplementation = implementation;
        this.isBuiltIn = true;
        CalcCore.addFunction(name, implementation);
    }

    public String getName() {
        return name;
    }

    public double evaluate(Map<String, Double> constants) throws CalcCoreException {
        if (isBuiltIn) {
            if (constants.containsKey(variable)) {
                return builtInImplementation.apply(constants.get(variable));
            } else {
                throw new IllegalArgumentException("Missing constant value for variable " + variable);
            }
        } else {
            String evaluatedExpression = expression;
            for (Map.Entry<String, Double> entry : constants.entrySet()) {
                evaluatedExpression = evaluatedExpression.replace(entry.getKey(), entry.getValue().toString());
            }
            return evalExpression(evaluatedExpression);
        }
    }

    private double evalExpression(String expression) throws CalcCoreException {
        String[] rpn = ExpressionParser.parse(expression);
        return ExpressionSolver.solve(rpn);
    }
}
