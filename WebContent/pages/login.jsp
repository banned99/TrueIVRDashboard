<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="stylesheet" href="css/custom_style.css"> -->
<title>True IVR Dashboard</title>
</head>
<body>
	<div id="wrapper" class="in">
		<%@ include file="__login__head.jsp"%>
		<div class="container" style="margin-top:60px; ">
			<center>
				<div>
					${ msg }
				</div>
				<form action="LoginServlet?action=login" method="POST">
					<div class="form-group">
						<label for="username">Username:</label> <input type="text"
							class="form-control" id="username" name="username">
					</div>
					<div class="form-group">
						<label for="password">Password:</label> <input type="password"
							class="form-control" id="password" name="password">
					</div>
					<input type="submit" class="btn btn-primary" />
				</form>
			</center>
		</div>
	</div>
</body>
</html>