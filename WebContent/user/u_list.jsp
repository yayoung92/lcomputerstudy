<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<table>
	<tr>
		<td colspan="4">전체 회원 수 : ${pagination.count}</td>
	<tr>
		<th>No</th>
		<th>ID</th>
		<th>이름</th>
		<th>등급</th>
	</tr>
	<c:forEach items="${list}" var="user" varStatus="status">
		<tr >
			<td><a href="user-detail.do?u_idx=${user.u_idx}">${user.u_idx}</a></td>
			<td>${user.u_id }</td>
			<td>${user.u_name }</td>
			<td>${user.levelname }</td>
		</tr>
	</c:forEach>
</table>

