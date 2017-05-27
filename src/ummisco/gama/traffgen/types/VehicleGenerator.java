package ummisco.gama.traffgen.types;



import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;



import msi.gama.metamodel.shape.GamaPoint;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gama.precompiler.GamlAnnotations.setter;
import msi.gama.precompiler.GamlAnnotations.var;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.runtime.IScope;
import msi.gama.util.GamaDate;
import msi.gama.util.GamaListFactory;
import msi.gama.util.IList;
import msi.gama.util.matrix.GamaMatrix;
import msi.gama.util.matrix.IMatrix;
import msi.gaml.types.IType;
import msi.gaml.types.Types;
import ummisco.gama.helpers.Transformer;


@vars({
		// Time Interval on which the vehicle will be generated
		@var(name = IVehicleGenerator.TIME_INTERVAL, type = IType.LIST, doc = @doc("the time interval on which this generation will occur")),
		// The coordinates on which the arriving vehicles will be positioned
		@var(name = IVehicleGenerator.COORDINATES, type = IType.LIST, doc = @doc("the coordinates on which the generation will occur")),
		@var(name = IVehicleGenerator.COORDINATES_CHOICE, type = IType.STRING, doc=@doc("the choice of changing between corrdinates")),
		// The type of transition of vehicles needed
		@var(name = IVehicleGenerator.TRANSITION_TYPE, type = IType.STRING, init= IVehicleGenerator.TRANSITION_TYPE, doc = @doc("the transition type")),
		// the types of vehicles that will be generated p.s the type could be
		// changed after
		@var(name = IVehicleGenerator.VEHICLE_TYPES, type = IType.LIST, doc = @doc("The vehicle Types generated by this generator")),
		// the transition matrix for generating vehicles, we should add a
		// function transform that would enable to change list to matrix
		@var(name = IVehicleGenerator.TRANSITION, type = IType.MATRIX, of=IType.FLOAT, doc = @doc("The transition Matrix")),
		// the speed generator
		@var(name = IVehicleGenerator.SPEED, type = 96, doc = @doc("the speed metadata")),
		@var(name = IVehicleGenerator.TIME_HEADWAY, type = IType.LIST, doc = @doc("the list of timeHeadway metadata")) ,

		@var(name = IVehicleGenerator.VEHICLE_LIST, type = IType.LIST, doc = @doc("list of vehicle type to generate")),
})
public class VehicleGenerator {

	public final static int Id = 99;
	public Random rnd = new Random();
	private IList<GamaDate> timeInterval = GamaListFactory.create(Types.get(IType.STRING));
	private IList<GamaPoint> coordinates = GamaListFactory.create(Types.get(IType.POINT));
	private String coordinatesChoice = new String();
	private String transitionType = IVehicleGenerator.TRANSITION_TYPE;
	private IList<String> vehicleTypes = GamaListFactory.create(Types.get(IType.STRING));
	private IMatrix<Double> transition;
	private SpeedGenerator speed;
	private IList<PeriodHeadwayGenerator> timeHeadway = GamaListFactory.create(Types.get(PeriodHeadwayGenerator.Id));
	
	private IList<Vehicle> vehicleList =GamaListFactory.create(Types.get(IType.AGENT));
	
    public VehicleGenerator(IList<GamaDate> timeInterval, String transitionType,
			IMatrix<Double> transition, IList<PeriodHeadwayGenerator> timeHeadway, SpeedGenerator speed, IList<String> vehicleTypes,
			IList<GamaPoint> coordinates, String coordinatesChoice) {
		this.timeInterval = timeInterval;
		this.transitionType = transitionType;
		this.transition = transition;
		this.timeHeadway = timeHeadway;
		this.speed = speed;
		this.vehicleTypes = vehicleTypes;
		this.coordinates = coordinates;
		this.coordinatesChoice = coordinatesChoice;
	}

	//	// Generate the dataframe of metaData to send to R
//	// public VehicleGeneratorMeta generateMetaData(IScope scope) {
//	//
//	// //
//	// @SuppressWarnings("unchecked")
//	// ArrayList<String> timeInterval = (ArrayList<String>)
//	// scope.getListArg(IVehicleGenerator.TIME_INTERVAL);
//	// String transitionType = (String)
//	// scope.getArg(IVehicleGenerator.TRANSITION_TYPE, IType.LIST);
//	// ArrayList<String> vehicleTypes = (ArrayList<String>)
//	// scope.getListArg(IVehicleGenerator.VEHICLE_TYPES);
//	// // Matrix to Arraylist
//	// ArrayList<ArrayList<Float>> transition = (ArrayList<ArrayList<Float>>)
//	// scope
//	// .getListArg(IVehicleGenerator.TRANSITION);
//	//
//	// SpeedGenerator speed = (SpeedGenerator)
//	// scope.getArg(IVehicleGenerator.SPEED, SpeedGenerator.Id);
//	// ArrayList<VehicleTHGenerator> THGenerator =
//	// (ArrayList<VehicleTHGenerator>) scope
//	// .getListArg(IVehicleGenerator.TIME_HEADWAY);
//	//
//	// /*
//	// * ArrayList<String> TimeIntervalList = new ArrayList<String>();
//	// *
//	// * ArrayList<String> vehiclesTypesList = new ArrayList<String>();
//	// *
//	// * ArrayList<String> transitionList = new ArrayList<String>();
//	// * ArrayList<String> transitionTypeList = new ArrayList<String>();
//	// *
//	// * ArrayList<Integer> periodSequenceList = new ArrayList<Integer>();
//	// * ArrayList<String> generationModelList = new ArrayList<String>();
//	// * ArrayList<String> parameterList = new ArrayList<String>();
//	// * ArrayList<Integer> durationList = new ArrayList<Integer>();
//	// * ArrayList<String> speedList = new ArrayList<String>();
//	// */
//	// VehicleGeneratorMeta vehicleGenMeta = new VehicleGeneratorMeta();
//	// for (int i = 0; i < THGenerator.size(); i++) {
//	// VehicleTHGenerator veh = THGenerator.get(i);
//	// ArrayList<String> vehicles = veh.getVehicleTypes(scope);
//	// ArrayList<PeriodHeadwayGenerator> periods = veh.getPeriods(scope);
//	// for (int j = 0; j < periods.size(); j++) {
//	// vehicleGenMeta.getTimeInterval().add(Transformer.listToString(timeInterval));
//	// // TimeIntervalList.add(Transformer.listToString(timeInterval));
//	// vehicleGenMeta.getTransitionType().add(transitionType);
//	// // transitionTypeList.add(transitionType);
//	// vehicleGenMeta.getTransition().add(Transformer.matrixToString(transition));
//	// // transitionList.add(Transformer.matrixToString(transition));
//	// vehicleGenMeta.getVehicleTypes().add(Transformer.listToString(vehicles));
//	// // vehiclesTypesList.add(Transformer.listToString(vehicles));
//	// vehicleGenMeta.getPeriodSequence().add(periods.get(j).getPeriodSequence(scope));
//	// // periodSequenceList.add(periods.get(j).getPeriodSequence(scope));
//	// vehicleGenMeta.getGenerationModel().add(periods.get(j).getGenerationModel(scope));
//	// // generationModelList.add(periods.get(j).getGenerationModel(scope));
//	// vehicleGenMeta.getParameters().add(Transformer.mapToString(periods.get(j).getParameters(scope)));
//	// //
//	// parameterList.add(Transformer.mapToString(periods.get(j).getParameters(scope)));
//	// vehicleGenMeta.getDuration().add(periods.get(j).getDuration(scope));
//	// // durationList.add(periods.get(j).getDuration(scope));
//	// vehicleGenMeta.getSpeed().add(Transformer.mapToString(speed.getMeanSpeed(scope)));
//	// // speedList.add(Transformer.mapToString(speed.getMeanSpeed(scope)));
//	// }
//	// }
//	/*
//	 * // Create the data Frame // Columns StringColumn timeIntervalColumn =
//	 * StringColumn.ofAll(StringColumnId.of(IVehicleGenerator.TIME_INTERVAL) ,
//	 * (Iterable<String>) TimeIntervalList.iterator()); StringColumn
//	 * vehicleTypeColumn =
//	 * StringColumn.ofAll(StringColumnId.of(IVehicleGenerator.VEHICLE_TYPES) ,
//	 * (Iterable<String>) vehiclesTypesList.iterator()); StringColumn
//	 * transitionTypeColumn =
//	 * StringColumn.ofAll(StringColumnId.of(IVehicleGenerator. TRANSITION_TYPE),
//	 * (Iterable<String>) transitionTypeList.iterator()); StringColumn
//	 * transitionColumn =
//	 * StringColumn.ofAll(StringColumnId.of(IVehicleGenerator.TRANSITION),
//	 * (Iterable<String>) transitionList.iterator()); IntColumn periodColumn =
//	 * IntColumn.ofAll(IntColumnId.of(IVehicleGenerator.PERIOD_SEQUENCE),
//	 * (IntStream) periodSequenceList.iterator()); StringColumn
//	 * generationModelColumn =
//	 * StringColumn.ofAll(StringColumnId.of(IVehicleGenerator.
//	 * GENERATION_MODEL), (Iterable<String>) generationModelList.iterator());
//	 * StringColumn parameterColumn =
//	 * StringColumn.ofAll(StringColumnId.of(IVehicleGenerator.PARAMETERS),
//	 * (Iterable<String>) parameterList.iterator()); IntColumn durationColumn =
//	 * IntColumn.ofAll(IntColumnId.of(IVehicleGenerator.DURATION), (IntStream)
//	 * durationList.iterator()); StringColumn speedColumn =
//	 * StringColumn.ofAll(StringColumnId.of(IVehicleGenerator.SPEED),
//	 * (Iterable<String>) speedList.iterator());
//	 */
//
//	// return vehicleGenMeta;
//	// }
//
	// to generate a dataframe String
	public String generateVehiclesMeta(IScope scope) {

		// get the metadata
		@SuppressWarnings("unchecked")
		ArrayList<String> timeInterval = (ArrayList<String>) scope.getListArg(IVehicleGenerator.TIME_INTERVAL);
		String transitionType = (String) scope.getArg(IVehicleGenerator.TRANSITION_TYPE, IType.LIST);
		ArrayList<String> vehicleTypes = (ArrayList<String>) scope.getListArg(IVehicleGenerator.VEHICLE_TYPES);
		// Matrix to Arraylist
		ArrayList<ArrayList<Float>> transition = (ArrayList<ArrayList<Float>>) scope
				.getListArg(IVehicleGenerator.TRANSITION);

		SpeedGenerator speed = (SpeedGenerator) scope.getArg(IVehicleGenerator.SPEED, SpeedGenerator.Id);
		ArrayList<PeriodHeadwayGenerator> THGenerator = (ArrayList<PeriodHeadwayGenerator>) scope
				.getListArg(IVehicleGenerator.TIME_HEADWAY);

		// send the metaData to R

		return null;
	}

	/**
	 * generate vehicles based on a count model
	 * @param scope
	 * @throws ParseException
	 */
	public void generateVehiclesCount(IScope scope) throws ParseException{
		// create the vehicle Type List
		if (transitionType.equals(IVehicleGenerator.TRANSITION_TYPE1)) {
					// if the user gives a list
					// create transition matrix
		
		}
		
		
		GamaDate timeStart = timeInterval.get(0);
		GamaDate timeEnd = timeInterval.get(1);
		long diffTime = Transformer.getDifference(timeStart, timeEnd);
		/**DateFormat sdf = new SimpleDateFormat("hh:mm");
		Date timeStart = sdf.parse(timeInterval.get(0));
		Date timeEnd = sdf.parse(timeInterval.get(1));
		// Get msec from each, and subtract.
		long diffTime = timeEnd.getTime() - timeStart.getTime();
		// change into seconds
		diffTime = diffTime / 1000;*/

		// now we need to know how many periods we'll be having in this time
		// interval
		// order periods
		Collections.sort(timeHeadway, new Comparator<PeriodHeadwayGenerator>() {
			@Override
			public int compare(PeriodHeadwayGenerator s1, PeriodHeadwayGenerator s2) {
				return Integer.compare(s1.getPeriodSequence(), s2.getPeriodSequence());
			}
		});
		
		long actualTime = 0;
		int periodSeq = 0;
		double periodExpire = 0;
		int vehicleQueue = 0;
		GamaDate nextDate = timeStart;
		double vehicleTimeHeadway = 0;
		GamaPoint initialPosition = null;
		// residu should be the duration for a period to finish if we have timeHeadway that does not complete the duration of a period
		double residu = 0;
		// choose a random first vehicle
		int previousVehicleType =  rnd.nextInt(vehicleTypes.size() - 1);
		while (actualTime < diffTime) {
			// generate the number of vehicles that are allowed to be generated in this period
			int numberVehicles = timeHeadway.get(periodSeq).generateNumberVehicles();
			
			// I am goning to generate a sequence of vehicle type that would be equal to the number of allowed vehicles
			ArrayList<String> vehicleTypeSequence = this.generateVehicleTypes(scope, previousVehicleType, (GamaMatrix<Double>) transition,
					numberVehicles);
			ArrayList<GamaPoint> positions = this.getPositions(numberVehicles);
			for(int i = 0 ; i < numberVehicles; i++){
				this.vehicleList.add(new Vehicle(vehicleTypeSequence.get(i), 0.0,
						0.0, 0.0, null, false, positions.get(i)));
			}
			// time headway
			vehicleList = timeHeadway.get(periodSeq).generateTimeHeadways(scope, vehicleList, nextDate);
			nextDate = vehicleList.lastValue(scope).getArrivalTime();
			vehicleList = speed.generateSpeeds(vehicleList);
			previousVehicleType = this.vehicleTypes.indexOf(vehicleList.lastValue(scope).getVehicleType());
			vehicleQueue += numberVehicles;
			// let's generaaaaate
			/*while (periodExpire <= timeHeadway.get(periodSeq).getDuration() && vehicleQueue < numberVehicles) {
				// generate vehicle Type
				int currentVehicleType = this.generateVehicleType(scope, previousVehicleType,
						(GamaMatrix<Double>) transition);
				
				// get the coordinate
				initialPosition = this.getPosition();
				// generate timeHeadway
				vehicleTimeHeadway = timeHeadway.get(periodSeq).generateTimeHeadway(scope, vehicleList,
						vehicleTypes.get(currentVehicleType));
				// add the time headway to consider time before period expires
				periodExpire += vehicleTimeHeadway;
				double vehicleSpeed = speed.generateSpeed(vehicleTypes.get(currentVehicleType),
						vehicleTypes.get(previousVehicleType));
				if (periodExpire <= timeHeadway.get(periodSeq).getDuration()) {
					// add the vehicle to the queue
					// add the vehicle to the queue
					nextDate = nextDate.plusMillis(residu*1000 + vehicleTimeHeadway*1000);
					residu = 0;
					Vehicle veh = new Vehicle(vehicleTypes.get(currentVehicleType), 0.0, 0.0, vehicleSpeed, nextDate,false, initialPosition);
					vehicleList.add(veh);
					previousVehicleType = currentVehicleType;
					vehicleQueue++;
				}
			}*/
			//residu =  periodExpire - nextDate.floatValue(scope);
			//vehicleNumber.add(vehicleQueue);
			//periodList.add(timeHeadway.get(periodSeq).getPeriodSequence());
			actualTime +=  timeHeadway.get(periodSeq).getDuration();
			periodExpire = 0;
			periodSeq = (periodSeq == timeHeadway.size() - 1) ? 0 : periodSeq++;
			
		}

		
	}
	
	/**
	 * Generate a sequence of positions according to a number of vehicles
	 * @param numberVehicles
	 * @return
	 */
	private ArrayList<GamaPoint> getPositions(int numberVehicles) {
		ArrayList<GamaPoint> positions = new ArrayList<GamaPoint>();
		for(int i =1; i <= numberVehicles; i++ ){
			positions.add(this.getPosition());
		}
		return positions;
	}

	/**
	 * Generate a sequence of vehicle types using the transition matrix
	 * @param scope
	 * @param previousVehicleType
	 * @param transition
	 * @param numberVehicles
	 * @return
	 */
	private ArrayList<String> generateVehicleTypes(IScope scope, int previousVehicleType, GamaMatrix<Double> transition, int numberVehicles) {
		ArrayList<String> _vehicleTypes = new ArrayList<String>();
		_vehicleTypes.add(this.vehicleTypes.get(previousVehicleType));
		for(int i =1; i <= numberVehicles; i++){
			vehicleTypes.add(this.vehicleTypes.get(this.generateVehicleType(scope, previousVehicleType, transition)));
			previousVehicleType = this.vehicleTypes.indexOf(_vehicleTypes.get(i-1));
		}
		return _vehicleTypes;
	}

	/**
	 * 
	 * Generate vehicles based on a headway model
	 * @param scope
	 * @throws ParseException
	 */
	public void generateVehiclesHeadway(IScope scope) throws ParseException {
		// get the metadata

		// create the vehicle Type List
		if (transitionType.equals(IVehicleGenerator.TRANSITION_TYPE1)) {
			// if the user gives a list
			// create transition matrix
		}
		/*
		 * else if(transitionType.equals(IVehicleGenerator.TRANSITION_TYPE2)){
		 * // the user is giving a matrix of probability transition
		 * 
		 * }else if(transitionType.equals(IVehicleGenerator.TRANSITION_TYPE3)){
		 * /// create sequence of vehicles
		 * 
		 * }else{ // error }
		 */
		
		GamaDate timeStart = timeInterval.get(0);
		GamaDate timeEnd = timeInterval.get(1);
		long diffTime = Transformer.getDifference(timeStart, timeEnd);
		/*DateFormat sdf = new SimpleDateFormat("hh:mm");
		Date timeStart = sdf.parse(timeInterval.get(0));
		Date timeEnd = sdf.parse(timeInterval.get(1));
		// Get msec from each, and subtract.
		long diffTime = timeEnd.getTime() - timeStart.getTime();
		// change into seconds
		diffTime = diffTime / 1000;*/

		// now we need to know how many periods we'll be having in this time
		// interval
		// order periods
		Collections.sort(timeHeadway, new Comparator<PeriodHeadwayGenerator>() {
			@Override
			public int compare(PeriodHeadwayGenerator s1, PeriodHeadwayGenerator s2) {
				return Integer.compare(s1.getPeriodSequence(), s2.getPeriodSequence());
			}
		});
		
		long actualTime = 0;
		int periodSeq = 0;
		double periodExpire = 0;
		int vehicleQueue = 0;
		GamaDate nextDate = timeStart;
		// residu should be the duration for a period to finish if we have timeHeadway that does not complete the duration of a period
		double residu = 0;
		double vehicleTimeHeadway = 0;
		GamaPoint initialPosition = null;
		// choose a random first vehicle
		int previousVehicleType =  rnd.nextInt(vehicleTypes.size() - 1);
		while (actualTime < diffTime) {
			// let's generaaaaate
			while (periodExpire <= timeHeadway.get(periodSeq).getDuration()) {
				// generate vehicle Type
				int currentVehicleType = this.generateVehicleType(scope, previousVehicleType,
						(GamaMatrix<Double>) transition);
				// get the coordinate
				initialPosition = this.getPosition();
				System.out.println("initial position "+initialPosition);
				// generate timeHeadway
				vehicleTimeHeadway = timeHeadway.get(periodSeq).generateTimeHeadway(scope, vehicleList,
						vehicleTypes.get(currentVehicleType), initialPosition);
				// add the time headway to consider time before period expires
				periodExpire += vehicleTimeHeadway;
				double vehicleSpeed = speed.generateSpeed(vehicleTypes.get(currentVehicleType), vehicleTypes.get(previousVehicleType));
				if (periodExpire <= timeHeadway.get(periodSeq).getDuration()) {
					// add the vehicle to the queue
					nextDate = nextDate.plusMillis(residu*1000 + vehicleTimeHeadway*1000);
					residu = 0;
					Vehicle veh = new Vehicle(vehicleTypes.get(currentVehicleType), 0.0, 0.0, vehicleSpeed, nextDate,false, initialPosition);
					vehicleList.add(veh);
					previousVehicleType = currentVehicleType;
					vehicleQueue++;
				}
			}
			
			//residu =  periodExpire - nextDate.floatValue(scope);
			//vehicleNumber.add(vehicleQueue);
			//periodList.add(timeHeadway.get(periodSeq).getPeriodSequence());
			actualTime +=  timeHeadway.get(periodSeq).getDuration();
			periodExpire = 0;
			periodSeq = (periodSeq == timeHeadway.size() - 1) ? 0 : periodSeq++;
			
		}
	}

	private GamaPoint getPosition() {
		// TODO Auto-generated method stub
		GamaPoint position = null;
		if(this.coordinatesChoice.equals(IVehicleGenerator.COORDINATE_CHOICE_RANDOM))
			position = this.coordinates.get(rnd.nextInt(this.coordinates.size()));
		
		return position;
	}

	// this will generate the next vehicle type in the queue
	private int generateVehicleType(IScope scope, int previous, GamaMatrix<Double> transition) {

		double r = rnd.nextDouble();
		//ArrayList<Float> transitions = transition.get(previous);
		IList<Double> transitions = transition.getRow(scope, previous);
		double max = Transformer.getMax(transitions);
		int next = 0;
		if(r >= max){
			// choose a vehicle at random
			next = rnd.nextInt(transitions.size() - 1);
		}else{
			next = transitions.indexOf(max);
		}
		return next;
	}

	@getter(IVehicleGenerator.TIME_INTERVAL)
	public IList<GamaDate> getTimeInterval() {
		return timeInterval;
	}

	@setter(IVehicleGenerator.TIME_INTERVAL)
	public void setTimeInterval(IList<GamaDate> timeInterval) {
		this.timeInterval = timeInterval;
	}

	@getter(IVehicleGenerator.COORDINATES)
	public IList<GamaPoint> getCoordinates() {
		return coordinates;
	}
	
	@setter(IVehicleGenerator.COORDINATES)
	public void setCoordinates(IList<GamaPoint> coordinates) {
		this.coordinates = coordinates;
	}

	@getter(IVehicleGenerator.TRANSITION_TYPE)
	public String getTransitionType() {
		return transitionType;
	}

	@setter(IVehicleGenerator.TRANSITION_TYPE)
	public void setTransitionType(String transitionType) {
		this.transitionType = transitionType;
	}

	@getter(IVehicleGenerator.VEHICLE_TYPES)
	public IList<String> getVehicleTypes() {
		return vehicleTypes;
	}
	@setter(IVehicleGenerator.VEHICLE_TYPES)
	public void setVehicleTypes(IList<String> vehicleTypes) {
		this.vehicleTypes = vehicleTypes;
	}

	@getter(IVehicleGenerator.TRANSITION)
	public IMatrix<Double> getTransition() {
		return transition;
	}
	@setter(IVehicleGenerator.TRANSITION)
	public void setTransition(IMatrix<Double>transition) {
		this.transition = transition;
	}
	
	@getter(IVehicleGenerator.SPEED)
	public SpeedGenerator getSpeed() {
		return speed;
	}
	@setter(IVehicleGenerator.SPEED)
	public void setSpeed(SpeedGenerator speed) {
		this.speed = speed;
	}

	@getter(IVehicleGenerator.TIME_HEADWAY)
	public IList<PeriodHeadwayGenerator> getTimeHeadway() {
		return timeHeadway;
	}
	
	@setter(IVehicleGenerator.TIME_HEADWAY)
	public void setTimeHeadway(IList<PeriodHeadwayGenerator> timeHeadway) {
		this.timeHeadway = timeHeadway;
	}

	@getter(IVehicleGenerator.VEHICLE_LIST)
	public IList<Vehicle> getVehicleList() {
		return vehicleList;
	}
	
	@setter(IVehicleGenerator.VEHICLE_LIST)
	public void setVehicleList(IList<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

	@getter(IVehicleGenerator.COORDINATES_CHOICE)
	public String getCoordinatesChoice() {
		return coordinatesChoice;
	}

	@setter(IVehicleGenerator.COORDINATES_CHOICE)
	public void setCoordinatesChoice(String coordinatesChoice) {
		this.coordinatesChoice = coordinatesChoice;
	}


	
	
	

}
