package ummisco.gama.distributions;

import umontreal.ssj.probdist.Pearson6Dist;
import umontreal.ssj.randvar.RandomVariateGen;
import umontreal.ssj.rng.RandomStream;

public class Pearson6Gene extends RandomVariateGen {

	// alpha1 shape1
	private double alpha1;

	// alpha1 shape2
	private double alpha2;

	// sigma scale
	private double sigma;
	
	// u location
	private double u;

	public Pearson6Gene(RandomStream s, double alpha1, double alpha2, double sigma,
			double u) {
		super(s, new Pearson6Distr(alpha1, alpha2, sigma,u));
		this.dist = new Pearson6Distr(alpha1, alpha2, sigma,u);
		this.setParams(alpha1, alpha2, sigma,u);
	}

	private void setParams(double alpha1, double alpha2, double sigma, double u) {
		this.alpha1 = alpha1;
		this.alpha2 = alpha2;
		this.sigma = sigma;
		this.u = u;
	}
	
	
	public double[] getParams(){
		double[] retour = {alpha1, alpha2, sigma, u};
		return retour;
	}
	
	
	public static double nextDouble (RandomStream s,
			 double alpha1, double alpha2, double sigma, double u) {
		System.out.println("Random Stream : "+s.nextDouble());
		return Pearson6Distr.inverseF (alpha1, alpha2, sigma, u, s.nextDouble());
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
			Double alpha1, Double alpha2, double sigma, Double u, int number) {
		double[] vals = new double[number];
		System.out.println("(");
		for(int i = 0; i < number; i++){
			vals[i] = Pearson6Distr.inverseF (alpha1, alpha2, sigma, u, s.nextDouble());
			System.out.println(vals[i]+",");
		}
		System.out.println(")");
		return vals;
		
	}

	
	

}
