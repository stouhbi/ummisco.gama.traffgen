package ummisco.gama.traffgen.generators;

import java.util.ArrayList;
import java.util.List;

import msi.gama.runtime.IScope;
import ummisco.gama.traffgen.types.TrafficLaw;

public class DiscretTrafficFlow extends TrafficTimeTable {
	private static int TIMETABLE_BUFFER = 5000;

	private TrafficLaw 		vehicleFlow;
	private List<Double>	timeTable;
	private int 		indexWrite;
	private int			indexRead;
	private int duration;
	private int trafficFlow=1;
	private int currentTick;



	public  DiscretTrafficFlow(TrafficLaw lw ,TrafficLaw flow, int duration) {
		super(lw);
		vehicleFlow = flow;
		timeTable = new ArrayList<Double>();
		indexWrite = 0;
		indexRead = 0;
		this.duration = duration;
		this.currentTick = 0;
	}

	void generateTimeTable(IScope scope, int begin, int end)
	{

		double expectedDuration = 0;
		//int size = timeTable.size();
		double tmp = 0;
		for(int i=begin;i<end;i++) {
			tmp = this.timeHeadwayLaw.getNext();
			timeTable.add(tmp);
			//timeTable.set(i%size, tmp);
			expectedDuration +=tmp;
		}

		// get the size after adding TH
		int size = timeTable.size();

		int nbVehicles = (end - begin);

		double flowDifference = duration - expectedDuration;

		/*double meanDiff = flowDifference / nbVehicles;
		double sdev = meanDiff / 10;
		double val;
		double res; 
		int tempI;

		for(int i = begin; i<end; i++) {
			 val = scope.getRandom().createGaussian(meanDiff,sdev );
			 tempI = i%size;
			 res = timeTable.get(tempI) + val;
			 timeTable.set(tempI, res > 0 ? res: timeTable.get(tempI));
		}*/
		
		if(flowDifference >= 0) timeTable.add(duration+1.0); // add dummy TIV
		indexRead = begin;
		indexWrite = end;

	}

	private double getNextVal(IScope scope)
	{

		return this.timeTable.get(indexRead++);
	}

	@Override
	public double next(IScope scope) {
		double val = -20;
		double step = (double) scope.getExperiment().getSimulation().getTimeStep(scope);

		currentTick += step; 
		if(indexRead == indexWrite) {
			trafficFlow = (int) vehicleFlow.getNext();
			System.out.println("need to generate more vehicles "+trafficFlow);
			generateTimeTable(scope, indexRead, indexWrite + trafficFlow);
		}
		val = getNextVal(scope);

		return val;
	}


	public TrafficLaw getVehicleFlow() {
		return vehicleFlow;
	}

	public void setVehicleFlow(TrafficLaw vehicleFlow) {
		this.vehicleFlow = vehicleFlow;
	}

	public List<Double> getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(List<Double> timeTable) {
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