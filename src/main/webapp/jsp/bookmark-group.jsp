<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>북마크 그룹 관리</title>
	<link rel="stylesheet" href="./css/default.css">
</head>
<body>
	<h1>북마크 그룹 관리</h1>
	<jsp:include page="./header.jsp"/>
	<div>
		<form action="./bookmark-group-add" method="get">
			<button type="submit">북마크 그룹 이름 추가</button>
		</form>
	</div>
	<div>
		<table>
			<tr>
				<th>ID</th>
				<th>북마크 이름</th>
				<th>순서</th>
				<th>등록일자</th>
				<th>수정일자</th>
				<th>비고</th>
			</tr>
			<c:if test="${groups.size() == 0}">
				<tr>
					<td colspan="6" align="center">만들어놓은 그룹이 없어요.</td>
				</tr>
			</c:if>
			<c:forEach var="data" items="${groups}">
				<tr>
					<td>${data.gid}</td>
					<td>${data.name}</td>
					<td>${data.level}</td>
					<td>${data.formattedCDate()}</td>
					<td>${data.formattedUDate()}</td>
					<td align="center" >
						<form action="./bookmark-group-edit" method="get">
							<input type="hidden" name="gid" value="${data.gid}">
							<button onclick="submit">수정</button>
						</form>
						<form action="./bookmark-group-delete" method="get">
							<input type="hidden" name="gid" value="${data.gid}">
							<input type="hidden" name="gid" value="${data.gid}">
							<button onclick="submit">삭제</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>

</html>