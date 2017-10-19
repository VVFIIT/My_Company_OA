<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>考勤列表</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });

        function insertAttendance() {
            <%--loading('正在跳转，请稍等...');--%>
            <%--$("#listForm").attr("action", "${ctx}/oa/attendance/insert");--%>
            <%--$("#listForm").submit();--%>
            window.location.href = "${ctx}/oa/attendance/insert";
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/oa/attendance/">考勤列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="oa" action="${ctx}/oa/attendance/" method="get"
           class="breadcrumb form-search">
    	  <ul class="ul-form">
				<li><label>姓名：</label>
					
				</li>
				<li><label>部门：</label>
					
				</li>
			</ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>日期</th>
        <th>正常出勤</th>
        <th>出差-短期</th>
        <th>出差-长期</th>
        <th>加班</th>
        <th>请假</th>
        <th>带薪假</th>
        <th>病假</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>2017年1月</td>
        <td>22</td>
        <td>0</td>
        <td>0</td>
        <td>0</td>
        <td>0</td>
        <td>0</td>
        <td>0</td>
        <td>确认</td>
        <td>
            <a href="${ctx}/oa/attendance/attendanceUpdate">查看</a>
            <a href=""
               onclick="return confirmx('确认要修改考勤吗？', this.href)">修改</a>
            <a href="">提交</a>
            <a href=""
               onclick="return confirmx('确认要删除该考勤吗？', this.href)">删除</a>
        </td>
        <%--</shiro:hasPermission>--%>
        <%--</td>--%>
    </tr>
    <c:forEach items="${list}" var="oa">
        <%--<c:set var="task" value="${act.task}"/>--%>
        <%--<c:set var="vars" value="${act.vars}"/>--%>
        <%--<c:set var="procDef" value="${act.procDef}"/>&lt;%&ndash;--%>
        <%--<c:set var="procExecUrl" value="${act.procExecUrl}" /> &ndash;%&gt;--%>
        <%--<c:set var="status" value="${act.status}"/>--%>
        <%--<tr>--%>
        <%--<td><fmt:formatDate value="${testAudit.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
        <%--<td>--%>
        <%--<a target="_blank"--%>
        <%--href="${pageContext.request.contextPath}/act/diagram-viewer?processDefinitionId=${task.processDefinitionId}&processInstanceId=${task.processInstanceId}">${task.name}</a>--%>
        <%--</td>--%>
        <%--<td>${procDef.name}</td>--%>
        <%--<td><b title='流程版本号'>V: ${procDef.version}</b></td>--%>
        <%--<td><fmt:formatDate value="${task.createTime}" type="both"/></td>--%>
        <%--<td>--%>
        <%--<c:if test="${empty task.assignee}">--%>
        <%--<a href="javascript:claim('${task.id}');">签收任务</a>--%>
        <%--</c:if>--%>
        <%--<c:if test="${not empty task.assignee}">&lt;%&ndash;--%>
        <%--<a href="${ctx}${procExecUrl}/exec/${task.taskDefinitionKey}?procInsId=${task.processInstanceId}&act.taskId=${task.id}">办理</a> &ndash;%&gt;--%>
        <%--<a href="${ctx}/act/task/form?taskId=${task.id}&taskName=${fns:urlEncode(task.name)}&taskDefKey=${task.taskDefinitionKey}&procInsId=${task.processInstanceId}&procDefId=${task.processDefinitionId}&status=${status}">任务办理</a>--%>
        <%--</c:if>--%>
        <%--<shiro:hasPermission name="oa:attendance:edit">--%>
        <%--<td>--%>
        <%--<a href="${ctx}/oa/attendance/look?id=${testAudit.id}">查看</a>--%>
        <%--<a href="${ctx}/oa/attendance/insert?id=${testAudit.id}" onclick="return confirmx('确认要修改考勤吗？', this.href)">修改</a>--%>
        <%--<a href="${ctx}/oa/attendance/ok?id=${testAudit.id}">提交</a>--%>
        <%--<a href="${ctx}/oa/attendance/delete?id=${testAudit.id}" onclick="return confirmx('确认要删除该考勤吗？', this.href)">删除</a>--%>
        <%--</td>--%>
        <%--</shiro:hasPermission>--%>
        <%--</td>--%>
        <%--</tr>--%>
    </c:forEach>
    </tbody>
</table>
</body>
</html>