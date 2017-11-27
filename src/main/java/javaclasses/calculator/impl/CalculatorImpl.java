package javaclasses.calculator.impl;

import javaclasses.calculator.CalculationException;
import javaclasses.calculator.Calculator;
import javaclasses.calculator.fsm.FiniteStateMachine;
import javaclasses.calculator.impl.parser.ParserFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.EnumSet.of;
import static javaclasses.calculator.impl.State.*;

/**
 * Finite state machine logic implemented as calculator-compiler.
 */
public class CalculatorImpl
        extends FiniteStateMachine<
        ExpressionReader,
        EvaluationContext,
        State,
        CalculationException>
        implements Calculator {

    private final ParserFactory parserFactory = new ParserFactory();

    private final Map<State, Set<State>> transitions = new HashMap<State, Set<State>>() {{
        put(START, of(NUMBER, OPEN_BRACKET, VARIABLE, FUNCTION));
        put(NUMBER, of(DELIMITER, BINARY_OPERATOR, CLOSE_BRACKET, VARIABLE_DELIMITER, FINISH));
        put(VARIABLE, of(VARIABLE_DELIMITER, ASSIGNMENT, BINARY_OPERATOR, CLOSE_BRACKET, FINISH));
        put(ASSIGNMENT, of(NUMBER, FUNCTION, OPEN_BRACKET, VARIABLE));
        put(VARIABLE_DELIMITER, of(NUMBER, FUNCTION, VARIABLE, FINISH));
        put(BINARY_OPERATOR, of(NUMBER, OPEN_BRACKET, FUNCTION, VARIABLE));
        put(OPEN_BRACKET, of(VARIABLE, CLOSE_BRACKET, NUMBER, OPEN_BRACKET, FUNCTION));
        put(FUNCTION, of(VARIABLE_DELIMITER, OPEN_BRACKET, BINARY_OPERATOR, CLOSE_BRACKET));
        put(DELIMITER, of(OPEN_BRACKET, NUMBER));
        put(CLOSE_BRACKET, of(VARIABLE_DELIMITER, DELIMITER, CLOSE_BRACKET, BINARY_OPERATOR, FINISH));
    }};

    @Override
    public double calculate(String expression) throws CalculationException {
        ExpressionReader reader = new ExpressionReader(expression);
        final EvaluationContext evaluationContext = new EvaluationContext(new ErrorHandler() {
            @Override
            public void raiseError(String message) throws CalculationException {
                throw new CalculationException(message.replace(".", " ") + "at position " + reader.getParsePosition() + "!");
            }
        });
        start(START, reader, evaluationContext);
        return evaluationContext.getResult();
    }

    @Override
    protected boolean acceptState(ExpressionReader reader,
                                  EvaluationContext context, State nextState) throws CalculationException {
        final ExpressionParser parser = parserFactory.getParser(nextState);
        return parser.parse(reader, context);
    }

    @Override
    protected boolean isFinishState(State state) {
        return state == FINISH;
    }

    @Override
    protected Set<State> getPossibleTransitions(State state) {
        return transitions.get(state);
    }

    @Override
    protected void raiseDeadlockError(State state, ExpressionReader reader)
            throws CalculationException {

        throw new CalculationException("Incorrect expression format at position " + reader.getParsePosition() + "!");

    }
}
