/*
*	Google Maper
*
*	Author: Rob Shan Lone
*	Copyright (c) 2013 The Old County Limited.
*
*	All rights reserved.
*/

var googleMaper = {
	zoom:8,
	map: null,
	latInterest: 0,
	longInterest: 0,
	latUser: 0,
	longUser: 0,
	setup: function(latInterest, longInterest){
		this.latInterest = latInterest;
		this.longInterest = longInterest;

		var mapOptions = {
			zoom: this.zoom,
			center: new google.maps.LatLng(latInterest, longInterest),
			mapTypeId: google.maps.MapTypeId.ROADMAP
		};

        var theMap = $('#canvasMap')[0];
        this.map = new google.maps.Map(theMap, mapOptions);

	},
	setUserMarker: function(latUser, longUser){

		this.latUser = latUser;
		this.longUser = longUser;

        var image = 'resources/img/usermarker.png';
        var myLatLng = new google.maps.LatLng(latUser, longUser);

        var beachMarker = new google.maps.Marker({
            position: myLatLng,
            map: this.map,
            icon: image
        });

	},
	setVenueMarkers: function(map, locations) {
        // Add markers to the map

        // Marker sizes are expressed as a Size of X,Y
        // where the origin of the image (0,0) is located
        // in the top left of the image.

        // Origins, anchor positions and coordinates of the marker
        // increase in the X direction to the right and in
        // the Y direction down.
        var image = new google.maps.MarkerImage('http://www.google.com/earth/outreach/images/icon_map.jpg',
            // This marker is 20 pixels wide by 32 pixels tall.
            new google.maps.Size(20, 32),
            // The origin for this image is 0,0.
            new google.maps.Point(0,0),
            // The anchor for this image is the base of the flagpole at 0,32.
            new google.maps.Point(0, 32));
        var shadow = new google.maps.MarkerImage('http://www.google.com/earth/outreach/images/icon_map.jpg',
            // The shadow image is larger in the horizontal dimension
            // while the position and offset are the same as for the main image.
            new google.maps.Size(37, 32),
            new google.maps.Point(0,0),
            new google.maps.Point(0, 32));
            // Shapes define the clickable region of the icon.
            // The type defines an HTML &lt;area&gt; element 'poly' which
            // traces out a polygon as a series of X,Y points. The final
            // coordinate closes the poly by connecting to the first
            // coordinate.
        var shape = {
            coord: [1, 1, 1, 20, 18, 20, 18 , 1],
            type: 'poly'
        };
        for (var i = 0; i < locations.length; i++) {
          var beach = locations[i];
          var myLatLng = new google.maps.LatLng(beach[1], beach[2]);
          var marker = new google.maps.Marker({
              position: myLatLng,
              map: map,
              shadow: shadow,
              icon: image,
              shape: shape,
              title: beach[0],
              zIndex: beach[3]
          });
        }
      }
};
