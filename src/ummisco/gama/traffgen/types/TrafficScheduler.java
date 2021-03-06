package ummisco.gama.traffgen.types;

import java.time.temporal.ChronoUnit;

import msi.gama.metamodel.agent.IAgent;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gama.precompiler.GamlAnnotations.operator;
import msi.gama.precompiler.GamlAnnotations.variable;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.runtime.IScope;
import msi.gama.util.IList;
import msi.gaml.types.IType;

@vars({
	//@variable(name = ITrafficScheduler.TRAFFIC_PERIODS, type = TrafficPeriodType.ID, doc = @doc("List of periods")),
	@variable(name = ITrafficScheduler.NEXT, type = IType.AGENT, doc = @doc("next agent to create in scheduler")),
	@variable(name = ITrafficScheduler.TYPE, type = IType.STRING, doc = @doc("Type of the scheduler"))
})

public class TrafficScheduler {

	public static final int Id = IType.AVAILABLE_TYPES+4571;
	TrafficPeriod[] periods;

	String type; // sequence or cycle

	int currentPeriod;
	double currentTick;
	double time;
	int duration;

	public TrafficScheduler(TrafficPeriod[] periods, String type) {
		init(periods, type);
	}
	
	public TrafficScheduler(TrafficPeriod[] periods, String type, int duration) {
		init(periods, type);
		this.duration= duration;
	}
	
	private void init(TrafficPeriod[] periods, String type){
		this.type = type;
		this.periods = periods;
		currentPeriod = periods.length > 0 ? 0:-1;
		currentTick = 0;
		time = 0;
	}

	@operator(value= ITrafficScheduler.CREATE_SCHEDULE)
	@doc(value= "to create a scheduler")
	public static TrafficScheduler createSchedule(final IList<TrafficPeriod> pds, final String type){
		TrafficPeriod[] periods = new TrafficPeriod[pds.size()];
		int i = 0;
		for(TrafficPeriod period: pds){
			periods[i] = period;
			i++;
		}

		return new TrafficScheduler(periods, type);

	}

	@getter(value=ITrafficScheduler.NEXT)
	@doc(value="get the next vehicle from the scheduler")
	public IAgent getNext(IScope scope){
		//if(isFinished(scope)) return null;
		double currentTime = (double) scope.getSimulation().getClock().getStartingDate().until(scope.getSimulation().getClock().getCurrentDate(), ChronoUnit.MILLIS)/1000;
		System.out.println("current Time is "+ currentTime);
		if(this.type.equals(ITrafficScheduler.TYPE_SEQUENCE))
			return scheduleSequence(scope,currentTime);
		else if(this.type.equals(ITrafficScheduler.TYPE_CYCLIC))
			return scheduleCycle(scope, currentTime);
		double step = (double) scope.getExperiment().getSimulation().getTimeStep(scope);
		time += step;
		return null;

	}

	public IAgent scheduleSequence(IScope scope, double currentTime){
		double duration = (double) periods[currentPeriod].getDuration();
		System.out.println("Period "+currentPeriod + "currentTick "+currentTick);
		if(Math.round(currentTick-0.5)%duration==0 & Math.round(currentTick-0.5)>=1){ // this period has finished, go to next
			System.out.println("Period "+currentPeriod+ " is finished");
			currentPeriod++;
			currentTick = 0;
			if(currentPeriod >= periods.length) return null;
		}

		double step = (double) scope.getExperiment().getSimulation().getTimeStep(scope);

		currentTick += step; 

		return periods[currentPeriod].getNext(scope);
	}

	public IAgent scheduleCycle(IScope scope, double currentTime){
		int duration = periods[currentPeriod].getDuration();
		System.out.println("Period "+currentPeriod + "currentTick "+currentTick);
		if(Math.round(currentTick-0.5)%duration==0 & Math.round(currentTick-0.5)>=1){ // this period has finished, go to next
			System.out.println("Period "+currentPeriod+ " is finished");
			currentPeriod++;
			if(currentPeriod >= periods.length) currentPeriod=0;

			currentTick = 0;
		}

		double step = (double) scope.getExperiment().getSimulation().getTimeStep(scope);

		currentTick += step; 
		return periods[currentPeriod].getNext(scope);
	}
	
	boolean isFinished(IScope scope){
		if(Math.round(time) == duration) return true;
		
		return false;
	}


}
