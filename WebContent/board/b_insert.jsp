<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성하기</title>
</head>
<body>
	<h2> 글 작성하기 </h2>
	<form action="board-b_insert-process.do" name="board" method="post">
	<table>
	<tr>
		<td>제목 </td>
			<td><input type="text" name="title"></td>
		</tr>
	<tr>
		<td>내용 </td>
		<td><textarea rows="5" cols="50" name="content"></textarea>
	</td>
	</tr>
 	<tr>
 		<td>작성자 </td>
		<td><input type="text" name="user"></td>
	</tr>
<%--	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="pw" value="${board.user.u_pw }"></td>
	<tr>
		<td>작성일시</td>
		<td><input type="date" name="date" value="${board.b_date}" readonly></td>
		</tr> --%> 
	</table>
	<input type="submit" value="글등록">
</form>
	
</body>
</html>