package ummisco.gama.traffgen.generators;

import msi.gama.runtime.IScope;
import ummisco.gama.traffgen.types.TrafficLaw;

public abstract class TrafficTimeTable {
	TrafficLaw timeHeadwayLaw = null;
	
	protected TrafficTimeTable(TrafficLaw t){
		this.timeHeadwayLaw = t;
	}
	
	public TrafficLaw getLaw(){
		return this.timeHeadwayLaw;
	}
	
	public abstract double next(IScope scope);
}