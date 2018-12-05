<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul class="pagination pagination-circle justify-content-center pg-purple mb-0">

	<c:if test="${ pagination.currentBlock > 1 }">
		<li class="page-item"><a class="page-link"
			href="?page=1">first</a>
		</li>
		<li id="prev" class="page-item"><a class="page-link"
			href="?page=${ pagination.prevBlockPage }"><i class="fas fa-arrow-left"></i></a>
		</li>
	</c:if>

	<c:forEach var="cnt" begin="${ pagination.startPage }" end="${  pagination.endPage }">
		<li class="page-item <c:if test="${ pagination.page == cnt }">active</c:if>">
			<a class="page-link" href="?page=${ cnt }">${ cnt }</a>
		</li>
	</c:forEach>
	
	<c:if test="${ pagination.currentBlock < pagination.totalBlock }">
		<li id="post" class="page-item">
			<a class="page-link" href="?page=${ pagination.nextBlockPage }">
							<i class="fas fa-arrow-right"></i></a>
		</li>
		<li class="page-item"><a class="page-link"
			href="?page=${ pagination.totalPage }">last</a>
		</li>
	</c:if>

</ul>

