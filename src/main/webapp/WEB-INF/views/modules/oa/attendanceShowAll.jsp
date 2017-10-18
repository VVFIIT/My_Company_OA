<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>考勤列表</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
        	s$("#year").val(d.getFullYear());
        	$("#month").val(((d.getMonth() + 1)+'').replace(/^(.)$/,'0$1')) 
        	alert($("#year").val(d.getFullYear()));
        });
        function attendanceShow() {

            window.location.href = "${ctx}/oa/attendance/show";
        }
        function updateAttendance() {
            loading('正在跳转，请稍等...');
            $("#listForm").attr("action", "${ctx}/cms/category/updateSort");
            $("#listForm").submit();
        }
    </script>
</head>
<body>
	<ul class="nav nav-tabs">
	    <li class="active"><a href="${ctx}/oa/attendance/showAll">考勤列表</a></li>
	</ul>
	
	<form:form id="attSearchListForm" modelAttribute="attendance" action="${ctx}/oa/attendance/showAll" method="post" class="breadcrumb form-search">
			<ul class="ul-form">
				<li><label>请选择年份：</label>
					<form:select path="year" class="input-medium">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('oa_year_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</li>
				<li><label>请选择月份：</label>
					<form:select path="month" class="input-medium">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('oa_month_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</li>
				<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" modelAttribute="attendance" class="table table-striped table-bordered table-condensed">
	    <thead>
	    <tr>
	        <th>姓名</th>
	        <th>考勤提交状态</th>
	        <th>操作</th>
	    </tr>
	    </thead>
	    <tbody>
	    <tr>
	        <td>高振东</td>
	        <td>提交</td>
	        <td>
	        	<div>
	            	<input id="btnSubmit" class="btn btn-primary" type="button" value="查看" onclick="attendanceShow();"/>
	            </div>
	        </td>
	    </tr>
	</tbody> 
	<%-- <table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th>姓名</th>
			<th>考勤提交状态</th>
			<th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="leave">
			<c:set var="task" value="${leave.task }" />
			<c:set var="pi" value="${leave.processInstance }" />
			<c:set var="hpi" value="${leave.historicProcessInstance }" />
			<tr>
				<td>${leave.id}</td>
				<td>${leave.createBy.name}</td>
				<td><fmt:formatDate value="${leave.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${leave.reason}</td>
				<c:if test="${not empty task}">
					<td>${task.name}</td>
					<td><a target="_blank" href="${ctx}/act/task/trace/photo/${task.processDefinitionId}/${task.executionId}">跟踪</a></td>
				</c:if>
				<c:if test="${empty task}">
					<td>已结束</td>
					<td>&nbsp;</td>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table> --%>
</body>
</html>
