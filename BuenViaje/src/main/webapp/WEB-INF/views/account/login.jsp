<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container mt-5 mb-5 pt-5 pb-5">
<div class="row justify-content-start">
<div class="col">
</div>
<div class="col">
<!--Section: Live preview-->
<section class="form-dark">

<!--Form without header-->
<div class="card card-image" style="background-image: url('https://mdbootstrap.com/img/Photos/Others/pricing-table7.jpg'); width: 27rem;">
    <div class="text-white rgba-stylish-strong py-5 px-5 z-depth-4">

        <!--Header-->
        <div class="text-center">
            <h3 class="white-text mb-5 mt-4 font-weight-bold"><strong>CHECK</strong> <a class="green-text font-weight-bold"><strong> IN</strong></a></h3>
        </div>
        
        <c:if test="${not empty login.url}">
			<div class="alert alert-warning"> 
			<strong>${ login.reason }</strong> 
			</div> 
		</c:if>

		<form:form commandName="login">
			<form:hidden path="url"/>
	        <!--Body-->
	        <div class="md-form">
	            <form:input path="userId" class="form-control white-text"/>
	            <label for="userId">Your ID</label>
	        </div>

	        <div class="md-form pb-3">
	            <form:password path="password" class="form-control white-text"/>
	            <label for="password">Your password</label>
	            <div class="form-check my-4">
	             
	            </div>
	        </div>
			
			<div class="error">${ error }</div>
	        <!--Grid row-->
	        <div class="row d-flex align-items-center mb-4">
	
	            <!--Grid column-->
	            <div class="text-center mb-3 col-md-12">
	                <button type="submit" class="btn btn-success btn-block btn-rounded z-depth-1">Log in</button>
	            </div>
	            <!--Grid column-->
	        </div>
	        <!--Grid row-->
		</form:form>

    </div>
</div>
<!--/Form without header-->

</section>
</div>
<div class="col">
</div>

</div>
</div>
<%-- <div class="row">
	<div class="col-md-6 offset-md-3"> 
		<h2 class="my-5"><i class="fas fa-lock"></i> Login</h2>
		
		<!-- ${url}, ${reason} 으로 불러도 되지만 로그인 실패해서 돌아왔을 경우 복원을 위해 login.url 로 기술 -->
		<c:if test="${not empty login.url}">
			<div class="alert alert-warning"> 
			<strong>${ login.reason }</strong> 
			</div> 
		</c:if>
		
		<form:form commandName="login">
			<form:hidden path="url"/>
			<div class="md-form">
				<form:input path="userId" class="form-control"/>
				<label for="userId">User ID</label>
			</div>
		
			<div class="md-form">
				<form:password path="password" class="form-control"/>
				<label for="password">Password</label>
			</div>
		
			<div class="error">${ error }</div>
			<button class="btn btn-secondary waves-effect btn-block my-4" type="submit">Sign in</button>	
		</form:form>
	</div>
</div>
 --%>