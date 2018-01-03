<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报销申请</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#reimburseApplyForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
			});
		});
		
		
		function addLongDistance(){			
			
			var longDistanceEveryNum = $("#longDistanceEveryNum").val(); 
			var a= longDistanceEveryNum.split("");
			var trMaxNum = parseInt(a[a.length-1])+1;
			longDistanceEveryNum = longDistanceEveryNum+trMaxNum;
			$("#longDistanceEveryNum").val(longDistanceEveryNum);
			var createDateLongDistanceId = 'createDateLongDistance'+trMaxNum;
			var itemNoLongDistanceId = 'itemNoLongDistance'+trMaxNum;
			var projectNameLongDistanceId = 'projectNameLongDistance'+trMaxNum;
			var remarkLongDistanceId = 'remarkLongDistance'+trMaxNum;
			var amountLongDistanceId = 'amountLongDistance'+trMaxNum;
			var longDistanceId = 'longDistance'+trMaxNum;
			var sameStr = "' maxlength='50' class='required' style='width:150px' type='text'/></td><td><input id='";
			var nameStr = "' name='";
		/* 	$("#longDistanceButton").before("<tr id='"+longDistanceId+"'><td><input id='"+
					createDateLongDistanceId+nameStr+createDateLongDistanceId+sameStr+
					itemNoLongDistanceId+nameStr+itemNoLongDistanceId+sameStr+
					projectNameLongDistanceId+nameStr+projectNameLongDistanceId+sameStr+
					remarkLongDistanceId+nameStr+remarkLongDistanceId+sameStr+
					amountLongDistanceId+nameStr+amountLongDistanceId/></td></tr>"); */
		

			$("#longDistanceButton").before("<tr id='"+longDistanceId+"'><td><input id='"+createDateLongDistanceId+nameStr+createDateLongDistanceId+
					"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate' style='width:190px;' value='<fmt:formatDate value='${businessTripModel.businessTripReservationList.get(0).beginDate}' pattern='yyyy-MM-dd HH:mm'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm&#39;});'/></td><td><input id='"
					+itemNoLongDistanceId+nameStr+itemNoLongDistanceId+sameStr+projectNameLongDistanceId+nameStr+projectNameLongDistanceId+sameStr+remarkLongDistanceId+nameStr+remarkLongDistanceId+sameStr+amountLongDistanceId+nameStr+amountLongDistanceId+"' maxlength='50' class='required' style='width:190px'/></td><td><input class='btn btn-primary' type='button' value='删除' onclick='removeLongDistance(&#39;"+longDistanceId+"&#39;)' style='width:100px'></td></tr>");		
					
		}
		
		//删除一条长途汽车	
		function removeLongDistance(longDistanceId){
			var longDistanceEveryNum = $("#longDistanceEveryNum").val();
			var idNum = longDistanceId.substring(12);
			var EveryNum = "";
			var a= longDistanceEveryNum.split("");
			for(var i=0;i<a.length;i++){
				if(a[i]!=idNum){
					EveryNum = EveryNum+a[i];
				}
			}
			$("#longDistanceEveryNum").val(EveryNum);
			var longDistanceRemovedStr = "$('#"+longDistanceId+"')";
			var Fn = Function;
			var longDistanceRemoved = new Fn('return ' + longDistanceRemovedStr)();
			longDistanceRemoved.remove();
		}
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs" id="updateTitle">
	    <li class="active"><a href="${ctx}/fa/reimbursement/toApplyForm">报销申请</a></li>
	</ul>
	<form:form id="reimburseApplyForm" modelAttribute="reimburseModel" target="mainFrame" action="${ctx}/fa/reimburse/commitApplyForm" method="post" class="breadcrumb form-search">
		<input id="longDistanceEveryNum" name="longDistanceEveryNum" type="hidden" value="1">
		<div >
			<label style="font-weight:bold">部门：${reimburseModel.officeName}</label>
			<label style="font-weight:bold">申请人：${reimburseModel.userName}</label>
		</div>
		<div ><label style="font-weight:bold">基本信息</label></div>
		<div>
			<table class="table table-striped table-bsordered table-condensed">
				<tr>
					
					<td><label style="font-weight:bold">申报日期</label></td>
					<td>
					<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:200px;"
							value="<fmt:formatDate value="${businessTripModel.businessTripApplication.beginDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					
					</td>
					<td><label style="font-weight:bold">申报期间</label></td>
					<td>
					<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:200px;"
							value="<fmt:formatDate value="${businessTripModel.businessTripApplication.beginDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					
					</td>
					<td>
					<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:200px;"
							value="<fmt:formatDate value="${businessTripModel.businessTripApplication.beginDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					
					</td>
				</tr>
		
				
			</table>
		</div>
	 	
	 	<div style="background:#40abe9"><label style="font-weight:bold">长途交通车费</label></div>
		<div>
			<table id="longDistanceTable" class="table table-striped table-bsordered table-condensed">
				<thead><tr><th>日期</th><th>项目编号</th><th>项目名称</th><th>摘要（内容、事由）</th><th>金额</th><th>删除</th></thead>
				<tbody>
				
					<tr id="longDistance1">
						<td><input id="createDateLongDistance1" name="createDateLongDistance1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:150px;"
							value="<fmt:formatDate value="${businessTripModel.businessTripReservationList.get(0).beginDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
						<td><input style="width:150px" id="itemNoLongDistance1" name="itemNoLongDistance1" maxlength="50" class="required" type="text"/></td>
						<td><input style="width:150px" id=projectNameLongDistance1" name="projectNameLongDistance1" maxlength="50"  type="text" /></td>
						<td><input style="width:150px" id="remarkLongDistance1" name="remarkLongDistance1" maxlength="50"  type="text"/></td>
						<td><input style="width:150px" id="amountLongDistance1" name="amountLongDistance1" maxlength="50"  type="text"/></td>
						<td><input class="btn btn-primary" type="button" value="删除" onclick="removeLongDistance('longDistance1')" style="width:100px"></td>
					<tr id="longDistanceButton">
						<td colspan="7" style="text-align:center"><input class="btn btn-primary" type="button" value="+" onclick="addLongDistance()" style="width:100px"></td>
					</tr>
					
				</tbody>
			</table>
		</div>
		<div style="background:#40abe9"><label style="font-weight:bold">出租车费</label></div>
		<div>
			<table id="longDistanceTable" class="table table-striped table-bsordered table-condensed">
				<thead><tr><th>日期</th><th>项目编号</th><th>项目名称</th><th>摘要（内容、事由）</th><th>金额</th><th>删除</th></thead>
				<tbody>
				
				
					<tr>
						<td><input id="reservationBeginDate1" name="reservationBeginDate1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:150px;"
							value="<fmt:formatDate value="${businessTripModel.businessTripReservationList.get(0).beginDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
						<td><input style="width:150px" id="reservationType1" name="reservationType1" maxlength="50"  type="text"/></td>
						<td><input style="width:150px" id="reservationCity1" name="reservationCity1" maxlength="50"  type="text" /></td>
						<td><input style="width:150px" id="reservationWorkPlace1" name="reservationWorkPlace1" maxlength="50"  type="text"/></td>
						<td><input style="width:150px" id="reservationWorkPlace1" name="reservationWorkPlace1" maxlength="50"  type="text"/></td>
						<td><input class="btn btn-primary" type="button" value="删除" onclick="removeBusinessTripReservation('reservation1')" style="width:100px"></td>
					<tr id="longDistanceButton">
						<td colspan="7" style="text-align:center"><input class="btn btn-primary" type="button" value="+" onclick="addNew()" style="width:100px"></td>
					</tr>
					
				</tbody>
			</table>
		</div>
		<div style="background:#40abe9"><label style="font-weight:bold">招待费</label></div>
		<div>
			<table id="longDistanceTable" class="table table-striped table-bsordered table-condensed">
				<thead><tr><th>日期</th><th>项目编号</th><th>项目名称</th><th>摘要（内容、事由）</th><th>金额</th><th>删除</th></thead>
				<tbody>
				
				
					<tr>
						<td><input id="reservationBeginDate1" name="reservationBeginDate1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:150px;"
							value="<fmt:formatDate value="${businessTripModel.businessTripReservationList.get(0).beginDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
						<td><input style="width:150px" id="reservationType1" name="reservationType1" maxlength="50" type="text"/></td>
						<td><input style="width:150px" id="reservationCity1" name="reservationCity1" maxlength="50" type="text" /></td>
						<td><input style="width:150px" id="reservationWorkPlace1" name="reservationWorkPlace1" maxlength="50" type="text"/></td>
						<td><input style="width:150px" id="reservationWorkPlace1" name="reservationWorkPlace1" maxlength="50" type="text"/></td>
						<td><input class="btn btn-primary" type="button" value="删除" onclick="removeBusinessTripReservation('reservation1')" style="width:100px"></td>
					<tr id="reservationButton">
						<td colspan="7" style="text-align:center"><input class="btn btn-primary" type="button" value="+" onclick="addNew()" style="width:100px"></td>
					</tr>
					
				</tbody>
			</table>
		</div>
		<div style="background:#40abe9"><label style="font-weight:bold">其他费</label></div>
		<div>
			<table id="longDistanceTable" class="table table-striped table-bsordered table-condensed">
				<thead><tr><th>日期</th><th>项目编号</th><th>项目名称</th><th>摘要（内容、事由）</th><th>金额</th><th>删除</th></thead>
				<tbody>
				
				
					<tr>
						<td><input id="reservationBeginDate1" name="reservationBeginDate1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:150px;"
							value="<fmt:formatDate value="${businessTripModel.businessTripReservationList.get(0).beginDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
						<td><input style="width:150px" id="reservationType1" name="reservationType1" maxlength="50" type="text"/></td>
						<td><input style="width:150px" id="reservationCity1" name="reservationCity1" maxlength="50" type="text" /></td>
						<td><input style="width:150px" id="reservationWorkPlace1" name="reservationWorkPlace1" maxlength="50" type="text"/></td>
						<td><input style="width:150px" id="reservationWorkPlace1" name="reservationWorkPlace1" maxlength="50" type="text"/></td>
						<td><input class="btn btn-primary" type="button" value="删除" onclick="removeBusinessTripReservation('reservation1')" style="width:100px"></td>
					<tr id="reservationButton">
						<td colspan="7" style="text-align:center"><input class="btn btn-primary" type="button" value="+" onclick="addNew()" style="width:100px"></td>
					</tr>
					
				</tbody>
			</table>
		</div>
		
		<div>
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交" style="margin-left:50px"/>
		</div>
	</form:form>
</body>
</html>