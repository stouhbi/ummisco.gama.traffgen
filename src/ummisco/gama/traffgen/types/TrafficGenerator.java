package ummisco.gama.traffgen.types;

import java.time.temporal.ChronoUnit;
import java.util.Map;

import msi.gama.metamodel.agent.IAgent;
import msi.gama.metamodel.shape.IShape;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gama.precompiler.GamlAnnotations.operator;
import msi.gama.precompiler.GamlAnnotations.variable;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.runtime.IScope;
import msi.gama.util.GamaListFactory;
import msi.gama.util.GamaMap;
import msi.gama.util.IList;
import msi.gama.util.matrix.IMatrix;
import msi.gaml.species.GamlSpecies;
import msi.gaml.types.IType;
import msi.gaml.types.Types;
import ummisco.gama.traffgen.generators.AbstractGenerator;
import ummisco.gama.traffgen.generators.AtomicGenerator;
import ummisco.gama.traffgen.generators.ContinuousTrafficFlow;
import ummisco.gama.traffgen.generators.DiscretTrafficFlow;
import ummisco.gama.traffgen.generators.IGenerator;
import ummisco.gama.traffgen.generators.ListSplitGenerator;
import ummisco.gama.traffgen.generators.LocationSplitGenerator;
import ummisco.gama.traffgen.generators.MapSplitGenerator;
import ummisco.gama.traffgen.generators.MatrixSplitGenerator;
import ummisco.gama.traffgen.generators.TrafficTimeTable;
import ummisco.gama.traffgen.generators.TransitionGenerator;

@vars({
	@variable(name = ITrafficGenerator.NEXT, type = IType.AGENT, doc = @doc("next agent to create")),
	@variable(name = ITrafficGenerator.PREVIOUS, type = IType.AGENT, doc = @doc("previous created agent")),
	@variable(name = ITrafficGenerator.BUFFERED_PREVIOUS, type = IType.LIST, doc=@doc("previous created agent inside the buffer"))
})
public class TrafficGenerator {
	public static final int Id = IType.AVAILABLE_TYPES+4568;
	IGenerator generator;

	private TrafficGenerator(IGenerator g)
	{
		this.generator = g;
	}
	
	@operator(value=ITrafficGenerator.ATOMIC_TRAFFIC_GENERATOR)
	@doc(value = "create an atomic traffic generator with one species and a shape location")
	public static TrafficGenerator createAtomicGenerator(final IScope scope, GamlSpecies agentSpecies, final TrafficLaw thLaw , final TrafficLaw speedLaw , final IShape shp){
		AtomicGenerator gen = new AtomicGenerator(agentSpecies,thLaw,speedLaw,shp);
		return new TrafficGenerator(gen);
	}

	@operator(value=ITrafficGenerator.ATOMIC_TRAFFIC_GENERATOR)
	@doc(value = "create an atomic traffic generator with one species")
	public static TrafficGenerator createAtomicGenerator(final IScope scope, GamlSpecies agentSpecies, final TrafficLaw thLaw , final TrafficLaw speedLaw){
		AtomicGenerator gen = new AtomicGenerator(agentSpecies,thLaw,speedLaw);
		return new TrafficGenerator(gen);
	}

	
	@operator(value=ITrafficGenerator.ATOMIC_TRAFFIC_GENERATOR)
	@doc(value = "create an atomic traffic generator with a list of species and a shape location")
	public static TrafficGenerator createAtomicGenerator(final IScope scope, IList<GamlSpecies> agentSpecies, final TrafficLaw thLaw , final TrafficLaw speedLaw , final IShape shp){
		GamlSpecies[] sps = new GamlSpecies[agentSpecies.size()];
		int i = 0;
		for(GamlSpecies sp:agentSpecies) {
			sps[i] = sp;
			i++;
		}
		AtomicGenerator gen = new AtomicGenerator(sps,thLaw,speedLaw,shp);
		return new TrafficGenerator(gen);
	}
	
	@operator(value=ITrafficGenerator.ATOMIC_TRAFFIC_GENERATOR)
	@doc(value = "create an atomic traffic generator with one species and vehicle flow, duration ")
	public static TrafficGenerator createAtomicGenerator(final IScope scope, GamlSpecies agentSpecies, final TrafficLaw thLaw , final TrafficLaw speedLaw , final TrafficLaw vehicleFlow, int duration){
		AtomicGenerator gen = new AtomicGenerator(agentSpecies,thLaw,speedLaw,vehicleFlow, duration);
		return new TrafficGenerator(gen);
	}
	
	@operator(value=ITrafficGenerator.ATOMIC_TRAFFIC_GENERATOR)
	@doc(value = "create an atomic traffic generator with one species vehicle flow, duration and a shape location")
	public static TrafficGenerator createAtomicGenerator(final IScope scope, GamlSpecies agentSpecies, final TrafficLaw thLaw , final TrafficLaw speedLaw , final TrafficLaw vehicleFlow, int duration, final IShape shp){
		AtomicGenerator gen = new AtomicGenerator(agentSpecies,thLaw,speedLaw,vehicleFlow, duration, shp);
		return new TrafficGenerator(gen);
	}
	
	
	@operator(value=ITrafficGenerator.ATOMIC_TRAFFIC_GENERATOR)
	@doc(value = "create an atomic traffic generator with a list of species, vehicle flow, duration and a shape location")
	public static TrafficGenerator createAtomicGenerator(final IScope scope, IList<GamlSpecies> agentSpecies, final TrafficLaw thLaw , final TrafficLaw speedLaw , final TrafficLaw vehicleFlow, int duration, final IShape shp){
		GamlSpecies[] sps = new GamlSpecies[agentSpecies.size()];
		int i = 0;
		for(GamlSpecies sp:agentSpecies) {
			sps[i] = sp;
			i++;
		}
		AtomicGenerator gen = new AtomicGenerator(sps,thLaw,speedLaw,vehicleFlow, duration, shp);
		return new TrafficGenerator(gen);
	}
	
	@operator(value=ITrafficGenerator.ATOMIC_TRAFFIC_GENERATOR)
	@doc(value = "create an atomic traffic generator with a list of species, vehicle flow, duration and a shape location")
	public static TrafficGenerator createAtomicGenerator(final IScope scope, IList<GamlSpecies> agentSpecies, final TrafficLaw thLaw , final TrafficLaw speedLaw , final TrafficLaw vehicleFlow, int duration){
		GamlSpecies[] sps = new GamlSpecies[agentSpecies.size()];
		int i = 0;
		for(GamlSpecies sp:agentSpecies) {
			sps[i] = sp;
			i++;
		}
		AtomicGenerator gen = new AtomicGenerator(sps,thLaw,speedLaw,vehicleFlow, duration);
		return new TrafficGenerator(gen);
	}

	@operator(value=ITrafficGenerator.ATOMIC_TRAFFIC_GENERATOR)
	@doc(value = "create an atomic traffic generator")
	public static TrafficGenerator createAtomicGenerator(final IScope scope, IList<GamlSpecies> agentSpecies, final TrafficLaw thLaw , final TrafficLaw speedLaw){
		GamlSpecies[] sps = new GamlSpecies[agentSpecies.size()];
		int i = 0;
		for(GamlSpecies sp:agentSpecies) {
			sps[i] = sp;
			i++;
		}
		AtomicGenerator gen = new AtomicGenerator(sps,thLaw,speedLaw);
		return new TrafficGenerator(gen);
	}

	
	
	
	@operator(value=ITrafficGenerator.MAP_TRAFFIC_GENERATOR)
	@doc(value = "create a map traffic generator")
	public static TrafficGenerator createMapTrafficGenerator(final IScope scope, final IList<TrafficGenerator> generators, final GamaMap<GamlSpecies,Double> nexts){
		AbstractGenerator[] gen = new AbstractGenerator[generators.size()];
		for(int i = 0; i< gen.length; i++) {
			gen[i] = (AbstractGenerator)generators.get(i).generator;
		}
		/*System.out.println("Length of nexts "+nexts.size());
		for (Map.Entry<GamlSpecies, Double> entry : nexts.entrySet())
		{
		    System.out.println("key: " + entry.getKey() + "; value: " + entry.getValue());
		}*/
		MapSplitGenerator res = new MapSplitGenerator(gen,nexts);
		return new TrafficGenerator(res);
	}
	

	@operator(value=ITrafficGenerator.SYNCHRONIZED_TRAFFIC_GENERATOR)
	@doc(value = "create a list traffic generator")
	public static TrafficGenerator createSynchronizeTrafficGenerator(final IScope scope, final IList<TrafficGenerator> generators, final IList<Double> nexts){
		AbstractGenerator[] gen = new AbstractGenerator[generators.size()];
		double[] choice = new double[generators.size()];
		for(int i = 0; i< gen.length; i++) {
			gen[i] = (AbstractGenerator)generators.get(i).generator;
			choice[i] = nexts.get(i).floatValue();
		}
		ListSplitGenerator res = new ListSplitGenerator(gen,choice);
		return new TrafficGenerator(res);
	}
	
	@operator(value=ITrafficGenerator.SYNCHRONIZED_TRAFFIC_GENERATOR)
	@doc(value = "create a matrix transition traffic generator")
	public static TrafficGenerator createSynchronizeTrafficGenerator(final IScope scope, final IList<TrafficGenerator> generators, final IMatrix<Double> nexts){
		AbstractGenerator[] gen = new AbstractGenerator[generators.size()];
		double[][] choice = new double[generators.size()][generators.size()];
		for(int i = 0; i< gen.length; i++) {
			gen[i] = (AbstractGenerator)generators.get(i).generator;
			for(int j=0;j<gen.length;j++)
				choice[i][j] = nexts.get(scope, j, i).floatValue();
		}
		MatrixSplitGenerator res = new MatrixSplitGenerator(gen,choice);
		return new TrafficGenerator(res);
	}
	
	@operator(value=ITrafficGenerator.SYNCHRONIZED_TRAFFIC_GENERATOR)
	@doc(value = "create a matrix transition traffic generator")
	public static TrafficGenerator createSynchronizeTrafficGenerator(final IScope scope, final IList<TrafficGenerator> generators, final IMatrix<Double> nexts, final IList<GamlSpecies> species){
		AbstractGenerator[] gen = new AbstractGenerator[generators.size()];
		double[][] choice = new double[nexts.getRow(scope, 0).length(scope)][nexts.getRow(scope, 0).length(scope)];
		
		for(int i = 0; i< gen.length; i++) {
			gen[i] = (AbstractGenerator)generators.get(i).generator;
			
		}
		
		
		for(int i = 0; i < choice.length; i++)
			for(int j = 0; j < choice[i].length; j++) 
				choice[i][j] = nexts.get(scope, j, i).floatValue();
		
		
		MatrixSplitGenerator res = new MatrixSplitGenerator(gen,choice, species);
		return new TrafficGenerator(res);
	}
	
	
	@operator(value=ITrafficGenerator.LOCATION_GENERATOR)
	@doc(value = "create a location traffic generator - we use only map in this case")
	public static TrafficGenerator createLocationTrafficGenerator(final IScope scope, final IList<TrafficGenerator> generators, final GamaMap<IShape,Double> choice){
		AbstractGenerator[] gen = new AbstractGenerator[generators.size()];
		for(int i = 0; i< gen.length; i++) {
			gen[i] = (AbstractGenerator)generators.get(i).generator;
		}
		LocationSplitGenerator res = new LocationSplitGenerator(gen,choice);
		return new TrafficGenerator(res);
	}

	
	
	/*@operator(value=ITrafficGenerator.DISCRET_TRAFFC_GENERATOR)
	@doc(value = "create a trafficTimeTable - DiscretTafficFlow")
	public static TrafficGenerator createDiscretTrafficGenerator(final IScope scope, final TrafficGenerator generator, TrafficLaw vehicleFlow, int duration){
		
		TrafficTimeTable timeTable = new DiscretTrafficFlow((AbstractGenerator) generator.generator, vehicleFlow, duration);
		return new TrafficGenerator(timeTable);
	}
	
	@operator(value=ITrafficGenerator.CONTINUOUS_TRAFFIC_GENERATOR)
	@doc(value = "create a trafficTimeTable - ContinuousTrafficFlow")
	public static TrafficGenerator createContinuousTrafficGenerator(final IScope scope, final TrafficGenerator generator){
		
		TrafficTimeTable timeTable = new ContinuousTrafficFlow((AbstractGenerator) generator.generator);
		return new TrafficGenerator(timeTable);
	}*/
	
	
	
	@getter(ITrafficGenerator.NEXT)
	public IAgent getNext(IScope scope) {
		double currentTime = (double) scope.getExperiment().getSimulation().getStartingDate().until(scope.getExperiment().getSimulation().getCurrentDate(), ChronoUnit.MILLIS)/1000;
		
		//		getStepInMillis()*scope.getSimulation().getClock().getCycle();
		return generator.next(scope, currentTime);
	}

	@getter(ITrafficGenerator.PREVIOUS)
	public IAgent getPreviousOne(IScope scope) {
		return generator.previous(1);
	}
	
	@getter(ITrafficGenerator.PREVIOUS)
	public IList<IAgent> getPreviousBuffer(IScope scope) {
		IList<IAgent> mlist = GamaListFactory.create(Types.AGENT, generator.bufferSize());
		for(int i=0;i<generator.bufferSize();i++)
			mlist.add(generator.previous(i-1));
		return mlist;
	}
	
	
	/*@operator(value=ITrafficPeriod.CREATE_PERIOD)
	@doc(value = "create a periode")
	public static TrafficGenerator createTrafficPeriod(final IScope scope, final TrafficGenerator gen, final Integer duration){
		TrafficPeriod res= new TrafficPeriod(gen, 0 , duration.intValue());
		return  new TrafficGenerator(null);
	}
	
	
	@operator(value= ITrafficScheduler.CREATE_SCHEDULE)
	@doc(value= "to create a scheduler")
	public static TrafficGenerator createSchedule(final IList<TrafficPeriod> pds, final String type){
		TrafficPeriod[] periods = new TrafficPeriod[pds.size()];
		int i = 0;
		for(TrafficPeriod period: pds){
			periods[i] = period;
			i++;
		}

		//return new TrafficScheduler(periods, type);
		return new TrafficGenerator(null);
	}*/
	
}
