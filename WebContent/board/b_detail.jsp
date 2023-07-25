<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		font-weight:700;
		border:none;
		cursor:pointer;
		padding:10px;
		
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
				<td>${board.b_writer}</td>
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
	<a href="board-b_list.do" style="width:70%;font-weight:700;background-color:#818181;color:#fff;" >돌아가기</a>
	</form>
</body>
</html>