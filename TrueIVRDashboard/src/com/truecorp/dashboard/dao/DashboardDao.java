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

	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

	public static List<Project> getRecentProject(int perPage, int page) throws SQLException {
		String sql = "SELECT project_id, project_name, " + "project_status, project_ac, "
				+ "project_target_date, project_launch_date, " + "project_file FROM Project WHERE ROWNUM >="
				+ (perPage * (page - 1)) + "AND ROWNUM <=" + (perPage * (page)) + ";";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			List<Project> projectList = new ArrayList<Project>();
			if (rs != null) {
				Project project = null;

				while (rs.next()) {
					project = new Project();
					project.setProjectId(rs.getInt("project_id"));
					project.setProjectName(rs.getString("project_name"));
					project.setProjectAccessChannel(rs.getString("project_ac"));
					project.setProjectPriority(rs.getString("project_priority"));
					project.setProjectTargetDate(
							new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_target_date")));
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

	public static List<Project> projectSearch(ProjectCriteria cri) throws SQLException {

		List<Project> resultList = new ArrayList<Project>();
		List<String> criteriaValue = new ArrayList<String>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT project_id, project_name, " + "project_status, project_ac, project_priority, "
				+ "project_target_date, project_launch_date, project_file FROM Project WHERE 1=1 ");

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

		if (cri.getProjectTargetDate() != null && cri.getProjectTargetDate().trim().length() > 0) {
			sql.append(" and project_target_date >= to_date(?,'dd/mm/yyyy')");
			criteriaValue.add(cri.getProjectTargetDate());
		}

		if (cri.getProjectLaunchDate() != null && cri.getProjectLaunchDate().trim().length() > 0) {
			sql.append(" and project_launch_date <= to_date(?,'dd/mm/yyyy')+0.99999");
			criteriaValue.add(cri.getProjectLaunchDate());
		}

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			System.out.println(sql.toString());
			System.out.println(criteriaValue);

			for (int i = 0; i < criteriaValue.size(); i++) {
				pstmt.setString(i + 1, criteriaValue.get(i));
			}

			rs = pstmt.executeQuery();
			Project model = null;

			while (rs.next()) {
				model = new Project();
				model.setProjectId(rs.getInt("project_id"));
				model.setProjectName(rs.getString("project_name"));
				model.setProjectStatus(rs.getString("project_status"));
				model.setProjectPriority(rs.getString("project_priority"));
				model.setProjectTargetDate(
						new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_target_date")));
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

	public static List<Project> projectSearch(ProjectCriteria cri, int page) throws SQLException {
		final int startRow = (((page - 1) * 20) + 1);
		final int endRow = (page * 20);

		List<Project> resultList = new ArrayList<Project>();
		List<String> criteriaValue = new ArrayList<String>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT project_id, project_name, " + "project_status, project_ac, project_priority, "
				+ "project_target_date, project_launch_date, project_file FROM Project WHERE 1=1 ");

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

		if (cri.getProjectTargetDate() != null && cri.getProjectTargetDate().trim().length() > 0) {
			sql.append(" and project_target_date >= to_date(?,'dd/mm/yyyy')");
			criteriaValue.add(cri.getProjectTargetDate());
		}

		if (cri.getProjectLaunchDate() != null && cri.getProjectLaunchDate().trim().length() > 0) {
			sql.append(" and project_launch_date <= to_date(?,'dd/mm/yyyy')+0.99999");
			criteriaValue.add(cri.getProjectLaunchDate());
		}

		String sqlPerPage = " SELECT * FROM ( " + " SELECT tab.*, ROWNUM rn FROM ( " + sql.toString() + " ) tab "
				+ " ) WHERE rn >= " + startRow + " AND rn <= " + endRow + " ";

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sqlPerPage.toString());

			System.out.println(sqlPerPage.toString());
			System.out.println(criteriaValue);

			for (int i = 0; i < criteriaValue.size(); i++) {
				pstmt.setString(i + 1, criteriaValue.get(i));
			}

			rs = pstmt.executeQuery();
			Project model = null;

			while (rs.next()) {
				model = new Project();
				model.setProjectId(rs.getInt("project_id"));
				model.setProjectName(rs.getString("project_name"));
				model.setProjectStatus(rs.getString("project_status"));
				model.setProjectPriority(rs.getString("project_priority"));
				model.setProjectStartDate(
						new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_target_date")));
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
	
	public static List<Project> getPriorityProject() throws SQLException {
		String sql = "SELECT * FROM Project WHERE project_target_date - CURDATE() <= 5";
		
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
					project.setProjectAccessChannel(rs.getString("project_ac"));
					project.setProjectPriority(rs.getString("project_priority"));
					project.setProjectStartDate(
							new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_target_date")));
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
							new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("project_target_date")));
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
}
