<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script>
	$(function() {
		$('#orderBy').change(function() {
			var orderBy = $('#orderBy').val();
			var url = '${ context }/gallery/card?orderBy=' + orderBy;
			location = url;
		});
	});
</script>

<div class="container">
	<div class="col-md-10 offset-md-1">
		<div class="card" style="margin-top: 28px">
			<div id="list"
				class="card-body card-body-cascade default-color d-flex justify-content-between align-items-center">
				<div class="float-left">
					<a href="${context}/gallery/card" class="btn btn-sm"> <i
						class="fas fa-table"></i>
					</a> <a href="${context}/gallery/list" class="btn btn-sm"> <i
						class="fas fa-bars"></i>
					</a>
				</div>
				<a href="" class="white-text mr-5"><h4>Gallery&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h4></a>

				<div class="float-right">
					<a href="create" class="btn btn-sm"> <i
						class="fas fa-pencil-alt mt-0"></i></a>


				</div>
			</div>

			<div class="card">
			<div class="card-body">
				<div class="float-left">
					<form action="card">
						Sort By <select id="orderBy" name="orderBy"
							class="btn btn-outline-primary dropdown-toggle btn-sm">
							<option value="country"
								<c:if test="${orderBy=='country'}">selected</c:if>>Country
							</option>
							<option value="gallery_id"
								<c:if test="${orderBy=='gallery_id'}">selected</c:if>>Registration
								Date</option>
							<option value="read_cnt"
								<c:if test="${orderBy=='read_cnt'}">selected</c:if>>Check
								order</option>
						</select>
						<!-- <button type="submit"
				class="btn btn-default btn-sm">
				<i class="fas fa-share-square"></i></button> -->
					</form>
				</div>

				<div class="float-right">
						<form action="search" method="post" class="form-inline mx-auto">
							<input type="hidden" name="check" value="card">
							<select name="category"
								class="btn btn-primary dropdown-toggle btn-sm">
								<option value="gallery_id"
							<c:if test="${ search.category == 'gallery_id' }">selected</c:if>>Gallery
							ID</option>
						<option value="writer"
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
						<%-- <a href="create"><i class="fas fa-plus"></i>Add</a> (total :
					${pagination.total}) --%>
				</div>
			</div>
			</div>
			</div>
			<br>
			<div class="row">
				<!--Grid column-->
				<c:forEach var="item" items="${ list }" varStatus="status">
					<div class="col-lg-4 col-md-12">
						<div class="z-depth-1-half">
							<div class="card mt-3">

								<img src="image/${item.list[0].imageId}" width="100"
									class="card-img-top" alt="">
								<div class="mask rgba-white-slight"></div>

							</div>


							<div class="card-body">
								<h5 class="card-title">${item.title}</h5>
								<hr>
								<p class="card-text">${item.summary}</p>
								<%-- <a href="view/${item.galleryId}?page=${pagination.page}" class="btn btn-default btn-sm">View</a> --%>
								<a href="view/${item.galleryId}?page=${pagination.page}"
									class="black-text d-flex justify-content-end">
									<h6>
										Read more <i class="fa fa-angle-double-right"></i>
									</h6>
								</a>
							</div>

						</div>
					</div>
				</c:forEach>
			</div>

			<%-- <c:forEach var="item" items="${list}" varStatus="status">	
		<div class="card" style="width: 400px">	
		<img class="card-img-top" 
			src="image/${item.list[0].imageId}" width="100">
			
		<div class="card-body">
			<h4 class="card-title"><a href="view/${item.galleryId}?page=${pagination.page}">
						${item.title}</a></h4>
			<p class="card-text">${item.description}</p>
			<a href="view/${item.galleryId}?page=${pagination.page}" class="btn btn-primary">View</a>
		</div>
		</div>	
	</c:forEach> --%>

		
	</div>
</div>