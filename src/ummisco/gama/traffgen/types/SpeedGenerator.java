package ummisco.gama.traffgen.types;


import java.util.Map;

import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gama.precompiler.GamlAnnotations.setter;
import msi.gama.precompiler.GamlAnnotations.var;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.util.IList;
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

	public IList<Vehicle> generateSpeeds(IList<Vehicle> vehicleList) {
		if(vehicleList.size()>1){
			vehicleList.get(0).setInitialSpeed(this.generateSpeed(vehicleList.get(0).getVehicleType(), null));
			for(int i = 1; i < vehicleList.size(); i++)
				vehicleList.get(i).setInitialSpeed(this.generateSpeed(vehicleList.get(i).getVehicleType(), vehicleList.get(i-1).getVehicleType()));
		}
		
		return vehicleList;
	}

}
