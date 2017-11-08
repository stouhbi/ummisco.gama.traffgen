package ummisco.gama.traffgen.generators;

import msi.gama.runtime.IScope;
import ummisco.gama.traffgen.types.TrafficLaw;

public class DiscretTrafficFlow extends TrafficTimeTable {
	private static int TIMETABLE_BUFFER = 1000;
	
	double 		vehicleFlow;
	double []	timeTable;
	int 		indexWrite;
	int			indexRead;

	public  DiscretTrafficFlow(TrafficLaw lw ,double flow) {
		super(lw);
		vehicleFlow = flow;
		timeTable = new double[TIMETABLE_BUFFER];
		indexWrite = 0;
		indexRead = 0;
	}
	
	void generateTimeTable(IScope scope, int begin, int end)
	{
		double duration = 0;
		int size = timeTable.length;
		double tmp = 0;
		for(int i=begin;i<end;i++) {
			tmp = this.timeHeadwayLaw.getNext();
			timeTable[i%size] = tmp;
			duration +=tmp;
		}
		
		int nbVehicles = (end - begin);
		
		double expectedDuration = this.vehicleFlow * nbVehicles ;
		double flowDifference = expectedDuration - duration;
		
		double meanDiff = flowDifference / nbVehicles;
		double sdev = meanDiff / 10;
		double val;
		double res; 
		int tempI;
		
		for(int i = begin; i<end; i++) {
			 val = scope.getRandom().createGaussian(meanDiff,sdev );
			 tempI = i%size;
			 res = timeTable[tempI] + val;
			 timeTable[tempI] = res > 0 ? res: timeTable[tempI];
		}
		
		indexWrite = end;
		
	}
	private double getNextVal()
	{
		return this.timeTable[indexRead++];
	}
	@Override
	public double next(IScope scope) {
		if(indexRead == indexWrite) {
			generateTimeTable(scope, indexRead, indexWrite +timeTable.length );
		}
		return getNextVal();
	}

}
