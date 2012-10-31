/*
	Romancineers Utils
		Common Utils
*/
var utils = {
		trimVariable: function(variable){
			return variable.replace(/^\s+|\s+$/g, "");
		},
		initialize: function() {

		},
		//getBase: 'http://robot-oi772f3re:8080/',
		feedHomePie: "jsonuniqueuser",
		feedUsers: "jsonmembers",
		feedInterests: "jsoninterests",
		feedLocations: "jsonlocations",
		/*getJsonData: function(feedUrl, callback){
			$.getJSON(feedUrl, function(data) {
				callback(data);
			});
		},*/
		init: function(){
			$('body').addClass('responsive');
			this.resize();
		},
		lastMode: "",
		getMode: function(screenWidth){
			var mode = "regular";

			var mobileThreshold = 600;
			var regularThreshold = 1280;
			var imacThreshold = 1500;

			if(screenWidth < mobileThreshold){
				//mobile mode
				mode = "mobile";
			}
			if(screenWidth > mobileThreshold && screenWidth < regularThreshold){
				//regular mode
				mode = "regular";
			}
			if(screenWidth > regularThreshold){
				//imac mode
				mode = "imac";
			}

			return mode;
		},
		resize: function(){
			var screenWidth = $(window).width();
			var screenHeight = $(window).height();

			var mode = this.getMode(screenWidth);
			$('html').removeClass().addClass(mode);
		}
	};
