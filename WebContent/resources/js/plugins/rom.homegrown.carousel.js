/*
*	Home Grown Carousel :: plugin
*
*	Author: Rob Shan Lone
*	Copyright (c) 2013 The Old County Limited.
*
*	All rights reserved.
*/

$.fn.homegrowncarousel = function(params) {
	// merge default and user parameters
	params = $.extend(
		{
			homegrownparent: "",
			inMotion:false,
			numItems:"",
			disableLeft: true,
			disableRight: false,
			maxBatchPossible: "",
			itemsPerRow:4,
			totalComb: 0,
			batchCount:0,//number of panes slid
			setMode: function(){
				var screenmode = $('html').attr('class');
				if(screenmode == "mobile"){
					this.itemsPerRow = 2;
				}
				if(screenmode == "regular"){
					this.itemsPerRow = 4;
				}
				if(screenmode == "imac"){
					this.itemsPerRow = 6;
				}
			},
			getDimensions: function(){
				this.setMode();

				params.numItems = $('#'+params.homegrownparent + ' li').size();

				var theHoneycomb = $('#'+params.homegrownparent + ' li');
				params.totalComb = theHoneycomb.outerWidth(true);

				totalWidth = (params.totalComb * params.numItems)/2 + params.totalComb;
				$('#'+params.homegrownparent + ' .wrapper ul').css('width', totalWidth);

				var combWidth = params.totalComb * params.itemsPerRow;
				params.maxBatchPossible = Math.ceil(totalWidth/combWidth);
			},
			setDimensions: function(){
				//loop through images and snap modes to ensure the image ratios fix.
				$('#'+params.homegrownparent + ' li').each(function(index) {
					var thisImages = $(this).find('img');

					var img = new Image();
					img.onload = function() {
						if(this.height > this.width){
							thisImages.addClass('portrait');
						}
						else{
							thisImages.addClass('widescreen');
						}
					}
					img.src = thisImages.attr("src");
				});
			},
			init:function(homegrownparent){
				params.homegrownparent = homegrownparent.id;
				//add wrappers
				var previousTemplate = '<div class="previous"><a href="#">Prev</a></div>';
				var nextTemplate = '<div class="next"><a href="#">Next</a></div>';
				$('#'+homegrownparent.id).prepend(previousTemplate);
				$('#'+homegrownparent.id).append(nextTemplate);

				this.getDimensions();
				this.setDimensions();

				//this.itemsPerRow

				var limits = Math.ceil(params.numItems/2);

				params.disableLeft = false;
				params.hidenshowControl("previous", 0);

				params.disableRight = false;
				params.hidenshowControl("next", 1);

				if(limits <= this.itemsPerRow){
					//not enough items?
					//hide next arrow
					params.hidenshowControl("next", 0);
				}

				$('#'+params.homegrownparent + ' .wrapper ul').fadeIn(1500);
			},
			shift:function(direction){
				globalDisable = false;

				var theCarousel = $('#'+params.homegrownparent + ' .wrapper');
				var totalWidth = theCarousel.outerWidth(true);

				if(params.inMotion == true){
					globalDisable = true;
				}

				if(params.disableLeft && direction == "prev"){
					//left is disabled user trying to go back - STOP THEM
					globalDisable = true;
				}

				if(params.disableRight && direction == "next"){
					//left is disabled user trying to go back - STOP THEM
					globalDisable = true;
				}

				//if the caroseul has enough items and its allowed to move then move.
				if(!globalDisable)
				{
					params.inMotion = true;
					if(direction == "next"){
						//prev
						params.batchCount++;
						direction = "-";
					}else{
						//next
						params.batchCount--;
						direction = "+";
					}

					$('#'+params.homegrownparent + ' .wrapper ul').animate({
						opacity: 0.85,
						marginLeft: direction+'='+totalWidth
					}, 1200, "easeOutCubic", function() {
						// Animation complete.
						$('#'+params.homegrownparent + ' .wrapper ul').animate({
							opacity: 1
						});

						params.checkArrows();
						params.inMotion = false;
					});
				}
			},
			checkArrows: function(){
				this.getDimensions();

				if(params.batchCount <= 0){
					//hide the left
					params.disableLeft = true;
					params.hidenshowControl("previous", 0);
				}else{
					//show the left
					params.disableLeft = false;
					params.hidenshowControl("previous", 1);
				}

				if(params.batchCount >= params.maxBatchPossible-1){
					//hide the right
					params.disableRight = true;
					params.hidenshowControl("next", 0);
				}else{
					//show the right
					params.disableRight = false;
					params.hidenshowControl("next", 1);
				}
			},
			hidenshowControl : function(side, display){
				if(display==1){
					$('#'+params.homegrownparent + " ."+side+" a").fadeIn(300);
				}
				else{
					$('#'+params.homegrownparent + " ."+side+" a").fadeOut(300);
				}
			}
		},
		params
	);
	// traverse all nodes

	this.each(function() {
		// express a single node as a $ object
		params.init(this);

		$('body').find('#'+params.homegrownparent).find(".previous a").live("click", function(event){
			event.preventDefault();
			params.shift("prev");
		});

		$('body').find('#'+params.homegrownparent).find(".next a").live("click", function(event){
			event.preventDefault();
			params.shift("next");
		});
	});

	// allow $ chaining
	return this;
};
