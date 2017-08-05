package ummisco.gama.traffgen.generators;

import msi.gama.metamodel.agent.IAgent;
import msi.gama.runtime.IScope;

public interface IGenerator {
	
	final int BUFFER_SIZE = 5;
	/**
	 * generate a new agent according to the date.
	 * @param date Date in second
	 * @return created agent
	 */
	public IAgent next(IScope scope,double date);
	public IAgent previous(int nbRedo);
	public int bufferSize();
	public void lockFlow(double fl);
}
