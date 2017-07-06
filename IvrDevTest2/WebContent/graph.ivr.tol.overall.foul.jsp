<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="truecorp.ivr.tol.model.FusionCharts" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IVR TOL OVERALL</title>
<script type="text/javascript" src="scripts/fusioncharts.js"></script>
<script type="text/javascript" src="scripts/themes/fusioncharts.theme.zune.js"></script>
</head>
<body>
<div id="chartoverallfoul"></div>
	<%
	FusionCharts pie3DChartOverallFoul= new FusionCharts(
		    "pie3d",// chartType
		    "chart_overall_foul",// chartId
		    "230", //   chartWidth
		    "150",//    chartHeight
		    "chartoverallfoul",//  chartContainer
		    "json",//   dataFormat
		    "{\"chart\": {\"showlabels\": \"0\",\"showlegend\": \"1\","+
		    "\"startingangle\": \"120\","+
			"\"enablemultislicing\": \"0\",\"slicingdistance\": \"15\","+
		    "\"showpercentvalues\": \"1\",\"showpercentintooltip\": \"0\","+
		    "\"plottooltext\": \"$label Total : $datavalue\","+
		    "\"theme\": \"zune\"},\"data\": [{\"label\": \"foul1\","+
		    "\"value\": \"600000\"}, {\"label\": \"foul2\","+
		    "\"value\": \"400000\"}, {\"label\": \"foul3\","+
		    "\"value\": \"500000\"}]}"
		);
	%>
	
	<%=pie3DChartOverallFoul.render()%>
</body>
</html>