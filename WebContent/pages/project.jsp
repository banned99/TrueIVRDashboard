<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="session.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>This is Home</title>
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<script src="js/project.js" type="text/javascript"></script>
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
</head>
<body>
	<%--@ include file="__header.jsp" --%>
	<div id="wrapper" class="in">
		<div>
			<input type="text" id="searchId" width="12%" />
			<input type="text" id="searchName" width="12%" />
			<input type="text" id="searchStatus" width="12%" />
			<input type="text" id="searchPrio" width="12%" />
			<input type="text" id="searchAC" width="12%" />
			<input type="date" id="searchStart" width="12%" />
			<input type="date" id="searchTarget" width="12%" />
			<input type="date" id="searchLaunch" width="12%" />
			<button type="submit" id="searchButt"> Search </button>
		</div>
		<div>
			<div style="float: left;">
				<select id="perPage">
					<option value="1" >1</option>
					<option value="10" >10</option>
					<option value="20" selected="selected">20</option>
					<option value="50" >50</option>
					<option value="100" >100</option>
				</select>
			</div>
			<div style="float: right;">
				<button id="prevPage" > Previous </button>
				<input type="text" id="curPage" readonly="readonly" value="1/1"/>
				<button id="nextPage" > Next </button>
			</div>
			<table>
				<thead>
					<tr>
						<th width="2.5%"></th>
						<th width="5%">Project ID</th>
						<th width="20%">Project Name</th>
						<th width="10%">Project Status</th>
						<th width="15%">Project Access Channel</th>
						<th width="5%">Project Priority</th>
						<th width="12.5%">Project Start Date</th>
						<th width="12.5%">Project Target Date</th>
						<th width="12.5%">Project Launch Date</th>
						<th width="5%">Project File</th>
						<th width="2.5%"></th>
					</tr>
				</thead>
				<tbody>
				</tbody>	
			</table>
		</div>
	</div>
</body>
</html>