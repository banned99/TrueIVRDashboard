<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="session.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>This is Home</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="js/dashboard.js" type="text/javascript"></script>
	<!-- Bootstrap Core CSS -->
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="__header.jsp" %>
	
	<div>
		<table>
			<thead>
				<tr>
					<th width="2.5%"></th>
					<th width="5%">Project ID</th>
					<th width="20%">Project Name</th>
					<th width="10%">Project Status</th>
					<th width="5%">Project Priority</th>
					<th width="15%">Project Access Channel</th>
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
</body>
</html>