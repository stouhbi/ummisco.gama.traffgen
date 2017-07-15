package ummisco.gama.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import cern.jet.random.Exponential;
import ch.netzwerg.paleo.Column;
import ch.netzwerg.paleo.DataFrame;
import msi.gama.metamodel.shape.GamaPoint;
import msi.gama.runtime.IScope;
import msi.gama.util.GamaDate;
import msi.gama.util.GamaListFactory;
import msi.gama.util.IList;
import msi.gaml.operators.Dates;
import ummisco.gama.distributions.Pareto3Dist;
import ummisco.gama.distributions.Pareto3Gen;
import ummisco.gama.distributions.Pareto4Dist;
import ummisco.gama.distributions.Pareto4Gen;
import ummisco.gama.distributions.Pearson5Distr;
import ummisco.gama.distributions.Pearson5Gene;
import ummisco.gama.traffgen.types.Vehicle;
import umontreal.ssj.probdist.Distribution;
import umontreal.ssj.probdist.ExponentialDist;

import umontreal.ssj.probdist.Pearson6Dist;
import umontreal.ssj.probdist.PoissonDist;
import umontreal.ssj.randvar.ExponentialGen;

import umontreal.ssj.randvar.Pearson6Gen;
import umontreal.ssj.randvar.PoissonGen;
import umontreal.ssj.randvar.RandomVariateGen;
import umontreal.ssj.rng.AntitheticStream;
import umontreal.ssj.rng.F2NL607;
import umontreal.ssj.rng.GenF2w32;
import umontreal.ssj.rng.LFSR113;
import umontreal.ssj.rng.LFSR258;
import umontreal.ssj.rng.MRG32k3a;
import umontreal.ssj.rng.RandomStream;

public class Transformer {

	public final static String PARETO_1 = "pareto1";
	public final static String PARETO_2 = "pareto2";
	public final static String PARETO_3 = "pareto3";
	public final static String PARETO_4 = "pareto4";
	public final static String EXPONENTIAL = "exponential";
	public final static String POISSON = "poisson";

	public final static String PEARSON_1 = "pearson1";
	public final static String PEARSON_2 = "pearson2";
	public final static String PEARSON_3 = "pearson3";
	public final static String PEARSON_4 = "pearson4";
	public final static String PEARSON_5 = "pearson5";
	public final static String PEARSON_6 = "pearson6";
	public final static String PEARSON_7 = "pearson7";






	public final static String LAMBDA = "lambda";
	public final static String ALPHA = "alpha";
	public final static String SIGMA = "sigma";
	public final static String GAMMA = "gamma";
	public final static String BETA = "beta";
	public final static String U = "u";

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
		System.out.println(((String)model.get("model")).toLowerCase());
		switch(((String)model.get("model")).toLowerCase()){
		case EXPONENTIAL:
			modelString = EXPONENTIAL.substring(0, 1).toUpperCase() + EXPONENTIAL.substring(1);
			modelString += "Dist"+"("+model.get(LAMBDA)+")";
			break;
		case POISSON:
			modelString = POISSON.substring(0, 1).toUpperCase() + POISSON.substring(1);
			modelString += "Dist"+"("+(Double)model.get(LAMBDA)+")";
			break;
		case PARETO_4:
			modelString = PARETO_4.substring(0,1).toUpperCase() + PARETO_4.substring(1);
			modelString += "Dist"+"(" + (Double)model.get(ALPHA)+","+(Double)model.get(GAMMA)+","+(Double)model.get(SIGMA)+
					","+(Double)model.get(U)+")";
			break;
		default:
			modelString = "";
		}	
		//return "PoissonDist("+params.get(LAMBDA)+")";
		System.out.println("modelString  "+modelString);
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

	public static IList<Integer> indexOfAll(GamaDate obj, ArrayList<Vehicle> list, IScope scope){
		IList<Integer> indexList = GamaListFactory.create();
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

	public static RandomVariateGen getGenerator(Map<String, Object> model, RandomStream s) {
		RandomVariateGen genModel;
		System.out.println(((String)model.get("model")).toLowerCase());
		switch(((String)model.get("model")).toLowerCase()){
		case EXPONENTIAL:
			System.out.println("Exponential");
			ExponentialDist dist1 = new ExponentialDist((Double)model.get(LAMBDA));
			genModel = new ExponentialGen(s, dist1);
			break;
		case POISSON:
			System.out.println("Poisson");
			PoissonDist dist2 = new PoissonDist((Double)model.get(LAMBDA));
			RandomStream st = new F2NL607();
			genModel = new PoissonGen(st, dist2);
			break;
		case PARETO_3:
			System.out.println("Pareto 3");
			Pareto3Dist dist = new Pareto3Dist((Double)model.get(U), (Double)model.get(GAMMA), (Double)model.get(BETA));
			genModel = new Pareto3Gen(s, dist);
			break;
		case PARETO_4:
			System.out.println("Pareto 4");
			Pareto4Dist dist3 = new Pareto4Dist((Double)model.get(ALPHA), (Double)model.get(GAMMA), (Double)model.get(SIGMA), (Double)model.get(U));
			RandomStream str = new GenF2w32();
			genModel = new Pareto4Gen(str, dist3);
			break;
		case PEARSON_5:
			System.out.println(" Pearson 5");
			Pearson5Distr dist4 = new Pearson5Distr((Double) model.get(ALPHA), (Double)model.get(BETA), (Double)model.get(U));
			RandomStream str1 = new MRG32k3a();
			genModel = new Pearson5Gene(s, dist4);
			break;
		case PEARSON_6:
			System.out.println(" Pearson 6");
			Pearson6Dist dist5 = new Pearson6Dist((Double)model.get(ALPHA), (Double)model.get(ALPHA), (Double)model.get(BETA));
			genModel = new Pearson6Gen(s, dist5);
			break;
		default:
			genModel = null;
		}	
		return genModel;
	}

	public static double getMax(Map<GamaPoint, Double> values) {
		double max = 0;
		for (Map.Entry<GamaPoint, Double> entry : values.entrySet()){
			if(values.get(entry.getKey()) >= max)
				max = values.get(entry.getKey());
		}
		return max;
	}

	public static double getMin(Map<GamaPoint, Double> values) {
		double min = Double.MAX_VALUE;
		for (Map.Entry<GamaPoint, Double> entry : values.entrySet()){
			if(values.get(entry.getKey()) <= min)
				min = values.get(entry.getKey());
		}
		return min;
	}

	public static Map<GamaPoint, Double> reset(Map<GamaPoint, Double> values) {
		for (Map.Entry<GamaPoint, Double> entry : values.entrySet()){
			values.put(entry.getKey(), 0.0);
		}
		return values;
	}

	public static GamaDate getMaxDate(Map<GamaPoint, GamaDate> values) {
		List<GamaPoint> list = new ArrayList<GamaPoint>(values.keySet());
		GamaDate maxDate = values.get(list.get(new Random().nextInt(list.size())));
		for (Map.Entry<GamaPoint, GamaDate> entry : values.entrySet()){
			if(entry.getValue().isGreaterThan(maxDate, false));
			maxDate = values.get(entry.getKey());
		}
		return maxDate;
	}
}
