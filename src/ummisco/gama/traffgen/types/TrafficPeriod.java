package ummisco.gama.traffgen.types;

import msi.gama.metamodel.agent.IAgent;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gama.precompiler.GamlAnnotations.operator;
import msi.gama.precompiler.GamlAnnotations.var;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.runtime.IScope;
import msi.gaml.types.IType;

@vars({
	@var(name = ITrafficPeriod.GENERATOR, type = TrafficGeneratorType.ID, doc = @doc("the generator")),
	@var(name = ITrafficPeriod.INDIVIDUAL, type = IType.NONE, doc = @doc("number of allowed individuals")),
	@var(name = ITrafficPeriod.DURATION, type = IType.FLOAT, doc=@doc("duration of this period")),
	@var(name = ITrafficPeriod.NEXT, type = IType.AGENT, doc = @doc("next agent to create in traffic period")),
})

public class TrafficPeriod {
	
	public static final int Id = IType.AVAILABLE_TYPES+4570;
	
	
	TrafficGenerator generator;
	TrafficLaw individualsLaw;
	int individualsAllowed;
	int duration; 
	int individuals = 0;
	
	 
	public TrafficPeriod(TrafficGenerator generator, TrafficLaw individualsLaw, int duration) {
		super();
		this.generator = generator;
		this.individualsLaw = individualsLaw;
		this.duration = duration;
	}
	public TrafficPeriod(TrafficGenerator generator, int individual, int duration) {
		super();
		this.generator = generator;
		this.individualsAllowed = individual;
		this.duration = duration;
	}
	
	
	@operator(value=ITrafficPeriod.CREATE_PERIOD)
	@doc(value = "create a periode")
	public static TrafficPeriod createTrafficPeriod(final IScope scope, final TrafficGenerator gen, final Integer duration, final Integer nbIndividual){
		TrafficPeriod res= new TrafficPeriod(gen, nbIndividual.intValue(), duration.intValue());
		return res;
	}
	@operator(value=ITrafficPeriod.CREATE_PERIOD)
	@doc(value = "create a periode")
	public static TrafficPeriod createTrafficPeriod(final IScope scope, final TrafficGenerator gen, final Integer duration, final TrafficLaw nbIndividual){
		TrafficPeriod res= new TrafficPeriod(gen, nbIndividual, duration.intValue());
		return res;
	}
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	@getter(value=ITrafficPeriod.NEXT)
	@doc(value =" Next on the period")
	public IAgent getNext(IScope scope){
		if(individuals >= individualsAllowed){
			return null;
		}else{
			individuals++;
			return this.generator.getNext(scope);
		}
	}
	
	
	
	
}
