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
			submitXYPosition (x, y, width, height, posX, posY);
		});
	});
	
	$(document).on('refresh', function (e) {
    	console.info("Event is: ", e);
    	console.info("Custom data is: ", e.detail);
		document.getElementById('x').value = e.detail.x;
		document.getElementById('y').value = e.detail.y;
		document.getElementById('width').value = 0;
		document.getElementById('height').value = 0;
		document.getElementById('posx').value = e.detail.x;
		document.getElementById('posy').value = e.detail.y;
		document.getElementById('counter').value = (${formXY.counter});
		document.getElementById('gameBoardFormXY').submit();	
	});
	
	function submitXYPosition (x, y, width, height, posX, posY) {
		x = Math.floor(x);
		y = Math.floor(y);
		posX = Math.floor(posX);
		posY = Math.floor(posY);
		document.getElementById('x').value = x;
		document.getElementById('y').value = y;
		document.getElementById('width').value = width;
		document.getElementById('height').value = height;
		document.getElementById('posx').value = posX;
		document.getElementById('posy').value = posY;
		document.getElementById('counter').value = (${formXY.counter});
		document.getElementById('gameBoardFormXY').submit();	
	}

	
	<%-- document.addEventListener("refresh", function(e){
    	console.info("Event is: ", e);
    	console.info("Custom data is: ", e.detail);
    	submitXYPosition (e.x, e.y, 0, 0, e.x, e.y);
	}); --%>

	
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
		<form:input type='hidden' path="counter" id="counter" name="counter"></form:input>
	</form:form>


	<table>
		<tr>
			<td></td>
			<td colspan="2"
				style="width: 300; text-align: center; vertical-align: middle; height: 90px;">${gameStat}</td>
		</tr>

		<tr>
			<td id="GameDashBoard" style="width: 300; text-align: center;">${blackCoun}<form:form
					action="gameBoard">
					<input type="submit" formmethod="post" style="width: 180px;"
						name="NewGame" id="NewGame" value="Nowa gra" />
					<br />
					<input type="submit" formmethod="post" style="width: 180px;"
						name="ComputerStart" id="ComputerStart"
						value="Piewrszy ruch: komputer" />
					<br />
					<input type="submit" formmethod="post" style="width: 180px;"
						name="StartForm" id="StartForm" value="Strona startowa"
						style="right: auto;" />
				</form:form> ${redCoun} <script type="text/javascript">
			var c = document.getElementById("GameDashBoard"); 
			var cellWidth = (${formGamePadPar.cellWidth}); 
			var height = (${formGamePadPar.height}); 
			var sizeX = (${formGamePadPar.sizeX});
			var tab = [
				(${formGamePad[0]}), (${formGamePad[1]}), (${formGamePad[2]}),
				(${formGamePad[3]}), (${formGamePad[4]}), (${formGamePad[5]}),
				(${formGamePad[5]}), (${formGamePad[7]}), (${formGamePad[8]}),
				(${formGamePad[9]}),
				(${formGamePad[10]}),(${formGamePad[11]}),(${formGamePad[12]}),(${formGamePad[13]}),(${formGamePad[14]}),(${formGamePad[15]}),
				(${formGamePad[16]}),(${formGamePad[17]}),(${formGamePad[18]}),(${formGamePad[19]}),(${formGamePad[20]}),(${formGamePad[21]}),(${formGamePad[22]}),(${formGamePad[23]}),
				(${formGamePad[24]}),(${formGamePad[25]}),(${formGamePad[26]}),(${formGamePad[27]}),(${formGamePad[28]}),(${formGamePad[29]}),(${formGamePad[30]}),(${formGamePad[31]}),
				(${formGamePad[32]}),(${formGamePad[33]}),(${formGamePad[34]}),(${formGamePad[35]}),(${formGamePad[36]}),(${formGamePad[37]}),(${formGamePad[38]}),(${formGamePad[39]}),
				(${formGamePad[40]}),(${formGamePad[41]}),(${formGamePad[42]}),(${formGamePad[43]}),(${formGamePad[44]}),(${formGamePad[45]}),(${formGamePad[46]}),(${formGamePad[47]}),
				(${formGamePad[48]}),(${formGamePad[49]}),(${formGamePad[50]}),(${formGamePad[51]}),(${formGamePad[52]}),(${formGamePad[53]}),(${formGamePad[54]}),(${formGamePad[55]}),
				(${formGamePad[56]}),(${formGamePad[57]}),(${formGamePad[58]}),(${formGamePad[59]}),(${formGamePad[60]}),(${formGamePad[61]}),(${formGamePad[62]}),(${formGamePad[63]})
				];
			var colourCellCount = 0;
			var redCellCount = 0;
			var blackCellCount = 0;
			
			c.height = height; 
			c.width = height;
			
			for (i = 0; i < sizeX * sizeX; i++){
				if (tab[i] == 1){
					colourCellCount++;
					redCellCount++;
				} else if (tab[i] == 2 || tab[i] == 5 || tab[i] == 6){
					colourCellCount++;
					blackCellCount++;
				} else if (tab[i] == 3){
					colourCellCount++;
					redCellCount++;
				} else if (tab[i] == 4){
					colourCellCount++;
					blackCellCount++;
				}
			}
			var computerStart = document.getElementById("ComputerStart");
			if (colourCellCount == 4){
				computerStart.type = "submit";
			} else {
				computerStart.type = "hidden";	
				computerStart.disabled = "true";
			}
				</script></td>
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
		var posXX = -1; 
		var posYY = -1;
		var tab = [
(${formGamePad[0]}), (${formGamePad[1]}), (${formGamePad[2]}), (${formGamePad[3]}), (${formGamePad[4]}), (${formGamePad[5]}), (${formGamePad[6]}), (${formGamePad[7]}),
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

		var computerM = false;
		posXX = -1; 
		posYY = -1;				
		for (i = 0; i < sizeX * sizeX; i++){
			var xx = i % sizeX;
			var yy = (i - xx) / sizeX;
			var posX = (cellWidth / 2) + xx * cellWidth + xx + 1; 
			var posY = (cellWidth / 2) + yy * cellWidth + yy + 1;
			
			ctx.beginPath();
			ctx.arc(posX, posY, (cellWidth / 2) - 3, 0, 2 * Math.PI);
			var fillStyle = "white";
			if (tab[i] == 1){
				fillStyle = "red";
			} else if  (tab[i] == 2){
				fillStyle = "black";
			} else if  (tab[i] == 3){
				fillStyle = "pink";
			} else if  (tab[i] == 4){
				fillStyle = "gray";
			} else if  (tab[i] == 5){
				fillStyle = "red";
			} else if  (tab[i] == 6){
				fillStyle = "red";
				computerM = true;
			}
			
			ctx.fillStyle = fillStyle;
			ctx.fill();
			if (fillStyle.localeCompare("pink") == 0 ||  fillStyle.localeCompare("gray") == 0 || computerM){
				posXX = posX; 
				posYY = posY;				
				computerM = false;
			}
		}
		if(posXX >= 0){		
			var myEvent = new CustomEvent("refresh", {
				detail: {
					x: posXX,
					y: posYY
				}
			});
			document.dispatchEvent(myEvent);
		}
		
		</script>
			</td>
		</tr>
	</table>
	<br>
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


<%-- dla pamięci użycie Canvas
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
 
 --%>
<%--
 pomocne do przepisania wyświetlania:
 https://pl.wikipedia.org/wiki/JavaServer_Pages
 
  
  --%>