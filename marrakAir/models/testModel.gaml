/**
* Name: testModel
* Author: saadtouhbi
* Description: 
* Tags: Tag1, Tag2, TagN
*/

model testModel

global {
	date starting_date <- date([2017,4,12,8,0,0]);
	float step <- 50 #msec;
	init{
		create simpleSpecie number: 1;
			
	}
	
		
	map typeTran <- [species_of(car)::0.720608575, species_of(bus)::0.002963841, species_of(moto)::0.276427583];	
	// Creation des lois
	traffgen_law headway1 <-  pareto_4_law(0.7530387, 0.37572535, 1.927126, 0.0);
	traffgen_law headway2 <-  pareto_4_law(0.18136234, 0.15520025, 1.528608, -0.3010062);
	traffgen_law headway3 <-  pareto_3_law(0.4571873, 2.432926, -0.007825314);
	traffgen_law headway4 <-  exponential_law(0.31071506);
	traffgen_law speed <- poisson_law(16);
	traffgen_law countLaw <- poisson_law(29.0232558);
		
	// creation des generateur atomiques
	traffgen_gen gen1 <- atomic_traffgen(species_of(car), headway1, speed, {4652.572627816505, 2453.786642591314, 0});
	traffgen_gen gen2 <- atomic_traffgen(species_of(bus), headway2, speed, {4652.572627816505, 2453.786642591314, 0});
	traffgen_gen gen3 <- atomic_traffgen(species_of(moto), headway3, speed, {4652.572627816505, 2453.786642591314, 0});
	traffgen_gen gen4 <- atomic_traffgen([species_of(car), species_of(bus), species_of(moto)], headway4, speed, countLaw, 48, {4652.572627816505, 2453.786642591314, 0});
	
	
	// creation de generateur de type de vehicules avec probabilite a chaque type de vehicule
	map typeTran1 <- [species_of(car)::0.5, species_of(bus)::0.1, species_of(moto)::0.4];
	map typeTran2 <- [species_of(car)::0.4, species_of(bus)::0.1, species_of(moto)::0.5];
	traffgen_gen typeGen1 <- map_traffgen([gen1, gen2, gen3], typeTran);
	traffgen_gen typeGen2 <- map_traffgen([gen4], typeTran);
	
	// creation des periodes
	traffgen_gen period1 <- create_period(typeGen1, 3600);
	traffgen_gen period2 <- create_period(typeGen2, 3600);
	
	// creation de planificateur	
	traffgen_gen schedule <- create_schedule([period1, period2], "sequence");


}


species simpleSpecie{
	
	reflex update{
		write current_date;
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


experiment simlpeExperiment {
	output{
		species simpleSpecie ;
	}
}