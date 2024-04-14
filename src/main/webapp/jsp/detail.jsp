<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>상세 정보</title>
	<link rel="stylesheet" href="./css/default.css">
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<jsp:include page="./header.jsp"/>
	<div>
		<form action="./bookmark-add" method="post">
			<input type="hidden" name="mgr_no" value="${data.wifi.mgr_no}">
			<select name="gid">
				<option value="" selected disabled hidden>북마크 그룹 이름 선택</option>
				<c:forEach var="group" items="${groups}">
					<option value="${group.gid}">${group.name}</option>
				</c:forEach>
			</select>
			<button type="submit">북마크 추가하기</button>
		</form>
	</div>
	<div>
		<table>
			<tr>
				<th>거리(Km)</th>
				<td>${data.distance() }</td>
			</tr>
			<tr>
				<th>관리번호</th>
				<td>${data.wifi.mgr_no }</td>
			</tr>
			<tr>
				<th>자치구</th>
				<td>${data.wifi.wrdofc }</td>
			</tr>
			<tr>
				<th>와이파이명</th>
				<td><a href="./detail?mgr_no=${data.wifi.mgr_no}">${data.wifi.main_nm }</a></td>
			</tr>
			<tr>
				<th>도로명주소</th>
				<td>${data.wifi.adres1 }</td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td>${data.wifi.adres2 }</td>
			</tr>
			<tr>
				<th>설치위치(층)</th>
				<td>${data.wifi.instl_floor }</td>
			</tr>
			<tr>
				<th>설치유형</th>
				<td>${data.wifi.instl_ty }</td>
			</tr>
			<tr>
				<th>설치기관</th>
				<td>${data.wifi.instl_mby }</td>
			</tr>
			<tr>
				<th>서비스구분</th>
				<td>${data.wifi.svc_se }</td>
			</tr>
			<tr>
				<th>망종류</th>
				<td>${data.wifi.cmcwr }</td>
			</tr>
			<tr>
				<th>설치년도</th>
				<td>${data.wifi.cnstc_year }</td>
			</tr>
			<tr>
				<th>실내외구분</th>
				<td>${data.wifi.inout_door }</td>
			</tr>
			<tr>
				<th>WIFI접속환경</th>
				<td>${data.wifi.remars3 }</td>
			</tr>
			<tr>
				<th>X좌표</th>
				<td>${data.lnt() }</td>
			</tr>
			<tr>
				<th>Y좌표</th>
				<td>${data.lat() }</td>
			</tr>
			<tr>
				<th>작업일자</th>
				<td>${data.wifi.work_dttm }</td>
			</tr>
		</table>
	</div>
</body>
<script>
	if (localStorage.getItem("UID"))
		document.getElementById("uid").setAttribute("value", localStorage.getItem("UID"));

</script>
</html>