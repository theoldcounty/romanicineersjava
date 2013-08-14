

//rom.date.js

var date ={
	init: function(){
		this.invokeVenue();
		this.invokeCandidate();
	},
	invokeVenue: function(){

		var venueId = $("#date").data("venue-id");

		console.log("venueId time to go for it", venueId);
		foursquareApi.exploreVenue(venueId);

		/*
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
		*/

	},
	invokeCandidate: function(){
		var candidateId = $("#date").data("recepientuid");
		console.log("candidateId", candidateId);
	}
}
