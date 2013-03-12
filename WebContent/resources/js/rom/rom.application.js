/*
*
* Author: Rob Shan Lone
* Copyright (c) 2012 The Old County Limited.
*
* All rights reserved.
*/

$.rom = {
		
};

$.rom.forms = {
		
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
		setUpRegistration: function(){
			
			 $( ".registration" ).tabs();
			 this.bindSlideControllers();
			 
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


var romController = {
		global: function(){
			//calls js that is used on all pages
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
					$.rom.forms.setUpRegistration();
					
					if(formType == "doughnutcharts"){
						var chartType = $('#formType').data('chart-type');
						//is chart edit
						$.rom.forms.setUpDoughnutCharts(chartType);
						$.rom.forms.setUpCharts();
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
			utils.init();

		},
		home: function(){
				//calls js that is used only on home page

			// Initialize Backbone views.

			//var getMembers= new fetchMembers();

			var controllerUsers = {
					init: function(){

						this.getUsers(function(response){
							//console.log("response", response);

							var usertip = new userTip();
							var isoFilter= new isotopefilters();
						});

					},
					getInterests: function(id, callback){
						var interests = "";
						var interestUrl = 'api?servicerequest=getInterests&id='+id+'&chartname=interests';
						
						$.getJSON(interestUrl, function(interestdata){
							//console.log("interestdata", interestdata);
							
							if(interestdata[0].response == "OK"){
								$.each(interestdata[0].dataResults, function(key, variable) {
									interests+=key+',';
								});
								interests = interests.substring(0, interests.length - 1);
								callback(interests);
							}
						});
					},
					getUsers: function(callback){
						var that = this;
						$('.users').empty();
						var peopleUrl = 'api?servicerequest=getMembers&skips=0&limits=20';
						if(peopleUrl != null){
							$.getJSON(peopleUrl, function(data){
								//console.log("data",data);

								//data
								$.each(data[0].users, function(index, value) {
									//console.log("data", data);
									var id = value._id.$oid;
									
									//populate person
									var country = value.country;
									var gender = value.gender;
									var title = value.username;
									var isOnline = value.isloggedon;
									
									var url = "user?id="+id;
									var featureAvatarThumbnail = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRTL5GtjP3j0_EYZBejnEs-Wx9CQu_bIlNmDJNG-6rfwa55cEi-";//value.pictureAvatar;

									//populate person
									var template = '<li class="element odd" data-user-country="'+country+'" data-user-interests="" data-user-online="'+isOnline+'" data-user-gender="'+gender+'" data-user-name="'+title+'" data-user-id="'+id+'"><div class="avatar"><a href="'+url+'"><img src="'+featureAvatarThumbnail+'"></a></div></li>';
									$('.users').append(template);
									
									that.getInterests(id, function(interests){
										$('.users').find('li[data-user-id='+id+']').attr('data-user-interests', interests);
									});									
								});

								callback(true);
							});
						}
					}
			};
			
			controllerUsers.init();
		},
		user: function(){

			var userId = $('.details').data('uid');

			//calls js that is used only on user page
			$(".group").colorbox({
				rel:'group',
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
			goPie.chosenChart(jsonUrl, colorCode, holder);
			/*interest d3 pie chart*/

			/*seeking d3 pie chart*/
			var jsonUrl = 'api?servicerequest=getInterests&id='+userId+'&chartname=seeking';
			var holder = '#seekingPie';
			var colorCode = 'brightOrange';
			goPie.chosenChart(jsonUrl, colorCode, holder);
			/*seeking d3 pie chart*/

			/*visiting d3 bubble chart*/
			var jsonUrl = 'api?servicerequest=getInterests&id='+userId+'&chartname=visiting';
			var holder = '#quizchart';
			var colourBands = ["rgba(0,0,0,0.2)", "rgba(230,0,0,0.2)", "rgba(212, 222, 22, 0.2)"];
			goBubble.chosenChart(jsonUrl, colourBands, holder);
			/*visiting d3 bubble chart*/


			//set user google map
			var lat = $('#canvasMap').data('lat');
			var long = $('#canvasMap').data('long');
			googlemaper.setup(lat, long);
			googlemaper.setUserMarker(lat, long);
		}
};

$(document).ready(function() {

	var url = window.location.href;
	//console.log("current url", url);

	var urlSplit = url.split("/");
	urlSplit.reverse();

	var lastParameter = urlSplit[0];

	romController.global();
	if(lastParameter == ""){
		//home page
		//console.log("home");
		romController.home();
	}

	if(lastParameter.match(/user/i) == "user"){
		//user page
		//console.log("user");
		romController.user();
	}

    /*
    var venues = [
	                 ['Bondi Beach', -33.890542, 151.274856, 4],
	                 ['Coogee Beach', -33.923036, 151.259052, 5],
	                 ['Cronulla Beach', -34.028249, 151.157507, 3],
	                 ['Manly Beach', -33.80010128657071, 151.28747820854187, 2],
	                 ['Maroubra Beach', -33.950198, 151.259302, 1]
	             ];
    googlemaper.setVenueMarkers(googlemaper.map, venues);*/

	var searchFilter = new searchFilters();

 });

$(window).resize(function() {
	utils.resize();
});
