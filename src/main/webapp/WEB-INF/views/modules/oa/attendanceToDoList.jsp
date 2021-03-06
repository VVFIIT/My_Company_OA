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
		<li class="active"><a href="${ctx}/act/task/todo/">考勤任务</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="act" action="${ctx}/act/task/todo/" method="get" class="breadcrumb form-search">
		<div>
		
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:20%">流程名称</th>
				<th style="width:15%">部门</th>
				<th style="width:15%">申请人</th>
				<th style="width:20%">当前环节</th>
				<th style="width:20%">创建时间</th>
				<th style="width:10%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="att">
				<tr>
					<td>${att.act.vars.map.title}</td> 
					<td>${att.department}</td>
					<td>${att.name}</td>
					<td>${att.act.task.name}</td>
					<td><fmt:formatDate value="${att.act.task.createTime}" type="both"/></td>
					<td>
						<a href="${ctx}/oa/attendance/toApproval?id=${att.id}&taskId=${att.act.task.id}&taskDefKey=${att.act.task.taskDefinitionKey}&procInsId=${att.procInsId}">审批</a>
					</td>
				</tr>
			</c:forEach>
		 
		</tbody>
	</table>
</body>
</html>
