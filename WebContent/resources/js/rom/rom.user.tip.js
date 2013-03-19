/*
*	User Tip
*
*	Author: Rob Shan Lone
*	Copyright (c) 2013 The Old County Limited.
*
*	All rights reserved.
*/

/**
	* @class Romance User Tip
	* @description Tool Tip to reveal more member details
*/

var userTip = Backbone.View.extend({

	/*
	* @description gets the user details to generate the pie chart
	*/
	getObj : function(that, callback){

		var userId = $(that).data('user-id');
		var userName = $(that).data('user-name');
		var userGender = $(that).data('user-gender').toLowerCase();

		var jsonUrl = 'api?servicerequest=getMembers&skips=0&limits=1&id='+userId;

		$.getJSON(jsonUrl, function(json) {

			var userAvatar = json[0].users[0].avatar;
			var goal1 = json[0].users[0].goal1;
			var goal2 = json[0].users[0].goal2;
			var goal3 = json[0].users[0].goal3;

			var userFollows = json[0].users[0].followers;
			var userPictureCount = json[0].users[0].pictureCount;

			//var featureImageThumbnail = value.pictureFeature;

			var contentsObj = {
							userId: userId,
							name: userName,
							avatar: userAvatar,
							goals: {
								head: goal1,
								heart: goal2,
								hand: goal3
							},
							gender: userGender,
							followers: userFollows,
							pictureCount: userPictureCount
						};
			callback(contentsObj);
		});
	},

 	/*
	 * @description event trigger hovered on item
	 */
	eventOnItem: function(){
		this.isrequired = true;
	},

 	/*
	 * @description event trigger hovered off item
	 */
	eventOffItem: function(){
		var delaytime = 300;
		this.isrequired = false;

		var that = this;
		window.setTimeout(function() {
			that.isneed();
		}, delaytime);
	},

 	/*
	 * @description event trigger hovered on tooltip
	 */
	eventOnTooltip: function(){
		this.isrequired = true;
	},

 	/*
	 * @description event trigger hovered off tooltip
	 */
	eventOffTooltip: function(){
		var delaytime = 400;
		this.isrequired = false;
		var that = this;
		window.setTimeout(function() {
			that.isneed();
		}, delaytime);
	},

	establishPie: function(){
		var holder = '#biometricPie';
		
		$(holder).empty();
		//create the biometric pie chart
		var specs = {
						color : 'brightOrange',
						w : 263,
						h : 210,
						r: 65,
						ir: 35
					};

		goPie.initChart('#biometricPie', specs);
		//create the biometric pie chart
		
		
	},
	
 	/*
	 * @description initialize
	 */
	initialize : function() {

		this.establishPie();
		this.tooltipWidth = parseInt($('#tooltip').outerWidth(true), 10);
		this.tooltipHeight = parseInt($('#tooltip').outerHeight(true), 10);

		var that = this;

		$(".hotspothead").mouseenter(function(e) {
			//hover on
			$('.head').fadeIn(200);
		}).mouseleave(function(e){
			//hover off
			$('.head').fadeOut(200);
		});

		$(".hotspotheart").mouseenter(function(e) {
			//hover on
			$('.heart').fadeIn(200);
		}).mouseleave(function(e){
			//hover off
			$('.heart').fadeOut(200);
		});

		$(".hotspothand").mouseenter(function(e) {
			//hover on
			$('.hand').fadeIn(200);
		}).mouseleave(function(e){
			//hover off
			$('.hand').fadeOut(200);
		});


		$(".users li").on({
			mouseenter: function(e){
				console.log("user li on");
				that.eventOnItem();

				var y_offset = 35;

				var offSetLeft = $(this).offset().left;
				var offSetTop = $(this).offset().top;

				var left = 0;
				var top = offSetTop + y_offset;

				var pointerDirection = that.getPointerDirection(e);
				$('#tooltip').removeClass().addClass(pointerDirection);

				switch(pointerDirection)
				{
					case "pointerLeft":
					   var x_offset = 75;
					  left = offSetLeft + x_offset;
					  break;
					case "pointerRight":
					  var x_offset = 25;
					  left = offSetLeft + x_offset - that.tooltipWidth;
					  break;
				}

				that.reposition(left, top);

				var listEl = this;
				that.getObj(listEl, function(contentsObj){
					that.populate(contentsObj); //show the tip
					that.show(0); //show the tip
				});


			},
			mousemove: function(e){
				var x_offset = 6;
				var y_offset = 6;

				var left = e.pageX + x_offset;
				var top = e.pageY + y_offset;
			},
			mouseleave: function(e){
				that.eventOffItem();
			}
		});

		$("#tooltip").mouseenter(function(e) {
			that.eventOnTooltip();
		}).mouseleave(function(e){
			that.eventOffTooltip();
		});
	},
	isactive: false,
	isrequired: false,
	toolTipHeight:"",
	toolTipWidth:"",
	getPointerDirection: function(e){
		var mouseLeft = e.pageX;

		var selectedElement = $('.users');
		var widthOfElement = selectedElement.outerWidth(true);
		var offset = selectedElement.offset();
		var edgeLeft = offset.left;
		var difference = (widthOfElement+edgeLeft) - mouseLeft;

		//get near end of container
		if(difference > this.tooltipWidth){
			mode = "pointerLeft";
		}
		else{
			mode = "pointerRight";
		}

		return mode;
	},
	destroy: function(){
		$('body').remove('#tooltip');
	},
	populate:function(contentsObj){

		$('.biospec h2').text(contentsObj.name);
		$('.bioimg .avatar img').attr("src", contentsObj.avatar);

		$('.bioimg .goals .head').text(contentsObj.goals.head);
		$('.bioimg .goals .heart').text(contentsObj.goals.heart);
		$('.bioimg .goals .hand').text(contentsObj.goals.hand);

		$('.biospec .gender').removeClass().addClass('gender').addClass(contentsObj.gender);

		$('.icons .myphotos span').text(contentsObj.pictureCount);
		$('.icons .followers span').text(contentsObj.followers);

		var profileLink = 'user?id='+contentsObj.userId;
		$('.fullprofile a').attr("href", profileLink);
		//update the biometric pie chart

		/*interest d3 pie chart*/
		//contentsObj.userId = "509f43fad1ef48c2c49c7a09";
		var jsonUrl = 'api?servicerequest=getInterests&id='+contentsObj.userId+'&chartname=interests';
		var holder = '#biometricPie';
		goPie.chosenUpdateChart(jsonUrl, holder);
		/*interest d3 pie chart*/
	},
	reposition:function(left, top){
		$('#tooltip').css('left', left).css('top', top);
	},
	show:function(duration){
		if(duration == 0)
		{
			$('#tooltip .costainner').show();
			$('#tooltip').show();
		}else
		{
			$('#tooltip').fadeIn(duration, function(){
				$('#tooltip .costainner').fadeIn(duration);
			});
		}
		this.isactive = true;
	},
	hide: function(duration){
		if(duration == 0)
		{
			$('#tooltip .costainner').hide();
			$('#tooltip').hide();
		}else
		{
			$('#tooltip .costainner').fadeOut(duration, function(){
				$('#tooltip').fadeOut(duration);
			});
		}
		this.isactive = false;
	},
	isneed: function(response){
		if(this.isrequired == false){
			this.hide(0); //hide the tip
		}
	}
});