package ummisco.gama.traffgen.types;

import msi.gama.precompiler.ISymbolKind;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.type;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.types.GamaType;

@type(name = ITrafficGenerator.TYPE_NAME, id = TrafficGenerator.Id, wraps = { TrafficGenerator.class}, kind = ISymbolKind.Variable.REGULAR
, doc= @doc(" traffic generator "))
public class TrafficGeneratorType extends GamaType<TrafficGenerator> {
	
	@Override
	public boolean canCastToConst() {
		return false;
	}

	@Override
	public TrafficGenerator cast(IScope scope, Object obj, Object param, boolean copy) throws GamaRuntimeException {
		return (TrafficGenerator) obj;
	}

	@Override
	public TrafficGenerator getDefault() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
