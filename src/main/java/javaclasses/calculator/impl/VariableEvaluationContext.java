package javaclasses.calculator.impl;

public class VariableEvaluationContext {
    private String variableName;
    private double variableValue;

    public VariableEvaluationContext(String variableName) {
        this.variableName = variableName;
    }

    public double getVariableValue() {
        return variableValue;
    }

    public void setVariableValue(double value) {
        this.variableValue = value;
    }

    public String getVariableName() {
        return variableName;
    }
}
