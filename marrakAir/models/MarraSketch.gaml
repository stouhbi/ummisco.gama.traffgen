/**
* Name: Marrasketch
* Author: Arnaud Grignard
* Description: Model only displaying the current gis file of the MarrakAir model
*/
  

model Marrasketch

global {
    date starting_date <- date([2017,4,12,8,0,0]);
	float step <- 50 #msec;
	file shape_file_roads <- file("../includes/SIG_simu/roads_gama.shp");
	file shape_file_buildings <- file("../includes/SIG_simu/buildings_gama.shp");
	file shape_file_bound <- file("../includes/SIG_simu/reperes.shp");
	graph the_graph; 
	geometry shape <- envelope(shape_file_bound);
	dummy dumdum <- dummy("word", 12.0);
	
	list vehicle_types <- ['Car', 'Bus', 'camaio'];
	map generation_model <- ['model'::'exponential','lambda'::0.5];
	//map generation_model1 <- ['model'::'pearson5', "alpha"::2.020608, "beta"::5.113421, "u"::-0.3689999];
	//map generation_model1 <- ['model'::'pareto4', "alpha"::6.829899e-01, "gamma"::0.35516840, "sigma"::2.075340, "u"::-0.12357438];
	//map generation_model2 <- ['model'::'pareto4', "alpha"::0.12256310, "gamma"::0.072108980, "sigma"::2.960743, "u"::-1.8884545];
	//map generation_model1 <- ['model'::'exponential', 'lambda'::0.31071506];
	//map generation_model2 <- ['model'::'exponential', 'lambda'::0.246329444];
	
	/** Pareto 3 */
	//map generation_model1 <- ['model'::'pareto3', "gamma"::0.4571873, "beta"::2.432926, "u"::-0.007825314];
	//map generation_model2 <- ['model'::'pareto3', "gamma"::0.4845089, "beta"::2.742127, "u"::0.0];
	
	/** Pareto 4 */
	map generation_model1 <- ['model'::'pareto4', "alpha"::0.7530387, "gamma"::0.37572535, "sigma"::1.927126, "u"::0.0];
	map generation_model2 <- ['model'::'pareto4', "alpha"::0.18136234, "gamma"::0.15520025, "sigma"::1.528608, "u"::-0.3010062];
	timeHeadway model1 <- generation_model(vehicle_types, generation_model1);
	timeHeadway model2 <- generation_model(vehicle_types, generation_model2);
	period_generator period1 <- period_generator(1, [model1], 48,["model"::"poisson", "lambda"::29.0232558 ]);
	period_generator period2 <- period_generator(2, [model2], 37,["model"::"poisson", "lambda"::4.7250000]);
	speed_generator speed1 <- speed_generator( ['Car'::20.0, 'Bus'::30.0, 'camaio'::40.0]);
	matrix transition <- matrix([[0.3,0.3,0.4],[0.3,0.4,0.3],[0.4,0.3,0.3]]);
	date timeStart <- date([2017,4,12,18,0,0]);
	date timeEnd <-  date([2017,4,12,20,0,0]);
	// [{4652.572627816505, 2453.786642591314, 0}::0.3486935, {4652.572627816505, 2473.786642591314, 0}::0.3706097, {4652.572627816505, 2493.786642591314, 0}::0.2806968]
	vehicle_generator gen1 <- vehicle_generator( [timeStart, timeEnd], "matrix", transition, vehicle_types, [period1, period2], speed1, [{4652.572627816505, 2453.786642591314, 0}::1.0], "split_independant");
	init {
		write dumdum.word;
		write dumdum.number;
		write model1.headwayGenerationModel;
		write period1.duration;
		write gen1.timeInterval;
		/* list vehicle_types <- ['car', 'Bus', 'camaio'];
		 string generation_model <- string("poisson");
		 map params <- ['lambda'::4];
		
		timeHeadway model1 {
		    	vehicleTypes <- vehicle_types;
		    	generationModel <- generation_model;
		    	parameters <- params;
		 }
	 	 speed speed1 {
	    	 	meanSpeed <- ['car'::20.0, 'Bus'::30.0, 'camaio'::40.0];
	    	 }
	    	 
	   	period period1{
	    		periodSequence <- 1;
	    		THGenerators <- [model1];
	    		duration <- 60;
	    	}
	    	
	    vehicleGenerator gen1 {
	    		timeInterval <- ["00:00", "01:00"];
	    		transitionType <- "matrix";
	    		transition <- [[1,0,0],[0,1,0],[0,0,1]];
	    		vehicleTypes <- vehicle_types;
	    		speed <- speed1;
	    		timeHeadway <- period1;
	    	}
	    	write gen1;*/
	    	
	   
		create road from: shape_file_roads with:[type::string(get("type"))]{
	         if (type = 'primary') {
					color <- #red;
		     }
		     if (type = 'secondary') {
					color <- #blue;
		     }
		     if (type = 'tertiary') {
					color <- #black;
		     }	
		     if(location = {4652.572627816505, 2453.786642591314, 0}){
		     		generators <- [gen1.timeInterval[0]::[gen1]];
					write "generating vehicles";
	  	  			//do generateVehicles("count");
	  	  			do generateVehicles("count");
	  	  			write generators;
	  	  			write length(vehicles);
	  	  			loop i from:0 to:length(vehicles)-1{
	  	  				vehicle v <- vehicles[i];
	  	  				save (v.type + ";" + v.arrival + ";" + v.headway + ";"  + v.position.x+":"+v.position.y+":"+v.position.z) to: "../includes/data.csv" type:"csv" rewrite:false;
	  	  			}
	  	  			/*write generators[0].vehicleList;
	  	  			write first(generators[0].timeHeadwayList);
	  	  			write generators[0].timeHeadwayList;
	  	  			write generators[0].transition;*/
		     }
		}
		
		// first(road).generators <-  [gen1.timeInterval[0]::[gen1]];
			//do generateVehicles("headway");
		
		
		//r.generators  <-  [gen1.timeInterval[0]::[gen1]];
		//r.generateVehicles("headway");
		
		the_graph <- as_edge_graph(road) ;
		
		
		
		create building from: shape_file_buildings with: [type:: string(read('building'))] {
			if type = 'small' {
				height<-10 + rnd(5);
			}
			if type = 'medium' {
				height<-50 + rnd(10);
			}
			if type = 'tall' {
				height<-100 +rnd(10) ;
			}
		}
		/*create people number: 100 {
			target <- any_location_in(one_of (road)) ;
			location <- any_location_in (one_of(building));
			source <- location;
		} */
	}

}

species road skills: [vehicleGen]{
    string type;
    rgb color;
    
    reflex generate {
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
    			
    }
	aspect base {
		draw shape color: color;
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
