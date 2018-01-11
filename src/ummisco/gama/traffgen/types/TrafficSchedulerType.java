package ummisco.gama.traffgen.types;

import msi.gama.precompiler.ISymbolKind;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.type;
import msi.gaml.types.GamaType;

@type(name = ITrafficScheduler.TYPE_NAME, id = TrafficScheduler.Id, wraps = { TrafficScheduler.class}, kind = ISymbolKind.Variable.REGULAR
, doc= @doc(" traffic law for generation "))
public class TrafficSchedulerType extends GamaType<TrafficScheduler>{

	@Override
	public boolean canCastToConst() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TrafficScheduler cast(IScope scope, Object obj, Object param, boolean copy) throws GamaRuntimeException {
		// TODO Auto-generated method stub
		return (TrafficScheduler) obj;
	}

	@Override
	public TrafficScheduler getDefault() {
		// TODO Auto-generated method stub
		return null;
	}

}
