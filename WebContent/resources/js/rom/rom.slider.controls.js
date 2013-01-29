/*
*
* Author: Rob Shan Lone
* Copyright (c) 2012 The Old County Limited.
*
* All rights reserved.
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
            // read initial values from markup and remove that
            var value = parseInt($(this).text(), 10);
            // var id = $(this).data('field')+'id';
            //$(this).attr("id", id);

			$(this).empty().slider({
				value: value,
				orientation: "horizontal",
				range: "min",
				animate: true
			}).bind('slidechange',function(event,ui){
				that.changedEvent(event, ui);
			});

			var thatEl = this;
			var t = window.setInterval(function(){
				$(thatEl).slider( "option", "value", 50 );
				clearTimeout(t);
			},100);


			var fieldName = $(this).data('field');
			var input = '<input id="slider'+j+'" type="hidden" name="'+fieldName+'" value="">';
			$(this).append(input);
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
