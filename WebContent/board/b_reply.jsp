<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글 쓰기</title>
</head>
<body>
	<h3>답글 달기</h3>
	<form action="board-b_reply-process.do" name="board" method="post">
		<table>
		<tr>
			<td>제목 </td>
				<td><input type="text" name="title"></td>
			</tr>
		<tr>
			<td>내용 </td>
			<td><textarea rows="5" cols="50" name="content"></textarea>
		</td>
		</tr>
	 	<tr>
	 		<td>작성자 </td>
			<td><input type="text" name="user">${board.user.u_idx }</td>
		</tr> 
		 <tr>
        <td align="center" colspan="2"><input type="hidden" name="b_groub" value="${b_groub}">
            <input type="hidden" name="b_order" value="${b_order}"> 
            <input type="hidden" name="b_depth" value="${b_depth}"> 
    </tr>
		</table>
	</form>
	<input type="submit" value="글등록">
</body>
</html>