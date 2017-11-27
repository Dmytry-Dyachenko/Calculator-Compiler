package javaclasses.calculator.impl;

/**
 * The all possible states of our machine.
 */
public enum State {
    START,
    NUMBER,
    VARIABLE,
    ASSIGNMENT,
    VARIABLE_DELIMITER,
    BINARY_OPERATOR,
    OPEN_BRACKET,
    CLOSE_BRACKET,
    DELIMITER,
    FUNCTION,
    FINISH
}
