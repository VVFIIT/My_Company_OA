<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出差任务列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/fa/businessTrip/toBusinessTripTaskList">出差任务列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="businessTripApplication" action="${ctx}/fa/businessTrip/toBusinessTripTaskList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>出差类型：</label>
				<form:input path="type" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Search"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>任务标题</th>
				<th>申请人姓名</th>
				<th>申请人部门</th>
				<th>出差类型</th>
				<th>出差开始日期</th>
				<th>预计结束日期</th>
				<th>任务状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="businessTripApplication">
			<tr>
				<td>
					${businessTripApplication.act.vars.map.title}
				</td>
				<td>
					${businessTripApplication.applicant.name}
				</td>
				<td>
					${businessTripApplication.office.name}
				</td>
				<td>
					${businessTripApplication.type}
				</td>
				<td>
					<fmt:formatDate value="${businessTripApplication.beginDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${businessTripApplication.endDate}" pattern="yyyy-MM-dd"/>
				</td>
				<c:if test="${businessTripApplication.status=='10'}"><td>待经理审批</td></c:if>
				<c:if test="${businessTripApplication.status=='20'}"><td>待财务审批</td></c:if>
				<c:if test="${businessTripApplication.status=='30'}"><td>经理审批驳回</td></c:if>
				<c:if test="${businessTripApplication.status=='40'}"><td>财务审批驳回</td></c:if>
				<c:if test="${businessTripApplication.status=='50'}"><td>审批通过</td></c:if>
				<td>
    				<a href="${ctx}/fa/businessTrip/toupdateBusinessTripInfo?id=${businessTripApplication.id}">修改</a>
					<a href="${ctx}/fa/businessTrip/deleteBusinessTripInfo?id=${businessTripApplication.id}" onclick="return confirmx('确认要删除吗？', this.href)">删除</a>
					<a href="${ctx}/fa/businessTrip/approveBusinessTripInfo?id=${businessTripApplication.id}">审批</a>
					<a href="${ctx}/fa/businessTrip/showBusinessTripInfo?id=${businessTripApplication.id}">查看</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>