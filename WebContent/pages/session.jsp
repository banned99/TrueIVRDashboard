<%@page import="com.truecorp.dashboard.model.Authorize" %>

<% 
	Authorize auth = (Authorize) session.getAttribute("user");
	if(auth == null  || auth.getUsername() == null || auth.getUsername().isEmpty())
	{
		session.invalidate();
		String path = "http://localhost:" + request.getServerPort() + request.getContextPath() + "/LoginServlet?action=view";
		response.sendRedirect(path);
	}
%>