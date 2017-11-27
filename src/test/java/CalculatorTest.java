import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.CalculatorImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    CalculatorImpl calculator = new CalculatorImpl();

    @Test(expected = CalculationException.class)
    public void testStateValidation() throws CalculationException {
        calculator.calculate("1++2");
    }

    @Test
    public void testStringWIthPlusMinusOperators() throws CalculationException {
        final double result = calculator.calculate("1+2-3+4");
        assertEquals(4, result, 0.001);
    }

    @Test
    public void testStringWIthAllOperatorsWithoutExponentiation() throws CalculationException {
        final double result = calculator.calculate("2+2*2+4");
        assertEquals(10, result, 0.001);
    }

    @Test
    public void testStringWIthAllOperators() throws CalculationException {
        final double result = calculator.calculate("2+2*2+4^2-12^0");
        assertEquals(21, result, 0.001);
    }

    @Test
    public void testStringWithUnitBrackets() throws CalculationException {
        final double result = calculator.calculate("2+2^(2+3)");
        assertEquals(34, result, 0.001);
    }

    @Test
    public void testStringWithBrackets() throws CalculationException {
        final double result = calculator.calculate("3+2*(2+3^(1+2))");
        assertEquals(61, result, 0.001);
    }

 /*   @Test
    public void testStringWithBracketsAndFunctions() throws CalculationException {
        final double result = calculator.calculate("sum(5,3+2*(2+3^(1+(min(1,2)+2))),1)");
        assertEquals(175, result, 0.001);
    }*/

    @Test
    public void testExpressionWithSpaces() throws CalculationException {
        final double result = calculator.calculate("    1    +    2  +  sum     (     1 ,   3    )");
        assertEquals(7, result, 0.001);
    }

    @Test
    public void testVariables() throws CalculationException {
        final double result = calculator.calculate("a=1;b=a+2;a+b;");
        assertEquals(4, result, 0.001);
    }


}
