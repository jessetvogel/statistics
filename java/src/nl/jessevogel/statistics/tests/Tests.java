package nl.jessevogel.statistics.tests;

import nl.jessevogel.statistics.DataPoint;
import nl.jessevogel.statistics.Fitting;
import nl.jessevogel.statistics.Variable;

import java.util.ArrayList;

public class Tests {

    public static boolean run() {
        int success = 0, failure = 0;
        System.out.println("Running tests...");

        // Run tests
        if(testLinearFit()) success++; else failure++;

        // Print results
        System.out.println("Completed!");
        System.out.println("" + success + "/" + (success + failure) + " were successful");

        return failure == 0;
    }

    private static boolean testLinearFit() {
        // http://onlinestatbook.com/2/regression/intro.html
        ArrayList<DataPoint> data = new ArrayList<>();
        data.add(new DataPoint(new Variable().setMean(1.0), new Variable().setMean(1.00)));
        data.add(new DataPoint(new Variable().setMean(2.0), new Variable().setMean(2.00)));
        data.add(new DataPoint(new Variable().setMean(3.0), new Variable().setMean(1.30)));
        data.add(new DataPoint(new Variable().setMean(4.0), new Variable().setMean(3.75)));
        data.add(new DataPoint(new Variable().setMean(5.0), new Variable().setMean(2.25)));

        Variable slope = new Variable("slope");
        Variable intercept = new Variable("intercept");

        Fitting.linearFit(data, slope, intercept);

        slope.print();
        intercept.print();

        if(Math.abs(slope.getMean() - 0.425) > 0.01) return false;
        if(Math.abs(intercept.getMean() - 0.785) > 0.01) return false;
        return true;
    }

}
