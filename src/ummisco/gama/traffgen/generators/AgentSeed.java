package ummisco.gama.traffgen.generators;


import msi.gama.metamodel.shape.ILocation;
import msi.gaml.species.GamlSpecies;

class AgentSeed {
	private GamlSpecies species;
	private double speed;
	private double activationDate;
	private ILocation startingPoint;
	private double tiv;
	
	 
	public AgentSeed(GamlSpecies species, double speed, double activationDate, ILocation startingPoint, double tiv) {
		super();
		this.species = species;
		this.speed = speed;
		this.activationDate = activationDate;
		this.startingPoint = startingPoint;
		this.setTiv(tiv);
	}
	
	public GamlSpecies getSpecies() {
		return species;
	}
	public void setSpecies(GamlSpecies species) {
		this.species = species;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public double getActivationDate() {
		return activationDate;
	}
	public void setActivationDate(double activationDate) {
		this.activationDate = activationDate;
	}
	public ILocation getStartingPoint() {
		return startingPoint;
	}
	public void setStartingPoint(ILocation startingPoint) {
		this.startingPoint = startingPoint;
	}

	public double getTiv() {
		return tiv;
	}

	public void setTiv(double tiv) {
		this.tiv = tiv;
	}
	
	
}
