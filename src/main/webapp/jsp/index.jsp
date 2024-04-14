<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>인덱스 페이지</title>
	<script text="text/javascript" src="./js/location.js"></script>
	<link rel="stylesheet" href="./css/default.css">
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<jsp:include page="./header.jsp"/>
	<div>
		<form action="./wifi-get" method="get">
			LAT: <input type="text" name="lat" id="lat">,
			LNT: <input type="text" name="lnt" id="lnt">
			<button type="button" onclick="getGPS()">내 위치 가져오기</button>
			<button type="submit" >근처 WIFI 정보 보기</button>
		</form>
	</div>
	<div>
		<table>
			<tr>
				<th>거리(Km)</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>WIFI접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>작업일자</th>
			</tr>
			<c:if test="${wifiList == null}">
				<tr>
					<td colspan="17" align="center">위치 정보를 입력한 뒤 정보 보기 버튼을 눌러 조회하세요.</td>
				</tr>
			</c:if>
			<c:forEach var="data" items="${wifiList}">
				<tr>
					<td>${data.distance() }</td>
					<td>${data.wifi.mgr_no }</td>
					<td>${data.wifi.wrdofc }</td>
					<td>
						<a href="./detail?mgr_no=${data.wifi.mgr_no}">${data.wifi.main_nm }</a>
					</td>
					<td>${data.wifi.adres1 }</td>
					<td>${data.wifi.adres2 }</td>
					<td>${data.wifi.instl_floor }</td>
					<td>${data.wifi.instl_ty }</td>
					<td>${data.wifi.instl_mby }</td>
					<td>${data.wifi.svc_se }</td>
					<td>${data.wifi.cmcwr }</td>
					<td>${data.wifi.cnstc_year }</td>
					<td>${data.wifi.inout_door }</td>
					<td>${data.wifi.remars3 }</td>
					<td>${data.lat() }</td>
					<td>${data.lnt() }</td>
					<td>${data.wifi.work_dttm }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>