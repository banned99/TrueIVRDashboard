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
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.truecorp.dashboard.criteria.ProjectCriteria;
import com.truecorp.dashboard.model.Project;
import com.truecorp.dashboard.service.DashboardService;

/**
 * Servlet implementation class DashboardServlet
 */
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
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

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		try {
			String action = request.getParameter("action");
				
			switch (action) {
			case "viewAllProject":
				viewAllProject(request, response);
				break;
			case "viewMostPrioProject":
				viewMostPriority(request, response);
				break;
			case "viewMostRecentProject":
				viewMostRecent(request, response);
				break;
			case "projectSearch":
				searchProject(request, response);
				break;
			}
		} catch (JsonSyntaxException | JsonIOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void searchProject(HttpServletRequest request, HttpServletResponse response) {
		try {
			JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
			
			DashboardService dashService = new DashboardService();
			List<Project> projects = new ArrayList<Project>();
			int page = 0 + data.get("page").getAsInt();
			
			int projectId = data.get("projectId").getAsInt();
			String projectName = data.get("projectName").getAsString();
			String projectStatus = data.get("projectStatus").getAsString();
			String projectAC = data.get("projectAC").getAsString();
			String projectPriority = data.get("projectPriority").getAsString();
			String projectTargetDate = data.get("projectTargetDate").getAsString();
			String projectLaunchDate = data.get("projectLaunchDate").getAsString();
			
			ProjectCriteria crit = new ProjectCriteria();
			crit.setProjectId(projectId);
			crit.setProjectName(projectName);
			crit.setProjectStatus(projectStatus);
			crit.setProjectPriority(projectPriority);
			crit.setProjectAccessChannel(projectAC);
			crit.setProjectTargetDate(projectTargetDate);
			crit.setProjectLaunchDate(projectLaunchDate);
			
			if(page > 0){
				projects = dashService.projectSearch(crit, page);
			} else {
				projects = dashService.projectSearch(crit);
			}
			
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

	private void viewMostRecent(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		try {
			DashboardService dashService = new DashboardService();
			List<Project> projects = new ArrayList<Project>();
			
			projects = dashService.get5RecentProject();
			
			Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(projects, new TypeToken<List<Project>>(){}.getType());
            JsonArray jsonArray = element.getAsJsonArray();
            
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().print(jsonArray);
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void viewMostPriority(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		try {
			DashboardService dashService = new DashboardService();
			List<Project> projects = new ArrayList<Project>();
			
			projects = dashService.getPriorityProj();
			
			Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(projects, new TypeToken<List<Project>>(){}.getType());
            JsonArray jsonArray = element.getAsJsonArray();
            
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().print(jsonArray);
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void viewAllProject(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		try {
			JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
			
			DashboardService dashService = new DashboardService();
			List<Project> projects = new ArrayList<Project>();
			
			int perPage = data.get("perPage").getAsInt();
			int pageNo = data.get("pageNo").getAsInt();
			projects = dashService.getAllProj(perPage, pageNo);
			
			Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(projects, new TypeToken<List<Project>>(){}.getType());
            JsonArray jsonArray = element.getAsJsonArray();
            
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().print(jsonArray);
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
