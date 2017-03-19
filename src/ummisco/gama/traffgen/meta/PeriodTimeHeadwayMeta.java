package ummisco.gama.traffgen.meta;

import java.util.HashMap;

public class PeriodTimeHeadwayMeta {

	private int periodSequence;
	private String generationModel;
	private HashMap<String, Float> params;
	
	public int getPeriodSequence() {
		return periodSequence;
	}
	public void setPeriodSequence(int periodSequence) {
		this.periodSequence = periodSequence;
	}
	public String getGenerationModel() {
		return generationModel;
	}
	public void setGenerationModel(String generationModel) {
		this.generationModel = generationModel;
	}
	public HashMap<String, Float> getParams() {
		return params;
	}
	public void setParams(HashMap<String, Float> params) {
		this.params = params;
	}
}
