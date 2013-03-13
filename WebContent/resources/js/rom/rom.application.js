/*
*	Rom Application
*
*	Author: Rob Shan Lone
*	Copyright (c) 2013 The Old County Limited.
*
*	All rights reserved.
*/

romApplication = {
	init: function(){

		var url = window.location.href;
		//console.log("current url", url);

		var urlSplit = url.split("/");
		urlSplit.reverse();

		var lastParameter = urlSplit[0];

		romController.global();
		

		/*home controller*/
		if(lastParameter == ""){
			//home page
			//console.log("home");
			romController.home();
		}

		/*user controller*/
		if(lastParameter.match(/user/i) == "user"){
			//user page
			//console.log("user");
			romController.user();
		}

		/*
		var venues = [
						 ['Bondi Beach', -33.890542, 151.274856, 4],
						 ['Coogee Beach', -33.923036, 151.259052, 5],
						 ['Cronulla Beach', -34.028249, 151.157507, 3],
						 ['Manly Beach', -33.80010128657071, 151.28747820854187, 2],
						 ['Maroubra Beach', -33.950198, 151.259302, 1]
					 ];
		googlemaper.setVenueMarkers(googlemaper.map, venues);*/

		var searchFilter = new searchFilters();
	}
};

$(document).ready(function() {
	romApplication.init();
});

$(window).resize(function() {
	romUtils.resize();
});