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
import ummisco.gama.distributions.Pearson3Gen;
import ummisco.gama.distributions.Pearson5Gene;
import ummisco.gama.distributions.Pearson6Gene;
import ummisco.gama.distributions.ShiftedLogNormalGen;
import ummisco.gama.distributions.SingletonStream;
import umontreal.ssj.randvar.ExponentialGen;
import umontreal.ssj.randvar.GammaGen;
import umontreal.ssj.randvar.InverseGammaGen;
import umontreal.ssj.randvar.LognormalGen;
import umontreal.ssj.randvar.PoissonGen;
import umontreal.ssj.randvar.RandomVariateGen;
import umontreal.ssj.rng.GenF2w32;
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
		//RandomStream stream = SingletonStream.getInstance();
		RandomStream stream = new GenF2w32();
		if(name.equals(ITrafficLaw.PEARSON_5))
			return new Pearson5Gene(stream, (parameters.get(ITrafficLaw.ALPHA)).doubleValue(), (parameters.get(ITrafficLaw.BETA)).doubleValue(), (parameters.get(ITrafficLaw.U)).doubleValue());
		
		if(name.equals(ITrafficLaw.PEARSON_3)) // lambda : rate comme gamma
			return new Pearson3Gen(stream, (parameters.get(ITrafficLaw.ALPHA)).doubleValue(), (parameters.get(ITrafficLaw.SIGMA)).doubleValue(), (parameters.get(ITrafficLaw.U)).doubleValue());
		
		if(name.equals(ITrafficLaw.PEARSON_6)) // alpha1 =  shape1, alpha2 = shape 2, beta = scale, u = location
			return new Pearson6Gene(stream, (parameters.get(ITrafficLaw.ALPHA)).doubleValue(), (parameters.get(ITrafficLaw.ALPHA2)).doubleValue(), (parameters.get(ITrafficLaw.BETA)).doubleValue(), (parameters.get(ITrafficLaw.U)).doubleValue());
		
		
		if(name.equals(ITrafficLaw.PARETO_4))
			return new Pareto4Gen(stream, (parameters.get(ITrafficLaw.ALPHA)).doubleValue(), (parameters.get(ITrafficLaw.GAMMA)).doubleValue(), (parameters.get(ITrafficLaw.SIGMA)).doubleValue(), (parameters.get(ITrafficLaw.U)).doubleValue());
		
		if(name.equals(ITrafficLaw.PARETO_3))
			return new Pareto3Gen(stream, (parameters.get(ITrafficLaw.U)).doubleValue(), (parameters.get(ITrafficLaw.GAMMA)).doubleValue(), (parameters.get(ITrafficLaw.BETA)).doubleValue());
		
		if(name.equals(ITrafficLaw.POISSON))
			return new PoissonGen(stream,parameters.get(ITrafficLaw.LAMBDA));
		
		if(name.equals(ITrafficLaw.EXPONENTIAL))
			return new ExponentialGen(stream, parameters.get(ITrafficLaw.LAMBDA));
		
		if(name.equals(ITrafficLaw.GAMMAM)) // alpha = shape, beta = rate
			return new GammaGen(stream, parameters.get(ITrafficLaw.ALPHA), parameters.get(ITrafficLaw.LAMBDA));
		
		if(name.equals(ITrafficLaw.INVERSE_GAMMA)) // alpha = shape, beta = scale
			return new InverseGammaGen(stream, parameters.get(ITrafficLaw.ALPHA), parameters.get(ITrafficLaw.BETA));
		
		if(name.equals(ITrafficLaw.LOGNORMAL)) // meanLog = mu; sdlog=sigma
			return new LognormalGen(stream, parameters.get(ITrafficLaw.MEANLOG), parameters.get(ITrafficLaw.SDLOG));
		
		if(name.equals(ITrafficLaw.SHIFTED_LOGNORMAL))
			return new ShiftedLogNormalGen(stream, parameters.get(ITrafficLaw.MEANLOG), parameters.get(ITrafficLaw.SDLOG), parameters.get(ITrafficLaw.U));
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
		if(map.containsKey(ITrafficLaw.MEANLOG))
			res.put(ITrafficLaw.MEANLOG, map.get(ITrafficLaw.MEANLOG));
		if(map.containsKey(ITrafficLaw.SDLOG))
			res.put(ITrafficLaw.SDLOG, map.get(ITrafficLaw.SDLOG));
		if(map.containsKey(ITrafficLaw.ALPHA2))
			res.put(ITrafficLaw.ALPHA2, map.get(ITrafficLaw.ALPHA2));
		return res;
	}
	
	@operator(value=ITrafficLaw.PARETO_4_LAW)
	@doc(value = "create a pareto 4 traffic law pareto_4_law(alpha(shape),gamma(inequality), sigma(scale), u(location))")
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
	@doc(value = "create a pareto 3 traffic law pareto_3_law(gamma(inequality), beta(scale), u(location))")
	public static TrafficLaw createPareto3(final IScope scope, final Double gamma, final Double beta, final Double u){
		
		TrafficLaw res = new TrafficLaw();
		res.put(ITrafficLaw.MODEL, ITrafficLaw.PARETO_3);
		res.put(ITrafficLaw.GAMMA, gamma);
		res.put(ITrafficLaw.BETA, beta);
		res.put(ITrafficLaw.U, u);
		return res;
	}
	
	@operator(value=ITrafficLaw.PEARSON_5_LAW)
	@doc(value = "create a pearson 5 traffic law person_5_law(alpha(shape), beta(scale),u(location));")
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
	@doc(value = "create a poisson traffic law")
	public static TrafficLaw createPoisson(final IScope scope, final Double lambda){
		TrafficLaw res = new TrafficLaw();
		res.put(ITrafficLaw.MODEL, ITrafficLaw.POISSON);
		res.put(ITrafficLaw.LAMBDA, lambda);
		return res;
	}
	

	@operator(value=ITrafficLaw.GAMMA_LAW)
	@doc(value = "create a gamma traffic law gamma_law(alpha(shape),lambda(rate))")
	public static TrafficLaw createGamma(final IScope scope, final Double alpha, final Double lambda){
		TrafficLaw res = new TrafficLaw();
		res.put(ITrafficLaw.MODEL, ITrafficLaw.GAMMAM);
		res.put(ITrafficLaw.ALPHA, alpha);
		res.put(ITrafficLaw.LAMBDA, lambda);
		return res;
	}
	
	@operator(value=ITrafficLaw.INVERSE_GAMMA_LAW)
	@doc(value = "create a inverse gamma traffic law inverse_gamma_law(alpha(shape), beta(scale))")
	public static TrafficLaw createInverseGamma(final IScope scope, final Double alpha, final Double beta){
		TrafficLaw res = new TrafficLaw();
		res.put(ITrafficLaw.MODEL, ITrafficLaw.INVERSE_GAMMA);
		res.put(ITrafficLaw.ALPHA, alpha);
		res.put(ITrafficLaw.BETA, beta);
		return res;
	}
	
	
	@operator(value=ITrafficLaw.LOGNORMAL_LAW)
	@doc(value = "create a lognormal traffic law lognormal_law(meanlog,sdlog)")
	public static TrafficLaw createLogNormal(final IScope scope, final Double meanLog, final Double sdLog){
		TrafficLaw res = new TrafficLaw();
		res.put(ITrafficLaw.MODEL, ITrafficLaw.LOGNORMAL);
		res.put(ITrafficLaw.MEANLOG, meanLog);
		res.put(ITrafficLaw.SDLOG, sdLog);
		return res;
	}
	
	@operator(value=ITrafficLaw.SHIFTED_LOGNORMAL_LAW)
	@doc(value = "create a shifted lognormal traffic law shifted_lognormal_law(meanlog, sdlog, u(location))")
	public static TrafficLaw createShiftedLogNormal(final IScope scope, final Double meanLog, final Double sdLog, final Double u){
		TrafficLaw res = new TrafficLaw();
		res.put(ITrafficLaw.MODEL, ITrafficLaw.SHIFTED_LOGNORMAL);
		res.put(ITrafficLaw.MEANLOG, meanLog);
		res.put(ITrafficLaw.SDLOG, sdLog);
		res.put(ITrafficLaw.U,  u);
		return res;
	}
	
	@operator(value=ITrafficLaw.PEARSON_3_LAW)
	@doc(value = "create a Pearson 3 traffic law pearson_3_law(alpha(shape), sigma(scale), u(location))")
	public static TrafficLaw createPearson3(final IScope scope, final Double alpha, final Double sigma, final Double u){
		TrafficLaw res = new TrafficLaw();
		res.put(ITrafficLaw.MODEL, ITrafficLaw.PEARSON_3);
		res.put(ITrafficLaw.ALPHA, alpha);
		res.put(ITrafficLaw.SIGMA, sigma);
		res.put(ITrafficLaw.U,  u);
		return res;
	}
	
	
	@operator(value=ITrafficLaw.PEARSON_6_LAW)
	@doc(value = "create a Pearson 6 traffic law pearson_6_law(alpha1(shape1), alpha2(shape2), beta(scale), u(location))")
	public static TrafficLaw createPearson6(final IScope scope, final Double alpha1, final Double alpha2, final Double beta, final Double u){
		TrafficLaw res = new TrafficLaw();
		res.put(ITrafficLaw.MODEL, ITrafficLaw.PEARSON_6);
		res.put(ITrafficLaw.ALPHA, alpha1);
		res.put(ITrafficLaw.ALPHA2, alpha2);
		res.put(ITrafficLaw.BETA, beta);
		res.put(ITrafficLaw.U,  u);
		return res;
	}
	
	
	
}
