<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글 쓰기</title>
</head>
<body>
	<h3>답글 달기</h3>
	<form action="board-b_reply-process.do" name="board" method="post">
		<input type="hidden" name="b_idx" value="${b_idx}"> 
		<table>
			<tr>
				<td>제목 </td>
					<td><input type="text" name="title" value="[답변]" size="60"></td>
				</tr>
			<tr>
				<td>내용 </td>
				<td><textarea rows="5" cols="50" name="content"></textarea>
			</td>
			</tr>
		  	<tr>
		 		<td>작성자 </td>
		 	  	<td>${sessionScope.user.u_name }</td>
				<!--  td><input type="text" name="user">${board.u_idx }</td -->
			</tr>
			 
		</table>
	
		<input type="submit" value="글등록">
	</form>
	
</body>
</html>