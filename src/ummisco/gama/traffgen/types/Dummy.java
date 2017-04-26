package ummisco.gama.traffgen.types;

import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gama.precompiler.GamlAnnotations.operator;
import msi.gama.precompiler.GamlAnnotations.setter;
import msi.gama.precompiler.GamlAnnotations.var;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.runtime.IScope;
import msi.gaml.types.IType;

@vars({
	@var(name="word", type=IType.STRING),
	@var(name="number", type=IType.FLOAT)
})
public class Dummy {

	public final static int Id = 89;
	private String word;
	private Double number;
	
	
	public Dummy(final String word, final Double number) {
		// TODO Auto-generated constructor stub
		this.word = word;
		this.number = number;
	}


	
	@getter("word")
	public String getWord() {
		return word;
	}
	
	@setter("word")
	public void setWord(String word) {
		this.word = word;
	}
	@getter("number")
	public Double getNumber() {
		return number;
	}
	
	@setter("number")
	public void setNumber(Double number) {
		this.number = number;
	}
	
}
