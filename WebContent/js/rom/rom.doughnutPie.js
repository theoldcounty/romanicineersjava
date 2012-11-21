/*
*
* Author: Rob Shan Lone
* Copyright (c) 2012 The Old County Limited.
*
* All rights reserved.
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
				var settings = $this.data('doughnutPie');

				// If we could't grab settings, create them from defaults and passed options
				if(typeof(settings) == 'undefined') {

					var defaults = {
						propertyName: 'value',
						onSomeEvent: function() {}
					}
					settings = $.extend({}, defaults, options);

					// Save our newly created settings
					$this.data('doughnutPie', settings);

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
				$this.removeData('doughnutPie');
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

		setup: function(obj, options){
			//setColors
			this.currentPieData = options.data;

			//console.log("this.currentPieData", this.currentPieData);
			//console.log("options", options);


			//options.specs.h
			//options.specs.w

			var setColors = options.specs.colors;
			//console.log("setColors", setColors);

			this.isInit = true;
			this.homegrownparent = obj.id;
			this.setMaxObj();
			var w = options.specs.w;
			var h = options.specs.h;

			this.r = options.specs.r;
			this.ir = options.specs.ir;

			var that = this;

			this.createPie(w, h, setColors, function(){
				that.bindEvent();
			});
		},

		isInit: false,

		maxObj: "",

		setMaxObj : function(){
			var that = this;
			var max = Math.max.apply(null, this.currentPieData.map(function(item){
				return item["octetTotalCount"];

			}));

			$.each(this.currentPieData, function(i, v) {
				if (v.octetTotalCount == max) {
					that.maxObj = v;
					return;
				}
			});
		},

		currentPieData: null,

		newPieData: null,

		oldPieData: null,

		futurePieData: null,

		r: 80,

		ir: 45,

		textOffset: 14,

		donut: null,

		createPie: function(w, h, setColors, pieCreatedCallback){

			//objects to be populated
			var lines, valueLabels, nameLabels;

			//populate pie slice parameters from array data
			this.donut = d3.layout.pie().value(function(d){
				return d.octetTotalCount;
			});


			//create colors from an ordinal scale
			//https://github.com/mbostock/d3/blob/master/lib/colorbrewer/colorbrewer.js
			//this.color = d3.scale.category20c();

			var colorType = 'colorbrewer.'+setColors[0].name+'['+setColors[0].col+']';
			this.color = d3.scale.ordinal()
				.domain(eval(setColors[0].domain))
				.range(eval(colorType));

			//draw arcs, populates parameter "d" in path object
			this.arc = d3.svg.arc()
				.startAngle(function(d){ return d.startAngle; })
				.endAngle(function(d){ return d.endAngle; })
				.innerRadius(this.ir)
				.outerRadius(this.r);

			//create visuals
			var pieId = (this.homegrownparent);

			var vis = d3.select("#"+pieId).append("svg:svg")
				.attr("width", w)
				.attr("height", h);

			//group for arcs
			this.arc_group = vis.append("svg:g")
				.attr("class", "arc")
				.attr("transform", "translate(" + (w/2) + "," + (h/2) + ")");

			//group for labels
			this.label_group = vis.append("svg:g")
				.attr("class", "label_group")
				.attr("transform", "translate(" + (w/2) + "," + (h/2) + ")");

			//group for text
			var center_group = vis.append("svg:g")
				.attr("class", "center_group")
				.attr("transform", "translate(" + (w/2) + "," + ((h/2)-10) + ")");

			//place holder circle
			var paths = this.arc_group.append("svg:circle")
				.attr("fill", "#EFEFEF")
				.attr("r", this.r);

			//white circles behind labels
			var whiteCircle = center_group.append("svg:circle")
				.attr("fill", "white")
				.attr("r", this.ir);

			//generate total label
			var totalLabel = center_group.append("svg:text")
				.attr("class", "label")
				.attr("dy", 25)
				.attr("text-anchor", "middle");

			//total value
			this.totalValue = center_group.append("svg:text")
				.attr("class", "total")
				.attr("dy", 7)
				.attr("text-anchor", "middle")
				.text("Waiting...");

			pieCreatedCallback();
		},

		bindEvent: function(){
			var that = this;

			//binding any events

			/*$(window).on("click", function() {

				//update json

				that.update();

			});*/
		},

		totalOctets:0,

		tweenDuration:250,

		update: function(obj, options){
			//console.log("going for pie");
			this.futurePieData = options;
			this.newPieData = this.getNewData();
			this.currentPieData = this.newPieData;
			this.setMaxObj();
			this.homegrownparent = obj.id;
			this.totalOctets = 0;
			this.updatePie();
		},

		filteredPieData: [],

		streakerDataAdded: "",

		getNewData: function(){

			return this.futurePieData;

		},

		removePieTween: function(d, i) {
			var that = this;
			s0 = 2 * Math.PI;
			e0 = 2 * Math.PI;
			var i = d3.interpolate({startAngle: d.startAngle, endAngle: d.endAngle}, {startAngle: s0, endAngle: e0});

			return function(t) {
				var b = i(t);
				return that.arc(b);
			};
		},

		pieTween: function(d){
			var theOldDataInPie = methods.oldPieData;
			// Interpolate the arcs in data space

			var s0;
			var e0;

			if(theOldDataInPie[i]){
				s0 = theOldDataInPie[i].startAngle;
				e0 = theOldDataInPie[i].endAngle;
			} else if (!(theOldDataInPie[i]) && theOldDataInPie[i-1]) {
				s0 = theOldDataInPie[i-1].endAngle;
				e0 = theOldDataInPie[i-1].endAngle;
			} else if(!(theOldDataInPie[i-1]) && theOldDataInPie.length > 0){
				s0 = theOldDataInPie[theOldDataInPie.length-1].endAngle;
				e0 = theOldDataInPie[theOldDataInPie.length-1].endAngle;
			} else {
				s0 = 0;
				e0 = 0;
			}

			var i = d3.interpolate({startAngle: s0, endAngle: e0}, {startAngle: d.startAngle, endAngle: d.endAngle});

			return function(t) {
				var b = i(t);
				return methods.arc(b);
			};
		},

		textTween: function(d, i) {

		var theOldDataInPie = methods.oldPieData;

		var a;

		if(theOldDataInPie[i]){
				a = (theOldDataInPie[i].startAngle + theOldDataInPie[i].endAngle - Math.PI)/2;
			} else if (!(theOldDataInPie[i]) && theOldDataInPie[i-1]) {
				a = (theOldDataInPie[i-1].startAngle + theOldDataInPie[i-1].endAngle - Math.PI)/2;
			} else if(!(theOldDataInPie[i-1]) && theOldDataInPie.length > 0) {
				a = (theOldDataInPie[theOldDataInPie.length-1].startAngle + theOldDataInPie[theOldDataInPie.length-1].endAngle - Math.PI)/2;
			} else {
				a = 0;
			}

			var b = (d.startAngle + d.endAngle - Math.PI)/2;
			var fn = d3.interpolateNumber(a, b);

			return function(t) {
				var val = fn(t);
				return "translate(" + Math.cos(val) * (methods.r+methods.textOffset) + "," + Math.sin(val) * (methods.r+methods.textOffset) + ")";
			};
		},

		filterData: function(element, index, array){
			element.name = methods.streakerDataAdded[index].title;
			element.value = methods.streakerDataAdded[index].octetTotalCount;
			methods.totalOctets += element.value;
			return (element.value > 0);
		},

		updatePie: function(){
			this.streakerDataAdded = this.newPieData;
			this.oldPieData = this.filteredPieData;
			this.newPieData = this.donut(this.streakerDataAdded);
			this.filteredPieData = this.newPieData.filter(this.filterData);
			var filteredData = this.filteredPieData;

			//only update if there is valid data
			if(filteredData.length > 0 && this.oldPieData.length > 0){
				this.animatePie(filteredData);
			}
		},

		animatePie: function(filteredData){
			var that = this;

			//remove placeholder
			var pieId = (this.homegrownparent);
			var vis = d3.select("#"+pieId);

			vis.selectAll("circle").remove();

			var totalValue = d3.select('#'+pieId+' .total');
			totalValue.text(function(){
				var maxPercentage = that.maxObj.octetTotalCount/that.totalOctets*100;
				$('#'+pieId+' .label').text(that.maxObj.title);
				return maxPercentage.toFixed(1)+'%';
			});

			//draw arc paths
			var arc_group = d3.select('#'+pieId+' .arc');
			paths = arc_group.selectAll("path").data(filteredData);
			paths.enter().append("svg:path")
				.attr("stroke", "white")
				.attr("stroke-width", 0.5)
				.attr("fill", function(d, i) { return that.color(i); })
				.transition()
				.duration(this.tweenDuration)
				.attrTween("d", this.pieTween);

			paths
				.transition()
				.duration(this.tweenDuration)
				.attrTween("d", this.pieTween);

			paths.exit()
				.transition()
				.duration(this.tweenDuration)
				.attrTween("d", this.removePieTween)
				.remove();

			//draw tick marks
			var label_group = d3.select('#'+pieId+' .label_group');
			lines = label_group.selectAll("line").data(filteredData);
			lines.enter().append("svg:line")
				.attr("x1", 0)
				.attr("x2", 0)
				.attr("y1", -this.r-3)
				.attr("y2", -this.r-8)
				.attr("stroke", "gray")
				.attr("transform", function(d) {
					return "rotate(" + (d.startAngle+d.endAngle)/2 * (180/Math.PI) + ")";
				});

			lines.transition()
				.duration(this.tweenDuration)
				.attr("transform", function(d) {
					return "rotate(" + (d.startAngle+d.endAngle)/2 * (180/Math.PI) + ")";
				});

			lines.exit().remove();

			//draw labels
			valueLabels = label_group.selectAll("text.value").data(filteredData)
				.attr("dy", function(d){
					if ((d.startAngle+d.endAngle)/2 > Math.PI/2 && (d.startAngle+d.endAngle)/2 < Math.PI*1.5 ) {
						return 5;
					} else {
						return -7;
					}
				})
				.attr("text-anchor", function(d){
					if ( (d.startAngle+d.endAngle)/2 < Math.PI ){
						return "beginning";
					} else {
						return "end";
					}

				})
				.text(function(d){
					var percentage = (d.value/that.totalOctets)*100;
				});


			valueLabels.enter().append("svg:text")
				.attr("class", "value")
				.attr("transform", function(d) {
					return "translate(" + Math.cos(((d.startAngle+d.endAngle - Math.PI)/2)) * (this.r+this.textOffset) + "," + Math.sin((d.startAngle+d.endAngle - Math.PI)/2) * (this.r+this.textOffset) + ")";
				})
				.attr("dy", function(d){
					if ((d.startAngle+d.endAngle)/2 > Math.PI/2 && (d.startAngle+d.endAngle)/2 < Math.PI*1.5 ) {
						return 5;
					} else {
						return -7;
					}
				})
				.attr("text-anchor", function(d){
					if ( (d.startAngle+d.endAngle)/2 < Math.PI ){
						return "beginning";
					} else {
						return "end";
					}
				}).text(function(d){
					var percentage = (d.value/that.totalOctets)*100;
				});

			valueLabels.transition().duration(this.tweenDuration).attrTween("transform", this.textTween);

			valueLabels.exit().remove();

			//draw labels with entity names
			nameLabels = label_group.selectAll("text.units").data(filteredData)
				.attr("dy", function(d){
					if ((d.startAngle+d.endAngle)/2 > Math.PI/2 && (d.startAngle+d.endAngle)/2 < Math.PI*1.5 ) {
						return 17;
					} else {
						return 5;
					}
				})
				.attr("text-anchor", function(d){
					if ((d.startAngle+d.endAngle)/2 < Math.PI ) {
						return "beginning";
					} else {
						return "end";
					}
				}).text(function(d){
					return d.name;
				});

			nameLabels.enter().append("svg:text")
				.attr("class", "units")
				.attr("transform", function(d) {
					return "translate(" + Math.cos(((d.startAngle+d.endAngle - Math.PI)/2)) * (this.r+this.textOffset) + "," + Math.sin((d.startAngle+d.endAngle - Math.PI)/2) * (this.r+this.textOffset) + ")";
				})
				.attr("dy", function(d){
					if ((d.startAngle+d.endAngle)/2 > Math.PI/2 && (d.startAngle+d.endAngle)/2 < Math.PI*1.5 ) {
						return 17;
					} else {
						return 5;
					}
				})
				.attr("text-anchor", function(d){
					if ((d.startAngle+d.endAngle)/2 < Math.PI ) {
						return "beginning";
					} else {
						return "end";
					}
				}).text(function(d){
					return d.name;
				});

			nameLabels.transition().duration(this.tweenDuration).attrTween("transform", this.textTween);
			nameLabels.exit().remove();
			this.currentPieData = this.newPieData;// the new data is now current data
		}
	};

	$.fn.doughnutPie = function() {
		var method = arguments[0];

		if(methods[method]) {
			method = methods[method];
			arguments = Array.prototype.slice.call(arguments, 1);
		} else if( typeof(method) == 'object' || !method ) {
			method = methods.init;
		} else {
			$.error( 'Method ' +  method + ' does not exist on jQuery.doughnutPie' );
			return this;
		}

		return method.apply(this, arguments);
	}

})(jQuery);



var goPie = {
	initChart: function(pieElement, specs){
		var dummyJson = [
							{
								title: "",
								octetTotalCount: Math.ceil(Math.random()*(100000))
							}
						];

					//console.log("specs", specs);

					var colorPalette = specs.color;
					var w = specs.w;
					var h = specs.h;
					var r = specs.r;
					var ir = specs.ir;

					switch(colorPalette)
					{
						case "spectral":
							var colors = [
											{
												name:"Spectral",
												col: 8,
												domain: ["foo", "bar", "baz", "gaz", "bar", "baz", "gaz"]
											}
										];
						  break;
						case "greys":
							var colors = [
											{
												name:"Greys",
												col: 8,
												domain: ["foo", "bar", "baz", "gaz", "bar", "baz", "gaz"]
											}
										];
						  break;
						case "brightOrange":
							var colors = [
											{
												name:"Oranges",
												col: 8,
												domain: ["foo", "bar", "baz", "gaz", "bar", "baz", "gaz"]
											}
										];
						  break;
						default:
							var colors = [
											{
												name:"OrRd",
												col: 8,
												domain: ["foo", "bar", "baz", "gaz", "bar", "baz", "gaz"]
											}
										];
					}


		var objs = {
						"data": dummyJson,
						"specs": {
							"colors": colors,
							"w": w,
							"h": h,
							"r": r,
							"ir": ir
						}
					};

		$(pieElement).doughnutPie('init', objs);
		this.updateCharts(pieElement, dummyJson);
	},
	updateCharts: function(pieElement, dynamicData){
		$(pieElement).doughnutPie('transitions', dynamicData); // Pass object "{}" to "init" as arguments
	},
	chosenChart: function(jsonUrl, colorCode, holder){

		$.getJSON(jsonUrl, function(data){
			var dataPieJson = [];
			var dataArray = data[0].dataResults;
			$.each(dataArray, function(key, val) {
				var tempObj = {
							title: key,
							octetTotalCount: val
							};

				dataPieJson.push(tempObj);
			});

			var specs = {
							color : colorCode,
							w : 300,
							h : 240,
							r: 80,
							ir: 45
						};

			goPie.initChart(holder, specs);
			goPie.updateCharts(holder, dataPieJson);
		});
	},
	chosenUpdateChart: function(jsonUrl, holder){
		//console.log("chosenUpdateChart");

		$.getJSON(jsonUrl, function(data){
			var dataPieJson = [];
			if(data[0].response == "OK"){
				var dataArray = data[0].dataResults;

				$.each(dataArray, function(key, val) {
					var tempObj = {
								title: key,
								octetTotalCount: val
								};

					dataPieJson.push(tempObj);
				});
				goPie.updateCharts(holder, dataPieJson);
			}
		});
	}
};
