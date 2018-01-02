package ummisco.gama.traffgen.generators;

import java.util.List;

import msi.gama.metamodel.agent.IAgent;
import msi.gama.metamodel.shape.IShape;
import msi.gama.runtime.IScope;
import msi.gaml.species.GamlSpecies;

public class ContinuousTrafficFlow extends TrafficTimeTable{

	public ContinuousTrafficFlow(AbstractGenerator tf){
		super(tf);
	}
	
	@Override
	public IAgent next(IScope scope) {
		IAgent tmp = this.generator.next(scope, this.currentDate);
		this.currentDate = ((AgentSeed) tmp).getActivationDate();
		return tmp;
	}

	@Override
	public void lockFlow(double fl) {
		
		
	}

	@Override
	protected AgentSeed nextElement(IScope scope, double lastDate, GamlSpecies spe, IShape location) {
		return this.generator.nextElement(scope, lastDate, spe, location);
	}

	@Override
	protected List<GamlSpecies> getManagedSpecies() {
		return this.getManagedSpecies();
	}
	
}
