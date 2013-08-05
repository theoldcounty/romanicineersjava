/*
*	Rom Page Handler
*
*	Author: Rob Shan Lone
*	Copyright (c) 2013 The Old County Limited.
*
*	All rights reserved.
*/

var pageHandler = {
		reBindEvents: function(section){
			console.log("section", section);
			if(section.match(/register/gi)){
				//_is registration form
				romForms.setUpRegistration();
			}

			if(section.match(/edit_chart/gi)){
				//_edit charts
				romForms.setUpDoughnutCharts(romUtils.getQueryVariable(section, "type"));
				romForms.setUpCharts();
			}
			
			if(section.match(/edit_user/gi)){
				//_is edit form
				romForms.setUpEdit();
			}
			
			if(section.match(/sendPrivateMessages/gi)){
				//_is edit form
				romForms.setUpPrivateMessage();
			}			
			
			
			if(section.match(/forgotpassword/gi)){
				//_is edit form
				romForms.setUpForgotPassword();
			}	

			if(section.match(/login/gi)){
				//_is login form
				romForms.setUpLogin();
			}	
			
			if(section.match(/imageUpload/gi)){
				//_is login form
				romForms.setUpUploadImageForm();
			}				
			
			
		},
		homeEvents: function(){

		},
		userEvents: function(){
			var userId = $('.details').data('uid');
			//get data id of user

			//initialize home grown carousel
			//$('#galleryCarousel').homegrowncarousel();

			/*personality sliders*/
			var personalityUrl = 'api?servicerequest=getPersonality&id='+userId;
			$.getJSON(personalityUrl, function(data){
				var sliderpreviews = [];

				var personalityArray = data[0].personality;
				$.each(personalityArray, function(key, val) {
					var negative = null;
					var positive = null;

					switch (key)
					{
						case "confidence":
							negative = "Reserved";
							positive = "Outgoing";
						break;
						case "reasoning":
							negative = "Direct";
							positive = "Flexible";
						break;
						case "emotion":
							negative = "Senstitive";
							positive = "Steady";
						break;
						case "daring":
							negative = "Consitent";
							positive = "Curious";
						break;
						case "attachment":
							negative = "Couple";
							positive = "Family";
						break;
						case "sensitivity":
							negative = "Tender";
							positive = "Passionate";
						break;
						case "comedy":
							negative = "Focus";
							positive = "Funny";
						break;
					}

					var tempObj = {
									"negative": negative,
									"positive": positive,
									"value": val
								};

					sliderpreviews.push(tempObj);
				});
				$('#sliderResults').sliderPreviews('init', sliderpreviews);
			});
			/*personality sliders*/

			var userPieWidth = romResponsive.userPieWidth;
			var userPieHeight = romResponsive.userPieHeight;
			var userPieRadius = romResponsive.userPieRadius;
			var userPieInnerRadius = romResponsive.userPieInnerRadius;

			/*interest d3 pie chart*/
			var jsonUrl = 'api?servicerequest=getInterests&id='+userId+'&chartname=interests';
			var holder = '#interestsPie';

			var specs = {
					color : 'spectral',
					w : userPieWidth,
					h : userPieHeight,
					r: userPieRadius,
					ir: userPieInnerRadius
				};

			goPie.chosenChart(jsonUrl, specs, holder, function(hold, chartId){
				if(!$(hold).data("response")){
					$(hold).parent().find('.nodata').show();
				}
				else{
					$(hold).parent().find('.editdata').show();
					$(hold).parent().find('.editdata a').attr('href', $(hold).parent().find('.editdata a').attr('href')+'&chartId='+chartId);
				}
			});
			/*interest d3 pie chart*/

			/*seeking d3 pie chart*/
			var jsonUrl = 'api?servicerequest=getInterests&id='+userId+'&chartname=seeking';
			var holder = '#seekingPie';

			var specs = {
					color : 'brightOrange',
					w : userPieWidth,
					h : userPieHeight,
					r: userPieRadius,
					ir: userPieInnerRadius
				};

			goPie.chosenChart(jsonUrl, specs, holder, function(hold, chartId){
				if(!$(hold).data("response")){
					$(hold).parent().find('.nodata').show();
				}
				else{
					$(hold).parent().find('.editdata').show();
					$(hold).parent().find('.editdata a').attr('href', $(hold).parent().find('.editdata a').attr('href')+'&chartId='+chartId);
				}
			});
			/*seeking d3 pie chart*/

			/*visiting d3 bubble chart*/
			var jsonUrl = 'api?servicerequest=getInterests&id='+userId+'&chartname=visiting';
			var holder = '#quizchart';
			var colourBands = ["rgba(0,0,0,0.2)", "rgba(230,0,0,0.2)", "rgba(212, 222, 22, 0.2)"];
			goBubble.chosenChart(jsonUrl, colourBands, holder, function(hold, chartId){
				if(!$(hold).data("response")){
					$(hold).parent().find('.nodata').show();
				}
				else{
					$(hold).parent().find('.editdata').show();
					$(hold).parent().find('.editdata a').attr('href', $(hold).parent().find('.editdata a').attr('href')+'&chartId='+chartId);
				}
			});
			/*visiting d3 bubble chart*/

			//set user google map
			var lat = $('#canvasMap').data('lat');
			var long = $('#canvasMap').data('long');
			googleMaper.setup(lat, long);
			googleMaper.setUserMarker(lat, long);

		},
		scheduleDate: function(){
			//_enable the google map/foursquare dating app
			new GoogleMaps({el: $(document)});
		}
};
