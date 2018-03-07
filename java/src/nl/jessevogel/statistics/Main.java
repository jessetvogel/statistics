package nl.jessevogel.statistics;

public class Main {

    public static void main(String[] args) {

        Variable a = new Variable("a").setValue(3.0).setVariance(0.5);
        Variable b = new Variable("b").setValue(4.0).setVariance(0.5);
        Variable c = new Variable("c");
        Propagation.add(c, a, b);

        a.print();
        b.print();
        c.print();

    }
}
