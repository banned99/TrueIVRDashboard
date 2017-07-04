<%@page import="com.truecorp.dashboard.model.Authorize" %>

<% 
	Authorize auth = (Authorize)session.getAttribute("user");
	if(auth == null  || auth.getUserID() == null || auth.getUserID().isEmpty())
	{
		session.invalidate();
		String path = "http://" + request.getLocalAddr() + ":" + request.getServerPort() + request.getContextPath() + "/pages/login.jsp";
		response.sendRedirect(path);
		return;
	}
%>