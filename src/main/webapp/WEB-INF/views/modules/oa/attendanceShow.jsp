<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>查看考勤</title>
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
<form:form id="searchForm" modelAttribute="attendanceMonth"  method="get"
           class="breadcrumb form-search">
    	  <ul class="ul-form">
				<li><label>姓名：${attendanceMonth.name}</label>
					
				</li>
				<li><label>部门：${attehdahceMonth.department}</label>
					
				</li>
			</ul>
</form:form>
<sys:message content="${message}"/>
<form:form id="attInsertListForm" modelAttribute="attendanceMonth"  method="post" class="breadcrumb form-search">
		<table id="attendanceTable" class="table table-striped table-bsordered table-condensed">
			<thead><tr><th>日期</th><th>星期</th><th>工作地点</th><th>考勤状态</th></thead>
			<tbody>
				<c:forEach items="${attendanceMonth.attendanceStatus}" var="attendanceDay" varStatus="status">
					<tr>
						<td>${attendanceMonth.year}/${attendanceMonth.month}/${attendanceDay.date}</td>
						<td>${attendanceDay.week}</td>
						<td><form:input path="year" value="大连市" htmlEscape="false" maxlength="50" class="required"/></td>
						<td>
							<form:select path="${fns:getDefaultStatus(status.count)}" class="input-medium">
								<form:options items="${fns:getDictList('oa_attendance_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</td>
					</tr>
					<form:input path="year" name="date" type="hidden" value="${attendanceday.date}"/>
					<form:input path="year" name="week" type="hidden" value="${attendanceday.week}"/>
				</c:forEach>
			</tbody>
		</table>
	</form:form>
</body>
</html>
