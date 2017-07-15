package com.truecorp.dashboard.model;

import java.sql.Blob;

public class Project {

	private int projectId;
	private String projectName;
	private String projectOwner;
	private String projectStatus;
	private String projectPriority;
	private String projectRequestDate;
	private String projectRequestSubmitDate;
	private String projectTargetDate;
	private String projectReason;
	private String projectLaunchDate;
	private String projectUcrNo;
	private String projectUcrDate;
	private String projectRequester;
	private String projectAccessChannel;
	private String projectManday;
	private String projectDetails;
	private String projectLastComment;
	private String projectUserlan;
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

	public String getProjectOwner() {
		return projectOwner;
	}

	public void setProjectOwner(String projectOwner) {
		this.projectOwner = projectOwner;
	}

	public String getProjectRequestDate() {
		return projectRequestDate;
	}

	public void setProjectRequestDate(String projectRequestDate) {
		this.projectRequestDate = projectRequestDate;
	}

	public String getProjectRequestSubmitDate() {
		return projectRequestSubmitDate;
	}

	public void setProjectRequestSubmitDate(String projectRequestSubmitDate) {
		this.projectRequestSubmitDate = projectRequestSubmitDate;
	}

	public String getProjectReason() {
		return projectReason;
	}

	public void setProjectReason(String projectReason) {
		this.projectReason = projectReason;
	}

	public String getProjectUcrNo() {
		return projectUcrNo;
	}

	public void setProjectUcrNo(String projectUcrNo) {
		this.projectUcrNo = projectUcrNo;
	}

	public String getProjectUcrDate() {
		return projectUcrDate;
	}

	public void setProjectUcrDate(String projectUcrDate) {
		this.projectUcrDate = projectUcrDate;
	}

	public String getProjectRequester() {
		return projectRequester;
	}

	public void setProjectRequester(String projectRequester) {
		this.projectRequester = projectRequester;
	}

	public String getProjectManday() {
		return projectManday;
	}

	public void setProjectManday(String projectManday) {
		this.projectManday = projectManday;
	}

	public String getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(String projectDetails) {
		this.projectDetails = projectDetails;
	}

	public String getProjectLastComment() {
		return projectLastComment;
	}

	public void setProjectLastComment(String projectLastComment) {
		this.projectLastComment = projectLastComment;
	}

	public String getProjectUserlan() {
		return projectUserlan;
	}

	public void setProjectUserlan(String projectUserlan) {
		this.projectUserlan = projectUserlan;
	}

}
