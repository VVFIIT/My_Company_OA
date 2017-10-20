<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>考勤列表</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
        	s$("#year").val(d.getFullYear());
        	$("#month").val(((d.getMonth() + 1)+'').replace(/^(.)$/,'0$1')) 
        	alert($("#year").val(d.getFullYear()));
        });
        function attendanceShow() {

            window.location.href = "${ctx}/oa/attendance/show";
        }
        function updateAttendance() {
            loading('正在跳转，请稍等...');
            $("#listForm").attr("action", "${ctx}/cms/category/updateSort");
            $("#listForm").submit();
        }
    </script>
</head>
<body>
	<ul class="nav nav-tabs">
	    <li class="active"><a href="${ctx}/oa/attendance/showAll">考勤列表</a></li>
	</ul>
	
	<form:form id="attSearchListForm" modelAttribute="attendance" action="${ctx}/oa/attendance/showAll" method="post" class="breadcrumb form-search">
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
	<sys:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th>姓名</th>
			<th>考勤提交状态</th>
			<th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${list}" var="attendance">
			<tr>
				<td>${attendance.name}</td>
				<td>
					${attendance.processStatus}
					
				</td>
				<td><a target="_blank" href="${ctx}/oa/attendance/show">查看</a></td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
