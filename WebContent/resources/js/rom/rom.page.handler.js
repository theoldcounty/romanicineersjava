/*
*	Rom Page Handler
*
*	Author: Rob Shan Lone
*	Copyright (c) 2013 The Old County Limited.
*
*	All rights reserved.
*/

var pageHandler = {
		reBindEvents: function(section){
			console.log("section", section);
			
			if(section.match(/register/gi)){
				//is regsitration form
				romForms.setUpRegistration();				
			}

			if(section.match(/edit_chart/gi)){
				console.log("found charts - set them up");
				romForms.setUpDoughnutCharts();
				romForms.setUpCharts();
			}
			
		},
		homeEvents: function(){
			
			
		},
		userEvents: function(){
			
			
		}
		
}