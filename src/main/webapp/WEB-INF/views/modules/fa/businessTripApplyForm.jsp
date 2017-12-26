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
		<div style="background:yellow">
			<label style="font-weight:bold">部门：${businessTripModel.businessTripApplication.officeId}</label>
			<label style="font-weight:bold">申请人：${businessTripModel.businessTripApplication.applicantId}</label>
		</div>
		<div style="background:red"><label style="font-weight:bold">出差信息</label></div>
		<div>
			<table class="table table-striped table-bsordered table-condensed">
				<tr>
					<td><label style="font-weight:bold">共同出差人</label></td>
					<td><form:input path="businessTripApplication.togetherId" htmlEscape="false" maxlength="50" class="required"/></td>
					<td><label style="font-weight:bold">联系方式</label></td>
					<td><form:input path="businessTripApplication.phone" htmlEscape="false" maxlength="50" class="required"/></td>
				</tr>
				<tr>
					<td><label style="font-weight:bold">身份证号</label></td>
					<td><form:input path="businessTripApplication.IDNo" htmlEscape="false" maxlength="50" class="required"/></td>
					<td><label style="font-weight:bold">项目名称</label></td>
					<td><form:input path="businessTripApplication.projectId" htmlEscape="false" maxlength="50" class="required"/></td>
				</tr>
				<tr>
					<td><label style="font-weight:bold">出差类型</label></td>
					<td><form:input path="businessTripApplication.type" htmlEscape="false" maxlength="50" class="required"/></td>
					<td><label style="font-weight:bold">出差事由</label></td>
					<td><form:input path="businessTripApplication.remark" htmlEscape="false" maxlength="50" class="required"/></td>
				</tr>
				<tr>
					<td><label style="font-weight:bold">出差日期</label></td>
					<td><form:input path="businessTripApplication.beginDate" htmlEscape="false" maxlength="50" class="required"/></td>
					<td><label style="font-weight:bold">客户经理</label></td>
					<td><form:input path="businessTripApplication.managerId" htmlEscape="false" maxlength="50" class="required"/></td>
				</tr>
			</table>
		</div>
		<div style="background:red"><label style="font-weight:bold">订房信息</label></div>
		<div>
			<table class="table table-striped table-bsordered table-condensed">
				<thead><tr><th>订房类型</th><th>出差城市</th><th>出差具体工作地点</th><th>入住日期</th><th>退房日期</th></thead>
				<tbody>
					<c:forEach items="${businessTripModel.businessTripReservationList}" var="businessTripReservation" varStatus="status">
						<tr>
							<td><form:input path="businessTripReservationList[${status.index}].type" htmlEscape="false" maxlength="50" class="required"/></td>
							<td><form:input path="businessTripReservationList[${status.index}].city" htmlEscape="false" maxlength="50" class="required"/></td>
							<td><form:input path="businessTripReservationList[${status.index}].workPlace" htmlEscape="false" maxlength="50" class="required"/></td>
							<td><form:input path="businessTripReservationList[${status.index}].beginDate" htmlEscape="false" maxlength="50" class="required"/></td>
							<td><form:input path="businessTripReservationList[${status.index}].endDate" htmlEscape="false" maxlength="50" class="required"/></td>
							<td><form:input path="businessTripReservationList[${status.index}].days" htmlEscape="false" maxlength="50" class="required"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div style="background:red"><label style="font-weight:bold">机票信息</label></div>
		<div>
			<table id="attendanceTable" class="table table-striped table-bsordered table-condensed">
				<thead><tr><th>行程时间</th><th>机票价格</th><th>起始地</th><th>目的地</th><th>申请理由</th></thead>
				<tbody>
					<c:forEach items="${businessTripModel.businessTripReservationList}" var="businessTripReservation" varStatus="status">
						<tr>
							<td><form:input path="businessTripAirTicketList[${status.index}].flyDate" htmlEscape="false" maxlength="50" class="required"/></td>
							<td><form:input path="businessTripAirTicketList[${status.index}].amount" htmlEscape="false" maxlength="50" class="required"/></td>
							<td><form:input path="businessTripAirTicketList[${status.index}].startLocation" htmlEscape="false" maxlength="50" class="required"/></td>
							<td><form:input path="businessTripAirTicketList[${status.index}].arrivedLocation" htmlEscape="false" maxlength="50" class="required"/></td>
							<td><form:input path="businessTripAirTicketList[${status.index}].remark" htmlEscape="false" maxlength="50" class="required"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div>
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交" style="margin-left:50px"/>
		</div>
	</form:form>
</body>
</html>