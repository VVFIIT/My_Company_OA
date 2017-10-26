<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>查看考勤</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/oa/attendance/showAll">考勤列表</a></li>
		<li class="active"><a href="${ctx}/oa/attendance/show">考勤详情</a></li>
	</ul>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<td>姓名：<input type="text" style="width: 120px;"
				value="${attendanceMonth.name}" disabled></td>
			<td>部门：<input type="text" style="width: 120px;"
				value="${attendanceMonth.department}" disabled></td>
		</tr>
	</table>




	<table id="attendanceTable"
		class="table table-striped table-bsordered table-condensed">
		<thead>
			<tr>
				<th>日期</th>
				<th>星期</th>
				<th>工作地点</th>
				<th>考勤状态</th>
		</thead>
		<tbody>
			<c:forEach items="${attendanceMonth.attendanceStatus}"
				var="attendanceDay" varStatus="status">
				<tr>
					<td>${attendanceMonth.year}/${attendanceMonth.month}/${attendanceDay.date}</td>
					<td><input type="text" style="width: 120px;"
						value="${attendanceDay.week}" disabled></td>
					<td><input type="text" style="width: 120px;"
						value="${attendanceDay.location}" disabled></td>
					<td>						
						<c:if test="${attendanceDay.status ==8}"> 
							<input type="text" style="width: 120px;color:#FF0000;" value="${fns:getDictLabel(attendanceDay.status,'oa_attendance_status','')}" disabled>
						</c:if>						
						<c:if test="${attendanceDay.status !=8}"> 
							<input type="text" style="width: 120px;" value="${fns:getDictLabel(attendanceDay.status,'oa_attendance_status','')}" disabled>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>
