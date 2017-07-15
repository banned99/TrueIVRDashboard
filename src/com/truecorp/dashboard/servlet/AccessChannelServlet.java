package com.truecorp.dashboard.servlet;

import java.io.IOException;
import java.sql.SQLException;
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
import com.google.gson.reflect.TypeToken;
import com.truecorp.dashboard.criteria.ACCriteria;
import com.truecorp.dashboard.model.AccessChannel;
import com.truecorp.dashboard.service.AccessChannelService;

/**
 * Servlet implementation class AccessChannelServlet
 */
public class AccessChannelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessChannelServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String action = request.getParameter("action");
		switch(action){
		case "view":
			request.getRequestDispatcher("pages/accesschannel.jsp").forward(request, response);
			break;
		case "viewAll":
			viewAllAccessChannel(request, response);
			break;
		case "getTotal":
			getTotalAC(request, response);
			break;
		case "acSearch":
			searchAC(request, response);
			break;
		}
	}

	private void searchAC(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		AccessChannelService acs = new AccessChannelService();
		
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int perPage = Integer.parseInt(request.getParameter("perPage"));
		
		ACCriteria cri = new ACCriteria();
		cri.setAcNo(request.getParameter("acNo"));
		cri.setAcName(request.getParameter("acName"));
		cri.setAcProductNo(request.getParameter("acProductNo"));
		cri.setAcProductName(request.getParameter("acProductName"));
		cri.setDisplay(request.getParameter("acDisplay"));
		
		List<AccessChannel> acList = acs.searchAC(cri, perPage, pageNo);
		
		Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(acList, new TypeToken<List<AccessChannel>>(){}.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(jsonArray);
		
	}
	
	private void getTotalAC(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		AccessChannelService acs = new AccessChannelService();
		
		Map<String, Integer> respond = new HashMap<String, Integer>();
		respond.put("total_ac", acs.getTotal());
		
		new Gson().toJson(respond, response.getWriter());
	}

	private void viewAllAccessChannel(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		AccessChannelService acs = new AccessChannelService();
		
		int perPage = Integer.parseInt(request.getParameter("perPage"));
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		List<AccessChannel> acList = acs.getAll(perPage, pageNo);
		
		Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(acList, new TypeToken<List<AccessChannel>>(){}.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(jsonArray);
	}
	
	

}
