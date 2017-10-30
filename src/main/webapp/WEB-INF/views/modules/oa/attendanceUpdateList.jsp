<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤修改</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function success(){
			alert("修改成功");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs" id="updateTitle">
	    <li><a href="${ctx}/oa/attendance/list">考勤列表</a></li>
	    <li class="active"><a>考勤修改</a></li>
	</ul>
	<form:form id="attInsertListForm" modelAttribute="attendanceMonth_InsertList" target="mainFrame" action="${ctx}/oa/attendance/attendanceUpdateList" method="post" class="breadcrumb form-search">
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
					<td><a href="${ctx}/oa/attendance/returnIndexPage" target="mainFrame"><input class="btn btn-primary" type="button" value="返回"/></a>
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="修改" onclick="success()" style="margin-left:50px"/></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>