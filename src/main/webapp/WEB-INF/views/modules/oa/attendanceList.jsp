<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>考勤列表</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });

        <%--function insertAttendance() {--%>
            <%--&lt;%&ndash;loading('正在跳转，请稍等...');&ndash;%&gt;--%>
            <%--&lt;%&ndash;$("#listForm").attr("action", "${ctx}/oa/attendance/insert");&ndash;%&gt;--%>
            <%--&lt;%&ndash;$("#listForm").submit();&ndash;%&gt;--%>
            <%--window.location.href = "${ctx}/oa/attendance/insert";--%>
        <%--}--%>
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
        
        function noInsertMonth() {
        	var mode = "${MODE}";
        	if(mode == "noInsertMonth"){
        		alert("您没有可以添加的月份！");
        	}
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/oa/attendance/">考勤列表</a></li>
    <li><a href="${ctx}/oa/attendance/insert" onclick="noInsertMonth()">考勤添加</a></li>
</ul>
<form:form id="searchForm" modelAttribute="attendanceMonth" action="${ctx}/oa/attendance/"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
</form:form>
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
    <tbody>
    <c:forEach items="${page.list}" var="attendanceMonth">
        <tr>
            <td>${fns:abbr(attendanceMonth.year,30)}年${fns:abbr(attendanceMonth.month,30)}月</td>
            <td>${fns:abbr(attendanceMonth.attendanceDayStatus.normalDay,30)}</td>
            <td>${fns:abbr(attendanceMonth.attendanceDayStatus.travelDayShort,30)}</td>
            <td>${fns:abbr(attendanceMonth.attendanceDayStatus.travelDayLong,30)}</td>
            <td>${fns:abbr(attendanceMonth.attendanceDayStatus.overtimeDay,30)}</td>
            <td>${fns:abbr(attendanceMonth.attendanceDayStatus.leaveDay,30)}</td>
            <td>${fns:abbr(attendanceMonth.attendanceDayStatus.paidLeaveDay,30)}</td>
            <td>${fns:abbr(attendanceMonth.attendanceDayStatus.sickLeaveDay,30)}</td>
            <td>${fns:getDictLabel(attendanceMonth.processStatus,'oa_attendance_check_status','')}</td>
            <td>
                <a href="${ctx}/oa/attendance/searchAttendanceInformation?id=${attendanceMonth.id}">查看</a>
                <a href="${ctx}/oa/attendance/modifyAttendanceInformation?id=${attendanceMonth.id}&processStatus=${attendanceMonth.processStatus}" style="${fns:getCheckStatus(attendanceMonth.processStatus)}">修改</a>
                <a id="processStatus" style="${fns:getCheckStatus(attendanceMonth.processStatus)}"
                   href="${ctx}/oa/attendance/checkProcessStatus?id=${attendanceMonth.id}&processStatus=${attendanceMonth.processStatus}"
                   onclick="return confirmx('确认要提交该考勤吗？', this.href)">提交</a>
                    <%--<a href=""--%>
                    <%--onclick="return confirmx('确认要删除该考勤吗？', this.href)">删除</a>--%>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>
