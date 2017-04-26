package ummisco.gama.traffgen.meta;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class VehicleGeneratorMeta {
	
	// list
	private ArrayList<String> timeInterval;
	private ArrayList<String> vehicleTypes;
	private ArrayList<String> transitionType;
	private ArrayList<String> transition;
	private ArrayList<Integer> periodSequence;
	private ArrayList<String> generationModel;
	private ArrayList<String> parameters;
	private ArrayList<Integer> duration;
	private ArrayList<String> speed;
	
	
	
	public VehicleGeneratorMeta(ArrayList<String> timeInterval, ArrayList<String> vehicleTypes,
			ArrayList<String> transitionType, ArrayList<String> transition, ArrayList<Integer> periodSequence,
			ArrayList<String> generationModel, ArrayList<String> parameters, ArrayList<Integer> duration,
			ArrayList<String> speed) {
		super();
		this.timeInterval = timeInterval;
		this.vehicleTypes = vehicleTypes;
		this.transitionType = transitionType;
		this.transition = transition;
		this.periodSequence = periodSequence;
		this.generationModel = generationModel;
		this.parameters = parameters;
		this.duration = duration;
		this.speed = speed;
	}
	
	public VehicleGeneratorMeta(){
		this.timeInterval = new ArrayList<String>();
		this.vehicleTypes = new ArrayList<String>();
		this.transitionType = new ArrayList<String>();
		this.transition = new ArrayList<String>();
		this.periodSequence = new ArrayList<Integer>();
		this.generationModel = new ArrayList<String>();
		this.parameters = new ArrayList<String>();
		this.duration = new ArrayList<Integer>();
		this.speed = new ArrayList<String>();
	}
	
	
	public ArrayList<String> getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(ArrayList<String> timeInterval) {
		this.timeInterval = timeInterval;
	}
	public ArrayList<String> getVehicleTypes() {
		return vehicleTypes;
	}
	public void setVehicleTypes(ArrayList<String> vehicleTypes) {
		this.vehicleTypes = vehicleTypes;
	}
	public ArrayList<String> getTransitionType() {
		return transitionType;
	}
	public void setTransitionType(ArrayList<String> transitionType) {
		this.transitionType = transitionType;
	}
	public ArrayList<String> getTransition() {
		return transition;
	}
	public void setTransition(ArrayList<String> transition) {
		this.transition = transition;
	}
	public ArrayList<Integer> getPeriodSequence() {
		return periodSequence;
	}
	public void setPeriodSequence(ArrayList<Integer> periodSequence) {
		this.periodSequence = periodSequence;
	}
	public ArrayList<String> getGenerationModel() {
		return generationModel;
	}
	public void setGenerationModel(ArrayList<String> generationModel) {
		this.generationModel = generationModel;
	}
	public ArrayList<String> getParameters() {
		return parameters;
	}
	public void setParameters(ArrayList<String> parameters) {
		this.parameters = parameters;
	}
	public ArrayList<Integer> getDuration() {
		return duration;
	}
	public void setDuration(ArrayList<Integer> duration) {
		this.duration = duration;
	}
	public ArrayList<String> getSpeed() {
		return speed;
	}
	public void setSpeed(ArrayList<String> speed) {
		this.speed = speed;
	}
	
	
	// concat two metadata
	public void concat(VehicleGeneratorMeta sample){
		this.timeInterval.addAll(sample.getTimeInterval());
		this.transition.addAll(sample.getTransition());
		this.transitionType.addAll(sample.getTransitionType());
		this.vehicleTypes.addAll(sample.getVehicleTypes());
		this.periodSequence.addAll(sample.getPeriodSequence());
		this.generationModel.addAll(sample.getGenerationModel());
		this.parameters.addAll(sample.getSpeed());
		this.duration.addAll(sample.getDuration());
		this.speed.addAll(sample.getSpeed());
	}

}
