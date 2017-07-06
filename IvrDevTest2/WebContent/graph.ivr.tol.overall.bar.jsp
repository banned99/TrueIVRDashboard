<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="truecorp.ivr.tol.model.FusionCharts" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IVR TOL OVERALL BAR</title>
<script type="text/javascript" src="scripts/fusioncharts.js"></script>
<script type="text/javascript" src="scripts/themes/fusioncharts.theme.overall.bar.js"></script>
</head>
<body>
	<div id="chartoverallbar"></div>

<%

	FusionCharts bar3DChartOverall= new FusionCharts(
			"mscombi2d",// chartType
	        "chart_overall_bar",// chartId
	        "600", //   chartWidth
	        "400",//    chartHeight
	        "chartoverallbar",//  chartContainer
	        "json",//   dataFormat
	        "{\"chart\": {\"caption\": \"Actual Revenues, Targeted Revenues & Profits\","+
	        "\"subcaption\": \"Last year\",\"xaxisname\": \"Month\","+
	        "\"yaxisname\": \"Amount (In USD)\",\"numberprefix\": \"$\","+
	        "\"theme\": \"overallbar\"},\"categories\": [{\"category\": [{\"label\": \"Jan\"},"+
	        " {\"label\": \"Feb\"}, {\"label\": \"Mar\"}, {\"label\": \"Apr\"},"+
	        " {\"label\": \"May\"}, {\"label\": \"Jun\"}, {\"label\": \"Jul\"},"+
	        " {\"label\": \"Aug\"}, {\"label\": \"Sep\"}, {\"label\": \"Oct\"},"+
	        " {\"label\": \"Nov\"}, {\"label\": \"Dec\"}]}],"+
	        "\"dataset\": [{\"seriesname\": \"Actual Revenue\","+
	        "\"data\": [{\"value\": \"16000\"}, {\"value\": \"20000\"},"+
	        " {\"value\": \"18000\"}, {\"value\": \"19000\"}, {\"value\": \"15000\"},"+
	        " {\"value\": \"21000\"}, {\"value\": \"16000\"}, {\"value\": \"20000\"},"+
	        " {\"value\": \"17000\"}, {\"value\": \"25000\"}, {\"value\": \"19000\"},"+
	        " {\"value\": \"23000\"}]}, {\"seriesname\": \"Projected Revenue\","+
	        "\"renderas\": \"line\",\"showvalues\": \"0\",\"data\": [{\"value\": \"15000\"},"+
	        " {\"value\": \"16000\"}, {\"value\": \"17000\"}, {\"value\": \"18000\"},"+
	        " {\"value\": \"19000\"}, {\"value\": \"19000\"}, {\"value\": \"19000\"},"+
	        " {\"value\": \"19000\"}, {\"value\": \"20000\"}, {\"value\": \"21000\"},"+
	        " {\"value\": \"22000\"}, {\"value\": \"23000\"}]},"+
	        " {\"seriesname\": \"Profit\",\"renderas\": \"area\",\"showvalues\": \"0\","+
	        "\"data\": [{\"value\": \"4000\"}, {\"value\": \"5000\"},"+
	        " {\"value\": \"3000\"}, {\"value\": \"4000\"}, {\"value\": \"1000\"},"+
	        " {\"value\": \"7000\"}, {\"value\": \"1000\"}, {\"value\": \"4000\"},"+
	        " {\"value\": \"1000\"}, {\"value\": \"8000\"}, {\"value\": \"2000\"},"+
	        " {\"value\": \"7000\"}]}]}"
	    );
%>
<%=bar3DChartOverall.render()%>
	
</body>
</html>