<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="iot"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="context" value="${ pageContext.request.contextPath }"></c:set>
<div class="container mt-3">
	<div class="col-md-10 offset-md-1">
		<div class="card" style="margin-top: 28px">
		<div id="list" class="card-body card-body-cascade default-color d-flex justify-content-between align-items-center">

			<a href="" class="white-text text-center"><h4>Travel List</h4></a>

			<div class="float-right">
				<a href="create" class="btn btn-sm">
				<i class="fas fa-pencil-alt mt-0"></i></a>
				

			</div>
		</div>
	</div>
		<%-- <iot:page-header icon="<i class='far fa-clipboard'></i>" title="Travel List"/>
		
		<div class="text-right">
			<c:if test="${not empty USER}"> 
				<a href="create"><i class="fas fa-pen"></i> Post/</a>
			</c:if> 
			<span class="mr-4 font-weight-bold">(총 : ${ pagination.total }건)</span>
		</div>
		<div>
			<div class="float-left">
				<p> Category : ${ search.category } / ${ search.secondCategory }</p>
				<p> Keyword : ${ keyword } / ${ secondKeyword }</p>
			</div> --%>
			
	<div class="card">
	<div class="card-body">	
	<div class="float-left">
					
		<form action="list">
			Sort By
			<select id="orderBy" name="orderBy" class="btn btn-outline-primary dropdown-toggle btn-sm">				
				<option value="travel_id" <c:if test="${ search.category == 'travel_id' }">selected</c:if>>Travel ID</option>
					<option value="writer" <c:if test="${ search.category == 'writer' }">selected</c:if>>writer</option>
					<option value="title" <c:if test="${ search.category == 'title' }">selected</c:if>>title</option>
					<option value="country" <c:if test="${ search.category == 'country' }">selected</c:if>>country</option>
			</select>
			<!-- <button type="submit"
				class="btn btn-default btn-sm">
				<i class="fas fa-share-square"></i></button> -->
		</form>
	</div>
			
			<%-- <div class="float-right mb-2">
				<form action="search" method="post">
				<input type="hidden" name="category" value="${ search.category }"/>
				<input type="hidden" name="keyword" value="${ search.keyword }"/>
				<select name="secondCategory">
					<option value="travel_id" <c:if test="${ search.category == 'travel_id' }">selected</c:if>>Travel ID</option>
					<option value="writer" <c:if test="${ search.category == 'writer' }">selected</c:if>>writer</option>
					<option value="title" <c:if test="${ search.category == 'title' }">selected</c:if>>title</option>
					<option value="country" <c:if test="${ search.category == 'country' }">selected</c:if>>country</option>
				</select> 
				<input type="text" id="search" class="text-secondary sm"
					name="secondKeyword" style="border: dashed 0.5px" required>
				<button type="submit"
					class="search_btn btn btn-outline-secondary btn-sm mr-2">
					<i class="fas fa-search"></i>
				</button>
			</form>
			</div> --%>
		
		<div class="float-right">
		
		<form action="search" method="post" class="form-inline mx-auto">
			<!-- <input type="hidden" name="check" value="list"> -->
			<select name="category"
						class="btn btn-primary dropdown-toggle btn-sm">
						<option value="travel_id">Travel ID</option>
						<option value="writer">writer</option>
						<option value="title">title</option>
						<option value="country">country</option>
					</select> 
					<input class="form-control" type="text" placeholder="Search" 
					aria-label="Search" name="keyword" required>
					<button class="btn btn-mdb-color btn-rounded btn-sm my-0 ml-sm-2"
						type="submit">Search</button>
		</form>
		<br>
		
	</div>
	<br>
	<br>
		<span> Category : ${ search.category } / ${ search.secondCategory }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span> Keyword : ${ keyword } / ${ secondKeyword }</span>
	</div>
	</div>
	
		
		<div class="card my-2">	
		<div class="card-body card-body-cascade">			
		<table class="table table-striped text-center">
			<tr>
				<th>No</th>
				<th>Title</th>
				<th>Writer</th>
				<th>Country</th>
				<th>Read Count</th>
				<th>Register Date</th>
			</tr>
			<c:forEach var="item" items="${ list }" varStatus="status">
				<fmt:formatDate var="regDate" value="${ item.regDate }" 
									pattern="yyyy-MM-dd" scope="request"/>
				<tr>
					<td>${ item.travelId }</td>
					<td>
						<a href="view/${ item.travelId }?page=${ pagination.page }">
						${item.title}</a>
						<iot:new-item date="${regDate}"/>
					</td>
					<td>
						<img src="${ context }/member/avata/${ item.writer }"
							 class="rounded-circle avata-sm">
						${ item.writer }
					</td>
					<td>${ item.country }</td>
					<td>${ item.readCnt }</td>
					<td>${ regDate }</td>
				</tr>
			</c:forEach>
		</table>
		<iot:pagination/>
		<%-- <jsp:include page="/WEB-INF/views/common/pagination.jsp"/> --%>
	</div>
</div>
</div>
</div>

