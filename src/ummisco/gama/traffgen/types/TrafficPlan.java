package ummisco.gama.traffgen.types;

import java.util.Map;

import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.var;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gaml.types.IType;


@vars({
	//@var(name = ITrafficScheduler.TRAFFIC_PERIODS, type = TrafficPeriodType.ID, doc = @doc("List of periods")),
	@var(name = ITrafficScheduler.NEXT, type = IType.AGENT, doc = @doc("next agent to create in scheduler")),
	@var(name = ITrafficScheduler.TYPE, type = IType.STRING, doc = @doc("Type of the scheduler"))
})


public class TrafficPlan {

	public static final int Id = IType.AVAILABLE_TYPES+4576;
	Map<Integer, TrafficScheduler> plan;
	
	int currentScheduler;
}
