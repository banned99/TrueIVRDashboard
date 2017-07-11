package com.truecorp.dashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import com.truecorp.dashboard.model.Project;
import com.truecorp.dashboard.util.DBUtil;

public class ProjectDao {

	public static Project getProjectById(String project_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM Project WHERE project_id=? LIMIT 1";
		Project project = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(0, project_id);
			rs = pstmt.executeQuery();
			
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
			}
			
		} catch (Exception e){
			
		}
		return project;
	}

}
