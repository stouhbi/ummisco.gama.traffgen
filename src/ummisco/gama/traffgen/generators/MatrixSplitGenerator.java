package ummisco.gama.traffgen.generators;

import msi.gama.metamodel.shape.IShape;
import msi.gama.runtime.IScope;
import msi.gama.util.matrix.IMatrix;
import msi.gaml.species.GamlSpecies;

public class MatrixSplitGenerator  extends SplitGenerator{
	
	double[][] listOfRate;
	GamlSpecies listOfSpecies;
	double lastDate = 0;;
	int previous = 0;

	public MatrixSplitGenerator(AbstractGenerator[] gen, double[][] choice) {
		super(gen);
		configureGenerator(choice);
	}

	
	private void configureGenerator(double[][] choice) {
		listOfRate = choice;
		for(int i = 0; i < listOfRate.length; i++){
			for(int j = 0; j < listOfRate[i].length; j++){
				System.out.println("line "+i+" column "+j  + " rate " + listOfRate[i][j]);
			}
		}
	}


	protected int getChoice(IScope scope) {
		double[] choices = listOfRate[previous];
		double number = scope.getRandom().between(0.0, 1.0);
		double tmp = 0;
		int choice = 0;
		for(int i = 0; i < choices.length; i++){
			tmp += choices[i];
			if(number < tmp ){
				choice = i;
				previous = i;
				return choice;
			}
		}
		previous = choice;
		return choice;
	}

	
	public void lockFlow(double fl) {}

	
	protected AgentSeed nextElement(IScope scope, double currentDate, GamlSpecies spe, IShape location) {
		int choice = getChoice(scope);
		this.lastDate = Math.max(currentDate, lastDate);
		AgentSeed agt= generators[choice].nextElement(scope,lastDate,spe, location  );
		
		if(agt!=null) this.lastDate = Math.max(agt.getActivationDate(), lastDate);
		return agt;
	}
	
	/*private int getGeneratorIndex(int choice){
		int index = 0;
		for(AbstractGenerator gen:generators){
			if(gen.getManagedSpecies().contains(listOfSpecies[choice])){
				return index;
			}
			index++;
		}
		return index;
	}*/

}
