package com.truecorp.dashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.truecorp.dashboard.criteria.ACCriteria;
import com.truecorp.dashboard.model.AccessChannel;
import com.truecorp.dashboard.util.DBUtil;

public class AccessChannelDao {
	public static List<AccessChannel> getAllAC(int perPage, int page) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int startRow = ((page - 1) * perPage);
		int endRow = (page * perPage);

		String sql = "SELECT k.* (SELECT a.* FROM AccessChannel a LIMIT ?,?) k LIMIT ?";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, perPage);
			rs = pstmt.executeQuery();

			List<AccessChannel> acList = new ArrayList<AccessChannel>();
			AccessChannel ac = null;

			while (rs.next()) {
				ac = new AccessChannel();
				ac.setAcNo(rs.getString("ac_id"));
				ac.setAcName(rs.getString("ac_name"));
				ac.setProductNo(rs.getString("ac_product_no"));
				ac.setProductName(rs.getString("ac_product_name"));
				ac.setDisplay(rs.getString("ac_display"));
				acList.add(ac);
			}
			return acList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return null;
	}
	
	public static int getTotalAC() throws SQLException{
		int total = 0;
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) as total FROM AccessChannel";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				total = rs.getInt(total);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return total;
	}
	
	public static List<AccessChannel> searchAC(ACCriteria cri, int perPage, int page) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<AccessChannel> acList = new ArrayList<AccessChannel>();
		List<String> criteriaValue = new ArrayList<String>();
		
		int startRow = ((page - 1) * perPage);
		int endRow = (page * perPage);
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT p.* FROM AccessChannel p WHERE 1=1 ");
		
		if (String.valueOf(cri.getAcNo()) != null && String.valueOf(cri.getAcNo()).trim().length() > 0) {
			sql.append(" and ac_id = ?");
			criteriaValue.add(String.valueOf(cri.getAcNo()));
		}

		if (cri.getAcName() != null && cri.getAcName().trim().length() > 0) {
			sql.append(" and lower(ac_name) like ? ");
			criteriaValue.add("%" + cri.getAcName().toLowerCase() + "%");
		}

		if (cri.getAcProductNo() != null && cri.getAcProductNo().trim().length() > 0) {
			sql.append(" and project_status = ? ");
			criteriaValue.add(cri.getAcProductNo());
		}

		if (cri.getAcProductName() != null && cri.getAcProductName().trim().length() > 0) {
			sql.append(" and project_priority = ? ");
			criteriaValue.add(cri.getAcProductName());
		}
		
		if (cri.getDisplay() != null && cri.getDisplay().trim().length() > 0) {
			sql.append(" and project_ac = ?");
			criteriaValue.add(cri.getDisplay());
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
			AccessChannel ac = null;

			while (rs.next()) {
				ac = new AccessChannel();
				ac.setAcNo(rs.getString("ac_no"));
				ac.setAcName(rs.getString("ac_name"));
				ac.setProductNo(rs.getString("ac_product_no"));
				ac.setProductName(rs.getString("ac_product_name"));
				ac.setDisplay(rs.getString("project_display"));
				acList.add(ac);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return acList;
	}

}
