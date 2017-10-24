<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>考勤列表</title>
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
    <li><a href="${ctx}/oa/attendance/insert">考勤添加</a></li>
</ul>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>日期</th>
        <th>正常出勤</th>
        <th>出差-短期</th>
        <th>出差-长期</th>
        <th>加班</th>
        <th>请假</th>
        <th>带薪假</th>
        <th>病假</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <form:form id="searchForm" modelAttribute="attendanceMonth" action="${ctx}/oa/attendance/" method="get"
               class="breadcrumb form-search" path="dataScope">
        <tbody>
        <c:forEach items="${list}" var="attendanceMonth">
            <tr>
                <td>${fns:abbr(attendanceMonth.year,30)}年${fns:abbr(attendanceMonth.month,30)}月</td>
                <%--<td>${fns:abbr(attendanceMonth.attendanceDayStatus.normalDay,30)}</td>--%>
                <td>${fns:getDictLabel(attendanceMonth.attendanceDayStatus.normalDay,'oa_attendance_status','')}</td>
                <%--<td>${fns:abbr(attendanceMonth.attendanceDayStatus.travelDayShort,30)}</td>--%>
                <td>${fns:getDictLabel(attendanceMonth.attendanceDayStatus.travelDayShort,'oa_attendance_status','')}</td>
                <%--<td>${fns:abbr(attendanceMonth.attendanceDayStatus.travelDayLong,30)}</td>--%>
                <td>${fns:getDictLabel(attendanceMonth.attendanceDayStatus.travelDayLong,'oa_attendance_status','')}</td>
                <%--<td>${fns:abbr(attendanceMonth.attendanceDayStatus.overtimeDay,30)}</td>--%>
                <td>${fns:getDictLabel(attendanceMonth.attendanceDayStatus.overtimeDay,'oa_attendance_status','')}</td>
                <%--<td>${fns:abbr(attendanceMonth.attendanceDayStatus.leaveDay,30)}</td>--%>
                <td>${fns:getDictLabel(attendanceMonth.attendanceDayStatus.leaveDay,'oa_attendance_status','')}</td>
            <%--<td>${fns:abbr(attendanceMonth.attendanceDayStatus.paidLeaveDay,30)}</td>--%>
                <td>${fns:getDictLabel(attendanceMonth.attendanceDayStatus.paidLeaveDay,'oa_attendance_status','')}</td>
                <%--<td>${fns:abbr(attendanceMonth.attendanceDayStatus.sickLeaveDay,30)}</td>--%>
                <td>${fns:getDictLabel(attendanceMonth.attendanceDayStatus.sickLeaveDay,'oa_attendance_status','')}</td>
                <td>${fns:getDictLabel(attendanceMonth.processStatus,'oa_attendance_check_status','')}</td>
                <td>
                    <a href="${ctx}/oa/attendance/insertList">查看</a>
                    <a href="${ctx}/oa/attendance/modifyAttendanceInformation">修改</a>
                    <a id="processStatus" style="${fns:getCheckStatus(attendanceMonth.processStatus)}"
                       href="${ctx}/oa/attendance/checkProcessStatus?id=${attendanceMonth.id}&processStatus=${attendanceMonth.processStatus}" onclick="return confirmx('确认要提交该考勤吗？', this.href)">提交</a>
                    <%--<a href=""--%>
                       <%--onclick="return confirmx('确认要删除该考勤吗？', this.href)">删除</a>--%>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </form:form>
</table>
</body>
</html>
