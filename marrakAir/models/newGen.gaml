/**
* Name: newGen
* Author: saadtouhbi
* Description: 
* Tags: Tag1, Tag2, TagN
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
	
	traffgen_law headway <-  pareto_4_law(0.7530387, 0.37572535, 1.927126, 0.0);
	traffgen_law speed <- poisson_law(25);
	//geometry locat <- square(2) at :{4652.572627816505, 2453.786642591314, 0};
	traffgen_gen gen1 <- atomic_traffgen(car, headway, speed, {4652.572627816505, 2453.786642591314, 0});
	traffgen_gen gen2 <- atomic_traffgen(bus, headway, speed, {4652.572627816505, 2453.786642591314, 0});
	traffgen_gen gen3 <- atomic_traffgen(camaio, headway, speed, {4652.572627816505, 2453.786642591314, 0});
	map typeTran <- [car::30.0, bus::30.0, camaio::40.0];
	traffgen_gen typeGen <- map_traffgen([gen1, gen2, gen3], typeTran);
	
	traffgen_gen discret_gen <- discret_gen(typeGen, poisson_law(30), 48);
	traffgen_gen continuous_gen <- continuous_gen(typeGen);
	
	traffgen_period period1 <- create_period(typeGen, 48, 20);
	traffgen_period period2 <- create_period(typeGen, 37, 20);
	
}


species road skills: [vehicleGen]{
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


species car  skills:[moving]{
	
	float width <- 2.0;
	float height <- 5.0;
	bool launched <- false;
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

species bus  skills:[moving]{
		
	float width <- 3.0;
	float height <- 8.0;
	bool launched <- false;
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

species camaio  skills:[moving] {
	float width <- 3.0;
	float height <- 7.0;
	bool launched <- false;
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
			species camaio aspect: base;
		}
	}
}


