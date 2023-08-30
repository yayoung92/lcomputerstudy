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
<form action="upload_file.jsp" method="post" enctype="multipart/form-data">
<table border=1>
	<tr>
		<td colspan=2 align="center"><h3>파일 업로드 폼</h3></td>
	</tr>
	<tr>
		<td>올린 사람 :</td><td><input type="text" name="name"></td>
	</tr>
	<tr>
		<td>제목 :</td><td><input type="text" name="subject"></td>
	</tr>
	<tr>
		<td>파일명1 :</td><td><input type="file" name="fileeName1"></td>
	</tr>
	<tr>
		<td>파일명2 :</td><td><input type="file" name="fileName"></td>
	</tr>
	<tr>
		<td colspan=2 align="center"><input type="submit" value="전송"></td>
	</tr>
</table>
</form>
</body>
</html>