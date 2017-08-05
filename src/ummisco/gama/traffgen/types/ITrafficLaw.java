package ummisco.gama.traffgen.types;

import java.util.ArrayList;

public interface ITrafficLaw {

	public final static String TYPE_NAME 	= "traffgen_law";
	
	static final String PARETO_4_LAW = "pareto_4_law";
	static final String PARETO_3_LAW = "pareto_3_law";
	static final String PEARSON_5_LAW = "pearson_5_law";
	static final String EXPO_LAW = "exponential_law";
	static final String POISSON_LAW = "poisson_law";
	
	
	
	
	public final static String PEARSON_5 	= "PEAESON_5";
	public final static String PARETO_4 	= "PARETO_4";
	public final static String PARETO_3 	= "PARETO_3";
	public final static String EXPONENTIAL 	= "EXPONENTIAL";
	public final static String POISSON 		= "POISSON";
	
	public static final String MODEL 		= "model";
	public static final String NEXT 		= "next";
	public static final String ALPHA 		= "alpha";
	public static final String BETA 		= "beta";
	public static final String GAMMA 		= "gamma";
	public static final String SIGMA 		= "sigma";
	public static final String LAMBDA 		= "lambda";
	public static final String U 			= "u";
	
	public final static String[] privateData = {MODEL};
	
	//public final static String LAW_PARAMETERS = "law_parameters";
	public final static String PARAMETERS = "parameters";
}
