package ummisco.gama.traffgen.species;

import msi.gama.metamodel.agent.GamlAgent;
import msi.gama.metamodel.agent.IAgent;
import msi.gama.metamodel.population.IPopulation;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gama.precompiler.GamlAnnotations.setter;
import msi.gama.precompiler.GamlAnnotations.species;
import msi.gama.precompiler.GamlAnnotations.var;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.util.GamaDate;
import msi.gaml.types.IType;

@species(name="vehicle", doc=@doc("The abstract specie of type vehicle"))
@vars({
	@var(name=IVehicleMeta.TYPE, type=IType.STRING, doc=@doc("the type of the vehicle")),
	@var(name=IVehicleMeta.WIDTH, type=IType.FLOAT, doc=@doc("the width of the vehicle")),
	@var(name=IVehicleMeta.HEIGHT, type=IType.FLOAT, doc=@doc("the height of the vehicle")),
	@var(name=IVehicleMeta.INITIAL_SPEED, type=IType.FLOAT, doc=@doc("the initial speed of the vehicle")),
	@var(name=IVehicleMeta.ARRIVAL_TIME, type=IType.DATE, doc=@doc("the arrival time to the network")),
	@var(name=IVehicleMeta.LAUNCHED, type=IType.BOOL, doc=@doc("is the vehicle launched"))
})
public class Vehicle extends GamlAgent {
	
	private String vehicleType;
	private Double vehicleWidth;
	private Double vehicleHeight;
	private Double initialSpeed;
	private GamaDate arrivalTime;
	private Boolean launched = false;

	@getter(IVehicleMeta.TYPE)
	public String getVehicleType(){
		return this.vehicleType;
	}

	@setter(IVehicleMeta.TYPE)
	public void setVehicleType(String type){
		this.vehicleType = type;
	}

	@getter(IVehicleMeta.WIDTH)
	public Double getVehicleWidth() {
		return vehicleWidth;
	}

	@setter(IVehicleMeta.WIDTH)
	public void setVehicleWidth(double width) {
		this.vehicleWidth = width;
	}

	@getter(IVehicleMeta.HEIGHT)
	public Double getVehicleHeight() {
		return vehicleHeight;
	}
	
	
	@setter(IVehicleMeta.HEIGHT)
	public void setVehicleHeight(double height) {
		this.vehicleHeight = height;
	}

	@getter(IVehicleMeta.INITIAL_SPEED)
	public double getInitialSpeed() {
		return initialSpeed;
	}

	@setter(IVehicleMeta.INITIAL_SPEED)
	public void setInitialSpeed(double initialSpeed) {
		this.initialSpeed = initialSpeed;
	}

	@getter(IVehicleMeta.ARRIVAL_TIME)
	public GamaDate getArrivalTime() {
		return arrivalTime;
	}

	@setter(IVehicleMeta.ARRIVAL_TIME)
	public void setArrivalTime(GamaDate arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Vehicle(IPopulation<? extends IAgent> s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	@getter(IVehicleMeta.LAUNCHED)
	public Boolean getLaunched() {
		return launched;
	}

	@setter(IVehicleMeta.LAUNCHED)
	public void setLaunched(Boolean launched) {
		this.launched = launched;
	}

	public Vehicle(IPopulation<? extends IAgent> s, String vehicleType, Double vehicleWidth, Double vehicleHeight,
			Double initialSpeed, GamaDate arrivalTime, Boolean launched) {
		super(s);
		this.vehicleType = vehicleType;
		this.vehicleWidth = vehicleWidth;
		this.vehicleHeight = vehicleHeight;
		this.initialSpeed = initialSpeed;
		this.arrivalTime = arrivalTime;
		this.launched = launched;
	}
	
	

}
