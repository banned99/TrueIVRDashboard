package com.truecorp.dashboard.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.truecorp.dashboard.criteria.ProjectCriteria;
import com.truecorp.dashboard.model.Project;
import com.truecorp.dashboard.service.DashboardService;
import com.truecorp.dashboard.service.ProjectService;

/**
 * Servlet implementation class DashboardServlet
 */
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
				
			switch (action) {
			case "viewMyProjectCount":
				countMyProject(request, response);
				break;
			case "viewMyProject":
				viewMyProject(request, response);
				break;
			case "viewProjectById":
				viewProjectById(request, response);
				break;
			case "viewAllProject":
				viewAllProject(request, response);
				break;
			case "projectSearch":
				searchProject(request, response);
				break;
			case "view":
				request.getRequestDispatcher("pages/project.jsp").forward(request, response);
				break;
			}
		} catch (JsonSyntaxException | JsonIOException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void countMyProject(HttpServletRequest request, HttpServletResponse response) throws SQLException, JsonIOException, IOException {
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		
		DashboardService ds = new DashboardService();
		
		Map<String, Integer> respond = new HashMap<String, Integer>();
		respond.put("my_total_projects", ds.getMyProjectCount(username));
		
		new Gson().toJson(respond, response.getWriter());
	}

	private void viewMyProject(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		
		System.out.println(username);
		
		DashboardService dashService = new DashboardService();
		List<Project> projs = dashService.getMyProjects(username);
		
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(projs, new TypeToken<List<Project>>(){}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonArray);
	}

	private void searchProject(HttpServletRequest request, HttpServletResponse response) {
		try {
			DashboardService dashService = new DashboardService();
			List<Project> projects = new ArrayList<Project>();
			
			String projectId = request.getParameter("projectId");
			String projectName = request.getParameter("projectName");
			String projectStatus = request.getParameter("projectStatus");
			String projectPriority = request.getParameter("projectPriority");
			String projectSubmitDateStart = request.getParameter("projectSubmitDateStart");
			String projectSubmitDateEnd = request.getParameter("projectSubmitDateEnd");
			String projectTargetDateStart = request.getParameter("projectTargetDateStart");
			String projectTargetDateEnd = request.getParameter("projectTargetDateEnd");
			
			if(projectSubmitDateStart.equals("01/01/2017") && projectSubmitDateEnd.equals("01/01/2017")){
				projectSubmitDateStart = null;
				projectSubmitDateEnd = null;
			}
			
			if(projectTargetDateStart.equals("01/01/2017") && projectTargetDateEnd.equals("01/01/2017")){
				projectTargetDateStart = null;
				projectTargetDateEnd = null;
			}
			
			ProjectCriteria crit = new ProjectCriteria();
			crit.setProjectId(projectId);
			crit.setProjectName(projectName);
			crit.setProjectStatus(projectStatus);
			crit.setProjectPriority(projectPriority);
			crit.setProjectSubmitDateStart(projectSubmitDateStart);
			crit.setProjectSubmitDateEnd(projectSubmitDateEnd);
			crit.setProjectTargetDateStart(projectTargetDateStart);
			crit.setProjectTargetDateEnd(projectTargetDateEnd);
			
			projects = dashService.projectSearch(crit);
			
			Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(projects, new TypeToken<List<Project>>(){}.getType());
            JsonArray jsonArray = element.getAsJsonArray();
            
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().print(jsonArray);
		} catch (JsonSyntaxException | JsonIOException | IOException | SQLException e) {
			e.printStackTrace();
		}
		
	}


	private void viewAllProject(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		try {
			DashboardService dashService = new DashboardService();
			List<Project> projects = new ArrayList<Project>();
			
			int perPage = Integer.parseInt(request.getParameter("perPage"));
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
			int max = Integer.parseInt(request.getParameter("max"));
			
			projects = dashService.getAllProj(perPage, pageNo, max);
			
			Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(projects, new TypeToken<List<Project>>(){}.getType());
            JsonArray jsonArray = element.getAsJsonArray();
            
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().print(jsonArray);
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			e.printStackTrace();
		}		
	}
	
	private void viewProjectById(HttpServletRequest request, HttpServletResponse response) {
		try {
			String project_id = request.getParameter("project_id");
			ProjectService pjs = new ProjectService();
			
			Project project = pjs.getProjectById(project_id);
			
			request.setAttribute("project", project);
			request.getRequestDispatcher("pages/detail.jsp").forward(request, response);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
