<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
$(function(){
	$('.delete_btn').click(function(e){
		e.preventDefault();
		if(confirm('${member.userId} 회원을 삭제하시겠습니까?') == true){
			var url = $(this).attr('href');
			location = url;
		}
	});
});
</script>
<div class="container mt-3">
	<div class="col-md-8 offset-md-2">
		<div class="mx-5">
			<h2 class="my-5"><i class="fas fa-user-circle"></i> Details</h2>
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
					<P>${ member.seq }</P>
					<p>${ member.userId }</p>
					<p>${ member.name }</p>
					<p>${ member.userLevel }</p>
					<p>${ member.phoneNumber }</p>
					<p>${ member.email }</p>
					<p>${ member.address }</p>
					<p>${ member.nickName }</p>	
					<p>
						<fmt:formatDate value="${ member.regDate }" pattern="yyyy-MM-dd"/>
					</p>
					<p>
						<fmt:formatDate value="${ member.updateDate }" pattern="yyyy-MM-dd"/>
					</p>
				</div>
			</div>
		</div>

		<hr>
		<div class="text-center">
			<a href="../edit/${ member.userId }?page=${ param.page }" class="btn btn-outline-secondary waves-effect">
				<i class="fas fa-pen"></i> Edit</a>
			<a href="../delete/${ member.userId }?page=${ param.page }" class="delete_btn btn btn-outline-danger waves-effect">
				<i class="far fa-trash-alt"></i> Delete</a>
			<a href="../list?page=${ param.page }" class="btn btn-outline-primary waves-effect"> 
				<i class="fas fa-undo"></i> Back</a>
		</div>
	</div>
</div>