<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Nowy użytkownik</title>
</head>
<body>
	<strong>${message}&nbsp;&nbsp;&nbsp;&nbsp;:-)</strong>
	<br />&nbsp;
	<br />

	<form:form action="newuserform" modelAttribute="form" method="post">
		<table>
			<tr>
				<td>Imię:</td>
				<td><form:input path="name" id="imie"></form:input> <c:if
						test="${pageContext.request.method=='POST'}">
						<form:errors path="name" />
					</c:if></td>
			</tr>
			<tr>
				<td>Adres email:</td>
				<td><form:input path="email" id="email"></form:input> <c:if
						test="${pageContext.request.method=='POST'}">
						<form:errors path="email" />
					</c:if></td>
			</tr>
			<tr>
				<td>Wiek:</td>
				<td><form:input path="age" id="wiek"></form:input> <c:if
						test="${pageContext.request.method=='POST'}">
						<form:errors path="age" />
					</c:if></td>
			</tr>
			<tr>
				<td>Hasło:</td>
				<td><form:input path="pass" id="haslo"></form:input> <c:if
						test="${pageContext.request.method=='POST'}">
						<form:errors path="pass" />
					</c:if></td>
			</tr>
			<tr>
				<td>Powtórz hasło:</td>
				<td><form:input path="pass2" id="haslo2"></form:input> <c:if
						test="${pageContext.request.method=='POST'}">
						<form:errors path="pass2" />
					</c:if></td>
			</tr>
			<tr>
				<td><br /> <input type="submit" name="submit"
					value="Wyślij formularz" /></td>
				<td><br /> <input type="submit" name="cancel" value="Wróć" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>