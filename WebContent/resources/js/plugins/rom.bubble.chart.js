/*
*	Bubble Chart :: plugin
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
				var settings = $this.data('bubbleChart');

				// If we could't grab settings, create them from defaults and passed options
				if(typeof(settings) == 'undefined') {

					var defaults = {
						propertyName: 'value',
						onSomeEvent: function() {}
					}
					settings = $.extend({}, defaults, options);

					// Save our newly created settings
					$this.data('bubbleChart', settings);

				} else {
					// We got settings, merge our passed options in with them (optional)
					settings = $.extend({}, settings, options);
					// If you wish to save options passed each time, add:
					// $this.data('bubbleChart', settings);
				}

				// run code here
				methods.setup(this, options);
			});
		},

		destroy: function(options) {
			// Repeat over each element in selector
			return $(this).each(function() {
				var $this = $(this);
				// Remove settings data when deallocating our plugin
				$this.removeData('bubbleChart');
			});
		},

		transitions: function(options) {
			// Repeat over each element in selector
			//var that = this;

			return $(this).each(function() {
				var $this = $(this);
				// run code here
				methods.update(this, options);
			});
		},

		bindEvent: function(){
			var that = this;
			//binding any events
		},
		currentData: null,
		setup: function(obj, options){
			this.currentData = options;

			var r = 270,
				format = d3.format(",d"),
				fill = d3.scale.category20c();

			var bubble = d3.layout.pack()
				.sort(null)
				.size([r, r]);

			var bubbleHolder = d3.select("#"+obj.id).append("svg")
				.attr("width", r)
				.attr("height", r)
				.attr("class", "bubble");

			var vis = bubbleHolder.append("svg:g")
				.attr("transform", "translate(15,15)");

			var node = vis.selectAll("g.node")
				.data(bubble.nodes(this.classes(this.currentData))
				.filter(function(d) { return !d.children; }))
				.enter().append("g")
				.attr("class", "node")
				.attr("transform", function(d) { return "translate(" + d.x/1.1 + "," + d.y/1.1 + ")"; });

			node.append("title")
				.text(function(d) { return d.className + ": " + format(d.value); });

			node.append("circle")
				.attr("r", function(d) { return d.r; })
				.style("fill", function(d) {
					return d.colorBand;
				});

			node.append("text")
				.attr("text-anchor", "middle")
				.attr("dy", ".3em")
				.text(function(d) { return d.className.substring(0, d.r / 3); });
		},
		classes:function(root) {
		  var classes = [];

		  function recurse(name, node) {
			if (node.children){
				node.children.forEach(function(child) {
					recurse(node.name, child);
				});
			}else{
				classes.push({colorBand: name, className: node.name, value: node.size});
			}
		  }

		  recurse(null, root);
		  return {children: classes};
		}
	};

	$.fn.bubbleChart = function() {
		var method = arguments[0];

		if(methods[method]) {
			method = methods[method];
			arguments = Array.prototype.slice.call(arguments, 1);
		} else if( typeof(method) == 'object' || !method ) {
			method = methods.init;
		} else {
			$.error( 'Method ' +  method + ' does not exist on jQuery.bubbleChart' );
			return this;
		}

		return method.apply(this, arguments);
	}

})(jQuery);

var goBubble = {
	chosenChart: function(jsonUrl, colourBands, holder, callback){

		$.getJSON(jsonUrl, function(data){
			var dataBubbleJson = [];
			var dataArray = data[0].dataResults;

			if(dataArray != undefined){
				$.each(dataArray, function(key, val) {
					var colourChoice = colourBands[Math.floor(Math.random()*colourBands.length)];
					var tempObj = {
						"name": colourChoice,
						"children":
						[
							{
								"name": key,
								"size": val
							}
						]
					};
					dataBubbleJson.push(tempObj);
				});
			}

			var datalength = dataBubbleJson.length;
			if(datalength > 0){
				$(holder).show();
				$(holder).attr("data-response", "true");
				var visitJson= {
						 "name": "flare",
						 "children": [
								{
									"name": "analytics",
									"children": dataBubbleJson
								}
							]
				};
				$(holder).bubbleChart('init', visitJson);
				callback(holder);
			}else{
				$(holder).hide();
				$(holder).attr("data-response", "false");
				callback(holder);
			}
		});
	}
};
