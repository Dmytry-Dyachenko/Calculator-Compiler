package javaclasses.calculator.impl;

class VariableEvaluationContext {
    private String variableName;
    private double variableValue;

    VariableEvaluationContext(String variableName) {
        this.variableName = variableName;
    }

    double getVariableValue() {
        return variableValue;
    }

    void setVariableValue(double value) {
        this.variableValue = value;
    }

    String getVariableName() {
        return variableName;
    }
}
