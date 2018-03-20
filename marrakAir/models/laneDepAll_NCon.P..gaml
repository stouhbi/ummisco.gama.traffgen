/**
* Name: newGen
* Author: saadtouhbi
* Description:
* Tags: traffic generation
*/

model newGen

/* Insert your model definition here */

global{

	date starting_date <- date([2017,4,12,8,0,0]);
	float step <- 50 #msec;
	file shape_file_roads <- file("../includes/SIG_simu/roads_gama.shp");
	file shape_file_buildings <- file("../includes/SIG_simu/buildings_gama.shp");
	file shape_file_bound <- file("../includes/SIG_simu/reperes.shp");
	graph the_graph;
	geometry shape <- envelope(shape_file_bound);

	// Generation Creation

	//traffgen_law headway1 <-  pareto_4_law(0.18, 0.19, 1.787, -0.198); // 2-4
	//traffgen_law headway1 <-  pareto_4_law(0.5977091, 0.5129527, 2.313488, 0.197999954); // 2-4
	//traffgen_law headway1 <-  pareto_4_law(1.29, 0.497, 2.997, 0.015); // 10-14 Casa c faux. can't mix two roads. phase length
	//traffgen_law headway1 <-  pareto_4_law(1.071276, 0.4240384, 2.395897, -0.009000063); // 10-14 all data
	//traffgen_law headway1 <-  pareto_4_law(0.3282351, 0.2914587, 2.040083, -0.198); // 2-4 all data
	//traffgen_law headway1 <-  pareto_4_law(1.071, 0.424, 2.396, -0.009); // 10-14
	//traffgen_law headway1 <-  inverse_gamma_law(3.467,1.0/1.306); // 2-4 all data
	//traffgen_law headway1 <-  gamma_law(1.1951304,0.1825619); // 2-4 all data
	//traffgen_law headway2 <-  pareto_4_law(0.672, 0.367, 2.189, -0.09);  // 5-9
	//traffgen_law headway2 <-  pareto_4_law(1.557670, 0.4599637, 2.645302, -0.01399994); // 10-14 all data
	//traffgen_law headway2 <-  pareto_4_law(1.436, 0.444, 2.46, 0.014); // 10-14 
	//traffgen_law headway2 <-  pareto_4_law(0.7208561, 0.3819021, 2.263117, -0.06309249); // 5-9
	//traffgen_law speed <- poisson_law(20);
	//geometry locat <- square(2) at :{4652.572627816505, 2453.786642591314, 0};
	
	//traffgen_law headway1 <-  pareto_3_law(0.412,2.327,-0.043); // 10-14
	//traffgen_law headway1 <-  pearson_6_law(9.471, 5.947, 1.53, -0.54); // 15-19 phase 1
	//traffgen_law headway1 <-  pearson_5_law(5.23, 14.473, -1.012); // 15-19 phase 1
	
	//traffgen_law headway1 <-  shifted_lognormal_law(0.99, 0.787, 0.045); // 5-9 phase 2 M5
	//traffgen_gen gen1 <- atomic_traffgen([species_of(car),species_of(bus), species_of(moto)], headway1, speed, {4652.572627816505, 2453.786642591314, 0});
	//traffgen_gen gen2 <- atomic_traffgen(species_of(bus), headway1, speed, {4652.572627816505, 2453.786642591314, 0});
	//traffgen_gen gen3 <- atomic_traffgen(species_of(moto), headway1, speed, {4652.572627816505, 2453.786642591314, 0});
	//map typeTran <- [species_of(car)::0.720608575, species_of(bus)::0.002963841, species_of(moto)::0.276427583];
	//traffgen_gen typeGen <- map_traffgen([gen1], typeTran);   
	

	//traffgen_law countLaw <- poisson_law(29.0232558);
	//traffgen_gen gen4 <- atomic_traffgen([species_of(car),species_of(bus), species_of(moto)], headway2, speed, {4652.572627816505, 2453.786642591314, 0});
	//traffgen_gen gen5 <- atomic_traffgen(species_of(bus), headway2, speed,  {4652.572627816505, 2453.786642591314, 0});
	//traffgen_gen gen6 <- atomic_traffgen(species_of(moto), headway2, speed,  {4652.572627816505, 2453.786642591314, 0});
	//traffgen_gen typeGen2 <- map_traffgen([gen4], copy(typeTran));
	//traffgen_period period1 <- create_period(typeGen, 48, 1390);
	//traffgen_period period2 <- create_period(typeGen2, 37, 3000);

	//traffgen_scheduler schedule <- create_schedule([period1, period2], "cycle"); 
	
	//// Continuous generation scenarios 
	map typeTran <- [species_of(car)::0.720608575, species_of(bus)::0.002963841, species_of(moto)::0.276427583];
	traffgen_law speed <- poisson_law(20);
	traffgen_law count_2_4 <- uniform_law(2,4);
	traffgen_law count_5_9 <- uniform_law(5,9);
	traffgen_law count_10_14 <- uniform_law(10,14);
	traffgen_law count_15_19 <- uniform_law(15,19);
	
	map locTran <- [{4652.572627816505, 2453.786642591314, 0}::0.2243127, {4652.572627816505, 2553.786642591314, 0}::0.4574742, {4652.572627816505, 2653.786642591314, 0}::0.3182131];
	/***		Var3G.1 *****/
	traffgen_law headway_3G_11 <-  pareto_3_law(0.588, 2.883, 0); // 5-9
	traffgen_law headway_3G_12 <-  pareto_4_law(0.111, 0.093, 1.611, -0.497); // 2-4
	
	traffgen_gen gen_3G_11 <- atomic_traffgen([species_of(car),species_of(bus), species_of(moto)], headway_3G_11, speed, count_5_9, 48);
	traffgen_gen gen_3G_12 <- atomic_traffgen([species_of(car),species_of(bus), species_of(moto)], headway_3G_12, speed, count_2_4, 37);
	traffgen_gen typeGen_3G_11 <- map_traffgen([gen_3G_11], typeTran);
	traffgen_gen typeGen_3G_12 <- map_traffgen([gen_3G_12], copy(typeTran));
	
	traffgen_gen locGen_3G_11 <- location_traffgen([typeGen_3G_11], locTran);
	traffgen_gen locGen_3G_12 <- location_traffgen([typeGen_3G_12], locTran);
	
	traffgen_period period_3G_11 <- create_period(locGen_3G_11, 48, 1390);
	traffgen_period period_3G_12 <- create_period(locGen_3G_12, 37, 3000);

	traffgen_scheduler schedule_3G_1 <- create_schedule([period_3G_11, period_3G_12], "cycle"); 
	
	/****         VAR3G.2  **********/
	traffgen_law headway_3G_21 <-   pareto_4_law(1.237, 0.568,2.366,-0.002); // 10-14 
	traffgen_law headway_3G_22 <-  pearson_3_law(2.688, 3.492, -0.326); // 15-19 
	
	traffgen_gen gen_3G_21 <- atomic_traffgen([species_of(car),species_of(bus), species_of(moto)], headway_3G_21, speed, count_10_14, 48);
	traffgen_gen gen_3G_22 <- atomic_traffgen([species_of(car),species_of(bus), species_of(moto)], headway_3G_22, speed, count_15_19, 37);
	traffgen_gen typeGen_3G_21 <- map_traffgen([gen_3G_21], typeTran);
	traffgen_gen typeGen_3G_22 <- map_traffgen([gen_3G_22], copy(typeTran));
	
	traffgen_gen locGen_3G_21 <- location_traffgen([typeGen_3G_21], locTran);
	traffgen_gen locGen_3G_22 <- location_traffgen([typeGen_3G_22], locTran);
	
	traffgen_period period_3G_21 <- create_period(locGen_3G_21, 48, 1390);
	traffgen_period period_3G_22 <- create_period(locGen_3G_22, 37, 3000);

	traffgen_scheduler schedule_3G_2 <- create_schedule([period_3G_21, period_3G_22], "cycle"); 
	

	
	
	
	
	reflex generate {
		vehicle truc <-  schedule_3G_1.next;
		if(truc!=nil){
			write "this vehicle width is" + truc.width + " height is "+ truc.height + " initial speed "+ truc.speed ;
			write  " arrival time "+ truc.activated_at;
			write "at location "+truc.location;
			save (string(truc.width) + ";" + string(truc.height) + ";" + string(truc.activated_at) + ";"  + string(truc.speed) + ";" + string(truc.tiv) + ";" + truc.location.x + ":" + truc.location.y + ":" + truc.location.z) to: "../includes/Var3NG.1.csv" type:"csv" rewrite:false;
			
		}
		
		truc <-  schedule_3G_2.next;
		if(truc!=nil){
			write "this vehicle width is" + truc.width + " height is "+ truc.height + " initial speed "+ truc.speed ;
			write  " arrival time "+ truc.activated_at;
			write "at location "+truc.location;
			save (string(truc.width) + ";" + string(truc.height) + ";" + string(truc.activated_at) + ";"  + string(truc.speed) + ";" + string(truc.tiv) + ";" + truc.location.x + ":" + truc.location.y + ":" + truc.location.z) to: "../includes/Var3NG.2.csv" type:"csv" rewrite:false;
			
		}
		
	}

}


species road {
    string type;
    rgb color;

   /* reflex generate {
    		write length(vehicles);
    		list indexes <-  self initVehicle();
    		if(length(indexes)>0 and length(vehicles)>0){
    			write "indexes" + length(indexes);
    			loop i from:0 to: length(indexes)-1 {
    				vehicle v <- vehicles[indexes[i]];
    				if(v.type = "Car"){
    					create car number:1 with:(location:copy(v.position), Speed:v.speed, launched:true);
    				}else if(v.type = "Bus"){
    					create bus number:1 with:(location:copy(v.position), Speed:v.speed, launched:true);
    				}else if(v.type = "camaio"){
    					create camaio number:1 with:(location:copy(v.position), Speed:v.speed, launched:true);
    				}

				write "launch vehicle" + v.type + " speed"+ v.speed + "position "+v.position;
			}
    		}

    }*/
	aspect base {
		draw shape color: color;
	}
}

species vehicle {
	float width;
	float height;
	float speed;
	float activated_at;
	float tiv;


}

species car  parent: vehicle skills:[moving]{

	float width <- 2.0;
	float height <- 5.0;
	bool launched <- false;
	float activationDate;
	float Speed;
	point previousLoc <- nil;
	point the_target <- {4372.338867595005, 2134.9091945041027, 0} ;

	reflex drive when: (launched=true and the_target !=nil){
		write "we are workiiiing "+location;
		previousLoc <- copy(location);
		do goto target: the_target on: the_graph speed: Speed;
	  	if the_target = location {
			the_target <- nil ;
	  	}
	}

	aspect base{
		draw rectangle(width*4, height*4) color: #blue;
	}
}

species bus  parent: vehicle skills:[moving]{

	float width <- 3.0;
	float height <- 8.0;
	bool launched <- false;
	float activationDate;
	float Speed;
	point previousLoc <- nil;
	point the_target <- {4372.338867595005, 2134.9091945041027, 0} ;

	reflex drive when: (launched=true and the_target !=nil){
		write "we are workiiiing "+location;
		previousLoc <- copy(location);
		do goto target: the_target on: the_graph speed: Speed ;
	  	if the_target = location {
			the_target <- nil ;
	  	}
	}

	aspect base{
		draw rectangle(width*4, height*4) color: #green;
	}
}

species moto  parent: vehicle skills:[moving] {
	float width <- 3.0;
	float height <- 7.0;
	bool launched <- false;
	float activationDate;
	float Speed;
	point previousLoc <- nil;
	point the_target <- {4372.338867595005, 2134.9091945041027, 0} ;

	reflex drive when: (launched=true and the_target !=nil){
		write "we are workiiiing "+location;
		previousLoc <- copy(location);
		do goto target: the_target on: the_graph speed: Speed ;
	  	if the_target = location {
			the_target <- nil ;
	  	}
	}

	aspect base{
		draw rectangle(width*4, height*4) color: #black;
	}
}

species people skills: [moving] {
	point target;
	path my_path;
	point source;
	aspect base {
		draw rectangle(10,10) color: #green;
	}

	reflex movement {
		do goto target:target on:the_graph;
	}

}

species building {
	string type;
	rgb color <- rgb(220,220,220);
	int height;
	aspect base {
		draw shape color: color depth: height;
	}
}


experiment GIS_agentification type: gui {
	output {
		display city_display type:opengl{
		//	image '../includes/SIG_simu/background.png' refresh: false;
			species building aspect:base;
			species road aspect:base;
			species people aspect:base;

			species car aspect:base;
			species bus aspect: base;
			species moto aspect: base;
		}
	}
}
