<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="session.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

	<title>Home</title>
	
	<!-- <!-- Bootstrap Core CSS 
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"> -->
	
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="js/homeAjax.js" type="text/javascript"></script>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="wrapper" class="in">

		<%@ include file="__header.jsp" %>
		
		<div id="mostprio"></div>
		
		<div id="mostrecent"></div>
		
		<div id="total_projects"></div>
	</div>
</body>
</html>