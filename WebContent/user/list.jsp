<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원목록2</title>
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
	<h1>회원 목록</h1>
	<div style="text-align: center;" class="userLevelDiv">
		<c:if test="${user.u_level >= 5 }">
			<button type="button" class="userLevel" >레벨 설정</button>
		</c:if>
	</div>
	<div id="userLevelName">
		<table>
			<tr>
				<td colspan="4">전체 회원 수 : ${pagination.count}</td>
			<tr>
				<th>No</th>
				<th>ID</th>
				<th>이름</th>
				<th>등급</th>
			</tr>
			<c:forEach items="${list}" var="user" varStatus="status" >
				<tr>
					<td><a href="user-detail.do?u_idx=${user.u_idx}">${user.u_idx}</a></td>
					<td>${user.u_id }</td>
					<td>${user.u_name }</td>
					<td>${user.levelname }</td>
				</tr>
			</c:forEach>
		</table>
	</div>	
	<div style="text-align: center;">
		<a href="board-b_list.do" ><input type="button" value="게시판 목록"></a>
	</div>
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
<script>

$(document).on('click', '.userLevel', function() {
	let targetUserIdx = prompt("레벨을 설정할 사용자의 ID를 입력하세요:", "");   
	let newLevel = prompt("새로운 회원 등급을 입력하세요:", "");
	
	$.ajax({
		method: "POST",
		url: "aj-user-level-update.do",
		data: { u_level: newLevel, u_id: targetUserIdx }
	 })
	 .done(function(msg) {
		 $('#userLevelName').html(msg);
	 });
});
</script>
</body>
</html>