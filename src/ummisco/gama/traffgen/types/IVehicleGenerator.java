package ummisco.gama.traffgen.types;

public interface IVehicleGenerator {

	public static Double MIN_HEADWAY = 0.05;
	// Variable names
	public final static String PERIOD_SEQUENCE = "periodSequence";
	public final static String HEADWAY_GENERATION_MODEL = "headwayGenerationModel";
	public final static String COUNT_GENERATION_MODEL = "countGenerationModel";
	public final static String PARAMETERS = "parameters";
	public final static String DURATION = "duration";
	public final static String MEAN_SPEED = "meanSpeed";
	public final static String TIME_INTERVAL = "timeInterval";
	public final static String COORDINATES = "coordinates";
	public final static String TRANSITION_TYPE = "transitionType";
	public final static String VEHICLE_TYPES = "vehicleTypes";
	public final static String TRANSITION = "transition";
	public final static String SPEED = "speed";
	public final static String TIME_HEADWAY = "timeHeadway";
	public final static String VEHICLE_TYPES_TH = "vehicleTypes";
	//public final static String PERIODS_TH = "periods";
	public final static String THGENERATOR_PERIOD = "THGenerators";
	
	public final static String VEHICLE_LIST = "vehicleList";
	public final static String SPEED_LIST = "speedList";
	public final static String TH_LIST = "timeHeadwayList";
	public final static String PERIOD_LIST = "periodList";
	public final static String VEHICLE_NUMBER = "vehicleNumber";
	
	// Type names
	public final static String PERIOD_TYPE = "period_generator";
	public final static String SPEED_TYPE = "speed_generator";
	public final static String VEHICLEGENERATOR_TYPE = "vehicle_generator";
	public final static String TIME_HEADWAY_TYPE = "timeHeadway";
	
	
	// Keywords
	public final static String TRANSITION_TYPE1 = "list";
	public final static String TRANSITION_TYPE2 = "matrix";
	public final static String TRANSITION_TYPE3 = "sequence";
	
	
	public final static String HEADWAY_METHOD = "headway";
	public final static String COUNT_METHOD = "count";
	
	public final static String COORDINATES_CHOICE = "coordinate_choice";
	
	public final static String COORDINATE_CHOICE_RANDOM = "random";
}
