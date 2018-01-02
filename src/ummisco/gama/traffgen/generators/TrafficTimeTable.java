package ummisco.gama.traffgen.generators;

import msi.gama.metamodel.agent.IAgent;
import msi.gama.runtime.IScope;
import ummisco.gama.traffgen.types.TrafficLaw;

public abstract class TrafficTimeTable extends AbstractGenerator{
	AbstractGenerator generator;
	double currentDate;
	
	protected TrafficTimeTable(AbstractGenerator tf){
		this.generator = tf;
	}
	
	public AbstractGenerator getGenerators(){
		return this.generator;
	}
	
	public abstract IAgent next(IScope scope);
}
