/**
	* @class Romance Slider Controls
	* @description Slider Controls
*/

var sliderControls = Backbone.View.extend({
	initialize : function() {
		var sliders = $("#"+ this.$el[0].id + " > span");

        // setup graphic EQ
        sliders.each(function() {
            // read initial values from markup and remove that
            var value = parseInt($(this).text(), 10);
			console.log("value",value);

            $(this).empty().slider({
                value: value,
                orientation: "horizontal",
				range: "min",
				animate: true
            });
        });
		this.bindEvents();
	},

	bindEvents: function() {
		console.log("click event");

		$('.ui-slider-handle').on("click", function(event){
			console.log("click detect", this);
			var value = parseInt($(this).css("left"), 10);
			console.log("new val", value);
		});
	}
});
