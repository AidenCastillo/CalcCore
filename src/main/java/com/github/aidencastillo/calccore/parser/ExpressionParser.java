package com.github.aidencastillo.calccore.parser;

import com.github.aidencastillo.calccore.CalcCore;
import com.github.aidencastillo.calccore.CalcCoreException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class ExpressionParser {
    public static String[] parse(String expression) throws CalcCoreException {
        try {
            String[] tokens = tokenize(expression);
            return toRPN(tokens);
        } catch (Exception e) {
            throw new CalcCoreException("Failed to parse expression: " + e.getMessage(), e);
        }
    }

    public static String[] tokenize(String s) {
        return s.replaceAll("\\s+", "").split("(?<=[-+*/()])|(?=[-+*/()])|(?<=[a-zA-Z])(?=[(])|(?<=[)])(?=[a-zA-Z])");
    }

    public static String[] toRPN(String[] tokens) throws CalcCoreException {
        int leftParmCounter = 0;
        int rightParmCounter = 0;
        Stack<String> operators = new Stack<>();
        Stack<String> output = new Stack<>();

        Map<String, Integer> precedence = new HashMap<>();
        Set<String> rightAssociative = new HashSet<>();

        precedence.put("+", 1);
        precedence.put("-", 1);
        precedence.put("*", 2);
        precedence.put("/", 2);
        precedence.put("^", 3);
        rightAssociative.add("^");

        for (String token : tokens) {
            if (isNumeric(token)) {
                output.push(token);
            } else if (precedence.containsKey(token)) {
                while (!operators.isEmpty() && precedence.getOrDefault(operators.peek(), 0) >= precedence.get(token)) {
                    output.push(operators.pop());
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
                leftParmCounter++;
            } else if (token.equals(")")) {
                rightParmCounter++;
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    output.push(operators.pop());
                }
                operators.pop();
                output.push(operators.pop());
            } else if (isFunction(token)) {
                operators.push(token);
            }
        }

        if (leftParmCounter != rightParmCounter) {
            throw new CalcCoreException("Invalid number of parenthesis" + leftParmCounter + " != " + rightParmCounter);
        }

        while (!operators.isEmpty()) {
            output.push(operators.pop());
        }

        return output.toArray(new String[0]);
    }

    public static boolean isFunction(String token) {
        return CalcCore.getFunctions().containsKey(token);
    }

    public static boolean isNumeric(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
