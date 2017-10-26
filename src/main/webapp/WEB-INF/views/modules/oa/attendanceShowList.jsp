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
	    <li class="active"><a>考勤查看</a></li>
	</ul>
	<form:form id="attInsertListForm" modelAttribute="attendanceMonth_InsertList" target="mainFrame" action="${ctx}/oa/attendance/attendanceInsertList" method="post" class="breadcrumb form-search">
		<table id="attendanceTable" class="table table-striped table-bsordered table-condensed">
			<thead><tr><th>日期</th><th>星期</th><th>工作地点</th><th>考勤状态</th></thead>
			<tbody>
				<c:forEach items="${attendanceMonth.attendanceStatus}" var="attendanceday" varStatus="status">
					<tr>
						<td>${attendanceMonth.year}/${attendanceMonth.month}/${attendanceday.date}</td>
						<td>${attendanceday.week}</td>
						<td>${attendanceday.location}</td>
						<td>${fns:getDictLabel(attendanceday.status,'oa_attendance_status','')}</td>
					</tr>
				</c:forEach>
				<tr>
					<td></td><td></td><td></td>
					<td><a href="${ctx}/oa/attendance/returnIndexPage"><input class="btn btn-primary" type="button" value="返回"/></a></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>