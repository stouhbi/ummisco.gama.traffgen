package ummisco.gama.distributions;

import umontreal.ssj.randvar.RandomVariateGen;
import umontreal.ssj.rng.RandomStream;

public class Pearson3Gen extends RandomVariateGen{
	
	// alpha shape
	private double alpha;
	
	// sigma scale = 1/lambda(rate)
	private double sigma;
	
	// u location
	private double u;
	
	

	public Pearson3Gen(RandomStream s, double alpha, double sigma, double u) {
		super(s, new Pearson3Dist(alpha, sigma, u));
		this.dist = new Pearson3Dist(alpha, sigma, u);
		this.setParams(alpha, sigma, u);
	}



	public void setParams(double alpha, double sigma, double u) {
		this.alpha = alpha;
		this.sigma = sigma;
		this.u = u;
		
	}
	
	public double[] getParams(){
		double[] retour = {alpha, sigma, u};
		return retour;
	}
	
	
	public static double nextDouble (RandomStream s,
			double alpha, double sigma, double u) {
		System.out.println("Random Stream : "+s.nextDouble());
		return Pearson3Dist.inverseF (alpha, sigma, u, s.nextDouble());
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
			double alpha, double sigma, double u, int number) {
		double[] vals = new double[number];
		System.out.println("(");
		for(int i = 0; i < number; i++){
			vals[i] = Pearson3Dist.inverseF (alpha, sigma, u, s.nextDouble());
			System.out.println(vals[i]+",");
		}
		System.out.println(")");
		return vals;
		
	}

	

}
