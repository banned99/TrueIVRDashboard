/**
 * 
 */
$(document).ready(function() {
	var element = document.getElementById("perPage");
	var perPage = element.options[element.selectedIndex].value;
	
	var pageElement = document.getElementById("curPage");
	var pageNo = 1;
	
	var pageMax = 1;
	var pageMin = 1;
	var projectCount;
	
	$.ajax({
		type : "GET",
		url : "HomeServlet",
		contentType : "application/json",
		data : {
			action : "getTotalProjects"
			}
		}).then(function(data, status, jqxhr){
			projectCount = JSON.parse(data).total_projects;
			$.ajax({
				type : "GET",
				url : "ProjectServlet",
				contentType : "application/json",
				data : {
					action : "viewAllProject",
					perPage : perPage,
					pageNo : pageNo
				}
			}).then(function(data, status, jqxhr){
				$.each(data, function( index, value ){
					$("#data").append(
							"<tr>" +
							"	<td>" + (index+1) + "</td>" +
							"	<td><a href='ProjectServlet?action=viewProjectById&project_id="+value.projectId+"'>" + value.projectName + "</td>" +
							"	<td>" + value.projectOwner + "</td>" +
							"	<td>" + value.projectRequester + "</td>" +
							"	<td>" + value.projectStatus + "</td>" +
							"	<td>" + value.projectPriority + "</td>" +
							"	<td>" + value.projectAccessChannel + "</td>" +
							"	<td>" + value.projectRequestSubmitDate + "</td>"+
							"	<td>" + value.projectTargetDate + "</td>" +
							"</tr>"
					);
				});
				pageMax = Math.ceil(projectCount / perPage);
				checkPageMaxMin();
				$('#curPage').val(pageNo);
				$('#maxPage').text("/ "+pageMax);
			});
	});
	
	$('#perPage').change(function() {
		pageNo = 1;
		perPage = element.options[element.selectedIndex].value;
		$.ajax({
			type: "GET",
			url: "ProjectServlet",
			contentType: "application/json",
			data: {
				action: "viewAllProject",
				perPage : perPage,
				pageNo: pageNo
			}
		}).then(function(data, status, jqxhr) {
			$('#data').empty();
			$.each(data, function( index, value ){
				$("#data").append(
						"<tr>" +
						"	<td>" + (index+1) + "</td>" +
						"	<td><a href='ProjectServlet?action=viewProjectById&project_id="+value.projectId+"'>" + value.projectName + "</td>" +
						"	<td>" + value.projectOwner + "</td>" +
						"	<td>" + value.projectRequester + "</td>" +
						"	<td>" + value.projectStatus + "</td>" +
						"	<td>" + value.projectPriority + "</td>" +
						"	<td>" + value.projectAccessChannel + "</td>" +
						"	<td>" + value.projectRequestSubmitDate + "</td>"+
						"	<td>" + value.projectTargetDate + "</td>" +
						"</tr>"); 
			});
			pageMax = Math.ceil(projectCount / perPage);
			checkPageMaxMin();
			$('#curPage').val(pageNo);
			$('#maxPage').text("/ "+pageMax);
		});
	});
	
	$('#nextPage').click(function () { 
		pageNo = pageNo + 1;
		$.ajax({
			type: "GET",
			url: "ProjectServlet",
			contentType: "application/json",
			data: {
				action: "viewAllProject",
				perPage : perPage,
				pageNo: pageNo
			}
		}).then(function(data, status, jqxhr) {
			$('#data').empty();
			$.each(data, function( index, value ){
				$("#data").append(
						"<tr>" +
						"	<td>" + (index+1) + "</td>" +
						"	<td><a href='ProjectServlet?action=viewProjectById&project_id="+value.projectId+"'>" + value.projectName + "</td>" +
						"	<td>" + value.projectOwner + "</td>" +
						"	<td>" + value.projectRequester + "</td>" +
						"	<td>" + value.projectStatus + "</td>" +
						"	<td>" + value.projectPriority + "</td>" +
						"	<td>" + value.projectAccessChannel + "</td>" +
						"	<td>" + value.projectRequestSubmitDate + "</td>"+
						"	<td>" + value.projectTargetDate + "</td>" +
						"</tr>");
			});
			checkPageMaxMin();
			$('#curPage').val(pageNo);
			$('#maxPage').text("/ "+pageMax);
		});
	});
	
	$('#prevPage').click(function () { 
		pageNo = pageNo - 1;
		$.ajax({
			type: "GET",
			url: "ProjectServlet",
			contentType: "application/json",
			data: {
				action: "viewAllProject",
				perPage : perPage,
				pageNo: pageNo
			}
		}).then(function(data, status, jqxhr) {
			$('#data').empty();
			$.each(data, function( index, value ){
				$("#data").append(
						"<tr>" +
						"	<td>" + (index+1) + "</td>" +
						"	<td><a href='ProjectServlet?action=viewProjectById&project_id="+value.projectId+"'>" + value.projectName + "</td>" +
						"	<td>" + value.projectOwner + "</td>" +
						"	<td>" + value.projectRequester + "</td>" +
						"	<td>" + value.projectStatus + "</td>" +
						"	<td>" + value.projectPriority + "</td>" +
						"	<td>" + value.projectAccessChannel + "</td>" +
						"	<td>" + value.projectRequestSubmitDate + "</td>"+
						"	<td>" + value.projectTargetDate + "</td>" +
						"</tr>");
			});
			checkPageMaxMin();
			$('#curPage').val(pageNo);
			$('#maxPage').text("/ "+pageMax);
		});
	});
	
	function checkPageMaxMin(){
		if (pageNo >= pageMax && pageNo <= pageMin) {
			$('#nextPage').prop("disabled", true);
			$('#prevPage').prop("disabled", true);
		} else if (pageNo >= pageMax && pageNo > pageMin) {
			$('#nextPage').prop("disabled", true);
			$('#prevPage').prop("disabled", false);
		} else if (pageNo < pageMax && pageNo <= pageMin) {
			$('#nextPage').prop("disabled", false);
			$('#prevPage').prop("disabled", true);
		} else if (pageNo < pageMax && pageNo > pageMin) {
			$('#nextPage').prop("disabled", false);
			$('#prevPage').prop("disabled", false);
		}
	}
	
	$('#searchButt').click(function () {
		var projectId = $('#searchId').val();
		var projectName = $('#searchName').val();
		var projectOwner = $('#searchOwner').val();
		var projectRequester = $('#searchRequester').val();
	 	var projectStatus = $('#searchStatus').val();
		var projectPriority = $('#searchPrio').val();
	 	var projectSubmitDateStart = $('#searchSubmit').val().substr(0,9);
	 	var projectSubmitDateEnd = $('#searchSubmit').val().substr(13,9);
	 	var projectTargetDateStart = $('#searchTarget').val().substr(0,9);
	 	var projectTargetDateEnd = $('#searchTarget').val().substr(13,9);
	 	if (projectId === null && projectName === null && projectOwner === null && projectRequester === null && projectPriority === null && 
	 			projectStatus === null && projectTargetDateStart === '01/01/2017' && projectSubmitDateStart === '01/01/2017' && 
	 			projectTargetDateEnd === '01/01/2017' && projectSubmitDateEnd === '01/01/2017')
	 		var action = "viewAllProject";
	 	else var action = "projectSearch";
		$.ajax({
			type: "GET",
			url: "ProjectServlet",
			contentType: "application/json",
			data: {
				action: action,
				perPage : perPage,
				pageNo: pageNo,
				projectId: projectId,
				projectName: projectName,
				projectOwner: projectOwner,
				projectRequester: projectRequester,
				projectPriority: projectPriority,
			 	projectStatus: projectStatus,
			 	projectTargetDateStart: projectTargetDateStart,
			 	projectTargetDateEnd: projectTargetDateEnd,
			 	projectSubmitDateStart: projectSubmitDateStart,
			 	projectSubmitDateEnd: projectSubmitDateEnd
			}
		}).then(function(data, status, jqxhr){
			$('#data').empty();
			$.each(data, function( index, value ){
				$("#data").append(
						"<tr>" +
						"	<td>" + (index+1) + "</td>" +
						"	<td><a href='ProjectServlet?action=viewProjectById&project_id="+value.projectId+"'>" + value.projectName + "</td>" +
						"	<td>" + value.projectOwner + "</td>" +
						"	<td>" + value.projectRequester + "</td>" +
						"	<td>" + value.projectStatus + "</td>" +
						"	<td>" + value.projectPriority + "</td>" +
						"	<td>" + value.projectAccessChannel + "</td>" +
						"	<td>" + value.projectRequestSubmitDate + "</td>"+
						"	<td>" + value.projectTargetDate + "</td>" +
						"</tr>");
			});
			pageMax=1;
			checkPageMaxMin();
			$('#curPage').val(pageNo);
			$('#maxPage').text("/ "+pageMax);
		});
	});
});