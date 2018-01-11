package ummisco.gama.traffgen.types;

import msi.gama.precompiler.ISymbolKind;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.type;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gama.util.GamaMap;
import msi.gaml.types.GamaType;


@type(name = ITrafficLaw.TYPE_NAME, id = TrafficLaw.Id, wraps = { TrafficLaw.class}, kind = ISymbolKind.Variable.REGULAR
, doc= @doc(" traffic law for generation "))
public class TrafficLawType extends GamaType<TrafficLaw> {

	@Override
	public boolean canCastToConst() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TrafficLaw cast(IScope scope, Object obj, Object param, boolean copy) throws GamaRuntimeException {
		if(obj instanceof TrafficLaw)
			return (TrafficLaw) obj;
		if(obj instanceof GamaMap) {
			GamaMap xx = (GamaMap) obj;
			return TrafficLaw.createFromMap(xx);
		}
		return null;
	}

	@Override
	public TrafficLaw getDefault() {
		// TODO Auto-generated method stub
		return null;
	}

}
