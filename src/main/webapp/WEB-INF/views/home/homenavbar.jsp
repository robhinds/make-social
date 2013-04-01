<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="container" style="position: relative;">
	<div class="row" style="padding-top:10px;">
		<div class="span12">
			<ul class="nav nav-pills pull-right">
				<li class="active">
					<a href="<c:url value='/' />">
						<sec:authorize access="isAnonymous()">Home</sec:authorize>
						<sec:authorize access="isAuthenticated()">You</sec:authorize>
					</a>
				</li>
				
				<sec:authorize access="isAnonymous()">
					<li >
						<a href="<c:url value='sign-up' />">
							Register
						</a>
					</li>
				</sec:authorize>
				
				<li>
					<a href="<c:url value='search-projects' />">
						Discover
					</a>
				</li>
				
				<sec:authorize access="isAuthenticated()">
					<li>
						<a href="<c:url value='/sign-out' />">
							Logout
						</a>
					</li>
				</sec:authorize>
				
			</ul>
			<h3 class="muted"></h3>
		</div>
	</div>
</div>
