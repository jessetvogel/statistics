package nl.jessevogel.statistics;

public class Variable {

    private String label;
    private double value;
    private double variance;

    public Variable() {
        label = "";
        value = 0.0;
        variance = 0.0;
    }

    public Variable(String label) {
        this.label = label;
        value = 0.0;
        variance = 0.0;
    }

    public Variable setValue(double value) {
        this.value = value;
        return this;
    }

    public Variable setVariance(double variance) {
        this.variance = variance;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public double getValue() {
        return value;
    }

    public double getVariance() {
        return variance;
    }

    public void print() {
        System.out.println("" + label + " = " + value + " ± " + Math.sqrt(variance));
    }

}
