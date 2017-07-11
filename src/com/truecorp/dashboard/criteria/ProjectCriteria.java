package com.truecorp.dashboard.criteria;

public class ProjectCriteria {

	private String projectId;
	private String projectName;
	private String projectStatus;
	private String projectAccessChannel;
	private String projectPriority;
	private String projectStartDate;
	private String projectTargetDate;
	private String projectLaunchDate;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId2) {
		this.projectId = projectId2;
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
	
	public void setProjectStartDate(String projectStartDate){
		this.projectStartDate = projectStartDate;
	}
	
	public String getProjectStartDate() {
		return projectStartDate;
	}
	
}
