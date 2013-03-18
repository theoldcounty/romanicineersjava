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
		$(".form").colorbox({
			width:"65%",/*
			height:"75%",*/
			onOpen:function(){
				//alert('onOpen: colorbox is about to open');
			},
			onLoad:function(){
				//alert('onLoad: colorbox has started to load the targeted content');
			},
			onComplete:function(){
				//alert('onComplete: colorbox has displayed the loaded content');

				var formType = $('#formType').data('form-type');

				console.log("form complete", formType);
				//is regsitration form
				romForms.setUpRegistration();

				if(formType == "doughnutcharts"){
					var chartType = $('#formType').data('chart-type');
					//is chart edit
					romForms.setUpDoughnutCharts(chartType);
					romForms.setUpCharts();
				}
			},
			onCleanup:function(){
				//alert('onCleanup: colorbox has begun the close process');
			},
			onClosed:function(){
				//alert('onClosed: colorbox has completely closed');
			}
		});

		//responsive development
		romResponsive.init();
		
	},
	init: function(){

		var url = window.location.href;
		//console.log("current url", url);

		var urlSplit = url.split("/");
		urlSplit.reverse();

		var lastParameter = urlSplit[0];

		this.global();
		

		/*home controller*/
		if(lastParameter == ""){
			//home page
			//console.log("home");
			controllerHome.init();
		}

		/*user controller*/
		if(lastParameter.match(/user/i) == "user"){
			//user page
			//console.log("user");
			controllerUser.init();
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