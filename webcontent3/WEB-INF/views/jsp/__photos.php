<?php
$pics = array(
	'http://www.hdwallpapersdepot.com/wp-content/uploads/2012/07/Jessica-Alba.jpg',
	'http://www.hdwallpapersdepot.com/wp-content/uploads/2012/07/Jessica-Alba-3.jpg',
	'http://photos.imageevent.com/afap/wallpapers/stars/jessicaalba//Jessica%20Alba%20---1.jpg',
	'http://www.topnews.in/light/files/Jessica-Alba_9.jpg',
	'http://4.bp.blogspot.com/_aGZwEIFiNOA/S_S6JNZpk1I/AAAAAAAAD08/Pd-A9qq8o7Y/s1600/Jessica-Alba-fashion--26-celebrity-64992_492_650.jpg'
	);
$key = rand(0, 4);
?>
<div id="colorboxWrapper" class="regular">
	<h2>My Photos</h2>
	<div class="tabs">User's Photos</div>
	<div class="innerWrap">
		<div class="photoWrap"><img src="<?php echo $pics[$key];?>"></div>
	</div>
	<div class="colorboxFooter"><span class="count">22 Photos</span></div>
</div>

