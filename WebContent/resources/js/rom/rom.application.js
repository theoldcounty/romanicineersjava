/*
*	Rom Application
*
*	Author: Rob Shan Lone
*	Copyright (c) 2013 The Old County Limited.
*
*	All rights reserved.
*/

romApplication = {
	global: function(){
		//responsive development
		romResponsive.init();

	},
	init: function(){

		var url = window.location.href;
		var urlSplit = url.split("/");
		urlSplit.reverse();

		var lastParameter = urlSplit[0];

		this.global();

		swipers.init();
		fancybox.init();
		gridUserHandler.init();

		tabs.init();	
		mcustomscroller.init();


		galleryFix.init();

		/*home controller*/
		if(lastParameter == ""){
			//home page
			//gridUserHandler.init();
		}

		/*user controller*/
		if(lastParameter.match(/user/i) == "user"){
			//user page
			pageHandler.userEvents();
		}

		/*schedule date controller*/
		if(lastParameter.match(/scheduledate/i) == "scheduledate"){
			pageHandler.scheduleDate();
		}

		var searchFilter = new searchFilters();
	}
};

$(document).ready(function() {
	romApplication.init();
});

$(window).resize(function() {
	romResponsive.resize();
});
