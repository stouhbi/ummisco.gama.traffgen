package ummisco.gama.traffgen.meta;

import java.awt.List;
import java.util.ArrayList;

public class GenerationModelMeta {

	private String roadID;
	private String timeInterval;
	private ArrayList<Integer> lanes;
	
	
	public String getRoadID() {
		return roadID;
	}
	public void setRoadID(String roadID) {
		this.roadID = roadID;
	}
	public String getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}
	public ArrayList<Integer> getLanes() {
		return lanes;
	}
	public void setLanes(ArrayList<Integer> lanes) {
		this.lanes = lanes;
	}
	
}
