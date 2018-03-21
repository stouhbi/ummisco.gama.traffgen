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

	
	
	//// Periodicity
	//map typeTran <- [species_of(car)::0.720608575, species_of(bus)::0.002963841, species_of(moto)::0.276427583];
	matrix typeTran <- matrix([[0.3666905, 0.2429616], [0.6333095, 0.7570384]]); // 2 roues, 4 roues
	map locTran <- [{4652.572627816505, 2453.786642591314, 0}::0.2243127, {4652.572627816505, 2553.786642591314, 0}::0.4574742, {4652.572627816505, 2653.786642591314, 0}::0.3182131];
	traffgen_law speed <- poisson_law(20);
	traffgen_law count_5_9 <- uniform_law(5,9);
	traffgen_law count_10_14 <- uniform_law(10,14);
	traffgen_law count_15_19 <- uniform_law(15,19);
	
	// Contnuous
	
	// 2 roues
	traffgen_law headway_VLG_11 <-  pearson_5_law(2.736, 6.24, -0.723); // phase1 10-14
	traffgen_law headway_VLG_12 <-  pareto_4_law(0.799, 0.456, 2.017, -0.02); // phase 2 5-9
	
	traffgen_gen gen_VLG_11 <- atomic_traffgen([species_of(moto)], headway_VLG_11, speed);
	traffgen_gen gen_VLG_12 <- atomic_traffgen([species_of(moto)], headway_VLG_12, speed);
	
	// 4 roues 
	traffgen_law headway_VLG_13 <-  pareto_3_law(0.588, 2.883, 0); // phase 1 5-9
	traffgen_law headway_VLG_14 <-  pearson_5_law(2.688, 3.492, -0.326); // phase 2 15-19
	
	
	traffgen_gen gen_VLG_13 <- atomic_traffgen([species_of(car)], headway_VLG_13, speed);
	traffgen_gen gen_VLG_14 <- atomic_traffgen([species_of(car)], headway_VLG_14, speed);
	
	
	
	traffgen_gen typeGen_VLG_11 <- synchronized_traffgen([gen_VLG_11, gen_VLG_13], typeTran);
	traffgen_gen typeGen_VLG_12 <- synchronized_traffgen([gen_VLG_12, gen_VLG_14], copy(typeTran));
	
	traffgen_gen locGen_VLG_11 <- location_traffgen([typeGen_VLG_11], locTran);
	traffgen_gen locGen_VLG_12 <- location_traffgen([typeGen_VLG_12], locTran);
	
	traffgen_period period_VLG_11 <- create_period(locGen_VLG_11, 48, 1390);
	traffgen_period period_VLG_12 <- create_period(locGen_VLG_12, 37, 3000);

	traffgen_scheduler schedule_VLG_1 <- create_schedule([period_VLG_11, period_VLG_12], "cycle"); 
	
	// Discret
	
	// 2 roues 
	traffgen_gen gen_VLNG_11 <- atomic_traffgen([species_of(moto)], headway_VLG_11, speed, count_10_14, 48);
	traffgen_gen gen_VLNG_12 <- atomic_traffgen([species_of(moto)], headway_VLG_12, speed, count_5_9, 37);
	
	// 4 roues
	traffgen_gen gen_VLNG_13 <- atomic_traffgen([species_of(car)], headway_VLG_13, speed, count_5_9, 48);
	traffgen_gen gen_VLNG_14 <- atomic_traffgen([species_of(car)], headway_VLG_14, speed, count_15_19, 37);
	
	traffgen_gen typeGen_VLNG_11 <- synchronized_traffgen([gen_VLNG_11, gen_VLNG_13], typeTran);
	traffgen_gen typeGen_VLNG_12 <- synchronized_traffgen([gen_VLNG_12, gen_VLNG_14], copy(typeTran));
	
	traffgen_gen locGen_VLNG_11 <- location_traffgen([typeGen_VLNG_11], locTran);
	traffgen_gen locGen_VLNG_12 <- location_traffgen([typeGen_VLNG_12], locTran);
	
	traffgen_period period_VLNG_11 <- create_period(locGen_VLNG_11, 48, 1390);
	traffgen_period period_VLNG_12 <- create_period(locGen_VLNG_12, 37, 3000);

	traffgen_scheduler schedule_VLNG_1 <- create_schedule([period_VLNG_11, period_VLNG_12], "cycle");
	
	
	
	// No period
	
	
	// contnuous
	
	// 2 roues 
	traffgen_gen gen_VLPG_11 <- atomic_traffgen([species_of(moto)], headway_VLG_11, speed);
	
	// 4 roues
	traffgen_gen gen_VLPG_13 <- atomic_traffgen([species_of(car)], headway_VLG_13, speed);
	
	traffgen_gen typeGen_VLPG_11 <- synchronized_traffgen([gen_VLPG_11, gen_VLPG_13], typeTran);
	
	
	traffgen_gen locGen_VLPG_11 <- location_traffgen([typeGen_VLPG_11], locTran);
	
	traffgen_period period_VLPG_11 <- create_period(locGen_VLPG_11, 360000, 1390);

	traffgen_scheduler schedule_VLPG_1 <- create_schedule([period_VLPG_11], "sequence");
	
	// Discret
	
	// 2 roues 
	traffgen_gen gen_VLNPG_11 <- atomic_traffgen([species_of(moto)], headway_VLG_11, speed, count_10_14, 48);
	
	// 4 roues
	traffgen_gen gen_VLNPG_13 <- atomic_traffgen([species_of(car)], headway_VLG_13, speed, count_5_9, 48);
	
	traffgen_gen typeGen_VLNPG_11 <- synchronized_traffgen([gen_VLNPG_11, gen_VLNPG_13], typeTran);
	
	
	traffgen_gen locGen_VLNPG_11 <- location_traffgen([typeGen_VLNPG_11], locTran);
	
	traffgen_period period_VLNPG_11 <- create_period(locGen_VLNPG_11, 360000, 1390);

	traffgen_scheduler schedule_VLNPG_1 <- create_schedule([period_VLNPG_11], "sequence");
	
	
	reflex generate {
		vehicle truc <-  schedule_VLG_1.next;
		if(truc!=nil){
			write "this vehicle width is" + truc.width + " height is "+ truc.height + " initial speed "+ truc.speed ;
			write  " arrival time "+ truc.activated_at;
			write "at location "+truc.location;
			save (string(truc.width) + ";" + string(truc.height) + ";" + string(truc.activated_at) + ";"  + string(truc.speed) + ";" + string(truc.tiv) + ";" + truc.location.x + ":" + truc.location.y + ":" + truc.location.z) to: "../includes/VarVLG.1M.csv" type:"csv" rewrite:false;
			
		}
		
		truc <-  schedule_VLNG_1.next;
		if(truc!=nil){
			write "this vehicle width is" + truc.width + " height is "+ truc.height + " initial speed "+ truc.speed ;
			write  " arrival time "+ truc.activated_at;
			write "at location "+truc.location;
			save (string(truc.width) + ";" + string(truc.height) + ";" + string(truc.activated_at) + ";"  + string(truc.speed) + ";" + string(truc.tiv) + ";" + truc.location.x + ":" + truc.location.y + ":" + truc.location.z) to: "../includes/VarVLNG.1M.csv" type:"csv" rewrite:false;
			
		}
		
		truc <-  schedule_VLPG_1.next;
		if(truc!=nil){
			write "this vehicle width is" + truc.width + " height is "+ truc.height + " initial speed "+ truc.speed ;
			write  " arrival time "+ truc.activated_at;
			write "at location "+truc.location;
			save (string(truc.width) + ";" + string(truc.height) + ";" + string(truc.activated_at) + ";"  + string(truc.speed) + ";" + string(truc.tiv) + ";" + truc.location.x + ":" + truc.location.y + ":" + truc.location.z) to: "../includes/VarVLPG.1M.csv" type:"csv" rewrite:false;
			
		}
		
		truc <-  schedule_VLNPG_1.next;
		if(truc!=nil){
			write "this vehicle width is" + truc.width + " height is "+ truc.height + " initial speed "+ truc.speed ;
			write  " arrival time "+ truc.activated_at;
			write "at location "+truc.location;
			save (string(truc.width) + ";" + string(truc.height) + ";" + string(truc.activated_at) + ";"  + string(truc.speed) + ";" + string(truc.tiv) + ";" + truc.location.x + ":" + truc.location.y + ":" + truc.location.z) to: "../includes/VarVLNPG.1M.csv" type:"csv" rewrite:false;
			
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
