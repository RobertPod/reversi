<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="header.jsp" />
<%--  <title>...</title> --%>
</head>
<body>
	Plug... about project and author.
	<br />&nbsp;
	<br />
	<form:form action="aboutProjectAndAuthorForm" modelAttribute="form" method="POST">

		<input type="submit" name="cancel" value="Strona startowa"
			style="width: 302px;" />
	</form:form>
</body>
</html>

