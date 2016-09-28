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
<jsp:include page="Header.jsp" />
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
		<form:form action="LogginPageForm" modelAttribute="form" method="POST">
			<tr>
				<td style="width: 45%;"><input type="submit" formmethod="post"
					style="color: red; width: 300px;" name="gameboard"
					value="Graj bez logowania" /></td>
				<td>login (e-mail):</td>
			</tr>
			<tr>
				<td><input type="submit" formmethod="post"
					style="width: 300px;" name="newuser" value="Załóż nowe konto" /></td>
				<td><form:input path="email" id="email" style="width: 300px;" /></td>
			</tr>
			<tr>
				<td></td>
				<td>hasło:</td>
			</tr>
			<tr>
				<td><input type="submit" formmethod="post"
					style="width: 300px;" name="gamerules" value="Zasady gry" /></td>
				<td><form:password path="pass" id="haslo" style="width: 300px;" /></td>
			</tr>
			<tr>
				<td><input type="submit" formmethod="post"
					style="width: 300px;" name="aboutgameandme"
					value="Poczytaj o projekcie i kodowaniu" /></td>
				<td><input type="submit" formmethod="post"
					style="width: 300px;" name="login" value="Zaloguj" /></td>
			</tr>
		</form:form>
	</table>

</body>
</html>