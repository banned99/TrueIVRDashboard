package com.truecorp.dashboard.model;

import java.sql.Blob;

public class Project {

	private int projectId;
	private String projectName;
	private String projectStatus;
	private String projectAccessChannel;
	private String projectPriority;
	private String projectStartDate;
	private String projectTargetDate;
	private String projectLaunchDate;
	private Blob projectFile;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int i) {
		this.projectId = i;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public String getProjectAccessChannel() {
		return projectAccessChannel;
	}

	public void setProjectAccessChannel(String projectAccessChannel) {
		this.projectAccessChannel = projectAccessChannel;
	}

	public String getProjectPriority() {
		return projectPriority;
	}

	public void setProjectPriority(String projectPriority) {
		this.projectPriority = projectPriority;
	}

	public String getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public String getProjectTargetDate() {
		return projectTargetDate;
	}

	public void setProjectTargetDate(String projectTargetDate) {
		this.projectTargetDate = projectTargetDate;
	}

	public String getProjectLaunchDate() {
		return projectLaunchDate;
	}

	public void setProjectLaunchDate(String projectLaunchDate) {
		this.projectLaunchDate = projectLaunchDate;
	}

	public Blob getProjectFile() {
		return projectFile;
	}

	public void setProjectFile(Blob projectFile) {
		this.projectFile = projectFile;
	}

}
