package ummisco.gama.traffgen.generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import msi.gama.metamodel.shape.IShape;
import msi.gama.runtime.IScope;
import msi.gaml.species.GamlSpecies;

public class LocationSplitGenerator extends SplitGenerator {

	double listOfRate[];
	IShape listOfShapes[];
	double lastDate = 0;

	public LocationSplitGenerator(AbstractGenerator[] gen, Map<IShape,Double> choice) {
		super(gen);
		configureGenerator(choice);
	}

	private void configureGenerator(Map<IShape, Double> choice) {

		Map<IShape,ArrayList<AbstractGenerator>> localMap = new HashMap<IShape, ArrayList<AbstractGenerator>>();
		int sum = 0;

		if(generators.length == 1){
			this.listOfRate = new double[choice.size()];
			this.listOfShapes = new IShape[choice.size()];
			int index = 0;
			for(IShape sp: choice.keySet()){
				this.listOfShapes[index] = sp;
				this.listOfRate[index] = choice.containsKey(sp)?choice.get(sp).doubleValue():0;
				index++;
			}
		}else{

			for(AbstractGenerator agt :generators)
			{
				for(IShape sp:agt.getManagedPositions()) {

					ArrayList<AbstractGenerator> gens = localMap.get(sp);
					if(gens == null) {
						gens = new ArrayList<AbstractGenerator>();
						localMap.put(sp, gens);
					}
					gens.add(agt);
					sum++;
				}
			}

			this.listOfRate = new double[choice.size()];
			this.listOfShapes = new IShape[choice.size()];
			//this.listOfGenerator = new AbstractGenerator[sum];
			int index = 0;
			for(IShape sp:localMap.keySet()) {
				ArrayList<AbstractGenerator> abs = localMap.get(sp);
				int size = abs.size();
				for(AbstractGenerator gen:abs) {
					//this.listOfGenerator[index]=gen;
					this.listOfShapes[index] = sp;
					this.listOfRate[index] = choice.containsKey(sp)?choice.get(sp).doubleValue():0;
					//System.out.println("chargement  " +gen+" " +sp+" "+sp.getClass().getName()+ " " + this.listOfRate[index]+" " +choice.containsKey(sp));
					index++;
				}
			}
		}

		this.index = 0;
	}

	@Override
	protected int getChoice(IScope scope) {
		double number = scope.getRandom().between(0.0, 1.0);
		double tmp = 0;
		int choice = 0;
		for(int i=0;i<listOfRate.length;i++){
			tmp+=listOfRate[i];
			if(number < tmp ){
				choice = i;
				return choice;
			}
		}
		return choice;
	}

	@Override
	public void lockFlow(double fl) {


	}

	@Override
	protected AgentSeed nextElement(IScope scope, double lastdate, GamlSpecies spe, IShape location) {
		int choice = getChoice(scope);
		this.lastDate = Math.max(lastdate, lastDate);
		int genChoice = getGeneratorIndex(choice);
		AgentSeed agt = this.generators[genChoice].nextElement(scope,lastDate,null,listOfShapes[choice] );
		this.lastDate = Math.max(agt.getActivationDate(), lastDate);
		return agt;
	}

	private int getGeneratorIndex(int choice){
		int index = 0;
		if(generators.length>1){
			for(AbstractGenerator gen:generators){
				if(gen.getManagedPositions().contains(listOfShapes[choice])){
					return index;
				}
				index++;
			}
		}
		return index;
	}
}
