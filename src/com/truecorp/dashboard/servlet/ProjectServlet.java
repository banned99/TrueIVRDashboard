package com.truecorp.dashboard.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			case "viewAllProject":
				viewAllProject(request, response);
				break;
			case "projectSearch":
				searchProject(request, response);
				break;
			case "viewProject":
				viewProject(request, response);
				break;
			case "view":
				request.getRequestDispatcher("pages/project.jsp").forward(request, response);
				break;
			}
		} catch (JsonSyntaxException | JsonIOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void viewProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String projectId = request.getParameter("project_id");
		
		ProjectService projService = new ProjectService();
		Project proj = projService.getProjectById(projectId);
		
		Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(proj, new TypeToken<Project>(){}.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(jsonArray);
	}

	private void searchProject(HttpServletRequest request, HttpServletResponse response) {
		try {
			DashboardService dashService = new DashboardService();
			List<Project> projects = new ArrayList<Project>();
			int perPage = Integer.parseInt(request.getParameter("perPage"));
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
			
			
			String projectId = request.getParameter("projectId");
			String projectName = request.getParameter("projectName");
			String projectStatus = request.getParameter("projectStatus");
			String projectAC = request.getParameter("projectAC");
			String projectPriority = request.getParameter("projectPriority");
			String projectStartDate = request.getParameter("projectStartDate");
			String projectTargetDate = request.getParameter("projectTargetDate");
			String projectLaunchDate = request.getParameter("projectLaunchDate");
			
			ProjectCriteria crit = new ProjectCriteria();
			crit.setProjectId(projectId);
			crit.setProjectName(projectName);
			crit.setProjectStatus(projectStatus);
			crit.setProjectPriority(projectPriority);
			crit.setProjectAccessChannel(projectAC);
			crit.setProjectStartDate(projectStartDate);
			crit.setProjectTargetDate(projectTargetDate);
			crit.setProjectLaunchDate(projectLaunchDate);
			
			projects = dashService.projectSearch(crit, pageNo, perPage);
			
			Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(projects, new TypeToken<List<Project>>(){}.getType());
            JsonArray jsonArray = element.getAsJsonArray();
            
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().print(jsonArray);
		} catch (JsonSyntaxException | JsonIOException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void viewAllProject(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		try {
			DashboardService dashService = new DashboardService();
			List<Project> projects = new ArrayList<Project>();
			
			int perPage = Integer.parseInt(request.getParameter("perPage"));
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
			
			projects = dashService.getAllProj(perPage, pageNo);
			
			Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(projects, new TypeToken<List<Project>>(){}.getType());
            JsonArray jsonArray = element.getAsJsonArray();
            
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().print(jsonArray);
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			e.printStackTrace();
		}		
	}
}
