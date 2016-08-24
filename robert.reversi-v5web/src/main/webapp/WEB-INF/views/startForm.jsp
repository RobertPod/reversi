<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="header.jsp"/> <%--  <title>...</title> --%>
</head>
<body>
	Cześć!!!
	<br />
	<strong>${message} = :-) = </strong>
	<form:form action="startForm" method="POST">
		<table>
			<tr>
				<td><input type="submit" name="error" value="Error" /></td>
				<td><input type="submit" name="newuser" value="Nowy użytkownik" /></td>

			</tr>
			<tr>
				<td colspan="3"><input type="submit" name="finduser"
					value="Pokaż użytkowników" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" name="gameboard"
					value="Plansza gry" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>