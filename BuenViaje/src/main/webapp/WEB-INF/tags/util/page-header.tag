<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="icon" required="false" type="String" %>
<%@ attribute name="title" required="true" type="String" %>

<h2 class="my-5">
	<c:if test="${ not empty icon }">
		${ icon } 
	</c:if>	${ title }
</h2>