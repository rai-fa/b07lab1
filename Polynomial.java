import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Polynomial {
	double[] coefficients;

	public Polynomial() {
		coefficients = new double[]{0};
		exponents = new int[]{0};
	}
	
	// constructor coeffs and exps arrays
	public Polynomial(double[] coefficients, int[] exponents) {
		if (coefficients.length != exponents.length) {
			throw new IllegalArgumentException("Coefficients and exponents arrays must have the same length.");
		}
		this.coefficients = coefficients;
        	this.exponents = exponents;
	}

	// Constructor from a file
    	public Polynomial(File file) throws IOException {
        	BufferedReader br = new BufferedReader(new FileReader(file));
        	String line = br.readLine();
        	br.close();
        
        	parsePolynomial(line);
    	}

	// Method to add two polynomials
    	public Polynomial add(Polynomial other) {
        	int maxLength = Math.max(this.coefficients.length, other.coefficients.length);
        	double[] resultCoefficients = new double[maxLength];  
		// # of coefficients in the resulting polynomial is determined by finding the longer polynomial
        
        	for (int i = 0; i < this.coefficients.length; i++) {
            		resultCoefficients[this.exponents[i]] += this.coefficients[i];
        	}

        	for (int i = 0; i < other.coefficients.length; i++) {
            		resultCoefficients[other.exponents[i]] += other.coefficients[i];
        	}

        	return createPolynomialFromArrays(resultCoefficients);
	}

	// Method to multiply two polynomials
    	public Polynomial multiply(Polynomial other) {
        	int maxExp = maxExponent(this.exponents) + maxExponent(other.exponents);
        	double[] resultCoefficients = new double[maxExp + 1];

        	for (int i = 0; i < this.coefficients.length; i++) {
            		for (int j = 0; j < other.coefficients.length; j++) {
                		int exp = this.exponents[i] + other.exponents[j];
                		resultCoefficients[exp] += this.coefficients[i] * other.coefficients[j];
            		}
        	}

        	return createPolynomialFromArrays(resultCoefficients);
    	}	
	
	
    	// Method to evaluate the polynomial for a given value of x
    	public double evaluate(double x) {
        	double result = 0;
        	for (int i = 0; i < coefficients.length; i++) {
            		result += coefficients[i] * Math.pow(x, i);
        	}
        	return result;
    	}

    	// Method to check if a given value is a root of the polynomial
    	public boolean hasRoot(double x) {
        	return evaluate(x) == 0;
    	}

	// Method to save the polynomial to a file
    	public void saveToFile(String filename) throws IOException {
        	BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        	StringBuilder sb = new StringBuilder();

        	for (int i = 0; i < coefficients.length; i++) {
            		if (coefficients[i] == 0) {
                		continue;
            		}

            		if (i > 0 && coefficients[i] > 0) {
                		sb.append("+");
            		}

            		sb.append(coefficients[i]);

            		if (exponents[i] != 0) {
                		sb.append("x");
                		if (exponents[i] != 1) {
                    			sb.append(exponents[i]);
                		}
            		}
        	}

        	bw.write(sb.toString());
        	bw.close();
    	}
	
	// Helper method to parse a polynomial from a string
    	private void parsePolynomial(String polynomial) {
        	List<Double> coeffList = new ArrayList<>();
        	List<Integer> expList = new ArrayList<>();

        	String[] terms = polynomial.split("(?=[+-])");

        	for (String term : terms) {
            		term = term.replaceAll(" ", "");
            		double coeff;
            		int exp;

            		if (term.contains("x")) {
                		String[] parts = term.split("x");
                		if (parts[0].isEmpty() || parts[0].equals("+")) {
                    			coeff = 1;
                		} 
				else if (parts[0].equals("-")) {
                    			coeff = -1;
                		} 
				else {
                    			coeff = Double.parseDouble(parts[0]);
                		}

                		if (parts.length == 1) {
                    			exp = 1;
                		} 
				else {
                    			exp = Integer.parseInt(parts[1]);
                		}
            		} 
			else {
                		coeff = Double.parseDouble(term);
                		exp = 0;
            		}

            		coeffList.add(coeff);
            		expList.add(exp);
        	}

        	coefficients = new double[coeffList.size()];
        	exponents = new int[expList.size()];

        	for (int i = 0; i < coeffList.size(); i++) {
            		coefficients[i] = coeffList.get(i);
            		exponents[i] = expList.get(i);
        	}
    	}

    	// Helper method to find the maximum exponent
    		private int maxExponent(int[] exponents) {
        	int max = exponents[0];
        	for (int i = 1; i < exponents.length; i++) {
            		if (exponents[i] > max) {
                		max = exponents[i];
            		}
        	}
        	return max;
    	}

    	// Helper method to create a Polynomial from coefficient array
    		private Polynomial createPolynomialFromArrays(double[] resultCoefficients) {
        	int count = 0;
        	for (double coeff : resultCoefficients) {
            		if (coeff != 0) {
                		count++;
            		}
        	}

        	double[] coeffs = new double[count];
        	int[] exps = new int[count];
        	int index = 0;

        	for (int i = 0; i < resultCoefficients.length; i++) {
            		if (resultCoefficients[i] != 0) {
                		coeffs[index] = resultCoefficients[i];
                		exps[index] = i;
                		index++;
            		}
        	}

        	return new Polynomial(coeffs, exps);
    	}

}


	