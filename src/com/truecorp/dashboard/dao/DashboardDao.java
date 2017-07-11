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
					project.setProjectStartDate(
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

			System.out.println(sqlPerPage.toString());
			System.out.println(criteriaValue);

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
				model.setProjectStartDate(
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
					project.setProjectStartDate(
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
					project.setProjectStartDate(
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

	public static int getTotalProject() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) as total FROM Project";
		int result = 0;
		try{
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				result = rs.getInt("total");
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return result;
	}
	
	public static int getTotalProjectYear() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) as total FROM Project WHERE YEAR(project_start_date) = YEAR(CURDATE())";
		int result = 0;
		try{
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				result = rs.getInt("total");
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return result;
	}
	
	public static int getTotalProjectMonth() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) as total FROM Project WHERE MONTH(project_start_date) = MONTH(CURDATE())";
		int result = 0;
		try{
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				result = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return result;
	}
	
	public static int getTotalProjectOpening() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) as total FROM Project WHERE project_status NOT IN ('cancelled', 'closed')";
		int result = 0;
		try{
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				result = rs.getInt("total");
			}
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return result;
	}
	
	public static int getTotalProjectFinished() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) as total FROM Project WHERE project_status = 'closed'";
		int result = 0;
		try{
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				result = rs.getInt("total");
			}
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return result;
	}
	
	public static int getTotalProjectCancelled() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) as total FROM Project WHERE project_status = 'cancelled'";
		int result = 0;
		try{
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				result = rs.getInt("total");
			}
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return result;
	}
	
	public static int getTotalProjectOnTime() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// CHANGE '2017-08-01' TO PROJECT FINISHDATE
		String sql = "SELECT distinct COUNT(*) as total from Project where DATEDIFF('2017-08-01', project_target_date) <= 0 order by project_id";
		int result = 0;
		try{
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				result = rs.getInt("total");
			}
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return result;
	}
	
	public static int getTotalProjectLate() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// CHANGE '2017-08-01' TO PROJECT FINISHDATE
		String sql = "SELECT distinct COUNT(*) as total from Project where DATEDIFF('2017-08-01', project_target_date) > 0 order by project_id";
		int result = 0;
		try{
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				result = rs.getInt("total");
			}
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return result;
	}
}
