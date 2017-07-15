<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="session.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Access Channel</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
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


<!-- Custom Fonts -->
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script src="js/accesschannel.js" type="text/javascript"></script> 


<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<div id="wrapper" class="in">
		<%@ include file="__header.jsp" %>
		<div id="page-wrapper" style="min-height: 537px;">
			<div class="row">
			<!-- /.dynamic timing -->
				<div class="col-lg-12">
					<div align=right>
						<br/><br/>
					</div>
					<h3 class="page-header">Access Channel</h3>
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<div class="row">
				<div style="float: left;">
					<select id="perPage">
						<option value="1" >1</option>
						<option value="10" >10</option>
						<option value="20" selected="selected">20</option>
						<option value="50" >50</option>
						<option value="100" >100</option>
					</select>
				</div>
				
				<div class="col-sm-12">
					<table width="100%"
						class="table table-bordered table-hover dataTable no-footer dtr-inline"
						id="dataTables-example" role="grid"
						aria-describedby="dataTables-example_info" style="width: 100%;">
						<thead>
							<tr role="row">
								<th class="sorting_asc" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Rendering engine: activate to sort column descending"
									style="width: 7%;" aria-sort="ascending"><center>No.</center></th>
								<th class="sorting_asc" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Rendering engine: activate to sort column descending"
									style="width: 80px;" aria-sort="ascending"><center>Access Channel</center></th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Browser: activate to sort column ascending"
									style="width: 60px;"><center>Product No.</center></th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Browser: activate to sort column ascending"
									style="width: 60px;"><center>Product Name</center></th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Platform(s): activate to sort column ascending"
									style="width: 60px;"><center>Display</center></th>
							</tr>
						</thead>
						<tbody align="center">
						</tbody>
					</table>
					<br>
					<div style="float: right;">
						<button id="prevPage" > Previous </button>
						<input type="text" id="curPage" readonly="readonly" value="1/1"/>
						<button id="nextPage" > Next </button>
					</div>
					
			</div>
			<br> <br>
			<div class="row"></div>
		</div>
	</div>
	</div>
	<!-- /.panel-body -->
	<!-- /.panel -->
	<!-- /.col-lg-12 -->
	<!-- /#page-wrapper -->
	<!-- /#wrapper -->
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/metisMenu.min.js"></script>

	<!-- DataTables JavaScript -->

	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>

</body>
</html>