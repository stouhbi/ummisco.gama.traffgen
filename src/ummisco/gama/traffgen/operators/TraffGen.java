package ummisco.gama.traffgen.operators;

import java.util.Map;

import msi.gama.metamodel.shape.GamaPoint;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.operator;
import msi.gama.runtime.IScope;
import msi.gama.util.GamaDate;
import msi.gama.util.IList;
import msi.gama.util.matrix.IMatrix;
import ummisco.gama.traffgen.types.Dummy;
import ummisco.gama.traffgen.types.IVehicleGenerator;
import ummisco.gama.traffgen.types.PeriodHeadwayGenerator;
import ummisco.gama.traffgen.types.SpeedGenerator;
import ummisco.gama.traffgen.types.VehicleGenerator;
import ummisco.gama.traffgen.types.VehicleTHGenerator;

public class TraffGen {

	
	@operator(value="dummy")
	@doc(value = "create a dummy")
	public static Dummy createDummy(final IScope scope, String word, Double number){
		
		return new Dummy(word, (Double) number);
	}
	
	@operator(value = "generation_model")
	@doc(value = "create a TH generator with model and parameters")
	public  static VehicleTHGenerator generationModel(final IScope scope, final IList<String> vehicleTypes, final Map<String, Object> generationModel) {
		return new VehicleTHGenerator( vehicleTypes, generationModel);
	}
	
	@operator(value = IVehicleGenerator.PERIOD_TYPE)
	@doc(value = " create a Period Type instance")
	public static PeriodHeadwayGenerator createPeriodTHGenerator(final IScope scope, final int periodSequence,
			final IList<VehicleTHGenerator> THgenerators, final int duration, Map<String, Object> countGenerationModel){
		return new PeriodHeadwayGenerator(periodSequence, THgenerators, duration,countGenerationModel);
	}
	
	@operator(value = IVehicleGenerator.SPEED_TYPE)
	@doc(value = "Create a TimeHeadway model instance")
	public static SpeedGenerator createSpeed(final IScope scope, Map<String, Double> meanSpeed){
		return new SpeedGenerator(meanSpeed);
	}
	
	@operator(value = IVehicleGenerator.VEHICLEGENERATOR_TYPE)
	@doc(value = "create instance of a vehicle Generator")
	public static VehicleGenerator createGenerator(final IScope scope, final IList<GamaDate> timeInterval,
			final String transitionType, final IMatrix<Double> transitionMatrix, final IList<String> vehicleTypes, final IList<PeriodHeadwayGenerator> timeHeadway,final SpeedGenerator speed,  Map<GamaPoint, Double> coordinates, String coordinatesChoice){
		return new VehicleGenerator(timeInterval, transitionType, transitionMatrix, timeHeadway, speed, vehicleTypes, coordinates, coordinatesChoice);
		
	}
}
