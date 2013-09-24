/*
*	Fancybox
*
*	Author: Rob Shan Lone
*	Copyright (c) 2013 The Old County Limited.
*
*	All rights reserved.
*/

var fancybox = {
	init: function(){
		$(".fancyboxtrigger").fancybox({
			maxWidth	: 800,
			maxHeight	: 600,
			fitToView	: false,
			width		: '70%',
			height		: '70%',
			beforeShow: function(){

				var section = $(this).attr("href");
				pageHandler.reBindEvents(section);
				
				tabs.init();
			}
		});

		$(".fancyboximage").fancybox({
		});
	}
};
