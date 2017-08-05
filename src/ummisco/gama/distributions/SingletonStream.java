package ummisco.gama.distributions;

import umontreal.ssj.rng.GenF2w32;
import umontreal.ssj.rng.RandomStream;

public class SingletonStream {

	private static RandomStream s = null;
	private SingletonStream(RandomStream s){
		SingletonStream.s = s;
	}
	
	
	public static RandomStream getInstance(){
		if(s == null){
			return new GenF2w32();
		}else{
			return s;
		}
			
	}
}
