<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
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
	<form action="board-b_detail.do" name="board" method="post">
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
	<a href="board-b_reply.do"><input type="button" value="답글"></a>
	</form>
</body>
</html>