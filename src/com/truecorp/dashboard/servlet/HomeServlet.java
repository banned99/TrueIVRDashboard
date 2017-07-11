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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.truecorp.dashboard.model.Project;
import com.truecorp.dashboard.service.DashboardService;

/**
 * Servlet implementation class HomeServlet
 * 
 * @param <E>
 */
public class HomeServlet<E> extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "viewMostPrioProject":
				viewMostPriority(request, response);
				break;
			case "viewMostRecentProject":
				viewMostRecent(request, response);
				break;
			case "viewStatistics":
				viewStat(request, response);
				break;
			case "view":
				request.getRequestDispatcher("pages/home.jsp").forward(request, response);
				break;
			}
		} catch (SQLException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void viewStat(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		DashboardService ds = new DashboardService();

		int total_projects = ds.getTotalProject();
		int total_in_year = ds.getTotalProjectYear();
		int total_in_month = ds.getTotalProjectMonth();
		int total_project_opened = ds.getTotalOpeningProject();
		int total_project_finished = ds.getTotalFinishedProject();
		int total_project_cancelled = ds.getTotalCancelled();
		int total_project_on_time = ds.getTotalOnTimeProject();
		int total_project_late = ds.getTotalLateProject();
		
		Map<String, Integer> stats = new HashMap<String, Integer>();
		stats.put("total_projects", total_projects);
		stats.put("total_in_year", total_in_year);
		stats.put("total_in_month", total_in_month);
		stats.put("total_project_opened", total_project_opened);
		stats.put("total_project_finished", total_project_finished);
		stats.put("total_project_cancelled", total_project_cancelled);
		stats.put("total_project_on_time", total_project_on_time);
		stats.put("total_project_late", total_project_late);
		
		new Gson().toJson(stats, response.getWriter());
	}

	private void viewMostPriority(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		try {
			DashboardService dashService = new DashboardService();
			List<Project> projects = new ArrayList<Project>();

			projects = dashService.getPriorityProj();

			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(projects, new TypeToken<List<Project>>() {}.getType());
			JsonArray jsonArray = element.getAsJsonArray();
			
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(jsonArray);
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
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
			JsonElement element = gson.toJsonTree(projects, new TypeToken<List<Project>>() {}.getType());
			JsonArray jsonArray = element.getAsJsonArray();
			
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(jsonArray);
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
