<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container mt-3">
	<div class="col-md-8 offset-md-2">
		<div class="mx-5">
			<h2 class="my-5">
				<img src="avata/${ USER.userId }" class="rounded-circle avata-md">
					  &nbsp;Profile
			</h2>
			<hr>
			<div class="row">
				<div class="col-md-4">
					<P>No.</P>
					<p>ID</p>
					<p>Name</p>
					<p>User Level</p>
					<p>Phone Number</p>
					<p>E-mail</p>
					<p>Address</p>
					<p>Nick Name</p>
					<p>Register Date</p>
					<p>Update Date</p>	
				</div>
				<div class="col-md-8">
					<P>${ USER.seq }</P>
					<p>${ USER.userId }</p>
					<p>${ USER.name }</p>
					<p>${ USER.userLevel }</p>
					<p>${ USER.phoneNumber }</p>
					<p>${ USER.email }</p>
					<p>${ USER.address }</p>
					<p>${ USER.nickName }</p>	
					<p>
						<fmt:formatDate value="${ USER.regDate }" pattern="yyyy-MM-dd"/>
					</p>
					<p>
						<fmt:formatDate value="${ USER.updateDate }" pattern="yyyy-MM-dd"/>
					</p>
				</div>
			</div>
		</div>

		<hr>
		<div class="text-center">
			<a href="edit" class="btn btn-outline-secondary waves-effect">
				<i class="fas fa-pen"></i> Edit</a>
			<a href="../" class="btn btn-outline-primary waves-effect"> 
				<i class="fas fa-undo"></i> Back</a>
		</div>
	</div>
</div>