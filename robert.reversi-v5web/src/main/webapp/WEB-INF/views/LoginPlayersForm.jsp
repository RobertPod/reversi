<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="Header.jsp" />
<%--  <title>...</title> --%>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 4px;
	height: 40px
}

body {
	font-family: arial, sans-serif;
	text-align: left;
}
</style>
</head>
<body>
	<table>
		<tr>
			<td style="width: 45%; height: 60px;"><span>Gracz: </span>${playerName}</td>
			<td><strong>Chwilowo ostatnie 20 sesji.<br> W
					trakcie pisania kodu ekran "zalogowani gracze" byłby zawsze pusty.
			</strong></td>

		</tr>
	</table>
	<c:if test="${sessionDisplayDataDTO[0].userName != ''}">
		<%!int data = 1;%>
		<%
			data = 1;
		%>
		<table>
			<tr align="center">
				<th>Lp.</th>
				<th>Imię</th>
				<th>Login Time</th>
				<th>Logout Time</th>
				<th>Host</th>
				<th>IP</th>
				<th>Wygrane</th>
				<th>Przegrane</th>
			</tr>
			<c:forEach items="${sessionDisplayDataDTO}" var="item">
				<tr>
					<td><%=data++%></td>
					<td>${item.userName}</td>
					<td>${item.loginTime}</td>
					<td>${item.logoutTime}</td>
					<td>${item.loginHostname}</td>
					<td>${item.ipAdress}</td>
					<td>${item.winGames}</td>
					<td>${item.lostGames}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<table>
		<tr>
			<td style="width: 45%; height: 60px;"><form:form
					action="LoginPlayersForm" modelAttribute="form" method="POST">

					<input type="submit" name="cancel" value="Strona startowa"
						style="width: 302px;">
				</form:form></td>
			<td></td>
		</tr>
	</table>

</body>
</html>