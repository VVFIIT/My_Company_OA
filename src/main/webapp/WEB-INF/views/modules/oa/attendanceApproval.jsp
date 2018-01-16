<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审批管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#attApprovalForm").validate({
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
		<li><a href="${ctx}/act/task/todo/">考勤任务</a></li>
		<li class="active"><a href="">考勤审批</a></li>
	</ul>
	<form:form id="attApprovalForm" modelAttribute="attendanceMonth" target="mainFrame" action="${ctx}/oa/attendanceApproval/save" method="post" class="breadcrumb form-search">
		<form:hidden id="flag" path="act.flag"/>	
		<form:input path="procInsId" type="hidden"/>		
		<form:input path="act.taskDefKey" type="hidden"/>
		<form:input path="act.taskId" type="hidden"/>
		<form:input path="email" type="hidden"/>
		<sys:message content="${message}"/>
		<div style="background:#f9f9f9; text-align:center">
			<label style="font-weight:bold; font-size:15px">姓名：${attendanceMonth.name}</label>
			<label style="font-weight:bold; font-size:15px">部门：${attendanceMonth.department}</label>
		</div>
		<c:if test="${attendanceMonth.act.taskDefKey eq 'HRApprovalAttendance'}">
			<div style="background:#40abe9; margin-bottom:10px"><label style="font-weight:bold">经理意见：</label></div>
			<div>${attendanceMonth.PMComment}</div>
		</c:if>
		<div style="background:#40abe9; margin-bottom:10px; margin-top:10px"><label style="font-weight:bold">您的意见：</label></div>
		<div>
			<fieldset>
				<form:textarea path="act.comment" class="required"  maxlength="200" cssStyle="width:50%"/>
			</fieldset>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="同 意" onclick="$('#flag').val('yes')"/>&nbsp;
			<input id="btnSubmit" class="btn btn-inverse" type="submit" value="驳 回" onclick="$('#flag').val('no')"/>&nbsp;	
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>		
	
		<table id="attendanceTable" class="table table-striped table-bsordered table-condensed">
			<thead><tr><th style="width:25%">日期</th><th style="width:25%">星期</th><th style="width:25%">工作地点</th><th style="width:25%">考勤状态</th></thead>
			<tbody>
				<c:forEach items="${attendanceMonth.attendanceStatus}" var="attendanceday" varStatus="status">
					<tr>
						<td>${attendanceMonth.year}/${attendanceMonth.month}/${attendanceday.date}</td>
						<td>${attendanceday.week}</td>
						<td>${attendanceday.location}</td>
						<td>${fns:getDictLabel(attendanceday.status,'oa_attendance_status','')}</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>

	</form:form>
</body>
</html>
