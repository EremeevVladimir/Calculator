package com.evv.calculator.model;

public class Calculator implements CalculatorInterface {
    @Override
    public double perform(double arg1, double arg2, Operator operator) {

        switch (operator) {
            case ADDITION:
                return arg1+arg2;
            case SUBTRACTION:
                return arg1-arg2;
            case MULTIPLICATION:
                return arg1*arg2;
            case DIVISION:
                return arg1/arg2;
            case PERCENTAGE:
                return arg1/100*arg2;
        }

        return 0;
    }
}
