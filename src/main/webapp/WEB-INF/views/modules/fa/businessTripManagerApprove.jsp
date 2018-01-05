<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出差任务列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#managerApproveForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/fa/businessTrip/toBusinessTripTaskList">出差任务列表</a></li>
		<li class="active"><a href="${ctx}/fa/businessTrip/toApproveBusinessTripInfo_Manager">出差经理审批</a></li>
	</ul>
	<form:form id="managerApproveForm" modelAttribute="businessTripApplication" action="${ctx}/fa/businessTrip/approveBusinessTripInfo_Manager" method="post" class="breadcrumb form-search">
		<form:hidden id="managerFlag" path="managerFlag"/>
		<form:hidden id="id" path="id"/>
		<fieldset>
			<legend>${testAudit.act.taskName}</legend>
			<table class="table-form">
				<tr>
					<td class="tit">您的意见:</td>
				</tr>
				<tr>
					<td colspan="5">
						<form:textarea path="ManagerComment" class="required" rows="3" maxlength="20" cssStyle="width:700px"/>
					</td>
				</tr>
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="同 意" onclick="$('#managerFlag').val('yes')"/>&nbsp;
			<!-- <input id="btnSubmit" class="btn btn-inverse" type="submit" value="驳 回" onclick="$('#flag').val('no')"/>&nbsp;	 -->
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>	
	</form:form>
</body>
</html>