<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审批管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		
			$("#inputFormApproveSave").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');		

					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">

		<li><a href="${ctx}/vehicle/vehicleProcess/">我的报销任务</a></li>
		<li><a href="${ctx}/vehicle/vehicleProcess/">报销申请审批</a></li>
	</ul>
	<form:form id="inputFormApproveSave" modelAttribute="reimburseMain" action="${ctx}/fa/reimburse/approveSave" method="post" class="form-horizontal">
	    <form:hidden id="flag" path="act.flag"/>	
	     <input name="procInstId" type="hidden" value="${reimburseMain.procInstId}"/>		 
		 <input name="status" type="hidden" value="${reimburseMain.status}"/>	
		 <input name="id" type="hidden" value="${reimburseMain.id}"/>
		<sys:message content="${message}"/>

		<fieldset>	
			<table class="table-form">

				<tr>
					<td class="tit">您的意见</td>
					<td colspan="5">
						<form:textarea path="act.comment" class="required" rows="5" maxlength="20" cssStyle="width:500px"/> 
					</td>
				</tr>
			</table>
		</fieldset>
		<div class="form-actions">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="同 意" onclick="$('#flag').val('yes')"/>&nbsp;
			 	<input id="btnSubmit" class="btn btn-inverse" type="submit" value="驳 回" onclick="$('#flag').val('no')"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>

	</form:form>
</body>
</html>
