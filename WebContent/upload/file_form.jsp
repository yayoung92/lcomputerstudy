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
	<h1>파일 업로드</h1>
	
	<form method="post" action="upload" enctype="multipary/form-data" name="fileForm" onsubmit="return validateForm(this);">
		작성자 : <input type="text" name="id" value="value"><br>
		제목 : <input type="text" name="title"><br>
		첨부파일 : <input type="file" name="attach"><br>
		<input type="submit" value="전송하기">
	</form>

	<script>
		function validateForm(form) {
			if (form.name.value == "") {
				alert("작성자를 입력하세요.");
				form.name.focus();
				return false;
			}
			if (form.title.value == "") {
				alert("제목을 입력하세요.");
				form.title.focus();
				return false;
			}
			if (form.attach.value == "") {
				alert("첨부파일을 넣어주세요.");
				return false;
			}
		}
	</script>
</body>
</html>