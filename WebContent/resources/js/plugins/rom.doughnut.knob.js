/*
*	Doughnut Knob :: plugin
*
*	Author: Rob Shan Lone
*	Copyright (c) 2013 The Old County Limited.
*
*	All rights reserved.
*/

(function($) {
	var privateFunction = function() {
		// code here
	}

	var methods = {
		init: function(options) {
			// Repeat over each element in selector

			return this.each(function() {
				var $this = $(this);

				// Attempt to grab saved settings, if they don't exist we'll get "undefined".
				var settings = $this.data('doughnutKnob');

				// If we could't grab settings, create them from defaults and passed options
				if(typeof(settings) == 'undefined') {
					var defaults = {
						propertyName: 'value',
						onSomeEvent: function() {}
					}
					settings = $.extend({}, defaults, options);

					// Save our newly created settings
					$this.data('doughnutKnob', settings);

				} else {
					// We got settings, merge our passed options in with them (optional)
					settings = $.extend({}, settings, options);
					// If you wish to save options passed each time, add:
					// $this.data('doughnutPie', settings);
				}

				// run code here
				methods.setup(this, options);
			});
		},

		destroy: function(options) {
			// Repeat over each element in selector
			return $(this).each(function() {
				var $this = $(this);

				// run code here
				// Remove settings data when deallocating our plugin
			});
		},

		setup: function(obj, options){
			//setColors

			//options.fieldName

			var element = '#'+obj.id;

			//create element on the fly
			//<input class="knob" data-width="150" data-cursor=true data-fgColor="#222222" data-thickness=.3 value="29">
			var initialValue = options.value;
			var template = '<input name="'+options.fieldName+'" class="knob" value="'+initialValue+'">';
			$(element).html(template);

			var knobElement = $(element +' .knob');

			$.each(options.data, function(index, value) {
				console.log("index", index);
				console.log("value", value);
				knobElement.attr('data-'+index,value);
			});

			if(options.type=="standard"){
				this.createStandard(knobElement);
			}else{
				//this.createInfinite($(element +' .knob');
			}

		},
		createStandard: function(knobElement){

			  $(knobElement).knob({
					/*change : function (value) {
						//console.log("change : " + value);
					},
					release : function (value) {
						console.log("release : " + value);
					},
					cancel : function () {
						console.log("cancel : " + this.value);
					},*/
					draw : function () {

						// "tron" case
						if(this.$.data('skin') == 'tron') {

							var a = this.angle(this.cv)  // Angle
								, sa = this.startAngle          // Previous start angle
								, sat = this.startAngle         // Start angle
								, ea                            // Previous end angle
								, eat = sat + a                 // End angle
								, r = 1;

							this.g.lineWidth = this.lineWidth;

							this.o.cursor
								&& (sat = eat - 0.3)
								&& (eat = eat + 0.3);

							if (this.o.displayPrevious) {
								ea = this.startAngle + this.angle(this.v);
								this.o.cursor
									&& (sa = ea - 0.3)
									&& (ea = ea + 0.3);
								this.g.beginPath();
								this.g.strokeStyle = this.pColor;
								this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sa, ea, false);
								this.g.stroke();
							}

							this.g.beginPath();
							this.g.strokeStyle = r ? this.o.fgColor : this.fgColor ;
							this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sat, eat, false);
							this.g.stroke();

							this.g.lineWidth = 2;
							this.g.beginPath();
							this.g.strokeStyle = this.o.fgColor;
							this.g.arc( this.xy, this.xy, this.radius - this.lineWidth + 1 + this.lineWidth * 2 / 3, 0, 2 * Math.PI, false);
							this.g.stroke();

							return false;
						}
					}
				});
		},
		createInfinite: function(knobElement){

			// Example of infinite knob, iPod click wheel
			var v, up=0,down=0,i=0
			,$idir = $("div.idir")
			,$ival = $("div.ival")
			,incr = function() { i++; $idir.show().html("+").fadeOut(); $ival.html(i); }
			,decr = function() { i--; $idir.show().html("-").fadeOut(); $ival.html(i); };

			$("input.infinite").knob({
				min:0,
				max:20,
				stopper: false,
				change: function () {
					if(v > this.cv){
						if(up){
							decr();
							up=0;
						}else{up=1;down=0;}
					} else {
						if(v < this.cv){
							if(down){
								incr();
								down=0;
							}else{down=1;up=0;}
						}
					}
					v = this.cv;
				}
			});
		}
	};

	$.fn.doughnutKnob = function() {
		var method = arguments[0];

		if(methods[method]) {
			method = methods[method];
			arguments = Array.prototype.slice.call(arguments, 1);
		} else if( typeof(method) == 'object' || !method ) {
			method = methods.init;
		} else {
			$.error( 'Method ' +  method + ' does not exist on jQuery.doughnutKnob' );
			return this;
		}

		return method.apply(this, arguments);
	};

})(jQuery);