import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) {
        // Test default constructor
        Polynomial p = new Polynomial();
        System.out.println("p(3) = " + p.evaluate(3));

        // Create polynomial p1: 6 + 5x^3
        double[] c1 = {6, 5};
        int[] e1 = {0, 3};
        Polynomial p1 = new Polynomial(c1, e1);

        // Create polynomial p2: -2x + 9x^4
        double[] c2 = {-2, 9};
        int[] e2 = {1, 4};
        Polynomial p2 = new Polynomial(c2, e2);

        // Add p1 and p2
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));

        // Check if 1 is a root of s
        if (s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");

        // Multiply p1 and p2
        Polynomial product = p1.multiply(p2);
        System.out.println("product(2) = " + product.evaluate(2));

        // Save polynomial to a file and read from the file
        try {
            s.saveToFile("polynomial.txt");
            Polynomial fromFile = new Polynomial(new File("polynomial.txt"));
            System.out.println("Polynomial read from file evaluated at 1 = " + fromFile.evaluate(1));
        } catch (IOException e) {
            System.out.println("An error occurred while handling the file.");
            e.printStackTrace();
        }
    }
}