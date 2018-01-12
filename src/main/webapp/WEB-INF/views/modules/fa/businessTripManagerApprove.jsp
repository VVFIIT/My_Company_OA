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
		<li class="active"><a href="">出差经理审批</a></li>
	</ul>
	<form:form id="managerApproveForm" modelAttribute="businessTripApplication" action="${ctx}/fa/businessTrip/approveBusinessTripInfo_Manager" method="post" class="breadcrumb form-search">
		<form:hidden id="managerFlag" path="managerFlag"/>
		<form:hidden id="id" path="id"/>
		<div style="background:#40abe9; margin-bottom:10px"><label style="font-weight:bold">您的意见</label></div>
		<div>
			<fieldset>
				<form:textarea path="managerComment" class="required"  maxlength="200" cssStyle="width:50%"/>
			</fieldset>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="同 意" onclick="$('#managerFlag').val('yes')"/>&nbsp;
			<input id="btnSubmit" class="btn btn-inverse" type="submit" value="驳 回" onclick="$('#managerFlag').val('no')"/>&nbsp;	
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>	
	</form:form>
</body>
</html>