/*
*	Rom responsive
*
*	Author: Rob Shan Lone
*	Copyright (c) 2013 The Old County Limited.
*
*	All rights reserved.
*/

var romResponsive = {
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
}