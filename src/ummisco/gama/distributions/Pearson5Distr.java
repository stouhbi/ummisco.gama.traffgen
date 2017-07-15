package ummisco.gama.distributions;

import umontreal.ssj.probdist.ContinuousDistribution;
import umontreal.ssj.probdist.GammaDist;
import umontreal.ssj.probdist.InverseGammaDist;

public class Pearson5Distr extends ContinuousDistribution {

	/** shape alpha>0 **/
	protected double alpha;
	
	
	/** scale beta>0 **/
	protected double beta;
	
	/** location u **/
	protected double u;
	
	
	public Pearson5Distr(double alpha, double beta, double u) {
		this.setParams(alpha, beta, u);
	}

	@Override
	public double cdf(double arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getParams() {
		double[] retour = {alpha, beta, u};
		return retour;
	}

	@Override
	public double density(double arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setParams (double alpha, double beta,double u) {
		if (alpha <= 0.0)
			throw new IllegalArgumentException ("alpha (shape) <= 0");
		if (beta <= 0.0)
			throw new IllegalArgumentException ("beta (scale) <= 0");

		this.alpha = alpha;
		this.beta = beta;
		this.u = u;
		//supportA = beta;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	public double getU() {
		return u;
	}

	public void setU(double u) {
		this.u = u;
	}
	
	

	/**
	 * Computes the inverse of the distribution function.
	 */
	public static double inverseF (double alpha, double beta, double u, double v) {
		if (alpha <= 0.0)
			throw new IllegalArgumentException ("alpha (shape) <= 0");
		if (beta <= 0.0)
			throw new IllegalArgumentException ("beta (scale) <= 0");

		System.out.println("u "+u + " alpha(shape) "+alpha+ " beta(scale)"+beta);
		System.out.println("value  "+ ( u +  InverseGammaDist.inverseF(alpha, beta, v)));
		return (u +  InverseGammaDist.inverseF (alpha, beta, v));
	}
	
	@Override
	public double inverseF(double v) {
		if (alpha <= 0.0)
			throw new IllegalArgumentException ("alpha (shape) <= 0");
		if (beta <= 0.0)
			throw new IllegalArgumentException ("beta (scale) <= 0");

		System.out.println("u "+u + " alpha(shape) "+alpha+ " beta(scale)"+beta);
		System.out.println("value  "+ ( u +  InverseGammaDist.inverseF(alpha, beta, v)));
		return (u + InverseGammaDist.inverseF (alpha, beta, v));
	}


	/**
	 * Returns a `String` containing information about the current
	 * distribution.
	 */
	public String toString () {
		return getClass().getSimpleName() + " : alpha (shape) = " + alpha + ", beta (scale) = "+beta+ ", u (location) = "+u;
	}
}
