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
			var that = this;
			this.bindGridUserEvents();
			this.emptyUserList();//clear out dummy holders

			var isoFilter= new isotopeFilters();
			this.getUsers(function(response){
				that.filteruserscrollenhance();
			});
		},
		start: 0,
		end: 35,
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
			$('[data-filter-users="true"]').empty();
		},
		calculateAge: function(dob){

			//var dob='19800810';
			var year=Number(dob.substr(0,4));
			var month=Number(dob.substr(4,2))-1;
			var day=Number(dob.substr(6,2));
			var today=new Date();
			var age=today.getFullYear()-year;
			if(today.getMonth()<month || (today.getMonth()==month && today.getDate()<day)){
				age--;
			}

			return age;
		},
		setFilterWidth: function(){
			var elementWidth = $('[data-filter-users="true"]').find("li").outerWidth(true);
			var count = $('[data-filter-users="true"]').find("li:not(.isotope-hidden)").length;

			var newWidth = count*elementWidth;

			$(".user_container").css("width", newWidth);
			console.log("newWidth", newWidth);
		},
		filteruserscrollenhance: function(){
			if($('[data-filter-users="true"]').parent().hasClass("user_container")){
				console.log("scroll user enhance fix");

				this.setFilterWidth();
				$("#filters li.reset a").click();
			}
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
							var ethnicity = value.ethnicity;//"Asian";

							var age = that.calculateAge(value.birthdate);


							var haspictures = false;
							if(value.gallery.length > 0){
								haspictures = true;
							}

							console.log("value", value);


							var url = "user?id="+id;

							var featureAvatarThumbnail = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRTL5GtjP3j0_EYZBejnEs-Wx9CQu_bIlNmDJNG-6rfwa55cEi-";//value.pictureAvatar;

							if(gender == "Male"){
								featureAvatarThumbnail = "http://zenahora.com/home/.gina/gregorytkac/zenahora.com/wp-content/uploads/2013/02/male.gif";
							}
							else{
								featureAvatarThumbnail = "http://zedequalszee.files.wordpress.com/2008/05/female.gif";
							}


							if(value.gallery[0] != undefined){
								featureAvatarThumbnail = "retrieveimage?image_id="+value.gallery[0].imgId+"&width=180";
							}

							//populate person
							var template = '<li class="element odd" data-user-country="'+country+'" data-user-interests="" data-user-ethnicity="'+ethnicity+'" data-user-age="'+age+'" data-user-pictures="'+haspictures+'" data-user-online="'+isOnline+'" data-user-gender="'+gender+'" data-user-name="'+title+'" data-user-id="'+id+'"><div class="avatar"><a href="'+url+'"><img src="'+featureAvatarThumbnail+'"></a></div></li>';
							$('[data-filter-users="true"]').isotope( 'insert',  $(template) );

							galleryFix.triggerRatioCheckAfterLoad('li[data-user-id='+id+'] img');

							that.getInterests(id, function(interests){
								$('[data-filter-users="true"]').find('li[data-user-id='+id+']').attr('data-user-interests', interests);
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
					$("#filters li.reset a").click();
					//window.setTimeout(function(){
						that.filteruserscrollenhance();
					//},200);
				});
			});
		}
};
