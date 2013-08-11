

var VenueOverlay = Backbone.View.extend({

	map: null,
	marker: null,
	infoBox: null,
	venueInfoVO: null,
	symbolUrl: 'resources/img/maps-symbol.png',
	mobileInfoLayer: null,

	initialize: function(attributes) {

		console.log("attributes", attributes);

		this.venueInfoVO = new VenueInfoVO({venueInfo: attributes});
		this.createMarker();
		this.bindEvents();
	},

	createMarker: function() {
		this.marker = new google.maps.Marker({
			position: this.getLatLng(),
			icon: this.getMarkerIcon()
		});

	},

	bindEvents: function() {
		google.maps.event.addListener(this.marker, 'click', _.bind(this.onMarkerClick, this));
	},

	onMarkerClick: function() {
		this.enableMarker(false);
		this.createInfoBox();
		// trigger event to close other possibly opened infoBoxes in googlemaps.js
		this.trigger(VenueOverlay.ClickOnMarkerEvent, this);
	},

	enableMarker: function(value) {
		var options = {
			visible: value,
			clickable: value
		};
		this.marker.setOptions(options);
	},

	createInfoBox: function() {
		if(!this.infoBox) {
			this.infoBox = new InfoBox({latlng: this.getLatLng(), map: this.map, infoLayer: $(this.getInfoLayer())});
			$(this.infoBox).bind(InfoBox.ClickCloseButton, _.bind(this.onCloseButtonInfoBox, this));

			console.log("created info box");
			$(this.infoBox).bind(InfoBox.ClickMoreInfoButton, _.bind(this.onClickMoreInfoButton, this));
		}
	},

	onCloseButtonInfoBox: function() {
		this.closeInfoBox();
	},

	closeInfoBox: function() {
		if(this.infoBox) {
			this.enableMarker(true);
			this.infoBox.closeInfoWindow();
			this.infoBox = null;
		}
	},

	onClickMoreInfoButton: function(event) {
		event.preventDefault();

		console.log("clicked on more info", this.venueInfoVO);
		var venueId = 23432432;
		//venueId

		$.fancybox( { type: 'iframe', href : 'http://robot-oi772f3re:8080/springApp21/date?venueId=23432432&senderUid=32432432&recepientUid=23423432', title : 'Lorem lipsum'} );
	},

	clearVenueOverLayer: function() {
		this.venueInfoVO.clear();
		this.closeInfoBox();
		this.marker.setMap(null);
		if(this.mobileInfoLayer != null) {
			this.mobileInfoLayer = null;
		}
	},

	getInfoLayer: function() {
		return '<div>' +
					'<div class="info-window">' +
						'<span class="tooltip-arrow"></span>' +
						'<div class="header-container">' +
							this.venueInfoVO.getHeaderInfo() +
							'<a class="close-button" href="#">close button</a>' +
						'</div>' +
						'<div class="desktop">' +
							'<ul class="info-list" data-role="expand-list">' +
								'<li class="list-item">' +
									'<a href="#" class="link-big">Link1</a>' +
									'<div class="wrapper">' +
										'<ul class="opening-hours">' +
											this.venueInfoVO.getDays() +
										'</ul>' +
									'</div>' +
								'</li>' +
								'<li class="list-item">' +
									'<a href="#" class="link-big">Services</a>' +
									'<div class="wrapper services">' +
										'<p>' + this.venueInfoVO.getServices() + '</p>' +
									'</div>' +
								'</li>' +
							'</ul>' +
						'</div>' +
						'<div class="button-container">' +
							'<a class="link-button arrow-right" href="scheduledate?venueId='+ this.venueInfoVO.getId() +'"><span class="text">Schedule a date at this venue</span></a>' +
						'</div>' +
					'</div>' +
				'</div>';
	},

	// set map and map to marker
	setMap: function(map) {
		this.map = map;
		this.marker.setMap(this.map);
	},



	getMarkerIcon: function() {

		var customIcon = this.symbolUrl;

		if(this.venueInfoVO.venueInfo.categories[0]){
			customIcon = this.venueInfoVO.venueInfo.categories[0].icon;

			customType = (this.venueInfoVO.venueInfo.categories[0].name).toLowerCase();
			console.log("customType", customType);

			//theater
			//indie theater
			//college theater


			//café


			//restaurant
			//italian restaurant
			//eastern european restaurant
			//asian restaurant
			//japanese restaurant
			//cajun / creole restaurant
			//turkish restaurant
			//korean restaurant
			//indian restaurant
			//chinese restaurant
			//african restaurant
			//vegetarian / vegan restaurant


			//ice cream shop
			//food truck
			//dessert shop
			//cocktail bar


			//comedy club
			//general entertainment
			//bar
			//art gallery


			//pub
			//gastropub
			//beer garden
			//brewery

			//performing arts venue
			//nightclub
			//breakfast spot
			//hotel
			//casino
			//rock club

			//gay bar


			//theme park


			//history museum

			//zoo
			//playground


			//plaza
			//snack place
			//bbq joint
			//snack place

			//library
			//science museum

			//aquarium

		}


		////console.log("this.venueInfoVO.categories", this.venueInfoVO.venueInfo.categories[0].icon);
		return customIcon; //this.symbolUrl;//this.venueInfoVO.venueInfo.categories[0];
		//this.symbolUrl; //new google.maps.LatLng(this.venueInfoVO.getLatitude(), this.venueInfoVO.getLongitude());
	},



	getLatLng: function() {
		return new google.maps.LatLng(this.venueInfoVO.getLatitude(), this.venueInfoVO.getLongitude());
	}

});

VenueOverlay.ClickOnMarkerEvent = "MCD-clickOnMarkerEvent";

var VenueInfoVO = Backbone.View.extend({

	venueInfo: null,
	days: ["Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"],
	times: null,
	services: null,

	initialize: function(attributes) {
		//console.log("attributes", attributes);

		this.venueInfo = attributes.venueInfo.venueInfo;

		this.setDays();
		this.setServices();
	},

	setDays: function() {
		this.times = [];
		/*
		this.times.push(this.venueInfo.monday);
		this.times.push(this.venueInfo.tuesday);
		this.times.push(this.venueInfo.wednesday);
		this.times.push(this.venueInfo.thursday);
		this.times.push(this.venueInfo.friday);
		this.times.push(this.venueInfo.saturday);
		this.times.push(this.venueInfo.sunday);*/
	},

	getDays: function() {
		var markup = "";
		var i = 0;
		for (i; i < this.times.length; i++) {
			if(this.times[i] != "" && this.times[i] != undefined) {
				if(i == (this.times.length - 1)) {
					markup += '<li class="last">' +
							'<p class="day">' + this.days[i] + '</p>' +
							'<p class="time">' + this.times[i] + ' Uhr</p>' +
						'</li>';
				} else {
					markup += '<li>' +
								'<p class="day">' + this.days[i] + '</p>' +
								'<p class="time">' + this.times[i] + ' Uhr</p>' +
							'</li>';
				}
			}
		}
		return markup;
	},

	setServices: function() {
		this.services = [];
		/*
		if(this.venueInfo.mccafe) {
			this.services.push("McCafe");
		}
		if(this.venueInfo.mcdrive) {
			this.services.push("McDrive");
		}
		if(this.venueInfo.wlan) {
			this.services.push("WLAN");
		}
		if(this.venueInfo.open24h) {
			this.services.push("24-Stunden geöffnet");
		}
		if(this.venueInfo.toggo) {
			this.services.push("Kindergeburtstag");
		}
		if(this.venueInfo.coupons) {
			this.services.push("Gutscheine");
		}*/
	},

	getServices: function() {
		var services = "";
		var i = 0;
		for (i; i < this.services.length; i++) {
			if( i != (this.services.length - 1)) {
				services += this.services[i] + ", ";
			} else {
				services += this.services[i];
			}
		}
		return services;
	},

	getId: function(){

		console.log("this.venueInfo", this.venueInfo.id);
		var id = 2;
		return id;
	},

	getHeaderInfo: function() {

		var name = "";
		if(this.venueInfo.name != undefined){
			name = '<p class="name">'+this.venueInfo.name+'</p>';
		}

		var address = "";
		if(this.venueInfo.location.address != undefined){
			address = '<p class="address">'+this.venueInfo.location.address+'</p>';
		}

		var city = "";
		if(this.venueInfo.location.city != undefined){
			city = '<p class="city">'+this.venueInfo.location.city+'</p>';
		}

		var state = "";
		if(this.venueInfo.location.state != undefined){
			state = '<p class="state">'+this.venueInfo.location.state+'</p>';
		}

		var postalCode = "";
		if(this.venueInfo.location.postalCode != undefined){
			postalCode = '<p class="postalCode">'+this.venueInfo.location.postalCode+'</p>';
		}

		return name + address+ city + state + postalCode;
	},

	getLatitude: function() {
		return this.venueInfo.location.lat;
	},

	getLongitude: function() {
		return this.venueInfo.location.lng;
	},

	clear: function() {
		this.venueInfo = null;
		this.times = [];
		this.services = [];
	}
});
