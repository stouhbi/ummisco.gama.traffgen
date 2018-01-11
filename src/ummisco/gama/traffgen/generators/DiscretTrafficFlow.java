package ummisco.gama.traffgen.generators;

import msi.gama.runtime.IScope;
import ummisco.gama.traffgen.types.TrafficLaw;

public class DiscretTrafficFlow extends TrafficTimeTable {
	private static int TIMETABLE_BUFFER = 5000;
	
	private TrafficLaw 		vehicleFlow;
	private double []	timeTable;
	private int 		indexWrite;
	private int			indexRead;
	private int duration;

	

	public  DiscretTrafficFlow(TrafficLaw lw ,TrafficLaw flow, int duration) {
		super(lw);
		vehicleFlow = flow;
		timeTable = new double[TIMETABLE_BUFFER];
		indexWrite = 0;
		indexRead = 0;
		this.duration = duration;
	}
	
	void generateTimeTable(IScope scope, int begin, int end)
	{
		double expectedDuration = 0;
		int size = timeTable.length;
		double tmp = 0;
		for(int i=begin;i<end;i++) {
			tmp = this.timeHeadwayLaw.getNext();
			timeTable[i%size] = tmp;
			expectedDuration +=tmp;
		}
		
		int nbVehicles = (end - begin);
		
		double flowDifference = duration - expectedDuration;
		
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
			int trafficFlow = (int) vehicleFlow.getNext();
			generateTimeTable(scope, indexRead, indexWrite + trafficFlow);
		}
		return getNextVal();
	}
	
	
	public TrafficLaw getVehicleFlow() {
		return vehicleFlow;
	}

	public void setVehicleFlow(TrafficLaw vehicleFlow) {
		this.vehicleFlow = vehicleFlow;
	}

	public double[] getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(double[] timeTable) {
		this.timeTable = timeTable;
	}

	public int getIndexWrite() {
		return indexWrite;
	}

	public void setIndexWrite(int indexWrite) {
		this.indexWrite = indexWrite;
	}

	public int getIndexRead() {
		return indexRead;
	}

	public void setIndexRead(int indexRead) {
		this.indexRead = indexRead;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}