package ummisco.gama.distributions;

import umontreal.ssj.randvar.RandomVariateGen;
import umontreal.ssj.rng.RandomStream;

public class Pareto3Gen extends RandomVariateGen {

	/** location u **/
	private double u;
	
	/** inequality gamma **/
	private double gamma;
	
	/** scale beta **/
	private double beta;
	
	
	public Pareto3Gen(RandomStream s, double u, double gamma, double beta) {
		super(s, new Pareto3Dist(u, gamma, beta));
		this.dist = new Pareto3Dist(u, gamma, beta);
		this.setParams(u, gamma, beta);
	}
	
	
	
	public Pareto3Gen (RandomStream s, Pareto3Dist dist) {
		super (s, dist);
		
		if (dist != null){
			this.dist = dist;
			setParams(dist.getU(), dist.getGamma(), dist.getBeta());
		}
			
	}

	

	public void setParams (double u, double gamma,double beta) {
		if (beta <= 0.0)
			throw new IllegalArgumentException ("beta (scale) <= 0");

		this.u = u;
		this.beta = beta;
		this.gamma = gamma;
		//supportA = beta;
	}
	
	
	public static double nextDouble (RandomStream s,
			double u, double gamma, double beta) {
		System.out.println("Random Stream : "+s.nextDouble());
		return Pareto3Dist.inverseF (u, gamma, beta, s.nextDouble());
	}

	@Override
	public double nextDouble() {
		double rnd = this.stream.nextDouble();
		System.out.println("class of dist " + this.dist.toString());
		double val = this.dist.inverseF(rnd);
		System.out.println("random streamer "+rnd+ " value generated "+val);
		return val;
	}
	
	
	public double[] getParams() {
		double[] retour = {u, gamma, beta};
		return retour;
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
}
