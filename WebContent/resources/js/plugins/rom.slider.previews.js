/*
*	Slider Previews :: plugin
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
			console.log("init");

			return this.each(function() {
				var $this = $(this);

				// Attempt to grab saved settings, if they don't exist we'll get "undefined".
				var settings = $this.data('sliderPreviews');

				// If we could't grab settings, create them from defaults and passed options
				if(typeof(settings) == 'undefined') {

					var defaults = {
						propertyName: 'value',
						onSomeEvent: function() {}
					}
					settings = $.extend({}, defaults, options);

					// Save our newly created settings
					$this.data('sliderPreviews', settings);

				} else {
					// We got settings, merge our passed options in with them (optional)
					settings = $.extend({}, settings, options);
					// If you wish to save options passed each time, add:
					// $this.data('sliderPreviews', settings);
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
				$this.removeData('sliderPreviews');
			});
		},

		bindEvent: function(){
			var that = this;

			//binding any events

			/*$(window).on("click", function() {
				//update json
				that.update();
			});*/
		},
		getTemplate: function(){


			return template;
		},
		currentData: null,
		setup: function(obj, options){
			this.currentData = options;

			var sliderpreviews = $(obj);
			sliderpreviews.empty();

			var that = this;

			$.each(this.currentData, function(index, v) {

				var neg = '<div class="negative">'+v.negative+'</div>';
				var pos = '<div class="positive">'+v.positive+'</div>';
				var bar = '<div class="bar"><div class="holder"><div class="indicate" style="left: '+v.value+'%;"></div></div></div>';

				var template = '<li>'+neg+''+bar+''+pos+'</li>';
				sliderpreviews.append(template);
			});
			//setting data
		}
	};

	$.fn.sliderPreviews = function() {
		var method = arguments[0];

		if(methods[method]) {
			method = methods[method];
			arguments = Array.prototype.slice.call(arguments, 1);
		} else if( typeof(method) == 'object' || !method ) {
			method = methods.init;
		} else {
			$.error( 'Method ' +  method + ' does not exist on jQuery.sliderPreviews' );
			return this;
		}

		return method.apply(this, arguments);
	}

})(jQuery);