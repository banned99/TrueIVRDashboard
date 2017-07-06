<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="truecorp.ivr.tol.model.FusionCharts" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IVR TOL BY</title>
<script type="text/javascript" src="scripts/fusioncharts.js"></script>
<script type="text/javascript" src="scripts/themes/fusioncharts.theme.by.js"></script>
</head>
<body>
<div id="chartby"></div>
	<%
	FusionCharts pie3DChartBy= new FusionCharts(
		    "pie3d",// chartType
		    "chart_by",// chartId
		    "333", //   chartWidth
		    "244",//    chartHeight
		    "chartby",//  chartContainer
		    "json",//   dataFormat
		    "{\"chart\": {\"showlabels\": \"0\",\"showlegend\": \"1\","+
		    "\"startingangle\": \"120\","+
			"\"enablemultislicing\": \"0\",\"slicingdistance\": \"15\","+
		    "\"showpercentvalues\": \"1\",\"showpercentintooltip\": \"0\","+
		    "\"plottooltext\": \"$label Total : $datavalue\","+
		    "\"theme\": \"by\"},\"data\": [{\"label\": \"BY_ANUMBER\","+
		    "\"value\": \"1250400\"}, {\"label\": \"BY_BNUMBER\","+
		    "\"value\": \"1463300\"}, {\"label\": \"BY_AGENT_TRF\","+
		    "\"value\": \"1050700\"}]}"
		);
	%>
	
	<%=pie3DChartBy.render()%>
</body>
</html>