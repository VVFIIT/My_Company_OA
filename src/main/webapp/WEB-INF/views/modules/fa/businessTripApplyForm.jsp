<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出差申请</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs" id="updateTitle">
	    <li class="active"><a href="${ctx}/fa/businessTrip/toApplyForm">出差申请</a></li>
	</ul>
	<form:form id="businessTripForm" modelAttribute="businessTripModel" target="mainFrame" action="${ctx}/fa/businessTrip/commitApplyForm" method="post" class="breadcrumb form-search">
		<table id="attendanceTable" class="table table-striped table-bsordered table-condensed">
			
			
		</table>
	</form:form>
</body>
</html>