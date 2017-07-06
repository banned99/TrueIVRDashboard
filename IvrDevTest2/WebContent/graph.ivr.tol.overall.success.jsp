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
<div id="chartoverallsuccess"></div>
	<%
	FusionCharts pie3DChartOverallSuccess= new FusionCharts(
		    "pie3d",// chartType
		    "chart_overall_success",// chartId
		    "240", //   chartWidth
		    "165",//    chartHeight
		    "chartoverallsuccess",//  chartContainer
		    "json",//   dataFormat
		    "{\"chart\": {\"showlabels\": \"0\",\"showlegend\": \"1\","+
		    "\"startingangle\": \"120\","+
			"\"enablemultislicing\": \"0\",\"slicingdistance\": \"15\","+
		    "\"showpercentvalues\": \"1\",\"showpercentintooltip\": \"0\","+
		    "\"plottooltext\": \"$label Total : $datavalue\","+
		    "\"theme\": \"zune\"},\"data\": [{\"label\": \"success1\","+
		    "\"value\": \"500000\"}, {\"label\": \"success2\","+
		    "\"value\": \"200000\"}, {\"label\": \"success3\","+
		    "\"value\": \"150000\"}, {\"label\": \"success4\","+
		    "\"value\": \"150500\"}]}"
		);
	%>
	
	<%=pie3DChartOverallSuccess.render()%>
</body>
</html>