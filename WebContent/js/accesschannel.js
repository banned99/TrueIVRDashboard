$(document).ready(function () {
	
	var element = document.getElementById("perPage");
	var perPage = element.options[element.selectedIndex].value;
	
	var pageElement = document.getElementById("curPage");
	var pageNo = 1;
	
	var pageMax = 1;
	var pageMin = 1;
	var channelCount;
	
	$.ajax({
		type : "GET",
		url : "AccessChannelServlet",
		contentType : "application/json",
		data : {
			action : "getTotal"
		}
	}).then(function (data, status, jqxhr){
		channelCount = JSON.parse(data).total_ac;
		$.ajax({
			type: "GET",
			url: "AccessChannelServlet",
			contentType: "application/json",
			data: {
				perPage: perPage,
				pageNo: pageNo,
				action: "viewAll"
			}
		}).then(function (data, status, jqxhr) {
			$.each(data, function( index, value ){
				$("tbody").append(
						"<tr>" +
						"	<td>" + value.acNo + "</td>" +
						"	<td>" + value.acName + "</td>" +
						"	<td>" + value.productNo + "</td>" +
						"	<td>" + value.productName + "</td>" +
						"	<td>" + value.display +"</td>" +
						"</tr>"
				);
			});
			pageMax = Math.ceil(channelCount / perPage);
			checkPageMaxMin();
			$('#curPage').val(1+"/"+pageMax);
		})
	})
	
	$('#perPage').change(function() {
		pageNo = 1;
		perPage = element.options[element.selectedIndex].value;
		$.ajax({
			type: "GET",
			url: "AccessChannelServlet",
			contentType: "application/json",
			data: {
				perPage: perPage,
				pageNo: pageNo,
				action: "viewAll"
			}
		}).then(function(data, status, jqxhr) {
			$('tbody').empty();
			$.each(data, function( index, value ){
				$("tbody").append(
						"<tr>" +
						"	<td>" + value.acNo + "</td>" +
						"	<td>" + value.acName + "</td>" +
						"	<td>" + value.productNo + "</td>" +
						"	<td>" + value.productName + "</td>" +
						"	<td>" + value.display +"</td>" +
						"</tr>"
						);
			});
			pageMax = Math.ceil(channelCount / perPage);
			checkPageMaxMin();
			$('#curPage').val(1+"/"+pageMax);
		});
	});
	
	$('#nextPage').click(function () { 
		pageNo = pageNo + 1;
		$.ajax({
			type: "GET",
			url: "AccessChannelServlet",
			contentType: "application/json",
			data: {
				perPage: perPage,
				pageNo: pageNo,
				action: "viewAll"
			}
		}).then(function(data, status, jqxhr) {
			$('tbody').empty();
			$.each(data, function( index, value ){
				$("tbody").append(
						"<tr>" +
						"	<td>" + value.acNo + "</td>" +
						"	<td>" + value.acName + "</td>" +
						"	<td>" + value.productNo + "</td>" +
						"	<td>" + value.productName + "</td>" +
						"	<td>" + value.display +"</td>" +
						"</tr>"
						);
			});
			checkPageMaxMin();
			$('#curPage').val(pageNo+"/"+pageMax);
		});
	});
	
	$('#prevPage').click(function () { 
		pageNo = pageNo - 1;
		$.ajax({
			type: "GET",
			url: "AccessChannelServlet",
			contentType: "application/json",
			data: {
				perPage: perPage,
				pageNo: pageNo,
				action: "viewAll"
			}
		}).then(function(data, status, jqxhr) {
			$('tbody').empty();
			$.each(data, function( index, value ){
				$("tbody").append(
						"<tr>" +
						"	<td>" + value.acNo + "</td>" +
						"	<td>" + value.acName + "</td>" +
						"	<td>" + value.productNo + "</td>" +
						"	<td>" + value.productName + "</td>" +
						"	<td>" + value.display +"</td>" +
						"</tr>"
						);
			});
			checkPageMaxMin();
			$('#curPage').val(pageNo+"/"+pageMax);
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
		$.ajax({
			type: "GET",
			url: "AccessChannelServlet",
			contentType: "application/json",
			data: {
				action: "acSearch",
				perPage : perPage,
				pageNo: pageNo,
				acId: $('#searchAcId').val(),
				acName: $('#searchAcName').val(),
				acProductNo: $('#searchProductNo').val(),
				acProductName: $('#searchProductName').val(),
				acDisplay: $('#searchDisplay').val()
			}
		}).then(function(data, status, jqxhr){
			$('tbody').empty();
			$.each(data, function( index, value ){
				$("tbody").append(
						"<tr>" +
						"	<td>" + value.acNo + "</td>" +
						"	<td>" + value.acName + "</td>" +
						"	<td>" + value.productNo + "</td>" +
						"	<td>" + value.productName + "</td>" +
						"	<td>" + value.display +"</td>" +
						"</tr>"
						);
			});
			checkPageMaxMin();
			$('#curPage').val(pageNo+"/"+pageMax);
		})
	});
	
})