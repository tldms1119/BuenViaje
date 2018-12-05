<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="context" value="${ pageContext.request.contextPath }" scope="request"/>
<div class="card card-body mp-3">
	<form id="reply-form" action="${context}/travel/reply/${travelId}" method="post">
		<input type="hidden" name="travelId" value="${travelId}">
		<input type="hidden" name="writer" value="${USER.userId}">
		<div class="mb-2">
			<strong>Comment</strong>
			Writer : ${USER.userId}
		</div>
		<textarea name="content" required style="width:100%"></textarea>
		<div class="text-right">
			Password : <input type="password" name="password" required>
			<button type="submit">OK</button>
			<button type="reset">Cancel</button>
		</div>
	</form>
</div>