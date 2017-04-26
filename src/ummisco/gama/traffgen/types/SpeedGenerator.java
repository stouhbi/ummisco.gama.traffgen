package ummisco.gama.traffgen.types;


import java.util.Map;

import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gama.precompiler.GamlAnnotations.setter;
import msi.gama.precompiler.GamlAnnotations.var;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gaml.types.IType;

@vars({
	@var(name=IVehicleGenerator.MEAN_SPEED, type=IType.MAP, doc=@doc("the mean speed per vehicle")),
})
public class SpeedGenerator {

	public static final int Id = 96;
	private Map<String, Double> meanSpeed;
	
	public SpeedGenerator(Map<String, Double> meanSpeed) {
		this.meanSpeed = meanSpeed;
	}

	@getter(IVehicleGenerator.MEAN_SPEED)
	public Map<String, Double> getMeanSpeed(){
		return meanSpeed;
	}

	@setter(IVehicleGenerator.MEAN_SPEED)
	public void setMeanSpeed(Map<String, Double> speed){
		this.meanSpeed = speed;
	}
	
	/**
	 * revise and see how to generate the vehicle speed based on the previous vehicle
	 * @param currentVehicleType
	 * @param previousVehicleType
	 * @return
	 */
	public double generateSpeed(String currentVehicleType, String previousVehicleType) {
		
		return meanSpeed.get(currentVehicleType);
	}

}
