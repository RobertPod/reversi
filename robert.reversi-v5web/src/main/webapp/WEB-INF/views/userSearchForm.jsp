<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="header.jsp" />
<%--  <title>...</title> --%>
<style>
table, th, td {
	border: 1px solid black;
	padding: 1px;
}

table {
	border-spacing: 0px;
}
</style>
</head>
<body>
	<strong>${message1}&nbsp;&nbsp;&nbsp;&nbsp;:-)</strong>
	<br />&nbsp;
	<br />

	<form:form action="userSearchForm">
	${message2}&nbsp;&nbsp;:-)
	<br />&nbsp;
	<br />
		<table>
			<tr>
				<td>Wprowadź <strong>Id</strong>:
				</td>
				<td><input type="text" name="searchID"></td>
			</tr>
			<tr>
				<td>lub</td>
				<td></td>
			</tr>
			<tr>
				<td>adres <strong>email</strong> szukanego użytkownika:
				</td>
				<td><input type="text" name="searchEmail"></td>
			</tr>
			<tr>
				<td><br /> <input type="submit" name="submit" value="Szukaj" /></td>
				<td><br /> <input type="submit" name="cancel" value="Wróć" /></td>
			</tr>
		</table>
	</form:form>
	<strong>${message3}&nbsp;&nbsp;&nbsp;&nbsp;:-)</strong>
	<br />&nbsp;
	<br />
	<c:if test="${formlist[0].userId > 0}">
		<c:if test="${fn:length(formlist) > 1}">
			<%!int data = 1;%>
			<%
				data = 1;
			%>
			<table>
				<tr align="center">
					<th>Lp.</th>
					<th>ID</th>
					<th>Nazwa</th>
					<th>Email użytkownika (login)</th>
					<th>Wiek</th>
					<th>Pierwsze logowanie</th>
				</tr>
				<c:forEach items="${formlist}" var="record">
					<tr align="left">
						<td><%=data++%></td>
						<td>${record.userId}</td>
						<td>${record.name}</td>
						<td>${record.email}</td>
						<td><c:if test="${record.age != 0}"> ${record.age} </c:if> <c:if
								test="${record.age == 0}">Nie podano</c:if></td>
						<td>${record.first_log}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${fn:length(formlist) == 1}">
			<form:form action="userSearchForm" modelAttribute="formlist"
				method="post">
				<table>
					<tr>
						<th>ID</th>
						<td>${formlist[0].userId}</td>
					</tr>
					<tr>
						<th>Nazwa użytkownika</th>
						<td>${formlist[0].name}</td>
					</tr>
					<tr>
						<th>Adres poczty użytkownika</th>
						<td>${formlist[0].email}</td>
					</tr>
					<tr>
						<th>Wiek użytkownika</th>
						<td><c:if test="${formlist[0].age != 0}"> ${formlist[0].age} </c:if>
							<c:if test="${formlist[0].age == 0}">Nie podano</c:if></td>
					</tr>
					<tr>
						<th>Pierwsze logowanie</th>
						<td>${formlist[0].first_log}</td>
					</tr>
				</table>
			</form:form>
		</c:if>
	</c:if>
</body>
</html>