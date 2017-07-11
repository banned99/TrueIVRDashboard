<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>
	<nav class="navbar navbar-default navbar-static-top navbar-fixed-top"
		role="navigation" style="margin-bottom: 0">
		<div class="container-fluid">
			<div class="navbar-header">
				<% 
				if(session.getAttribute("user") != null) 
					out.print("<a class='navbar-brand' href='HomeServlet?action=view'> True IVR Dashboard </a>");
				else
					out.print("<p class='navbar-brand'> True IVR Dashboard </a>");
				%>
			</div>
		</div>
	</nav>
</body>
</html>