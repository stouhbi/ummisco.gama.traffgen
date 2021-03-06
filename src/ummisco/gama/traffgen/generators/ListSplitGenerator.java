package ummisco.gama.traffgen.generators;


import msi.gama.metamodel.shape.IShape;
import msi.gama.runtime.IScope;
import msi.gaml.species.GamlSpecies;
import ummisco.gama.helpers.Transformer;

public class ListSplitGenerator extends SplitGenerator{

	double[] listOfRate;
	
	public ListSplitGenerator(AbstractGenerator[] gen, double[] choice) {
		super(gen);
		configureGenerator(choice);
	}

	private void configureGenerator(double[] choice) {
		listOfRate = choice;
		index = 0;
	}
	
	protected int getChoice(IScope scope) {
		double number = scope.getRandom().between(0.0, 1.0);
		double tmp = 0;
		int i = 0;
		for(i=0;i<listOfRate.length;i++){
			tmp+=listOfRate[i];
			if(number <= tmp ) return i;
		}
		
		return i==listOfRate.length ? Transformer.getMaxIndex(listOfRate):i;
	}

	
	public void lockFlow(double fl) {
		

	}

	
	protected AgentSeed nextElement(IScope scope, double currentDate, GamlSpecies spe, IShape location) {
		int choice = getChoice(scope);
		this.lastDate = Math.max(currentDate, lastDate);
		AgentSeed agt= generators[choice].nextElement(scope,lastDate,spe, location);
		if(agt!= null) this.lastDate = Math.max(agt.getActivationDate(), lastDate);
		return agt;
	}

	
	

}
