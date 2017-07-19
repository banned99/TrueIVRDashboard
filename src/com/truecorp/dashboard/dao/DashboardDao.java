package com.truecorp.dashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.truecorp.dashboard.criteria.ProjectCriteria;
import com.truecorp.dashboard.model.Project;
import com.truecorp.dashboard.model.Statistic;
import com.truecorp.dashboard.util.DBUtil;

public class DashboardDao {

	public static List<Project> getRecentProject(int perPage, int page) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		int startRow = ((page-1) * perPage);
		int endRow = (page * perPage);
		
		List<Project> projectList = new ArrayList<Project>();
		String sql = "SELECT k.* FROM "
				+ "(SELECT p.*, ac.*, ul.* FROM Project p "
				+ "LEFT OUTER JOIN project_affect_acchannel pas on p.project_id=pas.project_id "
				+ "LEFT OUTER JOIN accesschannel ac on pas.ac_no = ac.ac_no "
				+ "LEFT OUTER JOIN userlan ul on p.project_userlan = ul.userlan "
				+ "ORDER BY p.project_id DESC LIMIT ?,?)"
				+ " k LIMIT ?;";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, perPage);
			rs = pstmt.executeQuery();
			
			if (rs != null) {
				Project project = null;
				List<String> ac = new ArrayList<String>();
				int temp = 0;
				
				while (rs.next()) {
					if(rs.getInt("project_id") != temp){
						ac.add(rs.getString("ac_name"));
						project = new Project();
						project.setProjectId(rs.getInt("project_id"));
						project.setProjectName(rs.getString("project_name"));
						project.setProjectStatus(rs.getString("project_status"));
						project.setProjectAccessChannel(ac);
						project.setProjectPriority(rs.getString("project_priority"));
						
						if (rs.getDate("project_user_request_date") != null)
							project.setProjectRequestDate(
									new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_user_request_date")));
						else project.setProjectRequestDate("");
						
						if (rs.getDate("project_submit_date") != null)
							project.setProjectRequestSubmitDate(
									new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_submit_date")));
						else project.setProjectRequestDate("");
						
						if (rs.getDate("project_target_date") != null)
							project.setProjectTargetDate(
									new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_target_date")));
						else project.setProjectTargetDate("");
						
						project.setProjectReason(rs.getString("project_reason"));
						
						if(rs.getDate("project_launch_date") != null)
							project.setProjectLaunchDate(
								new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_launch_date")));
						else project.setProjectLaunchDate("");
						
						project.setProjectRequester(rs.getString("project_requester"));
						project.setProjectOwner(rs.getString("project_owner"));
						project.setProjectManday(rs.getString("project_manday"));
						project.setProjectUcrNo(rs.getString("project_ucr_no"));
						
						if(rs.getDate("project_ucr_date") != null)
							project.setProjectUcrDate(
								new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_ucr_date")));
						else project.setProjectUcrDate("");
						
						project.setProjectDetails(rs.getString("project_detail"));
						project.setProjectLastComment(rs.getString("project_last_comment"));
						
						project.setProjectUserlan(rs.getString("userlan_name"));
						projectList.add(project);
					} else {
						ac.add(rs.getString("ac_name"));
					}
				}
			}
			
		} catch (Exception e) {

		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return projectList;
	}

	public static List<Project> projectSearch(ProjectCriteria cri, int page, int perPage) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = ((page-1) * perPage);
		int endRow = (page * perPage);

		List<Project> resultList = new ArrayList<Project>();
		List<String> criteriaValue = new ArrayList<String>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT p.* FROM Project p "
				+ "LEFT OUTER JOIN project_affect_acchannel pas on p.project_id=pas.project_id " 
				+ "LEFT OUTER JOIN accesschannel ac on pas.ac_no = ac.ac_no "
				+ "LEFT OUTER JOIN userlan ul on p.project_userlan = ul.userlan "
				+ "WHERE 1=1 ");

		if (String.valueOf(cri.getProjectId()) != null && String.valueOf(cri.getProjectId()).trim().length() > 0) {
			sql.append(" and project_id = ?");
			criteriaValue.add(String.valueOf(cri.getProjectId()));
		}

		if (cri.getProjectName() != null && cri.getProjectName().trim().length() > 0) {
			sql.append(" and lower(project_name) like ? ");
			criteriaValue.add("%" + cri.getProjectName().toLowerCase() + "%");
		}

		if (cri.getProjectStatus() != null && cri.getProjectStatus().trim().length() > 0) {
			sql.append(" and project_status = ? ");
			criteriaValue.add(cri.getProjectStatus());
		}

		if (cri.getProjectPriority() != null && cri.getProjectPriority().trim().length() > 0) {
			sql.append(" and project_priority = ? ");
			criteriaValue.add(cri.getProjectPriority());
		}
		
		if (cri.getProjectAccessChannel() != null && cri.getProjectAccessChannel().trim().length() > 0) {
			sql.append(" and ac_name = ?");
			criteriaValue.add(cri.getProjectAccessChannel());
		}
		
		if (cri.getProjectStartDate() != null && cri.getProjectStartDate().trim().length() > 0) {
			sql.append(" and project_request_date = date_format(?, '%Y-%m-%d')");
			criteriaValue.add(cri.getProjectStartDate());
		}

		if (cri.getProjectTargetDate() != null && cri.getProjectTargetDate().trim().length() > 0) {
			sql.append(" and project_target_date = date_format(?, '%Y-%m-%d')");
			criteriaValue.add(cri.getProjectTargetDate());
		}

		if (cri.getProjectLaunchDate() != null && cri.getProjectLaunchDate().trim().length() > 0) {
			sql.append(" and project_launch_date = date_format(?, '%Y-%m-%d')");
			criteriaValue.add(cri.getProjectLaunchDate());
		}

		String sqlPerPage = "SELECT k.* FROM (" + sql +" LIMIT ?,?) k LIMIT ?";

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sqlPerPage.toString());

			for (int i = 0; i < criteriaValue.size(); i++) {
				pstmt.setString(i + 1, criteriaValue.get(i));
			}

			pstmt.setInt(criteriaValue.size()+1, startRow);
			pstmt.setInt(criteriaValue.size()+2, endRow);
			pstmt.setInt(criteriaValue.size()+3, perPage);
			
			rs = pstmt.executeQuery();
			Project project = null;
			
			List<String> ac = new ArrayList<String>();
			int temp = 0;
			
			while (rs.next()) {
				if(rs.getInt("project_id") != temp){
					ac.add(rs.getString("ac_name"));
					temp = rs.getInt("project_id");
					project = new Project();
					project.setProjectId(rs.getInt("project_id"));
					project.setProjectName(rs.getString("project_name"));
					project.setProjectStatus(rs.getString("project_status"));
					project.setProjectAccessChannel(ac);
					project.setProjectPriority(rs.getString("project_priority"));
					
					if (rs.getDate("project_user_request_date") != null)
						project.setProjectRequestDate(
								new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_user_request_date")));
					else project.setProjectRequestDate("");
					
					if (rs.getDate("project_submit_date") != null)
						project.setProjectRequestSubmitDate(
								new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_submit_date")));
					else project.setProjectRequestDate("");
					
					if (rs.getDate("project_target_date") != null)
						project.setProjectTargetDate(
								new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_target_date")));
					else project.setProjectTargetDate("");
					
					project.setProjectReason(rs.getString("project_reason"));
					
					if(rs.getDate("project_launch_date") != null)
						project.setProjectLaunchDate(
							new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_launch_date")));
					else project.setProjectLaunchDate("");
					
					project.setProjectRequester(rs.getString("project_requester"));
					project.setProjectOwner(rs.getString("project_owner"));
					project.setProjectManday(rs.getString("project_manday"));
					project.setProjectUcrNo(rs.getString("project_ucr_no"));
					
					if(rs.getDate("project_ucr_date") != null)
						project.setProjectUcrDate(
							new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_ucr_date")));
					else project.setProjectUcrDate("");
					
					project.setProjectDetails(rs.getString("project_detail"));
					project.setProjectLastComment(rs.getString("project_last_comment"));
					
					project.setProjectUserlan(rs.getString("userlan_name"));
					resultList.add(project);
				} else {
					ac.add(rs.getString("ac_name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return resultList;
	}
	
	public static List<Project> getPriorityProject() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM Project p "
				+ "LEFT OUTER JOIN project_affect_acchannel pas on p.project_id=pas.project_id " 
				+ "LEFT OUTER JOIN accesschannel ac on pas.ac_no = ac.ac_no "
				+ "WHERE DATEDIFF(project_target_date, CURDATE()) > 0 AND "
				+ "DATEDIFF(project_target_date, CURDATE()) <= 5 ORDER BY project_target_date DESC LIMIT 10";
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			List<Project> projectList = new ArrayList<Project>();
			Project project = null;
			
			if( rs != null){
				List<String> ac = new ArrayList<String>();
				int temp = 0;
				while(rs.next()){
					if(rs.getInt("project_id") != temp){
						ac = new ArrayList<String>();
						ac.add(rs.getString("ac_name"));
						temp = rs.getInt("project_id");
						project = new Project();
						project.setProjectId(temp);
						project.setProjectName(rs.getString("project_name"));
						project.setProjectStatus(rs.getString("project_status"));
						project.setProjectPriority(rs.getString("project_priority"));
						project.setProjectAccessChannel(ac);
						
						if (rs.getDate("project_target_date") != null)
							project.setProjectTargetDate(
									new SimpleDateFormat("yyyy/MM/dd").format(rs.getDate("project_target_date")));
						else project.setProjectTargetDate("");
						
						project.setProjectRequester(rs.getString("project_requester"));
						project.setProjectOwner(rs.getString("project_owner"));
						projectList.add(project);
					} else {
						ac.add(rs.getString("ac_name"));
						project.setProjectAccessChannel(ac);
					}
				}
			}
			
			return projectList;
		} catch (Exception e) {
			
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return null;
	}

	public static List<Project> get5RecentProject() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM Project p "
				+ "LEFT OUTER JOIN project_affect_acchannel pas on p.project_id=pas.project_id " 
				+ "LEFT OUTER JOIN accesschannel ac on pas.ac_no = ac.ac_no "
				+ "ORDER BY p.project_id DESC LIMIT 5;";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			List<Project> projectList = new ArrayList<Project>();
			Project project = null;
			
			if (rs != null){
				List<String> ac = new ArrayList<String>();
				int temp = 0;
				while (rs.next()){
					if(rs.getInt("project_id") != temp){
						ac.add(rs.getString("ac_name"));
						temp = rs.getInt("project_id");
						project = new Project();
						project.setProjectId(rs.getInt("project_id"));
						project.setProjectName(rs.getString("project_name"));
						project.setProjectStatus(rs.getString("project_status"));
						project.setProjectAccessChannel(ac);
						project.setProjectPriority(rs.getString("project_priority"));
						
						if (rs.getDate("project_user_request_date") != null)
							project.setProjectRequestDate(
									new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_user_request_date")));
						else project.setProjectRequestDate("");
						
						if (rs.getDate("project_submit_date") != null)
							project.setProjectRequestSubmitDate(
									new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_submit_date")));
						else project.setProjectRequestDate("");
						
						if (rs.getDate("project_target_date") != null)
							project.setProjectTargetDate(
									new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_target_date")));
						else project.setProjectTargetDate("");
						
						project.setProjectRequester(rs.getString("project_requester"));
						project.setProjectOwner(rs.getString("project_owner"));
						projectList.add(project);
					} else {
						ac.add(rs.getString("ac_name"));
					}
				}
			}
			
			return projectList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return null;
	}
	
	public static Statistic getStatistics() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Statistic stats = null;
		String[] sql = {
				"SELECT COUNT(*) as total FROM Project",
				"SELECT COUNT(*) as total FROM Project WHERE YEAR(project_submit_date) = YEAR(CURDATE()) AND YEAR(project_submit_date) = YEAR(CURDATE())",
				"SELECT COUNT(*) as total FROM Project WHERE project_status NOT IN ('Cancel', 'Done') AND YEAR(project_submit_date) = YEAR(CURDATE())",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Done' AND YEAR(project_submit_date) = YEAR(CURDATE())",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Cancel' AND YEAR(project_submit_date) = YEAR(CURDATE())",
				"SELECT COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) <= 0 AND YEAR(project_submit_date) = YEAR(CURDATE()) AND project_status = 'DONE' ",
				"SELECT COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) > 0 AND YEAR(project_submit_date) = YEAR(CURDATE()) AND project_status = 'DONE' ",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Develop' AND YEAR(project_submit_date) = YEAR(CURDATE())",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Requirement' AND YEAR(project_submit_date) = YEAR(CURDATE())",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Pending' AND YEAR(project_submit_date) = YEAR(CURDATE())",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Initiate' AND YEAR(project_submit_date) = YEAR(CURDATE())"
				};
		try{
			conn = DBUtil.getConnection();
			stats = new Statistic();
			
			for(int i=0; i<sql.length; i++){
				pstmt = conn.prepareStatement(sql[i]);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					switch (i){
					case 0:
						stats.setTotal_projects(rs.getInt("total"));
						break;
					case 1:
						stats.setTotal_in_year(rs.getInt("total"));
						break;
					case 2:
						stats.setTotal_project_opened(rs.getInt("total"));
						break;
					case 3:
						stats.setTotal_project_finished(rs.getInt("total"));
						break;
					case 4:
						stats.setTotal_project_cancelled(rs.getInt("total"));
						break;
					case 5:
						stats.setTotal_project_on_time(rs.getInt("total"));
						break;
					case 6:
						stats.setTotal_project_late(rs.getInt("total"));
						break;
					case 7:
						stats.setStatus_dev(rs.getInt("total"));
						break;
					case 8:
						stats.setStatus_req(rs.getInt("total"));
						break;
					case 9:
						stats.setStatus_pending(rs.getInt("total"));
						break;
					case 10:
						stats.setStatus_init(rs.getInt("total"));
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return stats;
	}
	
	public static Statistic getStatistics(String year) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Statistic stats = null;
		String[] sql = {
				"SELECT COUNT(*) as total FROM Project WHERE YEAR(project_submit_date) = ?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status NOT IN ('Cancel', 'Done') AND YEAR(project_submit_date) = ?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Done' AND YEAR(project_submit_date) = ?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Cancel' AND YEAR(project_submit_date) = ?",
				"SELECT COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) <= 0 AND YEAR(project_submit_date) = ?",
				"SELECT COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) > 0 AND YEAR(project_submit_date) = ?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Develop' AND YEAR(project_submit_date) = ?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Requirement' AND YEAR(project_submit_date) = ?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Pending' AND YEAR(project_submit_date) = ?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Initiate' AND YEAR(project_submit_date) = ?"
				};
		try{
			conn = DBUtil.getConnection();
			stats = new Statistic();
			
			for(int i=0; i<sql.length; i++){
				pstmt = conn.prepareStatement(sql[i]);
				pstmt.setString(1, year);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					switch (i){
					case 0:
						stats.setTotal_in_year(rs.getInt("total"));
						break;
					case 1:
						stats.setTotal_project_opened(rs.getInt("total"));
						break;
					case 2:
						stats.setTotal_project_finished(rs.getInt("total"));
						break;
					case 3:
						stats.setTotal_project_cancelled(rs.getInt("total"));
						break;
					case 4:
						stats.setTotal_project_on_time(rs.getInt("total"));
						break;
					case 5:
						stats.setTotal_project_late(rs.getInt("total"));
						break;
					case 6:
						stats.setStatus_dev(rs.getInt("total"));
						break;
					case 7:
						stats.setStatus_req(rs.getInt("total"));
						break;
					case 8:
						stats.setStatus_pending(rs.getInt("total"));
						break;
					case 9:
						stats.setStatus_init(rs.getInt("total"));
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return stats;
	}
	
	public static Statistic getStatistics(String year1, String year2) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Statistic stats = null;
		String[] sql = {
				"SELECT COUNT(*) as total FROM Project WHERE YEAR(project_submit_date) BETWEEN(?,?)",
				"SELECT COUNT(*) as total FROM Project WHERE project_status NOT IN ('Cancel', 'Done') AND YEAR(project_submit_date) BETWEEN(?,?)",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Done' AND YEAR(project_submit_date) BETWEEN(?,?)",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Cancel' AND YEAR(project_submit_date) BETWEEN(?,?)",
				"SELECT distinct COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) <= 0 AND YEAR(project_submit_date) BETWEEN(?,?)",
				"SELECT distinct COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) > 0 AND YEAR(project_submit_date) BETWEEN(?,?)",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Develop' AND YEAR(project_submit_date) BETWEEN(?,?)",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Requirement' AND YEAR(project_submit_date) BETWEEN(?,?)",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Pending' AND YEAR(project_submit_date) BETWEEN(?,?)",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Initiate' AND YEAR(project_submit_date) BETWEEN(?,?)"
				};
		try{
			conn = DBUtil.getConnection();
			stats = new Statistic();
			
			for(int i=0; i<sql.length; i++){
				pstmt = conn.prepareStatement(sql[i]);
				pstmt.setString(1, year1);
				pstmt.setString(2, year2);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					switch (i){
					case 0:
						stats.setTotal_in_year(rs.getInt("total"));
						break;
					case 1:
						stats.setTotal_project_opened(rs.getInt("total"));
						break;
					case 2:
						stats.setTotal_project_finished(rs.getInt("total"));
						break;
					case 3:
						stats.setTotal_project_cancelled(rs.getInt("total"));
						break;
					case 4:
						stats.setTotal_project_on_time(rs.getInt("total"));
						break;
					case 5:
						stats.setTotal_project_late(rs.getInt("total"));
						break;
					case 6:
						stats.setStatus_dev(rs.getInt("total"));
						break;
					case 7:
						stats.setStatus_req(rs.getInt("total"));
						break;
					case 8:
						stats.setStatus_pending(rs.getInt("total"));
						break;
					case 9:
						stats.setStatus_init(rs.getInt("total"));
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return stats;
	}

	public static List<Project> getMyProject(String username) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Project> projs = null;
		String sql = "SELECT * FROM Project p "
				+ "LEFT OUTER JOIN project_affect_acchannel pas ON p.project_id = pas.project_id "
				+ "LEFT OUTER JOIN accesschannel ac on pas.ac_no = ac.ac_no "
				+ "WHERE project_owner = ?";
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(0, username);
			
			rs = pstmt.executeQuery();
			
			projs = new ArrayList<Project>();
			Project project = null;
			List<String> ac = new ArrayList<String>();
			int temp = 0;
			
			while (rs.next()) {
				if(rs.getInt("project_id") != temp){
					ac.add(rs.getString("ac_name"));
					project = new Project();
					project.setProjectId(rs.getInt("project_id"));
					project.setProjectName(rs.getString("project_name"));
					project.setProjectStatus(rs.getString("project_status"));
					project.setProjectAccessChannel(ac);
					project.setProjectPriority(rs.getString("project_priority"));
					
					if (rs.getDate("project_user_request_date") != null)
						project.setProjectRequestDate(
								new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_user_request_date")));
					else project.setProjectRequestDate("");
					
					if (rs.getDate("project_submit_date") != null)
						project.setProjectRequestSubmitDate(
								new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_submit_date")));
					else project.setProjectRequestDate("");
					
					if (rs.getDate("project_target_date") != null)
						project.setProjectTargetDate(
								new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_target_date")));
					else project.setProjectTargetDate("");
					
					if(rs.getDate("project_launch_date") != null)
						project.setProjectLaunchDate(
							new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_launch_date")));
					else project.setProjectLaunchDate("");
					
					project.setProjectRequester(rs.getString("project_requester"));
					project.setProjectOwner(rs.getString("project_owner"));
					project.setProjectUserlan(rs.getString("userlan_name"));
					projs.add(project);
				} else {
					ac.add(rs.getString("ac_name"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return projs;
	}

	public static Statistic getMyStatistics(String username) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Statistic stats = null;
		String[] sql = {
				"SELECT COUNT(*) as total FROM Project WHERE project_owner=?",
				"SELECT COUNT(*) as total FROM Project WHERE YEAR(project_submit_date) = YEAR(CURDATE()) AND project_owner=?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status NOT IN ('Cancel', 'Done') AND project_owner=? AND YEAR(project_submit_date) = YEAR(CURDATE())",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Done' OR project_launch_date != null AND project_owner=? AND YEAR(project_submit_date) = YEAR(CURDATE())",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Cancel' AND project_owner=? AND YEAR(project_submit_date) = YEAR(CURDATE())",
				"SELECT distinct COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) <= 0 order by project_id AND project_owner=? AND YEAR(project_submit_date) = YEAR(CURDATE())",
				"SELECT distinct COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) > 0 order by project_id AND project_owner=? AND YEAR(project_submit_date) = YEAR(CURDATE())",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Develop' AND YEAR(project_submit_date) = YEAR(CURDATE())",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Requirement' AND YEAR(project_submit_date) = YEAR(CURDATE())",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Pending' AND YEAR(project_submit_date) = YEAR(CURDATE())",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Initiate' AND YEAR(project_submit_date) = YEAR(CURDATE())"
				};
		try{
			conn = DBUtil.getConnection();
			stats = new Statistic();
			
			for(int i=0; i<sql.length; i++){
				pstmt = conn.prepareStatement(sql[i]);
				pstmt.setString(1, username);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					switch (i){
					case 0:
						stats.setTotal_projects(rs.getInt("total"));
						break;
					case 1:
						stats.setTotal_in_year(rs.getInt("total"));
						break;
					case 2:
						stats.setTotal_project_opened(rs.getInt("total"));
						break;
					case 3:
						stats.setTotal_project_finished(rs.getInt("total"));
						break;
					case 4:
						stats.setTotal_project_cancelled(rs.getInt("total"));
						break;
					case 5:
						stats.setTotal_project_on_time(rs.getInt("total"));
						break;
					case 6:
						stats.setTotal_project_late(rs.getInt("total"));
						break;
					case 7:
						stats.setStatus_dev(rs.getInt("total"));
						break;
					case 8:
						stats.setStatus_req(rs.getInt("total"));
						break;
					case 9:
						stats.setStatus_pending(rs.getInt("total"));
						break;
					case 10:
						stats.setStatus_init(rs.getInt("total"));
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return stats;
	}
	
	public static Statistic getMyStatistics(String username, String year) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Statistic stats = null;
		String[] sql = {
				"SELECT COUNT(*) as total FROM Project WHERE YEAR(project_submit_date) = ? AND project_owner=?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status NOT IN ('Cancel', 'Done') AND YEAR(project_submit_date) = ? AND project_owner=?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Done' AND YEAR(project_submit_date) = ? AND project_owner=?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Cancel' AND YEAR(project_submit_date) = ? AND project_owner=?",
				"SELECT distinct COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) <= 0 AND YEAR(project_submit_date) = ? AND project_owner=?",
				"SELECT distinct COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) > 0 AND YEAR(project_submit_date) = ? AND project_owner=?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Develop' AND YEAR(project_submit_date) = ? AND project_owner=?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Requirement' AND YEAR(project_submit_date) = ? AND project_owner=?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Pending' AND YEAR(project_submit_date) = ? AND project_owner=?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Initiate' AND YEAR(project_submit_date) = ? AND project_owner=?"
				};
		try{
			conn = DBUtil.getConnection();
			stats = new Statistic();
			
			for(int i=0; i<sql.length; i++){
				pstmt = conn.prepareStatement(sql[i]);
				pstmt.setString(1, year);
				pstmt.setString(2, username);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					switch (i){
					case 0:
						stats.setTotal_in_year(rs.getInt("total"));
						break;
					case 1:
						stats.setTotal_project_opened(rs.getInt("total"));
						break;
					case 2:
						stats.setTotal_project_finished(rs.getInt("total"));
						break;
					case 3:
						stats.setTotal_project_cancelled(rs.getInt("total"));
						break;
					case 4:
						stats.setTotal_project_on_time(rs.getInt("total"));
						break;
					case 5:
						stats.setTotal_project_late(rs.getInt("total"));
						break;
					case 6:
						stats.setStatus_dev(rs.getInt("total"));
						break;
					case 7:
						stats.setStatus_req(rs.getInt("total"));
						break;
					case 8:
						stats.setStatus_pending(rs.getInt("total"));
						break;
					case 9:
						stats.setStatus_init(rs.getInt("total"));
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return stats;
	}
	
	public static Statistic getMyStatistics(String username, String year1, String year2) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Statistic myStat = null;
		
		String[] sql = {
				"SELECT COUNT(*) as total FROM Project WHERE YEAR(project_submit_date) BETWEEN(?,?) AND project_owner = ?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status NOT IN ('Cancel', 'Done') AND YEAR(project_submit_date) BETWEEN(?,?) AND project_owner = ?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Done' AND YEAR(project_submit_date) BETWEEN(?,?) AND project_owner = ?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Cancel' AND YEAR(project_submit_date) BETWEEN(?,?) AND project_owner = ?",
				"SELECT distinct COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) <= 0 AND YEAR(project_submit_date) BETWEEN(?,?) AND project_owner = ?",
				"SELECT distinct COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) > 0 AND YEAR(project_submit_date) BETWEEN(?,?) AND project_owner = ?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Develop' AND YEAR(project_submit_date) BETWEEN(?,?) AND project_owner = ?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Requirement' AND YEAR(project_submit_date) BETWEEN(?,?) AND project_owner = ?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Pending' AND YEAR(project_submit_date) BETWEEN(?,?) AND project_owner = ?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'Initiate' AND YEAR(project_submit_date) BETWEEN(?,?) AND project_owner = ?"
				};
		try{
			conn = DBUtil.getConnection();
			myStat = new Statistic();
			
			for(int i=0; i<sql.length; i++){
				pstmt = conn.prepareStatement(sql[i]);
				pstmt.setString(1, year1);
				pstmt.setString(2, year2);
				pstmt.setString(3, username);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					switch (i){
					case 0:
						myStat.setTotal_in_year(rs.getInt("total"));
						break;
					case 1:
						myStat.setTotal_project_opened(rs.getInt("total"));
						break;
					case 2:
						myStat.setTotal_project_finished(rs.getInt("total"));
						break;
					case 3:
						myStat.setTotal_project_cancelled(rs.getInt("total"));
						break;
					case 4:
						myStat.setTotal_project_on_time(rs.getInt("total"));
						break;
					case 5:
						myStat.setTotal_project_late(rs.getInt("total"));
						break;
					case 6:
						myStat.setStatus_dev(rs.getInt("total"));
						break;
					case 7:
						myStat.setStatus_req(rs.getInt("total"));
						break;
					case 8:
						myStat.setStatus_pending(rs.getInt("total"));
						break;
					case 9:
						myStat.setStatus_init(rs.getInt("total"));
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return myStat;
	}
}