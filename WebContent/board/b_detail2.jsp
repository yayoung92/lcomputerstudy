<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<style>
	table {
		border-collapse:collapse;
	}
	table tr th {
		font-weight:700;
	}
	table tr td, table tr th {
		border:1px solid #818181;
		width:200px;
		text-align:center;
	}
	a {
		text-decoration:none;
		color:#000;
		font-weigth:700;
		border:none;
		cursor:pointer;
		padding:10px;
		display:inline-block;
	}

</style>
<body>
	<h1>게시글 상세페이지</h1>
	<form action="board-b_detail2.do" name="board" method="post">
	<input type="hidden" name="b_idx" value="${board.b_idx}">
		<table>
			 <tr>
				<td>게시글 번호</td>
				<td>${board.b_idx}</td>
			</tr>
		
			<tr>
				<td>작성자</td>
				<td>${board.user.u_id}</td>
			</tr>
			<tr>
				<td>제목</td>	
				<td>${board.b_title}</td>
			</tr>

			<tr>
				<td>내용</td>
				<td>${board.b_content}</td>
			</tr>

			<tr>
				<td>날짜</td>	
				<td>${board.b_date}</td>
			</tr>
				
		</table>
	<a href="board-b_list.do"><input type="button" value="돌아가기"></a>
	<a href="board-b_reply.do?b_idx=${board.b_idx}"><input type="button" value="답글"></a>
	</form>
	<h3>댓글 리스트</h3>
		<hr>
	  	<c:forEach items="${board.commentList}" var="comment">
	  	<div class="commentList">
		  	<hr>
				${comment.user.u_id}<br>
			<c:forEach begin="1" end="${comment.c_depth}">&nbsp;</c:forEach>
			<c:if test="${comment.c_depth !=0}">ㄴ</c:if>
				${comment.c_content }<br>
				${comment.c_date }<br>
		</div>
		<div>
			<form action="c_delete.do" method="post">
				<input type="hidden" name="b_idx" value="${board.b_idx }">
				<input type="hidden" name="c_idx" value="${comment.c_idx }">
				<input type="submit" value="삭제">
				<a href="board-c_reComment.do?c_idx=${comment.c_idx}"><input type="button" value="대댓글"></a>
				<button type="button" class="reEdit">수정</button>
				<button type="button" class="reReply">대댓글</button>
			</form>
		</div>
		<div style="display: none;">
			<textarea rows="2" cols="80"></textarea>
			<button type="button" class="reInsert" cidx2="${comment.c_idx}">등록</button>
			<button type="button" class="reDelete">취소</button>
		</div>
		<div style="display: none;">
			<textarea rows="2" cols="80">${comment.c_content }</textarea>
			<button type="button" class="reInsert" cidx="${comment.c_idx}">수정완료</button>
			<button type="button" class="reDelete">취소</button>
		</div>
	<!--	<div id="commentList">
			<ul>
				<li>${comment.user.u_id}</li>
				<li>${comment.c_content }</li>
				<li>${comment.c_date }</li>
			</ul>
		</div> -->
		</c:forEach>
		<hr>

	<form action="c_comment.do" name="comment" method="post">
	<h3>댓글 달기</h3> 
	<input type="hidden" name="b_idx" value="${board.b_idx}"> 
		<div>작성자 : ${sessionScope.user.u_name }</div>
		<div>
			<textarea rows="5" cols="50" name="content"></textarea>
			<input type="submit" value="글등록">
		</div>

	</form>
<script>
$(document).on('click', '.reInsert', function () {
	let cIdx = $(this).attr('cidx');
	let comment = $(this).prev().val();
	
	$.ajax({
		method: "POST",
		url: "aj-comment-update.do",
		data: { c_idx: cIdx, c_comment: comment }
	})
	.done(function( msg ) {
		alert( "Data Saved: " + msg );
	});
});
/*$(document).on('click', '.reInsert', function () {
	
	let comment = $(this).prev().val();
});*/
$(document).on('click', '.reReply', function () {
	$(this).parent().parent().next().css('display', '');
	let cIdx = $(this).attr('cidx2');
	let comment = taxtarea.val();
	
	$.ajax({
		method: "POST",
		url: "aj-comment-update.do",
		data: { c_idx: cIdx, c_comment: comment }
	})
	.done(function( msg ) {
		alert( "Data Saved: " + msg );
	});
});
$(document).on('click', '.reDelete', function() {
	$(this).parent().toggle();
});
$(document).on('click', '.reInsert', function() {
	 var textarea = $(this).prev("textarea");
     var content = textarea.val();
     if (content.trim() === "") {
         alert("대댓글 내용을 입력하세요.");
         return;
     };
});
$(document).on('click', '.reEdit', function() {
	 /*var editForm = $(this).parent().parent().prev();
	 var content = "${comment.c_content }";*/
	 $(this).parent().parent().next().next().css('display', '');
});
</script>
</body>
</html>