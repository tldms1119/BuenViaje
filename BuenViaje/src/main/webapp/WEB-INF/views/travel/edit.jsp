<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="context" value="${ pageContext.request.contextPath }" scope="request"/>
<script src="${ context }/resources/bower_components/tinymce/tinymce.min.js"></script>
<script>
$(function() {
	tinymce.init({
		selector : 'textarea',
		height: 400
	});
	
	$('.delete-attachment').click(function(e){
		e.preventDefault();
		var parent = $(this).parent();
		var travelId = $('#travelId').val(); // ${ board.boardId };
		// 사용자 정의 속성 추출 메소드는 attr 말고 data
		var attachmentId = $(this).data('target');	
		var password = $('#password').val();
		
		if(password == ''){
			alert('비밀번호를 입력하세요');
			$('#password').focus();
			return;
		}
		
		var url = '../delete_attachment';
		var params = {
				travelId : travelId,
				password : password,
				attachmentId : attachmentId
		};
		
		if(confirm("첨부파일을 삭제하시겠습니까?") == true){
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
	<div class="col-md-8 offset-md-2">
		<h2 class="my-5"><i class="far fa-edit"></i> Edit</h2>
		
		<form:form commandName="travel" enctype="multipart/form-data">
			<form:hidden path="travelId"/> 
			<form:hidden path="writer"/>
			
			<div class="md-form">
				<label for="title">Title</label>
				<form:input path="title" class="form-control" required="required"/>
				<form:errors path="title" cssClass="error" delimiter="div"/>
			</div>
			
			<div class="mb-3">
				<label>Writer</label>
				<p class="font-weight-bold">${ USER.userId }</p>
			</div>
			
			<div class="md-form">
				<label for="country">Country</label>
				<form:input path="country" class="form-control" required="required"/>
				<form:errors cssClass="error" delimiter="div"/>
			</div>
			
			<div class="md-form">
				<label for="password">Password</label>
				<form:password path="password" class="form-control" required="required"/>
				<form:errors cssClass="error" delimiter="div"/>
			</div>
			
			<div>
				<label>Attached Files</label>
				<c:forEach var="file" items="${ travel.attachList }">
					<div> 
						<i class="fa fa-download"></i> ${ file.fileName }
						<a class="delete-attachment" href="#" 
								data-target="${ file.attachmentId }">
						<i class="far fa-trash-alt"></i></a> 
					</div>
				</c:forEach>
			</div><br>
			
			<div>
				<label>Attach Files</label><br>
				<input type="file" name="files" multiple="multiple">
			</div>
			
			<div class="md-form">
				<form:textarea path="content"/>
			</div>
			
			<div class="text-center">
				<a href="../view/${ travel.travelId }?page=${ param.page }" class="btn btn-outline-primary waves-effect"> 
					<i class="fas fa-undo"></i> Back</a>
					
				<button type="submit" class="btn btn-outline-secondary waves-effect">
					<i class="fas fa-edit"></i> OK</button>
			</div>	
		</form:form>
	</div>
</div>