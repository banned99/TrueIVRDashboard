package com.truecorp.dashboard.service;

import java.sql.SQLException;

import com.truecorp.dashboard.dao.ProjectDao;
import com.truecorp.dashboard.model.Project;

public class ProjectService {
	public Project getProjectById (String project_id) throws SQLException {
		return ProjectDao.getProjectById(project_id);
	}

	public String downloadFile(String project_id) throws SQLException {
		return ProjectDao.downloadFile(project_id);
	}
}
