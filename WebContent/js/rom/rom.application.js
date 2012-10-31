


$(document).ready(function() {

	console.log("adding colorbox");
	$(".group").colorbox({
		rel:'group',
		width:"65%",/*
		height:"75%",*/
		onOpen:function(){
			//alert('onOpen: colorbox is about to open');
		},
		onLoad:function(){
			//alert('onLoad: colorbox has started to load the targeted content');
		},
		onComplete:function(){
			//alert('onComplete: colorbox has displayed the loaded content');
		},
		onCleanup:function(){
			//alert('onCleanup: colorbox has begun the close process');
		},
		onClosed:function(){
			//alert('onClosed: colorbox has completely closed');
		}
	});


	$(".form").colorbox({
		width:"65%",/*
		height:"75%",*/
		onOpen:function(){
			//alert('onOpen: colorbox is about to open');
		},
		onLoad:function(){
			//alert('onLoad: colorbox has started to load the targeted content');
		},
		onComplete:function(){
			//alert('onComplete: colorbox has displayed the loaded content');
		},
		onCleanup:function(){
			//alert('onCleanup: colorbox has begun the close process');
		},
		onClosed:function(){
			//alert('onClosed: colorbox has completely closed');
		}
	});

	console.log("make a stand");
	var searchFilter = new searchFilters();

	//initialize home grown carousel
	$('#galleryCarousel').homegrowncarousel();


	// Initialize Backbone views.
	console.log("setting slider backbone.");
	console.log("slider-controls-nav", $('[data-role="slider-controls-nav"]'));


	$('[data-role="slider-controls-nav"]').each(function() {
		new sliderControls({el: $(this)});
	});



		var sliderpreviews = [
								{
									"negative": "Reserved",
									"positive": "Outgoing",
									"value": 53
								},
								{
									"negative": "Direct",
									"positive": "Flexible",
									"value": 23
								},
								{
									"negative": "Senstitive",
									"positive": "Steady",
									"value": 4
								},
								{
									"negative": "Consitent",
									"positive": "Curious",
									"value": 13
								},
								{
									"negative": "Couple",
									"positive": "Family",
									"value": 89
								},
								{
									"negative": "Tender",
									"positive": "Passionate",
									"value": 56
								},
								{
									"negative": "Focus",
									"positive": "Funny",
									"value": 78
								}

							];

	  $('#sliderResults').sliderPreviews('init', sliderpreviews);







	// Initialize Backbone views.
	var usertip = new userTip();



	var isoFilter= new isotopefilters();
	//var getMembers= new fetchMembers();

	/*get feed interests*/
	$.getJSON(utils.feedInterests, function(data) {
		console.log("feed interests",data);
	});

	/*get feed location*/
	$.getJSON(utils.feedLocations, function(data) {
		console.log("feed location",data);
	});

	//responsive development
	utils.init();
 });

//sort by age
//sort by wealth
//sort by ethnicity


$(window).resize(function() {
	utils.resize();
});

	var interestPieJson = [
						{
							title: "Cinema",
							octetTotalCount: Math.ceil(Math.random()*(100000))
						},
						{
							title: "Sex",
							octetTotalCount: Math.ceil(Math.random()*(100000))
						},
						{
							title: "Football",
							octetTotalCount: Math.ceil(Math.random()*(100000))
						},
						{
							title: "Music",
							octetTotalCount: Math.ceil(Math.random()*(100000))
						}
					];

	var futureFoodPieJson = [
						{
							title: "Pancakes",
							octetTotalCount: Math.ceil(Math.random()*(100000))
						},
						{
							title: "Crossant",
							octetTotalCount: Math.ceil(Math.random()*(100000))
						},
						{
							title: "Indian Curry",
							octetTotalCount: Math.ceil(Math.random()*(100000))
						},
						{
							title: "Pizza",
							octetTotalCount: Math.ceil(Math.random()*(100000))
						},
						{
							title: "Chinese",
							octetTotalCount: Math.ceil(Math.random()*(100000))
						}
					];

	var seekingPieJson = [
					{
						title: "Dating",
						octetTotalCount: Math.ceil(Math.random()*(100000))
					},
					{
						title: "Sex",
						octetTotalCount: Math.ceil(Math.random()*(100000))
					},
					{
						title: "Clubbing",
						octetTotalCount: Math.ceil(Math.random()*(100000))
					}
				];

	$(document).ready(function() {
	  // Handler for .ready() called.

		var specs = {
						color : 'spectral',
						w : 300,
						h : 240,
						r: 80,
						ir: 45
					}

		goPie.initChart('#interestsPie', specs);
		goPie.updateCharts('#interestsPie', interestPieJson);


		var specs = {
						color : 'brightOrange',
						w : 300,
						h : 240,
						r: 80,
						ir: 45
					}

		goPie.initChart('#seekingPie', specs);
		goPie.updateCharts('#seekingPie', futureFoodPieJson);
	});


	$(window).on("click", function() {

		var interestPieJson = [
							{
								title: "Cinema",
								octetTotalCount: Math.ceil(Math.random()*(100000))
							},
							{
								title: "Sex",
								octetTotalCount: Math.ceil(Math.random()*(100000))
							},
							{
								title: "Football",
								octetTotalCount: Math.ceil(Math.random()*(100000))
							},
							{
								title: "Music",
								octetTotalCount: Math.ceil(Math.random()*(100000))
							}
						];

		goPie.updateCharts('#interestsPie', interestPieJson);


		var futureFoodPieJson = [
							{
								title: "Pancakes",
								octetTotalCount: Math.ceil(Math.random()*(100000))
							},
							{
								title: "Crossant",
								octetTotalCount: Math.ceil(Math.random()*(100000))
							},
							{
								title: "Indian Curry",
								octetTotalCount: Math.ceil(Math.random()*(100000))
							},
							{
								title: "Pizza",
								octetTotalCount: Math.ceil(Math.random()*(100000))
							},
							{
								title: "Chinese",
								octetTotalCount: Math.ceil(Math.random()*(100000))
							}
						];

		goPie.updateCharts('#seekingPie', futureFoodPieJson);
	});



	var visitJson= {
					 "name": "flare",
					 "children": [
							{
								"name": "analytics",
								"children": [
									{
										"name": "rgba(0,0,0,0.2)",
										"children": [
											{
												"name": "Greece",
												"size": 3938
											}
										]
									},
									{
										"name": "rgba(230,0,0,0.2)",
										"children": [
											{
												"name": "Italy",
												"size": 3534
											}
										]
									},
									{
										"name": "rgba(212, 222, 22, 0.2)",
										"children": [
											{
												"name": "Canada",
												"size": 1074
											}
										]
									},
									{
										"name": "rgba(230,0,0,0.2)",
										"children": [
											{
												"name": "Australia",
												"size": 1134
											}
										]
									},
									{
										"name": "rgba(212, 222, 22, 0.2)",
										"children": [
											{
												"name": "Russia",
												"size": 3274
											}
										]
									}
								]
							}
						]
					};

	$(document).ready(function() {
	  // Handler for .ready() called.
	  $('#quizchart').bubbleChart('init', visitJson);
	});
