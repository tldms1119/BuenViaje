<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="context" value="${ pageContext.request.contextPath }"></c:set>
<script>
$(function(){	
	$('#id-check').click(function(){
		var userId = $('#userId').val();
		if(!userId){
			return alert('사용자 ID를 입력하세요');
		}
		var url = '${ context }/account/idcheck';
		var params = {
				userId : userId
		}
		
		// 콜백함수의 매개변수는 responseBody가 반환하는 Json 문자열
		$.get(url, params, function(data){
			if(data == "ok"){
				$('.check-result').text('사용 가능한 ID 입니다')
								.removeClass('error')
								.addClass('text-success');
				$('[type=submit]').prop('disabled', false);
			} else {
				$('.check-result').text('이미 존재하는 ID 입니다')
								.removeClass('text-success')
								.addClass('error');
				$('[type=submit]').prop('disabled', true);
			}
		});
	});
	
	$('#userId').keyup(function(){
		$('.check-result').text('중복 검사를 하세요')
						.removeClass('text-success')
						.addClass('error');
		$('[type=submit]').prop('disabled', true);
	});
	
	/* $('form').submit(function(event){
		event.preventDefault();
		var pw1 = $('#password').val();
		var pw2 = $('#password2').val();
		if(pw1 != pw2){
			alert('비밀번호가 일치하지 않습니다');
			$('[name=password]').focus();
			return;
		}
		this.submit();
	});  */
});
</script>
<div class="container mt-5 mb-5 pt-5 pb-5">
<div class="row justify-content-start">
<div class="col">
</div>
<div class="col">
<!--Section: Live preview-->
<section class="form-dark">

<!--Form without header-->
<div class="card card-image" style="background-image: url('${context}/resources/images/join.jpg'); width: 27rem;">
    <div class="text-white rgba-stylish-strong py-5 px-5 z-depth-4">

        <!--Header-->
        <div class="text-center">
            <h3 class="white-text mb-5 mt-4 font-weight-bold"><strong>PASS</strong> <a class="green-text font-weight-bold"><strong> PORT</strong></a></h3>
        </div>

        <!--Body-->
        <form:form commandName="member">
        	<form:hidden path="phoneNumber"/>
        	<form:hidden path="nickName"/>
        	<form:hidden path="address"/>
	        <div class="md-form">
	            <form:input path="userId" class="form-control white-text"/>
	            <label for="userId">Your ID</label>
	            <button type="button" class="btn btn-outline-secondary btn-sm" 
								id="id-check">check</button>
	            <span class="check-result error">ID Check</span>
	        </div>
        
	        <div class="md-form">
	            <form:input path="name" class="form-control white-text"/>
	            <form:errors path="name" element="div" cssClass="error"/>
	            <label for="name">Your name</label>
	        </div>
        
	        <div class="md-form">
	            <form:input path="email" class="form-control white-text"/>
	            <form:errors path="email" element="div" cssClass="error"/>
	            <label for="email">Your email</label>
	        </div>

	        <div class="md-form pb-3">
	            <form:password path="password" class="form-control white-text"/>
	            <label for="password">Your password</label>
	            <div class="form-check my-4">
	             
	            </div>
	        </div>
        
        <form:errors element="div" cssClass="error"/>

        <!--Grid row-->
        <div class="row d-flex align-items-center mb-4">

            <!--Grid column-->
            <div class="text-center mb-3 col-md-12">
                <button type="submit" class="btn btn-success btn-block btn-rounded z-depth-1">Sign in</button>
            </div>
            <!--Grid column-->
        </div>
        <!--Grid row-->
        
        </form:form>

        <!--Grid column-->
        <div class="col-md-12">
            <p class="font-small white-text d-flex justify-content-end">Have an account? <a href="${context}/account/login" class="green-text ml-1 font-weight-bold"> Log in</a></p>
        </div>
        <!--Grid column-->

    </div>
</div>
<!--/Form without header-->

</section>
</div>
<div class="col">
</div>

</div>
</div>
<!--Section: Live preview-->
<%-- <div class="row">
	<div class="col-md-6 offset-md-3">
		<h2 class="my-5"><i class="fas fa-user-circle"></i> Join</h2>
		<form:form commandName="member" encType="multipart/form-data">
			<div class="md-form">
				<form:input path="userId" class="form-control"/>
				<form:errors path="userId" element="div" cssClass="error"/>
				<label for="userId">User ID</label>
			</div>
			
			<p>
				<button type="button" class="btn btn-outline-secondary btn-sm" 
							id="id-check">ID 중복 검사</button>
				<span class="check-result error">ID 중복 검사를 하세요</span>
			</p>
		
			<div class="md-form">
				<form:password path="password" class="form-control"/>
				<form:errors path="password" element="div" cssClass="error"/>
				<label for="password">Password</label>
			</div>
			
			<div class="md-form">
				<input type="password" id="password2" class="form-control"/>
				<label for="password2">Password 확인</label>
			</div>
			
			<div class="md-form">
				<form:input path="name" class="form-control"/>
				<form:errors path="name" element="div" cssClass="error"/>
				<label for="name">Name</label>
			</div>
			
			<div class="mb-3">
				<label for="avata">Avata Image</label>
				<input type="file" name="avata" multiple="multiple">
			</div>
			
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
			
			<div class="error">${ error }</div>
			<button class="btn btn-secondary waves-effect btn-block my-4" 
						type="submit" disabled>Join</button>
		</form:form>
	</div>
</div> --%>