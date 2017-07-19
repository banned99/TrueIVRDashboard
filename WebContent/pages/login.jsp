<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>True IVR Dashboard</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>
<body>
	<%@ include file="__login__head.jsp" %>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
						<form name="frmLogin" method="post" action="LoginServlet?action=login">		
                            <fieldset>
                                <div class="form-group" align="center">
                                    Username: <input name="username" type="text" id="loginname"
							class="login-field">
                                </div>
                                <div class="form-group" align="center">
                                    Password: <input name="password" type="password" id="loginpass"
							class="login-field">
                                </div><br>                         
                                <input class="btn btn-info btn-primary btn-large btn-block" type="submit"
						value="Login" />

                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>