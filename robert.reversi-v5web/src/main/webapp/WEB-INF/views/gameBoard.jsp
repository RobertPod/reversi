<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="header.jsp" />
<%--  <title>...</title> --%>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$('#myCanvas').click(function(e) {
			var $this = $(this); // or use $(e.target) in some cases;
			var offset = $this.offset();
			var width = $this.width();
			var height = $this.height();
			var posX = offset.left;
			var posY = offset.top;
			var x = e.pageX - posX;
			var y = e.pageY - posY;
			document.getElementById('x').value = x;
			document.getElementById('y').value = y;
			document.getElementById('width').value = width;
			document.getElementById('height').value = height;
			document.getElementById('posx').value = x;
			document.getElementById('posy').value = y;
			document.getElementById('gameBoardFormXY').submit();
		});
	});
</script>
<%-- dla kontroli - wyświtlenie pozycji na ekranie --%>
<script>
	$(document).ready(function() {
		$('#myCanvas').click(function(e) {
			var offset = $(this).offset();
			$('#x_axis').html(e.clientX - offset.left);
			$('#y_axis').html(e.clientY - offset.top);
		});
	});
</script>
</head>
<body>
	<%-- wysłanie parametrów kliknięcia na czerwonym obszrze  --%>
	<form:form action='gameBoard' id='gameBoardFormXY'
		modelAttribute="formXY" method='POST'>
		<form:input type='hidden' path="x" id="x" name="x"></form:input>
		<form:input type='hidden' path="y" id="y" name="y"></form:input>
		<form:input type='hidden' path="width" id="width" name="width"></form:input>
		<form:input type='hidden' path="height" id="height" name="height"></form:input>
		<form:input type='hidden' path="posX" id="posx" name="posx"></form:input>
		<form:input type='hidden' path="posY" id="posy" name="posy"></form:input>
	</form:form>

	<canvas id="myCanvas" width="320" height="320" style="border:1px solid">
	Your browser does not support the HTML5 canvas tag. </canvas>
	<br>
	<p>
		<strong>Note:</strong> The canvas tag is not supported in Internet
		Explorer 8 and earlier versions.
	</p>

	<p>
		<%-- dla kontroli - wyświtlenie pozycji na ekranie --%>
		<br /> X Axis: - <span id="x_axis">0</span> <br /> Y Axis: - <span
			id="y_axis">0</span>
	</p>

</body>
</html>

<%-- 
	 http://www.w3schools.com/html/html5_canvas.asp
	 http://www.w3schools.com/html/html5_svg.asp
	 
	 <script>
		var c = document.getElementById("myCanvas");
		var ctx = c.getContext("2d");
		ctx.fillStyle = "#92B901";
		ctx.fillRect(50, 50, 100, 100);
	</script>
	 
 --%>
