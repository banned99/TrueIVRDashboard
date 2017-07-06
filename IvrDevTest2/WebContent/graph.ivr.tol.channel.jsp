<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="truecorp.ivr.tol.model.FusionCharts" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IVR TOL CHANNEL</title>
<script type="text/javascript" src="scripts/fusioncharts.js"></script>
<script type="text/javascript" src="scripts/themes/fusioncharts.theme.channel.js"></script>
</head>
<body>
<div id="chartchannel"></div>
	<%
	FusionCharts pie3DChartChannel= new FusionCharts(
		    "pie3d",// chartType
		    "chart_channel",// chartId
		    "333", //   chartWidth
		    "244",//    chartHeight
		    "chartchannel",//  chartContainer
		    "json",//   dataFormat
		    "{\"chart\": {\"showlabels\": \"0\",\"showlegend\": \"1\","+
		    "\"startingangle\": \"120\","+
			"\"enablemultislicing\": \"0\",\"slicingdistance\": \"15\","+
		    "\"showpercentvalues\": \"1\",\"showpercentintooltip\": \"0\","+
		    "\"plottooltext\": \"$label Total : $datavalue\","+
		    "\"theme\": \"channel\"},\"data\": [{\"label\": \"MARI\","+
		    "\"value\": \"1250400\"}, {\"label\": \"DTMF_1NO\","+
		    "\"value\": \"1463300\"}, {\"label\": \"DTMF\","+
		    "\"value\": \"1050700\"}]}"
		);
	%>
	
	<%=pie3DChartChannel.render()%>
</body>
</html>