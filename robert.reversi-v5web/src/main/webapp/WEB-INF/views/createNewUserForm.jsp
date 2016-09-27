<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<script src='https://www.google.com/recaptcha/api.js'></script>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
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
			<td style="width: 45%; height: 50;"><span>Gracz: </span><span
				style="font-size: 150%; color: red;"><strong>&nbsp;&nbsp;nieznany&nbsp;&nbsp;</strong></span></td>
			<td><strong>${messageWlk}</strong></td>

		</tr>
	</table>
	<form:form action="createNewUserForm" modelAttribute="form"
		method="post">
		<table>
			<tr>
				<td style="width: 45%; height: 45px"><strong>Imię</strong>: tak
					będzie nazywał Cię komputer tak będą Cię widzieć inni.&nbsp;&nbsp;<br />Minimum
					3 znaki, maksimum 20:</td>
				<td style="width: 25%; vertical-align: bottom"><form:input
						style="width: 300px;" path="name" id="imie" maxlength="20"
						tabindex="0"></form:input></td>
				<td style="width: 30%; vertical-align: bottom">${errorName}</td>
			</tr>
			<tr>
				<td height="45px"><strong>Adres email</strong>: Nie zbieram
					adresów, więc może być fikcyjny, byle poprawny formalnie, aktywny
					adres email umożliwi reset hasła i żadania informacji o wynikach
					gier:</td>
				<td style="vertical-align: bottom"><form:input path="email"
						id="email" style="width: 300px;" tabindex="0"></form:input></td>
				<td style="vertical-align: bottom">${errorEmail}</td>

			</tr>
			<%-- <tr>
				<td>Wiek:</td>
				<td><form:input path="age" id="wiek"></form:input></td>
			</tr> --%>
			<tr>
				<td height="45px"><strong>Hasło</strong>: powinno zawierać
					minimum 6 znaków w tym wielką i małą literę &nbsp;&nbsp;<br />oraz
					cyfrę i przynajmniej jeden ze znaków "@#$%!&":</td>
				<td style="vertical-align: bottom"><form:password path="pass"
						id="haslo" style="width: 300px;"></form:password></td>
				<td style="vertical-align: bottom">${errorPass}</td>
			</tr>
			<tr>
				<td height="45px"><strong>Powtórz hasło</strong>:</td>
				<td style="vertical-align: bottom"><form:password path="pass2"
						id="haslo2" style="width: 300px;"></form:password></td>
				<td style="vertical-align: bottom">${errorPass2}</td>
			</tr>
			<tr>
				<td height="45px"><strong>Akceptuję regulamin</strong>:</td>
				<td style="vertical-align: bottom"><form:checkbox
						path="acceptRules" id="acceptRules"></form:checkbox></td>
				<td style="vertical-align: bottom">${errorAccept}</td>
			</tr>
			<tr>
				<td height="45px"><strong>Jestem człowiekiem</strong>:</td>
				<td style="vertical-align: bottom">
					<div class="g-recaptcha"
						data-sitekey="6Lda5ikTAAAAADczo6VbLctYg_QHNSACoNxR7IrE"></div>
				</td>
				<td style="vertical-align: bottom">${errorHuman}</td>
			</tr>
			<tr>
				<td><br /> <input type="submit" name="cancel"
					value="Strona startowa" style="width: 302px;" /></td>
				<td colspan="2"><br /> <input type="submit" name="submit"
					value="Zarejestruj się" style="width: 302px;" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>