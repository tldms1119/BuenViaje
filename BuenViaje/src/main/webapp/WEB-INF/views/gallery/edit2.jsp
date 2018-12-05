<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="context" value="${ pageContext.request.contextPath }" scope="request"/>
<script>
$(function() {

	$('.delete-image').click(function(e){
		e.preventDefault();
		var parent = $(this).parent();
		var galleryId = $('#galleryId').val(); // ${ board.boardId };
		// 사용자 정의 속성 추출 메소드는 attr 말고 data
		var imageId = $(this).data('target');	
		var password = $('#password').val();
		
		if(password == ''){
			alert('Enter Password.');
			$('#password').focus();
			return;
		}
		
		var url = '../delete_image';
		var params = {
				galleryId : galleryId,
				password : password,
				imageId : imageId
		};
		
		if(confirm("Are you sure you want to delete it?") == true){
			// 결과 응답 데이터는 result로 전달됨
			$.get(url, params, function(result){
				if(result == 'ok'){
					alert('delete success');
					parent.remove();
				} else {
					alert('delete fail : ' + result);
				}
			});
		}
	});
	
});
</script>
<div class="container mt-3">

<div class="row">
		
	<div class="col-md-8 offset-md-2">
		<h3 class="my-5"><i class="fas fa-images"></i> Edit Gallery</h3>  
		
		<form:form commandName="galleryB">			
			<form:hidden path="galleryId"/>
			<input type="hidden" name="owner" value="${galleryB.owner}">
			<div>
				<label>Title</label>
				<p class="font-weight-bold">${galleryB.title}</p>
			</div>			
			
			<div>
	  			<label>Country</label>
	  			<p class="font-weight-bold">${galleryB.country}</p>
	  		</div>
			
			<div>
				<label>Description</label>
				<p class="font-weight-bold">${galleryB.description}</p>
			</div>
		
			<!-- <p>
	  			<button type="submit" class="btn btn-outline-default btn-sm">
	  				<i class="fas fa-plus"></i> Add Images</button>
	  			<button type="reset" class="btn btn-default btn-sm">
	  				<i class="fas fa-undo"></i> Reset</button>
	  		</p> -->	
	  		<div class="md-form">
				<label for="password">Password</label>
				<form:password path="password" class="form-control"/>
				<form:errors path="password" cssClass="error" delimiter="div"/>
			</div>
		</form:form>
		
		<h6>Image List</h6>
		<ul>
			<c:forEach var="image" items="${galleryB.list}">
				<%-- <li>${image.fileName}</li> --%>
				<div>
				<i class="fa fa-download"></i> ${ image.fileName } <a
					class="delete-image" href="#"
					data-target="${ image.imageId }"> <i
					class="far fa-trash-alt"></i></a>
				</div>
			</c:forEach>
		</ul>
		
			
			<!-- 파일 업로드 폼 구성 -->
		
		<form action="../update/add_images" method="post" enctype="multipart/form-data">
			<input type="hidden" name="galleryId" value="${galleryB.galleryId}">
								
			<!-- <input type="file" name="file"> -->
			<!-- <span class="btn btn-default fileinput-button">
				<span>Open</span>
				
			</span> -->
			<h6>Add Image</h6>
			<input type="file" name="files"
				multiple="multiple" accept="image/*">
				
			<div class="md-form">			
				<label for="source">Photo By</label>			
				<input type="text" name="source" value="${ galleryB.list[0].source }" class="form-control">
			</div>
				
			<p>
				<button type="submit" class="btn btn-default btn-sm">
					<i class="fas fa-check"></i> Submit
				</button>
				<a href="../edit/${galleryB.galleryId}?page=${param.page}" class="btn btn-default btn-sm">
					<i class="fas fa-undo"></i> Back
				</a>
			</p>
		</form>
						
	</div>
</div>
</div>