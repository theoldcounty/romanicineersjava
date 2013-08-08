

	<nav id="filters">
		<form id="filterisotope" action="#" method="post">
			<ul class="initial">
				<li class="shuffle"><a class="directisotope"><div class="icon"><img src="resources/images/icon_shuffle.png"></div>Shuffle</a></li>
				<li class="reset"><a class="directisotope"><div class="icon"><img src="resources/images/icon_show_all.png"></div>Reset</a></li>
				<li class="pruneoptions">
					<div class="grid">
						<div class="left fifty">
							<label>Location</label>
							<select name="location">
								<option value="">Any Country</option>
								<option value="GBR">United Kingdom</option>
								<option value="IND">India</option>
								<option value="CHE">Switzerland</option>
								<option value="CAN">Canada</option>
							</select>
							
							
							<label>Age</label>
							<input name="agerange" type="text" id="amount" style="border: 0; color: #f6931f; font-weight: bold;" />
							<div class="ageslider" id="slider-range"></div>
							
						</div>
						<div class="left fifty">
							
							<label>Interests</label>
							<select name="interest">
								<option value="">Any</option>
								<option value="River rafting">River rafting</option>
								<option value="Cinema">Cinema</option>
								<option value="Sex">Sex</option>
								<option value="Movies">Movies</option>
								<option value="Basketball">Basketball</option>
								<option value="Robotics">Robotics</option>
								<option value="Spaceflight">Spaceflight</option>
							</select>
							
							<label>Ethinicity</label>
							<select name="ethnicity">
								<option value="">Any</option>
								<option value="0">Caucasian</option>
								<option value="1">Asian</option>
								<option value="2">Black</option>
								<option value="3">Indian</option>
								<option value="4">Hispanic</option>
								<option value="5">Middle Eastern</option>
								<option value="6">Native American</option>
								<option value="7">Mixed Race</option>
								<option value="8">Other Ethnicity</option>
							</select>
						</div>
					</div>
				</li>
				<li class="submitoptions">
					<div class="grid">
						<div class="left fifty">
							<label>Male</label>
							<input type="checkbox" name="gender" value="male" checked="checked">
						</div>
						<div class="left fifty">			
							<label>Female</label>
							<input type="checkbox" name="gender" value="female" checked="checked">
						</div>
					</div>
					<div class="grid">
						<div class="left hundred">
							<label>With Pics</label>
							<input type="checkbox" name="withpics" value="true">
						</div>
						
						<div class="left hundred">
							<label>Online only</label>
							<input type="checkbox" name="isonline" value="true">
						</div>
						
						<div class="left hundred">
							<input type="submit" name="submit" value="submit">
						</div>
					</div>					
				</li>
			</ul>
		</form>
		<div class="optional"><div class="wrappers"></div><div class="close"><a>x</a></div></div>
	</nav>
	
	

<div class="content_6 content" data-custom-scroller="true" data-horizontalscroll="true" data-theme="light">
		<div class="user_container">
			<ul class="users" data-filter-users="true"></ul>
		</div>
	</div>

	
			<!-- <ul class="users" data-filter-users="true"></ul>-->
	<a id="getMore" href="#">More Members</a>
						
	
	
