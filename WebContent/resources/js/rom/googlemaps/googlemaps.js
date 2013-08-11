var GoogleMaps = Backbone.View.extend({
	events: {
		"click [data-id='reset-button']": "onResetButtonClick",
		"click [data-id='search-button']": "onSearchButtonClick",
		"click '.link-locate'": "onLocateButtonClick"
	},

	actionUrl: '',
	$searchForm: null,
	$searchTextfield: null,
	$queryFoursquarefield: null,
	$searchButton: null,
	map: null,
	autocomplete: null,
	geocoder: null,
	venueOverlays: [],
	currentClickedMarker: null,
	prevClickedMarker: null,
	filters: null,
	$defaultInputFields: {},

	options: {
		zoomLevel: 16,
		defaultCountry: new google.maps.LatLng(51.524846, -0.127459) //london
		//defaultCountry: new google.maps.LatLng(51, 9) //germany
	},

	autocompleteOptions: {
		types: ['geocode'],
		componentRestrictions: {}//country: 'DE'
	},

	googleMapOptions: {
		zoom: 6,
		panControl: false,
		mapTypeControl: false,
		streetViewControl: false,
		mapTypeId: google.maps.MapTypeId.ROADMAP,
		zoomControlOptions: {
			style: google.maps.ZoomControlStyle.LARGE,
			position: google.maps.ControlPosition.RIGHT_CENTER
		},
		styles: [
			{
				featureType: "poi.attraction",
				stylers: [
					{ visibility: "off" }
				]
			},{
				featureType: "poi.business",
				stylers: [
					{ visibility: "off" }
				]
			},{
				featureType: "poi.government",
				stylers: [
					{ visibility: "off" }
				]
			},{
				featureType: "poi.place_of_worship",
				stylers: [
					{ visibility: "simplified" }
				]
			},{
				featureType: "poi.sports_complex",
				stylers: [
					{ visibility: "simplified" }
				]
			}
		]
	},

	setUsers: function(){
		//set user
		this.setUserMarker($('#userData').data('user-lat'), $('#userData').data('user-long'));

		//set potetial candiate
		this.setUserMarker($('#candidateData').data('candidate-lat'), $('#candidateData').data('candidate-long'));
	},


	setUserMarker: function(latitute, longitute){
		//console.log("latitute", latitute);
		//console.log("longitute", longitute);

		//console.log("this.map", this.map);

		var image = 'resources/img/usermarker.png';
		var myLatLng = new google.maps.LatLng(latitute, longitute);

		var marker = new google.maps.Marker({
			position: myLatLng,
			map: this.map,
			icon: image
		});
	},

	initialize: function() {

		console.log("GoogleMaps");

		this.$searchForm = this.$el.find("#searchForm");
		this.$searchTextfield = this.$searchForm.find(".search-textfield");

		this.$queryFoursquarefield = this.$searchForm.find("#queryFoursquare");

		this.clearSearchTextfield();
		$searchButton = this.$searchForm.find("input[type='button']");

		//this.actionUrl = this.$searchForm.attr('action');
		//this.actionUrl = "temp/json/searchGoogleMaps.json";

		this.$defaultInputFields = {
			latitude: this.$el.find('[name=latitude]'),
			longitude: this.$el.find('[name=longitude]'),
			searchField: this.$el.find('[name=searchfield]')
		};

		//if browser supports geolocation show location button
		if(Modernizr.geolocation) {
			this.showLocateButton();
		}

		this.createGoogleMap();
		this.tryGeoLocation();
		this.bindEvents();
		this.setUsers();
	},

	showLocateButton: function() {
		this.$el.find(".link-locate").css("display", "block");
	},

	createGoogleMap: function() {
		this.map = new google.maps.Map(document.getElementById("map_canvas"), this.googleMapOptions);
		this.geocoder = new google.maps.Geocoder();
		//autocompletion drop down
		this.autocomplete = new google.maps.places.Autocomplete(this.$searchTextfield[0], this.autocompleteOptions);
	},

	autoCenterMapFocus: function(latitude, longitude){
		var self = this;

		initialLocation = new google.maps.LatLng(latitude, longitude);
		self.map.setZoom(self.options.zoomLevel);
		self.map.setCenter(initialLocation);

		self.sendSearchRequestToService();
	},

	bindEvents: function() {
		var self = this;

		this.$searchTextfield.bind("keypress", _.bind(this.onEnterInSearchTextfield, this));
		google.maps.event.addListener(this.autocomplete, 'place_changed', _.bind(this.onAutoCompletePlaceChange, this));
		//$(document).bind(VenueFilter.updateFilterEvent, _.bind(this.onFilterUpdate, this));

		$("#candidateData").on("click", function(event){
			event.preventDefault();
			//get data and switch the attention to this user.
			//console.log("get data on candidate");
			//set potetial candiate
			self.autoCenterMapFocus($('#candidateData').data('candidate-lat'), $('#candidateData').data('candidate-long'));
		});

		$("#userData").on("click", function(event){
			event.preventDefault();
			//get data and switch the attention to this user.
			//console.log("get data on user");
			//set user
			self.autoCenterMapFocus($('#userData').data('user-lat'), $('#userData').data('user-long'));
		});
	},

	onFilterUpdate: function(event, filters) {
		this.filters = filters;
		this.sendSearchRequestToService();
	},

	tryGeoLocation: function() {
		var self = this;
		//test for geolocation if it is available and user agrees then use geolocation otherwise set default value
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
				initialLocation = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
				self.map.setZoom(self.options.zoomLevel);
				self.map.setCenter(initialLocation);
				self.setData(position.coords.latitude, position.coords.longitude);
				self.sendSearchRequestToService();
			}, this.handleNoGeolocation(), {enableHighAccuracy: true, maximumAge: 1000});
		} else {
			this.handleNoGeolocation();
		}
	},

	handleNoGeolocation: function() {
		//google map has to be set to center once
		if(this.map.getCenter() == undefined) {
			this.map.setCenter(this.options.defaultCountry);
		}
	},

	onEnterInSearchTextfield: function(event) {
		//listen to key ENTER and trigger click to search for place
		if(event.which == 13 && this.getSearchTextValue() != "") {
			event.preventDefault();
			$searchButton.trigger("click");
		} else if (event.which == 13 && this.getSearchTextValue() == "") {
			event.preventDefault();
		}
	},

	onSearchButtonClick: function(event) {
		event.preventDefault();
		if(this.getSearchTextValue() != "") {
			this.geocoder.geocode({
				'address': this.getSearchTextValue(),
				'region': 'de'
			}, _.bind(this.onGeocodeResponse, this));
		}
	},

	onGeocodeResponse: function(results, status) {
		if(status == google.maps.GeocoderStatus.OK) {
			var formattedAddress = results[0].formatted_address;
			//controls whether search result contains germany
			if(formattedAddress.indexOf("Germany") != -1 || formattedAddress.indexOf("Deutschland") != -1){
				//TODO show layer that user is not in germany
			}
			this.map.setZoom(this.options.zoomLevel);
			this.map.setCenter(results[0].geometry.location);
			this.setData(results[0].geometry.location.lat(), results[0].geometry.location.lng());
			this.sendSearchRequestToService();
		}
	},

	onResetButtonClick: function(event) {
		event.preventDefault();
		if(this.getSearchTextValue() != "") {
			this.clearSearchTextfield();
		}
	},

	clearSearchTextfield: function() {
		this.$searchTextfield.val("");
	},

	onLocateButtonClick: function(event) {
		event.preventDefault();
		this.tryGeoLocation();
	},

	onAutoCompletePlaceChange: function() {
		var place = this.autocomplete.getPlace();
		if(place.geometry != undefined) {
			//viewport = The bounds of the recommended viewport for displaying this GeocodeResult
			//fitBounds = Sets the viewport to contain the given bounds
			if (place.geometry.viewport) {
				this.map.fitBounds(place.geometry.viewport);
			} else {
				this.map.setCenter(place.geometry.location);
				this.map.setZoom(this.options.zoomLevel);
			}
			this.setData(place.geometry.location.lat(), place.geometry.location.lng());
			this.sendSearchRequestToService();
		}
		var address = '';
		if (place.address_components) {
			address = [(place.address_components[0] && 				/* NO JS  subexpression should be wrapped in parens */
				place.address_components[0].short_name || ''),
				(place.address_components[1] &&						/* NO JS  subexpression should be wrapped in parens */
				place.address_components[1].short_name || ''),
				(place.address_components[2] &&						/* NO JS  subexpression should be wrapped in parens */
				place.address_components[2].short_name || '')
				].join(' ');
		}
	},

	sendSearchRequestToService: function() {
		this.clearAllMarker();

		var c = this.map.getCenter();
		var query = this.$queryFoursquarefield.val();
		//console.log("query", query);

		var queryObject = {
			q: query,
			lat: c.lat(),
			lng: c.lng()
		};

		var that = this;
		foursquareApi.search(queryObject, function(venues){
			that.onServiceSuccess(venues);
		});
	},

	onJSONPLoad: function(data) {
	},

	clearAllMarker: function() {
		if(this.venueOverlays.length > 0) {
			$(this.venueOverlays).each(function(index, venueOverlay){
				venueOverlay.clearVenueOverLayer();
				venueOverlay = null;
			});
		}
		this.venueOverlays = [];
	},

	//create post parameters
	getFormSerialization: function() {
		var serializationString = "";
		serializationString = this.$searchForm.serialize();
		if(this.filters && !$.isEmptyObject(this.filters)) {
			serializationString = serializationString + "&" + $.param(this.filters);
		}
		return serializationString;
	},

	//create symbols and set them on the map
	onServiceSuccess: function(data) {


		var self = this;
		$.each(data, function(index, venue) {
			//console.log("venue", venue);
			var venueOverlay = new VenueOverlay({venueInfo: venue});
			venueOverlay.setMap(self.map);
			venueOverlay.on(VenueOverlay.ClickOnMarkerEvent, _.bind(self.onMarkerClicked, self));
			self.venueOverlays.push(venueOverlay);
		});

	},

	onServiceError: function(xhr, ajaxOptions, thrownError) {
		//console.log("xhr", xhr);
		//console.log("ajaxOptions", ajaxOptions);
		//console.log("thrownError", thrownError);
	},

	onMarkerClicked: function(marker) {

		var venueId = marker.venueInfoVO.venueInfo.id;
		if(venueId){
			foursquareApi.exploreVenue(venueId);
		}

		if(this.currentClickedMarker) {
			this.prevClickedMarker = this.currentClickedMarker;
		}
		this.currentClickedMarker = marker;

		if(this.prevClickedMarker && this.prevClickedMarker != this.currentClickedMarker) {
			this.prevClickedMarker.closeInfoBox();
		}
	},

	//set values for input fields
	setData: function(lat, lnt) {
		this.$defaultInputFields.latitude.val(lat);
		this.$defaultInputFields.longitude.val(lnt);

		if(this.getSearchTextValue() != "") {
			this.$defaultInputFields.searchField.val(this.getSearchTextValue());
		}
	},

	getSearchTextValue: function() {
		return this.$searchTextfield.val();
	}

});
