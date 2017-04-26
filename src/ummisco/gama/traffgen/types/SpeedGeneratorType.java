package ummisco.gama.traffgen.types;


import java.util.Map;

import msi.gama.precompiler.ISymbolKind;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.types.GamaType;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.type;

@type(name = IVehicleGenerator.SPEED_TYPE, id = SpeedGenerator.Id, wraps = { SpeedGenerator.class}, kind = ISymbolKind.Variable.REGULAR
, doc= @doc(" The speed metadata "))
public class SpeedGeneratorType extends GamaType<SpeedGenerator>{

	@Override
	public boolean canCastToConst() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public SpeedGenerator cast(IScope scope, Object obj, Object param, boolean copy) throws GamaRuntimeException {
		// TODO Auto-generated method stub
		if(obj instanceof SpeedGenerator)
			return (SpeedGenerator) obj;
		else if(obj instanceof Map)
			return new SpeedGenerator((Map) obj);
		
		return null;
	}

	@Override
	public SpeedGenerator getDefault() {
		// TODO Auto-generated method stub
		return null;
	}

}
