/*
*	Rom Controller Home
*
*	Author: Rob Shan Lone
*	Copyright (c) 2013 The Old County Limited.
*
*	All rights reserved.
*/

var gridUserHandler = {
		init: function(){
			this.bindGridUserEvents();
			this.emptyUserList();//clear out dummy holders

			var isoFilter= new isotopeFilters();
			this.getUsers(function(response){});
		},
		start: 0,
		end: 5,
		getInterests: function(id, callback){
			var interests = "";
			var interestUrl = 'api?servicerequest=getInterests&id='+id+'&chartname=interests';

			$.getJSON(interestUrl, function(interestdata){
				if(interestdata[0].response == "OK"){
					$.each(interestdata[0].dataResults, function(key, variable) {
						interests+=key+',';
					});
					interests = interests.substring(0, interests.length - 1);
					callback(interests);
				}
			});
		},
		emptyUserList: function(){
			$('.users').empty();
		},
		getUsers: function(callback){
			var that = this;
			var peopleUrl = 'api?servicerequest=getMembers&skips='+this.start+'&limits='+this.end;
			if(peopleUrl != null){
				$.getJSON(peopleUrl, function(data){
					if(data[0].response == "OK"){

						var count = data[0].users.length;

						var j = 0;
						//data
						$.each(data[0].users, function(index, value) {
							var id = value._id.$oid;

							//populate person
							var country = value.country;
							var gender = value.gender;
							var title = value.username;
							var isOnline = value.isloggedon;

							var url = "user?id="+id;
							var featureAvatarThumbnail = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRTL5GtjP3j0_EYZBejnEs-Wx9CQu_bIlNmDJNG-6rfwa55cEi-";//value.pictureAvatar;

							//populate person
							var template = '<li class="element odd" data-user-country="'+country+'" data-user-interests="" data-user-online="'+isOnline+'" data-user-gender="'+gender+'" data-user-name="'+title+'" data-user-id="'+id+'"><div class="avatar"><a href="'+url+'"><img src="'+featureAvatarThumbnail+'"></a></div></li>';
							$('.users').isotope( 'insert',  $(template) );

							that.getInterests(id, function(interests){
								$('.users').find('li[data-user-id='+id+']').attr('data-user-interests', interests);
							});

							j++
							if(j == count){
								var usertip = new userTip();
								callback(true);
							}
						});
					}
				});
			}
		},
		bindGridUserEvents: function(){
			var that = this;

			$("a#getMore").click(function(e) {
				e.preventDefault();
				
				var nextBatch = 10;
				that.start = that.end;
				that.end +=nextBatch;
				that.getUsers(function(response){
					if(hasFiltered){
						$("#filters li.showall a").click();
					}
				});
			});
		}
};
