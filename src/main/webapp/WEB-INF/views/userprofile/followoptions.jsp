<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class=" white rounded">
<h4 class="text-right">Follow!</h4>
	<div class="text-justify">
		<sec:authorize access="isAnonymous()">
			<a href="<c:url value='/' />" title="Sign-In">Sign In</a> to follow this user!
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			Follow Me!
		</sec:authorize>
	</div>
</div>