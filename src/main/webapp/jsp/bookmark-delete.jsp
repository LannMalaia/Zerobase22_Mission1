<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>북마크 삭제</title>
	<link rel="stylesheet" href="./css/default.css">
</head>
<body>
	<h1>북마크 삭제</h1>
	<jsp:include page="./header.jsp"/>
	<div>북마크를 삭제하시겠습니까?</div>
	<div>
		<form action="./bookmark-delete" method="post">
			<input type="hidden" name="bid" value="${bookmark.bid}">
			<table>
				<tr>
					<th>북마크 그룹</th>
					<td>${bookmark.groupName}</td>
				</tr>
				<tr>
					<th>와이파이명</th>
					<td>${bookmark.wifiName}</td>
				</tr>
				<tr>
					<th>등록일자</th>
					<td>${bookmark.formattedCDate()}</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<a href="./bookmark">돌아가기</a>|
						<button type="submit">삭제</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>