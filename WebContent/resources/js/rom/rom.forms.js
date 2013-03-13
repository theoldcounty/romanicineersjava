/*
*	Forms
*
*	Author: Rob Shan Lone
*	Copyright (c) 2013 The Old County Limited.
*
*	All rights reserved.
*/

var romForms = {
		getRandom: function(){
			var min = 0;
			var max = 100;
			// and the formula is:
			var random = Math.floor(Math.random() * (max - min + 1)) + min;

			return random;
		},

		createChartFramework: function(obj, handlerCallback){
			var that = this;

			var holder = $('#holderCharts');
			holder.empty();

			//getPlaceJson
			//getSeekingJson
			$.getJSON(obj.jsonPath, function(data) {
				var items = [];

				var keyParent = data.type;

				var j = 1;
				$.each(data.options, function(key, val) {

					var initialPieVal = that.getRandom();

					holder.append('<fieldset id="'+key+'" class="doughnutWrap"/>');

					var wrapper = holder.find('#'+key);
					wrapper.append('<select name="'+keyParent+'"/>');

					var selectBox = wrapper.find('select');
					$.each(val, function(key1, val2) {
						selectBox.append('<option value="'+val2+'">'+val2+'</option>');
					});

					var doughnutPie = '<fieldset data-fieldname="'+keyParent+'knobs" id="knob'+j+'" class="knob" data-color="'+obj.pieColor+'" data-role="doughnut-knob" data-value="'+initialPieVal+'" data-pie-size="'+obj.pieSize+'"></fieldset>';
					wrapper.append(doughnutPie);

					j++;
				});

				handlerCallback();
			});

		},
		setUpDoughnutCharts: function(chartType){
			var that = this;

			//console.log("chartType", chartType);

			var obj = {
					pieColor : "#E2B227",
					pieSize : 85,
					jsonPath: "getInterestJson"
			};

			switch(chartType)
			{
				case "interests":
					obj.pieColor = "#E2B227";
					obj.jsonPath = "getInterestJson";
				  break;
				case "seeking-relationship":
					obj.pieColor = "#B23959";
					obj.jsonPath = "getSeekingJson";
				  break;
				case "future-holiday":
					obj.pieColor = "#5751e4";
					obj.jsonPath = "getPlaceJson";
				  break;
			}

			this.createChartFramework(obj, function(){
				that.bindDoughnutKnob();
			});
		},
		setLatLng: function(countryName){
			//search for it on google map and obtain its lat and long
			console.log("countryName", countryName);

			//set the lat and long in the reg map


			//
			// initialize geocoder
			//
			var geocoder = new google.maps.Geocoder();

			geocoder.geocode(
			{
			address: countryName
			}, function(results, status) {
				if (status == google.maps.GeocoderStatus.OK) {
					var result = results[0];

					var newLat = result.geometry.location.mb;
					var newLong = result.geometry.location.nb;

					$('input[name="latitude"]').val(newLat);
					$('input[name="longitude"]').val(newLong);

				} else if (status == google.maps.GeocoderStatus.ZERO_RESULTS) {
					alert("Sorry, the geocoder failed to locate the specified address.");
				} else {
					alert("Sorry, the geocoder failed with an internal error.");
				}
			});



		},
		bindGoogleMapEvents: function(){
			var that = this;

			//listen to current selected country

			var selectboxCountry = $('select[name="country"]');

			that.setLatLng(selectboxCountry.find(":selected").text());

			selectboxCountry.change(function() {
				that.setLatLng($(this).find(":selected").text());
			});
		},
		setUpRegistration: function(){

			 $( ".registration" ).tabs();
			 this.bindSlideControllers();
			 this.bindGoogleMapEvents();

			 $('#registerForm').submit(function(e) {
					e.preventDefault();
					//console.log("clicked on reg - lets do an ajax post at some point");

					var postUrl = window.location;
					var formResults = $(this).serializeArray();
					//console.log("formResults", formResults);

					$.post("register", formResults,
						function(data) {
					    	console.log("json response. " + data);
					    	var obj = jQuery.parseJSON(data);

					    	//console.log("obj " + obj);

					    	if(obj[0].response == "OK"){
					    		console.log("user registered succeffully");
					    		//close color box

					    		//refresh site
					    		//console.log("current page",window.location.href);

								window.setInterval(function(){
									console.log("run a reload timer.");
									//location.reload();
								},500);
					    		//location.reload();
					    		//user has been registered succesfully
					    	}else{
					    		//there is an error with the registeration.
					    		console.log("backend error with reg - likely the username or email already exists");
					    		$('.error').html(obj[0].error);
					    	}
						}
					);
				});
		},

		setUpCharts: function(){

			 $('#chartForm').submit(function(e) {
					e.preventDefault();
					//console.log("clicked on reg - lets do an ajax post at some point");

					var postUrl = window.location;
					var formResults = $(this).serializeArray();
					console.log("formResults", formResults);

					$.post("edit_chart", formResults,
						function(data) {
					    	console.log("json response. " + data);
					    	var obj = jQuery.parseJSON(data);

					    	//console.log("obj " + obj);

					    	if(obj[0].response == "OK"){
					    		console.log("user edit_chart succeffully");
					    		//close color box

					    		//refresh site
					    		//console.log("current page",window.location.href);

								window.setInterval(function(){
									console.log("run a reload timer.");
									//location.reload();
								},500);
					    		//location.reload();
					    		//user has been registered succesfully
					    	}else{
					    		//there is an error with the registeration.
					    		console.log("backend error with edit_chart - likely the username or email already exists");
					    		$('.error').html(obj[0].error);
					    	}
						}
					);
				});
		},

		bindDoughnutKnob: function(){
		 	$('[data-role="doughnut-knob"]').each(function() {
				//console.log("take on a new backbone view.");
			  	var optionObj = {
					"data":
					{
						"min": 0,
						"max": 100,
						"width": $(this).data('pie-size'),
						"height": $(this).data('pie-size'),
						"displayPrevious": true,
						"fgColor": $(this).data('color')
					},
					"fieldName": $(this).data('fieldname'),
					"value": $(this).data('value'),
					"type": "standard"
				};

				//console.log("optionObj", optionObj);

				$($(this)).doughnutKnob('init', optionObj);
			});
		},
		bindSlideControllers: function(){
			$('[data-role="slider-controls-nav"]').each(function() {
				new sliderControls({el: $(this)});
			});
		},
		init: function(){
			//console.log("form complete");
		}
};