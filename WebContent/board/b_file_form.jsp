<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<form action="board-b_insert-process.do" method="post" enctype="multipart/form-data">
		<h3>파일 업로드 폼</h3>
		작성자 : ${sessionScope.user.u_name }<br>
		파일명 : <input type="file" name="fileName"><br>
		파일설명 <br/><textarea name="fileComment" rows="5" cols="30"></textarea><br>
		<input type="submit" value="파일 등록">
	</form>
</body>
</html>