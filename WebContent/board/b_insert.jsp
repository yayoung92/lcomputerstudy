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
	<form action="board-b_insert-process.do" name="board" method="post" enctype="multipart/form-data">
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
	 		<td>작성자 : </td>
			<td>${sessionScope.user.u_name }</td>
		</tr> 
		<tr>
			<td>첨부파일 : </td>
			<td><input type="file" name="fileName"><br></td>
		</table>
		<input type="submit" value="글등록">
	</form>
	<a href="board-b_list.do">취소</a>
</body>
</html>