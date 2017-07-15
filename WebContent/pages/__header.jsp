<%@ page session="true"%>
<head>
	<link href="css/sb-admin-2.css" rel="stylesheet" type="text/css">
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
				<a href="LoginServlet?action=logout" >
					<span class="glyphicon glyphicon-log-in"></span> 
					Logout
				</a>
			</li>
		</ul>

		<!-- /.navbar-top-links -->

		<div class="navbar-default sidebar" >
			<div class="sidebar-nav navbar-collapse collapse">
				<ul class="nav in" id="side-menu">
					<li><a href="ProjectServlet?action=view">Projects</a></li>
					<li><a href="AccessChannelServlet?action=view">Access Channel</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> 
	</nav>
</body>