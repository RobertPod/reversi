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
			<td><strong>Plug... Game rules.<br />
			<a href="https://pl.wikipedia.org/wiki/Reversi" target="_blank">Reversi w Wikipedii</a><br />
			<a href="http://www.othellomania.pl/" target="_blank">Strona maniaków Reversi (Othello) - historyczna</a>
			</strong></td>

		</tr>
		<tr>
			<td><form:form action="GameRulesForm" modelAttribute="form"
					method="POST">

					<input type="submit" name="cancel" value="Strona startowa"
						style="width: 302px;">
				</form:form></td>
			<td></td>
		</tr>
	</table>
</body>
</html>