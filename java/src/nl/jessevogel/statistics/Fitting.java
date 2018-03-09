package nl.jessevogel.statistics;

import java.util.*;

public class Fitting {

    public static void linearFit(List<DataPoint> data, Variable slope, Variable intercept, Variable R2) {
        int n = data.size();

        Variable sumX = new Variable();
        Variable sumY = new Variable();
        Variable sumX2 = new Variable();
        Variable sumXY = new Variable();
        Variable sumY2 = new Variable();

        for(DataPoint p : data) {
            sumX = sumX.add(p.x);
            sumY = sumY.add(p.y);
            sumX2 = sumX2.add(p.x.sqr());
            sumXY = sumXY.add(p.x.multiply(p.y));
            sumY2 = sumY2.add(p.x.sqr());
        }

        Variable ssxx = sumX2.subtract(sumX.sqr().divide(n));
        Variable ssxy = sumXY.subtract(sumX.multiply(sumY).divide(n));

        slope.set(ssxy.divide(ssxx));
        intercept.set(sumY.subtract(slope.multiply(sumX)).divide(n));

        if(R2 != null) {
            Variable ssyy = sumY2.subtract(sumY.sqr().divide(n));
            R2.set(ssxy.sqr().divide(ssxx.multiply(ssyy)));
        }
    }

    public static void linearFit(List<DataPoint> data, Variable slope, Variable intercept) {
        linearFit(data, slope, intercept, null);
    }

}
