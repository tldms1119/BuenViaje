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
	
	$('.add-spot').click(function(e){
		e.preventDefault();
		var parent = $(this).parent();
		parent.append('<div><input type="text" id="spot" name="spot" placeholder="Tour spot" class="mt-1"/> <input type="text" id="brief" name="brief" placeholder="Brief Description" style="width:400px" class="mt-1 ml-1"/> <a class="delete-spot" href="#"><i class="far fa-minus-square"></i></a></div>');
	});
	
	// on으로 바꿔줘야함.. 시작 시기 핸들러는 안걸림
	$(document).on('click', '.delete-spot', function(e){
		e.preventDefault();
		var parent = $(this).parent();
		parent.remove();
	});
});
</script>

<div class="container mt-3">
	<div class="col-md-8 offset-md-2">
		<h2 class="my-5"><i class="far fa-edit"></i> Edit</h2>
		
		<form:form commandName="route">
			<form:hidden path="routeId"/> 
			<form:hidden path="writer"/>
			
			<div class="md-form">
				<label for="title">Title</label>
				<form:input path="title" class="form-control" required="required"/>
				<form:errors path="title" cssClass="error" delimiter="div"/>
			</div>
			
			<div class="mb-3">
				<label>Writer</label>
				<p class="font-weight-bold">${ USER.userId }</p>
			</div>
			
			<div class="md-form">
				<label for="password">Password</label>
				<form:password path="password" class="form-control" required="required"/>
				<form:errors cssClass="error" delimiter="div"/>
			</div>
			
			<div class="md-form">
				<label for="country">Country</label>
				<form:input path="country" class="form-control" required="required"/>
				<form:errors cssClass="error" delimiter="div"/>
			</div>
			
			<div class="md-form">
				<label for="city">City</label>
				<form:input path="city" class="form-control" required="required"/>
				<form:errors cssClass="error" delimiter="div"/>
			</div>
			
			<div>
				<label>Tour Spot</label>
				<a class="add-spot" href="#">
					<i class="far fa-plus-square"></i></a><br>
				<c:forEach var="item" items="${ route.list }">
					<div>
							<input type="text" id="spot" name="spot" 
								value="${ item.title }" class="mt-1"/>
							<input type="text" id="brief" name="brief" 
								style="width:400px" value="${ item.brief }" 
								class="mt-1 ml-1"/>
							<a class="delete-spot" href="#">
							<i class="far fa-minus-square"></i></a>
						</div>
				</c:forEach>
			</div><br>
			
			<div class="md-form">
				<form:textarea path="description"/>
			</div>
			
			<div class="text-center">
				<a href="../view/${ route.routeId }?page=${ param.page }" class="btn btn-outline-primary waves-effect"> 
					<i class="fas fa-undo"></i> Back</a>
					
				<button type="submit" class="btn btn-outline-secondary waves-effect">
					<i class="fas fa-edit"></i> OK</button>
			</div>	
		</form:form>
	</div>
</div>