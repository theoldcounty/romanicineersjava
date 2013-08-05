


	$(function() {
		$( "#slider-range" ).slider({
			range: true,
			min: 18,
			max: 99,
			values: [ 18, 38 ],
			slide: function( event, ui ) {
				$( "#amount" ).val(ui.values[ 0 ] + " - " + ui.values[ 1 ]);
			}
		});
		$( "#amount" ).val($( "#slider-range" ).slider( "values", 0 ) +
			" - " + $( "#slider-range" ).slider( "values", 1 ) );
	});