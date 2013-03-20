/*
*	Slider Controls
*
*	Author: Rob Shan Lone
*	Copyright (c) 2013 The Old County Limited.
*
*	All rights reserved.
*/

/**
	* @class Romance Slider Controls
	* @description Slider Controls
*/

var sliderControls = Backbone.View.extend({
	initialize : function() {
		var that = this;
		var sliders = $("#"+ this.$el[0].id + " > span");

        // setup graphic EQ
		var j = 0;
        sliders.each(function() {

			$(this).empty().slider({
				value: parseInt($(this).text(), 10),
				orientation: "horizontal",
				range: "min",
				animate: true
			}).bind('slidechange',function(event,ui){
				that.changedEvent(event, ui);
			});

			$(this).slider( "option", "value", 50 );

			var fieldName = $(this).data('field');
			var input = '<input id="slider'+j+'" type="hidden" name="'+fieldName+'" value="">';
			$(this).append(input);

			$(this).append('<span class="negative">'+$(this).data('negative')+'</span>');
			$(this).append('<span class="positive">'+$(this).data('positive')+'</span>');

			j++;
        });
	},

	changedEvent: function(event, ui){
		//console.log("event", event);
		//console.log("ui", ui);
		var element = $(ui.handle);
		var value = ui.value;

		element.parent().find('input').val(value);
	}
});
