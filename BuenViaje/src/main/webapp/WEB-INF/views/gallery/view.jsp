<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(function(){
	$('#delete-btn').click(function(e){
				
		e.preventDefault();
		$('.password-panel').show();
	});
	
	$('#delete-cancel').click(function() {
		$('#password').val('');
		$('.password-panel').hide();
	});
	
	$('#delete-confirm').click(function() {
		var password = $('#password').val();
		var galleryId = $(this).data('target');
		if(password == '') {
			alert('Enter Password.');
			$('#password').focus();
			return;
		}
		var result = confirm("Are you sure you want to delete it?");
		if(!result) return;
		
		var url ='../delete';
		var params = {
				galleryId : galleryId,
				password : password,				
		};
		
		$.get(url, params, function(result) {			
			// 결과 응답 데이터는 result로 전달됨
			if(result == 'ok') {
				location = '../list';
			} else {
				alert('삭제 실패 : ' + result);
			}
		});					
	})			
});
</script>

<h2 class="my-5 text-center">
	<i class="far fa-images"></i> ${gallery.title}
</h2>

	
<%-- <ul>
	<c:forEach var="image" items="${gallery.list}">
		<li>			
			<img src="../thumb/${image.imageId}" alt="${image.fileName}"><br>
			${image.fileName}
			${image.title}
			<a href="../download/${image.imageId}">
				<i class="fas fa-download"></i></a>
			
		</li>	
	</c:forEach>
</ul> --%>
<div class="container">
		
	<div id="demo" class="carousel slide carousel-fade carousel-thumbnails w-100 mx-auto" data-ride="carousel">
	
	  <!-- The slideshow -->
	  <div class="carousel-inner" role="listbox">
	  <c:forEach var="image" items="${gallery.list}" varStatus="status">
			<div class="carousel-item 
				<c:if test="${status.first}">active</c:if>">
				<div class="view">
				<img class="d-block w-100" src="../image/${image.imageId}">
				<div class="mask rgba-block-light"></div>
				</div>
			</div>
		</c:forEach>
	  </div>
	
	  <!-- Left and right controls -->
	  <a class="carousel-control-prev" href="#demo" data-slide="prev">
	    <span class="carousel-control-prev-icon"></span>
	  </a>
	  <a class="carousel-control-next" href="#demo" data-slide="next">
	    <span class="carousel-control-next-icon"></span>
	  </a>
	
	
	<!-- Indicators -->
	
	  <ul class="carousel-indicators">
	    <c:forEach var="image" items="${gallery.list}" varStatus="status">
			<li data-target="#demo" data-slide-to="${status.index}"
				<c:if test="${status.index == 0}">class="active"</c:if>>
				<%-- <img class="d-block w-100" src="../thumb/${image.imageId}" alt="${image.fileName}"
				class="image-fluid"> --%>
			</li>
		</c:forEach>
	  </ul>
	</div>
	 
	<br>
	  
	  	<div class="text-center">Photo By : ${gallery.list[0].source}</div>
		<div class="text-center">${gallery.description}</div>
	  
	  <hr>
	  <div class="text-center">
		<c:if test="${USER.userId == gallery.owner}">
			<a href="../edit/${gallery.galleryId}?page=${param.page}" class="btn btn-default btn-sm">
				<i class="fa fa-edit mr-2"></i> Edit
			</a>
			<a href="#" id="delete-btn" class="btn btn-default btn-sm">
				<i class="fa fa-trash mr-2"></i> Delete
			</a>
		</c:if>
		<a href="../list?page=${param.page}" class="btn btn-default btn-sm">
			<i class="fa fa-undo mr-2"></i> Back
		</a>
	  </div>
	
	<div class="password-panel text-center my-3" style="display:none">
		Password : 
		<input type="password" id="password">
			<button type="button" id="delete-confirm"
					data-target="${gallery.galleryId}" class="btn btn-default btn-sm">Submit</button>
			<button type="button" id="delete-cancel" class="btn btn-default btn-sm">Back</button>	
	</div>
	
	
	
</div>



