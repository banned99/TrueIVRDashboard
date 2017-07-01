package com.truecorp.dashboard.service;

import java.sql.SQLException;
import java.util.List;

import com.truecorp.dashboard.criteria.ProjectCriteria;
import com.truecorp.dashboard.dao.DashboardDao;
import com.truecorp.dashboard.model.Project;

public class DashboardService {
	public List<Project> getAllProj(int perPage, int page) throws SQLException {
		return DashboardDao.getRecentProject(perPage, page);
	}
	
	public List<Project> getPriorityProj() throws SQLException {
		return DashboardDao.getPriorityProject();
	}
	
	//Developing in process & NOT TESTED
	public List<Project> projectSearch(ProjectCriteria crit) throws SQLException{
		return DashboardDao.projectSearch(crit);
	}
	
	//Developing in process & NOT TESTED
	public List<Project> projectSearch(ProjectCriteria crit, int page) throws SQLException{
		return DashboardDao.projectSearch(crit, page);
	}
	
	public List<Project> get5RecentProject() throws SQLException {
		return DashboardDao.get5RecentProject();
	}
}
