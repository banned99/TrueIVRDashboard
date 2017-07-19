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
import com.truecorp.dashboard.model.Authorize;
import com.truecorp.dashboard.model.Project;
import com.truecorp.dashboard.model.Statistic;
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
			case "viewMyStats":
				viewMyStat(request, response);
				break;
			case "viewMostPrioProject":
				viewMostPriority(request, response);
				break;
			case "viewMostRecentProject":
				viewMostRecent(request, response);
				break;
			case "viewStatistics":
				viewStat(request, response);
				break;
			case "getTotalProjects":
				getTotalProjects(request, response);
				break;
			case "view":
				request.getRequestDispatcher("pages/home.jsp").forward(request, response);
				break;
			}
		} catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}
	}

	private void viewStat(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		DashboardService ds = new DashboardService();
		
		String year1 = request.getParameter("year1");
		String year2 = request.getParameter("year2");
		Statistic stat = null;
		
		if (year1 == null && year2 == null) {
			stat = ds.getStatistic();
		} else if (year1 != null && year2 == null) {
			stat = ds.getStatistic(year1);
		} else {
			stat = ds.getStatistic(year1, year2);
		}
		
		
		String json = new Gson().toJson(stat);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
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
			e.printStackTrace();
		}
	}
	
	private void viewMyStat(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		DashboardService ds = new DashboardService();
		
		String year1 = request.getParameter("year1");
		String year2 = request.getParameter("year2");
		Authorize auth = (Authorize) request.getSession().getAttribute("user");
		String username = auth.getUsername();
		Statistic stat = null;
		
		if (year1 == null && year2 == null) {
			stat = ds.getMyStatistic(username);
		} else if (year1 != null && year2 == null) {
			stat = ds.getMyStatistic(username, year1);
		} else {
			stat = ds.getMyStatistic(username, year1, year2);
		}
		
		
		String json = new Gson().toJson(stat);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	}
	
	private void getTotalProjects(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		DashboardService ds = new DashboardService();
		
		Map<String, Integer> respond = new HashMap<String, Integer>();
		respond.put("total_projects", ds.getStatistic().getTotal_projects());
		
		new Gson().toJson(respond, response.getWriter());
	}

}
