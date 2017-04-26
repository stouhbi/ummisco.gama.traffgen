package gaml.additions;
import msi.gama.outputs.layers.*;
import msi.gama.outputs.*;
import msi.gama.kernel.batch.*;
import msi.gaml.architecture.weighted_tasks.*;
import msi.gaml.architecture.user.*;
import msi.gaml.architecture.reflex.*;
import msi.gaml.architecture.finite_state_machine.*;
import msi.gaml.species.*;
import msi.gama.metamodel.shape.*;
import msi.gaml.expressions.*;
import msi.gama.metamodel.topology.*;
import msi.gama.metamodel.population.*;
import msi.gama.kernel.simulation.*;
import java.util.*;
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
	initializeTypes();
	initializeSymbols();
	initializeVars();
	initializeOperators();
	initializeFiles();
	initializeActions();
	initializeSkills();
	initializeSpecies();
	initializeDisplays();
	initializeExperiments();
	initializePopulationsLinkers();
}
public void initializeTypes() {
_type("dummy",new ummisco.gama.traffgen.types.DummyType(),89,104,ummisco.gama.traffgen.types.Dummy.class);
_type("period",new ummisco.gama.traffgen.types.PeriodHeadwayGeneratorType(),98,104,ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class);
_type("speed",new ummisco.gama.traffgen.types.SpeedGeneratorType(),96,104,ummisco.gama.traffgen.types.SpeedGenerator.class);
_type("vehicleGenerator",new ummisco.gama.traffgen.types.VehicleGeneratorType(),99,104,ummisco.gama.traffgen.types.VehicleGenerator.class);
_type("timeHeadway",new ummisco.gama.traffgen.types.VehicleTHGeneratorType(),97,104,ummisco.gama.traffgen.types.VehicleTHGenerator.class);};
public void initializeSpecies() {};
public void initializeSymbols() {};
public void initializeVars() throws SecurityException, NoSuchMethodException {
_var(ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class,"list of generators",desc(10,S(TYPE,"10",NAME,"generators",CONST,FALSE)),new GamaHelper(ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class){ @Override public Map run(IScope scope, IAgent a, IVarAndActionSupport t, Object... v) {return t == null? null:((ummisco.gama.traffgen.skills.VehicleGeneratorSkill)t).getGenerators();}},null,new GamaHelper(ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class){ @Override public Object  run(IScope scope, IAgent a, IVarAndActionSupport t, Object... arg) {if (t != null) ((ummisco.gama.traffgen.skills.VehicleGeneratorSkill) t).setGenerators((Map) arg[0]); return null; }});
_field(ummisco.gama.traffgen.types.Dummy.class,(String)null,new OperatorProto("word", null, new GamaHelper(){ @Override public String run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.Dummy) v[0]).getWord();}}, false, true, 4,ummisco.gama.traffgen.types.Dummy.class, false, 4,0,0,AI));
_field(ummisco.gama.traffgen.types.Dummy.class,(String)null,new OperatorProto("number", null, new GamaHelper(){ @Override public Double run(IScope scope, Object... v){return (v==null||v.length==0)? 0d:((ummisco.gama.traffgen.types.Dummy) v[0]).getNumber();}}, false, true, 2,ummisco.gama.traffgen.types.Dummy.class, false, 2,0,0,AI));
_field(ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class,"the number of the period in order",new OperatorProto("periodSequence", null, new GamaHelper(){ @Override public Integer run(IScope scope, Object... v){return (v==null||v.length==0)? 0:((ummisco.gama.traffgen.types.PeriodHeadwayGenerator) v[0]).getPeriodSequence();}}, false, true, 1,ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class, false, 1,0,0,AI));
_field(ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class,"the duration of the generation for this period",new OperatorProto("duration", null, new GamaHelper(){ @Override public Integer run(IScope scope, Object... v){return (v==null||v.length==0)? 0:((ummisco.gama.traffgen.types.PeriodHeadwayGenerator) v[0]).getDuration();}}, false, true, 1,ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class, false, 1,0,0,AI));
_field(ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class,"the TH Generator for each vehicle Type",new OperatorProto("THGenerators", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.PeriodHeadwayGenerator) v[0]).getTHGenerators();}}, false, true, 5,ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class,"The number of vehicle per period generation model",new OperatorProto("countGenerationModel", null, new GamaHelper(){ @Override public Map run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.PeriodHeadwayGenerator) v[0]).getCountGenerationModel();}}, false, true, 10,ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class, false, 10,4,0,AI));
_field(ummisco.gama.traffgen.types.SpeedGenerator.class,"the mean speed per vehicle",new OperatorProto("meanSpeed", null, new GamaHelper(){ @Override public Map run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.SpeedGenerator) v[0]).getMeanSpeed();}}, false, true, 10,ummisco.gama.traffgen.types.SpeedGenerator.class, false, 10,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"the time interval on which this generation will occur",new OperatorProto("timeInterval", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getTimeInterval();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"the coordinates on which the generation will occur",new OperatorProto("coordinates", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getCoordinates();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"the transition type",new OperatorProto("transitionType", null, new GamaHelper(){ @Override public String run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getTransitionType();}}, false, true, 4,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 4,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"The vehicle Types generated by this generator",new OperatorProto("vehicleTypes", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getVehicleTypes();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"The transition Matrix",new OperatorProto("transition", null, new GamaHelper(){ @Override public IMatrix run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getTransition();}}, false, true, 8,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 8,2,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"the speed metadata",new OperatorProto("speed", null, new GamaHelper(){ @Override public ummisco.gama.traffgen.types.SpeedGenerator run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getSpeed();}}, false, true, 96,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 96,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"the list of timeHeadway metadata",new OperatorProto("timeHeadway", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getTimeHeadway();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"list of vehicle type to generate",new OperatorProto("vehicleList", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getVehicleList();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"list of speed of each vehicle in vehicle List",new OperatorProto("speedList", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getSpeedList();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"List of time Headway of each vehicle in list",new OperatorProto("timeHeadwayList", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getTimeHeadwayList();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"list of preriod number based on the sequence of generation",new OperatorProto("periodList", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getPeriodList();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"list of number of vehicles for each period in the generation",new OperatorProto("vehicleNumber", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getVehicleNumber();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGeneratorX.class,"list of vehicle type to generate",new OperatorProto("vehicleList", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGeneratorX) v[0]).getVehicleList();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGeneratorX.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGeneratorX.class,"list of speed of each vehicle in vehicle List",new OperatorProto("speedList", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGeneratorX) v[0]).getSpeedList();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGeneratorX.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGeneratorX.class,"List of time Headway of each vehicle in list",new OperatorProto("timeHeadwayList", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGeneratorX) v[0]).getTimeHeadwayList();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGeneratorX.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGeneratorX.class,"list of preriod number based on the sequence of generation",new OperatorProto("periodList", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGeneratorX) v[0]).getPeriodList();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGeneratorX.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGeneratorX.class,"list of number of vehicles for each period in the generation",new OperatorProto("vehicleNumber", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGeneratorX) v[0]).getVehicleNumber();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGeneratorX.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleTHGenerator.class,"the vehicleTypes on which this generator is applied to",new OperatorProto("vehicleTypes", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleTHGenerator) v[0]).getVehicleTypes();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleTHGenerator.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleTHGenerator.class,"the headway  generation model for each",new OperatorProto("headwayGenerationModel", null, new GamaHelper(){ @Override public Map run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleTHGenerator) v[0]).getHeadwayGenerationModel();}}, false, true, 10,ummisco.gama.traffgen.types.VehicleTHGenerator.class, false, 10,0,0,AI));};
public void initializeOperators() throws SecurityException, NoSuchMethodException  {
_operator(S("dummy"),ummisco.gama.traffgen.operators.TraffGen.class.getMethod("createDummy", IScope.class,S,D),C(S,D),I(),ummisco.gama.traffgen.types.Dummy.class,F,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.Dummy run(IScope s,Object... o){return ummisco.gama.traffgen.operators.TraffGen.createDummy(s,((String)o[0]),asFloat(s,o[1]));}});
_operator(S("period"),ummisco.gama.traffgen.operators.TraffGen.class.getMethod("createPeriodTHGenerator", IScope.class,int.class,LI,int.class,Map.class),C(int.class,LI,int.class,Map.class),I(),ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class,F,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.PeriodHeadwayGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.operators.TraffGen.createPeriodTHGenerator(s,asInt(s,o[0]),((IList)o[1]),asInt(s,o[2]),((Map)o[3]));}});
_operator(S("speed"),ummisco.gama.traffgen.operators.TraffGen.class.getMethod("createSpeed", IScope.class,Map.class),C(Map.class),I(),ummisco.gama.traffgen.types.SpeedGenerator.class,F,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.SpeedGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.operators.TraffGen.createSpeed(s,((Map)o[0]));}});
_operator(S("vehicleGenerator"),ummisco.gama.traffgen.operators.TraffGen.class.getMethod("createGenerator", IScope.class,LI,S,IM,LI,LI,ummisco.gama.traffgen.types.SpeedGenerator.class),C(LI,S,IM,LI,LI,ummisco.gama.traffgen.types.SpeedGenerator.class),I(),ummisco.gama.traffgen.types.VehicleGenerator.class,F,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.VehicleGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.operators.TraffGen.createGenerator(s,((IList)o[0]),((String)o[1]),((IMatrix)o[2]),((IList)o[3]),((IList)o[4]),((ummisco.gama.traffgen.types.SpeedGenerator)o[5]));}});
_operator(S("generation_model"),ummisco.gama.traffgen.operators.TraffGen.class.getMethod("generationModel", IScope.class,LI,Map.class),C(LI,Map.class),I(),ummisco.gama.traffgen.types.VehicleTHGenerator.class,F,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.VehicleTHGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.operators.TraffGen.generationModel(s,((IList)o[0]),((Map)o[1]));}});};
public void initializeFiles() throws SecurityException, NoSuchMethodException  {};
public void initializeActions()  throws SecurityException, NoSuchMethodException {
_action("transform",ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class,new GamaHelper(T(IM), ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class){ @Override public IMatrix run(IScope s, IAgent a, IVarAndActionSupport t, Object... v){ return ((ummisco.gama.traffgen.skills.VehicleGeneratorSkill) t).transform(s); } },desc(PRIMITIVE, null, new ChildrenProvider(Arrays.asList(desc(ARG,NAME,"list", TYPE, "5", "optional", FALSE),desc(ARG,NAME,"method", TYPE, "4", "optional", TRUE))), NAME, "transform",TYPE, Ti(IM), VIRTUAL,FALSE),ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class.getMethod("transform", IScope.class));
_action("getVehiculeList",ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class,new GamaHelper(T(void.class), ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class){ @Override public Object run(IScope s, IAgent a, IVarAndActionSupport t, Object... v){  ((ummisco.gama.traffgen.skills.VehicleGeneratorSkill) t).getVehiculeList(s); return null;} },desc(PRIMITIVE, null, new ChildrenProvider(Arrays.asList(desc(ARG,NAME,TYPE, TYPE, "4", "optional", TRUE))), NAME, "generateVehicles",TYPE, Ti(void.class), VIRTUAL,FALSE),ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class.getMethod("getVehiculeList", IScope.class));
_action("initVehicle",ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class,new GamaHelper(T(void.class), ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class){ @Override public Object run(IScope s, IAgent a, IVarAndActionSupport t, Object... v){  ((ummisco.gama.traffgen.skills.VehicleGeneratorSkill) t).initVehicle(s); return null;} },desc(PRIMITIVE, null, new ChildrenProvider(Arrays.asList()), NAME, "initVehicle",TYPE, Ti(void.class), VIRTUAL,FALSE),ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class.getMethod("initVehicle", IScope.class));
_action("launchVehicles",ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class,new GamaHelper(T(void.class), ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class){ @Override public Object run(IScope s, IAgent a, IVarAndActionSupport t, Object... v){  ((ummisco.gama.traffgen.skills.VehicleGeneratorSkill) t).launchVehicles(s); return null;} },desc(PRIMITIVE, null, new ChildrenProvider(Arrays.asList()), NAME, "launchVehicles",TYPE, Ti(void.class), VIRTUAL,FALSE),ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class.getMethod("launchVehicles", IScope.class));};
public void initializeSkills() {
_skill("vehicleGen",ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class);};
public void initializeDisplays() {};
public void initializeExperiments() {};
public void initializePopulationsLinkers() {};

}