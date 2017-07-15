package ummisco.gama.distributions;

import umontreal.ssj.probdist.ContinuousDistribution;

public class Pareto3Dist extends ContinuousDistribution {

	/** location u **/
	private double u;
	
	/** inequality gamma **/
	private double gamma;
	
	/** scale beta **/
	private double beta;
	
	
	
	public Pareto3Dist(double u, double gamma, double beta) {
		this.setParams(u, gamma, beta);
	}

	public double getU() {
		return u;
	}

	public void setU(double u) {
		this.u = u;
	}

	public double getGamma() {
		return gamma;
	}

	public void setGamma(double gamma) {
		this.gamma = gamma;
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	@Override
	public double cdf(double arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getParams() {
		double[] retour = {u, gamma, beta};
		return retour;
	}

	@Override
	public double density(double arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setParams (double u, double gamma,double beta) {
		if (beta <= 0.0)
			throw new IllegalArgumentException ("beta (scale) <= 0");

		this.u = u;
		this.beta = beta;
		this.gamma = gamma;
		//supportA = beta;
	}
	
	
	
	/**
	 * Computes the inverse of the distribution function.
	 */
	public static double inverseF (double u, double gamma, double beta, double v) {
		if (beta <= 0.0)
			throw new IllegalArgumentException ("sigma (scale) <= 0");

		System.out.println("u "+u + " gamma(inequality) "+gamma+" beta(scale)"+beta);
		System.out.println("value  "+ Pareto4Dist.inverseF(1, gamma, beta, u, v));
		return Pareto4Dist.inverseF(1, gamma, beta, u, v);
	}
	
	@Override
	public double inverseF(double v) {
		if (beta <= 0.0)
			throw new IllegalArgumentException ("sigma (scale) <= 0");

		//System.out.println("u "+u + " gamma(inequality) "+gamma+" beta(scale)"+beta);
		//System.out.println("value  "+ Pareto4Dist.inverseF(1, gamma, beta, u, v));
		return Pareto4Dist.inverseF(1, gamma, beta, u, v);
	}
	
	
	/**
	 * Returns a `String` containing information about the current
	 * distribution.
	 */
	public String toString () {
		return getClass().getSimpleName() + ", gamma (inequality) = " + gamma + 
				", beta (scale) = "+beta+ ", u (location) = "+u;
	}

}
