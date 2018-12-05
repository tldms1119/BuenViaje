<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ attribute name="date" required="true" type="String" %>

<fmt:formatDate var="today" value="<%=new java.util.Date()%>" 
									pattern="yyyy-MM-dd"/>

<c:if test="${ date == today }">
	<span class="badge badge-pill badge-info"> 
		<i class="fas fa-tag"></i> new
	</span>
</c:if>