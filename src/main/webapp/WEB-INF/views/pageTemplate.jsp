<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
       <tiles:insertAttribute name="header" />
       <tiles:insertAttribute name="jsimports" />
    </head>
    <body>
		<div class="">
	        <tiles:insertAttribute name="topcontent" ignore="true" />
	    </div>
	    <div >
			<tiles:insertAttribute name="maincontent" />
			<tiles:insertAttribute name="footer" ignore="true"  />
	    </div>
    </body>
</html>