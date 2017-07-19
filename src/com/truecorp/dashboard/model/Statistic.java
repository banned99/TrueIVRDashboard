package com.truecorp.dashboard.model;

public class Statistic {
	private int total_projects;
	private int total_in_year;
	private int total_project_opened;
	private int total_project_finished;
	private int total_project_cancelled;
	private int total_project_on_time;
	private int total_project_late;
	private int status_dev;
	private int status_init;
	private int status_req;
	private int status_pending;

	public int getTotal_projects() {
		return total_projects;
	}

	public void setTotal_projects(int total_projects) {
		this.total_projects = total_projects;
	}

	public int getTotal_in_year() {
		return total_in_year;
	}

	public void setTotal_in_year(int total_in_year) {
		this.total_in_year = total_in_year;
	}

	public int getTotal_project_opened() {
		return total_project_opened;
	}

	public void setTotal_project_opened(int total_project_opened) {
		this.total_project_opened = total_project_opened;
	}

	public int getTotal_project_finished() {
		return total_project_finished;
	}

	public void setTotal_project_finished(int total_project_finished) {
		this.total_project_finished = total_project_finished;
	}

	public int getTotal_project_cancelled() {
		return total_project_cancelled;
	}

	public void setTotal_project_cancelled(int total_project_cancelled) {
		this.total_project_cancelled = total_project_cancelled;
	}

	public int getTotal_project_on_time() {
		return total_project_on_time;
	}

	public void setTotal_project_on_time(int total_project_on_time) {
		this.total_project_on_time = total_project_on_time;
	}

	public int getTotal_project_late() {
		return total_project_late;
	}

	public void setTotal_project_late(int total_project_late) {
		this.total_project_late = total_project_late;
	}
	
	public int getStatus_dev() {
		return status_dev;
	}

	public void setStatus_dev(int status_dev) {
		this.status_dev = status_dev;
	}
	public int getStatus_init() {
		return status_init;
	}

	public void setStatus_init(int status_init) {
		this.status_init = status_init;
	}

	public int getStatus_req() {
		return status_req;
	}

	public void setStatus_req(int status_req) {
		this.status_req = status_req;
	}

	public int getStatus_pending() {
		return status_pending;
	}

	public void setStatus_pending(int status_pending) {
		this.status_pending = status_pending;
	}
}
