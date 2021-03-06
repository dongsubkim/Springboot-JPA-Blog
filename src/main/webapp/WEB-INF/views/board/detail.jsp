<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<button class="btn btn-secondary" onclick="history.back()">Back</button>
	<c:if test="${board.user.id == principal.user.id}">
		<a class="btn btn-warning" href="/board/${board.id }/updateForm">Edit</a>
		<button id="btn-delete" class="btn btn-danger">Delete</button>
	</c:if>
	<br /><br />
	<div>
		Post No: <span id="id"><i>${board.id} </i></span>
		Author: <span><i>${board.user.username} </i></span>
	</div>
	<br />
	<div>
		<h3>${board.title }</h3>
	</div>
	<hr>
	<div>
	  <div>${board.content }</div>
	</div>
	
	<div class="card">
		<form>
			<input type="hidden" id="userId" value="${principal.user.id}"/>
			<input type="hidden" id="boardId" value="${board.id}"/>
			<div class="card-body"><textarea id="reply-content" class="form-control" rows="1" cols=""></textarea></div>
			<div class="card-footer"><button type="button" id="btn-reply-save" class="btn btn-primary">등록</button></div>
		</form>
	</div>
	<br /> 
	<div class="card">
		<div class="card-header">댓글 리스트</div>
		<ul id="reply--box" class="list-group">	
			<c:forEach var="reply" items="${board.replies}">
				<li id="reply--1" class="list-group-item d-flex justify-content-between">
					<div>${reply.content }</div>
					<div class="d-flex">
						<div class="font-italic">작성자: ${reply.user.username} &nbsp;</div>
						<button class="badge">Delete</button>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp"%>

