package ummisco.gama.traffgen.types;

import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.operator;
import msi.gama.precompiler.GamlAnnotations.var;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.runtime.IScope;
import msi.gaml.types.IType;

@vars({
	@var(name = ITrafficPeriod.GENERATOR, type = TrafficGeneratorType.ID, doc = @doc("list of parameters for the law")),
	@var(name = ITrafficPeriod.INDIVIDUAL, type = IType.NONE, doc = @doc("Name of the model")),
	@var(name = ITrafficPeriod.DURATION, type = IType.FLOAT, doc=@doc("Next value of the random generator"))
})

public class TrafficPeriod {
	TrafficGenerator generator;
	TrafficLaw individualsLaw;
	int individual;
	float duration; 
	
	
	public TrafficPeriod(TrafficGenerator generator, TrafficLaw individualsLaw, float duration) {
		super();
		this.generator = generator;
		this.individualsLaw = individualsLaw;
		this.duration = duration;
	}
	public TrafficPeriod(TrafficGenerator generator, int individual, float duration) {
		super();
		this.generator = generator;
		this.individual = individual;
		this.duration = duration;
	}
	
	
	@operator(value=ITrafficPeriod.CREATE_PERIOD)
	@doc(value = "create a periode")
	public static TrafficPeriod createTrafficPeriod(final IScope scope, final TrafficGenerator gen, final Double duration, final Integer nbIndividual){
		TrafficPeriod res= new TrafficPeriod(gen, nbIndividual.intValue(), duration.floatValue());
		return res;
	}
	@operator(value=ITrafficPeriod.CREATE_PERIOD)
	@doc(value = "create a periode")
	public static TrafficPeriod createTrafficPeriod(final IScope scope, final TrafficGenerator gen, final Double duration, final TrafficLaw nbIndividual){
		TrafficPeriod res= new TrafficPeriod(gen, nbIndividual, duration.floatValue());
		return res;
	}
	
	
}
