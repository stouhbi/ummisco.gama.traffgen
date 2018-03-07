package ummisco.gama.traffgen.types;

import java.util.ArrayList;

public interface ITrafficLaw {

	public final static String TYPE_NAME 	= "traffgen_law";
	
	static final String PARETO_4_LAW = "pareto_4_law";
	static final String PARETO_3_LAW = "pareto_3_law";
	
	static final String PEARSON_3_LAW = "pearson_3_law";
	static final String PEARSON_4_LAW = "pearson_4_law";
	static final String PEARSON_5_LAW = "pearson_5_law";
	static final String PEARSON_6_LAW = "pearson_6_law";
	
	public static final String UNIFORM_LAW = "uniform_law";
	
	static final String EXPO_LAW = "exponential_law";
	static final String SHIFTED_EXPO_LAW = "shifted_exponential_law";
	
	static final String POISSON_LAW = "poisson_law";
	
	public static final String INVERSE_GAMMA_LAW = "inverse_gamma_law";
	public static final String GAMMA_LAW = "gamma_law";
	
	public static final String LOGNORMAL_LAW 		= "lognormal_law";
	public static final String SHIFTED_LOGNORMAL_LAW 		= "shifted_lognormal_law";
	
	
	
	public final static String PEARSON_3 	= "PEAESON_3";
	public final static String PEARSON_4 	= "PEAESON_4";
	public final static String PEARSON_5 	= "PEAESON_5";
	public final static String PEARSON_6 	= "PEAESON_6";
		
	public final static String PARETO_4 	= "PARETO_4";
	public final static String PARETO_3 	= "PARETO_3";
	
	public final static String EXPONENTIAL 	= "EXPONENTIAL";
	public final static String SHIFTED_POISSON 		= "SHIFTED_POISSON";
	public final static String POISSON 		= "POISSON";
	
	
	public static final String GAMMAM 		= "GAMMA";
	public static final String INVERSE_GAMMA 		= "INVERSE_GAMMA";
	
	
	public static final String LOGNORMAL 		= "LOGNORMAL";
	public static final String SHIFTED_LOGNORMAL 		= "SHIFTED_LOGNORMAL";
	
	public static final String UNIFORM = "UNIFORM";
	
	public static final String MODEL 		= "model";
	public static final String NEXT 		= "next";
	public static final String ALPHA 		= "alpha";
	public static final String BETA 		= "beta";
	public static final String GAMMA 		= "gamma";
	public static final String MIN 		= "min";
	public static final String MAX 		= "max";
	
	public static final String SIGMA 		= "sigma";
	public static final String LAMBDA 		= "lambda";
	public static final String U 			= "u";
	public static final String ALPHA2 = "alpha2";
	public static final String MEANLOG = "meanlog";
	public static final String SDLOG = "sdlog";
	
	public final static String[] privateData = {MODEL};
	
	//public final static String LAW_PARAMETERS = "law_parameters";
	public final static String PARAMETERS = "parameters";

	

	
}
