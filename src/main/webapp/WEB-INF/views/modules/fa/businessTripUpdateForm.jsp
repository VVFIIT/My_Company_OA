<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出差申请</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var num = ${reservationEveryNum};
			addInitTr(num);
			$("#businessTripApplyForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
			});
		});
		function addInitTr(num){
			for(var i=1;i<num;i++){
				var reservationEveryNum = $("#reservationEveryNum").val();
				var a= reservationEveryNum.split("");
				var trMaxNum = parseInt(a[a.length-1])+1;
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
				var nameStr = "' name='";
				
				var number111 = parseInt(1);
				
				var number2 = Number(1);
				
				//var reservationTypeValue = '${JSON.parse(businessTripReservationList.get(number111)).workPlace}';
				
				/* var reservationTypeValue = '${JSON.stringify(businessTripReservationList.get(number111)).workPlace}';
			
				var reservationTypeValue1 = '${businessTripReservationList.get(1).workPlace}';
				
				var reservationTypeValue2 = '${businessTripReservationList.get(number111).workPlace}';
				
				alert(reservationTypeValue);
				
				alert(reservationTypeValue1);
				
				alert(reservationTypeValue2); 
				
				var reservationTypeValue3 = '${businessTripReservationList.get(number2).workPlace}';
				
				*/
				
				alert(47);
			
				
				var keyList = eval('${businessTripReservationList}'); 
				
				alert(keyList);
		//		var values = $("#paramsValues").val(); 
				
				
				
				//alert(list[1]);
				
				//alert(JSON.stringify(list));
				//alert(JSON.parse(list));
				
			
				
			//	alert(list.get(Number(1)));
			
			//	alert(JSON.stringify(list));
				
			//	alert(list.get(Number(1)));
				
				
				$("#reservationButton").before("<tr id='"+reservationId+"'><td><input id='"+reservationTypeId+nameStr+reservationTypeId+sameStr+reservationCityId+nameStr+reservationCityId+sameStr+reservationWorkPlaceId+nameStr+reservationWorkPlaceId+sameStr+reservationBeginDateId+nameStr+reservationBeginDateId+
						"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate' style='width:150px;' value='<fmt:formatDate value='${businessTripModel.businessTripReservationList.get(0).beginDate}' pattern='yyyy-MM-dd'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd&#39;});'/></td><td><input id='"+reservationEndDateId+nameStr+reservationEndDateId+
						"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate' style='width:150px;' value='<fmt:formatDate value='${businessTripModel.businessTripReservationList.get(0).endDate}' pattern='yyyy-MM-dd'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd&#39;});'/></td><td><input id='"+reservationDaysId+nameStr+reservationDaysId+
						"' maxlength='50' class='required' style='width:150px'/></td><td><input class='btn btn-primary' type='button' value='删除' onclick='removeBusinessTripReservation(&#39;"+reservationId+"&#39;)' style='width:100px'></td></tr>");
			}
		}
		/* 增加一条订房信息 */
		function addBusinessTripReservation(){
			var reservationEveryNum = $("#reservationEveryNum").val();
			var a= reservationEveryNum.split("");
			var trMaxNum = parseInt(a[a.length-1])+1;
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
			var nameStr = "' name='";
			$("#reservationButton").before("<tr id='"+reservationId+"'><td><input id='"+reservationTypeId+nameStr+reservationTypeId+sameStr+reservationCityId+nameStr+reservationCityId+sameStr+reservationWorkPlaceId+nameStr+reservationWorkPlaceId+sameStr+reservationBeginDateId+nameStr+reservationBeginDateId+
					"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate' style='width:150px;' value='<fmt:formatDate value='${businessTripModel.businessTripReservationList.get(0).beginDate}' pattern='yyyy-MM-dd'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd&#39;});'/></td><td><input id='"+reservationEndDateId+nameStr+reservationEndDateId+
					"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate' style='width:150px;' value='<fmt:formatDate value='${businessTripModel.businessTripReservationList.get(0).endDate}' pattern='yyyy-MM-dd'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd&#39;});'/></td><td><input id='"+reservationDaysId+nameStr+reservationDaysId+
					"' maxlength='50' class='required' style='width:150px'/></td><td><input class='btn btn-primary' type='button' value='删除' onclick='removeBusinessTripReservation(&#39;"+reservationId+"&#39;)' style='width:100px'></td></tr>");
		}
		/* 增加一条机票信息 */
		function addBusinessTripAirTicket(){
			var airTicketEveryNum = $("#airTicketEveryNum").val();
			var a= airTicketEveryNum.split("");
			var trMaxNum = parseInt(a[a.length-1])+1;
			airTicketEveryNum = airTicketEveryNum+trMaxNum;
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
					"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate' style='width:190px;' value='<fmt:formatDate value='${businessTripModel.businessTripReservationList.get(0).beginDate}' pattern='yyyy-MM-dd HH:mm'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm&#39;});'/></td><td><input id='"
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
	    <li class="active"><a href="${ctx}/fa/businessTrip/toupdateBusinessTripInfo">出差信息修改</a></li>
	</ul>
	<form id="businessTripApplyForm" target="mainFrame" action="${ctx}/fa/businessTrip/updateBusinessTripInfo" method="post" class="breadcrumb form-search">
		<sys:message content="${message}"/>
		<input id="reservationEveryNum" name="reservationEveryNum" value="1" type="hidden">
		<input id="airTicketEveryNum" name="airTicketEveryNum" value="1" type="hidden">
		<div style="background:#f9f9f9; text-align:center">
			<label style="font-weight:bold; font-size:15px">部门：${businessTripApplication.office.name}</label>
			<label style="font-weight:bold; font-size:15px">申请人：${businessTripApplication.applicant.name}</label>
		</div>
		<div style="background:#40abe9"><label style="font-weight:bold">出差信息</label></div>
		<div>
			<table class="table table-striped table-bsordered table-condensed">
				<tr>
					<td><label style="font-weight:bold">共同出差人</label></td>
					<td><input id="togetherId" name="togetherId" value="${businessTripApplication.togetherId}" maxlength="50" class="required"/></td>
					<td><label style="font-weight:bold">联系方式</label></td>
					<td><input id="phone" name="phone" value="${businessTripApplication.phone}" maxlength="50" class="required"/></td>
				</tr>
				<tr>
					<td><label style="font-weight:bold">身份证号</label></td>
					<td><input id="IDNo" name="IDNo" value="${businessTripApplication.IDNo}" maxlength="50" class="required"/></td>
					<td><label style="font-weight:bold">项目名称</label></td>
					<td><input id="projectId" name="projectId" value="${businessTripApplication.projectId}" maxlength="50" class="required"/></td>
				</tr>
				<tr>
					<td><label style="font-weight:bold">出差类型</label></td>
					<td><input id="type" name="type" value="${businessTripApplication.type}" maxlength="50" class="required"/></td>
					<td><label style="font-weight:bold">出差事由</label></td>
					<td><input id="remark" name="remark" value="${businessTripApplication.remark}"  maxlength="50" class="required"/></td>
				</tr>
				<tr>
					<td><label style="font-weight:bold">出差日期</label></td>
					<td>
						<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:200px;"
							value="<fmt:formatDate value="${businessTripApplication.beginDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					</td>
					<td><label style="font-weight:bold">客户经理</label></td>
					<td><input id="managerId" name="managerId" value="${businessTripApplication.managerId}" maxlength="50" class="required"/></td>
				</tr>
			</table>
		</div>
		<div style="background:#40abe9"><label style="font-weight:bold">订房信息</label></div>
		<div>
			<table class="table table-striped table-bsordered table-condensed">
				<thead><tr><th>订房类型</th><th>出差城市</th><th>具体地点</th><th>入住日期</th><th>退房日期</th><th>共计天数</th><th>删除记录</th></thead>
				<tbody>
					<%-- <c:forEach items="${businessTripReservationList}" var="businessTripReservation" varStatus="index">
						<tr>
							<td><input style="width:150px" id="reservationType${index.count}" name="reservationType${index.count}" value="${businessTripReservation.type}" maxlength="50" class="required"/></td>
							<td><input style="width:150px" id="reservationCity${index.count}" name="reservationCity${index.count}" value="${businessTripReservation.city}" maxlength="50" class="required"/></td>
							<td><input style="width:150px" id="reservationWorkPlace${index.count}" name="reservationWorkPlace${index.count}" value="${businessTripReservation.workPlace}" maxlength="50" class="required"/></td>
							<td><input id="reservationBeginDate${index.count}" name="reservationBeginDate${index.count}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:150px;"
								value="<fmt:formatDate value="${businessTripReservation.beginDate}" pattern="yyyy-MM-dd"/>"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
							<td><input id="reservationEndDate${index.count}" name="reservationEndDate${index.count}" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:150px;"
								value="<fmt:formatDate value="${businessTripReservation.endDate}" pattern="yyyy-MM-dd"/>"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
							<td><input style="width:150px" id="reservationDays"+index.count name="reservationDays"+index.count value="${businessTripReservation.days}" maxlength="50" class="required"/></td>
							<td><input class="btn btn-primary" type="button" value="删除" onclick="removeBusinessTripReservation('reservation1')" style="width:100px"></td>
						</tr>
					</c:forEach>
					<tr id="reservationButton">
						<td colspan="7" style="text-align:center"><input class="btn btn-primary" type="button" value="+" onclick="addBusinessTripReservation()" style="width:100px"></td>
					</tr> --%>
					 <tr id="reservation1">
						<td><input style="width:150px" id="reservationType1" name="reservationType1" value="${businessTripReservationList.get(0).type}" maxlength="50" class="required"/></td>
						<td><input style="width:150px" id="reservationCity1" name="reservationCity1" value="${businessTripReservationList.get(0).city}" maxlength="50" class="required"/></td>
						<td><input style="width:150px" id="reservationWorkPlace1" name="reservationWorkPlace1" value="${businessTripReservationList.get(0).workPlace}" maxlength="50" class="required"/></td>
						<td>
						<input id="reservationBeginDate1" name="reservationBeginDate1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:150px;"
							value="<fmt:formatDate value="${businessTripReservationList.get(0).beginDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
						</td>
						<td>
						<input id="reservationEndDate1" name="reservationEndDate1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:150px;"
							value="<fmt:formatDate value="${businessTripReservationList.get(0).endDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
						</td>
						<td><input style="width:150px" id="reservationDays1" name="reservationDays1" value="${businessTripReservationList.get(0).days}" maxlength="50" class="required"/></td>
						<td><input class="btn btn-primary" type="button" value="删除" onclick="removeBusinessTripReservation('reservation1')" style="width:100px"></td>
					</tr>
					<tr id="reservationButton">
						<td colspan="7" style="text-align:center"><input id="addBusinessTripReservation11" class="btn btn-primary" type="button" value="+" onclick="addBusinessTripReservation()" style="width:100px"></td>
					</tr> 
				</tbody>
			</table>
		</div>
		<%-- <div style="background:#40abe9"><label style="font-weight:bold">机票信息</label></div>
		<div>
			<table class="table table-striped table-bsordered table-condensed">
				<thead><tr><th style="width:15%">出行时间</th><th>机票价格</th><th>起始城市</th><th>目的城市</th><th>申请理由</th><th>删除记录</th></tr></thead>
				<tbody>
					<tr id="airTicket1">
						<td>
						<input id="airTicketFlyDate1" name="airTicketFlyDate1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:190px;"
							value="<fmt:formatDate value="${businessTripModel.businessTripReservationList.get(0).endDate}" pattern="yyyy-MM-dd HH:mm"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});"/>
						</td>
						<td><input style="width:190px" id="airTicketAmount1" name="airTicketAmount1" maxlength="50" class="required"/></td>
						<td><input style="width:190px" id="airTicketStartLocation1" name="airTicketStartLocation1" maxlength="50" class="required"/></td>
						<td><input style="width:190px" id="airTicketArrivedLocation1" name="airTicketArrivedLocation1" maxlength="50" class="required"/></td>
						<td><input style="width:190px" id="airTicketRemark1" name="airTicketRemark1" maxlength="50" class="required"/></td>
						<td><input class="btn btn-primary" type="button" value="删除" onclick="removeBusinessTripAirTicket('airTicket1')" style="width:100px"></td>
					</tr>
					<tr id="airTicketButton">
						<td colspan="6" style="text-align:center"><input class="btn btn-primary" type="button" value="+" onclick="addBusinessTripAirTicket()" style="width:100px"></td>
					</tr>
				</tbody>
			</table>
		</div> --%>
		<div>
			<input id="commitButton" class="btn btn-primary" type="submit" value="提交" style="width:100px"/>
		</div>
	</form>
</body>
</html>