<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="session.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>Project List</title>

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<!-- jQuery library -->
	<script src="js/jquery.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script src="js/bootstrap.min.js"></script>
	
	<script src="js/project.js" type="text/javascript"></script>
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- Custom Fonts -->
	<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
	
	<!-- MetisMenu CSS -->
	<link href="css/metisMenu.min.css" rel="stylesheet" type="text/css">
	
	<!-- DataTables CSS -->
	<link href="css/dataTables.bootstrap.css" rel="stylesheet"
	type="text/css">

	<!-- DataTables Responsive CSS -->
	<link href="css/dataTables.responsive.css" rel="stylesheet"
	type="text/css">
	
	<!-- Search -->
	<script src="http://momentjs.com/downloads/moment-with-locales.js"></script>
	<script src="http://momentjs.com/downloads/moment-timezone-with-data.js"></script>
	    
	<!-- Include Required Prerequisites -->
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/moment.min.js"></script>
	<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/bootstrap/3/css/bootstrap.css" />
	 
	<!-- Include Date Range Picker -->
	<script type="text/javascript" src="js/daterangepicker.js"></script>
	<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" />

	<script type="text/javascript">
	$(function() {
	    $('input[name="daterange"]').daterangepicker({
	        timePicker: true,
	        timePickerIncrement: 30,
	        locale: {
	            format: 'MM/DD/YYYY'
	        }
	    });
	});
	</script>
</head>
<body>
	<div id="wrapper" class="in">
		<%@ include file="__header.jsp"%>
		<div id="page-wrapper" style="min-height: 537px;">
			<div class="row">
				<!-- /.dynamic timing -->
				<div class="col-lg-12">
					<div align=right>
						<br> <br> <br>
					</div>
					<h3 class="page-header">Project List</h3>
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<!-- search -->
			<div id="panel-header" style="min-height: 100px;"> 
			<!-- <label style="margin-left:35px; margin-bottom: 10px;">Search</label> -->
				<div class="col-lg-12">
						<div id="dataTables-example_filter" class="dataTables_filter" style="height: 50px;">
							<label>
							<div class="col-sm-1">
								<input id="searchId" type="text"
								class="form-control input-sm" placeholder="No."
								aria-controls="dataTables-example">
							</div>
							<div class="col-sm-3">
								<input id="searchName" type="text"
								class="form-control input-sm" placeholder="Project Name"
								aria-controls="dataTables-example" >
							</div>
							<div class="col-sm-2">
								<input id="searchOwner" type="text"
								class="form-control input-sm" placeholder="Owner"
								aria-controls="dataTables-example">
							</div>
							<div class="col-sm-2">
								<input id="searchRequester" type="text"
								class="form-control input-sm" placeholder="Requester"
								aria-controls="dataTables-example">
							</div>
							<div class="col-sm-2">
								<input id="searchStatus" type="text"
								class="form-control input-sm" placeholder="Status"
								aria-controls="dataTables-example" >
							</div>
							<div class="col-sm-2">
								<input id="searchPrio" type="text"
								class="form-control input-sm" placeholder="Priority"
								aria-controls="dataTables-example">
							</div>
						</div>
				</div>
				

				
				<div class="col-lg-12">
					<div class="dataTables-example_filter">
						<label style="margin-left:20px;">Submit Date:</label>
						<label style="margin-left:10px;">
							<input id="searchSubmit" type="text" name="daterange" value="01/01/2017 - 01/01/2017" 
								class="form-control input-sm" />
						</label>
						<label style="margin-left:20px">Target Date:</label>
						<label style="margin-left:10px;">
							<input id="searchTarget" type="text" name="daterange" value="01/01/2017 - 01/01/2017" 
								class="form-control input-sm" />
						</label>
						<label><button id="searchButt" class="btn btn-info" style="margin-left: 10px;" type="submit" >Search</button></label>
						<label><button id="clearButt" class="btn btn-info" style="margin-left: 10px;" type="submit" >Clear</button></label>
					</div>
				</div>
			</div>
		<div class="col-sm-15">
			<div class="panel panel-warning">
				<table width="100%"
					class="table table-bordered table-hover dataTable no-footer dtr-inline"
					id="dataTables-example" role="grid"
					aria-describedby="dataTables-example_info" style="width: 100%;">
					<thead>
						<tr role="row">
							<th class="sorting_asc" tabindex="0"
								aria-controls="dataTables-example" rowspan="1" colspan="1"
								aria-label="Rendering engine: activate to sort column descending"
								style="width: 1%;" aria-sort="ascending"><center>No.</center></th>
							<th class="sorting_asc" tabindex="0"
								aria-controls="dataTables-example" rowspan="1" colspan="1"
								aria-label="Rendering engine: activate to sort column descending"
								style="width: 20%;" aria-sort="ascending"><center>Project Name</center></th>
							<th class="sorting" tabindex="0"
								aria-controls="dataTables-example" rowspan="1" colspan="1"
								aria-label="Browser: activate to sort column ascending"
								style="width: 7%;"><center>Owner</center></th>
							<th class="sorting" tabindex="0"
								aria-controls="dataTables-example" rowspan="1" colspan="1"
								aria-label="Browser: activate to sort column ascending"
								style="width: 5%;"><center>Requester</center></th>
							<th class="sorting" tabindex="0"
								aria-controls="dataTables-example" rowspan="1" colspan="1"
								aria-label="Browser: activate to sort column ascending"
								style="width: 5%;"><center>Status</center></th>
							<th class="sorting" tabindex="0"
								aria-controls="dataTables-example" rowspan="1" colspan="1"
								aria-label="Platform(s): activate to sort column ascending"
								style="width: 5%;"><center>Priority</center></th>
								<th class="sorting" tabindex="0"
								aria-controls="dataTables-example" rowspan="1" colspan="1"
								aria-label="Platform(s): activate to sort column ascending"
								style="width: 5%;"><center>Access Channel</center></th>
							<th class="sorting" tabindex="0"
								aria-controls="dataTables-example" rowspan="1" colspan="1"
								aria-label="Platform(s): activate to sort column ascending"
								style="width: 10%;"><center>Submit Date</center></th>
							<th class="sorting" tabindex="0"
								aria-controls="dataTables-example" rowspan="1" colspan="1"
								aria-label="Platform(s): activate to sort column ascending"
								style="width: 7%;"><center>Target Date</center></th>
						</tr>
					</thead>
					<tbody id="data" align="center">
					</tbody>
				</table>
				<br>
			</div>
			<div>
				<div style="float: left; vertical-align: middle;">
					<select id="perPage">
						<option value="1" >1</option>
						<option value="10" >10</option>
						<option value="20" selected="selected">20</option>
						<option value="50" >50</option>
						<option value="100" >100</option>
					</select>
				</div>
				<div style="float: right;">
					<button class="btn btn-outline btn-info" id="prevPage" type="button">Previous</button>
						<input type="text" id="curPage" 
							 value="1" style="width: 10%;"/>
						<span id='maxPage'>/ 1</span>
					<button class="btn btn-outline btn-info" id="nextPage" type="button">Next</button>
				</div>
			</div>
		</div>
		<div class="row">
			<!-- /.dynamic timing -->
			<div class="col-lg-12">
				<div align=right>
					<br> <br>
					<iframe
						src="http://free.timeanddate.com/clock/i5skeguw/n28/tlth/fs12/fc777/tt0/tw0/tm3/td2/th2/tb1"
						frameborder="0" width="136" height="16"> </iframe>
				</div>
				<br><br>
			</div>
			<!-- /.col-lg-12 -->
		</div>
	</div>
	</div>

	<script src="js/sb-admin-2.js"></script>
	
	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/metisMenu.min.js"></script>

</body>
</html>