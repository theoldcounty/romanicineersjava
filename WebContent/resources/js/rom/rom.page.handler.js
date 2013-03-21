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
				//_is registration form
				romForms.setUpRegistration();				
			}

			if(section.match(/edit_chart/gi)){
				//_edit charts
				romForms.setUpDoughnutCharts(romUtils.getQueryVariable(section, "type"));
			}
			
		},
		homeEvents: function(){
			
			
		},
		userEvents: function(){
			
			
		}
		
}