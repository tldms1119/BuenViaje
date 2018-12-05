<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="container mt-3">
	<div class="col-md-8 offset-md-2">
		<div class="mx-5">
		<h2 class="my-5"><i class="fas fa-user-circle"></i> Edit</h2>
		<form:form commandName="member">
			<form:hidden path="userId"/>
			<form:hidden path="name"/>
			<div>
				<label>ID</label>
				<p class="font-weight-bold">${ member.userId }</p>
			</div>
			
			<div>
				<label>Name</label>
				<p class="font-weight-bold">${ member.name }</p>
			</div>
			
			<label for="userLevel">User Level</label><br>
			<form:select class="small" path="userLevel" items="${ userLevels }"/>
			
			<div class="md-form">
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
			
			<div class="md-form">
				<form:password path="password" class="form-control"/>
				<!-- reject를 사용한 전역 error에 대한 메시지 -->
				<form:errors path="password" element="div" cssClass="error"/>
				<label for="password">Admin Password</label>
			</div>
			
			<form:errors element="div" cssClass="error"/>
			<hr>
			<div class="text-center">
				<button type="submit" class="btn btn-outline-secondary waves-effect">
					<i class="fas fa-check"></i> Sumbit</button>
				<a href="../list?page=${ param.page }" class="btn btn-outline-primary waves-effect"> 
					<i class="fas fa-undo"></i> Back</a>
			</div>
		</form:form>
		</div>
	</div>
</div>>