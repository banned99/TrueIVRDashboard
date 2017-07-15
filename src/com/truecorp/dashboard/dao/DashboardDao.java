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

	//TESTED
	public static List<Project> getRecentProject(int perPage, int page) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = ((page-1) * perPage);
		int endRow = (page * perPage);
		
		String sql = "select k.* from (SELECT p.* FROM Project p order by project_id DESC LIMIT ?,?) k LIMIT ?;";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, perPage);
			rs = pstmt.executeQuery();
			
			List<Project> projectList = new ArrayList<Project>();
			if (rs != null) {
				Project project = null;

				while (rs.next()) {
					project = new Project();
					project.setProjectId(rs.getInt("project_id"));
					project.setProjectName(rs.getString("project_name"));
					project.setProjectStatus(rs.getString("project_status"));
					project.setProjectAccessChannel(rs.getString("project_ac"));
					project.setProjectPriority(rs.getString("project_priority"));
					project.setProjectRequestDate(
							new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_start_date")));
					project.setProjectTargetDate(
							new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_target_date")));
					project.setProjectLaunchDate(
							new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_launch_date")));
					project.setProjectFile(rs.getBlob("project_file"));
					projectList.add(project);
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

	//Waiting for test
	public static List<Project> projectSearch(ProjectCriteria cri, int page, int perPage) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = ((page-1) * perPage);
		int endRow = (page * perPage);

		List<Project> resultList = new ArrayList<Project>();
		List<String> criteriaValue = new ArrayList<String>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT p.* FROM Project p WHERE 1=1 ");

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
			sql.append(" and project_ac = ?");
			criteriaValue.add(cri.getProjectAccessChannel());
		}
		
		if (cri.getProjectStartDate() != null && cri.getProjectStartDate().trim().length() > 0) {
			sql.append(" and project_start_date = date_format(?, '%Y-%m-%d')");
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
			Project model = null;

			while (rs.next()) {
				model = new Project();
				model.setProjectId(rs.getInt("project_id"));
				model.setProjectName(rs.getString("project_name"));
				model.setProjectStatus(rs.getString("project_status"));
				model.setProjectPriority(rs.getString("project_priority"));
				model.setProjectRequestDate(
						new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_start_date")));
				model.setProjectTargetDate(
						new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_target_date")));
				model.setProjectLaunchDate(
						new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_launch_date")));
				model.setProjectAccessChannel(rs.getString("project_ac"));
				model.setProjectFile(rs.getBlob("project_file"));
				resultList.add(model);
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
	
	//Testing
	public static List<Project> getPriorityProject() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM Project WHERE DATEDIFF(project_target_date, CURDATE()) > 0 AND "
				+ "DATEDIFF(project_target_date, CURDATE()) <= 5 ORDER BY project_target_date DESC";
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			List<Project> projectList = new ArrayList<Project>();
			Project project = null;
			
			if( rs != null){
				while(rs.next()){
					project = new Project();
					project.setProjectId(rs.getInt("project_id"));
					project.setProjectName(rs.getString("project_name"));
					project.setProjectStatus(rs.getString("project_status"));
					project.setProjectAccessChannel(rs.getString("project_ac"));
					project.setProjectPriority(rs.getString("project_priority"));
					project.setProjectRequestDate(
							new SimpleDateFormat("yyyy/MM/dd").format(rs.getDate("project_start_date")));
					project.setProjectTargetDate(
							new SimpleDateFormat("yyyy/MM/dd").format(rs.getDate("project_target_date")));
					project.setProjectLaunchDate(
							new SimpleDateFormat("yyyy/MM/dd").format(rs.getDate("project_launch_date")));
					project.setProjectFile(rs.getBlob("project_file"));
					projectList.add(project);
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
		
		String sql = "SELECT * FROM Project ORDER BY project_id DESC LIMIT 5;";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			List<Project> projectList = new ArrayList<Project>();
			Project project = null;
			
			if (rs != null){
				while (rs.next()){
					project = new Project();
					project.setProjectId(rs.getInt("project_id"));
					project.setProjectName(rs.getString("project_name"));
					project.setProjectStatus(rs.getString("project_status"));
					project.setProjectAccessChannel(rs.getString("project_ac"));
					project.setProjectPriority(rs.getString("project_priority"));
					project.setProjectRequestDate(
							new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_start_date")));
					project.setProjectTargetDate(
							new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_target_date")));
					project.setProjectLaunchDate(
							new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_launch_date")));
					project.setProjectFile(rs.getBlob("project_file"));
					projectList.add(project);
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
				"SELECT COUNT(*) as total FROM Project WHERE YEAR(project_start_date) = YEAR(CURDATE())",
				"SELECT COUNT(*) as total FROM Project WHERE MONTH(project_start_date) = MONTH(CURDATE())",
				"SELECT COUNT(*) as total FROM Project WHERE project_status NOT IN ('cancelled', 'closed')",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'closed'",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'cancelled'",
				"SELECT distinct COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) <= 0 order by project_id",
				"SELECT distinct COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) > 0 order by project_id"
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
						stats.setTotal_in_month(rs.getInt("total"));
						break;
					case 3:
						stats.setTotal_project_opened(rs.getInt("total"));
						break;
					case 4:
						stats.setTotal_project_finished(rs.getInt("total"));
						break;
					case 5:
						stats.setTotal_project_cancelled(rs.getInt("total"));
						break;
					case 6:
						stats.setTotal_project_on_time(rs.getInt("total"));
						break;
					case 7:
						stats.setTotal_project_late(rs.getInt("total"));
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
				"SELECT COUNT(*) as total FROM Project WHERE YEAR(project_request_date) = ?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status NOT IN ('cancelled', 'closed') AND YEAR(project_request_date) = ?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'closed' AND YEAR(project_request_date) = ?",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'cancelled' AND YEAR(project_request_date) = ?",
				"SELECT distinct COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) <= 0 AND YEAR(project_request_date) = ?",
				"SELECT distinct COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) > 0 AND YEAR(project_request_date) = ?"
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
				"SELECT COUNT(*) as total FROM Project WHERE YEAR(project_request_date) BETWEEN(?,?)",
				"SELECT COUNT(*) as total FROM Project WHERE project_status NOT IN ('cancelled', 'closed') AND YEAR(project_request_date) BETWEEN(?,?)",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'closed' AND YEAR(project_request_date) BETWEEN(?,?)",
				"SELECT COUNT(*) as total FROM Project WHERE project_status = 'cancelled' AND YEAR(project_request_date) BETWEEN(?,?)",
				"SELECT distinct COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) <= 0 AND YEAR(project_request_date) BETWEEN(?,?)",
				"SELECT distinct COUNT(*) as total from Project where DATEDIFF(project_launch_date, project_target_date) > 0 AND YEAR(project_request_date) BETWEEN(?,?)"
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
}