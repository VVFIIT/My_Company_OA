<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤添加</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form:form id="attSearchListForm" modelAttribute="attendance" target="downFrame" action="${ctx}/oa/attendance/attendanceSearchList" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>请选择年份：</label>
				<form:select path="year" id="defaultYearHidden" class="input-medium">
					<form:options items="${fns:getDictList('oa_year_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>请选择月份：</label>
				<form:select path="month" class="input-medium">
					<form:options items="${fns:getDictList('oa_month_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		</ul>
	</form:form>
	<div>
		<iframe id="downFrame" name="downFrame" scrolling="yes" frameborder="no" width="100%" height="450"></iframe>
	</div>
</body>
</html>