package ummisco.gama.traffgen.skills;

import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gama.precompiler.GamlAnnotations.setter;
import msi.gama.precompiler.GamlAnnotations.skill;
import msi.gama.precompiler.GamlAnnotations.var;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gama.util.GamaDate;
import msi.gama.util.GamaMapFactory;
import msi.gama.util.IList;
import msi.gama.util.matrix.IMatrix;
import msi.gaml.skills.Skill;
import msi.gaml.types.IType;
import msi.gaml.types.Types;
import ummisco.gama.helpers.Transformer;
import ummisco.gama.traffgen.types.IVehicleGenerator;

import ummisco.gama.traffgen.types.VehicleGenerator;
import umontreal.ssj.probdist.FisherFDist;
import msi.gama.precompiler.GamlAnnotations.action;
import msi.gama.precompiler.GamlAnnotations.arg;

@skill(name = "vehicleGen", doc = @doc("The skill vehicle Generator that will provide the necessary vehicles"))
@vars({ @var(name = IVehicleGeneratorSkill.GENERATORS, type = IType.MAP, doc = @doc("list of generators")) })
public class VehicleGeneratorSkill extends Skill {

	private Map<GamaDate, IList<VehicleGenerator>> generators = new HashMap<GamaDate, IList<VehicleGenerator>>();
	private ArrayList<String> vehicleType = new ArrayList<String>();
	private ArrayList<GamaDate> arrivalTime = new ArrayList<GamaDate>();
	private ArrayList<Double> speed = new ArrayList<Double>();
	public GamaDate firstDate = null;
	public GamaDate nextUpdate = null;

	// Iterative proportional fitting
	@action(name = "transform", args = { @arg(name = "list", type = IType.LIST, optional = false),
			@arg(name = "method", type = IType.STRING, optional = true), })
	public IMatrix<Float> transform(final IScope scope) throws GamaRuntimeException {
		String method = "IPU";
		if (!scope.hasArg("list")) {
			return null;
		}
		if (scope.hasArg("method")) {

		}
		return null;
	}

	// Generate the vehicles for each timeInterval
	@action(name = IVehicleGeneratorSkill.GENERATE_VEHICLES, args = {
			@arg(name = "type", type = IType.STRING, doc = @doc("type of generation, by count or by headway")) })
	public void getVehiculeList(final IScope scope) {
		firstDate = scope.getExperiment().getSimulation().getStartingDate();
		nextUpdate = firstDate.plus(Duration.ofMillis(1000));
		// generate
		String type = "count";
		if (scope.hasArg("type")) {
			type = (String) scope.getArg("type", IType.STRING);
		}
		for (Map.Entry<GamaDate, IList<VehicleGenerator>> entry : generators.entrySet()) {
			ArrayList<VehicleGenerator> gen = (ArrayList<VehicleGenerator>) entry.getValue();
			for (int i = 0; i < gen.size(); i++) {
				try {
					if (type.equals(IVehicleGenerator.HEADWAY_METHOD))
						// if generation is based on headway
						gen.get(i).generateVehiclesHeadway(scope);
					else if (type.equals(IVehicleGenerator.COUNT_METHOD))
						gen.get(i).generateVehiclesCount(scope);
					vehicleType.addAll(gen.get(i).getVehicleList());
					arrivalTime.addAll(gen.get(i).getTimeHeadwayList());
					speed.addAll(gen.get(i).getSpeedList());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("size of generators" + generators.size());
					System.out.println("there is an exception here ");
					e.printStackTrace();
				}
			}
		}
		
		for(int i=0; i< arrivalTime.size(); i++){
			System.out.println("arrival time "+arrivalTime.get(i).toString()+" Arrival time in milliseconds "+arrivalTime.get(i).floatValue(scope));
		}
	}

	@getter(IVehicleGeneratorSkill.GENERATORS)
	public Map<GamaDate, IList<VehicleGenerator>> getGenerators() {
		return generators;
	}

	@setter(IVehicleGeneratorSkill.GENERATORS)
	public void setGenerators(Map<GamaDate, IList<VehicleGenerator>> generators) {
		this.generators = generators;
	}

	@action(name = IVehicleGeneratorSkill.LAUNCH_VEHICLES, args = {}, doc = @doc("action for generating vehicles based on schedule"))
	/**
	 * Launch vehicles based on schedule
	 * 
	 * @param scope
	 */
	public void launchVehicles(final IScope scope) {
		// scope.getSimulation().
	}

	private IList<VehicleGenerator> getGenerators(GamaDate date) {
		return (IList<VehicleGenerator>) generators.get(date);
	}

	/*// Initiate the vehicle to launch on the simulation
	@action(name = IVehicleGeneratorSkill.INIT_VEHICLE, args = {}, doc = @doc("initiate vehicle from the list in order to"))
	public void initVehicle(final IScope scope) {
		// if nextupdate is not yet set
		if (nextUpdate == null) {
			ArrayList<VehicleGenerator> gens = (ArrayList<VehicleGenerator>) this.generators.get(firstDate);
			if (gens != null) {
				if (gens.size() > 1)
					nextUpdate = gens.get(0).getTimeInterval().get(1);
				for (int i = 0; i < gens.size(); i++) {
					if (gens.get(i).getTimeInterval().get(1).isSmallerThan(nextUpdate, false))
						nextUpdate = gens.get(i).getTimeInterval().get(1);
				}
			}
		}

		GamaDate now = scope.getExperiment().getSimulation().getCurrentDate();
		if (now.isSmallerThan(nextUpdate, true)) {
			// see if there is some vehicle to generate
			System.out.println("let's see if there is vehicles to generate");
			ArrayList<VehicleGenerator> gens = (ArrayList<VehicleGenerator>) this.generators.get(firstDate);
			if (gens != null) {
				for (VehicleGenerator gen : gens) {
					if (gen.getTimeHeadwayList().contains(now)) {
						// it's time to generate something
						int index = gen.getTimeHeadwayList().indexOf(now);
						System.out.println("vehicle to generate : type : " + gen.getVehicleList().get(index)
								+ " Speed : " + gen.getSpeedList().get(index) + " time "
								+ gen.getTimeHeadwayList().get(index).toISOString());
					}
				}
			}
		} else if (now.equals(nextUpdate)) {
			// now we should transition to new generators
			ArrayList<VehicleGenerator> gens = (ArrayList<VehicleGenerator>) this.generators.get(firstDate);
			if (gens != null) {
				if (gens.size() > 1)
					nextUpdate = gens.get(0).getTimeInterval().get(1);
				for (int i = 0; i < gens.size(); i++) {
					if (gens.get(i).getTimeInterval().get(1).isSmallerThan(nextUpdate, false))
						nextUpdate = gens.get(i).getTimeInterval().get(1);
				}
			}
		}
		System.out.println("next update "+nextUpdate);
	}

*/	
	
	@action(name = IVehicleGeneratorSkill.INIT_VEHICLE, args = {}, doc = @doc("initiate vehicle from the list in order to"))
	public void initVehicle(final IScope scope) {
		GamaDate now = scope.getExperiment().getSimulation().getCurrentDate();
		
		if(arrivalTime.contains(now)){
			// there is vehicles that sould be generated on this time
			ArrayList<Integer> indexes = Transformer.indexOfAll(now, arrivalTime, scope);
			for(int index: indexes){
				System.out.println("vehicle to generate : type : " + vehicleType.get(index)
						+ " Speed : " + speed.get(index) + " time "
						+ arrivalTime.get(index).toISOString());
			}
		}
	}
	
	
}
