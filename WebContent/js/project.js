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
			action : "viewStatistics"
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
							"</tr>"
					); // ADD FILE LATER
				});
				pageMax = Math.ceil(projectCount / perPage);
				checkPageMaxMin();
			});
	});
	
	$('#perPage').change(function() {
		pageNo = 1;
		$('#curPage').val(1);
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
			$('tbody').empty();
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
			pageMax = Math.ceil(projectCount / perPage);
			checkPageMaxMin();
		});
	});
	
	$('#nextPage').click(function () { 
		pageNo = pageNo + 1;
		$('#curPage').val(pageNo);
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
			$('tbody').empty();
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
			checkPageMaxMin();
		});
	});
	
	$('#prevPage').click(function () { 
		pageNo = pageNo - 1;
		$('#curPage').val(pageNo);
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
			$('tbody').empty();
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
			checkPageMaxMin();
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
	
	function calculatePage(total){
		projectCount = total;
	}
	
	$('#searchButt').click(function () {
		$.ajax({
			type: "GET",
			url: "ProjectServlet",
			contentType: "application/json",
			data: {
				action: "projectSearch",
				perPage : perPage,
				pageNo: pageNo,
				projectId: $('#searchId').val(),
				projectName: $('#searchName').val(),
				projectStatus: $('#searchStatus').val(),
				projectAC: $('#searchAC').val(),
				projectPriority: $('#searchPrio').val(),
			 	projectStartDate: $('#searchStart').val(),
			 	projectTargetDate: $('#searchTarget').val(),
			 	projectLaunchDate: $('#searchLaunch').val()
			}
		}).then(function(data, status, jqxhr){
			$('tbody').empty();
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
			checkPageMaxMin();
		})
	});
});