<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:set var="context" value="${ pageContext.request.contextPath }" scope="request"/>
<script src="${ context }/resources/bower_components/tinymce/tinymce.min.js"></script>
<script>
$(function() {
	tinymce.init({
		selector : 'textarea',
		height: 400
	});
});
</script>

<div class="container mt-3">
	<div class="col-md-8 offset-md-2">
		<h2 class="my-5"><i class="far fa-clipboard"></i> Write</h2>
		
		<form:form commandName="travel" enctype="multipart/form-data">
			<input type="hidden" name="writer" value="${ USER.userId }">
			
			<div class="md-form">
				<label for="title">Title</label>
				<form:input path="title" class="form-control"/>
				<form:errors path="title" cssClass="error" delimiter="div"/>
			</div>
			
			<div class="mb-3">
				<label>Writer</label>
				<p class="font-weight-bold">${ USER.userId }</p>
			</div>
			
			<div class="md-form">
				<label for="country">Country</label>
				<form:input path="country" class="form-control"/>
				<form:errors path="country" cssClass="error" delimiter="div"/>
			</div>
			
			<div class="md-form">
				<label for="password">Password</label>
				<form:password path="password" class="form-control"/>
				<form:errors path="password" cssClass="error" delimiter="div"/>
			</div>
			
			<div>
				<label>Attach Files</label><br>
				<input type="file" name="files" multiple="multiple">
			</div>
			
			<div class="md-form">
				<label for="content">Content</label>
				<form:textarea path="content"/>
			</div>
			
			<div class="text-center">
				<a href="list" class="btn btn-outline-primary waves-effect"> 
					<i class="fas fa-undo"></i> Back</a>
					
				<button type="submit" class="btn btn-outline-secondary waves-effect">
					<i class="fas fa-edit"></i> OK</button>
			</div>	
		</form:form>
	</div>
</div>