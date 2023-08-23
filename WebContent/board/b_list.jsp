<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
		border:0.5px solid #818181;
		text-align:center;
	}
	a {
		text-decoration:none;
		color:#000;
		font-weight:700;
		border:none;
		cursor:pointer;
		padding:10px;
		display:inline-block;
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
	.button-container {
		text-align: center;
	}
	.custom-button {
		padding: 10px 10px;
		background-color: #007bff;
		color: #fff;
      	border: none;
      	border-radius: 3px;
      	cursor: pointer;
	}
	
</style>
<body>
	<h1>무엇이든 적어보자 y게시판y 찡긋 </h1>
	<div style="text-align:center;">
		<form action="board-b_list.do" method="post">
			<select name="keyWord">
				<option value="title">제목</option>
				<option value="title&content">제목+내용</option>
				<option value="content">내용</option>
				<option value="u_id">작성자</option>
			</select>
			<input type="text" placeholder ="검색어 입력" name="search" id="keywordInput" value="${search}">
			<button id="searchBtn" type="button">검색</button>
			<input type="submit" value="검색">
		</form>
	</div>
	<table style="text-align:center;">
		<col width="50px">
		<col width="150px">
		<col width="200px">
		<col width="50px">
		<col width="70px">
		<col width="100px">
		<col width="50px">
			<tr>
				<th style="background-color:#eeeeee; text-align:center;">글번호</th>
				<th style="background-color:#eeeeee; text-align:center;">제목</th>
				<th style="background-color:#eeeeee; text-align:center;">내용</th>
				<th style="background-color:#eeeeee; text-align:center;">조회수</th>
				<th style="background-color:#eeeeee; text-align:center;">작성자</th>
				<th style="background-color:#eeeeee; text-align:center;">작성일</th>
				<th style="background-color:#eeeeee; text-align:center;">수정</th>
				
			</tr>
	
		<c:forEach items="${b_list }" var="board">
			<tr>
				<td><a href="board-b_detail2.do?b_idx=${board.b_idx}">${board.b_idx}</a></td>
				<td>
				<c:forEach begin="1" end="${board.b_depth}">&nbsp;</c:forEach>
				<c:if test="${board.b_depth !=0}">ㄴ</c:if>${board.b_title}</td>
				<td>${board.b_content}</td>
				<td>${board.b_view}</td>
				<td>${board.user.u_id}</td>
				<td>${board.b_date }</td>
				<td><a href="board-b_edit.do?b_idx=${board.b_idx }"><input type="button" value="수정"></a></td>
			</tr>
		</c:forEach>
	</table>
	<div class="button-container">
	    <a class="custom-button" href="board-b_insert.do" role="button">글쓰기</a>
	 </div>
<script>
$(document).on('click', '.searchBtn', function() {
	var url = document.location.href;
	url = url + "?keyWord="+$("#searchBtn").val();
	url = url + "&keyword="+$("#keywordInput").val();
	location.href= url;
});
</script>
</body>
</html>