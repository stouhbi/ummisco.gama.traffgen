package ummisco.gama.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import ch.netzwerg.paleo.Column;
import ch.netzwerg.paleo.DataFrame;
import msi.gama.runtime.IScope;
import msi.gama.util.GamaDate;
import msi.gama.util.IList;
import msi.gaml.operators.Dates;
import ummisco.gama.traffgen.species.Vehicle;
import umontreal.ssj.probdist.Distribution;
import umontreal.ssj.probdist.PoissonDist;

public class Transformer {

	public final static String PARETO_1 = "pareto1";
	public final static String PARETO_2 = "pareto2";
	public final static String PARETO_3 = "pareto3";
	public final static String PARETO_4 = "pareto4";
	public final static String EXPONENTIAL = "exponential";
	public final static String POISSON = "poisson";
	
	
	
	public final static String LAMBDA = "lambda";
	
 	
	public static String listToString(ArrayList list){
		String value = "";
		if(list.size()>1){
			value = (String) list.get(0);
		}
		for(int i = 1; i < list.size(); i++){
			value = value + "|" + (String) list.get(i);
		}
		
		return value;
	}
	
	public static String matrixToString(ArrayList matrix){
		String value = "";
		if(matrix.size()>1){
			value = listToString((ArrayList) matrix.get(0));
		}
		for(int i = 1; i < matrix.size(); i++){
			value = value + "-" + listToString((ArrayList) matrix.get(i));
		}
		return value;
	}
	
	public static String mapToString(Map map){
		String value = "";
		Set<String> keys = map.keySet();
		for(String key: keys){
			value = value + "|" + key + ":" + map.get(key);
		}
		if(value.charAt(0) == '|')
			value = value.substring(1, value.length());
		return value;
	}
	
	public static String distToString(Map<String, Object> model){
		String modelString = "";
		switch(((String)model.get("model")).toLowerCase()){
			case EXPONENTIAL:
				modelString = EXPONENTIAL.substring(0, 1).toUpperCase() + EXPONENTIAL.substring(1);
				modelString += "Dist"+"("+model.get(LAMBDA)+")";
				break;
			case POISSON:
				modelString = POISSON.substring(0, 1).toUpperCase() + POISSON.substring(1);
				modelString += "Dist"+"("+(Double)model.get(LAMBDA)+")";
				break;
			default:
				modelString = "";
		}	
		//return "PoissonDist("+params.get(LAMBDA)+")";
		return modelString;
	}

	public static double getMax(IList<Double> transitions) {
		double max  = 0;
		for(int i = 0; i < transitions.size(); i++){
			if(transitions.get(i) >= max)
				max = transitions.get(i) ;
		}
		return max;
	}

	public static long getDifference(GamaDate timeStart, GamaDate timeEnd) {
		long diff = (timeEnd.getHour() - timeStart.getHour())*3600;
		diff += (timeEnd.getMinute() - timeStart.getMinute())*60;
		System.out.println("diff equal "+diff);
		// change the diff to milliseconds
		//diff *=1000;
		return diff;
	}
	
	public static ArrayList<Integer> indexOfAll(GamaDate obj, ArrayList<Vehicle> list, IScope scope){
	    ArrayList<Integer> indexList = new ArrayList<Integer>();
	    GamaDate startingDate = scope.getExperiment().getSimulation().getStartingDate();
	    double now_millis = Dates.milliseconds_between(scope, startingDate, obj);
	    System.out.println("now "+now_millis);
	    for (int i = 0; i < list.size(); i++){
	    	double i_millis = Dates.milliseconds_between(scope, startingDate, list.get(i).getArrivalTime());
	    	if(Math.abs(now_millis - i_millis) <= 50){
	    		System.out.println("this matches "+i_millis);
	    		indexList.add(i);
	    	}     
	    }
	        
	    return indexList;
	}
	
	public static GamaDate getFirstDate(Set<GamaDate> dates) {
		GamaDate firstDate = null;
		if(dates.size()>1)
			firstDate = (GamaDate) dates.toArray()[0];
		for (GamaDate date : dates) {
			if (date.isSmallerThan(firstDate, false))
			firstDate = date;
		}
		return firstDate;
	}

	public static int getlastIndexof(ArrayList list, ArrayList values) {
		for(int i = 0; i < list.size(); i++){
			if(values.contains(list.get(i)))
				return i;
		}
		return -1;
	}
}
