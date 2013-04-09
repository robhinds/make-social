<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<h3>Recent Activity</h3><hr/>
<div class="activity-stream">

	<!-- Form to create new status if needed -->
	<form class="form-inline" action="<c:url value='/status' />" method="post">
		<input type="text" class="span6" placeholder="Tell your peeps what you are doing now..">
		<button type="submit" class="btn">post status</button>
	</form>
	<hr/>
	
	<div id="activityStream">
		<!-- Over to Backbone here.. -->
	</div>
	
</div>