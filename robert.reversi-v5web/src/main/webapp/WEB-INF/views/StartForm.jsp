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
</head>
<body>
	Cześć!!!
	<br />
	<strong>${message}</strong>

	<form:form action="StartForm" method="POST">
		<table>
			<tr>
				<td><input type="submit" formmethod="post"
					style="width: 180px;" name="error" value="Error" /></td>
				<td><input type="submit" formmethod="post"
					style="width: 180px;" name="newuser" value="Nowy użytkownik" /></td>

			</tr>
			<tr>
				<td colspan="2"><input type="submit" formmethod="post"
					style="width: 364px;" name="finduser" value="Pokaż użytkowników" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" formmethod="post"
					style="color: red; width: 364px;" name="gameboard"
					value="Plansza gry" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>