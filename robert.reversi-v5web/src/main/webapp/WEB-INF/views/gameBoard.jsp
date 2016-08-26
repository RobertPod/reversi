<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="header.jsp" />
<%--  <title>...</title> --%>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$('#myGamePad').click(function(e) {
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
			document.getElementById('posx').value = posX;
			document.getElementById('posy').value = posY;
			document.getElementById('gameBoardFormXY').submit();
		});
	});
</script>
</head>
<body>
	<script>
	$(document).ready(function() {
		document.getElementById('x').value = (${formXY.x});
		document.getElementById('y').value = (${formXY.y});
		document.getElementById('posx').value = (${formXY.posX});
		document.getElementById('posy').value = (${formXY.posY});
	});
	</script>
	<table>
		<tr>
			<td>
				<canvas id="myGamePad" width=${formGamePadPar.width
					}
					height=${formGamePadPar.height } style="border: 1px solid"> Your browser does not support the HTML5 canvas tag. </canvas>

				<script>
		var c = document.getElementById("myGamePad");
		var ctx = c.getContext("2d");
		var cellWidth = (${formGamePadPar.cellWidth});
		var height = (${formGamePadPar.height});
		var sizeX = (${formGamePadPar.sizeX});
		var tab = [
(${formGamePad[0]}), (${formGamePad[1]}), (${formGamePad[2]}), (${formGamePad[3]}), (${formGamePad[4]}), (${formGamePad[5]}), (${formGamePad[5]}), (${formGamePad[7]}),
(${formGamePad[8]}), (${formGamePad[9]}), (${formGamePad[10]}),(${formGamePad[11]}),(${formGamePad[12]}),(${formGamePad[13]}),(${formGamePad[14]}),(${formGamePad[15]}),
(${formGamePad[16]}),(${formGamePad[17]}),(${formGamePad[18]}),(${formGamePad[19]}),(${formGamePad[20]}),(${formGamePad[21]}),(${formGamePad[22]}),(${formGamePad[23]}),
(${formGamePad[24]}),(${formGamePad[25]}),(${formGamePad[26]}),(${formGamePad[27]}),(${formGamePad[28]}),(${formGamePad[29]}),(${formGamePad[30]}),(${formGamePad[31]}),
(${formGamePad[32]}),(${formGamePad[33]}),(${formGamePad[34]}),(${formGamePad[35]}),(${formGamePad[36]}),(${formGamePad[37]}),(${formGamePad[38]}),(${formGamePad[39]}),
(${formGamePad[40]}),(${formGamePad[41]}),(${formGamePad[42]}),(${formGamePad[43]}),(${formGamePad[44]}),(${formGamePad[45]}),(${formGamePad[46]}),(${formGamePad[47]}),
(${formGamePad[48]}),(${formGamePad[49]}),(${formGamePad[50]}),(${formGamePad[51]}),(${formGamePad[52]}),(${formGamePad[53]}),(${formGamePad[54]}),(${formGamePad[55]}),
(${formGamePad[56]}),(${formGamePad[57]}),(${formGamePad[58]}),(${formGamePad[59]}),(${formGamePad[60]}),(${formGamePad[61]}),(${formGamePad[62]}),(${formGamePad[63]})
		        ];
		
		for (i = 0; i <= sizeX; ++i){
			ctx.moveTo(0, i*(cellWidth+1));
			ctx.lineTo(height, i*(cellWidth+1));
			ctx.stroke();
			
			ctx.moveTo(i*(cellWidth+1), 0);
			ctx.lineTo(i*(cellWidth+1), height);
			ctx.stroke();
		}
		for (i = 0; i < sizeX * sizeX; i++){
			var xx = i % sizeX;
			var yy = (i - xx) / sizeX;
			
			ctx.beginPath();
			ctx.arc((cellWidth / 2) + xx * cellWidth + xx + 1, (cellWidth / 2) + yy * cellWidth + yy + 1, (cellWidth / 2) - 3, 0, 2 * Math.PI);
			if (tab[i] == 0){
				ctx.fillStyle = "white";				
			} else if (tab[i] == 1){
				ctx.fillStyle = "red";								
			} else{
				ctx.fillStyle = "black";				
			}
			ctx.fill();
		}
	</script>

			</td>
			<td>
				<canvas id="myGamePadVal" width=${formGamePadPar.width
					}
					height=${formGamePadPar.height } style="border: 1px solid"> Your browser does not support the HTML5 canvas tag. </canvas>

				<script>
		var c = document.getElementById("myGamePadVal");
		var ctx = c.getContext("2d");
		var cellWidth = (${formGamePadPar.cellWidth});
		var height = (${formGamePadPar.height});
		var sizeX = (${formGamePadPar.sizeX});
		var tab = [
(${formGamePad[0]}), (${formGamePad[1]}), (${formGamePad[2]}), (${formGamePad[3]}), (${formGamePad[4]}), (${formGamePad[5]}), (${formGamePad[5]}), (${formGamePad[7]}),
(${formGamePad[8]}), (${formGamePad[9]}), (${formGamePad[10]}),(${formGamePad[11]}),(${formGamePad[12]}),(${formGamePad[13]}),(${formGamePad[14]}),(${formGamePad[15]}),
(${formGamePad[16]}),(${formGamePad[17]}),(${formGamePad[18]}),(${formGamePad[19]}),(${formGamePad[20]}),(${formGamePad[21]}),(${formGamePad[22]}),(${formGamePad[23]}),
(${formGamePad[24]}),(${formGamePad[25]}),(${formGamePad[26]}),(${formGamePad[27]}),(${formGamePad[28]}),(${formGamePad[29]}),(${formGamePad[30]}),(${formGamePad[31]}),
(${formGamePad[32]}),(${formGamePad[33]}),(${formGamePad[34]}),(${formGamePad[35]}),(${formGamePad[36]}),(${formGamePad[37]}),(${formGamePad[38]}),(${formGamePad[39]}),
(${formGamePad[40]}),(${formGamePad[41]}),(${formGamePad[42]}),(${formGamePad[43]}),(${formGamePad[44]}),(${formGamePad[45]}),(${formGamePad[46]}),(${formGamePad[47]}),
(${formGamePad[48]}),(${formGamePad[49]}),(${formGamePad[50]}),(${formGamePad[51]}),(${formGamePad[52]}),(${formGamePad[53]}),(${formGamePad[54]}),(${formGamePad[55]}),
(${formGamePad[56]}),(${formGamePad[57]}),(${formGamePad[58]}),(${formGamePad[59]}),(${formGamePad[60]}),(${formGamePad[61]}),(${formGamePad[62]}),(${formGamePad[63]})
		        ];
		
		for (i = 0; i <= sizeX; ++i){
			ctx.moveTo(0, i*(cellWidth+1));
			ctx.lineTo(height, i*(cellWidth+1));
			ctx.stroke();
			
			ctx.moveTo(i*(cellWidth+1), 0);
			ctx.lineTo(i*(cellWidth+1), height);
			ctx.stroke();
			}
		ctx.font = "30px Arial";
		for (i = 0; i < sizeX * sizeX; i++){
			var xx = i % sizeX;
			var yy = (i - xx) / sizeX;

			ctx.strokeText(tab[i], xx * (cellWidth + 1) + 10, yy * (cellWidth + 1) + 35);
		}
	</script>
			</td>
		</tr>
	</table>
	<br>
	<p>
		<strong>Note:</strong> The canvas tag is not supported in Internet
		Explorer 8 and earlier versions.
	</p>
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
</body>
</html>

<%-- 
	 http://www.w3schools.com/html/html5_canvas.asp
	 http://www.w3schools.com/html/html5_svg.asp
	 
	 <script>
		var c = document.getElementById("myGamePad");
		var ctx = c.getContext("2d");
		ctx.fillStyle = "#92B901";
		ctx.fillRect(50, 50, 100, 100);
	</script>
	 

--%>
<%-- dla kontroli - wyświtlenie pozycji na ekranie
<script>
	$(document).ready(function() {
		$('#myGamePad').click(function(e) {
			var offset = $(this).offset();
			$('#x_axis').html(e.clientX - offset.left);
			$('#y_axis').html(e.clientY - offset.top);
		});
	});
</script>  --%>
<%-- dla kontroli - wyświtlenie pozycji na ekranie
		<br /> X Axis: - <span id="x_axis">0</span> <br /> Y Axis: - <span
			id="y_axis">0</span> --%>
