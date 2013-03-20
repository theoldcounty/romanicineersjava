
var shazamOverlay = {
	morphBox: function(newMessage){
		var that = this;
		//_fade out innerwrapped
		
		$('#shazam-overlay').fadeOut(350,function(){
			that.populate(that.addWrap(newMessage, "mobile"));
			that.rePosition();
			$('#shazam-overlay').fadeIn(350);		
		});
		
	},
	addWrap: function(contents, type){
		if(type == "mobile"){
			return '<div id="shazam-wrapper" class="mobile">'+contents+'</div>';
		}
	},
	show: function(link){
		var that = this;
		$('body').addClass('preventScroll');

		this.showPreloader();

		$('#shazam-overlay').fadeIn(300, function(){
			that.getContents(link, function(section){
				pageHandler.reBindEvents(section);
				that.hidePreloader();
				that.rePosition();
				$('#shazam-box').fadeIn(400);
			});
		});
	},
	hide: function(){
		$('#shazam-box').fadeOut(300, function(){
			$('#shazam-overlay').fadeOut(400);
			$('body').removeClass('preventScroll');
		});
	},
	showPreloader: function(){
		$('#preloader').fadeIn(20);
	},
	hidePreloader: function(){
		$('#preloader').fadeOut(20);
	},
	getContents: function(source, callback){
		var that = this;
		$.ajax({
			url: source,
			cache: false
		}).done(function( html ) {
			that.populate(html);
			callback(source);
		});
	},
	rePosition: function(){

		// get the screen height and width
		var maskHeight = $(window).height();
		var maskWidth = $(window).width();

		console.log("maskHeight", maskHeight);
		console.log("maskWidth", maskWidth);

		// calculate the values for center alignment
		var shazamTop =  (maskHeight/2) - ($('#shazam-box').height()/2.2);
		var shazamLeft = (maskWidth/2) - ($('#shazam-box').width()/2);


		console.log("shazamTop", shazamTop);
		console.log("shazamLeft", shazamLeft);

		// assign values to the overlay and shazam box
		$('#shazam-overlay').css({height:maskHeight, width:maskWidth});
		$('#shazam-box').css({top:shazamTop, left:shazamLeft});
	},
	populate: function(contents){
		// populate the shazam box
		$('#shazam-message .wraps').html(contents);
	}

};


$(document).ready(function () {

	// if user clicked on button, the overlay layer or the shazambox, close the shazam
	$('#shazam-overlay').click(function () {
		shazamOverlay.hide();
		return false;
	});

	$('.close').click(function () {
		shazamOverlay.hide();
		return false;
	});


	$('.shazam').click(function () {
		var link = $(this).attr("href");
		shazamOverlay.show(link);

		return false;
	});



	// if user resize the window, call the same function again
	// to make sure the overlay fills the screen and shazambox aligned to center
	$(window).resize(function () {

		//only do it if the shazam box is not hidden
		if (!$('#shazam-box').is(':hidden')){
			shazamOverlay.rePosition();
		}
	});


});