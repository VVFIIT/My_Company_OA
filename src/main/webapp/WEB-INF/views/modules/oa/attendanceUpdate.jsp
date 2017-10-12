<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤添加</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form:form id="updateForm" modelAttribute="attendanceUpdate" action="${ctx}/oa/attendanceUpdate" method="post">
		<ul class="ul-form">
			<li><label>请选择月份：</label>
				<form:select path="month" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('oa_month_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		</ul>
	</form:form>
	<table id="attendanceTable" class="table table-striped table-bsordered table-condensed">
		<thead><tr><th>日期</th><th>星期</th><th>工作地点</th><th>考勤状态</th></thead>
		<tbody>
			<c:forEach items="${dayslist}" var="day">
				<tr>
					<td>${day}</td>
					<td>星期一</td>
					<td>大连市</td>
					<td>正常出勤</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>