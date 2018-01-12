<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出差申请</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var reservationNum = ${reservationEveryNum};
			var airTicketNum = ${airTicketEveryNum};
			addReservationInitTr(reservationNum);
			addAirTicketInitTr(airTicketNum);
			
			$("#insertBusinessTripForm").validate({
				submitHandler: function(form){
					if(confirm('确定要重新提交该出差追加申请吗?')){
						loading('正在提交，请稍等...');
						form.submit();
					}else{
						return ; 
					}
				},
			});
		});
		/* 初始化订房信息 */
		function addReservationInitTr(reservationNum){
			for(var i=0;i<reservationNum;i++){
				var reservationEveryNum = $("#reservationEveryNum").val();
				var a= reservationEveryNum.split("");
				var trMaxNum = parseInt(a[a.length-1])+1;
				if(reservationEveryNum=='0'){
					reservationEveryNum = trMaxNum;
				}else{
					reservationEveryNum = reservationEveryNum+trMaxNum;
				}
				$("#reservationEveryNum").val(reservationEveryNum);
			}
			var sameStr = "' maxlength='50' class='required' style='width:190px'/></td><td><input id='";
			var nameStr = "' name='";
			var valueStr = "' value='";
			var reservationTypeValue1 = '111';
			<c:forEach items="${businessTripReservationInsertList}" var="businessTripReservationInsert" varStatus="index">
				var reservationCityId = 'reservationCity${index.count}';
				var reservationWorkPlaceId = 'reservationWorkPlace${index.count}';
				var reservationBeginDateId = 'reservationBeginDate${index.count}';
				var reservationEndDateId = 'reservationEndDate${index.count}';
				var reservationDaysId = 'reservationDays${index.count}';
				var reservationRemarkId = 'reservationRemark${index.count}';
				var reservationId = 'reservation${index.count}';
				
				var reservationCityValue = '${businessTripReservationInsert.city}';
				var reservationWorkPlaceValue = '${businessTripReservationInsert.workPlace}';
				var reservationBeginDateValue = '${businessTripReservationInsert.beginDate}';
				var reservationEndDateValue = '${businessTripReservationInsert.endDate}';
				var reservationDaysValue = '${businessTripReservationInsert.days}';
				var reservationRemarkValue = '${businessTripReservationInsert.remark}';
				$("#reservationButton").before("<tr id='"+reservationId+"'><td><input id='"+reservationCityId+nameStr+reservationCityId+valueStr+reservationCityValue+"' maxlength='50' class='required' style='width:120px'/></td><td><input id='"+reservationWorkPlaceId+nameStr+reservationWorkPlaceId+valueStr+reservationWorkPlaceValue+sameStr+reservationBeginDateId+nameStr+reservationBeginDateId+
						"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate required' style='width:150px;' value='<fmt:formatDate value='${businessTripReservationInsert.beginDate}' pattern='yyyy-MM-dd'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd&#39;});'/></td><td><input id='"+reservationEndDateId+nameStr+reservationEndDateId+
						"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate' style='width:150px;' value='<fmt:formatDate value='${businessTripReservationInsert.endDate}' pattern='yyyy-MM-dd'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd&#39;});'/></td><td><input id='"+reservationDaysId+nameStr+reservationDaysId+valueStr+reservationDaysValue+
						"' maxlength='50' style='width:100px'/></td><td><input id='"+reservationRemarkId+nameStr+reservationRemarkId+valueStr+reservationRemarkValue+"' maxlength='50' style='width:190px'/></td><td><input class='btn btn-primary' type='button' value='删除' onclick='removeBusinessTripReservation(&#39;"+reservationId+"&#39;)' style='width:100px'></td></tr>");
			</c:forEach>
		}
		/* 初始化机票信息 */
		function addAirTicketInitTr(airTicketNum){
			for(var i=0;i<airTicketNum;i++){
				var airTicketEveryNum = $("#airTicketEveryNum").val();
				var a= airTicketEveryNum.split("");
				var trMaxNum = parseInt(a[a.length-1])+1;
				if(airTicketEveryNum=='0'){
					airTicketEveryNum = trMaxNum;
				}else{
					airTicketEveryNum = airTicketEveryNum+trMaxNum;
				}
				$("#airTicketEveryNum").val(airTicketEveryNum);
			}
			var sameStr = "' maxlength='50' class='required' style='width:190px'/></td><td><input id='";
			var nameStr = "' name='";
			var valueStr = "' value='";
			var reservationTypeValue1 = '111';
			<c:forEach items="${businessTripAirTicketInsertList}" var="businessTripAirTicketInsert" varStatus="index">
				var airTicketFlyDateId = 'airTicketFlyDate${index.count}';
				var airTicketAmountId = 'airTicketAmount${index.count}';
				var airTicketStartLocationId = 'airTicketStartLocation${index.count}';
				var airTicketArrivedLocationId = 'airTicketArrivedLocation${index.count}';
				var airTicketRemarkId = 'airTicketRemark${index.count}';
				var airTicketId = 'airTicket${index.count}';
				
				var airTicketFlyDateValue = '${businessTripAirTicketInsert.flyDate}';
				
				var airTicketAmountValue = '${businessTripAirTicketInsert.amount}';
				var airTicketStartLocationValue = '${businessTripAirTicketInsert.startLocation}';
				var airTicketArrivedLocationValue = '${businessTripAirTicketInsert.arrivedLocation}';
				var airTicketRemarkValue = '${businessTripAirTicketInsert.remark}';
				$("#airTicketButton").before("<tr id='"+airTicketId+"'><td><input id='"+airTicketFlyDateId+nameStr+airTicketFlyDateId+
						"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate' style='width:170px;' value='<fmt:formatDate value='${businessTripAirTicketInsert.flyDate}' pattern='yyyy-MM-dd HH:mm'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm&#39;});'/></td><td><input id='"
						+airTicketAmountId+nameStr+airTicketAmountId+valueStr+airTicketAmountValue+sameStr+airTicketStartLocationId+nameStr+airTicketStartLocationId+valueStr+airTicketStartLocationValue+sameStr+airTicketArrivedLocationId+nameStr+airTicketArrivedLocationId+valueStr+airTicketArrivedLocationValue+sameStr+airTicketRemarkId+nameStr+airTicketRemarkId+valueStr+airTicketRemarkValue+"' maxlength='50' class='required' style='width:190px'/></td><td><input class='btn btn-primary' type='button' value='删除' onclick='removeBusinessTripAirTicket(&#39;"+airTicketId+"&#39;)' style='width:100px'></td></tr>");
			</c:forEach>
		}
		/* 增加一条订房信息 */
		function addBusinessTripReservation(){
			var reservationEveryNum = $("#reservationEveryNum").val();
			var a= reservationEveryNum.split("");
			var trMaxNum = parseInt(a[a.length-1])+1;
			if(reservationEveryNum=='0'){
				reservationEveryNum = trMaxNum;
			}else{
				reservationEveryNum = reservationEveryNum+trMaxNum;
			}
			$("#reservationEveryNum").val(reservationEveryNum);
			var reservationCityId = 'reservationCity'+trMaxNum;
			var reservationWorkPlaceId = 'reservationWorkPlace'+trMaxNum;
			var reservationBeginDateId = 'reservationBeginDate'+trMaxNum;
			var reservationEndDateId = 'reservationEndDate'+trMaxNum;
			var reservationDaysId = 'reservationDays'+trMaxNum;
			var reservationRemarkId = 'reservationRemark'+trMaxNum;
			var reservationId = 'reservation'+trMaxNum;
			var sameStr = "' maxlength='50' class='required' style='width:190px'/></td><td><input id='";
			var nameStr = "' name='";
			$("#reservationButton").before("<tr id='"+reservationId+"'><td><input id='"+reservationCityId+nameStr+reservationCityId+"' maxlength='50' class='required' style='width:120px'/></td><td><input id='"+reservationWorkPlaceId+nameStr+reservationWorkPlaceId+sameStr+reservationBeginDateId+nameStr+reservationBeginDateId+
					"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate required' style='width:150px;' value='<fmt:formatDate value='${businessTripModel.businessTripReservationList.get(0).beginDate}' pattern='yyyy-MM-dd'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd&#39;});'/></td><td><input id='"+reservationEndDateId+nameStr+reservationEndDateId+
					"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate' style='width:150px;' value='<fmt:formatDate value='${businessTripModel.businessTripReservationList.get(0).endDate}' pattern='yyyy-MM-dd'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd&#39;});'/></td><td><input id='"+reservationDaysId+nameStr+reservationDaysId+
					"' maxlength='50' style='width:100px'/></td><td><input id='"+reservationRemarkId+nameStr+reservationRemarkId+"' maxlength='50' style='width:190px'/></td><td><input class='btn btn-primary' type='button' value='删除' onclick='removeBusinessTripReservation(&#39;"+reservationId+"&#39;)' style='width:100px'></td></tr>");
		}
		/* 增加一条机票信息 */
		function addBusinessTripAirTicket(){
			var airTicketEveryNum = $("#airTicketEveryNum").val();
			var a= airTicketEveryNum.split("");
			var trMaxNum = parseInt(a[a.length-1])+1;
			if(airTicketEveryNum=='0'){
				airTicketEveryNum = trMaxNum;
			}else{
				airTicketEveryNum = airTicketEveryNum+trMaxNum;
			}
			$("#airTicketEveryNum").val(airTicketEveryNum);
			var airTicketFlyDateId = 'airTicketFlyDate'+trMaxNum;
			var airTicketAmountId = 'airTicketAmount'+trMaxNum;
			var airTicketStartLocationId = 'airTicketStartLocation'+trMaxNum;
			var airTicketArrivedLocationId = 'airTicketArrivedLocation'+trMaxNum;
			var airTicketRemarkId = 'airTicketRemark'+trMaxNum;
			var airTicketId = 'airTicket'+trMaxNum;
			var sameStr = "' maxlength='50' class='required' style='width:190px'/></td><td><input id='";
			var nameStr = "' name='";
			$("#airTicketButton").before("<tr id='"+airTicketId+"'><td><input id='"+airTicketFlyDateId+nameStr+airTicketFlyDateId+
					"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate' style='width:170px;' value='<fmt:formatDate value='${businessTripModel.businessTripReservationList.get(0).beginDate}' pattern='yyyy-MM-dd HH:mm'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm&#39;});'/></td><td><input id='"
					+airTicketAmountId+nameStr+airTicketAmountId+sameStr+airTicketStartLocationId+nameStr+airTicketStartLocationId+sameStr+airTicketArrivedLocationId+nameStr+airTicketArrivedLocationId+sameStr+airTicketRemarkId+nameStr+airTicketRemarkId+"' maxlength='50' class='required' style='width:190px'/></td><td><input class='btn btn-primary' type='button' value='删除' onclick='removeBusinessTripAirTicket(&#39;"+airTicketId+"&#39;)' style='width:100px'></td></tr>");
		}
		/* 移除一条订房信息 */
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
			var reservationRemovedStr = "$('#"+reservationId+"')";
			var Fn = Function;
			var reservationRemoved = new Fn('return ' + reservationRemovedStr)();
			reservationRemoved.remove();
		}
		/* 移除一条机票信息 */
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
			var airTicketRemovedStr = "$('#"+airTicketId+"')";
			var Fn = Function;
			var airTicketRemoved = new Fn('return ' + airTicketRemovedStr)();
			airTicketRemoved.remove();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs" id="updateTitle">
		<li><a href="${ctx}/fa/businessTrip/toBusinessTripTaskList">出差任务列表</a></li>
	    <li class="active"><a href="">出差申请追加</a></li>
	</ul>
	<form id="insertBusinessTripForm" target="mainFrame" action="${ctx}/fa/businessTrip/updateBusinessTripInsertInfo" method="post" class="breadcrumb form-search">
		<sys:message content="${message}"/>
		<input id="reservationEveryNum" name="reservationEveryNum" value="0" type="hidden">
		<input id="airTicketEveryNum" name="airTicketEveryNum" value="0" type="hidden">
		<input name="businessTripApplicationId" value="${businessTripApplicationId}" type="hidden">
		<input name="insertFlag" value="yes" type="hidden">
		<div style="background:#f9f9f9; text-align:center">
			<label style="font-weight:bold; font-size:15px">部门：${businessTripApplication.office.name}</label>
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
						<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:170px;"
							value="<fmt:formatDate value="${businessTripApplication.endDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					</td>
				</tr>
			</table>
		</div>
		<div style="background:#40abe9"><label style="font-weight:bold">订房信息</label></div>
		<div>
			<table class="table table-striped table-bsordered table-condensed">
				<thead><tr><th>出差城市</th><th>具体地点</th><th>入住日期</th><th>退房日期</th><th>共计天数</th><th>备注</th><th>删除记录</th></thead>
				<tbody>
					<c:forEach items="${businessTripReservationList}" var="businessTripReservation">
						<tr>
							<td>${businessTripReservation.city}</td>
							<td>${businessTripReservation.workPlace}</td>
							<td><fmt:formatDate value="${businessTripReservation.beginDate}" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${businessTripReservation.endDate}" pattern="yyyy-MM-dd"/></td>
							<td>${businessTripReservation.days}</td>
							<td>${businessTripReservation.remark}</td>
							<td>不可删除</td>
						</tr>
					</c:forEach>
					<tr id="reservationButton">
						<td colspan="7" style="text-align:center"><input id="addBusinessTripReservation11" class="btn btn-primary" type="button" value="+" onclick="addBusinessTripReservation()" style="width:100px"></td>
					</tr> 
				</tbody>
			</table>
		</div>
		<div style="background:#40abe9"><label style="font-weight:bold">机票信息</label></div>
		<div>
			<table class="table table-striped table-bsordered table-condensed">
				<thead><tr><th style="width:15%">出行时间</th><th>机票价格</th><th>起始城市</th><th>目的城市</th><th>申请理由</th><th>删除记录</th></tr></thead>
				<tbody>
					<c:forEach items="${businessTripAirTicketList}" var="businessTripAirTicket">
						<tr>
							<td><fmt:formatDate value="${businessTripAirTicket.flyDate}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td>${businessTripAirTicket.amount}</td>
							<td>${businessTripAirTicket.startLocation}</td>
							<td>${businessTripAirTicket.arrivedLocation}</td>
							<td>${businessTripAirTicket.remark}</td>
							<td>不可删除</td>
						</tr>
					</c:forEach>
					<tr id="airTicketButton">
						<td colspan="6" style="text-align:center"><input class="btn btn-primary" type="button" value="+" onclick="addBusinessTripAirTicket()" style="width:100px"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<input id="commitButton" class="btn btn-primary" type="submit" value="提交" style="width:100px"/>
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" style="width:100px"/>
		</div>
	</form>
</body>
</html>