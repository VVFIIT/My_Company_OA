<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出差申请</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
		
		function commitApplyForm(){
			var Fn = Function;
			var togetherId = $("#togetherId").val();
			var phone = $("#phone").val();
			var IDNo = $("#IDNo").val();
			var projectId = $("#projectId").val();
			var type = $("#type").val();
			var remark = $("#remark").val();
			var beginDate = $("#beginDate").val();
			var managerId = $("#managerId").val();
			var reservationNum = parseInt($("#reservationNum").val());
			var airTicketNum = parseInt($("#airTicketNum").val());
			var data = "reservationNum="+reservationNum+"&airTicketNum="+airTicketNum+"&togetherId="+togetherId+"&phone="+phone+"&IDNo="+IDNo+"&projectId="+projectId+"&type="+type+"&remark="+remark+"&beginDate="+beginDate+"&managerId="+managerId;
			for(var i=1; i<reservationNum+1; i++){
				var reservationEveryNum = $("#reservationEveryNum").val();
				var a= reservationEveryNum.split("");
				var reservationTypeVal = "$('#reservationType"+a[i-1]+"').val()";
				var reservationType = new Fn('return ' + reservationTypeVal)();
				var reservationCityVal = "$('#reservationCity"+a[i-1]+"').val()";
				var reservationCity = new Fn('return ' + reservationCityVal)();
				var reservationWorkPlaceVal = "$('#reservationWorkPlace"+a[i-1]+"').val()";
				var reservationWorkPlace = new Fn('return ' + reservationWorkPlaceVal)();
				var reservationBeginDateVal = "$('#reservationBeginDate"+a[i-1]+"').val()";
				var reservationBeginDate = new Fn('return ' + reservationBeginDateVal)();
				var reservationEndDateVal = "$('#reservationEndDate"+a[i-1]+"').val()";
				var reservationEndDate = new Fn('return ' + reservationEndDateVal)();
				var reservationDaysVal = "$('#reservationDays"+a[i-1]+"').val()";
				var reservationDays = new Fn('return ' + reservationDaysVal)();
				var reservationEveryData = "&reservationType"+i+"="+reservationType+"&reservationCity"+i+"="+reservationCity+"&reservationWorkPlace"+i+"="+reservationWorkPlace+"&reservationBeginDate"+i+"="+reservationBeginDate+"&reservationEndDate"+i+"="+reservationEndDate+"&reservationDays"+i+"="+reservationDays;
				data = data+reservationEveryData;
			}
			for(var j=1; j<reservationNum+1; j++){
				var airTicketEveryNum = $("#airTicketEveryNum").val();
				var a= airTicketEveryNum.split("");
				var airTicketFlyDateVal = "$('#airTicketFlyDate"+a[j-1]+"').val()";
				var airTicketFlyDate = new Fn('return ' + airTicketFlyDateVal)();
				var airTicketAmountVal = "$('#airTicketAmount"+a[j-1]+"').val()";
				var airTicketAmount = new Fn('return ' + airTicketAmountVal)();
				var airTicketStartLocationVal = "$('#airTicketStartLocation"+a[j-1]+"').val()";
				var airTicketStartLocation = new Fn('return ' + airTicketStartLocationVal)();
				var airTicketArrivedLocationVal = "$('#airTicketArrivedLocation"+a[j-1]+"').val()";
				var airTicketArrivedLocation = new Fn('return ' + airTicketArrivedLocationVal)();
				var airTicketRemarkVal = "$('#airTicketRemark"+a[j-1]+"').val()";
				var airTicketRemark = new Fn('return ' + airTicketRemarkVal)();
				var airTicketEveryData = "&airTicketFlyDate"+j+"="+airTicketFlyDate+"&airTicketAmount"+j+"="+airTicketAmount+"&airTicketStartLocation"+j+"="+airTicketStartLocation+"&airTicketArrivedLocation"+j+"="+airTicketArrivedLocation+"&airTicketRemark"+j+"="+airTicketRemark;
				data = data+airTicketEveryData;
			}
			var type = "json";
			$.post("${ctx}/fa/businessTrip/commitApplyForm", data, type);
			$("#businessTripApplyForm").submit();
		}
		
		function addBusinessTripReservation(){
			var reservationEveryNum = $("#reservationEveryNum").val();
			var a= reservationEveryNum.split("");
			var trMaxNum = parseInt(a[a.length-1])+1;
			var trNum = parseInt($("#reservationNum").val())+1;
			$("#reservationNum").val(trNum);
			reservationEveryNum = reservationEveryNum+trMaxNum;
			$("#reservationEveryNum").val(reservationEveryNum);
			var reservationTypeId = 'reservationType'+trMaxNum;
			var reservationCityId = 'reservationCity'+trMaxNum;
			var reservationWorkPlaceId = 'reservationWorkPlace'+trMaxNum;
			var reservationBeginDateId = 'reservationBeginDate'+trMaxNum;
			var reservationEndDateId = 'reservationEndDate'+trMaxNum;
			var reservationDaysId = 'reservationDays'+trMaxNum;
			var reservationId = 'reservation'+trMaxNum;
			var sameStr = "' maxlength='50' class='required' style='width:150px'/></td><td><input id='";
			$("#reservationButton").before("<tr id='"+reservationId+"'><td><input id='"+reservationTypeId+sameStr+reservationCityId+sameStr+reservationWorkPlaceId+sameStr+reservationBeginDateId+
					"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate' style='width:150px;' value='<fmt:formatDate value='${businessTripModel.businessTripReservationList.get(0).beginDate}' pattern='yyyy-MM-dd'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd&#39;});'/></td><td><input id='"+reservationEndDateId+
					"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate' style='width:150px;' value='<fmt:formatDate value='${businessTripModel.businessTripReservationList.get(0).endDate}' pattern='yyyy-MM-dd'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd&#39;});'/></td><td><input id='"+reservationDaysId+
					"' maxlength='50' class='required' style='width:150px'/></td><td><input class='btn btn-primary' type='button' value='删除' onclick='removeBusinessTripReservation(&#39;"+reservationId+"&#39;)' style='width:100px'></td></tr>");
		}
		
		function addBusinessTripAirTicket(){
			
			var airTicketEveryNum = $("#airTicketEveryNum").val();
			var a= airTicketEveryNum.split("");
			var trMaxNum = parseInt(a[a.length-1])+1;
			var trNum = parseInt($("#airTicketNum").val())+1;
			$("#airTicketNum").val(trNum);
			airTicketEveryNum = airTicketEveryNum+trMaxNum;
			$("#airTicketEveryNum").val(airTicketEveryNum);
			var airTicketFlyDateId = 'airTicketFlyDate'+trMaxNum;
			var airTicketAmountId = 'airTicketAmount'+trMaxNum;
			var airTicketStartLocationId = 'airTicketStartLocation'+trMaxNum;
			var airTicketArrivedLocationId = 'airTicketArrivedLocation'+trMaxNum;
			var airTicketRemarkId = 'airTicketRemark'+trMaxNum;
			var airTicketId = 'airTicket'+trMaxNum;
			var sameStr = "' maxlength='50' class='required' style='width:190px'/></td><td><input id='";
			$("#airTicketButton").before("<tr id='"+airTicketId+"'><td><input id='"+airTicketFlyDateId+
					"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate' style='width:190px;' value='<fmt:formatDate value='${businessTripModel.businessTripReservationList.get(0).beginDate}' pattern='yyyy-MM-dd HH:mm'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm&#39;});'/></td><td><input id='"
					+airTicketAmountId+sameStr+airTicketStartLocationId+sameStr+airTicketArrivedLocationId+sameStr+airTicketRemarkId+"' maxlength='50' class='required' style='width:190px'/></td><td><input class='btn btn-primary' type='button' value='删除' onclick='removeBusinessTripAirTicket(&#39;"+airTicketId+"&#39;)' style='width:100px'></td></tr>");
		}
		
		function removeBusinessTripReservation(reservationId){
			var reservationEveryNum = $("#reservationEveryNum").val();
			var idNum = reservationId.substring(11);
			var EveryNum = "";
			var a= reservationEveryNum.split("");
			for(var i=0;i<a.length;i++){
				if(a[i]!=idNum){
					EveryNum = EveryNum+a[i];
				}
			}
			$("#reservationEveryNum").val(EveryNum);
			var trNum = parseInt($("#reservationNum").val())-1;
			$("#reservationNum").val(trNum);
			var reservationRemovedStr = "$('#"+reservationId+"')";
			var Fn = Function;
			var reservationRemoved = new Fn('return ' + reservationRemovedStr)();
			reservationRemoved.remove();
		}
		
		function removeBusinessTripAirTicket(airTicketId){
			var airTicketEveryNum = $("#airTicketEveryNum").val();
			var idNum = airTicketId.substring(9);
			var EveryNum = "";
			var a= airTicketEveryNum.split("");
			for(var i=0;i<a.length;i++){
				if(a[i]!=idNum){
					EveryNum = EveryNum+a[i];
				}
			}
			$("#airTicketEveryNum").val(EveryNum);
			var trNum = parseInt($("#airTicketNum").val())-1;
			$("#airTicketNum").val(trNum);
			var airTicketRemovedStr = "$('#"+airTicketId+"')";
			var Fn = Function;
			var airTicketRemoved = new Fn('return ' + airTicketRemovedStr)();
			airTicketRemoved.remove();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs" id="updateTitle">
	    <li class="active"><a href="${ctx}/fa/businessTrip/toApplyForm">出差申请</a></li>
	</ul>
	<form id="businessTripApplyForm" target="mainFrame" action="${ctx}/fa/businessTrip/commitApplyFormSoncend" method="post" class="breadcrumb form-search">
		<sys:message content="${message}"/>
		<input id="reservationNum" type="hidden" value="1">
		<input id="airTicketNum" type="hidden" value="1">
		<input id="reservationEveryNum" type="hidden" value="1">
		<input id="airTicketEveryNum" type="hidden" value="1">
		<div style="background:#f9f9f9; text-align:center">
			<label style="font-weight:bold; font-size:15px">部门：${businessTripModel.businessTripApplication.officeId}</label>
			<label style="font-weight:bold; font-size:15px">申请人：${businessTripModel.businessTripApplication.applicantId}</label>
		</div>
		<div style="background:#40abe9"><label style="font-weight:bold">出差信息</label></div>
		<div>
			<table class="table table-striped table-bsordered table-condensed">
				<tr>
					<td><label style="font-weight:bold">共同出差人</label></td>
					<td><input id="togetherId" maxlength="50" class="required"/></td>
					<td><label style="font-weight:bold">联系方式</label></td>
					<td><input id="phone" maxlength="50" class="required"/></td>
				</tr>
				<tr>
					<td><label style="font-weight:bold">身份证号</label></td>
					<td><input id="IDNo" maxlength="50" class="required"/></td>
					<td><label style="font-weight:bold">项目名称</label></td>
					<td><input id="projectId" maxlength="50" class="required"/></td>
				</tr>
				<tr>
					<td><label style="font-weight:bold">出差类型</label></td>
					<td><input id="type" maxlength="50" class="required"/></td>
					<td><label style="font-weight:bold">出差事由</label></td>
					<td><input id="remark"  maxlength="50" class="required"/></td>
				</tr>
				<tr>
					<td><label style="font-weight:bold">出差日期</label></td>
					<td>
						<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:200px;"
							value="<fmt:formatDate value="${businessTripModel.businessTripApplication.beginDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					</td>
					<td><label style="font-weight:bold">客户经理</label></td>
					<td><input id="managerId" maxlength="50" class="required"/></td>
				</tr>
			</table>
		</div>
		<div style="background:#40abe9"><label style="font-weight:bold">订房信息</label></div>
		<div>
			<table class="table table-striped table-bsordered table-condensed">
				<thead><tr><th>订房类型</th><th>出差城市</th><th>具体地点</th><th>入住日期</th><th>退房日期</th><th>共计天数</th><th>删除记录</th></thead>
				<tbody>
					<tr id="reservation1">
						<td><input style="width:150px" id="reservationType1" maxlength="50" class="required"/></td>
						<td><input style="width:150px" id="reservationCity1" maxlength="50" class="required"/></td>
						<td><input style="width:150px" id="reservationWorkPlace1" maxlength="50" class="required"/></td>
						<td>
						<input id="reservationBeginDate1" name="reservationBeginDate1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:150px;"
							value="<fmt:formatDate value="${businessTripModel.businessTripReservationList.get(0).beginDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
						</td>
						<td>
						<input id="reservationEndDate1" name="reservationEndDate1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:150px;"
							value="<fmt:formatDate value="${businessTripModel.businessTripReservationList.get(0).endDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
						</td>
						<!-- <td><input style="width:150px" id="reservationBeginDate1" maxlength="50" class="required"/></td> -->
						<!-- <td><input style="width:150px" id="reservationEndDate1" maxlength="50" class="required"/></td> -->
						<td><input style="width:150px" id="reservationDays1" maxlength="50" class="required"/></td>
						<td><input class="btn btn-primary" type="button" value="删除" onclick="removeBusinessTripReservation('reservation1')" style="width:100px"></td>
					</tr>
					<tr id="reservationButton">
						<td colspan="7" style="text-align:center"><input class="btn btn-primary" type="button" value="+" onclick="addBusinessTripReservation()" style="width:100px"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="background:#40abe9"><label style="font-weight:bold">机票信息</label></div>
		<div>
			<table class="table table-striped table-bsordered table-condensed">
				<thead><tr><th style="width:15%">出行时间</th><th>机票价格</th><th>起始城市</th><th>目的城市</th><th>申请理由</th><th>删除记录</th></tr></thead>
				<tbody>
					<tr id="airTicket1">
						<td>
						<input id="airTicketFlyDate1" name="airTicketFlyDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:190px;"
							value="<fmt:formatDate value="${businessTripModel.businessTripReservationList.get(0).endDate}" pattern="yyyy-MM-dd HH:mm"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});"/>
						</td>
						<!-- <td><input style="width:190px" id="airTicketFlyDate" maxlength="50" class="required"/></td> -->
						<td><input style="width:190px" id="airTicketAmount1" maxlength="50" class="required"/></td>
						<td><input style="width:190px" id="airTicketStartLocation1" maxlength="50" class="required"/></td>
						<td><input style="width:190px" id="airTicketArrivedLocation1" maxlength="50" class="required"/></td>
						<td><input style="width:190px" id="airTicketRemark1" maxlength="50" class="required"/></td>
						<td><input class="btn btn-primary" type="button" value="删除" onclick="removeBusinessTripAirTicket('airTicket1')" style="width:100px"></td>
					</tr>
					<tr id="airTicketButton">
						<td colspan="6" style="text-align:center"><input class="btn btn-primary" type="button" value="+" onclick="addBusinessTripAirTicket()" style="width:100px"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<input id="commitButton" class="btn btn-primary" type="button" value="提交" onclick="commitApplyForm()" style="width:100px"/>
		</div>
	</form>
</body>
</html>