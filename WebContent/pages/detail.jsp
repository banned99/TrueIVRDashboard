<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Detail Project</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">

<!-- MetisMenu CSS -->
<link href="css/metisMenu.min.css" rel="stylesheet" type="text/css">

<!-- Custom CSS -->
<link href="css/sb-admin-2.css" rel="stylesheet" type="text/css">

<!-- Custom Fonts -->
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">

<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<div id="wrapper" class="in">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<img src="img/trueicon.png" />
		</div>
		<!-- /.navbar-header -->

		<div style="float: right;">
			<p align="right" class="navbar-text">Welcome
				${userVo.userFullname}</p>
		</div>

		<!-- /.navbar-top-links -->

		<div class="navbar-default sidebar in">
			<div class="sidebar-nav navbar-collapse collapse">
				<ul class="nav in" id="side-menu">
					<li><a href="pages/home.jsp">Dashboard</a></li>
					<li><a href="forms.html">Access Channel</a></li>
					<li><a href="forms.html">Detail Project</a></li>
					<li><a href="pages/login.jsp">Sign Out</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>



		<div id="page-wrapper" style="min-height: 537px;">
			<div class="row">
			<!-- /.dynamic timing -->
				<div class="col-lg-12">
					<div align=right>09
						<br><iframe
							src="http://free.timeanddate.com/clock/i5skeguw/n28/tlth/fs12/fc777/tt0/tw0/tm3/td2/th2/tb1"
							frameborder="0" width="136" height="16"></iframe>
					</div>
					<h3 class="page-header">Detail Project</h3>
				</div>
				<!-- /.col-lg-12 -->
			</div>



				<div class="col-lg-12">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <!-- project name -->
                            Moo-Ga-Ta Project
                        </div>
                        
                        <div class="panel-body">
                        	<div class="col-lg-4" align="right">
                        		Project Name:<br>
								Status:<br>
								Access Channel:<br>
								Priority:<br>
								User Request Date:<br>
								Submit Requirement Date:<br>
								Target Date:<br>
								Reason:<br>
								Launch Date:<br>
								UCR Date:<br>
								UCR No:<br>
								Requester:<br>	
								Owner:<br>
								Man-day:<br>
								Detail text:<br><br>

								file attached:<br>
								
                        	</div>
                        	
                        	<div class="col-lg-8">
                        	<!-- project detail from db -->
                        		Moo-Ga-Ta Project<br>
								open<br>
								1112<br>
								high<br>
								12/07/17<br>
								18/07/17<br>
								17/07/17<br>
								<br>
								16/07/17<br>
								17/07/17<br>
								10015<br>
								Mamuayy<br>	
								Caliv3r<br>
								<br>
								Moo-Ga-Ta 3 persons<br><br>

								<br>
                        	</div>
                        </div>
                        
                        <div class="panel-footer">
                        </div>
                    </div>
                </div>
	
	

			<br> <br>
			<div class="row"></div>
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

	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>

</body>
</html>