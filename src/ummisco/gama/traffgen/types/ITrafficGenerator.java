package ummisco.gama.traffgen.types;

public interface ITrafficGenerator {

	public final static String TYPE_NAME 						= "traffgen_gen";
	
	public final static String ACTIVATION_DATE 					= "activated_at";
	
	
	public final static String NEXT 							= "next";
	public final static String FLOW 							= "flow";
	
	public final static String PREVIOUS 						= "previous";
	public final static String BUFFERED_PREVIOUS 				= "buffered_previous";
	
	
	public final static String ATOMIC_TRAFFIC_GENERATOR 		= "atomic_traffgen";
	public final static String SCHEDULED_TRAFFIC_GENERATOR 		= "scheduled_traffgen";
	public final static String SYNCHRONIZED_TRAFFIC_GENERATOR 	= "synchronized_traffgen";
	public final static String MAP_TRAFFIC_GENERATOR 	= "map_traffgen";
	
}
