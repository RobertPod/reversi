<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="header.jsp" />
<%--  <title>...</title> --%>
</head>
<body>
	<strong>${messageWlk}</strong>
	<form:form action="createNewUserForm" modelAttribute="form"
		method="post">
		<table>
			<tr>
				<td><strong>Imię</strong> / tak będzie nazywał Cię
					komputer&nbsp;&nbsp;<br />tak będą Cię widzieć inni.&nbsp;&nbsp;<br />Minimum
					3 znaki, maksimum 20:</td>
				<td style="vertical-align: bottom"><form:input path="name"
						id="imie" maxlength="20"></form:input></td>
				<td style="vertical-align: bottom">${errorName}</td>
			</tr>
			<tr>
				<td><strong>Adres email</strong> Nie zbieram adresów,
					&nbsp;&nbsp;<br />więc może być fikcyjny, byle poprawny
					formalnie,&nbsp;&nbsp;<br />aktywny adres email umożliwi reset
					hasła &nbsp;&nbsp;<br />i żadania informacji o wynikach gier:</td>
				<td style="vertical-align: bottom"><form:input path="email"
						id="email"></form:input></td>
				<td style="vertical-align: top">${messageWlk}</td>
			</tr>
			<%-- <tr>
				<td>Wiek:</td>
				<td><form:input path="age" id="wiek"></form:input></td>
			</tr> --%>
			<tr>
				<td><strong>Hasło</strong> powinno zawierać minimum 6
					znaków&nbsp;&nbsp;<br />w tym dużą i małą literę oraz cyfrę:</td>
				<td style="vertical-align: bottom"><form:password path="pass"
						id="haslo"></form:password></td>
				<td></td>
			</tr>
			<tr>
				<td><strong>Powtórz hasło</strong>:</td>
				<td style="vertical-align: bottom"><form:password path="pass2"
						id="haslo2"></form:password></td>
				<td></td>
			</tr>
			<tr>
				<td><strong>Akceptuję regulamin</strong>:</td>
				<td style="vertical-align: bottom"><form:checkbox
						path="acceptRules" id="acceptRules"></form:checkbox></td>
				<td></td>
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