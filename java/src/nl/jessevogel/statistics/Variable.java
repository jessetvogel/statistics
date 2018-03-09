package nl.jessevogel.statistics;

import java.util.Random;

public class Variable {

    private String label;
    private double mean;
    private double variance;

    public Variable() {
        label = "";
        mean = 0.0;
        variance = 0.0;
    }

    public Variable(String label) {
        this.label = label;
        mean = 0.0;
        variance = 0.0;
    }

    public Variable set(Variable other) {
        mean = other.mean;
        variance = other.variance;
        return this;
    }

    public Variable setLabel(String label) {
        this.label = label;
        return this;
    }

    public Variable setMean(double mean) {
        this.mean = mean;
        return this;
    }

    public Variable setVariance(double variance) {
        this.variance = variance;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public double getMean() {
        return mean;
    }

    public double getVariance() {
        return variance;
    }

    public void print() {
        System.out.println("" + label + " = " + mean + " ± " + Math.sqrt(variance));
    }

    public double createSample() {
        // Use Gaussian distribution
        Random random = new Random();
        return random.nextGaussian() * Math.sqrt(variance) + mean;
    }

    public Variable fromSamples(double[] samples) {
        // Estimate mean
        double mean = 0.0;
        for(double sample : samples)
            mean += sample;
        mean /= samples.length;

        // Estimate variance
        double variance = 0.0;
        for(double sample : samples)
            variance += (sample - mean) * (sample - mean);
        variance /= samples.length - 1;

        this.mean = mean;
        this.variance = variance;
        return this;
    }

    // Error propagation through arithmetic
    // https://en.wikipedia.org/wiki/Propagation_of_uncertainty
    public Variable add(Variable other) {
        return (new Variable())
                .setMean(mean + other.getMean())
                .setVariance(variance + other.variance);
    }

    public Variable add(double x) {
        return (new Variable())
                .setMean(mean + x)
                .setVariance(variance);
    }

    public Variable subtract(Variable other) {
        return (new Variable())
                .setMean(mean - other.getMean())
                .setVariance(variance + other.variance);
    }

    public Variable subtract(double x) {
        return (new Variable())
                .setMean(mean - x)
                .setVariance(variance);
    }

    public Variable multiply(Variable other) {
        double otherMean = other.getMean();
        return (new Variable())
                .setMean(mean * otherMean)
                .setVariance(mean * mean * otherMean * otherMean * (variance / (mean * mean) + other.variance / (otherMean * otherMean)));
    }

    public Variable multiply(double x) {
        return (new Variable())
                .setMean(mean * x)
                .setVariance(variance * x * x);
    }

    public Variable divide(Variable other) {
        double otherMean = other.getMean();
        return (new Variable())
                .setMean(mean / otherMean)
                .setVariance(mean * mean / (otherMean * otherMean) * (variance / (mean * mean) + other.variance / (otherMean * otherMean)));
    }

    public Variable divide(double x) {
        return (new Variable())
                .setMean(mean / x)
                .setVariance(variance / (x * x));
    }

    public Variable power(Variable other) {
        double otherMean = other.getMean();
        double newMean = Math.pow(mean, otherMean);
        return (new Variable())
                .setMean(newMean)
                .setVariance(newMean * newMean * (variance * otherMean * otherMean / (mean * mean) + Math.log(mean) * Math.log(mean) * other.variance));
    }

    public Variable power(double x) {
        double newMean = Math.pow(mean, x);
        return (new Variable())
                .setMean(newMean)
                .setVariance(variance * newMean * newMean / (mean * mean) * x * x);
    }

    public Variable sqr() {
        return power(2.0);
    }

    public Variable sqrt() {
        return power(0.5);
    }

}
