<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<c:choose>
				<c:when test="${comment.user.u_id eq sessionScope.user.u_id}">
					<button type="button" class="reReDelete" cidx="${comment.c_idx }" bidx="${board.b_idx }">삭제</button>
					<button type="button" class="reEdit">수정</button>
				</c:when>
				<c:when test="${sessionScope.user.u_level >= 5 }">
					<button type="button" class="reReDelete" cidx="${comment.c_idx }" bidx="${board.b_idx }">삭제</button>
				</c:when>
			</c:choose>
		<button type="button" class="reReply">대댓글</button>
	</div>
	<div style="display: none;">
		<textarea rows="2" cols="80"></textarea>
		<button type="button" class="reReInsert" cidx="${comment.c_idx}" bidx="${board.b_idx }">등록</button>
		<button type="button" class="reDelete">취소</button>
	</div>
	<div style="display: none;">
		<textarea rows="2" cols="80">${comment.c_content }</textarea>
		<button type="button" class="reInsert" cidx="${comment.c_idx}" bidx="${board.b_idx }">수정완료</button>
		<button type="button" class="reDelete">취소</button>
	</div>
</c:forEach>
