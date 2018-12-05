<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${ pageContext.request.contextPath }" scope="request"/>
<script src="${context}/resources/js/reply.js"></script>
<script>
var context = '${context}';		// js에서 사용할 컨텍스트 경로 명
var user = '${USER.userId}';	// 현재 사용자 ID
$(function(){
	$('.delete-btn').click(function(e){
		e.preventDefault();
		$('.password-panel').show();
	});
	
	$('#delete-cancel').click(function(){
		$('#password').val('');
		$('.password-panel').hide();
	});
	
	$('#delete-confirm').click(function(){
		var password = $('#password').val();
		var travelId = $(this).data('target');
		if(password == ''){
			alert('비밀번호를 입력하세요');
			$('#password').focus();
			return;
		}
		
		var result = confirm('게시글을 삭제할까요?');
		if(!result) return;
		
		var url = '../delete';
		var params = {
				travelId : travelId,
				password : password
		};
		
		$.get(url, params, function(result){
			if(result == 'ok'){
				location = '../list';
			} else {
				alert('delete fail' + result);
			}
		});
	});
	
	$('#reply-form').replyForm($('#reply-list'));
	$('#reply-list').replyList(${travel.travelId});
});
</script>
<div class="container mt-3">
	<div class="col-md-8 offset-md-2">
		<h2 class="my-5"><i class="far fa-file-alt"></i> View</h2>
		<div>
			<label>Travel ID</label>
			<p class="font-weight-bold">${ travel.travelId }</p>
		</div>
		<div>
			<label>Writer</label>
			<p class="font-weight-bold">${ travel.writer }</p>
		</div>
		<div>
			<label>Country</label>
			<p class="font-weight-bold">${ travel.country }</p>
		</div>
		<div>
			<label>Read Count</label>
			<p class="font-weight-bold">${ travel.readCnt }</p>
		</div>
		<%-- <div>
			<label>Attached Files</label>
			<c:forEach var="file" items="${ travel.attachList }">
				<div> <a href="../download/${ file.attachmentId }"> 
					<i class="fa fa-download"></i> ${ file.fileName }</a> 
				</div>
			</c:forEach>
		</div><br> --%>
		<div>
			<div class="font-weight-bold float-left">
				<label>Content</label>
			</div>
			<div class="font-weight-bold float-right">
				<fmt:formatDate value="${ travel.regDate }"
				pattern="yyyy-MM-dd" />
			</div>
		</div><br><hr>
		
		<div>
			<ul class="text-center" style="list-style-type: none">
					<c:forEach var="item" items="${ travel.attachList }" varStatus="status">
						<li>
							<img src="../attachment/${ item.attachmentId }" alt="${ item.fileName }"><br>
							${ item.fileName }
							<%-- <a href="../download/${ image.attachmentId }">
								<i class="fas fa-file-download"></i></a> --%>
						</li>
					</c:forEach>
				</ul>
			<p class="text-center"><c:if test="${ empty travel.content }">Empty Content</c:if>
			${ travel.content }</p>
		</div>
		<hr>
		
		<c:if test="${ not empty USER }">
			<jsp:include page="reply-form.jsp"/>
		</c:if>
		
		<div id="reply-list" class="mt-5">
			<jsp:include page="reply-list.jsp"/>
		</div>
	
		<hr>
		<div class="text-center">
			<a href="../list?page=${ param.page }" 
				class="btn btn-outline-primary waves-effect"> <i
				class="fas fa-undo"></i>Back
			</a>
			<c:if test="${USER.userId == travel.writer}">
				<a href="../edit/${ travel.travelId }?page=${ param.page }" 
					class="btn btn-outline-secondary waves-effect"> 
					<i class="fas fa-edit"></i>Edit
				</a>
				<a href="" 
					class="delete-btn btn btn-outline-danger waves-effect"> 
					<i class="fas fa-trash-alt"></i>Delete
				</a>
			</c:if>
		</div>
		
		<div class="password-panel text-center my-3" style="display:none">
			password : <input type="password" id="password">
			<button type="button" id="delete-confirm" data-target="${ travel.travelId }">
				OK</button>
			<button type="button" id="delete-cancel">취소</button>
		</div>
	</div>
</div>