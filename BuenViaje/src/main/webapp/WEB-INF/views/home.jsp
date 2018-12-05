<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page session="false"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:set var="context" value="${ pageContext.request.contextPath }" scope="request"/>
<!-- Font Awesome -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
<!-- Bootstrap core CSS -->
<link href="${ context }/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Material Design Bootstrap -->
<link href="${ context }/resources/bower_components/MDBootstrap/css/mdb.min.css" rel="stylesheet">
<!-- Your custom styles (optional) -->
<link href="${ context }/resources/css/main.css" rel="stylesheet">

<!-- SCRIPTS --> 
<!-- JQuery --> 
<script type="text/javascript" src="${ context }/resources/bower_components/jquery/dist/jquery.min.js"></script> 
<!-- Bootstrap tooltips --> 
<script type="text/javascript" src="${ context }/resources/bower_components/popper.js/dist/umd/popper.min.js"></script> 
<!-- Bootstrap core JavaScript --> 
<script type="text/javascript" src="${ context }/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<style>
 .logo img {
 	display: block;
 	margin: auto;
 }
</style>
<body>
<!--Main Navigation-->
<header>

  <nav class="navbar fixed-top navbar-expand-lg navbar-dark scrolling-navbar">
    <a class="navbar-brand text-white" href="${ context }/"><strong>Buen Viaje</strong></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a class="nav-link" href="${ context }/travel/list">Travel <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${ context }/gallery/card">Gallery</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${ context }/route/list">Route</a>
        </li>
        <c:if test="${ USER.userLevel == 'ADMIN' }">
				<li class="nav-item"><a class="nav-link"
					href="${ context }/admin/member/list">User Management</a></li>
		</c:if>
      </ul>
      <ul class="navbar-nav">
			<c:if test="${ empty USER }">
				<li class="nav-item"><a class="nav-link"
					href="${ context }/account/login">Login</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${ context }/account/join">Join</a></li>
			</c:if>
			<c:if test="${ not empty USER }">
				<li class="nav-item"><a class="nav-link"
					href="${ context }/member/view"> 
					<img src="${ context }/member/avata/${USER.userId}"
						class="rounded-circle avata-sm"> ${USER.userId}
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${ context }/account/logout">Logout</a></li>
			</c:if>
		</ul>
    </div>
  </nav>

  <div class="view intro-2" style="height: 600px">
    <div class="full-bg-img">
      <div class="mask rgba-purple-light flex-center">
        <div class="container text-center white-text wow fadeInUp">
        <br>
        <div class="logo"> 
           	 		
            <img src="${context}/resources/images/buenviaje2.gif" width=80%>
             <%-- <img src="${context}/resources/images/home2W.png" width=60%> --%>
		</div>
 
          <!-- <h2>This Navbar is fixed and transparent</h2> -->
          <br>
          <h2></h2>
          <br>
          <h5></h5>
          <p></p>
          <br>
          <p></p>
        </div>
      </div>
    </div>
  </div>

</header>
<!--Main Navigation-->

<!--Main Layout-->
<main class="text-center py-5">

  <div class="container">
    <div class="row">
      <div class="col-md-12">

        <p align="justify">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
          incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation
          ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
          voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
          proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet,
          consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
          enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
          consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
          nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
          mollit anim id est laborum.</p>
          
          <img src="${context}/resources/images/buenviaje-01.jpg" width=100%>

      </div>
    </div>
  </div>

</main>
<!--Main Layout-->

<div class="container-fluid text-center text-md-left">
	<div class="row">
		<div class="col-md-6 pb-3">
			<h6 class="font-weight-bold">Created by Sieun</h6>
			<p>This is a footer. Here you can use rows and columns here to
				organize your footer content</p>
		</div>
		<div class="col-md-6 pb-3">
			<h6 class="font-weight-bold text-uppercase">Links</h6>
			<ul class="list-unstyled">
				<li><a href="#!">Link 1</a></li>
				<li><a href="#!">Link 2</a></li>
				<li><a href="#!">Link 3</a></li>
				<li><a href="#!">Link 4</a></li>
			</ul>
		</div>
	</div>
</div>
<div class="footer-copyright py-3 text-center">
	<h6 class="font-weight-bold">ⓒ 2018 Copyright</h6>
</div>
</body>
</html>


