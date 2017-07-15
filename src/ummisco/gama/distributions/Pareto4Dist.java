package ummisco.gama.distributions;

import umontreal.ssj.probdist.ContinuousDistribution;
import umontreal.ssj.util.Num;

public class Pareto4Dist extends ContinuousDistribution {

	/** shape parameter **/
	private double alpha;

	/** inequality parameter **/
	private double gamma;

	/** scale paramter > 0 **/
	private double sigma;
	/** location parameter  x > u**/
	private double u;



	public Pareto4Dist(double alpha, double gamma, double sigma, double u) {
		this.setParams(alpha, gamma, sigma, u);
	}

	@Override
	public double cdf(double x) {
		// TODO Auto-generated method stub
		return cdf(alpha, gamma, sigma, u, x);
	}

	@Override
	public double[] getParams() {
		double[] retour = {alpha, gamma, sigma, u};
		return retour;
	}

	@Override
	public double density(double arg0) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	public void setParams (double alpha, double gamma, double sigma, double u) {
		if (gamma <= 0.0)
			throw new IllegalArgumentException ("gamma (inequality) <= 0");
		if (sigma <= 0.0)
			throw new IllegalArgumentException ("sigma (scale) <= 0");

		this.alpha = alpha;
		this.sigma = sigma;
		this.gamma = gamma;
		this.u = u;
		//supportA = beta;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public double getGamma() {
		return gamma;
	}

	public void setGamma(double gamma) {
		this.gamma = gamma;
	}

	public double getSigma() {
		return sigma;
	}

	public void setSigma(double sigma) {
		this.sigma = sigma;
	}

	public double getU() {
		return u;
	}

	public void setU(double u) {
		this.u = u;
	}

	/**
	 * Computes the density function.
	 */
	/*public static double density (double alpha, double gamma, double sigma, double u, double x) {
		if (gamma <= 0.0)
			throw new IllegalArgumentException ("gamma (inequality) <= 0");
		if (sigma <= 0.0)
			throw new IllegalArgumentException ("sigma (scale) <= 0");

		return x <= u ? 0 : alpha*Math.pow (beta/x, alpha)/x;
	}*/
	
	
	/**
	 * Computes the distribution function.
	 */
	public static double cdf (double alpha, double gamma, double sigma, double u, double x) {
		if (gamma <= 0.0)
			throw new IllegalArgumentException ("gamma (inequality) <= 0");
		if (sigma <= 0.0)
			throw new IllegalArgumentException ("sigma (scale) <= 0");
		if (x <= u)
			return 0.0;
		return 1.0 - Math.pow (Math.pow((x - u)/sigma, 1/gamma), -alpha);
	}

	/**
	 * Computes the complementary distribution function.
	 */
	/*public static double barF (double alpha, double gamma, double sigma, double u, double x) {
		if (gamma <= 0.0)
			throw new IllegalArgumentException ("gamma (inequality) <= 0");
		if (sigma <= 0.0)
			throw new IllegalArgumentException ("sigma (scale) <= 0");
		if (x <= u)
			return 0;
		return Math.pow (Math.pow((x - u)/sigma, 1/gamma), -alpha);
	}*/

	/**
	 * Computes the inverse of the distribution function.
	 */
	public static double inverseF (double alpha, double gamma, double sigma, double u, double v) {
		if (gamma <= 0.0)
			throw new IllegalArgumentException ("gamma (inequality) <= 0");
		if (sigma <= 0.0)
			throw new IllegalArgumentException ("sigma (scale) <= 0");

		//System.out.println("u "+u + " sigme(scale) "+sigma+ " gama(inequality)"+gamma+" alpha(shape)"+alpha);
		//System.out.println("value  "+ (u + sigma*Math.pow((Math.pow(v, (-1/alpha))) -1,gamma)));
		double value = Math.pow(v, (-1/alpha));
		value = value - 1;
		value = Math.pow(value, gamma);
		value = u + sigma*value;
		return value;
	}
	
	@Override
	public double inverseF(double v) {
		if (gamma <= 0.0)
			throw new IllegalArgumentException ("gamma (inequality) <= 0");
		if (sigma <= 0.0)
			throw new IllegalArgumentException ("sigma (scale) <= 0");

		//System.out.println("u "+u + " sigme(scale) "+sigma+ " gama(inequality)"+gamma+" alpha(shape)"+alpha);
		//System.out.println("value  "+ (u + sigma*Math.pow((Math.pow(v, (-1/alpha))) -1,gamma)));
		double value = Math.pow(v, (-1/alpha));
		value = value - 1;
		value = Math.pow(value, gamma);
		value = u + sigma*value;
		return value;
	}


	/**
	 * Returns a `String` containing information about the current
	 * distribution.
	 */
	public String toString () {
		return getClass().getSimpleName() + " : alpha (shape) = " + alpha + ", gamma (inequality) = " + gamma + 
				", sigma (scale) = "+sigma+ ", u (location) = "+u;
	}

}
