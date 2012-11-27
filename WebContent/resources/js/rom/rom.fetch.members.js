/*
*
* Author: Rob Shan Lone
* Copyright (c) 2012 The Old County Limited.
*
* All rights reserved.
*/

/**
	* @class Romance Fetch Members
	* @description Retrieving members
*/

var fetchMembers = Backbone.View.extend({

  	/*
	 * @description initialize
	 */
	initialize : function() {
		console.log("get feed members");
		/*get feed members*/
		var that = this;
		utils.getJsonData(utils.feedUsers, function(data){
			that.populateHome(data);
		});
	},

	getTemplate: function(value){
		console.log("value", value);

		console.log("value.interestData", value.interestData);

		var country = "United States";
		var interests = "movies";
		var online = 1;
		var gender = "Male";
		var userId = 21;
		var name = "Brad Pitt";

		var template = '<li class="element odd" data-user-country="United Kingdom" data-user-interests="movies" data-user-online="1" data-user-gender="'+value.gender+'" data-user-name="'+value.name+'" data-user-id="21"><div class="avatar"><a href="#"><img src="'+value.pictureAvatar+'"></a></div></li>';
		$(template).data('user-country', country);
		$(template).data('user-interests', interests);
		$(template).data('user-online', online);
		$(template).data('user-gender', gender);
		$(template).data('user-id', userId);
		$(template).data('user-name', name);

		return template;
	},

	populateHome: function(data){
		//console.log("data", data);
		console.log("populate home");

		$('.users.isotope').empty();
		//$('.users').isotope('destroy');

		var that = this;
		var itemList = '';
		$.each(data, function(index, value) {
			//console.log(value);
			var userItem = that.getTemplate(value);
			itemList += userItem;
			console.log("index", index);

		});
		$('.users').append(itemList).isotope( 'addItems', $(itemList) );
		//$('.users').isotope( 'reloadItems');

		//var isoFilter= new isotopefilters();
	}

 });
