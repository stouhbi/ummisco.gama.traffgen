package ummisco.gama.traffgen.generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import msi.gama.metamodel.agent.IAgent;
import msi.gama.metamodel.shape.IShape;
import msi.gama.runtime.IScope;
import msi.gaml.species.GamlSpecies;

public class MapSplitGenerator extends SplitGenerator {
	double [] listOfRate;
	GamlSpecies [] listOfSpecies;
	//AbstractGenerator [] listOfGenerator;
	
	
	public MapSplitGenerator(AbstractGenerator[] gen, Map<GamlSpecies,Double> choice) {
		super(gen);
		configureGenerator(choice);
	}

	
	private void configureGenerator(Map<GamlSpecies,Double> choice)
	{
		Map<GamlSpecies,ArrayList<AbstractGenerator>> localMap = new HashMap<GamlSpecies, ArrayList<AbstractGenerator>>();
		int sum = 0;

		for(AbstractGenerator agt :generators)
		{
			for(GamlSpecies sp:agt.getManagedSpecies()) {
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
		this.listOfSpecies = new GamlSpecies[choice.size()];
		//this.listOfGenerator = new AbstractGenerator[sum];
		int index = 0;
		for(GamlSpecies sp:localMap.keySet()) {
			ArrayList<AbstractGenerator> abs = localMap.get(sp);
			int size = abs.size();
			for(AbstractGenerator gen:abs) {
				//this.listOfGenerator[index]=gen;
				this.listOfSpecies[index] = sp;
				this.listOfRate[index] = choice.containsKey(sp)?choice.get(sp).doubleValue():0;
				//System.out.println("chargement  " +gen+" " +sp+" "+sp.getClass().getName()+ " " + this.listOfRate[index]+" " +choice.containsKey(sp));
				index++;
			}
		}
		
		System.out.println("Length of species "+ listOfSpecies.length);
		System.out.println("Length of Generators" + generators.length);
		System.out.println("Length of Rates" + listOfRate.length);
		for(int i = 0; i < listOfRate.length; i++){
			System.out.println(" rate "+ listOfRate[i]);
		}
		
		this.index=0;
	}
	
	
	@Override
	protected int getChoice(IScope scope) {
		double number = scope.getRandom().between(0.0, 1.0);
		double tmp = 0;
		int i = 0;
		for(i=0;i<listOfRate.length;i++){
			tmp+=listOfRate[i];
			if(number <= tmp ) return i;
		}
		return i==listOfRate.length ? i-1:i;
	}

	protected AgentSeed nextElement(IScope scope,double lastdate, GamlSpecies spe, IShape location) {
		int choice = getChoice(scope);
		this.lastDate = Math.max(lastdate, lastDate);
		int genChoice = getGeneratorIndex(choice);
		// Si par exemple 'lutilisateur fait l'erreur de mettre des species plus que le nombre de species géré par les générateurs
		// Un problème se rétabli. Il faut voir comment régler ce problème.
		AgentSeed agt = this.generators[genChoice].nextElement(scope,lastDate,listOfSpecies[choice],location );
		/*if(listOfGenerator.length==1){
			agt = listOfGenerator[0].nextElement(scope,lastDate,listOfSpecies[choice],location );
		}else{
			agt = listOfGenerator[choice].nextElement(scope,lastDate,listOfSpecies[choice],location );
		}*/
		
		if(agt!=null) this.lastDate = Math.max(agt.getActivationDate(), lastDate);
		return agt;
	}
	
	private int getGeneratorIndex(int choice){
		int index = 0;
		for(AbstractGenerator gen:generators){
			if(gen.getManagedSpecies().contains(listOfSpecies[choice])){
				return index;
			}
			index++;
		}
		return index;
	}


	@Override
	public void lockFlow(double fl) {
	}

}
