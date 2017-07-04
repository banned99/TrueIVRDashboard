/**
 * 
 */
$(document).ready(function() {
	$.ajax({
		"type" : "GET",
		"url" : "DashboardServlet",
		"contentType" : "application/json",
		"data" : {
//			"action" : "viewMostRecentProject"
			
			"action" : "viewAllProject",
			"perPage" : "20",
			"pageNo" : "1"
			
//			"action" : "viewMostPrioProject"
			
		}
	}).then(function(data, status, jqxhr){
		$.each(data, function( index, value ){
			$("tbody").append(
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
					"</tr>"); // ADD FILE LATER
		});
	});
});