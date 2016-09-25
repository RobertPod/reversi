<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="header.jsp" />
<%--  <title>...</title> --%>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 800px;
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
			<td><strong>${startMessage}</strong></td>

		</tr>
	</table>
	<table>
		<form:form action="UserPageForm" method="POST">
			<tr>
				<td style="width: 45%;"><input type="submit" formmethod="post"
					style="color: red; width: 300px;" name="gameboard" value="Graj" /></td>
				<td>${adresEmail}</td>
			</tr>
			<tr>
				<td><input type="submit" formmethod="post"
					style="width: 300px;" name="loginPlayers"
					value="Aktualnie zalogowani gracze" /></td>
				<td>${ipAdress}</td>
			</tr>
			<tr>
				<td><input type="submit" formmethod="post"
					style="width: 300px;" name="yourLogins"
					value="Zobacz swoje rozgrywki" /></td>
				<td>${accountCreate}</td>
			</tr>
			<tr>
				<td><input type="submit" formmethod="post"
					style="width: 300px;" name="yourGames"
					value="Zobacz swoje rozgrywki" /></td>
				<td>${lastLoginDate}</td>
			</tr>
			<tr>
				<td><input type="submit" formmethod="post"
					style="width: 300px;" name="gamerules" value="Zasady gry" /></td>
				<td>${gamesWinLost}</td>
			</tr>
			<tr>
				<td><input type="submit" formmethod="post"
					style="width: 300px;" name="aboutgameandme"
					value="Poczytaj o projekcie i kodowaniu" /></td>
				<td><input type="submit" formmethod="post"
					style="width: 300px;" name="logout" value="Wyloguj" /></td>
			</tr>
		</form:form>
	</table>

</body>
</html>