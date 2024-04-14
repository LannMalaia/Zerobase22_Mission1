<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>위치 히스토리 목록</title>
	<link rel="stylesheet" href="./css/default.css">
</head>
<body>
	<h1>위치 히스토리 목록</h1>
	<jsp:include page="./header.jsp"/>
	<div>
		<table>
			<tr>
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>
			</tr>
			<c:if test="${histories.size() == 0}">
				<tr>
					<td colspan="5" align="center">아직 검색 기록이 없어요.</td>
				</tr>
			</c:if>
			<c:forEach var="data" items="${histories}">
				<tr>
					<td>${data.hid }</td>
					<td>${data.x }</td>
					<td>${data.y }</td>
					<td>${data.formattedCDate() }</td>
					<td align="center" >
						<form action="./history" method="post">
							<input type="hidden" name="id" value="${data.hid}">
							<button onclick="submit">삭제</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>

</html>