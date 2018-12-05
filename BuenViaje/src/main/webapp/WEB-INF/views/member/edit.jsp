<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="row">
	<div class="col-md-6 offset-md-3">
		<h2 class="my-5">
			<img src="avata/${ USER.userId }" class="rounded-circle avata-md">
					  &nbsp;Edit Profile</h2>
		<form:form commandName="member" encType="multipart/form-data">
			<form:hidden path="userId"/>
			<form:hidden path="name"/>
			<div class="mb-3">
				<label>User ID</label>
				<p class="font-weight-bold">${ member.userId }</p>
			</div>
			
			<div class="mb-3">
				<label>Name</label>
				<p class="font-weight-bold">${ member.name }</p>
			</div>
			
			<div class="mb-3">
				<label>User Level</label>
				<p class="font-weight-bold">${ member.userLevel }</p>
			</div>
			
			<div class="md-form">
				<form:password path="password" class="form-control"/>
				<form:errors path="password" element="div" cssClass="error"/>
				<form:errors element="div" cssClass="error"/>
				<label for="password">Password</label>
			</div>
			
			<div class="mb-3">
				<label for="avata">Avata Image</label><br>
				<input type="file" name="avata" multiple="multiple">
			</div>
			
			<div class="md-form  mt-5">
				<form:input path="email" class="form-control"/>
				<form:errors path="email" element="div" cssClass="error"/>
				<label for="email">Email</label>
			</div>
			
			<div class="md-form">
				<form:input path="address" class="form-control"/>
				<label for="address">Address</label>
			</div>
			
			<div class="md-form">
				<form:input path="nickName" class="form-control"/>
				<label for="nickName">Nick Name</label>
			</div>
			
			<div class="md-form">
				<form:input path="phoneNumber" class="form-control"/>
				<form:errors path="phoneNumber" element="div" cssClass="error"/>
				<label for="phoneNumber">Phone Number</label>
			</div>
			
			<div class="text-center">
			<button type="submit" class="btn btn-outline-secondary waves-effect">
				<i class="fas fa-pen"></i> Edit</button>
			<a href="view" class="btn btn-outline-primary waves-effect"> 
				<i class="fas fa-undo"></i> Back</a>
		</div>
		</form:form>
	</div>
</div>