package ummisco.gama.traffgen.types;

import msi.gama.precompiler.ISymbolKind;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.type;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.types.GamaType;

@type(name = ITrafficPeriod.TYPE_NAME, id = TrafficPeriod.Id, wraps = { TrafficPeriod.class}, kind = ISymbolKind.Variable.REGULAR
, doc= @doc(" traffic law for generation "))
public class TrafficPeriodType extends GamaType<TrafficPeriod>{

	@Override
	public boolean canCastToConst() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TrafficPeriod cast(IScope scope, Object obj, Object param, boolean copy) throws GamaRuntimeException {
		// TODO Auto-generated method stub
		return (TrafficPeriod) obj;
	}

	@Override
	public TrafficPeriod getDefault() {
		// TODO Auto-generated method stub
		return null;
	}

}
