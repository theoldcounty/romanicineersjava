/*
*
* Author: Rob Shan Lone
* Copyright (c) 2012 The Old County Limited.
*
* All rights reserved.
*/

/**
	* @class Romance User Tip
	* @description Tool Tip to reveal more member details
*/

var searchFilters = Backbone.View.extend({

 	/*
	 * @description initialize
	 */
	initialize : function() {
		console.log("init search filter");
		this.bindEvent();

		//if query parameter exists
		if(window.location.hash) {
		  // Fragment exists
		} else {
		  // Fragment doesn't exist
		}

	},


 	/*
	 * @description initialize
	 */
	runIsotope : function(obj) {
		$('.users').isotope(obj);
	},


 	/*
	 * @description initialize
	 */
	bindEvent : function() {
		var that = this;
		$('#query').submit(function(e) {
			e.preventDefault();

			var term = $(this).serializeArray();
			var filterChoice = term[0].value;
			console.log("filterChoice", filterChoice);
			window.location.hash = "?q="+filterChoice;

			//window.location = 'http://davidwalsh.name'; //redirects to homepage

			var obj = { filter: $('.element:interest('+filterChoice+')') };
			that.runIsotope(obj);
			//take query and process it on filter
		});


		//if query emptied - do another trigger to show all
		//{ filter: $('.element*') }

		//var obj = { filter: $('.element:interest('+filterChoice+')') };
		//that.runIsotope(obj);
	}

});
