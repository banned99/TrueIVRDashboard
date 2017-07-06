<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>
	<nav class="navbar navbar-default navbar-static-top navbar-fixed-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<a class="navbar-brand" href="HomeServlet?action=view">True IVR
					Dashboard</a>
		</div>
		<!-- /.navbar-header -->

		<ul class="nav navbar-nav navbar-right" style="margin-right: 10px">
				<li>
					<a href="#">
						<span class="glyphicon glyphicon-user"></span>
						Welcome ${ user.userFullname }
					</a>
				</li>
				<li>
					<a href="javascript:if(confirm('Are you sure to Logout?')){window.location='LoginServlet?action=logout';}">
						<span class="glyphicon glyphicon-log-in"></span> 
						Logout
					</a>
				</li>
			</ul>

		<!-- /.navbar-top-links -->

		<div class="navbar-default sidebar in" >
			<div class="sidebar-nav navbar-collapse collapse">
				<ul class="nav in" id="side-menu">
					<li><a href="pages/project.jsp">Projects</a></li>
					<li><a href="pages/ac.jsp">Access Control</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>
</body>
</html>