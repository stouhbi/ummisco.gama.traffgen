package ummisco.gama.distributions;

import umontreal.ssj.randvar.RandomVariateGen;
import umontreal.ssj.rng.RandomStream;

public class ShiftedLogNormalGen extends RandomVariateGen{
	
	// meanlog shape
	private double meanlog;
	
	// sdlog scale
	private double sdlog;
	
	// location
	private double u;

	public ShiftedLogNormalGen(RandomStream s, Double meanlog, Double sdlog, Double u) {
		super(s, new ShiftedLogNormalDist(meanlog, sdlog, u));
		this.dist = new ShiftedLogNormalDist(meanlog, sdlog, u);
		this.setParams(meanlog, sdlog, u);
		
	}
	
	
	public void setParams (double meanlog, double sdlog,double u) {
		this.meanlog = meanlog;
		this.sdlog = sdlog;
		this.u = u;
		//supportA = beta;
	}
	
	public double[] getParams() {
		double[] retour = {meanlog, sdlog, u};
		return retour;
	}
	
	
	public static double nextDouble (RandomStream s,
			Double meanlog, Double sdlog, Double u) {
		System.out.println("Random Stream : "+s.nextDouble());
		return ShiftedLogNormalDist.inverseF (meanlog, sdlog, u, s.nextDouble());
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
			Double meanlog, Double sdlog, Double u, int number) {
		double[] vals = new double[number];
		System.out.println("(");
		for(int i = 0; i < number; i++){
			vals[i] = ShiftedLogNormalDist.inverseF (meanlog, sdlog, u, s.nextDouble());
			System.out.println(vals[i]+",");
		}
		System.out.println(")");
		return vals;
		
	}


}
