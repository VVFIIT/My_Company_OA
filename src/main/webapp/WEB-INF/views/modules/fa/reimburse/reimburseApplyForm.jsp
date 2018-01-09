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
		
		//添加一条长途车费
		function addNewLongDistance(){			
			
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

			$("#longDistanceButton").before("<tr id='"+longDistanceId+"'><td><input id='"+createDateLongDistanceId+nameStr+createDateLongDistanceId+
					"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate' style='width:190px;' value='<fmt:formatDate value='${businessTripModel.businessTripReservationList.get(0).beginDate}' pattern='yyyy-MM-dd HH:mm'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm&#39;});'/></td><td><input id='"
					+itemNoLongDistanceId+nameStr+itemNoLongDistanceId+sameStr+projectNameLongDistanceId+nameStr+projectNameLongDistanceId+sameStr+remarkLongDistanceId+nameStr+remarkLongDistanceId+sameStr+amountLongDistanceId+nameStr+amountLongDistanceId+"' maxlength='50' class='required' style='width:190px'/></td><td><input class='btn btn-primary' type='button' value='删除' onclick='removeLongDistance(&#39;"+longDistanceId+"&#39;)' style='width:100px'></td></tr>");		
					
		}
		
		//添加出租车费
		function addNewTaxi(){			
			
			var taxiEveryNum = $("#taxiEveryNum").val(); 
			var a= taxiEveryNum.split("");
			var trMaxNum = parseInt(a[a.length-1])+1;
			taxiEveryNum = taxiEveryNum+trMaxNum;
			$("#taxiEveryNum").val(taxiEveryNum);
			
			var createDateTaxiId = 'createDateTaxi'+trMaxNum;
			var itemNoTaxiId = 'itemNoTaxi'+trMaxNum;
			var projectNameTaxiId = 'projectNameTaxi'+trMaxNum;
			var remarkTaxiId = 'remarkTaxi'+trMaxNum;
			var timeTaxiId = 'timeTaxi'+trMaxNum;
			var departureLocationTaxiId = 'departureLocationTaxi'+trMaxNum;
			var arrivedLocationTaxiId = 'arrivedLocationTaxi'+trMaxNum;
			var amountTaxiId = 'amountTaxi'+trMaxNum;
			var taxiId = 'taxi'+trMaxNum;
			var sameStr = "' maxlength='50' class='required' style='width:150px' type='text'/></td><td><input id='";
			var nameStr = "' name='";

			$("#taxiButton").before("<tr id='"+taxiId+"'><td><input id='"+createDateTaxiId+nameStr+createDateTaxiId+
					"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate' style='width:190px;' value='<fmt:formatDate value='${businessTripModel.businessTripReservationList.get(0).beginDate}' pattern='yyyy-MM-dd HH:mm'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm&#39;});'/></td><td><input id='"
					+itemNoTaxiId+nameStr+itemNoTaxiId+sameStr+projectNameTaxiId+nameStr+projectNameTaxiId+sameStr+remarkTaxiId+nameStr+remarkTaxiId+sameStr+timeTaxiId+nameStr+timeTaxiId+sameStr+departureLocationTaxiId+nameStr+departureLocationTaxiId+sameStr+arrivedLocationTaxiId+nameStr+arrivedLocationTaxiId+sameStr+amountTaxiId+nameStr+amountTaxiId+"' maxlength='50' class='required' style='width:190px'/></td><td><input class='btn btn-primary' type='button' value='删除' onclick='removeLongDistance(&#39;"+taxiId+"&#39;)' style='width:100px'></td></tr>");		
					
		}

		//添加一条招待费
		function addHospitality(){			
			
			var hospitalityEveryNum = $("#hospitalityEveryNum").val(); 
			var a= hospitalityEveryNum.split("");
			var trMaxNum = parseInt(a[a.length-1])+1;
			hospitalityEveryNum = hospitalityEveryNum+trMaxNum;
			$("#hospitalityEveryNum").val(hospitalityEveryNum);
			
			var createDateHospitalityId = 'createDateHospitality'+trMaxNum;
			var itemNoHospitalityId = 'itemNoHospitality'+trMaxNum;
			var projectNameHospitalityId = 'projectNameHospitality'+trMaxNum;
			var clientNameId = 'clientNameHospitality'+trMaxNum;
			var inviteesNameId = 'inviteesNameHospitality'+trMaxNum;
			var invitedPositionId = 'invitedPositionHospitality'+trMaxNum;
			var numberId = 'numberHospitality'+trMaxNum;
			var amountHospitalityId = 'amountHospitality'+trMaxNum;
			var hospitalityId = 'hospitality'+trMaxNum;
			var sameStr = "' maxlength='50' class='required' style='width:150px' type='text'/></td><td><input id='";
			var nameStr = "' name='";

			$("#hospitalityButton").before("<tr id='"+hospitalityId+"'><td><input id='"+createDateHospitalityId+nameStr+createDateHospitalityId+
					"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate' style='width:190px;' value='<fmt:formatDate value='${businessTripModel.businessTripReservationList.get(0).beginDate}' pattern='yyyy-MM-dd HH:mm'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm&#39;});'/></td><td><input id='"
					+itemNoHospitalityId+nameStr+itemNoHospitalityId+sameStr+projectNameHospitalityId+nameStr+projectNameHospitalityId+sameStr+clientNameId+nameStr+clientNameId+sameStr+inviteesNameId+nameStr+inviteesNameId+sameStr+invitedPositionId+nameStr+invitedPositionId+sameStr+numberId+nameStr+numberId+sameStr+amountHospitalityId+nameStr+amountHospitalityId+"' maxlength='50' class='required' style='width:190px'/></td><td><input class='btn btn-primary' type='button' value='删除' onclick='removeLongDistance(&#39;"+hospitalityId+"&#39;)' style='width:100px'></td></tr>");		
					
		}
		
		//添加一条其他费
		function addNewOther(){			
			
			var otherEveryNum = $("#otherEveryNum").val(); 
			var a= otherEveryNum.split("");
			var trMaxNum = parseInt(a[a.length-1])+1;
			otherEveryNum = otherEveryNum+trMaxNum;
			$("#otherEveryNum").val(otherEveryNum);
			var createDateOtherId = 'createDateOther'+trMaxNum;
			var itemNoOtherId = 'itemNoOther'+trMaxNum;
			var projectNameOtherId = 'projectNameOther'+trMaxNum;
			var remarkOtherId = 'remarkOther'+trMaxNum;
			var amountOtherId = 'amountOther'+trMaxNum;
			var otherId = 'other'+trMaxNum;
			var sameStr = "' maxlength='50' class='required' style='width:150px' type='text'/></td><td><input id='";
			var nameStr = "' name='";

			$("#otherButton").before("<tr id='"+otherId+"'><td><input id='"+createDateOtherId+nameStr+createDateOtherId+
					"' type='text' readonly='readonly' maxlength='20' class='input-medium Wdate' style='width:190px;' value='<fmt:formatDate value='${businessTripModel.businessTripReservationList.get(0).beginDate}' pattern='yyyy-MM-dd HH:mm'/>' onclick='WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm&#39;});'/></td><td><input id='"
					+itemNoOtherId+nameStr+itemNoOtherId+sameStr+projectNameOtherId+nameStr+projectNameOtherId+sameStr+remarkOtherId+nameStr+remarkOtherId+sameStr+amountOtherId+nameStr+amountOtherId+"' maxlength='50' class='required' style='width:190px'/></td><td><input class='btn btn-primary' type='button' value='删除' onclick='removeOther(&#39;"+otherId+"&#39;)' style='width:100px'></td></tr>");		
					
		}
		
		//删除一条长途汽车	
		function removeLongDistance(longDistanceId){
			var longDistanceEveryNum = $("#longDistanceEveryNum").val();
			var idNum = longDistanceId.substring(20);
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
		
		//删除一条出租车费	
		function removeTaxi(longTaxiId){
			var taxiEveryNum = $("#taxiEveryNum").val();
			var idNum = taxiId.substring(12);
			var EveryNum = "";
			var a= taxiEveryNum.split("");
			for(var i=0;i<a.length;i++){
				if(a[i]!=idNum){
					EveryNum = EveryNum+a[i];
				}
			}
			$("#taxiEveryNum").val(EveryNum);
			var taxiRemovedStr = "$('#"+taxiId+"')";
			var Fn = Function;
			var taxiRemoved = new Fn('return ' + taxiRemovedStr)();
			taxiRemoved.remove();
		}
		
	
		
		//删除一条招待费	
		function removeHospitality(hospitalityId){
			var hospitalityEveryNum = $("#hospitalityEveryNum").val();
			var idNum = hospitalityId.substring(19);
			var EveryNum = "";
			var a= hospitalityEveryNum.split("");
			for(var i=0;i<a.length;i++){
				if(a[i]!=idNum){
					EveryNum = EveryNum+a[i];
				}
			}
			$("#hospitalityEveryNum").val(EveryNum);
			var hospitalityRemovedStr = "$('#"+hospitalityId+"')";
			var Fn = Function;
			var hospitalityRemoved = new Fn('return ' + hospitalityRemovedStr)();
			hospitalityRemoved.remove();
		}
		
		//删除一条其他费	
		function removeOther(otherId){
			var otherEveryNum = $("#otherEveryNum").val();
			var idNum = otherId.substring(13);
			var EveryNum = "";
			var a= otherEveryNum.split("");
			for(var i=0;i<a.length;i++){
				if(a[i]!=idNum){
					EveryNum = EveryNum+a[i];
				}
			}
			$("#otherEveryNum").val(EveryNum);
			var otherRemovedStr = "$('#"+otherId+"')";
			var Fn = Function;
			var otherRemoved = new Fn('return ' + otherRemovedStr)();
			otherRemoved.remove();
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs" id="updateTitle">
	    <li class="active"><a href="${ctx}/fa/reimburse/toApplyForm">报销申请</a></li>
	</ul>
	<form:form id="reimburseApplyForm" modelAttribute="reimburseMain" target="mainFrame" action="${ctx}/fa/reimburse/commitApplyForm" method="post" class="breadcrumb form-search">
		<input id="longDistanceEveryNum" name="longDistanceEveryNum" type="hidden" value="1">
		<input id="taxiEveryNum" name="taxiEveryNum" type="hidden" value="1">
		<input id="hospitalityEveryNum" name="hospitalityEveryNum" type="hidden" value="1">
		<input id="otherEveryNum" name="otherEveryNum" type="hidden" value="1">
		<div >
			<label style="font-weight:bold">部门：${reimburseMain.office.name}</label>
			<label style="font-weight:bold">申请人：${reimburseMain.applicant.name}</label>
		</div>
		<div ><label style="font-weight:bold">基本信息</label></div>
		<div>
			<table class="table table-striped table-bsordered table-condensed">
				<tr>
					
					<td><label style="font-weight:bold">申报日期</label></td>
					<td>
					<input id="applyDate" name="applyDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:200px;"
							value="<fmt:formatDate value="${reimburseMain.applyDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					
					</td>
					<td><label style="font-weight:bold">申报期间</label></td>
					<td>
					<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:200px;"
							value="<fmt:formatDate value="${reimburseMain.beginDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					
					</td>
					<td>
					<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:200px;"
							value="<fmt:formatDate value="${reimburseMain.endDate}" pattern="yyyy-MM-dd"/>"
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
							value="<fmt:formatDate value="${businessTripModel.hospitalityList.get(0).createDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
						<td><input style="width:150px" id="itemNoLongDistance1" name="itemNoLongDistance1" maxlength="50" type="text"/></td>
						<td><input style="width:150px" id=projectNameLongDistance1" name="projectNameLongDistance1" maxlength="50"  type="text" /></td>
						<td><input style="width:150px" id="remarkLongDistance1" name="remarkLongDistance1" maxlength="50"  type="text"/></td>
						<td><input style="width:150px" id="amountLongDistance1" name="amountLongDistance1" maxlength="50"  type="text"/></td>
						<td><input class="btn btn-primary" type="button" value="删除" onclick="removeLongDistance('longDistance1')" style="width:100px"></td>
					<tr id="longDistanceButton">
						<td colspan="7" style="text-align:center"><input class="btn btn-primary" type="button" value="+" onclick="addNewLongDistance()" style="width:100px"></td>
					</tr>
					
				</tbody>
			</table>
		</div>
		<div style="background:#40abe9"><label style="font-weight:bold">出租车费</label></div>
		<div>
			<table id="taxiTable" class="table table-striped table-bsordered table-condensed">
				<thead><tr><th>日期</th><th>项目编号</th><th>项目名称</th><th>摘要（内容、事由）</th><th>时间</th><th>出发地点</th><th>到达地点</th><th>金额</th><th>删除</th></thead>
				<tbody>
					<tr id="taxi">	
						<td><input id="createDateTaxi1" name="createDateTaxi1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:150px;"
							value="<fmt:formatDate value="${businessTripModel.hospitalityList.get(0).createDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>		
						<td><input style="width:150px" id="itemNoTaxi1" name="itemNoTaxi1" maxlength="50" type="text"/></td>
						<td><input style="width:150px" id=projectNameTaxi1" name="projectNameTaxi1" maxlength="50"  type="text" /></td>
						<td><input style="width:150px" id="remarkTaxi1" name="remarkTaxi1" maxlength="50"  type="text"/></td>
						<td><input style="width:150px" id="timeTaxi1" name="timeTaxi1" maxlength="50"  type="text"/></td>
						<td><input style="width:150px" id="departureLocationTaxi1" name="departureLocationTaxi1" maxlength="50"  type="text"/></td>
						<td><input style="width:150px" id="arrivedLocationTaxi1" name="arrivedLocationTaxi1" maxlength="50"  type="text"/></td>
						<td><input style="width:150px" id="amountTaxi1" name="amountTaxi1" maxlength="50"  type="text"/></td>
						<td><input class="btn btn-primary" type="button" value="删除" onclick="removeTaxi('taxi1')" style="width:100px"></td>
					<tr id="taxiButton">
						<td colspan="7" style="text-align:center"><input class="btn btn-primary" type="button" value="+" onclick="addNewTaxi()" style="width:100px"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="background:#40abe9"><label style="font-weight:bold">招待费</label></div>
		<div>
			<table id="hospitalityTable" class="table table-striped table-bsordered table-condensed">
				<thead><tr><th>日期</th><th>项目编号</th><th>项目名称</th><th>客户名称</th><th>受邀人姓名</th><th>受邀人职务</th><th>人数</th><th>金额</th><th>删除</th></thead>
				<tbody>
				<tr id="hospitality1">		
			
					<td>
					<input id="createDateHospitality1" name="createDateHospitality1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:150px;"
							value="<fmt:formatDate value="${businessTripModel.hospitalityList.get(0).createDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					</td>			
					<td><input style="width:150px" id="itemNoHospitality1" name="itemNoHospitality1" maxlength="50" type="text"/></td>
					<td><input style="width:150px" id=projectNameHospitality1" name="projectNameHospitality1" maxlength="50"  type="text" /></td>
					<td><input style="width:150px" id="clientNameHospitality1" name="clientNameHospitality1" maxlength="50"  type="text"/></td>
					<td><input style="width:150px" id="inviteesNameHospitality1" name="inviteesNameHospitality1" maxlength="50"  type="text"/></td>
					<td><input style="width:150px" id="invitedPositionHospitality1" name="invitedPositionHospitality1" maxlength="50"  type="text"/></td>
					<td><input style="width:150px" id="numberHospitality1" name="numberHospitality1" maxlength="50"  type="text"/></td>
					<td><input style="width:150px" id="amountHospitality1" name="amountHospitality1" maxlength="50"  type="text"/></td>
					<td><input class="btn btn-primary" type="button" value="删除" onclick="removeHospitality('hospitality1')" style="width:100px"></td>
				
					<tr id="hospitalityButton">
						<td colspan="7" style="text-align:center"><input class="btn btn-primary" type="button" value="+" onclick="addNewHospitality()" style="width:100px"></td>
					</tr>
					
				</tbody>
			</table>
		</div>
		<div style="background:#40abe9"><label style="font-weight:bold">其他费</label></div>
		<div>
			<table id="otherTable" class="table table-striped table-bsordered table-condensed">
				<thead><tr><th>日期</th><th>项目编号</th><th>项目名称</th><th>摘要（内容、事由）</th><th>金额</th><th>删除</th></thead>
				<tbody>
					<tr id="Other1">
						<td><input id="createDateOther1" name="createDateOther1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:150px;"
							value="<fmt:formatDate value="${businessTripModel.businessTripReservationList.get(0).beginDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
						<td><input style="width:150px" id="itemNoOther1" name="itemNoOther1" maxlength="50" type="text"/></td>
						<td><input style="width:150px" id=projectNameOther1" name="projectNameOther1" maxlength="50"  type="text" /></td>
						<td><input style="width:150px" id="remarkOther1" name="remarkOther1" maxlength="50"  type="text"/></td>
						<td><input style="width:150px" id="amountOther1" name="amountOther1" maxlength="50"  type="text"/></td>
						<td><input class="btn btn-primary" type="button" value="删除" onclick="removeOther('Other1')" style="width:100px"></td>
					<tr id="otherButton">
						<td colspan="7" style="text-align:center"><input class="btn btn-primary" type="button" value="+" onclick="addNewOther()" style="width:100px"></td>
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