package gaml.additions;
import msi.gama.outputs.layers.*;
import msi.gama.outputs.*;
import msi.gama.kernel.batch.*;
import msi.gama.kernel.root.*;
import msi.gaml.architecture.weighted_tasks.*;
import msi.gaml.architecture.user.*;
import msi.gama.outputs.layers.charts.*;
import msi.gaml.architecture.reflex.*;
import msi.gaml.architecture.finite_state_machine.*;
import msi.gaml.species.*;
import msi.gama.metamodel.shape.*;
import msi.gaml.expressions.*;
import msi.gama.metamodel.topology.*;
import msi.gaml.statements.test.*;
import msi.gama.metamodel.population.*;
import msi.gama.kernel.simulation.*;
import java.util.*;
import msi.gaml.statements.draw.*;
import  msi.gama.metamodel.shape.*;
import msi.gama.common.interfaces.*;
import msi.gama.runtime.*;
import java.lang.*;
import msi.gama.metamodel.agent.*;
import msi.gaml.types.*;
import msi.gaml.compilation.*;
import msi.gaml.factories.*;
import msi.gaml.descriptions.*;
import msi.gama.util.file.*;
import msi.gama.util.matrix.*;
import msi.gama.util.graph.*;
import msi.gama.util.path.*;
import msi.gama.util.*;
import msi.gama.runtime.exceptions.*;
import msi.gaml.factories.*;
import msi.gaml.statements.*;
import msi.gaml.skills.*;
import msi.gaml.variables.*;
import msi.gama.kernel.experiment.*;
import msi.gaml.operators.*;
import msi.gaml.extensions.genstar.*;
import msi.gama.common.interfaces.*;
import msi.gama.extensions.messaging.*;
import msi.gama.metamodel.population.*;
import msi.gaml.operators.Random;
import msi.gaml.operators.Maths;
import msi.gaml.operators.Points;
import msi.gaml.operators.Spatial.Properties;
import msi.gaml.operators.System;
import static msi.gaml.operators.Cast.*;
import static msi.gaml.operators.Spatial.*;
import static msi.gama.common.interfaces.IKeyword.*;
	@SuppressWarnings({ "rawtypes", "unchecked" })

public class GamlAdditions extends AbstractGamlAdditions {
	public void initialize() throws SecurityException, NoSuchMethodException {
	initializeDoc();
	initializeType();
	initializeFactory();
	initializeSpecies();
	initializeSymbol();
	initializeVars();
	initializeOperator();
	initializeFile();
	initializeAction();
	initializeSkill();
	initializeDisplay();
	initializeExperiment();
	initializeConstant();
	initializeTests();
}public void initializeDoc()  {
}public void initializeType()  {
_type("dummy",new ummisco.gama.traffgen.types.DummyType(),89,104,ummisco.gama.traffgen.types.Dummy.class);
_type("traffgen_gen",new ummisco.gama.traffgen.types.TrafficGeneratorType(),4618,104,ummisco.gama.traffgen.types.TrafficGenerator.class);
_type("traffgen_law",new ummisco.gama.traffgen.types.TrafficLawType(),4617,104,ummisco.gama.traffgen.types.TrafficLaw.class);
_type("traffgen_period",new ummisco.gama.traffgen.types.TrafficPeriodType(),4620,104,ummisco.gama.traffgen.types.TrafficPeriod.class);
_type("traffgen_scheduler",new ummisco.gama.traffgen.types.TrafficSchedulerType(),4621,104,ummisco.gama.traffgen.types.TrafficScheduler.class);
}public void initializeFactory()  {
}public void initializeSpecies()  {
}public void initializeSymbol()  {
}public void initializeVars()  {
_field(ummisco.gama.traffgen.types.Dummy.class,(String)null,new OperatorProto("word", null, new GamaHelper(){ @Override public String run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.Dummy) v[0]).getWord();}}, false, true, 4,ummisco.gama.traffgen.types.Dummy.class, false, 4,0,0,AI));
_field(ummisco.gama.traffgen.types.Dummy.class,(String)null,new OperatorProto("number", null, new GamaHelper(){ @Override public Double run(IScope scope, Object... v){return (v==null||v.length==0)? 0d:((ummisco.gama.traffgen.types.Dummy) v[0]).getNumber();}}, false, true, 2,ummisco.gama.traffgen.types.Dummy.class, false, 2,0,0,AI));
_field(ummisco.gama.traffgen.types.TrafficGenerator.class,"next agent to create",new OperatorProto("next", null, new GamaHelper(){ @Override public IAgent run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.TrafficGenerator) v[0]).getNext(scope);}}, false, true, 11,ummisco.gama.traffgen.types.TrafficGenerator.class, false, 11,0,0,AI));
_field(ummisco.gama.traffgen.types.TrafficGenerator.class,"previous created agent",new OperatorProto("previous", null, new GamaHelper(){ @Override public IAgent run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.TrafficGenerator) v[0]).getPreviousOne(scope);}}, false, true, 11,ummisco.gama.traffgen.types.TrafficGenerator.class, false, 11,0,0,AI));
_var(ummisco.gama.traffgen.types.TrafficGenerator.class,"previous created agent inside the buffer",desc(5,S(TYPE,"5",NAME,"buffered_previous",CONST,FALSE)),null,null,null);
_field(ummisco.gama.traffgen.types.TrafficLaw.class,"list of parameters for the law",new OperatorProto("parameters", null, new GamaHelper(){ @Override public GamaMap run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.TrafficLaw) v[0]).getParameters();}}, false, true, 10,ummisco.gama.traffgen.types.TrafficLaw.class, false, 10,0,0,AI));
_field(ummisco.gama.traffgen.types.TrafficLaw.class,"Name of the model",new OperatorProto("model", null, new GamaHelper(){ @Override public String run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.TrafficLaw) v[0]).getModel();}}, false, true, 4,ummisco.gama.traffgen.types.TrafficLaw.class, false, 4,0,0,AI));
_field(ummisco.gama.traffgen.types.TrafficLaw.class,"Next value of the random generator",new OperatorProto("next", null, new GamaHelper(){ @Override public Double run(IScope scope, Object... v){return (v==null||v.length==0)? 0d:((ummisco.gama.traffgen.types.TrafficLaw) v[0]).getNext();}}, false, true, 2,ummisco.gama.traffgen.types.TrafficLaw.class, false, 2,0,0,AI));
_var(ummisco.gama.traffgen.types.TrafficPeriod.class,"the generator",desc(-201,S(TYPE,"-201",NAME,"generator",CONST,FALSE)),null,null,null);
_var(ummisco.gama.traffgen.types.TrafficPeriod.class,"number of allowed individuals",desc(0,S(TYPE,"0",NAME,"individual",CONST,FALSE)),null,null,null);
_var(ummisco.gama.traffgen.types.TrafficPeriod.class,"duration of this period",desc(2,S(TYPE,"2",NAME,"duration",CONST,FALSE)),null,null,null);
_field(ummisco.gama.traffgen.types.TrafficPeriod.class,"next agent to create in traffic period",new OperatorProto("next", null, new GamaHelper(){ @Override public IAgent run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.TrafficPeriod) v[0]).getNext(scope);}}, false, true, 11,ummisco.gama.traffgen.types.TrafficPeriod.class, false, 11,0,0,AI));
_field(ummisco.gama.traffgen.types.TrafficScheduler.class,"next agent to create in scheduler",new OperatorProto("next", null, new GamaHelper(){ @Override public IAgent run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.TrafficScheduler) v[0]).getNext(scope);}}, false, true, 11,ummisco.gama.traffgen.types.TrafficScheduler.class, false, 11,0,0,AI));
_var(ummisco.gama.traffgen.types.TrafficScheduler.class,"Type of the scheduler",desc(4,S(TYPE,"4",NAME,TYPE,CONST,FALSE)),null,null,null);
_field(ummisco.gama.traffgen.types.VehicleGeneratorX.class,"list of vehicle type to generate",new OperatorProto("vehicleList", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGeneratorX) v[0]).getVehicleList();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGeneratorX.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGeneratorX.class,"list of speed of each vehicle in vehicle List",new OperatorProto("speedList", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGeneratorX) v[0]).getSpeedList();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGeneratorX.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGeneratorX.class,"List of time Headway of each vehicle in list",new OperatorProto("timeHeadwayList", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGeneratorX) v[0]).getTimeHeadwayList();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGeneratorX.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGeneratorX.class,"list of preriod number based on the sequence of generation",new OperatorProto("periodList", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGeneratorX) v[0]).getPeriodList();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGeneratorX.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGeneratorX.class,"list of number of vehicles for each period in the generation",new OperatorProto("vehicleNumber", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGeneratorX) v[0]).getVehicleNumber();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGeneratorX.class, false, 5,0,0,AI));
}public void initializeOperator() throws SecurityException, NoSuchMethodException {
_operator(S("atomic_traffgen"),ummisco.gama.traffgen.types.TrafficGenerator.class.getMethod("createAtomicGenerator", IScope.class,LI,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class),C(LI,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class),AI,ummisco.gama.traffgen.types.TrafficGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficGenerator.createAtomicGenerator(s,((IList)o[0]),((ummisco.gama.traffgen.types.TrafficLaw)o[1]),((ummisco.gama.traffgen.types.TrafficLaw)o[2]));}});
_operator(S("atomic_traffgen"),ummisco.gama.traffgen.types.TrafficGenerator.class.getMethod("createAtomicGenerator", IScope.class,LI,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,IS),C(LI,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,IS),AI,ummisco.gama.traffgen.types.TrafficGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficGenerator.createAtomicGenerator(s,((IList)o[0]),((ummisco.gama.traffgen.types.TrafficLaw)o[1]),((ummisco.gama.traffgen.types.TrafficLaw)o[2]),((IShape)o[3]));}});
_operator(S("atomic_traffgen"),ummisco.gama.traffgen.types.TrafficGenerator.class.getMethod("createAtomicGenerator", IScope.class,LI,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,int.class,IS),C(LI,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,int.class,IS),AI,ummisco.gama.traffgen.types.TrafficGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficGenerator.createAtomicGenerator(s,((IList)o[0]),((ummisco.gama.traffgen.types.TrafficLaw)o[1]),((ummisco.gama.traffgen.types.TrafficLaw)o[2]),((ummisco.gama.traffgen.types.TrafficLaw)o[3]),asInt(s,o[4]),((IShape)o[5]));}});
_operator(S("atomic_traffgen"),ummisco.gama.traffgen.types.TrafficGenerator.class.getMethod("createAtomicGenerator", IScope.class,GamlSpecies.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class),C(GamlSpecies.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class),AI,ummisco.gama.traffgen.types.TrafficGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficGenerator.createAtomicGenerator(s,((GamlSpecies)o[0]),((ummisco.gama.traffgen.types.TrafficLaw)o[1]),((ummisco.gama.traffgen.types.TrafficLaw)o[2]));}});
_operator(S("atomic_traffgen"),ummisco.gama.traffgen.types.TrafficGenerator.class.getMethod("createAtomicGenerator", IScope.class,GamlSpecies.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,IS),C(GamlSpecies.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,IS),AI,ummisco.gama.traffgen.types.TrafficGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficGenerator.createAtomicGenerator(s,((GamlSpecies)o[0]),((ummisco.gama.traffgen.types.TrafficLaw)o[1]),((ummisco.gama.traffgen.types.TrafficLaw)o[2]),((IShape)o[3]));}});
_operator(S("atomic_traffgen"),ummisco.gama.traffgen.types.TrafficGenerator.class.getMethod("createAtomicGenerator", IScope.class,GamlSpecies.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,int.class),C(GamlSpecies.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,int.class),AI,ummisco.gama.traffgen.types.TrafficGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficGenerator.createAtomicGenerator(s,((GamlSpecies)o[0]),((ummisco.gama.traffgen.types.TrafficLaw)o[1]),((ummisco.gama.traffgen.types.TrafficLaw)o[2]),((ummisco.gama.traffgen.types.TrafficLaw)o[3]),asInt(s,o[4]));}});
_operator(S("atomic_traffgen"),ummisco.gama.traffgen.types.TrafficGenerator.class.getMethod("createAtomicGenerator", IScope.class,GamlSpecies.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,int.class,IS),C(GamlSpecies.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,int.class,IS),AI,ummisco.gama.traffgen.types.TrafficGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficGenerator.createAtomicGenerator(s,((GamlSpecies)o[0]),((ummisco.gama.traffgen.types.TrafficLaw)o[1]),((ummisco.gama.traffgen.types.TrafficLaw)o[2]),((ummisco.gama.traffgen.types.TrafficLaw)o[3]),asInt(s,o[4]),((IShape)o[5]));}});
_operator(S("map_traffgen"),ummisco.gama.traffgen.types.TrafficGenerator.class.getMethod("createMapTrafficGenerator", IScope.class,LI,GM),C(LI,GM),AI,ummisco.gama.traffgen.types.TrafficGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficGenerator.createMapTrafficGenerator(s,((IList)o[0]),((GamaMap)o[1]));}});
_operator(S("synchronized_traffgen"),ummisco.gama.traffgen.types.TrafficGenerator.class.getMethod("createSynchronizeTrafficGenerator", IScope.class,LI,LI),C(LI,LI),AI,ummisco.gama.traffgen.types.TrafficGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficGenerator.createSynchronizeTrafficGenerator(s,((IList)o[0]),((IList)o[1]));}});
_operator(S("synchronized_traffgen"),ummisco.gama.traffgen.types.TrafficGenerator.class.getMethod("createSynchronizeTrafficGenerator", IScope.class,LI,IM),C(LI,IM),AI,ummisco.gama.traffgen.types.TrafficGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficGenerator.createSynchronizeTrafficGenerator(s,((IList)o[0]),((IMatrix)o[1]));}});
_operator(S("exponential_law"),ummisco.gama.traffgen.types.TrafficLaw.class.getMethod("createExponential", IScope.class,D),C(D),AI,ummisco.gama.traffgen.types.TrafficLaw.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficLaw run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficLaw.createExponential(s,asFloat(s,o[0]));}});
_operator(S("pareto_3_law"),ummisco.gama.traffgen.types.TrafficLaw.class.getMethod("createPareto3", IScope.class,D,D,D),C(D,D,D),AI,ummisco.gama.traffgen.types.TrafficLaw.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficLaw run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficLaw.createPareto3(s,asFloat(s,o[0]),asFloat(s,o[1]),asFloat(s,o[2]));}});
_operator(S("pareto_4_law"),ummisco.gama.traffgen.types.TrafficLaw.class.getMethod("createPareto4", IScope.class,D,D,D,D),C(D,D,D,D),AI,ummisco.gama.traffgen.types.TrafficLaw.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficLaw run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficLaw.createPareto4(s,asFloat(s,o[0]),asFloat(s,o[1]),asFloat(s,o[2]),asFloat(s,o[3]));}});
_operator(S("pearson_5_law"),ummisco.gama.traffgen.types.TrafficLaw.class.getMethod("createPearson5", IScope.class,D,D,D),C(D,D,D),AI,ummisco.gama.traffgen.types.TrafficLaw.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficLaw run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficLaw.createPearson5(s,asFloat(s,o[0]),asFloat(s,o[1]),asFloat(s,o[2]));}});
_operator(S("poisson_law"),ummisco.gama.traffgen.types.TrafficLaw.class.getMethod("createPoisson", IScope.class,D),C(D),AI,ummisco.gama.traffgen.types.TrafficLaw.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficLaw run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficLaw.createPoisson(s,asFloat(s,o[0]));}});
_operator(S("create_period"),ummisco.gama.traffgen.types.TrafficPeriod.class.getMethod("createTrafficPeriod", IScope.class,ummisco.gama.traffgen.types.TrafficGenerator.class,I,I),C(ummisco.gama.traffgen.types.TrafficGenerator.class,I,I),AI,ummisco.gama.traffgen.types.TrafficPeriod.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficPeriod run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficPeriod.createTrafficPeriod(s,((ummisco.gama.traffgen.types.TrafficGenerator)o[0]),asInt(s,o[1]),asInt(s,o[2]));}});
_operator(S("create_period"),ummisco.gama.traffgen.types.TrafficPeriod.class.getMethod("createTrafficPeriod", IScope.class,ummisco.gama.traffgen.types.TrafficGenerator.class,I,ummisco.gama.traffgen.types.TrafficLaw.class),C(ummisco.gama.traffgen.types.TrafficGenerator.class,I,ummisco.gama.traffgen.types.TrafficLaw.class),AI,ummisco.gama.traffgen.types.TrafficPeriod.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficPeriod run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficPeriod.createTrafficPeriod(s,((ummisco.gama.traffgen.types.TrafficGenerator)o[0]),asInt(s,o[1]),((ummisco.gama.traffgen.types.TrafficLaw)o[2]));}});
_operator(S("create_schedule"),ummisco.gama.traffgen.types.TrafficScheduler.class.getMethod("createSchedule", LI,S),C(LI,S),AI,ummisco.gama.traffgen.types.TrafficScheduler.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficScheduler run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficScheduler.createSchedule(((IList)o[0]),((String)o[1]));}});
}public void initializeFile() throws SecurityException, NoSuchMethodException {
}public void initializeAction() throws SecurityException, NoSuchMethodException {
}public void initializeSkill()  {
}public void initializeDisplay()  {
}public void initializeExperiment()  {
}public void initializeConstant()  {
}public void initializeTests()  {
}
}