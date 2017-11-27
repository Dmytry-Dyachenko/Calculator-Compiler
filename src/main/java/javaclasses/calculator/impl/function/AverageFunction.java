package javaclasses.calculator.impl.function;

import javaclasses.calculator.impl.Function;

import java.util.List;

/**
 * Implementation of the average function.
 */

public class AverageFunction implements Function {
    @Override
    public double execute(List<Double> arguments) {
        final SumFunction sum = new SumFunction();
        return sum.execute(arguments) / arguments.size();
    }

    @Override
    public int getMinCountOfArguments() {
        return 2;
    }

    @Override
    public int getMaxCountOfArguments() {
        return 10;
    }
}
