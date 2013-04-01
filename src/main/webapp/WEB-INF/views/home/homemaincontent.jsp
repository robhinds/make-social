<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<div class="container" ">
	<div class="row">
		<div class="span3 white rounded">
			<img src="img/create.png" style="display: block; margin-left: auto; margin-right: auto" />
			<h4 class="text-center">Create Projects</h4>
			<div class="text-justify">
				<p>Donec id elit non mi porta gravida at eget metus. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum</p>
			</div>
		</div>
		<div class="span3 white rounded">
			<img src="img/track.png" style="display: block; margin-left: auto; margin-right: auto" />
			<h4 class="text-center">Track Projects</h4>
			<div class="text-justify">
				<p>Donec id elit non mi porta gravida at eget metus. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum</p>
			</div>
		</div>
		<div class="span3 white rounded">
			<img src="img/share.png" style="display: block; margin-left: auto; margin-right: auto" />
			<h4 class="text-center">Share Projects</h4>
			<div class="text-justify">
				<p>Donec id elit non mi porta gravida at eget metus. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum</p>
			</div>
		</div>
		<div class="span3 white rounded">
			<img src="img/discover.png" style="display: block; margin-left: auto; margin-right: auto" />
			<h4 class="text-center">Discover Projects</h4>
			<div class="text-justify">
				<p>Donec id elit non mi porta gravida at eget metus. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum</p>
			</div>
		</div>
	</div>
</div>

<div class="container section-buffer">
	<div class="row">
		<div class="span8">
			<h1 style="font-weight: 100;">Let's Make Things Social</h1>
			Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condi
			Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condi
			Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condi
			<br/><br/>
			Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condi
			Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condi
			Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condi
			Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condi
			<br/><br/>
			Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condi
			Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condi
		</div>
		<div class="span4">
			<div class="white rounded">
				<form class="form-signin dark-rounded" action="loginProcess" method="post">
			    	<h3 class="form-signin-heading">Already Registered?</h3>
			    	<div>
						<c:if test="${not empty param.success}">
							<div>
								<div class="alert alert-info"><h4>Thanks for registering!</h4><br/> Sign in below to start making stuff</div>
							</div>
						</c:if>
						<c:if test="${not empty param.loginFailure}">
							<div>
								<div class="alert alert-error"><h4>Error Signing In!</h4><br/> Check your username and password is correct</div>
							</div>
						</c:if>
					</div>
					<input type="text" class="input-block-level" name='j_username' placeholder="User name">
					<input type="password" class="input-block-level" name='j_password' placeholder="Password">
					<button class="btn btn-large btn-primary pull-right" type="submit">Sign in</button>
				</form>
			</div>
			<div class="white rounded" style="height: 610px;">
				<img src="img/avatars.png"  class="img-rounded pull-right" />
			</div>
		</div>
	</div>
</div>