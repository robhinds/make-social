<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<h3>Recent Activity</h3><hr/>
<div class="activity-stream">

	<!-- Form to create new status if needed -->
	<tiles:insertAttribute name="createstatus"  ignore="true"/>
	
	<p><img src="<c:url value='/resources/img/activity_edit.png' />" /> Rob created a new project: "project title"  <span class="fine-print">(3 minutes ago)</span></p><hr/>
	<p><img src="<c:url value='/resources/img/activity_edit.png' />" /> Rob created a new project: "project title2" <span class="fine-print">(6 minutes ago)</span></p><hr/>
	<p><img src="<c:url value='/resources/img/activity_status.png' />" /> "status update right here, yo!"  <span class="fine-print">(posted by Rob, 11 minutes ago)</span></p><hr/>
	
</div>