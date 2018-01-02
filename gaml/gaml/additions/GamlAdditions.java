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
_type("period_generator",new ummisco.gama.traffgen.types.PeriodHeadwayGeneratorType(),98,104,ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class);
_type("speed_generator",new ummisco.gama.traffgen.types.SpeedGeneratorType(),96,104,ummisco.gama.traffgen.types.SpeedGenerator.class);
_type("traffgen_gen",new ummisco.gama.traffgen.types.TrafficGeneratorType(),4618,104,ummisco.gama.traffgen.types.TrafficGenerator.class);
_type("traffgen_law",new ummisco.gama.traffgen.types.TrafficLawType(),4617,104,ummisco.gama.traffgen.types.TrafficLaw.class);
_type("vehicle_generator",new ummisco.gama.traffgen.types.VehicleGeneratorType(),99,104,ummisco.gama.traffgen.types.VehicleGenerator.class);
_type("timeHeadway",new ummisco.gama.traffgen.types.VehicleTHGeneratorType(),97,104,ummisco.gama.traffgen.types.VehicleTHGenerator.class);
_type("vehicle",new ummisco.gama.traffgen.types.VehicleType(),94,104,ummisco.gama.traffgen.types.Vehicle.class);
_type("traffgen_period",new ummisco.gama.traffgen.types.TrafficPeriodType(),4620,104,ummisco.gama.traffgen.types.TrafficPeriod.class);
}public void initializeFactory()  {
}public void initializeSpecies()  {
}public void initializeSymbol()  {
}public void initializeVars()  {
_var(ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class,"list of generators",desc(10,S(TYPE,"10",NAME,"generators",CONST,FALSE)),null,null,null);
_var(ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class,"the list of vehicles to generate",desc(5,S(TYPE,"5",NAME,"vehicles",CONST,FALSE)),null,null,null);
_field(ummisco.gama.traffgen.types.Dummy.class,(String)null,new OperatorProto("word", null, new GamaHelper(){ @Override public String run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.Dummy) v[0]).getWord();}}, false, true, 4,ummisco.gama.traffgen.types.Dummy.class, false, 4,0,0,AI));
_field(ummisco.gama.traffgen.types.Dummy.class,(String)null,new OperatorProto("number", null, new GamaHelper(){ @Override public Double run(IScope scope, Object... v){return (v==null||v.length==0)? 0d:((ummisco.gama.traffgen.types.Dummy) v[0]).getNumber();}}, false, true, 2,ummisco.gama.traffgen.types.Dummy.class, false, 2,0,0,AI));
_field(ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class,"the number of the period in order",new OperatorProto("periodSequence", null, new GamaHelper(){ @Override public Integer run(IScope scope, Object... v){return (v==null||v.length==0)? 0:((ummisco.gama.traffgen.types.PeriodHeadwayGenerator) v[0]).getPeriodSequence();}}, false, true, 1,ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class, false, 1,0,0,AI));
_field(ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class,"the duration of the generation for this period",new OperatorProto("duration", null, new GamaHelper(){ @Override public Integer run(IScope scope, Object... v){return (v==null||v.length==0)? 0:((ummisco.gama.traffgen.types.PeriodHeadwayGenerator) v[0]).getDuration();}}, false, true, 1,ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class, false, 1,0,0,AI));
_field(ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class,"the TH Generator for each vehicle Type",new OperatorProto("THGenerators", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.PeriodHeadwayGenerator) v[0]).getTHGenerators();}}, false, true, 5,ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class,"The number of vehicle per period generation model",new OperatorProto("countGenerationModel", null, new GamaHelper(){ @Override public Map run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.PeriodHeadwayGenerator) v[0]).getCountGenerationModel();}}, false, true, 10,ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class, false, 10,4,0,AI));
_field(ummisco.gama.traffgen.types.SpeedGenerator.class,"the mean speed per vehicle",new OperatorProto("meanSpeed", null, new GamaHelper(){ @Override public Map run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.SpeedGenerator) v[0]).getMeanSpeed();}}, false, true, 10,ummisco.gama.traffgen.types.SpeedGenerator.class, false, 10,0,0,AI));
_field(ummisco.gama.traffgen.types.TrafficGenerator.class,"next agent to create",new OperatorProto("next", null, new GamaHelper(){ @Override public IAgent run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.TrafficGenerator) v[0]).getNext(scope);}}, false, true, 11,ummisco.gama.traffgen.types.TrafficGenerator.class, false, 11,0,0,AI));
_field(ummisco.gama.traffgen.types.TrafficGenerator.class,"previous created agent",new OperatorProto("previous", null, new GamaHelper(){ @Override public IAgent run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.TrafficGenerator) v[0]).getPreviousOne(scope);}}, false, true, 11,ummisco.gama.traffgen.types.TrafficGenerator.class, false, 11,0,0,AI));
_var(ummisco.gama.traffgen.types.TrafficGenerator.class,"previous created agent inside the buffer",desc(5,S(TYPE,"5",NAME,"buffered_previous",CONST,FALSE)),null,null,null);
_field(ummisco.gama.traffgen.types.TrafficLaw.class,"list of parameters for the law",new OperatorProto("parameters", null, new GamaHelper(){ @Override public GamaMap run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.TrafficLaw) v[0]).getParameters();}}, false, true, 10,ummisco.gama.traffgen.types.TrafficLaw.class, false, 10,0,0,AI));
_field(ummisco.gama.traffgen.types.TrafficLaw.class,"Name of the model",new OperatorProto("model", null, new GamaHelper(){ @Override public String run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.TrafficLaw) v[0]).getModel();}}, false, true, 4,ummisco.gama.traffgen.types.TrafficLaw.class, false, 4,0,0,AI));
_field(ummisco.gama.traffgen.types.TrafficLaw.class,"Next value of the random generator",new OperatorProto("next", null, new GamaHelper(){ @Override public Double run(IScope scope, Object... v){return (v==null||v.length==0)? 0d:((ummisco.gama.traffgen.types.TrafficLaw) v[0]).getNext();}}, false, true, 2,ummisco.gama.traffgen.types.TrafficLaw.class, false, 2,0,0,AI));
_var(ummisco.gama.traffgen.types.TrafficPeriod.class,"list of parameters for the law",desc(-201,S(TYPE,"-201",NAME,"generator",CONST,FALSE)),null,null,null);
_var(ummisco.gama.traffgen.types.TrafficPeriod.class,"Name of the model",desc(0,S(TYPE,"0",NAME,"individual",CONST,FALSE)),null,null,null);
_var(ummisco.gama.traffgen.types.TrafficPeriod.class,"Next value of the random generator",desc(2,S(TYPE,"2",NAME,"duration",CONST,FALSE)),null,null,null);
_field(ummisco.gama.traffgen.types.Vehicle.class,"the type of the vehicle",new OperatorProto(TYPE, null, new GamaHelper(){ @Override public String run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.Vehicle) v[0]).getVehicleType();}}, false, true, 4,ummisco.gama.traffgen.types.Vehicle.class, false, 4,0,0,AI));
_field(ummisco.gama.traffgen.types.Vehicle.class,"the width of the vehicle",new OperatorProto("width", null, new GamaHelper(){ @Override public Double run(IScope scope, Object... v){return (v==null||v.length==0)? 0d:((ummisco.gama.traffgen.types.Vehicle) v[0]).getVehicleWidth();}}, false, true, 2,ummisco.gama.traffgen.types.Vehicle.class, false, 2,0,0,AI));
_field(ummisco.gama.traffgen.types.Vehicle.class,"the height of the vehicle",new OperatorProto("height", null, new GamaHelper(){ @Override public Double run(IScope scope, Object... v){return (v==null||v.length==0)? 0d:((ummisco.gama.traffgen.types.Vehicle) v[0]).getVehicleHeight();}}, false, true, 2,ummisco.gama.traffgen.types.Vehicle.class, false, 2,0,0,AI));
_field(ummisco.gama.traffgen.types.Vehicle.class,"the initial speed of the vehicle",new OperatorProto("speed", null, new GamaHelper(){ @Override public Double run(IScope scope, Object... v){return (v==null||v.length==0)? 0d:((ummisco.gama.traffgen.types.Vehicle) v[0]).getInitialSpeed();}}, false, true, 2,ummisco.gama.traffgen.types.Vehicle.class, false, 2,0,0,AI));
_field(ummisco.gama.traffgen.types.Vehicle.class,"the arrival time to the network",new OperatorProto("arrival", null, new GamaHelper(){ @Override public GamaDate run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.Vehicle) v[0]).getArrivalTime();}}, false, true, 23,ummisco.gama.traffgen.types.Vehicle.class, false, 23,0,0,AI));
_field(ummisco.gama.traffgen.types.Vehicle.class,"is the vehicle launched",new OperatorProto("launched", null, new GamaHelper(){ @Override public Boolean run(IScope scope, Object... v){return (v==null||v.length==0)? false:((ummisco.gama.traffgen.types.Vehicle) v[0]).getLaunched();}}, false, true, 3,ummisco.gama.traffgen.types.Vehicle.class, false, 3,0,0,AI));
_field(ummisco.gama.traffgen.types.Vehicle.class,"the initial position of the vehicle",new OperatorProto("position", null, new GamaHelper(){ @Override public GamaPoint run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.Vehicle) v[0]).getPosition();}}, false, true, 7,ummisco.gama.traffgen.types.Vehicle.class, false, 7,0,0,AI));
_field(ummisco.gama.traffgen.types.Vehicle.class,"the headway of the vehicle",new OperatorProto("headway", null, new GamaHelper(){ @Override public Double run(IScope scope, Object... v){return (v==null||v.length==0)? 0d:((ummisco.gama.traffgen.types.Vehicle) v[0]).getTimeHeadway();}}, false, true, 2,ummisco.gama.traffgen.types.Vehicle.class, false, 2,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"the time interval on which this generation will occur",new OperatorProto("timeInterval", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getTimeInterval();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"the coordinates on which the generation will occur, mapped with fraction of each coordinate",new OperatorProto("coordinates", null, new GamaHelper(){ @Override public Map run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getCoordinates();}}, false, true, 10,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 10,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"the choice of changing between corrdinates",new OperatorProto("coordinate_choice", null, new GamaHelper(){ @Override public String run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getCoordinatesChoice();}}, false, true, 4,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 4,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"the transition type",new OperatorProto("transitionType", null, new GamaHelper(){ @Override public String run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getTransitionType();}}, false, true, 4,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 4,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"The vehicle Types generated by this generator",new OperatorProto("vehicleTypes", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getVehicleTypes();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"The transition Matrix",new OperatorProto("transition", null, new GamaHelper(){ @Override public IMatrix run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getTransition();}}, false, true, 8,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 8,2,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"the speed metadata",new OperatorProto("speed", null, new GamaHelper(){ @Override public ummisco.gama.traffgen.types.SpeedGenerator run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getSpeed();}}, false, true, 96,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 96,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"the list of timeHeadway metadata",new OperatorProto("timeHeadway", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getTimeHeadway();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGenerator.class,"list of vehicle type to generate",new OperatorProto("vehicleList", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGenerator) v[0]).getVehicleList();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGenerator.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGeneratorX.class,"list of vehicle type to generate",new OperatorProto("vehicleList", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGeneratorX) v[0]).getVehicleList();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGeneratorX.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGeneratorX.class,"list of speed of each vehicle in vehicle List",new OperatorProto("speedList", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGeneratorX) v[0]).getSpeedList();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGeneratorX.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGeneratorX.class,"List of time Headway of each vehicle in list",new OperatorProto("timeHeadwayList", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGeneratorX) v[0]).getTimeHeadwayList();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGeneratorX.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGeneratorX.class,"list of preriod number based on the sequence of generation",new OperatorProto("periodList", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGeneratorX) v[0]).getPeriodList();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGeneratorX.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleGeneratorX.class,"list of number of vehicles for each period in the generation",new OperatorProto("vehicleNumber", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleGeneratorX) v[0]).getVehicleNumber();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleGeneratorX.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleTHGenerator.class,"the vehicleTypes on which this generator is applied to",new OperatorProto("vehicleTypes", null, new GamaHelper(){ @Override public IList run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleTHGenerator) v[0]).getVehicleTypes();}}, false, true, 5,ummisco.gama.traffgen.types.VehicleTHGenerator.class, false, 5,0,0,AI));
_field(ummisco.gama.traffgen.types.VehicleTHGenerator.class,"the headway  generation model for each",new OperatorProto("headwayGenerationModel", null, new GamaHelper(){ @Override public Map run(IScope scope, Object... v){return (v==null||v.length==0)? null:((ummisco.gama.traffgen.types.VehicleTHGenerator) v[0]).getHeadwayGenerationModel();}}, false, true, 10,ummisco.gama.traffgen.types.VehicleTHGenerator.class, false, 10,0,0,AI));
}public void initializeOperator() throws SecurityException, NoSuchMethodException {
_operator(S("dummy"),ummisco.gama.traffgen.operators.TraffGen.class.getMethod("createDummy", IScope.class,S,D),C(S,D),AI,ummisco.gama.traffgen.types.Dummy.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.Dummy run(IScope s,Object... o){return ummisco.gama.traffgen.operators.TraffGen.createDummy(s,((String)o[0]),asFloat(s,o[1]));}});
_operator(S("period_generator"),ummisco.gama.traffgen.operators.TraffGen.class.getMethod("createPeriodTHGenerator", IScope.class,int.class,LI,int.class,Map.class),C(int.class,LI,int.class,Map.class),AI,ummisco.gama.traffgen.types.PeriodHeadwayGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.PeriodHeadwayGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.operators.TraffGen.createPeriodTHGenerator(s,asInt(s,o[0]),((IList)o[1]),asInt(s,o[2]),((Map)o[3]));}});
_operator(S("speed_generator"),ummisco.gama.traffgen.operators.TraffGen.class.getMethod("createSpeed", IScope.class,Map.class),C(Map.class),AI,ummisco.gama.traffgen.types.SpeedGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.SpeedGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.operators.TraffGen.createSpeed(s,((Map)o[0]));}});
_operator(S("atomic_traffgen"),ummisco.gama.traffgen.types.TrafficGenerator.class.getMethod("createAtomicGenerator", IScope.class,LI,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class),C(LI,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class),AI,ummisco.gama.traffgen.types.TrafficGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficGenerator.createAtomicGenerator(s,((IList)o[0]),((ummisco.gama.traffgen.types.TrafficLaw)o[1]),((ummisco.gama.traffgen.types.TrafficLaw)o[2]));}});
_operator(S("atomic_traffgen"),ummisco.gama.traffgen.types.TrafficGenerator.class.getMethod("createAtomicGenerator", IScope.class,LI,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,IS),C(LI,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,IS),AI,ummisco.gama.traffgen.types.TrafficGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficGenerator.createAtomicGenerator(s,((IList)o[0]),((ummisco.gama.traffgen.types.TrafficLaw)o[1]),((ummisco.gama.traffgen.types.TrafficLaw)o[2]),((IShape)o[3]));}});
_operator(S("atomic_traffgen"),ummisco.gama.traffgen.types.TrafficGenerator.class.getMethod("createAtomicGenerator", IScope.class,GamlSpecies.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class),C(GamlSpecies.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class),AI,ummisco.gama.traffgen.types.TrafficGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficGenerator.createAtomicGenerator(s,((GamlSpecies)o[0]),((ummisco.gama.traffgen.types.TrafficLaw)o[1]),((ummisco.gama.traffgen.types.TrafficLaw)o[2]));}});
_operator(S("atomic_traffgen"),ummisco.gama.traffgen.types.TrafficGenerator.class.getMethod("createAtomicGenerator", IScope.class,GamlSpecies.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,IS),C(GamlSpecies.class,ummisco.gama.traffgen.types.TrafficLaw.class,ummisco.gama.traffgen.types.TrafficLaw.class,IS),AI,ummisco.gama.traffgen.types.TrafficGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficGenerator.createAtomicGenerator(s,((GamlSpecies)o[0]),((ummisco.gama.traffgen.types.TrafficLaw)o[1]),((ummisco.gama.traffgen.types.TrafficLaw)o[2]),((IShape)o[3]));}});
_operator(S("map_traffgen"),ummisco.gama.traffgen.types.TrafficGenerator.class.getMethod("createMapTrafficGenerator", IScope.class,LI,GM),C(LI,GM),AI,ummisco.gama.traffgen.types.TrafficGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficGenerator.createMapTrafficGenerator(s,((IList)o[0]),((GamaMap)o[1]));}});
_operator(S("synchronized_traffgen"),ummisco.gama.traffgen.types.TrafficGenerator.class.getMethod("createSynchronizeTrafficGenerator", IScope.class,LI,LI),C(LI,LI),AI,ummisco.gama.traffgen.types.TrafficGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficGenerator.createSynchronizeTrafficGenerator(s,((IList)o[0]),((IList)o[1]));}});
_operator(S("synchronized_traffgen"),ummisco.gama.traffgen.types.TrafficGenerator.class.getMethod("createSynchronizeTrafficGenerator", IScope.class,LI,IM),C(LI,IM),AI,ummisco.gama.traffgen.types.TrafficGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficGenerator.createSynchronizeTrafficGenerator(s,((IList)o[0]),((IMatrix)o[1]));}});
_operator(S("exponential_law"),ummisco.gama.traffgen.types.TrafficLaw.class.getMethod("createExponential", IScope.class,D),C(D),AI,ummisco.gama.traffgen.types.TrafficLaw.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficLaw run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficLaw.createExponential(s,asFloat(s,o[0]));}});
_operator(S("pareto_3_law"),ummisco.gama.traffgen.types.TrafficLaw.class.getMethod("createPareto3", IScope.class,D,D,D),C(D,D,D),AI,ummisco.gama.traffgen.types.TrafficLaw.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficLaw run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficLaw.createPareto3(s,asFloat(s,o[0]),asFloat(s,o[1]),asFloat(s,o[2]));}});
_operator(S("pareto_4_law"),ummisco.gama.traffgen.types.TrafficLaw.class.getMethod("createPareto4", IScope.class,D,D,D,D),C(D,D,D,D),AI,ummisco.gama.traffgen.types.TrafficLaw.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficLaw run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficLaw.createPareto4(s,asFloat(s,o[0]),asFloat(s,o[1]),asFloat(s,o[2]),asFloat(s,o[3]));}});
_operator(S("pearson_5_law"),ummisco.gama.traffgen.types.TrafficLaw.class.getMethod("createPearson5", IScope.class,D,D,D),C(D,D,D),AI,ummisco.gama.traffgen.types.TrafficLaw.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficLaw run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficLaw.createPearson5(s,asFloat(s,o[0]),asFloat(s,o[1]),asFloat(s,o[2]));}});
_operator(S("poisson_law"),ummisco.gama.traffgen.types.TrafficLaw.class.getMethod("createPoisson", IScope.class,D),C(D),AI,ummisco.gama.traffgen.types.TrafficLaw.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficLaw run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficLaw.createPoisson(s,asFloat(s,o[0]));}});
_operator(S("create_period"),ummisco.gama.traffgen.types.TrafficPeriod.class.getMethod("createTrafficPeriod", IScope.class,ummisco.gama.traffgen.types.TrafficGenerator.class,D,I),C(ummisco.gama.traffgen.types.TrafficGenerator.class,D,I),AI,ummisco.gama.traffgen.types.TrafficPeriod.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficPeriod run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficPeriod.createTrafficPeriod(s,((ummisco.gama.traffgen.types.TrafficGenerator)o[0]),asFloat(s,o[1]),asInt(s,o[2]));}});
_operator(S("create_period"),ummisco.gama.traffgen.types.TrafficPeriod.class.getMethod("createTrafficPeriod", IScope.class,ummisco.gama.traffgen.types.TrafficGenerator.class,D,ummisco.gama.traffgen.types.TrafficLaw.class),C(ummisco.gama.traffgen.types.TrafficGenerator.class,D,ummisco.gama.traffgen.types.TrafficLaw.class),AI,ummisco.gama.traffgen.types.TrafficPeriod.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.TrafficPeriod run(IScope s,Object... o){return ummisco.gama.traffgen.types.TrafficPeriod.createTrafficPeriod(s,((ummisco.gama.traffgen.types.TrafficGenerator)o[0]),asFloat(s,o[1]),((ummisco.gama.traffgen.types.TrafficLaw)o[2]));}});
_operator(S("vehicle_generator"),ummisco.gama.traffgen.operators.TraffGen.class.getMethod("createGenerator", IScope.class,LI,S,IM,LI,LI,ummisco.gama.traffgen.types.SpeedGenerator.class,Map.class,S),C(LI,S,IM,LI,LI,ummisco.gama.traffgen.types.SpeedGenerator.class,Map.class,S),AI,ummisco.gama.traffgen.types.VehicleGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.VehicleGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.operators.TraffGen.createGenerator(s,((IList)o[0]),((String)o[1]),((IMatrix)o[2]),((IList)o[3]),((IList)o[4]),((ummisco.gama.traffgen.types.SpeedGenerator)o[5]),((Map)o[6]),((String)o[7]));}});
_operator(S("generation_model"),ummisco.gama.traffgen.operators.TraffGen.class.getMethod("generationModel", IScope.class,LI,Map.class),C(LI,Map.class),AI,ummisco.gama.traffgen.types.VehicleTHGenerator.class,false,-13,-13,-13,-13,new GamaHelper(){ @Override public ummisco.gama.traffgen.types.VehicleTHGenerator run(IScope s,Object... o){return ummisco.gama.traffgen.operators.TraffGen.generationModel(s,((IList)o[0]),((Map)o[1]));}});
}public void initializeFile() throws SecurityException, NoSuchMethodException {
}public void initializeAction() throws SecurityException, NoSuchMethodException {
_action("initVehicle",ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class,new GamaHelper(T(LI), ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class){ @Override public IList run(IScope s, IAgent a, IVarAndActionSupport t, Object... v){ return ((ummisco.gama.traffgen.skills.VehicleGeneratorSkill) t).initVehicle(s); } },desc(PRIMITIVE, null, new ChildrenProvider(Arrays.asList()), NAME, "initVehicle",TYPE, Ti(LI), VIRTUAL,FALSE),ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class.getMethod("initVehicle", IScope.class));
_action("transform",ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class,new GamaHelper(T(IM), ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class){ @Override public IMatrix run(IScope s, IAgent a, IVarAndActionSupport t, Object... v){ return ((ummisco.gama.traffgen.skills.VehicleGeneratorSkill) t).transform(s); } },desc(PRIMITIVE, null, new ChildrenProvider(Arrays.asList(desc(ARG,NAME,"list", TYPE, "5", "optional", FALSE),desc(ARG,NAME,"method", TYPE, "4", "optional", TRUE))), NAME, "transform",TYPE, Ti(IM), VIRTUAL,FALSE),ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class.getMethod("transform", IScope.class));
_action("getVehiculeList",ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class,new GamaHelper(T(void.class), ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class){ @Override public Object run(IScope s, IAgent a, IVarAndActionSupport t, Object... v){  ((ummisco.gama.traffgen.skills.VehicleGeneratorSkill) t).getVehiculeList(s); return null;} },desc(PRIMITIVE, null, new ChildrenProvider(Arrays.asList(desc(ARG,NAME,TYPE, TYPE, "4", "optional", TRUE))), NAME, "generateVehicles",TYPE, Ti(void.class), VIRTUAL,FALSE),ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class.getMethod("getVehiculeList", IScope.class));
_action("launchVehicles",ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class,new GamaHelper(T(void.class), ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class){ @Override public Object run(IScope s, IAgent a, IVarAndActionSupport t, Object... v){  ((ummisco.gama.traffgen.skills.VehicleGeneratorSkill) t).launchVehicles(s); return null;} },desc(PRIMITIVE, null, new ChildrenProvider(Arrays.asList()), NAME, "launchVehicles",TYPE, Ti(void.class), VIRTUAL,FALSE),ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class.getMethod("launchVehicles", IScope.class));
}public void initializeSkill()  {
_skill("vehicleGen",ummisco.gama.traffgen.skills.VehicleGeneratorSkill.class,AS);
}public void initializeDisplay()  {
}public void initializeExperiment()  {
}public void initializeConstant()  {
}public void initializeTests()  {
}
}