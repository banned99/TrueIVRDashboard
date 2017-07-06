<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="truecorp.ivr.tol.model.FusionCharts" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IVR TOL OVERALL</title>
<script type="text/javascript" src="scripts/fusioncharts.js"></script>
<script type="text/javascript" src="scripts/themes/fusioncharts.theme.overall.js"></script>
</head>
<body>
<div id="chartoverall"></div>
	<%
	FusionCharts pie3DChartOverall= new FusionCharts(
		    "pie3d",// chartType
		    "chart_overall",// chartId
		    "333", //   chartWidth
		    "244",//    chartHeight
		    "chartoverall",//  chartContainer
		    "json",//   dataFormat
		    "{\"chart\": {\"showlabels\": \"0\",\"showlegend\": \"1\","+
		    "\"startingangle\": \"120\","+
			"\"enablemultislicing\": \"0\",\"slicingdistance\": \"15\","+
		    "\"showpercentvalues\": \"1\",\"showpercentintooltip\": \"0\","+
		    "\"plottooltext\": \"$label Total : $datavalue\","+
		    "\"theme\": \"overall\"},\"data\": [{\"label\": \"SUCCESS\","+
		    "\"value\": \"1250400\"}, {\"label\": \"ERROR\","+
		    "\"value\": \"1463300\"}, {\"label\": \"WARNING\","+
		    "\"value\": \"1050700\"}]}"
		);
	%>
	
	<%=pie3DChartOverall.render()%>
</body>
</html>