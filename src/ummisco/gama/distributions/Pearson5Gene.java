package ummisco.gama.distributions;

import umontreal.ssj.randvar.RandomVariateGen;
import umontreal.ssj.rng.RandomStream;

public class Pearson5Gene extends RandomVariateGen {

	/** shape alpha>0 **/
	protected double alpha;
	
	
	/** scale beta>0 **/
	protected double beta;
	
	/** location u **/
	protected double u;

	public Pearson5Gene(RandomStream s, double alpha, double beta, double u) {
		super(s, new Pearson5Distr(alpha, beta, u));
		this.alpha = alpha;
		this.beta = beta;
		this.u = u;
	}
	
	public Pearson5Gene(RandomStream s, Pearson5Distr dist) {
		super(s, dist);
		if (dist != null){
			this.dist = dist;
			setParams(dist.getAlpha(), dist.getBeta(), dist.getU());
		}
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
	
	public double[] getParams() {
		double[] retour = {alpha, beta, u};
		return retour;
	}
	
}
