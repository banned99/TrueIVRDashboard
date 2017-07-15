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
			var prio = value.projectPriority;
			var target = new Date(value.projectTargetDate);
			var display = target.getDate() + "/" + (target.getMonth()+1) + "/" + target.getYear();
			var days_left = target.getDate() - new Date().getDate();
			if(prio === 'High') prio = 5;
			else if(prio === 'Mid') prio = 3;
			else if(prio === 'Low') prio = 1;
			if(days_left <= prio)
				$("#mostprio").append(
					`<tr>
						<td>` + value.projectId + `</td>
						<td>` + value.projectName + `</td>
						<td>` + value.projectOwner + `</td>
						<td>` + value.projectRequester + `</td>
						<td>` + value.projectStatus + `</td>
						<td>` + value.projectAccessChannel + `</td>
						<td>` + value.projectPriority + `</td>
						<td>` + display + `</td>
						<td>` + days_left + `</td>
					</tr>`
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
					`<tr>
						<td>` + value.projectId + `</td>
						<td>` +  value.projectName + `</td>
						<td>` +  value.projectOwner + `</td>
						<td>` +  value.projectRequester + `</td>
						<td>` +  value.projectStatus + `</td>
						<td>` +  value.projectAccessChannel + `</td>
						<td>` +  value.projectPriority + `</td>
						<td>` +  value.projectRequestDate + `</td>
						<td>` +  value.projectRequestSubmitDate + `</td>
						<td>` +  value.projectTargetDate + `</td>
						<td> File </td>
					</tr>`
			); 
		});
	});
	
	$.ajax({
		type : "GET",
		url : "HomeServlet",
		contentType : "application/json",
		data : {
			action : "viewStatistics",
			year1: $("#year1").val(),
			year2: $("#year2").val()
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

