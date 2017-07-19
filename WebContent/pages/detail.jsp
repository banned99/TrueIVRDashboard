<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@include file="session.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Detail Project</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
	<%@ include file="__header.jsp" %>

		<div id="page-wrapper" style="min-height: 537px;">
			<div class="row">
			<!-- /.dynamic timing -->
				<div class="col-lg-12">
					<div align=right>
						<br><iframe
							src="http://free.timeanddate.com/clock/i5skeguw/n28/tlth/fs12/fc777/tt0/tw0/tm3/td2/th2/tb1"
							frameborder="0" width="136" height="16"></iframe>
					</div>
					<h3 class="page-header">Project Details</h3>
				</div>
				<!-- /.col-lg-12 -->
			</div>
				<div class="col-lg-12">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                        	${ project.projectName }
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
								Detail:<br><br>
								
                        	</div>
                        	
                        	<div class="col-lg-8">
                        	<!-- project detail from db -->
                        		${ project.projectName }<br>
								${ project.projectStatus }<br>
								${ project.projectAccessChannel }<br>
								${ project.projectPriority }<br>
								${ project.projectRequestDate }<br>
								${ project.projectRequestSubmitDate }<br>
								${ project.projectTargetDate }<br>
								${ project.projectReason }<br>
								${ project.projectLaunchDate }<br>
								${ project.projectUcrDate }<br>
								${ project.projectUcrNo }<br>
								${ project.projectRequester }<br>	
								${ project.projectOwner }<br>
								${ project.projectManday }<br>
								${ project.projectDetails }<br><br>
								
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

	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>

</body>
</html>