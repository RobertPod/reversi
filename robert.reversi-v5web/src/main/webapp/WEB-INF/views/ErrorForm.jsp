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
	Cześć!!! Coś poszło nie tak!
	<br /> ${"curl http://httpbin.org/ip"}
	<br /> curl -o ip: http://httpbin.org/ip
	<?php
		phpinfo();
	?>
</body>
</html>