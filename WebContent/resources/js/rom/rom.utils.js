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
