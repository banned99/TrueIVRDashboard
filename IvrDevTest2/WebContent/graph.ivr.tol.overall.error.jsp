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
<div id="chartoverallerror"></div>
	<%
	FusionCharts pie3DChartOverallError= new FusionCharts(
		    "pie3d",// chartType
		    "chart_overall_error",// chartId
		    "230", //   chartWidth
		    "150",//    chartHeight
		    "chartoverallerror",//  chartContainer
		    "json",//   dataFormat
		    "{\"chart\": {\"showlabels\": \"0\",\"showlegend\": \"1\","+
		    "\"startingangle\": \"120\","+
			"\"enablemultislicing\": \"0\",\"slicingdistance\": \"15\","+
		    "\"showpercentvalues\": \"1\",\"showpercentintooltip\": \"0\","+
		    "\"plottooltext\": \"$label Total : $datavalue\","+
		    "\"theme\": \"zune\"},\"data\": [{\"label\": \"error1\","+
		    "\"value\": \"300000\"}, {\"label\": \"error2\","+
		    "\"value\": \"400000\"}, {\"label\": \"error3\","+
		    "\"value\": \"500000\"}]}"
		);
	%>
	
	<%=pie3DChartOverallError.render()%>
</body>
</html>