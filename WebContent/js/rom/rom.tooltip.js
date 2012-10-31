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

//http://localhost/romancineers/js/json/romancineers.gethomepie.json
		$.getJSON(utils.feedHomePie, function(json) {
			var userAvatar = json[0].avatar;
			var userGoals = json[0].goals;
			var userInterestPie = json[0].interestPie;

			var contentsObj = {
							userId: userId,
							name: userName,
							avatar: userAvatar,
							goals: userGoals,
							gender: userGender,
							interestPieJson: userInterestPie
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

 	/*
	 * @description initialize
	 */
	initialize : function() {

		//create the biometric pie chart
		var specs = {
						color : 'brightOrange',
						w : 263,
						h : 210,
						r: 65,
						ir: 35
					}

		goPie.initChart('#biometricPie', specs);
		//create the biometric pie chart

		this.tooltipWidth = parseInt($('#tooltip').outerWidth(true), 10);
		this.tooltipHeight = parseInt($('#tooltip').outerHeight(true), 10);

		var that = this;

		$(".users li").on({
			mouseenter: function(e){
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

		//update the biometric pie chart
		goPie.updateCharts('#biometricPie', contentsObj.interestPieJson);
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
