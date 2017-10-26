<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤添加</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
        function isExistMonth() {
        	var startYear = ${startYear};
        	var startMonth = ${startMonth};
        	var endYear = ${endYear};
        	var endMonth = ${endMonth};
        	var year = document.getElementById("inputYear").value;
        	var month = document.getElementById("inputMonth").value;
        	var iframe = document.getElementById("downFrame");
        	iframe.style.display = "";
        	if(year<startYear || year>endYear || (year==startYear && month<startMonth) || (year==endYear && month>endMonth)){
        		var iframe = document.getElementById("downFrame");
            	iframe.style.display = "none";
        		alert("该日期超出范围！不可选择");
        	}
        	<c:forEach items="${existAttendanceMonthList}" var="existAttendanceMonth"> 
        		if("${existAttendanceMonth.year}" == year && "${existAttendanceMonth.month}" == month){
        			var iframe = document.getElementById("downFrame");
                	iframe.style.display = "none";
                	alert("该年月考勤已存在，不需要添加！");
        		}
        	</c:forEach>
        }
    </script>
</head>
<body>
	<ul class="nav nav-tabs">
	    <li><a href="${ctx}/oa/attendance/">考勤列表</a></li>
	    <li class="active"><a href="${ctx}/oa/attendance/insert">考勤添加</a></li>
	</ul>
	<form:form id="attSearchListForm" modelAttribute="attendanceMonth_Insert" target="downFrame" action="${ctx}/oa/attendance/attendanceSearchList" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>请选择年份：</label>
				<form:select path="year" id="inputYear" class="input-medium">
					<form:options items="${fns:getDictList('oa_year_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>请选择月份：</label>
				<form:select path="month" id="inputMonth" class="input-medium">
					<form:options items="${fns:getDictList('oa_month_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="确定" onclick="isExistMonth()"/></li>
			<li class="btns"><a href="${ctx}/oa/attendance/returnIndexPage"><input class="btn btn-primary" type="button" value="返回"/></a></li>
		</ul>
	</form:form>
	<div>
		<iframe id="downFrame" name="downFrame" scrolling="yes" frameborder="no" width="100%" height="400"></iframe>
	</div>
</body>
</html>