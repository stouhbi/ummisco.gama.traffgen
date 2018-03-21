/**
* Name: noPeriod
* Author: saadtouhbi
* Description: 
* Tags: Tag1, Tag2, TagN
*/

model noPeriod



/* Insert your model definition here */

global{

	date starting_date <- date([2017,4,12,8,0,0]);
	float step <- 50 #msec;
	file shape_file_roads <- file("../includes/SIG_simu/roads_gama.shp");
	file shape_file_buildings <- file("../includes/SIG_simu/buildings_gama.shp");
	file shape_file_bound <- file("../includes/SIG_simu/reperes.shp");
	graph the_graph;
	geometry shape <- envelope(shape_file_bound);

	
	
	
	
	traffgen_law speed <- poisson_law(20);
	traffgen_law count_2_4 <- uniform_law(2,4);
	traffgen_law count_5_9 <- uniform_law(5,9);
	traffgen_law count_10_14 <- uniform_law(10,14);
	traffgen_law count_15_19 <- uniform_law(15,19);
	
	
	/* PERCENTAGE TRANSITION */
	
	// continuus, matrix transition vehicle type dependent
	list typeTranP <- [0.2793914, 0.720608575]; // 2 roues, 4 roues
	
	/***		VarPVG.1   *****/
	// 2 roues
	traffgen_law headway_PVG_11 <-  lognormal_law(0.966, 1.049); // 5-9
	
	// 4 roues
	traffgen_law headway_PVG_13 <-  pareto_4_law(0.684, 0.32, 1.923, -0.009); // 10-14
	// 2 roues
	traffgen_gen gen_PVG_11 <- atomic_traffgen(species_of(moto), headway_PVG_11, speed, {4652.572627816505, 2453.786642591314, 0});
	
	// 4 roues
	traffgen_gen gen_PVG_13 <- atomic_traffgen(species_of(car), headway_PVG_13, speed, {4652.572627816505, 2453.786642591314, 0});
	
	
	traffgen_gen typeGen_PVG_1 <- synchronized_traffgen([gen_PVG_11, gen_PVG_13], typeTranP);
	
	traffgen_period period_PVG_1 <- create_period(typeGen_PVG_1, 36000, 1390);

	traffgen_scheduler schedule_PVG_1 <- create_schedule([period_PVG_1], "sequence"); 
	
	// discret, matrix transition vehicle type dependent
	/***		VarPVNG.1   *****/
	// 2 roues
	traffgen_law headway_PVNG_11 <-  lognormal_law(0.966, 1.049); // 5-9
	
	// 4 roues
	traffgen_law headway_PVNG_13 <-  pareto_4_law(0.684, 0.32, 1.923, -0.009); // 10-14
	// 2 roues
	traffgen_gen gen_PVNG_11 <- atomic_traffgen(species_of(moto), headway_PVNG_11, speed, count_5_9, 48, {4652.572627816505, 2453.786642591314, 0});
	
	// 4 roues
	traffgen_gen gen_PVNG_13 <- atomic_traffgen(species_of(car), headway_PVNG_13, speed, count_10_14, 48, {4652.572627816505, 2453.786642591314, 0});
	
	
	traffgen_gen typeGen_PVNG_1 <- synchronized_traffgen([gen_PVNG_11, gen_PVNG_13], typeTranP);
	
	traffgen_period period_PVNG_1 <- create_period(typeGen_PVNG_1, 36000, 1390);

	traffgen_scheduler schedule_PVNG_1 <- create_schedule([period_PVNG_1], "sequence"); 
	
	/* MATRIX TRANSITION */
	// continuus, matrix transition vehicle type dependent
	matrix typeTranM <- matrix([[0.3666905, 0.2429616], [0.6333095, 0.7570384]]); // 2 roues, 4 roues
	
	/***		VarVG.1   *****/
	// 2 roues
	traffgen_law headway_VG_11 <-  lognormal_law(0.966, 1.049); // 5-9
	
	// 4 roues
	traffgen_law headway_VG_13 <-  pareto_4_law(0.684, 0.32, 1.923, -0.009); // 10-14
	// 2 roues
	traffgen_gen gen_VG_11 <- atomic_traffgen(species_of(moto), headway_VG_11, speed, {4652.572627816505, 2453.786642591314, 0});
	
	// 4 roues
	traffgen_gen gen_VG_13 <- atomic_traffgen(species_of(car), headway_VG_13, speed, {4652.572627816505, 2453.786642591314, 0});
	
	
	traffgen_gen typeGen_VG_1 <- synchronized_traffgen([gen_VG_11, gen_VG_13], typeTranM);
	
	traffgen_period period_VG_1 <- create_period(typeGen_VG_1, 36000, 1390);

	traffgen_scheduler schedule_VG_1 <- create_schedule([period_VG_1], "sequence"); 
	
	// discret, matrix transition vehicle type dependent
	/***		VarVNG.1   *****/
	// 2 roues
	traffgen_law headway_VNG_11 <-  lognormal_law(0.966, 1.049); // 5-9
	
	// 4 roues
	traffgen_law headway_VNG_13 <-  pareto_4_law(0.684, 0.32, 1.923, -0.009); // 10-14
	// 2 roues
	traffgen_gen gen_VNG_11 <- atomic_traffgen(species_of(moto), headway_VNG_11, speed, count_5_9, 48, {4652.572627816505, 2453.786642591314, 0});
	
	// 4 roues
	traffgen_gen gen_VNG_13 <- atomic_traffgen(species_of(car), headway_VNG_13, speed, count_10_14, 48, {4652.572627816505, 2453.786642591314, 0});
	
	
	traffgen_gen typeGen_VNG_1 <- synchronized_traffgen([gen_VNG_11, gen_VNG_13], typeTranM);
	
	traffgen_period period_VNG_1 <- create_period(typeGen_VNG_1, 36000, 1390);

	traffgen_scheduler schedule_VNG_1 <- create_schedule([period_VNG_1], "sequence"); 
	
	
	/** LANE DEPENDENT */
	
	
	map typeTranMap <- [species_of(car)::0.720608575, species_of(bus)::0.002963841, species_of(moto)::0.276427583];
	map locTran <- [{4652.572627816505, 2453.786642591314, 0}::0.2243127, {4652.572627816505, 2553.786642591314, 0}::0.4574742, {4652.572627816505, 2653.786642591314, 0}::0.3182131];
	
	// Continuous
	traffgen_law headway_3G_11 <-  pareto_3_law(0.588, 2.883, 0); // 5-9
	
	traffgen_gen gen_3G_11 <- atomic_traffgen([species_of(car),species_of(bus), species_of(moto)], headway_3G_11, speed);
	traffgen_gen typeGen_3G_11 <- map_traffgen([gen_3G_11], typeTranMap);
	
	traffgen_gen locGen_3G_11 <- location_traffgen([typeGen_3G_11], locTran);
	
	traffgen_period period_3G_11 <- create_period(locGen_3G_11, 36000, 1390);

	traffgen_scheduler schedule_3G_1 <- create_schedule([period_3G_11], "sequence"); 
	
	
	// Non Continuous
	traffgen_law headway_3NG_11 <-  pareto_3_law(0.588, 2.883, 0); // 5-9
	
	traffgen_gen gen_3NG_11 <- atomic_traffgen([species_of(car),species_of(bus), species_of(moto)], headway_3NG_11, speed, count_5_9, 48);
	traffgen_gen typeGen_3NG_11 <- map_traffgen([gen_3NG_11], typeTranMap);
	
	traffgen_gen locGen_3NG_11 <- location_traffgen([typeGen_3NG_11], locTran);
	
	traffgen_period period_3NG_11 <- create_period(locGen_3NG_11, 36000, 1390);

	traffgen_scheduler schedule_3NG_1 <- create_schedule([period_3NG_11], "sequence"); 
	
	
	/** LANE INDEPENDENT */
	
	// Continuous 
	traffgen_law headway_CG_11 <-  pareto_4_law(0.5977091, 0.5129527, 2.313488, 0.197999954); // 2-4
	
	traffgen_gen gen_CG_11 <- atomic_traffgen([ species_of(moto), species_of(car)], headway_CG_11, speed, {4652.572627816505, 2453.786642591314, 0});
	traffgen_gen typeGen_CG_11 <- synchronized_traffgen([gen_CG_11], typeTranM, [species_of(moto), species_of(car)]);
	
	traffgen_period period_CG_11 <- create_period(typeGen_CG_11, 36000, 1390);

	traffgen_scheduler schedule_CG_1 <- create_schedule([period_CG_11], "sequence"); 
	
	// Discret
	traffgen_law headway_NCG_11 <-  pareto_4_law(0.5977091, 0.5129527, 2.313488, 0.197999954); // 2-4
	
	traffgen_gen gen_NCG_11 <- atomic_traffgen([ species_of(moto), species_of(car)], headway_NCG_11, speed, count_2_4, 48, {4652.572627816505, 2453.786642591314, 0});
	traffgen_gen typeGen_NCG_11 <- synchronized_traffgen([gen_NCG_11], typeTranM, [species_of(moto), species_of(car)]);
	
	traffgen_period period_NCG_11 <- create_period(typeGen_NCG_11, 36000, 1390);

	traffgen_scheduler schedule_NCG_1 <- create_schedule([period_NCG_11], "sequence"); 
	
	

	
	
	
	
	reflex generate {
		vehicle truc <-  schedule_PVG_1.next;
		if(truc!=nil){
			write "this vehicle width is" + truc.width + " height is "+ truc.height + " initial speed "+ truc.speed ;
			write  " arrival time "+ truc.activated_at;
			write "at location "+truc.location;
			save (string(truc.width) + ";" + string(truc.height) + ";" + string(truc.activated_at) + ";"  + string(truc.speed) + ";" + string(truc.tiv) + ";" + truc.location.x + ":" + truc.location.y + ":" + truc.location.z) to: "../includes/PVG_1.csv" type:"csv" rewrite:false;
			
		}
		
		truc <-  schedule_PVNG_1.next;
		if(truc!=nil){
			write "this vehicle width is" + truc.width + " height is "+ truc.height + " initial speed "+ truc.speed ;
			write  " arrival time "+ truc.activated_at;
			write "at location "+truc.location;
			save (string(truc.width) + ";" + string(truc.height) + ";" + string(truc.activated_at) + ";"  + string(truc.speed) + ";" + string(truc.tiv) + ";" + truc.location.x + ":" + truc.location.y + ":" + truc.location.z) to: "../includes/PVNG_1.csv" type:"csv" rewrite:false;
			
		}
		
		
		truc <-  schedule_VG_1.next;
		if(truc!=nil){
			write "this vehicle width is" + truc.width + " height is "+ truc.height + " initial speed "+ truc.speed ;
			write  " arrival time "+ truc.activated_at;
			write "at location "+truc.location;
			save (string(truc.width) + ";" + string(truc.height) + ";" + string(truc.activated_at) + ";"  + string(truc.speed) + ";" + string(truc.tiv) + ";" + truc.location.x + ":" + truc.location.y + ":" + truc.location.z) to: "../includes/VG_1.csv" type:"csv" rewrite:false;
			
		}
		
		truc <-  schedule_VNG_1.next;
		if(truc!=nil){
			write "this vehicle width is" + truc.width + " height is "+ truc.height + " initial speed "+ truc.speed ;
			write  " arrival time "+ truc.activated_at;
			write "at location "+truc.location;
			save (string(truc.width) + ";" + string(truc.height) + ";" + string(truc.activated_at) + ";"  + string(truc.speed) + ";" + string(truc.tiv) + ";" + truc.location.x + ":" + truc.location.y + ":" + truc.location.z) to: "../includes/VNG_1.csv" type:"csv" rewrite:false;
			
		}
		
		
		truc <-  schedule_3G_1.next;
		if(truc!=nil){
			write "this vehicle width is" + truc.width + " height is "+ truc.height + " initial speed "+ truc.speed ;
			write  " arrival time "+ truc.activated_at;
			write "at location "+truc.location;
			save (string(truc.width) + ";" + string(truc.height) + ";" + string(truc.activated_at) + ";"  + string(truc.speed) + ";" + string(truc.tiv) + ";" + truc.location.x + ":" + truc.location.y + ":" + truc.location.z) to: "../includes/3G_1.csv" type:"csv" rewrite:false;
			
		}
		
		truc <-  schedule_3NG_1.next;
		if(truc!=nil){
			write "this vehicle width is" + truc.width + " height is "+ truc.height + " initial speed "+ truc.speed ;
			write  " arrival time "+ truc.activated_at;
			write "at location "+truc.location;
			save (string(truc.width) + ";" + string(truc.height) + ";" + string(truc.activated_at) + ";"  + string(truc.speed) + ";" + string(truc.tiv) + ";" + truc.location.x + ":" + truc.location.y + ":" + truc.location.z) to: "../includes/3NG_1.csv" type:"csv" rewrite:false;
			
		}
		
		truc <-  schedule_CG_1.next;
		if(truc!=nil){
			write "this vehicle width is" + truc.width + " height is "+ truc.height + " initial speed "+ truc.speed ;
			write  " arrival time "+ truc.activated_at;
			write "at location "+truc.location;
			save (string(truc.width) + ";" + string(truc.height) + ";" + string(truc.activated_at) + ";"  + string(truc.speed) + ";" + string(truc.tiv) + ";" + truc.location.x + ":" + truc.location.y + ":" + truc.location.z) to: "../includes/CG_1.csv" type:"csv" rewrite:false;
			
		}
		
		truc <-  schedule_NCG_1.next;
		if(truc!=nil){
			write "this vehicle width is" + truc.width + " height is "+ truc.height + " initial speed "+ truc.speed ;
			write  " arrival time "+ truc.activated_at;
			write "at location "+truc.location;
			save (string(truc.width) + ";" + string(truc.height) + ";" + string(truc.activated_at) + ";"  + string(truc.speed) + ";" + string(truc.tiv) + ";" + truc.location.x + ":" + truc.location.y + ":" + truc.location.z) to: "../includes/NCG_1.csv" type:"csv" rewrite:false;
			
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
