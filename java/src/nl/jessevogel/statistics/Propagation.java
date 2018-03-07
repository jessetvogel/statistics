package nl.jessevogel.statistics;

/*
 * https://en.wikipedia.org/wiki/Propagation_of_uncertainty
 *
 * */

public class Propagation {

    // Original
    public static Variable add(Variable total, Variable a, Variable b) {
        total.setValue(a.getValue() + b.getValue());
        total.setVariance(a.getVariance() + b.getVariance());
        return total;
    }

    public static Variable subtract(Variable difference, Variable a, Variable b) {
        difference.setValue(a.getValue() - b.getValue());
        difference.setVariance(a.getVariance() + b.getVariance());
        return difference;
    }

    public static Variable multiply(Variable product, Variable a, Variable b) {
        double A = a.getValue();
        double B = b.getValue();
        double P = A * B;
        product.setValue(P);
        product.setVariance(P * P * (a.getVariance() / (A * A) + b.getVariance() / (B * B)));
        return product;
    }

    public static Variable divide(Variable ratio, Variable a, Variable b) {
        double A = a.getValue();
        double B = b.getValue();
        if(B == 0.0) { System.out.println("Cannot divide by 0!"); return ratio; } // TODO: handle errors?
        double R = A / B;
        ratio.setValue(R);
        ratio.setVariance(R * R * (a.getVariance() / (A * A) + b.getVariance() / (B * B)));
        return ratio;
    }

    public static Variable power(Variable output, Variable a, Variable b) {
        double A = a.getValue();
        double B = b.getValue();
        double O = Math.pow(A, B);
        output.setValue(O);
        output.setVariance(O * O * (a.getVariance() * B * B / (A * A) + Math.log(A) * Math.log(A) * b.getVariance()));
        return output;
    }

    // Derived
    public static Variable sqr(Variable output, Variable a) {
        return power(output, a, (new Variable()).setValue(2.0).setVariance(0.0));
    }

    public static Variable sqrt(Variable output, Variable a) {
        return power(output, a, (new Variable()).setValue(0.5).setVariance(0.0));
    }

    public static Variable exp(Variable output, Variable exponent) {
        return power(output, (new Variable()).setValue(Math.E).setVariance(0.0), exponent);
    }

}
