package ummisco.gama.traffgen.types;

import java.util.List;


import msi.gama.precompiler.ISymbolKind;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.types.GamaType;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.type;

@type(name = IVehicleGenerator.TIME_HEADWAY_TYPE, id = VehicleTHGenerator.Id, wraps = { VehicleTHGenerator.class}, kind = ISymbolKind.Variable.REGULAR
, doc= @doc(" The timeHeadway metadata "))
public class VehicleTHGeneratorType extends GamaType<VehicleTHGenerator>{

	@Override
	public boolean canCastToConst() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public VehicleTHGenerator cast(IScope scope, Object obj, Object param, boolean copy) throws GamaRuntimeException {
		// TODO Auto-generated method stub
		return (VehicleTHGenerator) obj;
	}

	@Override
	public VehicleTHGenerator getDefault() {
		// TODO Auto-generated method stub
		return null;
	}

}
