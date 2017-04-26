package ummisco.gama.traffgen.types;

import java.text.ParseException;

import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gama.precompiler.GamlAnnotations.setter;
import msi.gama.precompiler.GamlAnnotations.var;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.runtime.IScope;
import msi.gama.util.GamaDate;
import msi.gama.util.GamaListFactory;
import msi.gama.util.IList;
import msi.gaml.types.IType;
import msi.gaml.types.Types;
@vars({
	@var(name = IVehicleGenerator.VEHICLE_LIST, type = IType.LIST, doc = @doc("list of vehicle type to generate")),
	@var(name = IVehicleGenerator.SPEED_LIST, type = IType.LIST, doc = @doc("list of speed of each vehicle in vehicle List")),
	@var(name = IVehicleGenerator.TH_LIST, type = IType.LIST, doc = @doc("List of time Headway of each vehicle in list")),
	@var(name = IVehicleGenerator.PERIOD_LIST, type = IType.LIST, doc = @doc("list of preriod number based on the sequence of generation")),
	@var(name = IVehicleGenerator.VEHICLE_NUMBER, type = IType.LIST, doc = @doc("list of number of vehicles for each period in the generation"))
})
public abstract class VehicleGeneratorX {

	public final static int Id = 99;

	protected IList<String> vehicleList =GamaListFactory.create(Types.get(IType.STRING));
	protected IList<Double> speedList = GamaListFactory.create(Types.get(IType.FLOAT));
	protected IList<GamaDate> timeHeadwayList = GamaListFactory.create(Types.get(IType.DATE));
	protected IList<Integer> periodList = GamaListFactory.create(Types.get(IType.INT));
	protected IList<Integer> vehicleNumber =  GamaListFactory.create(Types.get(IType.INT));
	
	abstract public void generateVehiclesHeadway(IScope scope) throws ParseException;

	abstract public void generateVehiclesCount(IScope scope) throws ParseException;
	
	
	

	@getter(IVehicleGenerator.VEHICLE_LIST)
	public IList<String> getVehicleList() {
		return vehicleList;
	}
	
	@setter(IVehicleGenerator.VEHICLE_LIST)
	public void setVehicleList(IList<String> vehicleList) {
		this.vehicleList = vehicleList;
	}

	@getter(IVehicleGenerator.SPEED_LIST)
	public IList<Double> getSpeedList() {
		return speedList;
	}

	@setter(IVehicleGenerator.SPEED_LIST)
	public void setSpeedList(IList<Double> speedList) {
		this.speedList = speedList;
	}

	@getter(IVehicleGenerator.TH_LIST)
	public IList<GamaDate> getTimeHeadwayList() {
		return timeHeadwayList;
	}
	@setter(IVehicleGenerator.TH_LIST)
	public void setTimeHeadwayList(IList<GamaDate> timeHeadwayList) {
		this.timeHeadwayList = timeHeadwayList;
	}

	@getter(IVehicleGenerator.PERIOD_LIST)
	public IList<Integer> getPeriodList() {
		return periodList;
	}

	@setter(IVehicleGenerator.PERIOD_LIST)
	public void setPeriodList(IList<Integer> periodList) {
		this.periodList = periodList;
	}

	@getter(IVehicleGenerator.VEHICLE_NUMBER)
	public IList<Integer> getVehicleNumber() {
		return vehicleNumber;
	}

	@setter(IVehicleGenerator.VEHICLE_NUMBER)
	public void setVehicleNumber(IList<Integer> vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	
	
}
