
var swipers = {
	init: function(){

		var localArray = new Array();

		$('[data-swiper="true"]').each(function(index) {
			var options = {
				mode: $(this).data("mode"),
				loop: $(this).data("loop"),
				speed: $(this).data("speed"),
				slidesPerGroup: $(this).data("slidespergroup"),
				slidesPerView: $(this).data("slidesperview"),
				freeMode: $(this).data("freemode"),
				scrollContainer: $(this).data("scrollcontainer"),
				freeModeFluid: $(this).data("freemodefluid")
			};

			var id = "swipes"+index;
			$(this).attr("id", id);

			var mySwiper = $(this).swiper(options);
			localArray[id] = mySwiper;
		});

		this.objSwipers = localArray;

		this.bindEvents();
	},
	getSwiper: function(id){
		return this.objSwipers[id];
	},
	setSwiperTo: function(id, index){
		var speed = 900;

		this.getSwiper(id).swipeTo(index, speed, function(msg){
			//console.log(msg);
		});
	},
	goPreviousSwipe: function(id){
		this.getSwiper(id).swipePrev();//run transition to previous slide
	},
	goNextSwipe: function(id){
		this.getSwiper(id).swipeNext();//run transition to next slide
	},
	getActiveSwipe: function(id) {
		return this.getSwiper(id).activeIndex;
	},
	setSwiperSize: function(id){
		this.getSwiper(id).resizeFix();
	},
	bindEvents: function(){
		var that = this;

		$(".arrow-left").click(function(e) {
			var swiperId = $(this).parent().find('.swiper-container').attr("id");
			that.getSwiper(swiperId).swipePrev();// run transition to previous slide
		});

		$(".arrow-right").click(function(e) {
			var swiperId = $(this).parent().find('.swiper-container').attr("id");
			that.getSwiper(swiperId).swipeNext();//run transition to next slide
		});


	},
	objSwipers: new Array()
};

