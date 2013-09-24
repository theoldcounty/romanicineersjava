

var galleryFix = {
	init: function(){

		var that = this;

		var selectorimg = ".featureImg img";
		that.triggerRatioCheckAfterLoad(selectorimg);

		var selectorimg = ".profilePics .imageholder img";
		$(selectorimg).each(function( index ) {
			that.triggerRatioCheckAfterLoad(this);
		});

		
		

		$(".imageholder").mouseenter(function() {
			
			var imageHolder = $(this);
			
			imageHolder.prepend('<div class="cog"><a href="deleteimage?image_id='+$(this).data("image-id")+'">x</a></div>');   
		
			$(".cog a").click(function(e) {
				e.preventDefault();
				
				$.ajax({
					  url: $(this).attr("href"),
					  cache: false
					}).done(function( html ) {
					  console.log("deleted");
					});				
				
				imageHolder.fadeOut(200);
			});		
		}).mouseleave(function() {
			$(this).find('.cog').remove();
		});

	},
	triggerRatioCheckAfterLoad: function(selectorimg){
		var that = this;

		var img = new Image();

		  // Create var for image source
		var imageSrc = $(selectorimg).attr("src");

		  // define what happens once the image is loaded.
		img.onload = function() {
			// Stuff to do after image load ( jQuery and all that )
			// Within here you can make use of src=imageSrc,
			// knowing that it's been loaded.
			that.checkRatioImg(selectorimg);
		};

		  // Attach the source last.
		  // The onload function will now trigger once it's loaded.
		img.src = imageSrc;

	},
	checkRatioImg: function(img){

		var width = $(img).width();
		var height = $(img).height();

		console.log("width", width);
		console.log("height", height);

		if(width > height){
			//img wide

			console.log("landscape");
			$(img).addClass("landscape");

		}
		else{
			//img portrait

			console.log("portrait");
			$(img).addClass("portrait");
		}
	}
};
