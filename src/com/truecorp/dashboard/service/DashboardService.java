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
	public List<Project> projectSearch(ProjectCriteria crit, int pageNo, int perPage) throws SQLException{
		return DashboardDao.projectSearch(crit, pageNo, perPage);
	}
	
	public List<Project> get5RecentProject() throws SQLException {
		return DashboardDao.get5RecentProject();
	}

	public int getTotalProject() throws SQLException {
		return DashboardDao.getTotalProject();
	}

	public int getTotalProjectYear() throws SQLException {
		return DashboardDao.getTotalProjectYear();
	}

	public int getTotalProjectMonth() throws SQLException{
		return DashboardDao.getTotalProjectMonth();
	}

	public int getTotalOpeningProject() throws SQLException {
		return DashboardDao.getTotalProjectOpening();
	}

	public int getTotalFinishedProject() throws SQLException {
		return DashboardDao.getTotalProjectFinished();
	}

	public int getTotalCancelled() throws SQLException {
		return DashboardDao.getTotalProjectCancelled();
	}

	public int getTotalOnTimeProject() throws SQLException {
		return DashboardDao.getTotalProjectOnTime();
	}

	public int getTotalLateProject() throws SQLException {
		return DashboardDao.getTotalProjectLate();
	}
}
