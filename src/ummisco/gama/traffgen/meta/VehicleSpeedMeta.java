package ummisco.gama.traffgen.meta;

import java.util.ArrayList;

public class VehicleSpeedMeta{

	
	private float meanSpeed;
	private ArrayList<String> vehicleTypes;

	public ArrayList<String> getVehicleTypes() {
		return vehicleTypes;
	}
	public void setVehicleTypes(ArrayList<String> vehicleTypes) {
		this.vehicleTypes = vehicleTypes;
	}
	
	public float getMeanSpeed() {
		return meanSpeed;
	}

	public void setMeanSpeed(float meanSpeed) {
		this.meanSpeed = meanSpeed;
	}
}
