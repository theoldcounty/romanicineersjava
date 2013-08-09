/*
*	Isotope Filter
*
*	Author: Rob Shan Lone
*	Copyright (c) 2013 The Old County Limited.
*
*	All rights reserved.
*/

/**
	* @class Romance Isotope
	* @description Isotope filtering and sorting
*/
var hasFiltered = false;

var isotopeFilters = Backbone.View.extend({
	initialize : function() {
		this.bindNav();
		this.generateFilters();

		var $container = $('[data-filter-users="true"]'),
			// object that will keep track of options
			isotopeOptions = {},
			// defaults, used if not explicitly set in hash
			defaultOptions = {
				filter: '*',
				sortBy: 'original-order',
				sortAscending: true,
				layoutMode: 'masonry'
			};

		// ensure no transforms used in Opera
		if ( $.browser.opera ) {
			defaultOptions.transformsEnabled = false;
		}

		var setupOptions = $.extend( {}, defaultOptions, {
			itemSelector : '.element',
			getSortData : {
				name : function ( $elem ) {
					return $elem.find('.name').text();
				}
			}
		});

		// set up Isotope
		$container.isotope( setupOptions );

		var $optionSets = $('#options').find('.option-set'),
		isOptionLinkClicked = false;

		// switches selected class on buttons
		function changeSelectedLink( $elem ) {
			// remove selected class on previous item
			$elem.parents('.option-set').find('.selected').removeClass('selected');
			// set selected class on new item
			$elem.addClass('selected');
		}

		$optionSets.find('a').click(function(){
			var $this = $(this);
			// don't proceed if already selected
			if ( $this.hasClass('selected') ) {
				return;
			}
			changeSelectedLink( $this );
				// get href attr, remove leading #
				var href = $this.attr('href').replace( /^#/, '' ),
				// convert href into object
				// i.e. 'filter=.inner-transition' -> { filter: '.inner-transition' }
				option = $.deparam( href, true );
			// apply new option to previous
			$.extend(isotopeOptions, option);
			// set hash, triggers hashchange on window
			$.bbq.pushState(isotopeOptions);
			isOptionLinkClicked = true;
			return false;
		});

		var hashChanged = false;

		$(window).bind( 'hashchange', function( event ){
		// get options object from hash
		var hashOptions = window.location.hash ? $.deparam.fragment(window.location.hash, true) : {},
			// do not animate first call
			aniEngine = hashChanged ? 'best-available' : 'none',
			// apply defaults where no option was specified
			options = $.extend( {}, defaultOptions, hashOptions, {animationEngine: aniEngine});
		// apply options from hash
		$container.isotope( options );
		// save options
		isotopeOptions = hashOptions;

		// if option link was not clicked
		// then we'll need to update selected links
		if(!isOptionLinkClicked){
			// iterate over options
			var hrefObj, hrefValue, $selectedLink;
			for ( var key in options ) {
				hrefObj = {};
				hrefObj[ key ] = options[ key ];
				// convert object into parameter string
				// i.e. { filter: '.inner-transition' } -> 'filter=.inner-transition'
				hrefValue = $.param( hrefObj );
				// get matching link
				$selectedLink = $optionSets.find('a[href="#' + hrefValue + '"]');
				changeSelectedLink( $selectedLink );
			}
		}
			isOptionLinkClicked = false;
			hashChanged = true;
		})
		.trigger('hashchange');// trigger hashchange to capture any hash data on init
	},
	trimVariable: function(variable){
		return variable.replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g,'').replace(/\s+/g,' ');
	},
	generateFilters: function(){
		var that = this;
		var filter = {
			isotopeTime: function(obj){
				$('[data-filter-users="true"]').isotope(obj);
			}
		};

		$('#filterisotope').submit(function(e) {
			e.preventDefault();

			var combinedFilterObj = '.element';


			//interest selection

			var interests = $('[name=interest]').val();
			if(interests.length > 0){
				combinedFilterObj+= ':interest('+interests+')';
			}


			//age range combo
			var agerange = $('#amount').val().split("-");
			combinedFilterObj+= ':agerange('+that.trimVariable(agerange[0])+','+that.trimVariable(agerange[1])+')';



			//country selection
			var location = $('[name=location]').val();
			if(location.length > 0){
				combinedFilterObj+= ':country('+location+')';
			}


			//ethnicity
			var ethnicity = $('[name=ethnicity]').val();
			if(ethnicity.length > 0){
				combinedFilterObj+= ':ethnicity('+ethnicity+')';
			}


			//haspictures
			var haspictures = $('[name=withpics]:checked').val();
			if(haspictures != undefined){
				combinedFilterObj+= ':haspictures('+haspictures+')';
			}


			//isonline
			var isonline = $('[name=isonline]:checked').val();
			if(isonline != undefined){
				combinedFilterObj+= ':isonline('+isonline+')';
			}


			var genderCount = $('[name=gender]:checked').length;

			if(genderCount == 1){
				//if just one gender selected

				combinedFilterObj+= ':gender('+$('[name=gender]:checked').val()+')';
			}
			else{
				//if more than one gender selected

				var combinedString = '';

				$('[name=gender]:checked').each(function( index ) {
					var copyOfFilter = combinedFilterObj;
					copyOfFilter+= ':gender('+$(this).val()+')';

					combinedString+= copyOfFilter+',';
				});

				combinedFilterObj = combinedString.substring(0, combinedString.length - 1);

			}

			//console.log("combinedFilterObj:: ", combinedFilterObj);

			filter.isotopeTime({ filter: $(combinedFilterObj) });
		});



		$("a.directisotope").click(function() {
			$("#filters li a").removeClass("selected");
			$(this).addClass("selected");

			var type = $(this).parent().attr("class");

			switch(type)
			{
				case "reset":
						filter.isotopeTime({ filter: $('.element*') });
						filter.isotopeTime({ sortBy : 'original-order' });
					break;
				case "shuffle":
						filter.isotopeTime('shuffle');
					break;
			}
		});
	},
	bindNav: function(){
		var nav = {
			//lastPane: "",
			selectedPane: "",
			isOpen: false,
			toggleDuration: 550,
			fadeDuration: 150,
			togglePanel: function(paneToToggle){
				if(!nav.isOpen){
					//$('.user_container').css("opacity", 0);
					$('#'+paneToToggle).slideDown(nav.toggleDuration, function(){
						console.log("slide down filters");
						$("#filters li.reset a").click();

						$('#user_handler_filter').slideDown(nav.toggleDuration, function(){
							//show pane

							gridUserHandler.filteruserscrollenhance();
						});

						//window.setTimeout(function(){
							//$('.user_container').css("opacity", 1);
						//},1000);

						nav.isOpen = true;
					});
				}else{
					//$('.user_container').css("opacity", 0);

						$('#user_handler_filter').slideUp(nav.toggleDuration, function(){
							//hide pane


							$('#'+paneToToggle).slideUp(nav.toggleDuration, function(){
								console.log("slide up filters");
								nav.isOpen = false;
							});
						});

				}
			}
		};


		$("#calltoaction li a").click(function() {
			nav.selectedPane = $(this).parent().attr("class");

			$("#calltoaction li a").removeClass();
			$(this).addClass("selected");


			if(nav.selectedPane == "filters"){
				nav.togglePanel("filterspane");
			}
		});
	}
});

$.extend($.expr[':'], {
	gender: function(elm, i, match) {
		return $(elm).data("user-gender").toLowerCase() == match[3].toLowerCase();
	},
	country: function(elm, i, match) {
		return $(elm).data("user-country").toLowerCase() == match[3].toLowerCase();
	},
	ethnicity: function(elm, i, match) {
		return $(elm).data("user-ethnicity") == match[3];
	},
	interest: function(elm, i, match) {
		var response = false;
		var data = $(elm).data("user-interests").toLowerCase().split(",");
		$.each(data, function(i, item) {
			if(romUtils.trimVariable(data[i]) == match[3].toLowerCase()){
				response = true;
			}
		});

		return response;
	},
	agerange: function(elm, i, match) {
		var userAge = $(elm).data("user-age");
		var ranges = match[3].split(",");
		var valid = false;

		if(userAge >= ranges[0] && userAge <= ranges[1]){
			valid = true;
		}

		return valid;
	},
	haspictures: function(elm, i, match){
		return true == $(elm).data("user-pictures");
	},
	isonline: function(elm, i, match){
		return true == $(elm).data("user-online");
	}
});
