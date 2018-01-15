<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待办任务</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		/**
		 * 签收任务
		 */
		function claim(taskId) {
			$.get('${ctx}/act/task/claim' ,{taskId: taskId}, function(data) {
				if (data == 'true'){
		        	top.$.jBox.tip('签收完成');
		            location = '${ctx}/act/task/todo/';
				}else{
		        	top.$.jBox.tip('签收失败');
				}
		    });
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/act/task/todo/">待办任务</a></li>
	
	</ul>
	<form:form id="searchForm" modelAttribute="act" action="${ctx}/act/task/todo/" method="get" class="breadcrumb form-search">
		<div>
		
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>任务</th>
				<th>部门</th>
				<th>申请人</th>
				<th>当前环节</th>
				<th>操作</th>
			
			</tr>
		</thead>
		<tbody>
		  <tr>
			<c:forEach items="${list}" var="att">
			<td>${att.act.procDef.name}</td> 
			<td>${att.department}</td>
			<td>${att.name}</td>
			<td>${att.act.task.name}</td>
			<td>
			<a href="${ctx}/oa/attendance/toApproval?taskId=${task.id}&taskName=${fns:urlEncode(task.name)}&taskDefKey=${task.taskDefinitionKey}&procInsId=${task.processInstanceId}&procDefId=${task.processDefinitionId}&status=${status}&resourceName=${procDef.resourceName}">审批</a>
			</td>
			 </c:forEach>
			
		 </tr>
		
		</tbody>
	</table>
</body>
</html>
