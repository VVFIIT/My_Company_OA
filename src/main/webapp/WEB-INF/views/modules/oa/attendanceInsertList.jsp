<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤添加</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function success(){
			alert("添加成功");
		}
	</script>
</head>
<body>
	<form:form id="attInsertListForm" modelAttribute="attendanceMonth_InsertList" target="mainFrame" action="${ctx}/oa/attendance/attendanceInsertList" method="post" class="breadcrumb form-search">
		<table id="attendanceTable" class="table table-striped table-bsordered table-condensed">
			<thead><tr><th>日期</th><th>星期</th><th>工作地点</th><th>考勤状态</th></thead>
			<tbody>
				<c:forEach items="${attendanceMonth1.attendanceStatus}" var="attendanceday" varStatus="status">
					<tr>
						<td>${attendanceMonth1.year}/${attendanceMonth1.month}/${attendanceday.date}</td>
						<td>${attendanceday.week}</td>
						<td><form:input path="${fns:getDefaultLocation(status.count)}" htmlEscape="false" maxlength="50" class="required"/></td>
						<td>
							<form:select path="${fns:getDefaultStatus(status.count)}" class="input-medium">
								<form:options items="${fns:getDictList('oa_attendance_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td></td><td></td><td></td>
					<td><input id="btnSubmit" class="btn btn-primary" type="submit" value="提交" onclick="success()" style="margin-left:110px"/></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>