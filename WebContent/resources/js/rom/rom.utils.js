/*
*	Utils
*
*	Author: Rob Shan Lone
*	Copyright (c) 2013 The Old County Limited.
*
*	All rights reserved.
*/

var romUtils = {
	trimVariable: function(variable){
		return variable.replace(/^\s+|\s+$/g, "");
	},
	initialize: function() {

	},
	getQueryVariable:function(url, variable) {
		
		var query = url.split("?")[1];
		
	    //var query = window.location.search.substring(1);
	    var vars = query.split("&");
	    console.log("vars", vars);
	    for (var i=0; i< vars.length; i++) {
	      var pair = vars[i].split("=");
	      if (pair[0] == variable) {
	    	  
	    	  console.log("pair[1]", pair[1]);
	      return pair[1];
	    }
	  }
	  alert('Query Variable ' + variable + ' not found');
	},	
	
	//getBase: 'http://robot-oi772f3re:8080/',
	feedHomePie: "jsonuniqueuser",
	feedUsers: "jsonmembers",
	feedInterests: "jsoninterests",
	feedLocations: "jsonlocations"
	/*getJsonData: function(feedUrl, callback){
		$.getJSON(feedUrl, function(data) {
			callback(data);
		});
	},*/


};
