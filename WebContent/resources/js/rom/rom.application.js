/*
*
* Author: Rob Shan Lone
* Copyright (c) 2012 The Old County Limited.
*
* All rights reserved.
*/

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
