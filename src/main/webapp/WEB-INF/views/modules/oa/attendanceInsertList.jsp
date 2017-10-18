<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤添加</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form:form id="attInsertListForm" modelAttribute="attendance" action="${ctx}/oa/attendance/attendanceInsertList" method="post" class="breadcrumb form-search">
		<table id="attendanceTable" class="table table-striped table-bsordered table-condensed">
			<thead><tr><th>日期</th><th>星期</th><th>工作地点</th><th>考勤状态</th></thead>
			<tbody>
				<c:forEach items="${attendanceList}" var="attendance">
					<tr>
						<td>${attendance.date}</td>
						<td>${attendance.week}</td>
						<td><form:input path="city" value="大连市" htmlEscape="false" maxlength="50" class="required"/></td>
						<td>
							<form:select path="attStatus" value="公休日" class="input-medium">
								<form:options items="${fns:getDictList('oa_attendance_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</td>
					</tr>
					<form:input path="date" name="date" type="hidden" value="${attendance.date}"/>
					<form:input path="week" name="week" type="hidden" value="${attendance.week}"/>
				</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>