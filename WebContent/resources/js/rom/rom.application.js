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

		// if user clicked on button, the overlay layer or the shazambox, close the shazam
		$('#shazam-overlay').click(function () {
			shazamOverlay.hide();
			return false;
		});

		$('.close').click(function () {
			shazamOverlay.hide();
			return false;
		});


		$('.shazam').click(function () {
			var link = $(this).attr("href");
			console.log("shazam link", link);
			shazamOverlay.show(link);

			return false;
		});

		//responsive development
		romResponsive.init();

	},
	init: function(){

		var url = window.location.href;
		var urlSplit = url.split("/");
		urlSplit.reverse();

		var lastParameter = urlSplit[0];

		this.global();

		/*home controller*/
		if(lastParameter == ""){
			//home page
			gridUserHandler.init();
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

	// if user resize the window, call the same function again
	// to make sure the overlay fills the screen and shazambox aligned to center
	//only do it if the shazam box is not hidden
	if (!$('#shazam-box').is(':hidden')){
		shazamOverlay.rePosition();
	}

	romResponsive.resize();
});
