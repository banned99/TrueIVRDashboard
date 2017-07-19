/**
 * 
 */
$(document).ready(function () {
	$.ajax({
		type : "GET",
		url : "HomeServlet",
		contentType : "application/json",
		data : {
			action : "viewStatistics",
			year1: $("#year1").val(),
			year2: $("#year2").val()
		}
	}).then(function (json, status, jqxhr){
		google.charts.load('current', {
			'packages' : [ 'bar' ]
		});
		
		google.charts.setOnLoadCallback(drawBarChart);
		
		function drawBarChart() {
			var data = google.visualization.arrayToDataTable([
					[ 'Year', 'Total in Year', 'In Process', 'Finished', 
						'Cancelled', 'Finished on Time', 'Finished Late'],
					[ (new Date().getYear()+1900)+"", 
						json.total_in_year,
						json.total_project_opened, 
						json.total_project_finished, 
						json.total_project_cancelled, 
						json.total_project_on_time,
						json.total_project_late 
						]
					]);
		
			var options = {
				chart : {
					title : 'Projects',
					subtitle : 'Projects Status: '+(new Date().getYear() + 1900)+"",
				},
				bars : 'vertical' // Required for Material Bar Charts.
			};
		
			var chart = new google.charts.Bar(document
					.getElementById('barchart_material'));
		
			chart.draw(data, google.charts.Bar.convertOptions(options));
		}
		
		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(drawCoreChart);
		
		function drawCoreChart() {
			var data = google.visualization.arrayToDataTable([
					[ 'My Project', 'Items' ], 
					[ 'In Process', json.total_project_opened ], 
					[ 'Finished Ontime', json.total_project_on_time ],
					[ 'Finished Late', json.total_project_late ],
					[ 'Cancelled', json.total_project_cancelled ]]);
		
			var options = {
				title : 'Projects in ' + (new Date().getYear()+1900+"") + " (Total:"+ json.total_in_year +")",
				pieHole : 0.2,
			};
		
			var chart = new google.visualization.PieChart(document
					.getElementById('donutchart'));
			chart.draw(data, options);
		}
	})
})