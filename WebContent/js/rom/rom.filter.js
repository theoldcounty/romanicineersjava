/**
	* @class Romance Isotope
	* @description Isotope filtering and sorting
*/

var isotopefilters = Backbone.View.extend({
	initialize : function() {
		this.bindNav();
		this.generateFilters();

		var $container = $('.users'),
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
	generateFilters: function(){

		var filter = {
			lastFilter:"",
			isotopeTime: function(obj){
				$('.users').isotope(obj);
			},
			populate: function(filtertype){

				switch(filtertype)
				{
					case "location":
							var data = ["United Kingdom", "Japan", "Spain", "France"];

						break;
					case "interest":
							var data = ["Cinema", "Sex", "Movies", "Football", "Robots", "Spaceflight"];
						break;
				}

				var html = "";
				$.each(data, function(i, item) {
					html+='<option value="'+data[i]+'">'+data[i]+'</option>';
				});

				var inputType = '<select name="options">'+html+'</select>';

				var data = '<div class="'+filtertype+' badge"></div><div class="attributes"><label>'+filtertype+'</label>'+inputType+'</div>';
				$('#filters .optional .wrappers').html('<form id="optional">'+data+'<input type="submit" name="submit" value="Apply" /></form>');
			},
			fadeInOptionalPane: function(filtertype){
				filter.lastFilter = filtertype;
				filter.populate(filtertype);
				$('#filters ul.initial').fadeOut(300, function(){
						$('#filters .optional').fadeIn(300);
				});
			},
			fadeOutOptionalPane: function(){
				$('#filters .optional').fadeOut(300, function(){
						$('#filters ul.initial').fadeIn(300);
				});
			}
		};

		$(document).on("submit", "form#optional", function(event){
			event.preventDefault();
			var results = $(this).serializeArray();
			var filterChoice = results[0].value;

			switch(filter.lastFilter){
				case "location":
						filter.isotopeTime({ filter: $('.element:country('+filterChoice+')') });

					break;
				case "interest":
						filter.isotopeTime({ filter: $('.element:interest('+filterChoice+')') });
					break;
			}
		});

		$("#filters .optional .close a").click(function(event) {
			event.preventDefault();
			filter.fadeOutOptionalPane();
		});

		$("#filters li a").click(function() {
			var type = $(this).parent().attr("class");

			switch(type)
			{
				case "location":
						filter.fadeInOptionalPane('location');
					break;
				case "interest":
						filter.fadeInOptionalPane('interest');
					break;
				case "showall":
						filter.isotopeTime({ filter: $('.element*') });
					break;
				case "female":
						filter.isotopeTime({ filter: $('.element:gender(Female)') });
					break;
				case "male":
						filter.isotopeTime({ filter: $('.element:gender(Male)') });
					break;
				case "shuffle":
						filter.isotopeTime('shuffle');
					break;
			}
		});
	},
	bindNav: function(){
		var nav = {
			lastPane: "",
			selectedPane: "",
			isOpen: false,
			toggleDuration: 550,
			fadeDuration: 150,
			togglePanel: function(paneToToggle){
				if(!nav.isOpen){
					$('#'+paneToToggle).slideDown(nav.toggleDuration, function(){
						console.log("slide down filters");
						nav.isOpen = true;
					});
				}else{
					$('#'+paneToToggle).slideUp(nav.toggleDuration, function(){
						console.log("slide up filters");
						nav.isOpen = false;
					});
				}
			},
			fadePanel: function(lastPanel, newPanel){
				$('#'+lastPanel).fadeOut(nav.fadeDuration, function(){
						$('#'+newPanel).fadeIn(nav.fadeDuration);
				});
			}
		};

		$("#calltoaction li a").click(function() {
			nav.selectedPane = $(this).parent().attr("class");

			/*choose selected tab*/
			$("#calltoaction li a").removeClass();
			$(this).addClass("selected");

			/*if navigation selected - slide down
			- if the pane is open - and another filter chosen - fade out and fade in replacement.*/

			if(nav.selectedPane == "filters" || nav.selectedPane == "search"){
				var toFade = false;
				if(nav.lastPane != nav.selectedPane){
					toFade = true;
				}

				if(nav.lastPane == ""){
					toFade = false;
				}

				if(toFade){
					//fade in pane
					console.log("fade in panel");
					nav.fadePanel(nav.lastPane, nav.selectedPane);
				}else{
					//toggle pane
					console.log("toggle in panel");
					nav.togglePanel(nav.selectedPane);
				}

				nav.lastPane = nav.selectedPane;
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
	interest: function(elm, i, match) {
		var response = false;
		var data = $(elm).data("user-interests").toLowerCase().split(",");
		$.each(data, function(i, item) {
			if(utils.trimVariable(data[i]) == match[3].toLowerCase()){
				response = true;
			}
		});

		return response;
	},
	online: function(elm, i, match){
		return true == $(elm).data("user-online");
	}
});
