package ummisco.gama.traffgen.types;

import java.time.temporal.ChronoUnit;

import msi.gama.metamodel.agent.IAgent;
import msi.gama.metamodel.shape.IShape;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gama.precompiler.GamlAnnotations.operator;
import msi.gama.precompiler.GamlAnnotations.var;
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
import ummisco.gama.traffgen.generators.IGenerator;
import ummisco.gama.traffgen.generators.ListSplitGenerator;
import ummisco.gama.traffgen.generators.MapSplitGenerator;
import ummisco.gama.traffgen.generators.MatrixSplitGenerator;
import ummisco.gama.traffgen.generators.TransitionGenerator;

@vars({
	@var(name = ITrafficGenerator.NEXT, type = IType.AGENT, doc = @doc("next agent to create")),
	@var(name = ITrafficGenerator.PREVIOUS, type = IType.AGENT, doc = @doc("previous created agent")),
	@var(name = ITrafficGenerator.BUFFERED_PREVIOUS, type = IType.LIST, doc=@doc("previous created agent inside the buffer"))
})
public class TrafficGenerator {
	public static final int Id = IType.AVAILABLE_TYPES+4568;
	IGenerator generator;

	private TrafficGenerator(IGenerator g)
	{
		this.generator = g;
	}
	
	@operator(value=ITrafficGenerator.ATOMIC_TRAFFIC_GENERATOR)
	@doc(value = "create an atomic traffic generator")
	public static TrafficGenerator createAtomicGenerator(final IScope scope, GamlSpecies agentSpecies, final TrafficLaw thLaw , final TrafficLaw speedLaw , final IShape shp){
		AtomicGenerator gen = new AtomicGenerator(agentSpecies,thLaw,speedLaw,shp);
		return new TrafficGenerator(gen);
	}

	@operator(value=ITrafficGenerator.ATOMIC_TRAFFIC_GENERATOR)
	@doc(value = "create an atomic traffic generator")
	public static TrafficGenerator createAtomicGenerator(final IScope scope, GamlSpecies agentSpecies, final TrafficLaw thLaw , final TrafficLaw speedLaw){
		AtomicGenerator gen = new AtomicGenerator(agentSpecies,thLaw,speedLaw);
		return new TrafficGenerator(gen);
	}

	
	@operator(value=ITrafficGenerator.ATOMIC_TRAFFIC_GENERATOR)
	@doc(value = "create an atomic traffic generator")
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
	
	@getter(ITrafficGenerator.NEXT)
	public IAgent getNext(IScope scope) {
		double duration = scope.getSimulation().getClock().getStartingDate().until(scope.getSimulation().getClock().getCurrentDate(), ChronoUnit.MILLIS)/1000;
				
				
		//		getStepInMillis()*scope.getSimulation().getClock().getCycle();
		return generator.next(scope, duration);
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
	
	
}
