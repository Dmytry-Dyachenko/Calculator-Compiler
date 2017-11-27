package javaclasses.calculator.impl.parser;

import javaclasses.calculator.impl.EvaluationContext;
import javaclasses.calculator.impl.ExpressionParser;
import javaclasses.calculator.impl.ExpressionReader;
import javaclasses.calculator.impl.Function;
import javaclasses.calculator.impl.function.FunctionFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;


/**
 * Checking for "variable" state.
 */

public class VariableParser implements ExpressionParser {

    @Override
    public boolean parse(ExpressionReader reader, EvaluationContext context) {
        final String expression = reader.getRemainingExpression();

        if (isItFunction(expression)){
            return false;
        }

        final Pattern pattern = compile("[a-zA-Z]");
        final Matcher matcher = pattern.matcher(expression);

        if (matcher.find()) {
            String variableName = matcher.group();
            context.pushVariableToContext(variableName);
            reader.incrementParsePosition(variableName.length());
            return true;

        }
        return false;
    }

    private boolean isItFunction(String expression) {
        final FunctionFactory factory = new FunctionFactory();
        for (String functionName: factory.getFunctionsName()){
            if (expression.startsWith(functionName)){
                return true;
            }
        }
        return false;
    }


}
