<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<form class="form-inline" action="<c:url value='/status' />" method="post">
	<input type="text" class="span6" placeholder="Tell your peeps what you are doing now..">
	<button type="submit" class="btn">post status</button>
</form>
<hr/>