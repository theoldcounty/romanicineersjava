

	<nav id="filters">
		<ul class="initial">
			<li class="shuffle"><a><div class="icon"><img src="resources/images/icon_shuffle.png"></div>Shuffle</a></li>
			<li class="reset"><a><div class="icon"><img src="resources/images/icon_show_all.png"></div>Reset</a></li>
			<li class="pruneoptions">
				<div class="grid">
					<div class="left fifty">
						<label>Location</label>
						<select>
							<option>Any Country</option>
							<option>United Kingdom</option>
						</select>
						
						
						<label>Age</label>
						<input type="text" id="amount" style="border: 0; color: #f6931f; font-weight: bold;" />
						<div class="ageslider" id="slider-range"></div>
						
					</div>
					<div class="left fifty">
						
						<label>Interests</label>
						<select>
							<option>Any</option>
							<option>Robots</option>
						</select>
					
						
						<label>Ethinicity</label>
						<select>
							<option>Any</option>
							<option>Asian</option>
						</select>
					</div>
				</div>
			</li>
			<li class="submitoptions">
				<div class="grid">
					<div class="left fifty">
						<label>Male</label>
						<input type="checkbox" name="gender" value="male">
					</div>
					<div class="left fifty">			
						<label>Female</label>
						<input type="checkbox" name="gender" value="female">
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
		<div class="optional"><div class="wrappers"></div><div class="close"><a>x</a></div></div>
	</nav>
