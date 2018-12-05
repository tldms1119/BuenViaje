<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script>
$(function(){
	$('#orderBy').change(function(){
		var orderBy = $('#orderBy').val();
		var url = '${ context }/gallery/list?orderBy='+ orderBy;
		location = url;
	});
});

</script>
<div class="container">
	<div class="col-md-10 offset-md-1">
	<div class="card" style="margin-top: 28px">
		<div id="list" class="card-body card-body-cascade default-color d-flex justify-content-between align-items-center">
			<div class="float-left">
			<a href="${context}/gallery/card" class="btn btn-sm">
				<i class="fas fa-table"></i>
			</a>
			<a href="${context}/gallery/list" 
				class="btn btn-sm">
				<i class="fas fa-bars"></i>
			</a>
		</div>
			<a href="" class="white-text mr-5"><h4>Gallery&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h4></a>

			<div class="float-right">
				<a href="create" class="btn btn-sm">
				<i class="fas fa-pencil-alt mt-0"></i></a>
				

			</div>
		</div>
	</div>
		
	
<!-- 	<div class="text-right">		
		<select name="dropdown-menu">
			<option><a class="dropdown-item" href="#">등록일</a></option>
			<option><a class="dropdown-item" href="#">조회순</a></option>
		</select>
	</div>	 -->
	<div class="card">
	<div class="card-body">
	<div class="float-left">
		<form action="list">
			Sort By
			<select id="orderBy" name="orderBy" class="btn btn-outline-primary dropdown-toggle btn-sm">
				<option value="country"
					<c:if test="${orderBy=='country'}">selected</c:if>
					>Country
				</option>
				<option value="gallery_id"
					<c:if test="${orderBy=='gallery_id'}">selected</c:if>
					>Registration Date
				</option>
				<option value="read_cnt"
					<c:if test="${orderBy=='read_cnt'}">selected</c:if>
					>Check order
				</option>
			</select>
			<!-- <button type="submit"
				class="btn btn-default btn-sm">
				<i class="fas fa-share-square"></i></button> -->
		</form>
	</div>
		
	<div class="float-right">
		<form action="search" method="post" class="form-inline mx-auto">
			<!-- <input type="hidden" name="check" value="list"> -->
			<select name="category"
						class="btn btn-primary dropdown-toggle btn-sm">
						<option value="gallery_id"
							<c:if test="${ search.category == 'gallery_id' }">selected</c:if>>Gallery
							ID</option>
						<option value="owner"
							<c:if test="${ search.category == 'owner' }">selected</c:if>>Owner</option>
						<option value="title"
							<c:if test="${ search.category == 'title' }">selected</c:if>>Title</option>
						<option value="country"
							<c:if test="${ search.category == 'country' }">selected</c:if>>Country</option>
					</select> 
					<input class="form-control" type="text" placeholder="Search" 
					aria-label="Search" name="keyword" required>
					<button class="btn btn-mdb-color btn-rounded btn-sm my-0 ml-sm-2"
						type="submit">Search</button>
		</form>
	</div>
	</div>
	</div>
	  				
	<div class="card my-2">	
		<div class="card-body card-body-cascade">
			<table class="table table-striped">
		<thead>
			<tr>
				<th>#</th>
				<th>Title</th>
				<th>Country</th>				
				<th>Preview</th>
				<th>Images</th>
				<th>Owner</th>			
				<th>Date</th>
				<th>View Count</th>
				<!-- <th><a href="?orderBy=read_cnt">View Count</a></th>
				<th><a href="?orderBy=reg_date">Date</a></th> -->
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="item" items="${list}" varStatus="status">
				<fmt:formatDate var="regDate" value="${item.regDate}"
										pattern="yyyy-MM-dd"/>
			
				<tr>
					<td>
						${item.galleryId}
					</td>				
					<td>
						<a href="view/${item.galleryId}?page=${pagination.page}">
						${item.title}</a>
					<c:if test="${today==regDate}">
						<span class="badge badge-pill badge-danger">
							<i class="fas fa-tag"></i>New</span>
					</c:if>
					</td>
					<td>
						${item.country}
					</td>
					<td>
						<c:if test="${not empty item.list[0]}">
						<img src="image/${item.list[0].imageId}" width="30">
						</c:if>
					</td>
					<td>
						${item.list[0].imageId}/
						${item.list.size()}				
						${image.fileName}
					</td>
					
					<td>
						${item.owner}
					</td>					
					<td>
						${regDate}
					</td>
					<td>
						${item.readCnt}
					</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
			
		
		</div>
		
		
		<jsp:include page="/WEB-INF/views/common/pagination.jsp"/>				
		
		
	</div>
	
	
</div>

</div>
