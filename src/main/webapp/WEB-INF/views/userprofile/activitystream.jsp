<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<h3>Recent Activity</h3><hr/>
<div class="activity-stream">
	<!-- Form to create new status if needed -->
	<tiles:insertAttribute name="createstatus" ignore="true" />
	
	<div id="notification-timeline">
		<!-- Over to Backbone here.. -->
	</div>
	
</div>