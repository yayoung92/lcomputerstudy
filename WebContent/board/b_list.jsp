<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판</title>
</head>
<style>
	h1 {
		text-align:center;
	}
	table {
		border-collapse:collapse;
		margin:40px auto;
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
	}
	
	ul {
		width:600px;
		height:50px;
		margin:10px auto;
	}
	li {
		list-style:none;
		width:50px;
		line-height:50px;
		border:1px solid #ededed;
		float:left;
		text-align:center;
		margin:0 5px;
		border-radius:5px;
	} 
</style>
<body>
	<h1>무엇이든 적어보자 y게시판y 찡긋 </h1>
	<table style="text-align:center; border:1px solid #dddddd">
	
			<tr>
				<th style="background-color:#eeeeee; text-align:center;">글번호</th>
				<th style="background-color:#eeeeee; text-align:center;">제목</th>
				<th style="background-color:#eeeeee; text-align:center;">조회수</th>
				<th style="background-color:#eeeeee; text-align:center;">작성자</th>
				<th style="background-color:#eeeeee; text-align:center;">작성일시</th>
			</tr>
	
		<c:forEach items="${b_list}" var="board">
			<tr>
				<td>${board.b_idx}</td>
				<td>${board.b_title }</td>
				<td>${board.b_view}</td>
				<td>${board.b_writer}</td>
				<td>${board.b_date}</td>
			</tr>
		</c:forEach>

	</table>
	<div class='right-bos'>
		<a href="board-edit.do?u_idx=${board.b_idx}" style="width:70%;font-weight:700;background-color:#818181;color:#fff;" >글쓰기</a>
	</div>
	
	
</body>
</html>