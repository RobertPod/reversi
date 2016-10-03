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
		<tr>
			<td style="width: 45%; height: 60px;"><form:form
					action="LoginPlayersForm2" modelAttribute="form" method="POST">

					<input type="submit" name="cancel" value="Strona startowa"
						style="width: 302px;" />
				</form:form></td>
			<td></td>
		</tr>
	</table>

</body>
</html>