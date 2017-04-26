package ummisco.gama.traffgen.types;



import msi.gama.precompiler.ISymbolKind;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gama.precompiler.GamlAnnotations.type;
import msi.gaml.types.GamaType;


@type(name = "dummy", id = Dummy.Id, kind = ISymbolKind.Variable.REGULAR, wraps = { Dummy.class })
public class DummyType  extends GamaType<Dummy>{

	@Override
	public boolean canCastToConst() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Dummy cast(IScope scope, Object obj, Object param, boolean copy) throws GamaRuntimeException {
		if(obj instanceof Dummy){
			
			return new Dummy(((Dummy) obj).getWord(), ((Dummy) obj).getNumber());
		}
		return null;
	}

	@Override
	public Dummy getDefault() {
		// TODO Auto-generated method stub
		return null;
	}

}
