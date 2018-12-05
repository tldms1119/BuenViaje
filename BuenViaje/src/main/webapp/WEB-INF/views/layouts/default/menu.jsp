<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.navbar {
	background-color: #3E4551;
}
.nav-link {
 	color: white;
}
</style>
<!--Navbar-->
<nav class="navbar navbar-expand-lg">
	<!-- Navbar brand -->
	<a class="navbar-brand text-white" href="${ context }/">Buen Viaje</i></a>

	<!-- Collapse button -->
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#basicExampleNav" aria-controls="basicExampleNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<!-- Collapsible content -->
	<c:set var="context" value="${ pageContext.request.contextPath }"
		scope="request" />
	<div class="collapse navbar-collapse" id="basicExampleNav">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="${ context }/travel/list">Travel <span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item"><a class="nav-link"
				href="${ context }/gallery/card">Gallery</a></li>
			<li class="nav-item"><a class="nav-link"
				href="${ context }/route/list">Route</a></li>

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
	<!-- Collapsible content -->
</nav>