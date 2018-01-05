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
	    <li><a href="${ctx}/fa/businessTrip/toBusinessTripTaskList">出差任务列表</a></li>
		<li class="active"><a href="">查看出差信息</a></li>
	</ul>
	<div class="breadcrumb form-search">
	<div style="background:#f9f9f9; text-align:center">
		<label style="font-weight:bold; font-size:15px">部门：${businessTripApplication.office.name}</label>&nbsp;&nbsp;&nbsp;&nbsp;
		<label style="font-weight:bold; font-size:15px">申请人：${businessTripApplication.applicant.name}</label>
	</div>
	<div style="background:#40abe9"><label style="font-weight:bold">出差信息</label></div>
	<div>
		<table class="table table-striped table-bsordered table-condensed">
			<tr>
				<td><label style="font-weight:bold">共同出差人</label></td>
				<td>${businessTripApplication.together.name}</td>
				<td><label style="font-weight:bold">联系方式</label></td>
				<td>${businessTripApplication.phone}</td>
			</tr>
			<tr>
				<td><label style="font-weight:bold">身份证号</label></td>
				<td>${businessTripApplication.IDNo}</td>
				<td><label style="font-weight:bold">项目名称</label></td>
				<td>${businessTripApplication.project.name}</td>
			</tr>
			<tr>
				<td><label style="font-weight:bold">出差类型</label></td>
				<td>${businessTripApplication.type}</td>
				<td><label style="font-weight:bold">出差事由</label></td>
				<td>${businessTripApplication.remark}</td>
			</tr>
			<tr>
				<td><label style="font-weight:bold">出差日期</label></td>
				<td>
					<fmt:formatDate value="${businessTripApplication.beginDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td><label style="font-weight:bold">客户经理</label></td>
				<td>${businessTripApplication.manager.name}</td>
			</tr>
		</table>
	</div>
	<div style="background:#40abe9"><label style="font-weight:bold">订房信息</label></div>
	<div>
		<table class="table table-striped table-bsordered table-condensed">
			<thead><tr><th style="width:15%">订房类型</th><th style="width:15%">出差城市</th><th style="width:15%">具体地点</th><th style="width:15%">入住日期</th><th style="width:15%">退房日期</th><th style="width:15%">共计天数</th></thead>
			<tbody>
				<c:forEach items="${businessTripReservationList}" var="businessTripReservation">
					<tr>
						<td>${businessTripReservation.type}</td>
						<td>${businessTripReservation.city}</td>
						<td>${businessTripReservation.workPlace}</td>
						<td><fmt:formatDate value="${businessTripReservation.beginDate}" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatDate value="${businessTripReservation.endDate}" pattern="yyyy-MM-dd"/></td>
						<td>${businessTripReservation.days}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div style="background:#40abe9"><label style="font-weight:bold">机票信息</label></div>
	<div>
		<table class="table table-striped table-bsordered table-condensed">
			<thead><tr><th style="width:20%">出行时间</th><th style="width:20%">机票价格</th><th style="width:20%">起始城市</th><th style="width:20%">目的城市</th><th style="width:20%">申请理由</th></tr></thead>
			<tbody>
				<c:forEach items="${businessTripAirTicketList}" var="businessTripAirTicket">
					<tr>
						<td><fmt:formatDate value="${businessTripAirTicket.flyDate}" pattern="yyyy-MM-dd HH:mm"/></td>
						<td>${businessTripAirTicket.amount}</td>
						<td>${businessTripAirTicket.startLocation}</td>
						<td>${businessTripAirTicket.arrivedLocation}</td>
						<td>${businessTripAirTicket.remark}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div>
		<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" style="width:100px"/>
	</div>
	</div>
</body>
</html>