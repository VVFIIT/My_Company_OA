<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤添加</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form:form id="attSearchListForm" modelAttribute="attendance" action="${ctx}/oa/attendance/attendanceSearchList" method="post" class="breadcrumb form-search">
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
	<form:form id="attInsertListForm" modelAttribute="attendance" action="${ctx}/oa/attendance/attendanceInsertList" method="post" class="breadcrumb form-search">
		<table id="attendanceTable" class="table table-striped table-bsordered table-condensed">
			<thead><tr><th>日期</th><th>星期</th><th>工作地点</th><th>考勤状态</th></thead>
			<tbody>
				<c:forEach items="${attendanceList}" var="attendance">
					<tr>
						<td>${attendance.date}</td>
						<td>${attendance.week}</td>
						<td><input type="text" name="address" value="大连市"></td>
						<td>
							<form:select path="attStatus" class="input-medium">
								<form:options items="${fns:getDictList('oa_attendance_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="返回"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>