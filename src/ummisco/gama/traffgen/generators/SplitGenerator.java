package ummisco.gama.traffgen.generators;

import java.util.ArrayList;
import java.util.List;

import msi.gama.metamodel.agent.IAgent;
import msi.gama.metamodel.shape.ILocation;
import msi.gama.metamodel.shape.IShape;
import msi.gama.runtime.IScope;
import msi.gaml.species.GamlSpecies;
import ummisco.gama.traffgen.types.ITrafficGenerator;

public abstract class SplitGenerator extends AbstractGenerator {
	protected AbstractGenerator[] generators;
	protected double[] lastCreationDates;
	double lastDate = 0;
	
	protected abstract int getChoice(IScope scope);
	public  abstract void lockFlow(double fl);
	
	SplitGenerator(AbstractGenerator[] gen) {
		this.generators = gen;
		lastCreationDates = new double[gen.length];
	}
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
	
	
	protected double maxDate() {
		double res = -1;
		for(double dte:lastCreationDates)
			res = Math.max(dte,res);
		return res;
	}
	
	
	@Override
	protected List<IShape> getManagedPositions() {
		ArrayList<IShape> allPositions = new ArrayList<IShape>();
		for(AbstractGenerator agt :generators) {
			for(IShape sp :agt.getManagedPositions()) {
				if(!allPositions.contains(sp))
					allPositions.add(sp);
			}
		}
		return allPositions;
	}
	
	public static double getMax(double[] choices) {
		double max = Double.MIN_VALUE;
		for(int i  = 0; i < choices.length; i++  ){
			if(max <= choices[i]) max = choices[i];
		}
		return max;
	}

}
