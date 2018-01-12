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
		<li class="active"><a href="${ctx}/fa/businessTrip/toBusinessTripInfoList">出差信息查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="businessTripApplication" action="${ctx}/fa/businessTrip/toBusinessTripInfoList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>出差项目：</label>
				<%-- <form:input path="type" htmlEscape="false" maxlength="64" class="input-medium"/> --%>
				<form:select path="project.name" class="input-medium">
					<form:option value="" label=""/>
					<c:forEach items="${projectNameList}" var="projectName">
						<form:option value="${projectName}" label="${projectName}"/>
					</c:forEach>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="Search"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>申请人姓名</th>
				<th>申请人部门</th>
				<th>出差类型</th>
				<th>出差项目</th>
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
					${businessTripApplication.applicant.name}
				</td>
				<td>
					${businessTripApplication.office.name}
				</td>
				<td>
					${businessTripApplication.type}
				</td>
				<td>
					${businessTripApplication.project.name}
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
					<c:if test="${businessTripApplication.status=='50'}">
						<c:if test="${businessTripApplication.applicant.id==loginUserId}">
							<a href="${ctx}/fa/businessTrip/toInsertBusinessTripInfo?id=${businessTripApplication.id}">追加</a>
						</c:if>
					</c:if>
					<a href="${ctx}/fa/businessTrip/toShowBusinessTripInfo?id=${businessTripApplication.id}&mode=search">查看</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>