<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
<h2>게시글 수정</h2>
	  <form action="board-b_edit-process.do" name="board" method="post">
		<input type="hidden" name="b_idx" value="${board.b_idx}">
	   		<p> 제목 : <input type="text" name="edit_b_title" value="${board.b_title}"></p>
	   		<p> 내용 : <input type="text" name="edit_b_content" value="${board.b_content}"></p>
	   		<p> 작성자 : <input type="text" name="edit_b_writer" value="${board.user.u_id}" readonly></p>
	   		<p> 수정일시 : <input type="date" name="edit_b_date" value="${board.b_date}"></p>
	   		<p> <input type="submit" value="수정완료"></p>
	   		<p> <a href="board-b_delete-process.do?b_idx=${board.b_idx}"><input type="button" value="삭제"></a></p>
     	 </form>
</body>
</html>