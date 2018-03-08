package ummisco.gama.traffgen.generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import msi.gama.common.geometry.GeometryUtils;
import msi.gama.common.interfaces.IKeyword;
import msi.gama.metamodel.agent.IAgent;
import msi.gama.metamodel.shape.IShape;
import msi.gama.runtime.IScope;
import msi.gama.util.IList;
import msi.gaml.species.GamlSpecies;
import ummisco.gama.traffgen.types.ITrafficGenerator;

public abstract class AbstractGenerator implements IGenerator {
	AbstractGenerator parent;
	int index;
	IAgent [] previous;
	int bufferSize = IGenerator.BUFFER_SIZE;

	Map<GamlSpecies, Queue<AgentSeed>> gotIndividuals =null;


	protected abstract AgentSeed nextElement(IScope scope, double lastDate, GamlSpecies spe, IShape location);
	protected abstract List<GamlSpecies> getManagedSpecies();

	protected abstract List<IShape> getManagedPositions();



	public IAgent previous(int nb){

		if(nb > previous.length||nb == 0)
			return null;
		if(parent != null)
			return parent.previous(nb);
		int idx = (index + previous.length - nb) % previous.length;
		return previous[idx];
	}

	private void addNextToTree(IAgent agt){
		previous[index] = agt;
		index = (index++)%previous.length;
	}

	@SuppressWarnings("unchecked")
	private void generateBuffers(){
		this.previous = new IAgent[bufferSize];
		this.index = 0;
		List<GamlSpecies> species = this.getManagedSpecies();
		gotIndividuals = new HashMap<GamlSpecies,Queue<AgentSeed>>();
		for(GamlSpecies sp:species) {
			gotIndividuals.put(sp, new LinkedList<AgentSeed>()) ;
		}
	}

	private boolean shouldGenerateData(){
		int index=0;
		for(Queue<AgentSeed> agt:gotIndividuals.values())
			if(agt.isEmpty()) index++;
		if(index==gotIndividuals.size()){
			System.out.println("should generate more vehicles");
			return true; // all queues are empty
		}
		return false;
	}

	private AgentSeed popFirst(double currentDate, double step){
		Queue<AgentSeed> tmplist=null;
		double minDate = Double.MAX_VALUE;
		AgentSeed agttmp = null;
		for(Queue<AgentSeed> agt:gotIndividuals.values()){

			// clean up queue
			boolean end = false;
			
			while(!end && !agt.isEmpty()){
				agttmp = agt.peek();
				if(agttmp.getActivationDate() < currentDate - 2*step) // remove old vehicles
					agt.poll();
				else end = true;
			}
			// if there is still agents in this queue
			if(!agt.isEmpty()){ 
				
				agttmp = agt.peek();
				if(minDate> agttmp.getActivationDate())
				{
					tmplist = agt;
					minDate = agttmp.getActivationDate();
				}
			}

		}
		agttmp = null;
		System.out.println("current Date "+currentDate + " minDate"+ minDate);
		if(minDate>=currentDate - step & minDate <=currentDate){
			System.out.println("agent generated");
			agttmp = tmplist.poll();
		}

		return agttmp;
	}


	private IAgent convertAgentSeedToAgent(IScope scope,AgentSeed sagt){
		Map<String,Object> values = new HashMap<String,Object>();
		if(sagt.getStartingPoint() != null)
		{
			values.put(IKeyword.LOCATION, sagt.getStartingPoint());
		}
		values.put(ITrafficGenerator.ACTIVATION_DATE, new Double(sagt.getActivationDate()));
		values.put(ITrafficGenerator.SPEED, new Double(sagt.getSpeed()));
		values.put(ITrafficGenerator.TIV, new Double(sagt.getTiv()));
		ArrayList<Map<String,Object>> list = new ArrayList<>();
		list.add(values);

		IList<IAgent> magt=  sagt.getSpecies().getPopulation(scope).createAgents(scope, 1, list, false, true);
		IAgent agt = magt.get(0);
		return agt;
	}


	public IAgent next(IScope scope,double currentdate){
		if(this.parent !=null) 
			return null;
		else
			if(this.gotIndividuals == null)
				generateBuffers();

		while(shouldGenerateData()){
			AgentSeed tmp= nextElement(scope,currentdate, null, null);
			if(tmp !=null) this.gotIndividuals.get(tmp.getSpecies()).add(tmp);
			else break;
		}

		double step = (double) scope.getExperiment().getSimulation().getTimeStep(scope);

		AgentSeed agtseed = popFirst(currentdate, step);


		IAgent agt = null;
		if(agtseed !=null){
			agt = convertAgentSeedToAgent(scope,agtseed);
			addNextToTree(agt);
		}

		return agt;
	}




	public int bufferSize(){
		return this.previous.length;
	}

}
