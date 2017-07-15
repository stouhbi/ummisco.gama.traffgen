package ummisco.gama.distributions;

import umontreal.ssj.randvar.RandomVariateGen;
import umontreal.ssj.randvar.UniformGen;
import umontreal.ssj.rng.RandomStream;

public class Pareto4Gen extends RandomVariateGen {

	/** shape parameter **/
	private double alpha;

	/** inequality parameter **/
	private double gamma;

	/** scale paramter > 0 **/
	private double sigma;
	/** location parameter  x > u**/
	private double u;
	public Pareto4Gen(RandomStream s, double alpha, double gamma, double sigma, double u) {
		super(s, new Pareto4Dist(alpha, gamma, sigma, u));
		this.dist = new Pareto4Dist(alpha, gamma, sigma, u);
		this.setParams(alpha, gamma, sigma, u);
	}




	public void setParams (double alpha, double gamma, double sigma, double u) {
		if (gamma <= 0.0)
			throw new IllegalArgumentException ("gamma (inequality) <= 0");
		if (sigma <= 0.0)
			throw new IllegalArgumentException ("sigma (scale) <= 0");

		this.alpha = alpha;
		this.sigma = sigma;
		this.gamma = gamma;
		this.u = u;
		//supportA = beta;
	}

	public Pareto4Gen (RandomStream s, Pareto4Dist dist) {
		super (s, dist);
		
		if (dist != null){
			this.dist = dist;
			setParams(dist.getAlpha(), dist.getGamma(), dist.getSigma(), dist.getU());
		}
			
	}


	public static double nextDouble (RandomStream s,
			double alpha, double gamma, double sigma, double u) {
		System.out.println("Random Stream : "+s.nextDouble());
		return Pareto4Dist.inverseF (alpha, gamma, sigma, u, s.nextDouble());
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
			double alpha, double gamma, double sigma, double u, int number) {
		double[] vals = new double[number];
		System.out.println("(");
		for(int i = 0; i < number; i++){
			vals[i] = Pareto4Dist.inverseF (alpha, gamma, sigma, u, s.nextDouble());
			System.out.println(vals[i]+",");
		}
		System.out.println(")");
		return vals;
		
	}

}
