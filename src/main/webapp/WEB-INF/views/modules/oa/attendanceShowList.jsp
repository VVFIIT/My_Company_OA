<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs" id="updateTitle">
	    <li><a href="${ctx}/oa/attendance/">考勤列表</a></li>
	    <li class="active"><a href="${ctx}/oa/attendance/insert">考勤查看</a></li>
	</ul>
	<form:form id="attInsertListForm" modelAttribute="attendanceMonth_InsertList" target="mainFrame" action="${ctx}/oa/attendance/attendanceInsertList" method="post" class="breadcrumb form-search">
		<table id="attendanceTable" class="table table-striped table-bsordered table-condensed">
			<thead><tr><th>日期</th><th>星期</th><th>工作地点</th><th>考勤状态</th></thead>
			<tbody>
				<c:forEach items="${attendanceMonth.attendanceStatus}" var="attendanceday" varStatus="status">
					<tr>
						<td>${attendanceMonth.year}/${attendanceMonth.month}/${attendanceday.date}</td>
						<td>${attendanceday.week}</td>
						<td><form:input path="${fns:getDefaultLocation(status.count)}" htmlEscape="false" maxlength="50" class="required" disable="true"/></td>
						<td>
							<form:select path="${fns:getDefaultStatus(status.count)}" class="input-medium" disable="true">
								<form:options items="${fns:getDictList('oa_attendance_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form:form>
</body>
</html>