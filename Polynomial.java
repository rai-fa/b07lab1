public class Polynomial {
	double[] coefficients;

	public Polynomial() {
		coefficients = {0};
	
	public Polynomial(double[] a) {
		coefficients = a

	// Method to add two polynomials
    	public Polynomial add(Polynomial other) {
        	int maxLength = Math.max(this.coefficients.length, other.coefficients.length);
        	double[] resultCoefficients = new double[maxLength];
        
        	for (int i = 0; i < maxLength; i++) {
            		double thisCoefficient = (i < this.coefficients.length) ? this.coefficients[i] : 0;
            		double otherCoefficient = (i < other.coefficients.length) ? other.coefficients[i] : 0;
            		resultCoefficients[i] = thisCoefficient + otherCoefficient;
        	}
        
        	return new Polynomial(resultCoefficients);
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

}


	