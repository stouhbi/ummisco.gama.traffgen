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
	

	
	public IAgent previous(int nb)
	{
		
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
	private void generateBuffers()
	{
		this.previous = new IAgent[bufferSize];
		this.index = 0;
		List<GamlSpecies> species = this.getManagedSpecies();
		gotIndividuals = new HashMap<GamlSpecies,Queue<AgentSeed>>();
		for(GamlSpecies sp:species) {
			gotIndividuals.put(sp, new LinkedList<AgentSeed>()) ;
		}
	}
	
	private boolean shouldGenerateData()
	{
		for(Queue<AgentSeed> agt:gotIndividuals.values())
			if(agt.isEmpty()) return true;
		return false;
	}
	
	private AgentSeed popFirst()
	{
		Queue<AgentSeed> tmplist=null;
		double minDate = Double.MAX_VALUE;
		AgentSeed agttmp;
		for(Queue<AgentSeed> agt:gotIndividuals.values())
		{
			if(!agt.isEmpty()){
				agttmp = agt.peek();
				if(minDate> agttmp.getActivationDate())
				{
					tmplist = agt;
					minDate = agttmp.getActivationDate();
				}
			}
			
		}
		agttmp = tmplist.poll();
		return agttmp;
	}
	
	
	private IAgent convertAgentSeedToAgent(IScope scope,AgentSeed sagt)
	{
		Map<String,Object> values = new HashMap<String,Object>();
		if(sagt.getStartingPoint() != null)
		{
			values.put(IKeyword.LOCATION, sagt.getStartingPoint());
		}
		values.put(ITrafficGenerator.ACTIVATION_DATE, new Double(sagt.getActivationDate()));
		values.put(ITrafficGenerator.SPEED, new Double(sagt.getSpeed()));
		ArrayList<Map<String,Object>> list = new ArrayList<>();
		list.add(values);
		
		IList<IAgent> magt=  sagt.getSpecies().getPopulation(scope).createAgents(scope, 1, list, false, true);
		IAgent agt = magt.get(0);
		return agt;
	}
	
	
	public IAgent next(IScope scope,double currentdate)
	{
		if(this.parent !=null) 
			return null;
		else
			if(this.gotIndividuals == null)
				generateBuffers();
		
		while(shouldGenerateData())
		{
			AgentSeed tmp= nextElement( scope,0, null, null);
			this.gotIndividuals.get(tmp.getSpecies()).add(tmp);
		}
		AgentSeed agtseed = popFirst();
		
		IAgent agt = convertAgentSeedToAgent(scope,agtseed);
		addNextToTree(agt);
		return agt;
	}
	

	
	
	public int bufferSize()
	{
		return this.previous.length;
	}
	
}
