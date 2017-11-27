package javaclasses.calculator;

/**
 * The main api for calculator.
 */
public interface Calculator {
    /**
     * Calculate the input expression.
     * You can use float numbers and some functions as a sum - summary(it’s a two arguments at least),
     * aver - average(it’s a two arguments at least),
     * min - return min value(it’s a two arguments at least),
     * max - return max value(it’s a two arguments at least),
     * and print - Prints the value(it’s a one argument).
     *
     * You can use variables too, variable can have any name, except the functions name, described above.
     * @param expression - math expression.
     * @return The result of the calculating.
     * @throws CalculationException - exception with information about
     * location of error in expression string.
     */
    double calculate(String expression) throws CalculationException;
}
