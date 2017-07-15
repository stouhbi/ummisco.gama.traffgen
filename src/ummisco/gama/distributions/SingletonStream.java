package ummisco.gama.distributions;

import umontreal.ssj.hups.AntitheticPointSet;
import umontreal.ssj.rng.AntitheticStream;
import umontreal.ssj.rng.GenF2w32;
import umontreal.ssj.rng.LFSR113;
import umontreal.ssj.rng.LFSR258;
import umontreal.ssj.rng.RandomStream;

public class SingletonStream {

	private static RandomStream s = null;
	private SingletonStream(RandomStream s){
		this.s = s;
	}
	
	
	public static RandomStream getInstance(){
		if(s == null){
			return new GenF2w32();
		}else{
			return s;
		}
			
	}
}
