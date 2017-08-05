package ummisco.gama.traffgen.generators;

import msi.gama.runtime.IScope;
import ummisco.gama.traffgen.types.TrafficLaw;

public class ContinuousTrafficFlow extends TrafficTimeTable{

	public ContinuousTrafficFlow(TrafficLaw tf){
		super(tf);
	}
	
	@Override
	public double next(IScope scope) {
		return this.timeHeadwayLaw.getNext();
	}
	
}
