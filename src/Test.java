import umontreal.ssj.rng.AntitheticStream;
import umontreal.ssj.rng.F2NL607;
import umontreal.ssj.rng.GenF2w32;
import umontreal.ssj.rng.LFSR113;
import umontreal.ssj.rng.LFSR258;
import umontreal.ssj.rng.RandomStream;

public class Test {



	public static void main(String[] args) {
		RandomStream s = new GenF2w32();
		//RandomStream str = new AntitheticStream(s);
		System.out.println("(");
		for(int i = 0; i < 2000; i++){
			System.out.println(s.nextDouble()+",");
		}
		System.out.println(")");
	}
}
