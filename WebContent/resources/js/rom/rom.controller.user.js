/*
*	Rom Controller User
*
*	Author: Rob Shan Lone
*	Copyright (c) 2013 The Old County Limited.
*
*	All rights reserved.
*/

var controllerUser = {
		init: function(){
			
			var userId = $('.details').data('uid');

		/*
			//calls js that is used only on user page
			$(".group").colorbox({
				rel:'group',
				width:"65%",
				onOpen:function(){
					//alert('onOpen: colorbox is about to open');
				},
				onLoad:function(){
					//alert('onLoad: colorbox has started to load the targeted content');
				},
				onComplete:function(){
					//alert('onComplete: colorbox has displayed the loaded content');
				},
				onCleanup:function(){
					//alert('onCleanup: colorbox has begun the close process');
				},
				onClosed:function(){
					//alert('onClosed: colorbox has completely closed');
				}
			});

			//calls js that is used only on user page
			$(".iframebox").colorbox({
				//iframe:true,
				width:"65%",
				height:"75%",
				onOpen:function(){
					//alert('onOpen: colorbox is about to open');
				},
				onLoad:function(){
					//alert('onLoad: colorbox has started to load the targeted content');
				},
				onComplete:function(){
					//alert('onComplete: colorbox has displayed the loaded content');
				},
				onCleanup:function(){
					//alert('onCleanup: colorbox has begun the close process');
				},
				onClosed:function(){
					//alert('onClosed: colorbox has completely closed');
				}
			});
		*/
			//get data id of user

			//initialize home grown carousel
			$('#galleryCarousel').homegrowncarousel();

			//console.log("userId", userId);
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


			/*interest d3 pie chart*/
			var jsonUrl = 'api?servicerequest=getInterests&id='+userId+'&chartname=interests';
			var holder = '#interestsPie';
			var colorCode = 'spectral';
			goPie.chosenChart(jsonUrl, colorCode, holder, function(hold){
				if(!$(hold).data("response")){
					$(hold).parent().find('.nodata').show();
				}				
			});

			/*interest d3 pie chart*/

			/*seeking d3 pie chart*/
			var jsonUrl = 'api?servicerequest=getInterests&id='+userId+'&chartname=seeking';
			var holder = '#seekingPie';
			var colorCode = 'brightOrange';
			goPie.chosenChart(jsonUrl, colorCode, holder, function(hold){
				if(!$(hold).data("response")){
					$(hold).parent().find('.nodata').show();
				}
			});	
			/*seeking d3 pie chart*/

			/*visiting d3 bubble chart*/
			var jsonUrl = 'api?servicerequest=getInterests&id='+userId+'&chartname=visiting';
			var holder = '#quizchart';
			var colourBands = ["rgba(0,0,0,0.2)", "rgba(230,0,0,0.2)", "rgba(212, 222, 22, 0.2)"];
			goBubble.chosenChart(jsonUrl, colourBands, holder, function(hold){
				if(!$(hold).data("response")){
					$(hold).parent().find('.nodata').show();
				}
			});				
			/*visiting d3 bubble chart*/


			//set user google map
			var lat = $('#canvasMap').data('lat');
			var long = $('#canvasMap').data('long');
			googleMaper.setup(lat, long);
			googleMaper.setUserMarker(lat, long);			
			
		}
};
