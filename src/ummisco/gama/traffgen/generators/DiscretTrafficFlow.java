package ummisco.gama.traffgen.generators;

import java.util.List;

import msi.gama.metamodel.agent.IAgent;
import msi.gama.metamodel.shape.ILocation;
import msi.gama.metamodel.shape.IShape;
import msi.gama.runtime.IScope;
import msi.gaml.species.GamlSpecies;
import ummisco.gama.traffgen.types.TrafficLaw;

public class DiscretTrafficFlow extends TrafficTimeTable {
	private static int TIMETABLE_BUFFER = 10000;
	
	TrafficLaw 		vehicleFlow;
	IAgent []	timeTable;
	int 		indexWrite;
	int			indexRead;
	double duration;

	public  DiscretTrafficFlow(AbstractGenerator lw ,TrafficLaw flow, double duration) {
		super(lw);
		vehicleFlow = flow;
		this.duration = duration;
		timeTable = new IAgent[TIMETABLE_BUFFER];
		indexWrite = 0;
		indexRead = 0;
	}
	
	void generateTimeTable(IScope scope, int begin, int end)
	{
		double totalDduration = 0;
		int size = timeTable.length;
		AgentSeed tmp = null;
		for(int i=begin;i<end;i++) {
			GamlSpecies prevSpec = ((AgentSeed) this.previous[index]).getSpecies();
			ILocation prevLoc = ((AgentSeed) this.previous[index]).getStartingPoint();
			tmp =  this.generator.nextElement(scope, this.currentDate, prevSpec, prevLoc);
			
			timeTable[i%size] = (IAgent) tmp;
			totalDduration +=tmp.getActivationDate();
		}
		
		int nbVehicles = (end - begin);
		
		//double expectedDuration = duration ;
		double flowDifference = duration - totalDduration;
		
		double meanDiff = flowDifference / nbVehicles;
		double sdev = meanDiff / 10;
		double val;
		double res; 
		int tempI;
		
		for(int i = begin; i<end; i++) {
			 val = scope.getRandom().createGaussian(meanDiff,sdev );
			 tempI = i%size;
			 double th = ((AgentSeed) timeTable[tempI]).getActivationDate();
			 res = th + val;
			 ((AgentSeed) timeTable[tempI]).setActivationDate(res > 0 ? res: th);
		}
		
		
		
		indexWrite = end;
		
	}
	private IAgent getNextVal()
	{
		return this.timeTable[indexRead++];
	}
	
	
	@Override
	public IAgent next(IScope scope) {
		if(indexRead == indexWrite) {
			int newFlow = (int) this.vehicleFlow.getNext();
			generateTimeTable(scope, indexRead, indexWrite + newFlow );
		}
		return getNextVal();
	}

	@Override
	public void lockFlow(double fl) {
		
	}

	@Override
	protected AgentSeed nextElement(IScope scope, double lastDate, GamlSpecies spe, IShape location) {
		return (AgentSeed) this.timeTable[indexRead++];
	}

	@Override
	protected List<GamlSpecies> getManagedSpecies() {
		return this.generator.getManagedSpecies();
	}

}
