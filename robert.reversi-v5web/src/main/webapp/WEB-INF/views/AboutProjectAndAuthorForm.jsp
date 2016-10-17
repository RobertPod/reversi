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
	<table>
		<tr>
			<td style="width: 45%; height: 60px;"><span></span></td>
			<td><strong>Plug... About author.<br /> <a
					href="https://www.linkedin.com/in/robertpodsiadly" target="_blank">Profil
						w serwisie LinkedIn</a><br /> <a
					href="https://github.com/RobertPod/reversi" target="_blank">Źródła
						strony w serwisie GitHub</a>
			</strong></td>

		</tr>
		<tr>
			<td><form:form action="AboutProjectAndAuthorForm"
					modelAttribute="form" method="POST">

					<input type="submit" name="cancel" value="Strona startowa"
						style="width: 302px;" />
				</form:form></td>
			<td></td>
		</tr>
	</table>
</body>
</html>

