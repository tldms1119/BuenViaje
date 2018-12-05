<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container mt-3">
	<div class="col-md-8 offset-md-2">
		<h2 class="my-5">
			<i class="fas fa-search"></i> Search List
		</h2>

		<div class="text-right mb-2">
			<div class="mr-4 font-weight-bold">(총 : ${ list.size() }
				건)</div>
			<form action="search">
				<select name="category">
					<option value="user_id">User ID</option>
					<option value="name">Name</option>
					<option value="email">Email</option>
				</select> 
				<input type="text" id="search" class="text-secondary sm"
					name="keyword" style="border: dashed 0.5px" required>
				<button type="submit"
					class="search_btn btn btn-outline-secondary btn-sm mr-2">
					<i class="fas fa-search"></i>
				</button>
			</form>
		</div>
		<table class="table table-striped text-center">
			<tr>
				<th>No</th>
				<th>ID</th>
				<th>Name</th>
				<th>E-mail</th>
				<th>Register Date</th>
			</tr>
			<c:forEach var="member" items="${ list }" varStatus="status">
				<fmt:formatDate var="regDate" value="${ member.regDate }"
					pattern="yyyy-MM-dd" />
				<tr>
					<td>${ status.index + 1 }</td>
					<td><a
						href="view/${ member.userId }?page=${ pagination.page }"> ${ member.userId }</a>
						<c:if test="${ regDate == today }">
							<span class="badge badge-pill badge-info"> <i
								class="fas fa-tag"></i> new
							</span>
						</c:if></td>
					<td>
						<img src="${ context }/member/avata/${ member.userId }"
							 class="rounded-circle avata-sm">
						${ member.name }
					</td>
					<td>${ member.email }</td>
					<td>${ regDate }</td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/views/common/pagination.jsp" />
	</div>
</div>