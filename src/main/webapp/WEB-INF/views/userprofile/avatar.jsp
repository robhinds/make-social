<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<script type="text/JavaScript">
	$(document).ready(function(){		
		var gravatar = $('<img id="user-avatar">').attr({src: '//www.gravatar.com/avatar/${gUrl}?d=mm&r=x', style:'float:left;margin-right:8px;', class:'img-rounded'});
		$('#avatarImg').append(gravatar);
	});
</script>

<div class="white rounded">
	<h4>${userName}'s Profile</h4>
	<div class="text-justify" style="min-height: 80px;">
		<div id="avatarImg"></div>
		<p>Welcome to ${userName}'s Make-Social profile</p>
	</div>
</div>