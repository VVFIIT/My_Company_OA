<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出差任务列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#FAApproveForm").validate({
				submitHandler: function(form){
					loading('正在提交审批，请稍等...');
					form.submit();
				},
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/fa/businessTrip/toBusinessTripTaskList">出差任务列表</a></li>
		<li class="active"><a href="">出差财务审批</a></li>
	</ul>
	<form:form id="FAApproveForm" modelAttribute="businessTripHotelHelper" action="${ctx}/fa/businessTrip/approveBusinessTripInfo_FA" method="post" class="breadcrumb form-search">
		<form:hidden id="FAFlag" path="businessTripApplication.FAFlag"/>
		<form:hidden id="id" path="businessTripApplication.id"/>
		<div style="background:#40abe9; margin-bottom:10px"><label style="font-weight:bold">您的意见</label></div>
		<div>
			<fieldset>
				<form:textarea path="businessTripApplication.FAComment" class="required"  maxlength="200" cssStyle="width:50%"/>
			</fieldset>
		</div>
		<div style="background:#40abe9; margin-top:10px"><label style="font-weight:bold">酒店信息</label></div>
		<table class="table table-striped table-bsordered table-condensed">
			<tr>
				<td><label style="font-weight:bold">住宿酒店</label></td>
				<td><form:input path="businessTripHotel.hotel" maxlength="50" class="required"/></td>
			</tr>
			<tr>
				<td><label style="font-weight:bold">具体地址</label></td>
				<td><form:input path="businessTripHotel.address" maxlength="50" class="required"/></td>
			</tr>
			<tr>
				<td><label style="font-weight:bold">酒店联系人</label></td>
				<td><form:input path="businessTripHotel.contact" maxlength="50" class="required"/></td>
			</tr>
			<tr>
				<td><label style="font-weight:bold">联系电话</label></td>
				<td><form:input path="businessTripHotel.contactPhone" maxlength="50" class="required"/></td>
			</tr>
		</table>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="同 意" onclick="$('#FAFlag').val('yes')"/>&nbsp;
			<!-- <input id="btnSubmit" class="btn btn-inverse" type="submit" value="驳 回" onclick="$('#flag').val('no')"/>&nbsp;	 -->
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>	
	</form:form>
</body>
</html>