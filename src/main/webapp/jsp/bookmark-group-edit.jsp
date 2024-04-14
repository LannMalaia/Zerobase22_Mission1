<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>북마크 그룹 수정</title>
	<link rel="stylesheet" href="./css/default.css">
</head>
<body>
	<h1>북마크 그룹 수정</h1>
	<jsp:include page="./header.jsp"/>
	<div>
		<form action="./bookmark-group-edit" method="post">
			<input type="hidden" name="gid" value="${group.gid}">
			<table>
				<tr>
					<th>북마크 이름</th>
					<td>
						<input type="text" name="name" value="${group.name}">
					</td>
				</tr>
				<tr>
					<th>순서</th>
					<td>
						<input type="number" name="level" value="${group.level}">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<a href="./bookmark-group">돌아가기</a>|
						<button type="submit">수정</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>