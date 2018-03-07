package ummisco.gama.distributions;

import umontreal.ssj.probdist.ContinuousDistribution;
import umontreal.ssj.probdist.LognormalDist;

public class ShiftedLogNormalDist extends ContinuousDistribution {

	// meanlog shape mu
	private double meanlog;

	// sdlog scale sigma
	private double sdlog;

	// location
	private double u;
	
	private LognormalDist dist;
	

	public ShiftedLogNormalDist(double meanlog, double sdlog, double u) {
		this.setParams(meanlog, sdlog, u);
		dist = new LognormalDist(meanlog, sdlog);
	}

	@Override
	public double cdf(double x) {
		// TODO Auto-generated method stub
		return cdf(this.meanlog, this.sdlog, this.u, x);
	}

	
	public static double cdf(double meanlog, double sdlog, double u, double x){
		return LognormalDist.cdf(meanlog, sdlog, x-u);
	}
	
	
	public void setParams (double meanlog, double sdlog,double u) {
		this.meanlog = meanlog;
		this.sdlog = sdlog;
		this.u = u;
		//supportA = beta;
	}
	
	@Override
	public double[] getParams() {
		double[] retour = {meanlog, sdlog, u};
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
	public static double inverseF (double meanlog, double sdlog, double u, double x) {
		if(x-u < 0.0)
			return 0.0;
		return (u+LognormalDist.inverseF(meanlog, sdlog, x));
	}
	
	@Override
	public double inverseF(double x) {
		if(x-u < 0.0)
			return 0.0;
		return (u+LognormalDist.inverseF(meanlog, sdlog, x));
	}
	
	
	/**
	 * Returns a `String` containing information about the current
	 * distribution.
	 */
	public String toString () {
		return getClass().getSimpleName() + ", mu (meanlog) = " + meanlog + 
				", sigma (sdlog) = "+sdlog+ ", u (location) = "+u;
	}

}
