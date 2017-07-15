package com.truecorp.dashboard.service;

import java.sql.SQLException;
import java.util.List;

import com.truecorp.dashboard.criteria.ProjectCriteria;
import com.truecorp.dashboard.dao.DashboardDao;
import com.truecorp.dashboard.model.Project;
import com.truecorp.dashboard.model.Statistic;

public class DashboardService {
	public List<Project> getAllProj(int perPage, int page) throws SQLException {
		return DashboardDao.getRecentProject(perPage, page);
	}
	
	public List<Project> getPriorityProj() throws SQLException {
		return DashboardDao.getPriorityProject();
	}
	
	public List<Project> projectSearch(ProjectCriteria crit, int pageNo, int perPage) throws SQLException{
		return DashboardDao.projectSearch(crit, pageNo, perPage);
	}
	
	public List<Project> get5RecentProject() throws SQLException {
		return DashboardDao.get5RecentProject();
	}

	public Statistic getStatistic() throws SQLException {
		return DashboardDao.getStatistics();
	}

	public Statistic getStatistic(String year) throws SQLException {
		return DashboardDao.getStatistics(year);
	}
	
	public Statistic getStatistic(String year1, String year2) throws SQLException {
		return DashboardDao.getStatistics(year1, year2);
	}
}
