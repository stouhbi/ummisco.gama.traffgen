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
import ummisco.gama.distributions.SingletonStream;
import ummisco.gama.helpers.Transformer;
import umontreal.ssj.randvar.RandomVariateGenInt;
import umontreal.ssj.rng.LFSR113;
import umontreal.ssj.rng.RandomStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import msi.gama.metamodel.shape.GamaPoint;
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
			
			this.countModel = (RandomVariateGenInt) Transformer.getGenerator(countGenerationModel, SingletonStream.getInstance());
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

	public double generateTimeHeadway(IScope scope,IList<Vehicle> vehicleList, String currentVehicle, GamaPoint initalPosition, Double remaining) {
		@SuppressWarnings("unchecked")
		ArrayList<VehicleTHGenerator> thgenerators = (ArrayList<VehicleTHGenerator>) getTHGenerators();
		int genPreviousVehiceIndex = 0;
		int genCurrentVehicleIndex = 0;

		/**
		 * This parts need to be rewritten to take into consideration if the two
		 * vehicle Types are of different generation model
		 */
		for (int i = 0; i < thgenerators.size(); i++) {
			if(vehicleList != null){
			if (thgenerators.get(i).getVehicleTypes().contains(vehicleList.lastValue(scope)))
				genPreviousVehiceIndex = i;
			if (thgenerators.get(i).getVehicleTypes().contains(currentVehicle))
				genCurrentVehicleIndex = i;
			}else{
				genPreviousVehiceIndex = -1;
				if (thgenerators.get(i).getVehicleTypes().contains(currentVehicle))
					genCurrentVehicleIndex = i;
			}
		}

		if (genCurrentVehicleIndex == genPreviousVehiceIndex || genPreviousVehiceIndex == -1) {
			// we'll use the same model to generate TimeHeadway
			return thgenerators.get(genCurrentVehicleIndex).generateTimeHeadway(remaining);
		} else {
			// means that time headway model for the current vehicle type is
			// we need to find the last vehicle Type with the same generator and try to generate a coherent timeHeadway
			IList<String> vehicles = thgenerators.get(genPreviousVehiceIndex).getVehicleTypes();
			int index = Transformer.getlastIndexof((ArrayList<Vehicle>) vehicleList,  (ArrayList<String>) vehicles);
			double arrival = 0;
			if(index !=-1){
				boolean found = false;
				while(!found){
					arrival = thgenerators.get(genCurrentVehicleIndex).generateTimeHeadway(remaining);
					GamaDate arrivalTime = vehicleList.get(index).getArrivalTime().plusMillis(arrival*1000);
					double diff =  Math.abs(Dates.milliseconds_between(scope, arrivalTime, vehicleList.lastValue(scope).getArrivalTime()));
					if(initalPosition == vehicleList.lastValue(scope).getPosition()){
						if(diff > IVehicleGenerator.MIN_HEADWAY*1000)
							// the min time headway between the new vehicle and the last vehicle is over 0.05
							found = true;
					}else{
						found = true;
					}
						
				}
			}else{
				// it's the first time that any of vehicle type of this group is generated
				arrival =  thgenerators.get(genCurrentVehicleIndex).generateTimeHeadway(remaining);
			}
			return arrival;
		}

	}
	
	

	public int generateNumberVehicles() {
		return this.countModel.nextInt();
	}

	public IList<Vehicle> generateTimeHeadways(IScope scope, IList<Vehicle> vehicles, GamaDate startDate, double residu) {
		ArrayList<Double> arrivals = new ArrayList<Double>();
		double sum = 0;
		double remaining = this.getDuration();
		// calculate the max that a timeHeadway should be
		double max_TimeHeadway = (vehicles.size()==0) ? 0:this.getDuration()/vehicles.size();
		boolean sequenceSuccess = false;
		int attempts = 3;
		while(!sequenceSuccess){
			System.out.println("Remaining "+remaining);
			/*if(residu < IVehicleGenerator.MIN_HEADWAY){
				arrivals.add(this.generateTimeHeadway(scope, null, vehicles.get(0).getVehicleType(), vehicles.get(0).getPosition(), (Double) sum ));
			}else if(residu==-1.0){
				arrivals.add(0.0);
				residu = 0.0;
			}else{
				arrivals.add(0.0);
			}*/
			residu=0.0;
			arrivals.add(this.generateTimeHeadway(scope, null, vehicles.get(0).getVehicleType(), vehicles.get(0).getPosition(), (Double) sum ));
			sum+=arrivals.get(0);
			remaining = this.getDuration() - sum;
			for(int i = 1; i < vehicles.size(); i++){
				IList<Vehicle> list2 = GamaListFactory.create(Types.get(VehicleTHGenerator.Id));
				for(int j = 0; j<=i; j++)
					list2.add(vehicles.get(j));
				
				arrivals.add(this.generateTimeHeadway(scope, list2, vehicles.get(i).getVehicleType(), vehicles.get(i).getPosition(), (Double) remaining ));
				sum += arrivals.get(i);
				
				remaining = this.getDuration() - sum;
				System.out.println("sum "+sum+ " Remaning "+remaining);
				if(remaining < 0){
					//  the additions has exceeded the period we should start again
					double lastTH = arrivals.get(i);
					arrivals.remove(i);
					remaining = remaining + lastTH;
					sum -= lastTH;
					break;
					/*if(attempts==0)
						break;
					attempts--;
					i--;*/
				}
					
			}
			
			System.out.println("New Sum --------------- "+sum + "Period "+ this.getDuration());
			if(sum <= this.getDuration()){
				// it worked no need to redo
				System.out.println("found combination");
				sequenceSuccess = true;
			}else{
				arrivals.clear();
			}
			sum = 0;
			remaining = this.getDuration();
		}
		if(arrivals.size() != vehicles.size()){ // the size changed due to some remaining error
			if(vehicles.size() - arrivals.size() == 1)
				vehicles.remove(arrivals.size());
			IList<Vehicle> list2 = GamaListFactory.create(Types.get(VehicleTHGenerator.Id));
			for(int i =0; i<arrivals.size(); i++){
				list2.add(vehicles.get(i));
			}
			vehicles = list2;
		}
		if(residu<IVehicleGenerator.MIN_HEADWAY){
			startDate = startDate.plusMillis((arrivals.get(0)+residu)*1000);
			double arrival = arrivals.get(0) + residu;
			residu = 0;
			vehicles.get(0).setArrivalTime(startDate);
			vehicles.get(0).setTimeHeadway(arrival);
		}else{
			//startDate = startDate;
			double arrival = residu;
			residu = 0;
			vehicles.get(0).setArrivalTime(startDate);
			
			vehicles.get(0).setTimeHeadway(arrival);
		}
		
		
		// now add arrival time to each vehicle
		for(int i = 1; i < arrivals.size(); i++){
			startDate = startDate.plusMillis((arrivals.get(i)+residu)*1000);
			double arrival = arrivals.get(i) + residu;
			residu = 0;
			vehicles.get(i).setArrivalTime(startDate);
			
			vehicles.get(i).setTimeHeadway(arrival);
		}
		return vehicles;
	}

}
