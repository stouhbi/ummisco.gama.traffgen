package ummisco.gama.traffgen.generators;

import java.util.ArrayList;
import java.util.List;

import msi.gama.metamodel.shape.IShape;
import msi.gama.runtime.IScope;
import msi.gaml.species.GamlSpecies;

public abstract class TransitionGenerator extends AbstractGenerator {
	
	protected AbstractGenerator[] generators;
	protected double[] lastCreationDates;

	public TransitionGenerator(AbstractGenerator[] gen) {
		// TODO Auto-generated constructor stub
	}
	
	protected abstract int getChoice(IScope scope);
	public  abstract void lockFlow(double fl);

	@Override
	protected List<GamlSpecies> getManagedSpecies() {
		ArrayList<GamlSpecies> allSpecies= new ArrayList<GamlSpecies> ();
		for(AbstractGenerator agt :generators) {
			for(GamlSpecies sp :agt.getManagedSpecies()) {
				if(!allSpecies.contains(sp))
					allSpecies.add(sp);
			}
		}
		return allSpecies;	
	}

}
