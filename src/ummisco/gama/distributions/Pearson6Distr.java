package ummisco.gama.distributions;

import umontreal.ssj.probdist.ContinuousDistribution;
import umontreal.ssj.probdist.LognormalDist;
import umontreal.ssj.probdist.Pearson6Dist;

public class Pearson6Distr extends ContinuousDistribution {


	// alpha1 shape1
	private double alpha1;

	// alpha1 shape2
	private double alpha2;

	// sigma scale
	private double sigma;

	// u location
	private double u;
	
	private Pearson6Dist dist;


	public Pearson6Distr(double alpha1, double alpha2, double sigma, double u) {
		this.setParams(alpha1, alpha2, sigma, u);
		this.dist = new Pearson6Dist(alpha1, alpha2, sigma);
	}

	@Override
	public double cdf(double x) {
		// TODO Auto-generated method stub
		return cdf(alpha1, alpha2, sigma, u, x);
	}
	
	public static double cdf(double alpha1, double alpha2, double sigma, double u, double x){
		return Pearson6Dist.cdf(alpha1, alpha2, sigma, x-u);
	}

	private void setParams(double alpha1, double alpha2, double sigma, double u) {
		this.alpha1 = alpha1;
		this.alpha2 = alpha2;
		this.sigma = sigma;
		this.u = u;
	}
	
	@Override
	public double[] getParams(){
		double[] retour = {alpha1, alpha2, sigma, u};
		return retour;
	}

	@Override
	public double density(double arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Computes the inverse of the distribution function.
	 */
	public static double inverseF(double alpha1, double alpha2, double sigma, double u, double x)  {
		if(x-u < 0.0)
			return 0.0;
		return (u+Pearson6Dist.inverseF(alpha1, alpha2, sigma, x));
	}
	
	@Override
	public double inverseF(double x) {
		if(x-u < 0.0)
			return 0.0;
		return (u+Pearson6Dist.inverseF(alpha1, alpha2, sigma, x));
	}
	
	
	/**
	 * Returns a `String` containing information about the current
	 * distribution.
	 */
	public String toString () {
		return getClass().getSimpleName() + ", alpha1 (shape1) = " + alpha1 + ", alpha2 (shape2) = " + alpha2
				+ ", sigma (scale) = "+sigma+ ", u (location) = "+u;
	}

}
