<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="container">
	<div class="row" id="user-profile-page" >
		<div class="span3">
			
			<!-- Users Avatar/welcome -->
			<tiles:insertAttribute name="useravatar" />

			<!-- Users Project List -->
			<tiles:insertAttribute name="userprojects" />
			
		</div>
		
		<!-- Users Activity Stream -->
		<div class="span7">
			<tiles:insertAttribute name="useractivitystream" />
		</div>
		
		<div class="span2">
			<tiles:insertAttribute name="userfollow" ignore="true"/>
			<tiles:insertAttribute name="usersuggestedprojects" ignore="true"/>
			<tiles:insertAttribute name="usersuggestedpeople" ignore="true" />
			<tiles:insertAttribute name="useretsy" ignore="true" />
		</div>
	</div>
</div>