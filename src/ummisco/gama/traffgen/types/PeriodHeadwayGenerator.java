package ummisco.gama.traffgen.types;

import msi.gama.precompiler.GamlAnnotations.var;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.runtime.IScope;
import msi.gama.util.GamaDate;
import msi.gama.util.GamaListFactory;
import msi.gama.util.IList;
import msi.gaml.operators.Dates;
import msi.gaml.types.IType;
import msi.gaml.types.Types;
import ummisco.gama.helpers.Transformer;
import ummisco.gama.traffgen.species.Vehicle;
import umontreal.ssj.probdist.DiscreteDistributionInt;
import umontreal.ssj.probdist.Distribution;
import umontreal.ssj.probdist.DistributionFactory;
import umontreal.ssj.randvar.RandomVariateGenInt;
import umontreal.ssj.rng.LFSR113;
import umontreal.ssj.rng.RandomStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gama.precompiler.GamlAnnotations.setter;

@vars({ @var(name = IVehicleGenerator.PERIOD_SEQUENCE, type = IType.INT, doc = @doc("the number of the period in order")),
		@var(name = IVehicleGenerator.DURATION, type = IType.INT, doc = @doc("the duration of the generation for this period")),
		@var(name = IVehicleGenerator.THGENERATOR_PERIOD, type = IType.LIST, doc = @doc("the TH Generator for each vehicle Type")),
		@var(name = IVehicleGenerator.COUNT_GENERATION_MODEL, type = IType.MAP, of = IType.STRING, doc = @doc("The number of vehicle per period generation model")) })
public class PeriodHeadwayGenerator {
	public static final int Id = 98;

	
	public static RandomStream streamer = new LFSR113();
	private int periodSequence;
	private int duration;
	private IList<VehicleTHGenerator> THGenerators = GamaListFactory.create(Types.get(VehicleTHGenerator.Id));
	private Map<String, Object> countGenerationModel = new HashMap<String, Object>();
	private RandomVariateGenInt countModel;

	public PeriodHeadwayGenerator(int periodSequence, IList<VehicleTHGenerator> tHgenerators, int duration,
			Map<String, Object> countGenerationModel) {
		// TODO Auto-generated constructor stub
		this.periodSequence = periodSequence;
		this.THGenerators = tHgenerators;
		this.setCountGenerationModel(countGenerationModel);
		this.duration = duration;
		if (this.countGenerationModel != null) {
			Distribution countModelDist = DistributionFactory
					.getDistribution(Transformer.distToString(countGenerationModel));
			this.countModel = new RandomVariateGenInt(streamer, (DiscreteDistributionInt) countModelDist);
		}
	}

	@getter(IVehicleGenerator.PERIOD_SEQUENCE)
	public int getPeriodSequence() {
		return periodSequence;
	}

	@setter(IVehicleGenerator.PERIOD_SEQUENCE)
	public void setPeriodSequence(int periodSequence) {
		this.periodSequence = periodSequence;
	}

	@getter(IVehicleGenerator.DURATION)
	public int getDuration() {
		return duration;
	}

	@setter(IVehicleGenerator.DURATION)
	public void getDuration(int duration) {
		this.duration = duration;
	}

	@getter(IVehicleGenerator.THGENERATOR_PERIOD)
	public IList<VehicleTHGenerator> getTHGenerators() {
		return THGenerators;
	}

	@getter(IVehicleGenerator.THGENERATOR_PERIOD)
	public void setTHGenerators(IList<VehicleTHGenerator> thGenerators) {
		this.THGenerators = thGenerators;
	}

	@getter(IVehicleGenerator.COUNT_GENERATION_MODEL)
	public Map<String, Object> getCountGenerationModel() {
		return countGenerationModel;
	}

	@setter(IVehicleGenerator.COUNT_GENERATION_MODEL)
	public void setCountGenerationModel(Map<String, Object> model) {
		this.countGenerationModel = model;
	}

	public double generateTimeHeadway(IScope scope,IList<Vehicle> vehicleList, String currentVehicle) {
		@SuppressWarnings("unchecked")
		ArrayList<VehicleTHGenerator> thgenerators = (ArrayList<VehicleTHGenerator>) getTHGenerators();
		int genPreviousVehiceIndex = 0;
		int genCurrentVehicleIndex = 0;

		/**
		 * This parts need to be rewritten to take into consideration if the two
		 * vehicle Types are of different generation model
		 */
		for (int i = 0; i < thgenerators.size(); i++) {
			if (thgenerators.get(i).getVehicleTypes().contains(vehicleList.lastValue(scope)))
				genPreviousVehiceIndex = i;
			if (thgenerators.get(i).getVehicleTypes().contains(currentVehicle))
				genCurrentVehicleIndex = i;
		}

		if (genCurrentVehicleIndex == genPreviousVehiceIndex) {
			// we'll use the same model to generate TimeHeadway
			return thgenerators.get(genCurrentVehicleIndex).generateTimeHeadway();
		} else {
			// means that time headway model for the current vehicle type is
			// we need to find the last vehicle Type with the same generator and try to generate a coherent timeHeadway
			IList<String> vehicles = thgenerators.get(genPreviousVehiceIndex).getVehicleTypes();
			int index = Transformer.getlastIndexof((ArrayList<Vehicle>) vehicleList,  (ArrayList<String>) vehicles);
			double arrival = 0;
			if(index !=-1){
				boolean found = false;
				while(!found){
					arrival = thgenerators.get(genCurrentVehicleIndex).generateTimeHeadway();
					GamaDate arrivalTime = vehicleList.get(index).getArrivalTime().plusMillis(arrival*1000);
					double diff =  Math.abs(Dates.milliseconds_between(scope, arrivalTime, vehicleList.lastValue(scope).getArrivalTime()));
					if(diff > IVehicleGenerator.MIN_HEADWAY*1000)
						// the min time headway between the new vehicle and the last vehicle is over 0.05
						found = true;
				}
			}else{
				// it's the first time that any of vehicle type of this group is generated
				arrival =  thgenerators.get(genCurrentVehicleIndex).generateTimeHeadway();
			}
			return arrival;
		}

	}
	
	

	public int generateNumberVehicles() {
		return this.countModel.nextInt();
	}

}
