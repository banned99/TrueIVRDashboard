package com.truecorp.dashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.truecorp.dashboard.model.Project;
import com.truecorp.dashboard.util.DBUtil;

public class ProjectDao {

	public static Project getProjectById(String project_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM project p "
				+ "LEFT OUTER JOIN project_affect_acchannel pas on p.project_id=pas.project_id "
				+ "LEFT OUTER JOIN userlan ul on p.project_userlan = ul.userlan "
				+ "LEFT OUTER JOIN accesschannel ac on pas.ac_no = ac.ac_no where p.project_id=?;";
		Project project = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, project_id);
			rs = pstmt.executeQuery();
			
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
				} else {
					ac.add(rs.getString("ac_name"));
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return project;
	}

	public static String downloadFile(String project_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT project_file FROM Project WHERE project_id=? LIMIT 1";
		String file = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, project_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				file = rs.getString("project_file");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		
		return file;
	}

}
