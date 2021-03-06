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
	
	public static double nextDouble (RandomStream s,
			double alpha, double beta, double u) {
		System.out.println("Random Stream : "+s.nextDouble());
		return Pearson5Distr.inverseF (alpha, beta, u, s.nextDouble());
	}

	@Override
	public double nextDouble() {
		double rnd = this.stream.nextDouble();
		System.out.println("class of dist " + this.dist.toString());
		double val = this.dist.inverseF(rnd);
		System.out.println("random streamer "+rnd+ " value generated "+val);
		return val;
	}
	
	
	public double[] nextDoubles(int number){
		double[] vals = new double[number];
		System.out.println("(");
		for(int i = 0; i < number; i++){
			vals[i] = this.nextDouble();
			System.out.println(vals[i]+",");
		}
		System.out.println(")");
		return vals;
	}
	
	

	public static double[] nextDoubles (RandomStream s,
			double alpha, double beta, double u, int number) {
		double[] vals = new double[number];
		System.out.println("(");
		for(int i = 0; i < number; i++){
			vals[i] = Pearson5Distr.inverseF (alpha, beta, u, s.nextDouble());
			System.out.println(vals[i]+",");
		}
		System.out.println(")");
		return vals;
		
	}
	
}
