/*
*	Mcustomscroller
*
*	Author: Rob Shan Lone
*	Copyright (c) 2013 The Old County Limited.
*
*	All rights reserved.
*/

var mcustomscroller = {
	init: function(){
		$(window).load(function(){
			$('[data-custom-scroller="true"]').each(function(index) {

				var config= {
					horizontalScroll: $(this).data("horizontalscroll"),
					theme: $(this).data("theme"),
					advanced:{
						autoExpandHorizontalScroll:true,
						updateOnContentResize: true,
						updateOnBrowserResize: true
					}
				}

				$(this).mCustomScrollbar(config);
			});
		});
	}
}

