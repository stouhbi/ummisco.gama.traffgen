package ummisco.gama.traffgen.generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.sun.javafx.geom.IllegalPathStateException;

import msi.gama.common.geometry.GeometryUtils;
import msi.gama.common.interfaces.IKeyword;
import msi.gama.metamodel.agent.IAgent;
import msi.gama.metamodel.shape.ILocation;
import msi.gama.metamodel.shape.IShape;
import msi.gama.precompiler.GamlProperties;
import msi.gama.runtime.IScope;
import msi.gama.util.IList;
import msi.gaml.species.GamlSpecies;
import ummisco.gama.traffgen.types.ITrafficGenerator;
import ummisco.gama.traffgen.types.TrafficLaw;

public class AtomicGenerator extends AbstractGenerator implements IGenerator {

	
	TrafficTimeTable timeHeadwayLaw = null;
	TrafficLaw speedLaw = null;
	ArrayList<GamlSpecies> species;
	IShape shape;
	double lastDate;
	
	
	private AtomicGenerator(TrafficLaw timeHeadwayLaw, TrafficLaw spl, IShape shape) {
		super();
		this.timeHeadwayLaw = new ContinuousTrafficFlow(spl);
		this.speedLaw = spl;
		this.species = new ArrayList<GamlSpecies>();
		this.shape = shape;
		lastDate = 0;
		
	}
	
	public AtomicGenerator(GamlSpecies species,TrafficLaw timeHeadwayLaw, TrafficLaw spl, IShape shape) {
		this(timeHeadwayLaw,spl,shape);
		this.species.add(species);
	}	
	
	public AtomicGenerator(GamlSpecies species,TrafficLaw timeHeadwayLaw, TrafficLaw spl) {
		this(timeHeadwayLaw,spl,null);
		this.species.add(species);
	}	
	
	public AtomicGenerator(GamlSpecies[] species,TrafficLaw timeHeadwayLaw, TrafficLaw spl, IShape shape) {
		this(timeHeadwayLaw,spl,shape);
		for(GamlSpecies sp:species)
		this.species.add(sp);
	}
	public AtomicGenerator(GamlSpecies[] species,TrafficLaw timeHeadwayLaw, TrafficLaw spl) {
		this(timeHeadwayLaw,spl,null);
		for(GamlSpecies sp:species)
		this.species.add(sp);
	}
	
	public List<GamlSpecies> getManagedSpecies(){
		for(GamlSpecies s:species)
			System.out.println("all "+this+" " + s.getName());
		return this.species;
	}
	
	private GamlSpecies chooseSpecies(IScope scope, GamlSpecies s)
	{
		if(s!=null && species.contains(s))
			return s;
		int choice = scope.getRandom().between(0, this.species.size()-1);
		return this.species.get(choice);
	}
	
	
	public void lockFlow(double flow){
		if(flow<=0)
			unlockFlow();
		TrafficLaw lw = this.timeHeadwayLaw.getLaw();
		this.timeHeadwayLaw = new DiscretTrafficFlow(lw, flow);
	}
	
	public void unlockFlow(){
		this.timeHeadwayLaw = new ContinuousTrafficFlow(this.timeHeadwayLaw.getLaw());
	}
	
	public ILocation chooseLocation(IScope scope,IShape loc)
	{
		IShape space = null;
		if(loc == null)
			if(this.shape == null)
				return null;
			else
				space = this.shape;
		else
			space = loc;
		final ILocation p = GeometryUtils.pointInGeom(space, scope.getRandom());
		return p;
	}
	
	@Override
	protected AgentSeed nextElement(IScope scope, double date, GamlSpecies s, IShape location ) {
		GamlSpecies spe = this.chooseSpecies(scope, s);
		double start =this.lastDate; // Math.max(date, this.lastDate);
		double newDate = start + timeHeadwayLaw.next(scope);
		lastDate = newDate;
		double speed = speedLaw.getNext();
		System.out.println("creation agent "+ spe.getName()+" à la date "+ newDate);	
		
		AgentSeed sdd = new AgentSeed(spe,speed,newDate,chooseLocation(scope,location)); 

		return sdd;
	}
	
	
}
