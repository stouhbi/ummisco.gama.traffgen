package ummisco.gama.distributions;

import umontreal.ssj.probdist.ContinuousDistribution;
import umontreal.ssj.probdist.GammaDist;
import umontreal.ssj.probdist.LognormalDist;

public class Pearson3Dist extends ContinuousDistribution {


	// alpha shape
	private double alpha;

	// sigma scale = 1/lambda(rate)
	private double sigma;

	// u location
	private double u;
	
	private GammaDist dist;


	public Pearson3Dist(double alpha, double sigma, double u) {
		this.alpha = alpha;
		this.sigma = sigma;
		this.u = u;
		this.dist = new GammaDist(alpha, 1/sigma);
	}

	@Override
	public double cdf(double x) {
		return cdf(alpha, sigma, u, x);
	}

	public static double cdf(double alpha, double sigma, double u, double x){
		return GammaDist.cdf(alpha, 1.0/sigma, 10, x-u);
	}
	
	public void setParams(double alpha, double sigma, double u) {
		this.alpha = alpha;
		this.sigma = sigma;
		this.u = u;

	}

	@Override
	public double[] getParams(){
		double[] retour = {alpha, sigma, u};
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
	public static double inverseF (double alpha, double sigma, double u, double x) {
		if(x-u < 0.0)
			return 0.0;
		return GammaDist.inverseF(alpha, 1.0/sigma, 5, x-u);
	}
	
	@Override
	public double inverseF(double x) {
		if(x-u < 0.0)
			return 0.0;
		return GammaDist.inverseF(alpha, 1.0/sigma, 5, x-u);
	}
	
	
	/**
	 * Returns a `String` containing information about the current
	 * distribution.
	 */
	public String toString () {
		return getClass().getSimpleName() + ", alpha (shape) = " + alpha + 
				", sigma (scale) = "+sigma+ ", u (location) = "+u;
	}

}
