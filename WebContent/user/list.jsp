<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원목록2</title>
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
	.toolbar {
		text-align: right;
		margin-bottom: 10px;
	}
</style>
<body>
	<h1>회원 목록</h1>
	<div class="toolbor">
		<input type="button" value="관리자">
	</div>
	<table>
		<tr>
			<td colspan="4">전체 회원 수 : ${pagination.count}</td>
		</tr>
		<tr>
			<th>No</th>
			<th>ID</th>
			<th>이름</th>
			<th>권한</th>
		</tr>
		<c:forEach items="${list}" var="user" varStatus="status">
			<tr>
				<td><a href="user-detail.do?u_idx=${user.u_idx}">${user.u_idx}</a></td>
				<td>${user.u_id}</td>
				<td>${user.u_name}</td>
				<td><input type="button" value="관리자"></td>
			</tr>
		</c:forEach>
	</table>

	<div>
		<ul>
			 <c:choose>
				<c:when test="${ pagination.page > 5}">
					<li><a href="user-list.do?page=${pagination.prevPage}">◀</a></li>
				</c:when>
			</c:choose> 
			<c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
					<c:choose>
						<c:when test="${ pagination.page == i }">
							<li style="background-color:#ededed;">
								<span>${i}</span>
							</li>
						</c:when>
						<c:when test="${ pagination.page != i }">
							<li><a href="user-list.do?page=${i}">${i}</a></li>
						</c:when>
					</c:choose>
			</c:forEach>
			 <c:choose>
				<c:when test="${ pagination.page <= pagination.endPage && pagination.page < pagination.lastPage}">
					<li style=""><a href="user-list.do?page=${pagination.nextPage}">▶</a></li>
				</c:when>
			</c:choose> 
		</ul>
	</div>
</body>
</html>