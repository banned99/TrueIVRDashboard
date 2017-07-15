<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Home</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- MetisMenu CSS -->
<link href="css/metisMenu.min.css" rel="stylesheet" type="text/css">

<!-- DataTables CSS -->
<link href="css/dataTables.bootstrap.css" rel="stylesheet"
	type="text/css">

<!-- DataTables Responsive CSS -->
<link href="css/dataTables.responsive.css" rel="stylesheet"
	type="text/css">

<!-- Custom CSS -->
<link href="css/sb-admin-2.css" rel="stylesheet" type="text/css">

<!-- Custom Fonts -->
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">

<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<!-- donut chart -->
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load("current", {
		packages : [ "corechart" ]
	});
	google.charts.setOnLoadCallback(drawChart);
	function drawChart() {
		var data = google.visualization.arrayToDataTable([
				[ 'Task', 'Hours per Day' ], [ 'Work', 11 ], [ 'Eat', 2 ],
				[ 'Commute', 2 ], [ 'Watch TV', 2 ], [ 'Sleep', 7 ] ]);

		var options = {
			title : 'The summary of project statuses',
			pieHole : 0.4,
		};

		var chart = new google.visualization.PieChart(document
				.getElementById('donutchart'));
		chart.draw(data, options);
	}
</script>

<!-- bar chart -->
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'bar' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {
		var data = google.visualization.arrayToDataTable([
				[ 'Year', 'Completed', 'Pending', 'Cancel' ],
				[ '2014', 1000, 400, 200 ], [ '2015', 1170, 460, 250 ],
				[ '2016', 660, 1120, 300 ], [ '2017', 1030, 540, 350 ] ]);

		var options = {
			chart : {
				title : 'Projects',
				subtitle : 'Projects Status: 2014-2017',
			},
			bars : 'horizontal' // Required for Material Bar Charts.
		};

		var chart = new google.charts.Bar(document
				.getElementById('barchart_material'));

		chart.draw(data, google.charts.Bar.convertOptions(options));
	}
</script>
</head>
<body>
	<div id="wrapper" class="in">
		<%@ include file="__header.jsp" %>
				
		<div id="page-wrapper" style="min-height: 537px;">
			<div class="row">
				<!-- /.dynamic timing -->
				<div class="col-lg-12">
					<div align=right>
						<br><br>
					</div>

					<h3 class="page-header">Dashboard</h3>
				</div>
					<div class="col-lg-7">
						<div class="panel panel-default">
							<div class="panel-heading">Statistic</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<div id="barchart_material" style="width: 520px; height: 370px;"></div>
							</div>
							<!-- /.panel-body -->
						</div>
						<!-- /.panel -->
					</div>
			
			
					<div class="col-lg-5">
						<div class="panel panel-default">
							<div class="panel-heading">Donut Chart Example</div>
							<!-- /.panel-heading -->
							<div id="donutchart" style="width: 350px; height: 400px;"></div>
						</div>
						<!-- /.panel-body -->
					</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="row">
				<!-- /.dynamic timing -->
				<div class="col-lg-12">
					<div align=right>
						<br>
						<iframe
							src="http://free.timeanddate.com/clock/i5skeguw/n28/tlth/fs12/fc777/tt0/tw0/tm3/td2/th2/tb1"
							frameborder="0" width="136" height="16"></iframe>
					</div>

					<h3 class="page-header">Most Priority</h3>
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<div class="row">
				<div class="col-sm-12">
					<table width="100%"
						class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline"
						id="mostprio-table" role="grid"
						aria-describedby="dataTables-example_info" style="width: 100%;">
						<thead>
							<tr role="row">
								<th class="sorting_asc" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Rendering engine: activate to sort column descending"
									style="width: 20px;" aria-sort="ascending">Project ID</th>
								<th class="sorting_asc" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Rendering engine: activate to sort column descending"
									style="width: 160px;" aria-sort="ascending">Name</th>
								<th class="sorting_asc" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Rendering engine: activate to sort column descending"
									style="width: 60px;" aria-sort="ascending">Owner</th>
								<th class="sorting_asc" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Rendering engine: activate to sort column descending"
									style="width: 60px;" aria-sort="ascending">Requester</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Browser: activate to sort column ascending"
									style="width: 60px;">Status</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Platform(s): activate to sort column ascending"
									style="width: 60px;">Access Channel</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Engine version: activate to sort column ascending"
									style="width: 60px;">Priority</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="CSS grade: activate to sort column ascending"
									style="width: 60px;">Target Date</th>
								<th class="sorting_asc" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Rendering engine: activate to sort column descending"
									style="width: 20px;" aria-sort="ascending">Days Left</th>
							</tr>
						</thead>
						<tbody id="mostprio">
						</tbody>
					</table>
					<br>

					<div class="row">

						<!-- /.col-lg-6 -->
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3>Recent Projects</h3>
								</div>
								<!-- /.panel-heading -->
								<div class="panel-body">
									<div class="table-responsive">
										<table class="table">
											<thead>
												<tr>
													<th>Project ID</th>
													<th>Name</th>
													<th>Owner</th>
													<th>Requester</th>
													<th>Status</th>
													<th>Access Channel</th>
													<th>Priority</th>
													<th>Request Date</th>
													<th>Submit Date</th>
													<th>Target Date</th>
													<th>Reference File</th>
												</tr>
											</thead>
											<tbody id="mostrecent">
											</tbody>
										</table>
									</div>
									<!-- /.table-responsive -->
								</div>
								<!-- /.panel-body -->
							</div>
							<!-- /.panel -->
						</div>
						<!-- /.col-lg-6 -->
					</div>
				</div>


				<!-- /.table-responsive -->

				
			</div>

		</div>
		<br> <br>
		<div class="row"></div>
	</div>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/metisMenu.min.js"></script>

	<!-- DataTables JavaScript -->
	<script src="js/jquery.dataTables.min.js"></script>
	<script src="js/dataTables.bootstrap.min.js"></script>
	<script src="js/dataTables.responsive.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>
	
	<script src="js/homeAjax.js" type="text/javascript"></script>
</body>
</html>