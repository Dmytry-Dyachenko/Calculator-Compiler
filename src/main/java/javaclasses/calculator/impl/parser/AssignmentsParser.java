package javaclasses.calculator.impl.parser;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.impl.EvaluationContext;
import javaclasses.calculator.impl.ExpressionParser;
import javaclasses.calculator.impl.ExpressionReader;

import static java.util.regex.Pattern.compile;


/**
 * Checking for "function" state.
 */

public class AssignmentsParser implements ExpressionParser {

    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) throws CalculationException {

        final String expression = reader.getRemainingExpression();
        final String ASSIGNMENT = "=";

        if (expression.startsWith(ASSIGNMENT)) {
            context.pushAssignmentToContext();
            reader.incrementParsePosition(ASSIGNMENT.length());
            return true;
        }
        return false;
    }

}
