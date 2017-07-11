package com.truecorp.dashboard.service;

import com.truecorp.dashboard.dao.ProjectDao;
import com.truecorp.dashboard.model.Project;

public class ProjectService {
	public Project getProjectById (String project_id) {
		return ProjectDao.getProjectById(project_id);
	}
}
