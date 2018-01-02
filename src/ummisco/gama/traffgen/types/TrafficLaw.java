package ummisco.gama.traffgen.types;

import java.util.Map;

import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.getter;
import msi.gama.precompiler.GamlAnnotations.operator;
import msi.gama.precompiler.GamlAnnotations.var;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gama.runtime.IScope;
import msi.gama.util.GamaMap;
import msi.gaml.types.IType;
import msi.gaml.types.Types;
import ummisco.gama.distributions.Pareto3Gen;
import ummisco.gama.distributions.Pareto4Gen;
import ummisco.gama.distributions.Pearson5Gene;
import ummisco.gama.distributions.SingletonStream;
import umontreal.ssj.randvar.ExponentialGen;
import umontreal.ssj.randvar.PoissonGen;
import umontreal.ssj.randvar.RandomVariateGen;
import umontreal.ssj.rng.RandomStream;


@vars({
	@var(name = ITrafficLaw.PARAMETERS, type = IType.MAP, doc = @doc("list of parameters for the law")),
	@var(name = ITrafficLaw.MODEL, type = IType.STRING, doc = @doc("Name of the model")),
	@var(name = ITrafficLaw.NEXT, type = IType.FLOAT, doc=@doc("Next value of the random generator"))
})
public class TrafficLaw extends GamaMap<String, Object> {
	
	public static final int Id = IType.AVAILABLE_TYPES+4567;
	
	RandomVariateGen internalDistribution = null;
	
	public TrafficLaw() {
		super(11, Types.STRING, Types.NO_TYPE);
	}
	
	private boolean isPrivateData(String dte) {
		for(String s : ITrafficLaw.privateData)
			if(s.equals(dte))
				return true;
		return false;
	}

	@getter(ITrafficLaw.PARAMETERS)
	public GamaMap<String, Double> getParameters() {
		GamaMap<String, Double> res = new GamaMap<String,Double>(11, Types.STRING, Types.FLOAT);
		for(String nm:this.keySet()) {
			if(!isPrivateData(nm))
				res.put(nm, (Double)this.get(nm));
		}
		return res;
	}
	
	@getter(ITrafficLaw.NEXT)
	public double getNext()
	{
		if(this.internalDistribution==null)
			this.init();
		return this.internalDistribution.nextDouble();
	}
	
	@getter(ITrafficLaw.MODEL)
	public String getModel() {
		return (String) this.get(ITrafficLaw.MODEL);
	}
	
	private void init()
	{
		String model = this.getModel();
		this.internalDistribution = TrafficLaw.buildDistribution(model, this.getParameters());
	}
	
	static RandomVariateGen buildDistribution(String name, Map<String,Double> parameters)
	{
		RandomStream stream = SingletonStream.getInstance();
		if(name.equals(ITrafficLaw.PEARSON_5))
			return new Pearson5Gene(stream, (parameters.get(ITrafficLaw.ALPHA)).doubleValue(), (parameters.get(ITrafficLaw.BETA)).doubleValue(), (parameters.get(ITrafficLaw.U)).doubleValue());
		if(name.equals(ITrafficLaw.PARETO_4))
			return new Pareto4Gen(stream, (parameters.get(ITrafficLaw.ALPHA)).doubleValue(), (parameters.get(ITrafficLaw.GAMMA)).doubleValue(), (parameters.get(ITrafficLaw.SIGMA)).doubleValue(), (parameters.get(ITrafficLaw.U)).doubleValue());
		if(name.equals(ITrafficLaw.PARETO_3))
			return new Pareto3Gen(stream, (parameters.get(ITrafficLaw.U)).doubleValue(), (parameters.get(ITrafficLaw.GAMMA)).doubleValue(), (parameters.get(ITrafficLaw.BETA)).doubleValue());
		if(name.equals(ITrafficLaw.POISSON))
			return new PoissonGen(stream,parameters.get(ITrafficLaw.LAMBDA));
		if(name.equals(ITrafficLaw.EXPONENTIAL))
			return new ExponentialGen(stream, parameters.get(ITrafficLaw.LAMBDA));
		return null;
	}
	
	
	public static TrafficLaw createFromMap(GamaMap<String, Object> map)
	{
		TrafficLaw res = new TrafficLaw();
		Object mdl = map.get(ITrafficLaw.MODEL);
		if(mdl != null && mdl instanceof String)
			res.put(ITrafficLaw.MODEL, mdl);
		else
			return null;
		if(map.containsKey(ITrafficLaw.ALPHA))
			res.put(ITrafficLaw.ALPHA, map.get(ITrafficLaw.ALPHA));
		if(map.containsKey(ITrafficLaw.BETA))
			res.put(ITrafficLaw.BETA, map.get(ITrafficLaw.BETA));
		if(map.containsKey(ITrafficLaw.GAMMA))
			res.put(ITrafficLaw.GAMMA, map.get(ITrafficLaw.GAMMA));
		if(map.containsKey(ITrafficLaw.U))
			res.put(ITrafficLaw.U, map.get(ITrafficLaw.U));
		if(map.containsKey(ITrafficLaw.SIGMA))
			res.put(ITrafficLaw.SIGMA, map.get(ITrafficLaw.SIGMA));
		if(map.containsKey(ITrafficLaw.LAMBDA))
			res.put(ITrafficLaw.LAMBDA, map.get(ITrafficLaw.LAMBDA));
		return res;
	}
	
	@operator(value=ITrafficLaw.PARETO_4_LAW)
	@doc(value = "create a pareto 4 traffic law")
	public static TrafficLaw createPareto4(final IScope scope, final Double alpha, final Double gamma, final Double sigma, final Double u){
		
		TrafficLaw res = new TrafficLaw();
		res.put(ITrafficLaw.MODEL, ITrafficLaw.PARETO_4);
		res.put(ITrafficLaw.ALPHA, alpha);
		res.put(ITrafficLaw.GAMMA, gamma);
		res.put(ITrafficLaw.SIGMA, sigma);
		res.put(ITrafficLaw.U, u);
		return res;
	}

	@operator(value=ITrafficLaw.PARETO_3_LAW)
	@doc(value = "create a pareto 3 traffic law")
	public static TrafficLaw createPareto3(final IScope scope, final Double gamma, final Double beta, final Double u){
		
		TrafficLaw res = new TrafficLaw();
		res.put(ITrafficLaw.MODEL, ITrafficLaw.PARETO_3);
		res.put(ITrafficLaw.GAMMA, gamma);
		res.put(ITrafficLaw.BETA, beta);
		res.put(ITrafficLaw.U, u);
		return res;
	}
	
	@operator(value=ITrafficLaw.PEARSON_5_LAW)
	@doc(value = "create a pearson 5 traffic law person_5_law(alpha, beta,u);")
	public static TrafficLaw createPearson5(final IScope scope, final Double alpha, final Double beta, final Double u){
		TrafficLaw res = new TrafficLaw();
		res.put(ITrafficLaw.MODEL, ITrafficLaw.PEARSON_5);
		res.put(ITrafficLaw.ALPHA, alpha);
		res.put(ITrafficLaw.BETA, beta);
		res.put(ITrafficLaw.U, u);
		return res;
	}
	
	@operator(value=ITrafficLaw.EXPO_LAW)
	@doc(value = "create an exponential traffic law")
	public static TrafficLaw createExponential(final IScope scope, final Double lambda){
		TrafficLaw res = new TrafficLaw();
		res.put(ITrafficLaw.MODEL, ITrafficLaw.EXPONENTIAL);
		res.put(ITrafficLaw.LAMBDA, lambda);
		return res;
	}
	
	@operator(value=ITrafficLaw.POISSON_LAW)
	@doc(value = "create an exponential traffic law")
	public static TrafficLaw createPoisson(final IScope scope, final Double lambda){
		TrafficLaw res = new TrafficLaw();
		res.put(ITrafficLaw.MODEL, ITrafficLaw.EXPONENTIAL);
		res.put(ITrafficLaw.LAMBDA, lambda);
		return res;
	}
	

	
	
}
