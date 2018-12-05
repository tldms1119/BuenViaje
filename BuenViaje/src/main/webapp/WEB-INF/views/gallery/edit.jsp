<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container mt-3">

<div class="row">
		
	<div class="col-md-8 offset-md-2">
		<h3 class="my-5"><i class="fas fa-images"></i> Edit Gallery</h3>
	
	  	<form:form commandName="galleryB">
	  		<form:hidden path="owner" value="${USER.userId}"/>
	  		
	  		<div class="md-form">	  
	  			<label for="title">Title</label>
	  			<form:input path="title" class="form-control"/>
	  			<form:errors path="title" element="div" cssClass="error"/>
	  		</div>

			<div class="md-form">
				<label for="password">Password</label>
				<form:password path="password" class="form-control"/>
				<form:errors path="password" cssClass="error" delimiter="div"/>
			</div>
			
			<div class="md-form">
	  			<label for="country">Country</label>
	  			<form:input path="country" class="form-control"/>
	  			<form:errors path="country" element="div" cssClass="error"/>
	  		</div>
			
			<div class="md-form">
	  			<label for="description">Description</label>
	  			<form:textarea path="description" class="form-control" rows="4"/>
	  		</div>
	  		
	  		<p>
	  			<button type="submit" class="btn btn-outline-default btn-sm">
	  				<i class="fas fa-plus"></i> Edit Images</button>
	  			<a href="../view/${galleryB.galleryId}?page=${param.page}" class="btn btn-default btn-sm">
					<i class="fas fa-undo"></i> Back
				</a>
	  		</p>
	  	</form:form>

	</div>
</div>
</div>