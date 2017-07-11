/**
 * 
 */
$(document).ready(function() {
	$.ajax({
		type : "GET",
		url : "HomeServlet",
		contentType : "application/json",
		data : {
			action : "viewMostPrioProject"
		}
	}).then(function (data, status, jqxhr){
		$.each(data, function(index, value){
			$("#mostprio").append(
					"<tr>" +
					"	<td></td>" +
					"	<td>" + value.projectId + "</td>" +
					"	<td>" + value.projectName + "</td>" +
					"	<td>" + value.projectStatus + "</td>" +
					"	<td>" + value.projectAccessChannel + "</td>" +
					"	<td>" + value.projectPriority + "</td>" +
					"	<td>" + value.projectStartDate + "</td>" +
					"	<td>" + value.projectTargetDate + "</td>" +
					"	<td>" + value.projectLaunchDate + "</td>" +
					"	<td> File </td>" +
					"	<td></td>" +
					"</tr>"
			); 
		});
	});
	
	$.ajax({
		type : "GET",
		url : "HomeServlet",
		contentType : "application/json",
		data : {
			action : "viewMostRecentProject"
		}
	}).then(function (data, status, jxqhr){
		$.each(data, function(index, value){
			$("#mostrecent").append(
					"<tr>" +
					"	<td></td>" +
					"	<td>" + value.projectId + "</td>" +
					"	<td>" + value.projectName + "</td>" +
					"	<td>" + value.projectStatus + "</td>" +
					"	<td>" + value.projectAccessChannel + "</td>" +
					"	<td>" + value.projectPriority + "</td>" +
					"	<td>" + value.projectStartDate + "</td>" +
					"	<td>" + value.projectTargetDate + "</td>" +
					"	<td>" + value.projectLaunchDate + "</td>" +
					"	<td> File </td>" +
					"	<td></td>" +
					"</tr>"
			); 
		});
	});
	
	$.ajax({
		type : "GET",
		url : "HomeServlet",
		contentType : "application/json",
		data : {
			action : "viewStatistics"
		}
	}).then(function (data, status, jqxhr){
		$('#total_projects').append(data.total_projects);
		$('#total_in_year').append(data.total_in_year);
		$('#total_in_month').append(data.total_in_month);
		$('#total_project_opened').append(data.total_project_opened);
		$('#total_project_finished').append(data.total_project_finished);
		$('#total_project_cancelled').append(data.total_project_cancelled);
		$('#total_project_on_time').append(data.total_project_on_time);
		$('#total_project_late').append(data.total_project_late);
	});
});

