package nl.jessevogel.statistics;

import nl.jessevogel.statistics.tests.Tests;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<DataPoint> measurements = new ArrayList<>();
        measurements.add(new DataPoint(new Variable().setMean(41.37), new Variable().setMean(38)));
        measurements.add(new DataPoint(new Variable().setMean(41.39), new Variable().setMean(37)));
        measurements.add(new DataPoint(new Variable().setMean(42.02), new Variable().setMean(40)));
        measurements.add(new DataPoint(new Variable().setMean(42.53), new Variable().setMean(35)));
        measurements.add(new DataPoint(new Variable().setMean(42.95), new Variable().setMean(29)));
        measurements.add(new DataPoint(new Variable().setMean(44.06), new Variable().setMean(37)));
        measurements.add(new DataPoint(new Variable().setMean(46.38), new Variable().setMean(20)));
        measurements.add(new DataPoint(new Variable().setMean(46.96), new Variable().setMean(24)));

        ArrayList<DataPoint> data = new ArrayList<>();
        for(DataPoint p : measurements) {
            p.y.setVariance(p.y.getMean() * p.y.getMean() * 0.25 * 0.25);
            data.add(new DataPoint(p.x, p.y.power(-2.0)));
        }

        Variable slope = new Variable("slope");
        Variable intercept = new Variable("intercept");

        Fitting.linearFit(data, slope, intercept);

        slope.print();
        intercept.print();


    }
}
