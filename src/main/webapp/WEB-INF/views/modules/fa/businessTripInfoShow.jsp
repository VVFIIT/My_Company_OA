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
	    <c:if test="${mode=='task'}"><li><a href="${ctx}/fa/businessTrip/toBusinessTripTaskList">出差任务列表</a></li></c:if>
	    <c:if test="${mode=='search'}"><li><a href="${ctx}/fa/businessTrip/toBusinessTripInfoList">出差信息查询</a></li></c:if>
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
				<td><label style="font-weight:bold">项目名称</label></td>
				<td>${businessTripApplication.project.name}</td>
				<td><label style="font-weight:bold">身份证号</label></td>
				<td>${businessTripApplication.IDNo}</td>
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
				<td><label style="font-weight:bold">结束日期</label></td>
				<td>
					<fmt:formatDate value="${businessTripApplication.endDate}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</table>
	</div>
	<div style="background:#40abe9"><label style="font-weight:bold">订房信息</label></div>
	<div>
		<table class="table table-striped table-bsordered table-condensed">
			<thead><tr><th style="width:10%">出差城市</th><th style="width:25%">具体地点</th><th style="width:15%">入住日期</th><th style="width:15%">退房日期</th><th style="width:10%">共计天数</th><th style="width:20%">备注</th></thead>
			<tbody>
				<c:forEach items="${businessTripReservationList}" var="businessTripReservation">
					<c:if test="${businessTripReservation.insertFlag=='yes'}">
						<tr>
							<td style="color:red">${businessTripReservation.city}</td>
							<td style="color:red">${businessTripReservation.workPlace}</td>
							<td style="color:red"><fmt:formatDate value="${businessTripReservation.beginDate}" pattern="yyyy-MM-dd"/></td>
							<td style="color:red"><fmt:formatDate value="${businessTripReservation.endDate}" pattern="yyyy-MM-dd"/></td>
							<td style="color:red">${businessTripReservation.days}</td>
							<td style="color:red">${businessTripReservation.remark}</td>
						</tr>
					</c:if>
					<c:if test="${businessTripReservation.insertFlag=='no'}">
						<tr>
							<td>${businessTripReservation.city}</td>
							<td>${businessTripReservation.workPlace}</td>
							<td><fmt:formatDate value="${businessTripReservation.beginDate}" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${businessTripReservation.endDate}" pattern="yyyy-MM-dd"/></td>
							<td>${businessTripReservation.days}</td>
							<td>${businessTripReservation.remark}</td>
						</tr>
					</c:if>
					
				</c:forEach>
			</tbody>
		</table>
	</div>
	<c:if test="${businessTripApplication.status=='50'}">
		<c:forEach items="${businessTripHotelHelperList}" var="businessTripHotelHelper">
			<c:if test="${businessTripHotelHelper.businessTripHotel.insertFlag=='yes'}">
				<div style="background:#40abe9; margin-top:10px; color:red"><label style="font-weight:bold">${businessTripHotelHelper.businessTripReservation.city}&nbsp;<fmt:formatDate value="${businessTripHotelHelper.businessTripReservation.beginDate}" pattern="yyyy-MM-dd"/>--<fmt:formatDate value="${businessTripHotelHelper.businessTripReservation.endDate}" pattern="yyyy-MM-dd"/>&nbsp;酒店信息</label></div>
				<table class="table table-striped table-bsordered table-condensed">
					<tr>
						<td><label style="font-weight:bold">订房类型</label></td>
						<td style="color:red">${businessTripHotelHelper.businessTripHotel.type}</td>
						<td><label style="font-weight:bold">联系人</label></td>
						<td style="color:red">${businessTripHotelHelper.businessTripHotel.contact}</td>
					</tr>
					<tr>
						<td><label style="font-weight:bold">住宿酒店</label></td>
						<td style="color:red">${businessTripHotelHelper.businessTripHotel.hotel}</td>
						<td><label style="font-weight:bold">联系电话</label></td>
						<td style="color:red">${businessTripHotelHelper.businessTripHotel.contactPhone}</td>
					</tr>
					<tr>
						<td><label style="font-weight:bold">具体地址</label></td>
						<td style="color:red" colspan="3">${businessTripHotelHelper.businessTripHotel.address}</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${businessTripHotelHelper.businessTripHotel.insertFlag=='no'}">
				<div style="background:#40abe9; margin-top:10px"><label style="font-weight:bold">${businessTripHotelHelper.businessTripReservation.city}&nbsp;<fmt:formatDate value="${businessTripHotelHelper.businessTripReservation.beginDate}" pattern="yyyy-MM-dd"/>--<fmt:formatDate value="${businessTripHotelHelper.businessTripReservation.endDate}" pattern="yyyy-MM-dd"/>&nbsp;酒店信息</label></div>
				<table class="table table-striped table-bsordered table-condensed">
					<tr>
						<td><label style="font-weight:bold">订房类型</label></td>
						<td>${businessTripHotelHelper.businessTripHotel.type}</td>
						<td><label style="font-weight:bold">联系人</label></td>
						<td>${businessTripHotelHelper.businessTripHotel.contact}</td>
					</tr>
					<tr>
						<td><label style="font-weight:bold">住宿酒店</label></td>
						<td>${businessTripHotelHelper.businessTripHotel.hotel}</td>
						<td><label style="font-weight:bold">联系电话</label></td>
						<td>${businessTripHotelHelper.businessTripHotel.contactPhone}</td>
					</tr>
					<tr>
						<td><label style="font-weight:bold">具体地址</label></td>
						<td colspan="3">${businessTripHotelHelper.businessTripHotel.address}</td>
					</tr>
				</table>
			</c:if>
		</c:forEach>
	</c:if>
	<div style="background:#40abe9"><label style="font-weight:bold">机票信息</label></div>
	<div>
		<table class="table table-striped table-bsordered table-condensed">
			<thead><tr><th style="width:20%">出行时间</th><th style="width:20%">机票价格</th><th style="width:20%">起始城市</th><th style="width:20%">目的城市</th><th style="width:20%">申请理由</th></tr></thead>
			<tbody>
				<c:forEach items="${businessTripAirTicketList}" var="businessTripAirTicket">
					<c:if test="${businessTripAirTicket.insertFlag=='yes'}">
						<tr>
							<td style="color:red"><fmt:formatDate value="${businessTripAirTicket.flyDate}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td style="color:red">${businessTripAirTicket.amount}</td>
							<td style="color:red">${businessTripAirTicket.startLocation}</td>
							<td style="color:red">${businessTripAirTicket.arrivedLocation}</td>
							<td style="color:red">${businessTripAirTicket.remark}</td>
						</tr>
					</c:if>
					<c:if test="${businessTripAirTicket.insertFlag=='no'}">
						<tr>
							<td><fmt:formatDate value="${businessTripAirTicket.flyDate}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td>${businessTripAirTicket.amount}</td>
							<td>${businessTripAirTicket.startLocation}</td>
							<td>${businessTripAirTicket.arrivedLocation}</td>
							<td>${businessTripAirTicket.remark}</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<c:if test="${businessTripApplication.managerComment!=null}">
		<div style="background:#40abe9; margin-bottom:10px"><label style="font-weight:bold">经理的意见</label></div>
		<div>
			<c:if test="${businessTripApplication.managerFlag == 'yes'}"><p>${businessTripApplication.managerComment}</p></c:if>
			<c:if test="${businessTripApplication.managerFlag == 'no'}"><p style="color:red">${businessTripApplication.managerComment}</p></c:if>
		</div>
	</c:if>
	<c:if test="${businessTripApplication.FAComment!=null}">
		<div style="background:#40abe9; margin-bottom:10px"><label style="font-weight:bold">财务的意见</label></div>
		<div>
			<c:if test="${businessTripApplication.FAFlag == 'yes'}"><p>${businessTripApplication.FAComment}</p></c:if>
			<c:if test="${businessTripApplication.FAFlag == 'no'}"><p style="color:red">${businessTripApplication.FAComment}</p></c:if>
		</div>
	</c:if>
	<div>
		<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" style="width:100px"/>
	</div>
	</div>
</body>
</html>