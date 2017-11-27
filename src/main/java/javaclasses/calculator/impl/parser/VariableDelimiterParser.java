package javaclasses.calculator.impl.parser;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.EvaluationContext;
import javaclasses.calculator.impl.ExpressionReader;

public class VariableDelimiterParser implements javaclasses.calculator.impl.ExpressionParser {

    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CalculationException {

        final String VARIABLE_DELIMITER = ";";

        final String expression = reader.getRemainingExpression();
        if (expression.startsWith(VARIABLE_DELIMITER)) {
            context.pushVariableDelimiter();
            reader.incrementParsePosition(VARIABLE_DELIMITER.length());
            return true;
        }
        return false;
    }
}
