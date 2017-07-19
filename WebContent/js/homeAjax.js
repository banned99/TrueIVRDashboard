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
			var display = target.getDate() + "/" + (target.getMonth()+1) + "/" + (target.getYear() + 1900);
			var days_left = target.getDate() - new Date().getDate();
			if(prio === 'High') prio = 5;
			else if(prio === 'Medium') prio = 3;
			else if(prio === 'Low') prio = 1;
			var acrow = "";
			$.each(value.projectAccessChannel, function(indexx, temps){
				acrow += temps+"\n";
			})
			if(days_left <= prio){
				$("#mostprio").append(
					`<tr>
						<td>` + value.projectId + `</td>
						<td>` + value.projectName + `</td>
						<td>` + value.projectOwner + `</td>
						<td>` + value.projectRequester + `</td>
						<td>` + value.projectStatus + `</td>
						<td>` + acrow + `</td>
						<td>` + value.projectPriority + `</td>
						<td>` + display + `</td>
						<td>` + days_left + `</td>
					</tr>`
				); 
			}
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
			var acrow = "";
			$.each(value.projectAccessChannel, function(indexx, temps){
				acrow += temps+"\n";
			})
			$("#mostrecent").append(
					`<tr>
						<td>` + value.projectId + `</td>
						<td>` +  value.projectName + `</td>
						<td>` +  value.projectOwner + `</td>
						<td>` +  value.projectRequester + `</td>
						<td>` +  value.projectStatus + `</td>
						<td>` +  acrow + `</td>
						<td>` +  value.projectPriority + `</td>
						<td>` +  value.projectRequestDate + `</td>
						<td>` +  value.projectRequestSubmitDate + `</td>
						<td>` +  value.projectTargetDate + `</td>
					</tr>`
			); 
		});
	});
});

