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
	
<!-- MetisMenu CSS -->
<link href="css/metisMenu.min.css" rel="stylesheet" type="text/css">

<!-- DataTables CSS -->
<link href="css/dataTables.bootstrap.css" rel="stylesheet"
	type="text/css">

<!-- DataTables Responsive CSS -->
<link href="css/dataTables.responsive.css" rel="stylesheet"
	type="text/css">

<!-- Custom Fonts -->
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script src="js/accesschannel.js" type="text/javascript"></script> 

</head>
<body>
	<div id="wrapper" class="in">
		<%@ include file="__header.jsp" %>
		<div id="page-wrapper" style="min-height: 537px;">
		<div class="row">
				<!-- /.dynamic timing -->
				<div class="col-lg-12">
					<div align=right>
						<br> <br> <br> <br>
						<iframe
							src="http://free.timeanddate.com/clock/i5skeguw/n28/tlth/fs12/fc777/tt0/tw0/tm3/td2/th2/tb1"
							frameborder="0" width="136" height="16"> </iframe>
					</div>
					<h3 class="page-header">Access Channel List</h3>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			
			<div id="panel-header" style="min-height: 50px;"> 
				<div width="100%">
							<div class="col-lg-2" style="float: left;">
								<input id="searchId" type="text"
								class="form-control input-sm" placeholder="No."
								aria-controls="dataTables-example">
							</div>
							<div class="col-lg-2">
								<input id="searchAC" type="text"
								class="form-control input-sm" placeholder="Access Channel Name"
								aria-controls="dataTables-example" >
							</div>
							<div class="col-lg-2">
								<input id="searchProductName" type="text"
								class="form-control input-sm" placeholder="Product Name"
								aria-controls="dataTables-example">
							</div>
							<div class="col-lg-2">
								<input id="searchDisplay" type="text"
								class="form-control input-sm" placeholder="Display"
								aria-controls="dataTables-example">
							</div>
							<div class="col-lg-3">
								<button id="searchButt" class="btn btn-info" style="margin-left: 30px" type="submit" >Search</button>
								<button id="clearButt" class="btn btn-info" style="margin-left: 30px" type="submit" >Clear</button>
							</div>
				</div>
			</div>

			<div class="row">
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
			<br> <br>
			<div class="row"></div>
		</div>
	</div>
	</div>
	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>

</body>
</html>