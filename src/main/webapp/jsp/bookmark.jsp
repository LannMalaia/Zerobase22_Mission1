<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>북마크 목록</title>
	<link rel="stylesheet" href="./css/default.css">
</head>
<body>
	<h1>북마크 목록</h1>
	<jsp:include page="./header.jsp"/>
	<div>
		<table>
			<tr>
				<th>ID</th>
				<th>북마크 그룹</th>
				<th>와이파이명</th>
				<th>등록일자</th>
				<th>비고</th>
			</tr>
			<c:if test="${bookmarks.size() == 0}">
				<tr>
					<td colspan="5" align="center">아직 북마크를 등록하지 않았어요.</td>
				</tr>
			</c:if>
			<c:forEach var="bookmark" items="${bookmarks}">
				<tr>
					<td>${bookmark.bid}</td>
					<td>${bookmark.groupName}</td>
					<td><a href="./detail?mgr_no=${bookmark.wifiMgrNo}">${bookmark.wifiName}</a></td>
					<td>${bookmark.formattedCDate()}</td>
					<td align="center">
						<a href="./bookmark-delete?bid=${bookmark.bid}&gid=${bookmark.gid}">삭제</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>

</html>