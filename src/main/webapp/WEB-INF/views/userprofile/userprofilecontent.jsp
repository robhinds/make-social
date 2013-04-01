<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<script type="text/JavaScript">
	$(document).ready(function(){		
		var gravatar = $('<img id="user-avatar">').attr({src: '//www.gravatar.com/avatar/${gUrl}?d=mm&r=x', style:'float:left;margin-right:8px;', class:'img-rounded'});
		$('#avatarImg').append(gravatar);
	});
</script>
<div class="container">
	<div class="row">
		<div class="span3">
			<div class="white rounded">
				<h4>${userName}'s Profile</h4>
				<div class="text-justify" style="min-height: 80px;">
					<div id="avatarImg"></div>
					
					<p>Welcome to ${userName}'s Make-Social profile</p>
				</div>
			</div>
		
			<div class="white rounded">
				<div>
					<h4>Your Projects:</h4><hr/>
				</div>
				<div>
					<a href="" >Some recipe thing </a> <span class="tag cooking pull-right">cooking</span>
					<hr/>
					<a href="" >some DIY thing </a> <span class="tag diy pull-right">diy</span>
					<hr/>
					<a href="" >a craft project</a> <span class="tag craft pull-right">craft</span>
					<hr/>
					<a href="" >knitting project</a> <span class="tag craft pull-right">craft</span>
					<hr/>
				</div>
			</div>
		</div>
		<div class="span7">
			<h3>Recent Activity</h3><hr/>
			<div class="activity-stream">
				<form class="form-inline">
  					<input type="text" class="span6" placeholder="Tell your peeps what you are doing now..">
  					<button type="submit" class="btn">post status</button>
				</form>
				<hr/>
				<p><img src="<c:url value='/resources/img/activity_edit.png' />" /> Rob created a new project: "project title"  <span class="fine-print">(3 minutes ago)</span></p><hr/>
				<p><img src="<c:url value='/resources/img/activity_edit.png' />" /> Rob created a new project: "project title2" <span class="fine-print">(6 minutes ago)</span></p><hr/>
				<p><img src="<c:url value='/resources/img/activity_status.png' />" /> "status update right here, yo!"  <span class="fine-print">(posted by Rob, 11 minutes ago)</span></p><hr/>
				
			</div>
		</div>
		<div class="span2">
			<div class=" white rounded">
				<h4 class="text-right">Project ideas</h4>
				<div class="text-justify">
					<p>Donec id elit non mi porta gravida at eget metus. Fusce
						dapibus, tellus ac cursus commodo, tortor mauris condimentum</p>
				</div>
			</div>
			<div class=" white rounded">
			<h4 class="text-right">People ideas</h4>
				<div class="text-justify">
					<p>Donec id elit non mi porta gravida at eget metus. Fusce
						dapibus, tellus ac cursus commodo, tortor mauris condimentum</p>
				</div>
			</div>
		</div>
	</div>
</div>