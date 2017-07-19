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

</head>
<body>
	<div id="wrapper" class="in">
	<%@ include file="__header.jsp" %>

		<div id="page-wrapper" style="min-height: 537px;">
			<div class="row">
			<!-- /.dynamic timing -->
				<div class="col-lg-12">
					<br>
					<br>
					<br>
					<h3 class="page-header">Project Details</h3>
				</div>
				<!-- /.col-lg-12 -->
			</div>
				<div class="col-lg-12">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                        	${ project.projectName } Project
                        </div>
                        
                        <div class="panel-body">
                        	<div class="col-lg-12" align="justify">
                        		<span class="col-sm-3">Project Name: </span> ${ project.projectName }<br>
								<span class="col-sm-3">Status: </span> ${ project.projectStatus }<br>
								<span class="col-sm-3">Access Channel: </span> <c:forEach items="${ project.projectAccessChannel }" var="ac" varStatus="index">
									<c:choose>
										<c:when test="${ index.index == 0 }"> ${ ac }</c:when>
										<c:when test="${ index.index > 0 }">, ${ ac }</c:when>
									</c:choose>
								</c:forEach><br>
								<span class="col-sm-3">Priority: </span> ${ project.projectPriority }<br>
								<span class="col-sm-3">User Request Date: </span> ${ project.projectRequestDate }<br>
								<span class="col-sm-3">Submit Requirement Date: </span> ${ project.projectRequestSubmitDate }<br>
								<span class="col-sm-3">Target Date: </span> ${ project.projectTargetDate }<br>
								<span class="col-sm-3">Reason: </span>${ project.projectReason }<br>
								<span class="col-sm-3">Launch Date: </span> ${ project.projectLaunchDate }<br>
								<span class="col-sm-3">UCR Date: </span> ${ project.projectUcrDate }<br>
								<span class="col-sm-3">UCR No: </span> ${ project.projectUcrNo }<br>
								<span class="col-sm-3">Requester: </span> ${ project.projectRequester }<br>
								<span class="col-sm-3">Owner: </span> ${ project.projectOwner }<br>
								<span class="col-sm-3">Man-day: </span> ${ project.projectManday }<br>
								<span class="col-sm-3">Detail: </span> ${ project.projectDetails }<br><br>
								
                        	</div>
                        </div>
                        
                        <div class="panel-footer">
                        </div>
                    </div>
                </div>
			<div class="row">
			<!-- /.dynamic timing -->
				<div class="col-lg-12">
					<div align=right>
						<br><iframe
							src="http://free.timeanddate.com/clock/i5skeguw/n28/tlth/fs12/fc777/tt0/tw0/tm3/td2/th2/tb1"
							frameborder="0" width="136" height="16"></iframe>
					</div>
				</div>
				<!-- /.col-lg-12 -->
			</div>
		</div>
	</div>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>

</body>
</html>