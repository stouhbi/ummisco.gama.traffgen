package ummisco.gama.traffgen.types;

import msi.gama.precompiler.GamlAnnotations.var;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.util.GamaListFactory;
import msi.gama.util.IList;
import msi.gaml.types.IType;
import msi.gaml.types.Types;
import umontreal.ssj.probdist.DiscreteDistribution;
import umontreal.ssj.probdist.DiscreteDistributionInt;
import umontreal.ssj.probdist.Distribution;
import umontreal.ssj.probdist.DistributionFactory;
import umontreal.ssj.randvar.RandomVariateGen;
import umontreal.ssj.randvar.RandomVariateGenInt;
import umontreal.ssj.rng.LFSR113;
import umontreal.ssj.rng.RandomStream;

import java.util.HashMap;
import java.util.Map;


import ummisco.gama.helpers.Transformer;	

import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gama.precompiler.GamlAnnotations.setter;

@vars({
	@var(name = IVehicleGenerator.VEHICLE_TYPES_TH, type = IType.LIST, doc= @doc("the vehicleTypes on which this generator is applied to")),
	@var(name = IVehicleGenerator.HEADWAY_GENERATION_MODEL, type=IType.MAP, doc=@doc("the headway  generation model for each"))
	
})
public class VehicleTHGenerator{
	public static final int Id = 97;
	public static RandomStream streamer = new LFSR113();
	private RandomVariateGen headwayModel;
	private IList<String> vehicleTypes = GamaListFactory.create(Types.get(IType.STRING));
	private Map<String, Object> headwayGenerationModel = new HashMap<String, Object>();
	
	public VehicleTHGenerator( IList<String> vehicleTypes,Map<String, Object> headwayGenerationModel) {
		// TODO Auto-generated constructor stub
		this.setVehicleTypes(vehicleTypes);
		this.setHeadwayGenerationModel(headwayGenerationModel);
		Distribution headwaymodelDist = DistributionFactory.getDistribution(Transformer.distToString(headwayGenerationModel));
		this.headwayModel = new RandomVariateGen(streamer, headwaymodelDist);
		

		
		
	}
	
	@setter(IVehicleGenerator.VEHICLE_TYPES_TH)
	public void setVehicleTypes(IList<String> vehicleTypes){
		this.vehicleTypes = vehicleTypes;
	}
	
	
	@getter(IVehicleGenerator.VEHICLE_TYPES_TH)
	public IList<String> getVehicleTypes(){
		return this.vehicleTypes;
	}
	
	@getter(IVehicleGenerator.HEADWAY_GENERATION_MODEL)
	public Map<String, Object> getHeadwayGenerationModel(){
		return this.headwayGenerationModel;
	}
	
	@setter(IVehicleGenerator.HEADWAY_GENERATION_MODEL)
	public void setHeadwayGenerationModel(Map<String, Object> generationModel){
		this.headwayGenerationModel = generationModel;
	}
	
	
	

	public double generateTimeHeadway() {
		// TODO Auto-generated method stub
		return this.headwayModel.nextDouble();
	}
	
	

	
}
