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
		getMode: function(screenWidth, screenHeight){
			var mode = "regular";

			var mobileThreshold = 600;
			var ipadThreshold = 900;
			var regularThreshold = 1280;
			var imacThreshold = 1500;

			if(screenWidth < mobileThreshold){
				//mobile mode
				mode = "mobile";
			}
			if(screenWidth > mobileThreshold && screenWidth < ipadThreshold){
				//regular mode
				mode = "ipad";
			}			
			if(screenWidth > ipadThreshold && screenWidth < regularThreshold){
				//regular mode
				mode = "regular";
			}
			if(screenWidth > regularThreshold){
				//imac mode
				mode = "imac";
			}

			return mode;
		},
		userPieWidth: 375,
		userPieHeight: 280,
		userPieRadius: 70,
		userPieRadius: 35,
		setUserPieSizes: function(mode){
			
			switch(mode)
			{
				case "mobile":
					this.userPieWidth = 265;
					this.userPieHeight = 220;
					this.userPieRadius = 60;
					this.userPieInnerRadius = 25;
				  break;			
				case "regular":
					this.userPieWidth = 280;
					this.userPieHeight = 280;
					this.userPieRadius = 65;
					this.userPieInnerRadius = 35;					
					/*
					this.userPieWidth = 285;
					this.userPieHeight = 240;
					this.userPieRadius = 45;
					this.userPieInnerRadius = 20;
					*/
				  break;
				case "imac":
					this.userPieWidth = 375;
					this.userPieHeight = 280;
					this.userPieRadius = 80;
					this.userPieInnerRadius = 40;
				  break;
			}		
			
		},
		resize: function(){
			var screenWidth = $(window).width();
			var screenHeight = $(window).height();

			var mode = this.getMode(screenWidth, screenHeight);
			this.setUserPieSizes(mode);
			
			$('html').removeClass().addClass(mode);
		}		
};