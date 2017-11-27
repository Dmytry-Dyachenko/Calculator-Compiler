import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.CalculatorImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    CalculatorImpl calculator = new CalculatorImpl();


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

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

    @Test
    public void testStringWithBracketsAndFunctions() throws CalculationException {
        final double result = calculator.calculate("sum(1,2*min(1,3+aver(2,4)),2)");
        assertEquals(5, result, 0.001);
    }

    @Test
    public void testExpressionWithSpaces() throws CalculationException {
        final double result = calculator.calculate("    1    +    2  +  sum     (     1 ,   3    )");
        assertEquals(7, result, 0.001);
    }

    @Test
    public void testVariablesAndOutMethod() throws CalculationException {
        calculator.calculate("a=1;b=2;print(a+b);");
        assertEquals("3.0\r\n", outContent.toString());

    }

    @Test
    public void testWorkWithVariablesAndOutMethod() throws CalculationException {
        final double result = calculator.calculate("art=1;b=2+art;art+b+1;");
        assertEquals(5.0, result, 0.001);
    }

    @Test
    public void testWorkWithUpperCaseNamedVariables() throws CalculationException {
        final double result = calculator.calculate("superArt=1;bestOfYard=2+superArt;superArt+bestOfYard+1;");
        assertEquals(5.0, result, 0.001);
    }


}
