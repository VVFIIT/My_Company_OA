<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤添加</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form:form id="attInsertListForm" modelAttribute="attendanceMonth" action="${ctx}/oa/attendance/attendanceInsertList" method="post" class="breadcrumb form-search">
		<table id="attendanceTable" class="table table-striped table-bsordered table-condensed">
			<thead><tr><th>日期</th><th>星期</th><th>工作地点</th><th>考勤状态</th></thead>
			<tbody>
				<c:forEach items="${attendanceDayList}" var="attendanceday" varStatus="status">
					<tr>
						<td>${attendanceday.date}</td>
						<td>${attendanceday.week}</td>
						<td><form:input path="location" value="大连市" htmlEscape="false" maxlength="50" class="required"/></td>
						<td>
							<form:select path="${fns:getStatus(${status.index})}">
								<form:options items="${fns:getDictList('oa_attendance_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</td>
					</tr>
					<form:input path="date" name="date" type="hidden" value="${attendanceday.date}"/>
					<form:input path="week" name="week" type="hidden" value="${attendanceday.week}"/>
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