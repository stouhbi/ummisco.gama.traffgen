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
}


species simpleSpecie{
	
	reflex update{
		write current_date;
	}
}


experiment simlpeExperiment {
	output{
		species simpleSpecie ;
	}
}