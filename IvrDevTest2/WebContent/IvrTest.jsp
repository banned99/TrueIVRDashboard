<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="truecorp.ivr.tol.model.FusionCharts" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Monitoring API GetProfile</title>
</head>
<body>
	<header>
	<h1 align="center"><font color="#20B833">IVR - Daily Dashboard PTP TOL</font></h1>
	</header>
	
	<?php if($user_id!=null && $user_id!=""){?>
	
		<div align="center"><table width="100%" height="100%" border="0" bordercolor="black">
    		<tr align="center">
    			<td>
    				<table border="0">
    					<tr align="center">
    						<td>
    							<p><b>PTP TOL OVERALL</b></p>
			    				<p><font color="#FF6600" size="2"><span id="ct_jpgraph_1"></span></font><script type="text/javascript">window.onload = display_c();</script></p>
							    <%@ include file="graph.ivr.tol.overall.jsp" %>
    						</td>
    						<td>
			    				<p><b>PTP TOL HANDLE</b></p>
			    				<p><font color="#FF6600" size="2"><span id="ct_jpgraph_2"></span></font><script type="text/javascript">window.onload = display_c();</script></p>
							    <%@ include file="graph.ivr.tol.handle.jsp" %>
			    			</td>
			    			<td>
			    				<p><b>PTP TOL CHANNEL</b></p>
			    				<p><font color="#FF6600" size="2"><span id="ct_jpgraph_3"></span></font><script type="text/javascript">window.onload = display_c();</script></p>
							    <%@ include file="graph.ivr.tol.channel.jsp" %>
			    			</td>
			    			<td>
			    				<p><b>PTP TOL BY</b></p>
			    				<p><font color="#FF6600" size="2"><span id="ct_jpgraph_4"></span></font><script type="text/javascript">window.onload = display_c();</script></p>
							    <%@ include file="graph.ivr.tol.by.jsp" %>
			    			</td>
    					</tr>
    					<tr align="center">
    		
    							<td>
    								<%@ include file="graph.ivr.tol.overall.error.jsp" %>
	    						</td>
	    						<td>
	    							<%@ include file="graph.ivr.tol.overall.foul.jsp" %>
	    						</td>
	    						<td>
	    							<%@ include file="graph.ivr.tol.overall.success.jsp" %>
	    						</td>

    					</tr>
    				</table>
    			</td>
    		</tr>
    		<tr align="center">
    			<td>
    				<p><b>PTP TOL OVERALL BAR</b></p>
		    		<p><font color="#FF6600" size="2"><span id="ct_jpgraph_tyn_2"></span></font><script type="text/javascript">window.onload = display_c();</script></p>
    			<table border="1">			
					<tr>
						<td>
						    <h5>PTP OVERALL BAR</h5>
							    <%@ include file="graph.ivr.tol.overall.bar.jsp" %>
						</td>
						<td>
					    	<img id="jpgraph_ptp_tol_detail_bar_hour" alt="Monitoring PTP TOL BAR BY HOUR" src="jpgraph_ptp_tol_overall_bar_hour.php"/>
	    				</td>
    				</tr>
    			</table>			
    			</td>
    		</tr>
    		
    		<tr tr align="center">
    			<td>
    				<br>
    				<p><b>PTP TOL HANDLE GRAPH</b></p>
    				<p><font color="#FF6600" size="2"><span id="ct_jpgraph_tyn_2"></span></font><script type="text/javascript">window.onload = display_c();</script></p>
				<table border="1">			
					<tr>
						<td>
						    <img id="jpgraph_ptp_tol_byhandle_bar" alt="Monitoring PTP TOL HANDLE BAR" src="jpgraph_ptp_tol_handle_bar.php"/>
						</td>
						<td>
					    	<img id="jpgraph_ptp_tol_byhandle_bar_hour" alt="Monitoring PTP TOL HANDLE BAR BY HOUR" src="jpgraph_ptp_tol_handle_bar_hour.php"/>
	    				</td>
    				</tr>
    			</table>	
    			</td>
    		</tr>
    		
    		<tr tr align="center">
    			<td>
    				<br>
    				<p><b>PTP TOL CHANNEL GRAPH</b></p>
    				<p><font color="#FF6600" size="2"><span id="ct_jpgraph_tyn_2"></span></font><script type="text/javascript">window.onload = display_c();</script></p>
				<table border="1">			
					<tr>
						<td>
						    <img id="jpgraph_ptp_tol_channel_bar" alt="Monitoring PTP TOL CHANNEL BAR" src="jpgraph_ptp_tol_channel_bar.php"/>
						</td>
						<td>
					    	<img id="jpgraph_ptp_tol_channel_bar_hour" alt="Monitoring PTP TOL CHANNEL BAR BY HOUR" src="jpgraph_ptp_tol_channel_bar_hour.php"/>
	    				</td>
    				</tr>
    			</table>	
    			</td>
    		</tr>
    		
    		<tr tr align="center">
    			<td>
    				<br>
    				<p><b>PTP TMH BY GRAPH</b></p>
    				<p><font color="#FF6600" size="2"><span id="ct_jpgraph_tyn_2"></span></font><script type="text/javascript">window.onload = display_c();</script></p>
				<table border="1">			
					<tr>
						<td>
						    <img id="jpgraph_ptp_tol_by_bar" alt="Monitoring PTP TMH BY BAR" src="jpgraph_ptp_tol_by_bar.php"/>
						</td>
						<td>
					    	<img id="jpgraph_ptp_tol_by_bar_hour" alt="Monitoring PTP TMH BY BAR BY HOUR" src="jpgraph_ptp_tol_by_bar_hour.php"/>
	    				</td>
    				</tr>
    			</table>	
    			</td>
    		</tr>
    	</table></div>

    	
<?php }else{ require("access_deny_page.php"); }?>
	<div id="apDiv4"><footer>
			<p><?php print $footer ?></p>

	</footer></div>
    						
    						
</body>
</html>