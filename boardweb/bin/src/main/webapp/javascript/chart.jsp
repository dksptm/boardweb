<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>구글차트 라이브러리</title>
</head>
<body>
	<div id="piechart" style="width: 900px; height: 500px;"></div>
	
	<script type="text/javascript"
		src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
		
		let dataAry = [['Sido', 'Count per Sido']];
		
		fetch('../getSidoInfo.do')
		.then(resolve => resolve.json())
		.then(result => {
			console.log(result);
			result.forEach(info => {
				let inAry = [];
				inAry.push(info.sido);
				inAry.push(info.cnt);
				dataAry.push(inAry);
			})
			console.log(dataAry);
			google.charts.load('current', {'packages' : [ 'corechart' ]});
			google.charts.setOnLoadCallback(drawChart);
		})
		.catch(err => console.log('에러=> ', err));

		function drawChart() {
			
			var data = google.visualization.arrayToDataTable(dataAry);

			var options = {
				title : '시도별 센터수'
			};

			var chart = new google.visualization.PieChart(document.getElementById('piechart'));

			chart.draw(data, options);
		}
	</script>
</body>
</html>