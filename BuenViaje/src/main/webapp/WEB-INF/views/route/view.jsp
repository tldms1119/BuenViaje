<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="${ context }/resources/css/route.css" rel="stylesheet">
<script>
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
		var routeId = $(this).data('target');
		if(password == ''){
			alert('비밀번호를 입력하세요');
			$('#password').focus();
			return;
		}
		
		var result = confirm('게시글을 삭제할까요?');
		if(!result) return;
		
		var url = '../delete';
		var params = {
				routeId : routeId,
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
});
</script>
<div class="container mt-3">
	<div class="col-md-8 offset-md-2">
		<h2 class="my-5"><i class="far fa-file-alt"></i> View</h2>
		<div>
			<label>Route ID</label>
			<p class="font-weight-bold">${ route.routeId }</p>
		</div>
		<div>
			<label>Writer</label>
			<p class="font-weight-bold">${ route.writer }</p>
		</div>
		<div>
			<label>Country</label>
			<p class="font-weight-bold">${ route.country }</p>
		</div>
		<div>
			<label>Country</label>
			<p class="font-weight-bold">${ route.city }</p>
		</div>
		<div>
			<label>Read Count</label>
			<p class="font-weight-bold">${ route.readCnt }</p>
		</div>
		<div>
			<div class="font-weight-bold float-left">
				<label>Description</label>
			</div>
			<div class="font-weight-bold float-right">
				<fmt:formatDate value="${ route.regDate }"
				pattern="yyyy-MM-dd" />
			</div>
		</div><br><hr>
		
		<div>
			<div class="box">
				<ul id="first-list">
				<c:forEach var="item" items="${ route.list }" varStatus="status">
					<c:if test="${ status.index < 3 }">
					<li><span></span>
						<div class="title">${ item.title }</div><br>
						<div class="info">${ item.brief }</div><br>
						<div class="name">- ${route.writer} -</div>
						<div class="time">
							<span></span> <!-- <span></span> -->
						</div>
					</li>
					</c:if>				
				</c:forEach>
				
				<!-- 여기서부터  -->
				 <div class="arrow" id="btn1">
            
                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Capa_1" x="0px" y="0px" width="512px" height="512px" viewBox="0 0 284.929 284.929" style="enable-background:new 0 0 284.929 284.929;" xml:space="preserve">
                    <g>
                        <g>
                            <path d="M135.899,167.877c1.902,1.902,4.093,2.851,6.567,2.851s4.661-0.948,6.562-2.851L282.082,34.829    c1.902-1.903,2.847-4.093,2.847-6.567s-0.951-4.665-2.847-6.567L267.808,7.417c-1.902-1.903-4.093-2.853-6.57-2.853    c-2.471,0-4.661,0.95-6.563,2.853L142.466,119.622L30.262,7.417c-1.903-1.903-4.093-2.853-6.567-2.853    c-2.475,0-4.665,0.95-6.567,2.853L2.856,21.695C0.95,23.597,0,25.784,0,28.262c0,2.478,0.953,4.665,2.856,6.567L135.899,167.877z" fill="#FFFFFF"/>
                            <path d="M267.808,117.053c-1.902-1.903-4.093-2.853-6.57-2.853c-2.471,0-4.661,0.95-6.563,2.853L142.466,229.257L30.262,117.05    c-1.903-1.903-4.093-2.853-6.567-2.853c-2.475,0-4.665,0.95-6.567,2.853L2.856,131.327C0.95,133.23,0,135.42,0,137.893    c0,2.474,0.953,4.665,2.856,6.57l133.043,133.046c1.902,1.903,4.093,2.854,6.567,2.854s4.661-0.951,6.562-2.854l133.054-133.046    c1.902-1.903,2.847-4.093,2.847-6.565c0-2.474-0.951-4.661-2.847-6.567L267.808,117.053z" fill="#FFFFFF"/>
                        </g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                </svg>
                
            	</div> 
				<!-- 여기까지 지우면 됩니다 -->
				</ul>
				
				<ul id="second-list">
				<c:forEach var="item" items="${ route.list }" varStatus="status">
					<c:if test="${ status.index >= 3 }">
					<li><span></span>
						<div class="title">${ item.title }</div><br>
						<div class="info">${ item.brief }</div><br>
						<div class="name">- ${route.writer} -</div>
						<div class="time">
							<span></span> <span></span>
						</div>
					</li>
					</c:if>				
				</c:forEach>
				<!-- 여기서부터 -->
				<div class="arrow" id="btn2">
            
                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Capa_1" x="0px" y="0px" width="512px" height="512px" viewBox="0 0 284.929 284.929" style="enable-background:new 0 0 284.929 284.929;" xml:space="preserve">
                    <g>
                        <g>
                            <path d="M135.899,167.877c1.902,1.902,4.093,2.851,6.567,2.851s4.661-0.948,6.562-2.851L282.082,34.829    c1.902-1.903,2.847-4.093,2.847-6.567s-0.951-4.665-2.847-6.567L267.808,7.417c-1.902-1.903-4.093-2.853-6.57-2.853    c-2.471,0-4.661,0.95-6.563,2.853L142.466,119.622L30.262,7.417c-1.903-1.903-4.093-2.853-6.567-2.853    c-2.475,0-4.665,0.95-6.567,2.853L2.856,21.695C0.95,23.597,0,25.784,0,28.262c0,2.478,0.953,4.665,2.856,6.567L135.899,167.877z" fill="#FFFFFF"/>
                            <path d="M267.808,117.053c-1.902-1.903-4.093-2.853-6.57-2.853c-2.471,0-4.661,0.95-6.563,2.853L142.466,229.257L30.262,117.05    c-1.903-1.903-4.093-2.853-6.567-2.853c-2.475,0-4.665,0.95-6.567,2.853L2.856,131.327C0.95,133.23,0,135.42,0,137.893    c0,2.474,0.953,4.665,2.856,6.57l133.043,133.046c1.902,1.903,4.093,2.854,6.567,2.854s4.661-0.951,6.562-2.854l133.054-133.046    c1.902-1.903,2.847-4.093,2.847-6.565c0-2.474-0.951-4.661-2.847-6.567L267.808,117.053z" fill="#FFFFFF"/>
                        </g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                    <g>
                    </g>
                </svg>
                
            </div>
				<!-- 여기까지 지우면 됩니다 -->
				</ul>
				
			 <script src="../../resources/route.js"></script>
			</div>
			<br>
			<div class="text-center"><c:if test="${ empty route.description }">Empty Description</c:if>
			${ route.description }</div>
		</div>
	
		<hr>
		<div class="text-center">
			<a href="../list?page=${ param.page }" 
				class="btn btn-outline-primary waves-effect"> <i
				class="fas fa-undo"></i>Back
			</a>
			<c:if test="${USER.userId == route.writer}">
				<a href="../edit/${ route.routeId }?page=${ param.page }" 
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
			<button type="button" id="delete-confirm" data-target="${ route.routeId }">
				OK</button>
			<button type="button" id="delete-cancel">취소</button>
		</div>
	</div>
</div>