<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@include file="session.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

	<title>Home</title>
	
	<!-- Bootstrap Core CSS -->
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
	
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
	<!-- <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"> -->
	
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="js/homeAjax.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/metisMenu.min.js"></script>
	
	<!-- DataTables JavaScript -->
	<script src="js/jquery.dataTables.min.js"></script>
	<script src="js/dataTables.bootstrap.min.js"></script>
	<script src="js/dataTables.responsive.js"></script>
	
	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>
	
	<script>
		$(document).ready(function() {
			$('#dataTables-example').DataTable({
				responsive : true
			});
		});
	</script>
	
	<!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div id="wrapper" class="in">

		<!-- Navigation TOP -->
		<%@include file="__header.jsp"%>

		<div id="page-wrapper" style="min-height: 537px; margin-top: 30px;">
			<div class="row">
				<div class="col-lg-12">
					<h3 class="page-header">Top Projects</h3>
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<div class="row">
				<div class="col-sm-12">
					<table width="100%"
						class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline"
						id="top" role="grid"
						aria-describedby="dataTables-example_info" style="width: 100%;">
						<thead>
							<tr role="row">
								<th style="width: 4%;">#</th>
								<th class="sorting_asc" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Rendering engine: activate to sort column descending"
									style="width: 160px;" aria-sort="ascending">Project Lists</th>
								<th class="sorting_asc" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Rendering engine: activate to sort column descending"
									style="width: 80px;" aria-sort="ascending">User</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Browser: activate to sort column ascending"
									style="width: 60px;">Status</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Platform(s): activate to sort column ascending"
									style="width: 60px;">Priority</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Engine version: activate to sort column ascending"
									style="width: 60px;">Launch Date</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="CSS grade: activate to sort column ascending"
									style="width: 60px;">Deadline</th>
							</tr>
						</thead>
						<tbody>
							<tr class="gradeC odd" role="row">
								<td>1</td>
								<td class="sorting_1">Change IVR system</td>
								<td class="sorting_1">muay</td>
								<td><p>
										<button type="button" class="btn btn-warning">Waiting</button>
									</p></td>
								<td>Danger</td>
								<td class="center">18/06/17</td>
								<td class="center">29/06/17</td>
							</tr>
							<tr class="gradeA even" role="row">
								<td>2</td>
								<td class="sorting_1">Upgrade Software Version</td>
								<td class="sorting_1">muay</td>
								<td><p>
										<button type="button" class="btn btn-warning">Waiting</button>
									</p></td>
								<td>Danger</td>
								<td class="center">19/06/17</td>
								<td class="center">29/06/17</td>
							</tr>
							<tr class="gradeA odd" role="row">
								<td>3</td>
								<td class="sorting_1">Install AAA program</td>
								<td class="sorting_1">rose</td>
								<td><p>
										<button type="button" class="btn btn-warning">Waiting</button>
									</p></td>
								<td>Danger</td>
								<td class="center">20/06/17</td>
								<td class="center">29/06/17</td>
							</tr>
							<tr class="gradeA even" role="row">
								<td>4</td>
								<td class="sorting_1">Data Requests to DB Admin</td>
								<td class="sorting_1">min</td>
								<td><p>
										<button type="button" class="btn btn-danger">Approved</button>
									</p></td>
								<td>Hurry</td>
								<td class="center">23/05/17</td>
								<td class="center">29/06/17</td>
							</tr>
							<tr class="gradeA odd" role="row">
								<td>5</td>
								<td class="sorting_1">Closed Phone Number</td>
								<td class="sorting_1">jan</td>
								<td><p>
										<button type="button" class="btn btn-danger">Approved</button>
									</p></td>
								<td>Please</td>
								<td class="center">18/06/17</td>
								<td class="center">30/06/17</td>
							</tr>
						</tbody>
					</table>
					<br>

					<div class="row">

						<!-- /.col-lg-6 -->
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3>Related Works</h3>
								</div>
								<!-- /.panel-heading -->
								<div class="panel-body">
									<div class="table-responsive">
										<table class="table">
											<thead>
												<tr>
													<th>#</th>
													<th>Project Name</th>
													<th>Group</th>
													<th>Category</th>
													<th>Sub Category</th>
													<th>Username</th>
												</tr>
											</thead>
											<tbody>
												<tr class="success">
													<td>1</td>
													<td>Bingsoo project</td>
													<td>food</td>
													<td>dessert</td>
													<td>icecream</td>
													<td>Muay</td>
												</tr>
												<tr>
													<td>2</td>
													<td>Shabu project</td>
													<td>food</td>
													<td>shabu</td>
													<td>japanese</td>
													<td>Rose</td>
												</tr>
												<tr class="warning">
													<td>3</td>
													<td>CM IT</td>
													<td>IT</td>
													<td>IVR</td>
													<td>IVR support</td>
													<td>Jan</td>
												</tr>
												<tr class="danger">
													<td>4</td>
													<td>Call Center Changing</td>
													<td>IT</td>
													<td>Call Center</td>
													<td>Tel</td>
													<td>Min</td>
												</tr>
												<tr class="info">
													<td>5</td>
													<td>Improving system</td>
													<td>IT</td>
													<td>IVR</td>
													<td>IVR</td>
													<td>Boom</td>
												</tr>
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
			</div>
			<br> <br>
			<div class="row"></div>
		</div>
		<!-- /.table-responsive -->
		<div id="mostprio"></div>
	</div>
</body>
</html>